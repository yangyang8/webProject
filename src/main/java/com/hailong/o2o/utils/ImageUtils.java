package com.hailong.o2o.utils;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.imageio.ImageIO;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;


/**
 * 这个是用来处理我们的上传的图片的数据的工具类
 * 在这个类下我们的我们是得到我们的图片，然后对这个图片进行加载处理的操作
 * @author Administrator
 *
 */
public class ImageUtils {
	//创建一个随机数对象
	private static Random r=new Random();
	//创建一个日期格式化对象
	private static SimpleDateFormat format=new SimpleDateFormat("yyyyMMddHHmmss");
	//我们的相对路径可以根据我们的执行线程来得到类加载器，从而根据类加载器去得到去得到相对路径
	private static final String BASEPATH=Thread.currentThread().getContextClassLoader().getResource("").getPath();
	/**
	 * 主要是增加水印，返回进行输出图片的绝对路径
	 * @param file  文件流对象
	 * @param targetFile 保存的文件目录，也就是相对子路径
	 */
	public static String genernateThumbnails(InputStream inputStream,String targetFile,String srcPath){
		//生成唯一图片的路径
		String realFileName=getRandomFile();
		//得到扩展名
		String extension=getFileExtension(srcPath);
		//创建路径
		makeDirPath(targetFile);
		//得到全部的路径
		String relativeAddr=targetFile+realFileName+extension;
		File dest=new File(PathUtils.getBasePath(),relativeAddr);
		try{
			Thumbnails.of(inputStream).size(200,200)
			.outputQuality(0.5f).watermark(Positions.BOTTOM_RIGHT,
					ImageIO.read(new File(BASEPATH,"watermark.png")),0.25f)
			.toFile(dest);
			return relativeAddr;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 进行创建我们的文件夹
	 * @param targetFile 创建的文件目录
	 */
	private static void makeDirPath(String targetFile) {
		String absolution=PathUtils.getBasePath()+targetFile;
		//创建文件对象
		File file=new File(absolution);
		if(!file.exists()){
			file.mkdirs();
		}
	}
    /**
     * 得到我们的文件扩展名
     * @param file 根据文件对象，得到扩展名
     * @return 返回文件扩展名
     */
	private static String getFileExtension(String srcPath) {
		return srcPath.substring(srcPath.lastIndexOf("."));
	}
    /**
     * 为了保证我们的文件名一致性，唯一性，定义文件名格式如下
     * yyyymmddHHmmss+5位的随机数组成部分
     * @return
     */
	public static String getRandomFile() {
		//得到一个10000到99999的随机数字(也就是五位随机数)
		long num=r.nextInt(89999)+10000;
		//得到规定格式的日期
		String date=format.format(new Date());
		return date+num;
	}
	
	public static void deleteFileAndDir(String path){
		//先创建一个文件对象
		File srcFile=new File(PathUtils.getBasePath(),path);
		if(srcFile.exists()){
			//连文件和目录一起给delete
			if(srcFile.isDirectory()){
				File files[]=srcFile.listFiles();
				for(File f:files){
					//删除文件
					f.delete();
				}
			}
			//删除目录
			srcFile.delete();
		}
	}

	//这个main方法现在主要是用来先测试我们的如何使用Thumbnails的工具类的操作
	public static void main(String[] args) throws IOException {
		
		//使用我们的Thumbnails的工具方法来操作图片
		Thumbnails.of("G://o2o//images//yanghailong.jpg")  //去得到保存电脑上的图片，给这个图片增加水印
		.size(200,200)  //格式化图片的大小
		.outputQuality(0.5f) //设置这个图片的输出的压缩比
		.watermark(Positions.BOTTOM_RIGHT,ImageIO.read(new File(BASEPATH,"watermark.png")),0.25f) //设置水印，同时指定水印的位置，水印图片，透明度
		.toFile("G://o2o//images//yanghailongnew.jpg");
	}
}
