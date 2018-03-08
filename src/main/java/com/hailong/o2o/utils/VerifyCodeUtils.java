package com.hailong.o2o.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * 进行判断一下我们的验证码是否有效
 * @author Administrator
 *
 */
public class VerifyCodeUtils {
	public static boolean verifyCodeDemo(HttpServletRequest requset){
		//先得到我们的图片的验证码,从session当中的key中取出图片验证码
		String verifyCodeExcept=(String)requset.getSession().getAttribute("KAPTCHA_SESSION_KEY");
		//取出我们的人工写的验证码												  
		String verifyCodeActual=HttpServletRequestUtils.getString(requset,"verifyCodeActual");
		//进行判断二者是否相等
		if(verifyCodeActual==null||!verifyCodeActual.equals(verifyCodeExcept)){
			return false;
		}else{
			return true;
		}
	}
}
