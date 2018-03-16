/**
 * 这个javascript当中主要是做一个数据的初始化的操作和数据的校验的工作和通过ajax把数据传给后台
 */
//初始化的操作方法 jquery
$(function(){
	var shopId=getShopId('shopId');
	var isEdit=shopId?true:false;
	//定义跳转的变量
	//getshopinitinfo后台的这个方法主要是返回区域类别和商铺类别的信息
	var initUrl="/SSM2SpringBoot/shopadmin/getshopinitinfo";
	var registerUrl="/SSM2SpringBoot/shopadmin/shopRegister";
	//根据id查询商铺对象
	var getShopByIdUrl="/SSM2SpringBoot/shopadmin/getShopById?shopId="+shopId;
	//修改商铺进行提交
	var modifyShopUrl="/SSM2SpringBoot/shopadmin/modifyShop";
	//alert(initUrl);
	
	if(isEdit){
		getShopInitInfoShopId(shopId);
	}else{
		getShopInitInfo();
	}
	//定义一个初始商铺分类和区域分类的信息，这个方法是我们的前台的初始化页面的显示的方法
	function getShopInitInfo(){
		//进行调用getJson的方法,function是一个回调的方法
		$.getJSON(initUrl,function(data){
			if(data.success){
				//成功
				var tempHtml="";
				var tempAreaHtml="";
				//进行遍历商铺分类数据
				data.shopCategoryList.map(function(item,index){
					tempHtml+="<option data-id="+item.shopCategoryId+">"+
					item.shopCategoryName+"</option>";
				});
				//进行操作设置我们的验证码的操作
				
				//进行遍历区域分类数据
				data.areaList.map(function(item,index){
					tempAreaHtml+="<option data-id="+item.areaId+">"+
					item.areaName+"</option>";
				});
				//然后把数据设置到我们html的相应的位置
				$('#shop-category').html(tempHtml);
				$('#shop-area').html(tempAreaHtml);
			}
		});
		
		//进行定义我们的商铺的注册的方法
		$('#submit').click(function(){
			//定义一个变量先
			var shop={} ;//把数据封装到shop对象当中
			shop.shopName=$('#shop_name').val();
			shop.shopAddr=$('#shop_addr').val();
			shop.phone=$('#shop_phone').val();
			shop.shopDesc=$('#shop_desc').val();
			
			
			//进行把选择的店铺的分类信息传过去
			shop.shopCategory={
					shopCategoryId:$('#shop-category').find('option').not(function(){
						return !this.selected;
					}).data('id')
			};
			
			//进行把选择的店铺的区域信息传过去
			shop.area={
					areaId:$('#shop-area').find('option').not(function(){
						return !this.selected;
					}).data('id')
			};
			//下面去把图片数据转换成一个文件对象，然后通过ajax上传给后台
			var shopImg=$('#shop_img')[0].files[0];
			var formData=new FormData();
			formData.append('shopImg',shopImg);
			formData.append('shopStr',JSON.stringify(shop));
			//进行定义验证码的数据
			var verifyCodeActual=$('#shop-Kaptcha').val();
			if(!verifyCodeActual){
				$.toast('请输入验证码');
				return ;
			}
			formData.append('verifyCodeActual',verifyCodeActual);
			//上传数据给后台
			$.ajax({
				url:isEdit?modifyShopUrl:registerUrl,//上传url地址
				type:'POST', //上传方式
				data:formData, //上传数据
				contentType:false,//因为数据比较的混乱，所以数据类型无法限定
				processData:false,
				cache:false,
				success:function(data){ //接收后台返回的数据
					if(data.success){
						$.toast('提交成功!');
					}else{
						$.toast('提交失败!'+data.errMgs);
					}
					$('#verifyCode').click();
				}
			});
		});
	}
	 //下面这个方法是我们的店铺的修改显示页面的方法
	function getShopInitInfoShopId(shopId){
		
		//进行调用getJson的方法,function是一个回调的方法
		$.getJSON(getShopByIdUrl,function(data){
			//我们的后台的数据都放在这个data对象当中
			if(data.success){
				var shop=data.shop;
				//进行设置我们的页面的值
				$('#shop_name').val(shop.shopName);
				$('#shop_addr').val(shop.shopAddr);
				$('#shop_phone').val(shop.phone);
				$('#shop_desc').val(shop.shopDesc);
				//进行遍历商铺分类数据
				var shopCategory='<option data-id="'+shop.shopCategory.shopCategoryId+'"selected >'+
					shop.shopCategory.shopCategoryName+"</option>";
				
				//进行操作设置我们的验证码的操作
				var tempAreaHtml="";
				//进行遍历区域分类数据
				data.areaList.map(function(item,index){
					tempAreaHtml+="<option data-id="+item.areaId+">"+
					item.areaName+"</option>";
				});
				//然后把数据设置到我们html的相应的位置
				$('#shop-category').html(shopCategory);
				$('#shop-category').attr("disabled","disabled")
				$('#shop-area').html(tempAreaHtml);
				$("#shop-area option[data-id='"+shop.area.areaId+"']").attr("selected","selected");
			}
		});	
		//进行定义我们的商铺的修改的方法
		$('#submit').click(function(){
			//定义一个变量先
			var shop={} ;//把数据封装到shop对象当中
			shop.shopName=$('#shop_name').val();
			shop.shopAddr=$('#shop_addr').val();
			shop.phone=$('#shop_phone').val();
			shop.shopDesc=$('#shop_desc').val();
			shop.shopId=shopId;
			
			//进行把选择的店铺的分类信息传过去
			shop.shopCategory={
					shopCategoryId:$('#shop-category').find('option').not(function(){
						return !this.selected;
					}).data('id')
			};
			
			//进行把选择的店铺的区域信息传过去
			shop.area={
					areaId:$('#shop-area').find('option').not(function(){
						return !this.selected;
					}).data('id')
			};
			//下面去把图片数据转换成一个文件对象，然后通过ajax上传给后台
			var shopImg=$('#shop_img')[0].files[0];
			var formData=new FormData();
			formData.append('shopImg',shopImg);
			formData.append('shopStr',JSON.stringify(shop));
			//进行定义验证码的数据
			var verifyCodeActual=$('#shop-Kaptcha').val();
			if(!verifyCodeActual){
				$.toast('请输入验证码');
				return ;
			}
			formData.append('verifyCodeActual',verifyCodeActual);
			//上传数据给后台
			$.ajax({
				url:isEdit?modifyShopUrl:registerUrl,//上传url地址
				type:'POST', //上传方式
				data:formData, //上传数据
				contentType:false,//因为数据比较的混乱，所以数据类型无法限定
				processData:false,
				cache:false,
				success:function(data){ //接收后台返回的数据
					if(data.success){
						$.toast('提交成功!');
					}else{
						$.toast('提交失败!'+data.errMgs);
					}
					$('#verifyCode').click();
				}
			});
		});
	}
	
})