package com.hailong.o2o.web.superadmin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hailong.o2o.entity.Area;
import com.hailong.o2o.service.AreaService;


/**
 * 下面这个类是我们的区域控制器的具体操作类
 * @author Administrator
 *
 */
@Controller //表明这个是一个controller的类
@RequestMapping("/superman") //进行窄化依赖的操作
public class AreaController {
	//得到我们的业务层的对象
   @Autowired
   private AreaService areaService;
   //下面开始使用日志对象，这个class表示的是监听的是那个对象(类)
   private Logger logger=LoggerFactory.getLogger(AreaController.class);
   //下面进行开发具体的的操作
   @RequestMapping(value={"/queryarealist"},method=RequestMethod.GET)
   @ResponseBody  //表示返回的是json格式的数据
   public Map<String,Object> queryAreaList(){
	   //用info级别的日志来显示方法的开始
	   logger.info("========start=======");
	   //用debug级别的日志进行调优，涉及执行时间
	   long startTime=System.currentTimeMillis();
	   //进行创建一个Map对象
	   Map<String,Object> mapList=new HashMap<String,Object>();
	   try{
		   List<Area> list=areaService.queryAreaList();
		   //增加list中的数据到map中
		   mapList.put("rows",list);
		   //增加list中的行数目到map当中
		   mapList.put("tatol", list.size());
	   }catch(Exception e){
		   mapList.put("success",false);
		   mapList.put("errMsg",e.toString());
	   }
	   //进行error级别的操作
	   logger.error("=======test error======");
	   long endTime=System.currentTimeMillis();
	   logger.debug("execution time[{} ms]",endTime-startTime);
	   //用info级别的日志来显示方法的结束
	   logger.info("========end=======");
	   //返回数据
	return mapList;
   }
}
