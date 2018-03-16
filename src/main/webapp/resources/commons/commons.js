/**
 * 这个是我们的点击更换验证码的操作
 */

function changerVerifyCode(img){
	//alert("哈哈");
	img.src="../kaptcha?"+Math.floor(Math.random()*100);
}

//根据传入进来的url来获取参数,这个方法还是有问题
function getShopId(name){
	var reg=new RegExp("(^|&)"+name+"=([^&]*)(&|$)");
	var r=window.location.search.substring(1).match(reg);
	if(r!=null){
		return decodeURIComponent(r[2]);
	}
	return '';
}