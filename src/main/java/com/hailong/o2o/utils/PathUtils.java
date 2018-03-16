package com.hailong.o2o.utils;
/**
 * 在这个类下只要是进行操作我们的类的路径的操作的，主要是有二个方法
 * 一个方法是得到我们的图片的保存的绝对路径的根路径的方法
 * 另一个是得到我们的项目下的图片路径
 * @author Administrator
 *
 */
public class PathUtils {
	//得到我们的系统的名字
	private static final String SYSTEMNAME=System.getProperty("os.name");
	//定义我们文件分割符操作
	private static final String SEPERATOR=System.getProperty("file.separator");
	/**
	 * 得到我们的图片的根路径,这个路径是我们的自己定死的
	 * @return  返回的是我们系统的根路径
	 */
	public static String getBasePath(){
		//保存图片根路径的变量
		String basePath;
		//根据我们不同的系统名，设置不同的图片保存的根路径
		if(SYSTEMNAME.toLowerCase().startsWith("win")){
			basePath="G:/o2o/images/";
		}else{
			basePath="/home/o2o/images/";
		}
		//进行根据我们不同的系统去得到不同的文件分割符
		basePath=basePath.replace("/",SEPERATOR);
		//最后返回
		return basePath;
	}
	/**
	 * 返回项目图片的子路径
	 * @param shopId 店铺id
	 * @return 返回项目图片的子路径
	 */
	public static String getShopImagePath(long shopId){
		String imagePath="/upload/item/shop/"+shopId+"/";
		return imagePath.replace("/",SEPERATOR);
	}
}
