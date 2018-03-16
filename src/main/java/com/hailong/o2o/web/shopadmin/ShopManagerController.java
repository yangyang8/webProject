package com.hailong.o2o.web.shopadmin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hailong.o2o.dto.ShopExecution;
import com.hailong.o2o.entity.Area;
import com.hailong.o2o.entity.PersonInfo;
import com.hailong.o2o.entity.Shop;
import com.hailong.o2o.entity.ShopCategory;
import com.hailong.o2o.enums.ShopStatusEnums;
import com.hailong.o2o.service.AreaService;
import com.hailong.o2o.service.ShopCategoryService;
import com.hailong.o2o.service.ShopService;
import com.hailong.o2o.utils.HttpServletRequestUtils;
import com.hailong.o2o.utils.VerifyCodeUtils;

/**
 * 这个controller主要是用来实现店铺管理相关的逻辑
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/shopadmin")
public class ShopManagerController {
	@Autowired
	private ShopService shopService;
	// 创建出我们的ShopCategoryService的对象
	@Autowired
	private ShopCategoryService shopCategoryService;
	// 进行得到我们的AreaService的对象
	@Autowired
	private AreaService areaService;

	// 根据id得到我们的数据
	@SuppressWarnings("null")
	@RequestMapping(value = "/getShopById", method = { RequestMethod.GET })
	@ResponseBody
	public Map<String, Object> getShopById(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		Long id = HttpServletRequestUtils.getLong(request, "shopId");
		if (id == null && id < 0) {
			map.put("success", false);
			map.put("errMgs", "传入的id不能为空");
		}
		try {
			// 进行查询数据
			Shop shop = shopService.selectShopById(id);
			if (shop != null && shop.getShopId() != null) {
				map.put("shop", shop);
				map.put("areaList", areaService.queryAreaList());
				map.put("success", true);
			} else {
				map.put("success", false);
				map.put("errMgs", "传统的id的用户不存在");
			}
		} catch (Exception e) {
			map.put("success", false);
			map.put("errMgs", "操作失败" + e.getMessage());
		}
		return map;
	}

	// 下面的这个方法是我们的前端页面初始化的操作的调用的方法
	@RequestMapping(value = "/getshopinitinfo", method = { RequestMethod.GET })
	@ResponseBody
	public Map<String, Object> getShopInitInfo() {
		// 进行创建这个出一个Map的对象
		Map<String, Object> map = new HashMap<String, Object>();
		List<ShopCategory> shopCategoryList = shopCategoryService.queryShopCategoryList(new ShopCategory());
		if (shopCategoryList == null || shopCategoryList.size() <= 0) {
			map.put("success", false);
			map.put("errMgs", "店铺类别为空");
			return map;
		} else {
			map.put("shopCategoryList", shopCategoryList);
		}
		List<Area> areaList = areaService.queryAreaList();
		if (areaList == null || shopCategoryList.size() <= 0) {
			map.put("success", false);
			map.put("errMgs", "区域类别为空");
			return map;
		} else {
			// 存在的问题，这里最好是areaList和shopCategoryList一起判断，不然后代码的可读性不是很多
			map.put("success", true);
			map.put("areaList", areaList);
		}
		return map;
	}

	/**
	 * 下面的这个方法为我们的商品的修改的方法
	 * 
	 */
	// 在这个方法当中我们主要是进行操作我们的店铺的修改商店信息的业务的相当的处理
	@RequestMapping("/modifyShop")
	@ResponseBody // 这个注解只要是把我们的Map<String,Object>转换成JSON格式的数据
	public Map<String, Object> modifyShop(HttpServletRequest request) {
		/**
		 * 进行检验操作和初始化的操作,只要是进行我们的的数据能不从前端输入，就不要从前端输入 因为前端是给一些不是专业的人用的
		 */
		Map<String, Object> map = new HashMap<String, Object>();
		// 进行验证我们的验证是否可用
		boolean verifyResult = VerifyCodeUtils.verifyCodeDemo(request);
		if (!verifyResult) {
			map.put("success", false);
			map.put("errMgs", "输入的验证码错误");
			return map;
		}
		String str = HttpServletRequestUtils.getString(request, "shopStr");
		// 进行转换成功
		if (str == null) {
			map.put("success", false);
			map.put("errMgs", "http请求的数据不能为空");
			return map;
		}
		// 进行把我们的字符串转换成Shop对象
		ObjectMapper mapper = new ObjectMapper();
		Shop shop = null;
		// 下面开始转换成图片信息，把图片的数据转换成CommonsMultipartFile对象
		CommonsMultipartFile cfiles = null;
		CommonsMultipartResolver mResoler = new CommonsMultipartResolver(request.getSession().getServletContext());
		// 进行判段一下
		if (mResoler.isMultipart(request)) {
			// 进行强转操作
			MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
			cfiles = (CommonsMultipartFile) mRequest.getFile("shopImg");
		} else {
			map.put("success", false);
			map.put("errMgs", "图片数据不能为空");
			return map;
		}
		try {
			shop = mapper.readValue(str, Shop.class);
		} catch (Exception e) {
			// 转换失败
			map.put("success", false);
			map.put("errMgs", "输入店铺的信息");
			return map;
		}
		// 数据封装成功
		if (shop != null && shop.getShopId() != null) {
			// 这里规定我们的用户一旦注册了用户，那么则这个商店就不能修改用了，所以这里就不能修改用户
			ShopExecution shopExectuon = null;
			try {
				if (cfiles != null) {
					shopExectuon = shopService.updateShop(shop, cfiles.getInputStream(), cfiles.getOriginalFilename());
				} else {
					shopExectuon = shopService.updateShop(shop, null, null);
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
			if (shopExectuon.getStatus() == ShopStatusEnums.SUCCESS.getStatus()) {
				// 说明插入成功了
				map.put("success", true);
			} else {
				map.put("success", false);
				map.put("errMgs", shopExectuon.getStatusInfo());
			}
		} else {
			map.put("success", false);
		}

		return map;
	}

	// 在这个方法当中我们主要是进行操作我们的店铺的注册的业务的相当的处理
	@RequestMapping("/shopRegister")
	@ResponseBody // 这个注解只要是把我们的Map<String,Object>转换成JSON格式的数据
	public Map<String, Object> shopRegister(HttpServletRequest request) {
		/**
		 * 进行检验操作和初始化的操作,只要是进行我们的的数据能不从前端输入，就不要从前端输入 因为前端是给一些不是专业的人用的
		 */
		Map<String, Object> map = new HashMap<String, Object>();
		// 进行验证我们的验证是否可用
		boolean verifyResult = VerifyCodeUtils.verifyCodeDemo(request);
		if (!verifyResult) {
			map.put("success", false);
			map.put("errMgs", "输入的验证码错误");
			return map;
		}
		String str = HttpServletRequestUtils.getString(request, "shopStr");
		// 进行转换成功
		if (str == null) {
			map.put("success", false);
			map.put("errMgs", "http请求的数据不能为空");
			return map;
		}
		// 进行把我们的字符串转换成Shop对象
		ObjectMapper mapper = new ObjectMapper();
		Shop shop = null;
		// 下面开始转换成图片信息，把图片的数据转换成CommonsMultipartFile对象
		CommonsMultipartFile cfiles = null;
		CommonsMultipartResolver mResoler = new CommonsMultipartResolver(request.getSession().getServletContext());
		// 进行判段一下
		if (mResoler.isMultipart(request)) {
			// 进行强转操作
			MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
			cfiles = (CommonsMultipartFile) mRequest.getFile("shopImg");
		} else {
			map.put("success", false);
			map.put("errMgs", "图片数据不能为空");
			return map;
		}
		try {
			shop = mapper.readValue(str, Shop.class);
			/*
			 * // 如果shop为null if (shop == null) { // 转换失败 map.put("success",
			 * false); map.put("errMgs", "http请求的数据不能为空"); return map; }
			 */

		} catch (Exception e) {
			// 转换失败
			map.put("success", false);
			map.put("errMgs", "输入店铺的信息");
			return map;
		}
		// 数据封装成功
		if (shop != null && cfiles != null) {
			// 创建一个文件对象
			// File file=new
			// File(PathUtils.getBasePath(),ImageUtils.getRandomFile());
			// 要进行把我们的CommonsMultipartFile转换成File对象，通过IO流对考的方式进行转换
			// CommonsMultipartFile2File(cfiles.getInputStream(),file);
			// 可以进行注册我们的店铺了,只要有返回值就说明返回插入成功了
			// 进行一些页面的初始化的操作,在用户登录的时候把这个PersonInfo对象放进行Session对象当中，这个是要在后面进行从Session当中取出数据，然后插入设置进去
			// 从Session当中取出用户的数据
			PersonInfo person = (PersonInfo) request.getSession().getAttribute("user");
			// Session TODO
			shop.setOwner(person);
			ShopExecution shopExectuon = null;
			try {
				shopExectuon = shopService.insertShop(shop, cfiles.getInputStream(), cfiles.getOriginalFilename());
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (shopExectuon.getStatus() == ShopStatusEnums.CHECK.getStatus()) {
				// 说明插入成功了
				map.put("success", true);
				@SuppressWarnings("unchecked")
				List<Shop> shopList = (List<Shop>) request.getSession().getAttribute("shopList");
				// 表示的是第一次创建商铺时
				if (shopList == null && shopList.size() == 0) {
					shopList = new ArrayList<Shop>();
				}
				// 存数据
				shopList.add(shopExectuon.getShop());
				request.getSession().setAttribute("shopList", shopList);
			} else {
				map.put("success", false);
				map.put("errMgs", shopExectuon.getStatusInfo());
			}
		} else {
			map.put("success", false);
		}

		return map;
	}

	/**
	 * 这个方法是只要是把我们的文件上传类转换成文件对象，但是在实际开发当中能不用文件的生成和创建就不用文件的生成的创建，因为这样 的成本的很高的
	 * 
	 * @param inputStream
	 *            文件上传类的输入流
	 * @param targetFile
	 *            要成文件的对象
	 */
	private void CommonsMultipartFile2File(InputStream inputStream, File targetFile) {
		// 定义一个输出流对象
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(targetFile);
			// 定义一个长度
			int len = -1;
			// 定义一个缓冲区
			byte buffer[] = new byte[4096];
			while ((len = inputStream.read(buffer)) != -1) {
				out.write(buffer, 0, len);
			}
		} catch (Exception e) {
			throw new RuntimeException("调用CommonsMultipartFile2File方法产Exception" + e.getMessage());
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (Exception e) {
				throw new RuntimeException("调用CommonsMultipartFile2File方法产Exception" + e.getMessage());
			}
		}
	}
	
	/**
	 * 此方法是为了得到我们的用户的商铺的列表的
	 * @param request
	 * @return  返回操作成功否
	 */
	@RequestMapping(value="/getshoplist",method={RequestMethod.GET})
	@ResponseBody
	public Map<String,Object> getShopList(HttpServletRequest request){
		Map<String,Object> map=new HashMap<String,Object>();
		//创建用户
		PersonInfo user=new PersonInfo();
		user.setUserId(1L);
		//先给用户一个固定的名字
		user.setUsername("杨海龙");
		//先得到这个User对象
		request.getSession().setAttribute("user",user);
		//最后取出
		user=(PersonInfo) request.getSession().getAttribute("user");
		try{
			//先得到一个Shop对象,根据用户id得到我们的商店对象，有很多，这个不对
//			Shop shopCondition=shopService.selectShopById(user.getUserId());
			Shop shopCondition=new Shop();
			//得到设置查询的条件，根据用户id来查询商店列表
			shopCondition.setOwner(user);
			//进行查询操作,定死了，因为我们的每个用户不可能超过100个商铺，所以这里也就不用进行分页了
			ShopExecution shopExecution=shopService.getShopList(shopCondition, 0, 100);
			if(shopExecution!=null&&shopExecution.getStatus()!=ShopStatusEnums.INNER_ERROR.getStatus()){
				//说明成功了
				map.put("success",true);
				//给前台返回商铺列表
				map.put("shopList",shopExecution.getList());
				//最后给前台返回用户信息
				map.put("user",user);
			}else{
				map.put("success",false);
				map.put("errMgs",ShopStatusEnums.INNER_ERROR.getStatusInfo());
			}
		}catch(Exception e){
			map.put("success",false);
			map.put("errMgs",e.getMessage());
		}
		//最后返回
		return map;
	}
	
	//得到我们的操作的商铺的列表的数据
	@RequestMapping(value="/shopoperatormanager",method={RequestMethod.GET})
	@ResponseBody
	public Map<String,Object> shopOperatorManager(HttpServletRequest request){
		Map<String,Object> map=new HashMap<String,Object>();
		//得到我们用户传进行的商店id
		Long shopId=HttpServletRequestUtils.getLong(request,"shopId");
		if(shopId<=0){
			//那么则尝试从Session中获取商店数据
			Shop shop=(Shop) request.getSession().getAttribute("currentShop");
			if(shop==null||shop.getShopId()==null){
				//那么则说明用户没有权限操作这个商店，所以要重定向到我们的商铺的列表
				map.put("redirect",true);
				map.put("url","SSM2SpringBoot/shopadmin/getshoplist");
			}else{
				//说明可以从商铺中取出数据
				map.put("redirect",false);
				map.put("shopId",shop.getShopId());
			}
		}else{
			//说明用户传了shop的id
			Shop currentShop=new Shop();
			currentShop.setShopId(shopId);
			//放入到Session当中
			request.getSession().setAttribute("currentShop",currentShop);
			//说明可以从商铺中取出数据
			map.put("redirect",false);
		}
		return map;
	}
}
