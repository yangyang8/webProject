package com.hailong.o2o;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 这个类是我们的所以的测试类的基类，所以的测试类都要继承于这个类，因为这个类主要是做一些初始化操作的
 * @author Administrator
 *
 */
//指定我们的spring和junit4的整合的测试环境类
@RunWith(SpringJUnit4ClassRunner.class)
//指定我们的spring的配置文件类
@ContextConfiguration({"classpath:spring/spring-dao.xml",
					"classpath:spring/spring-service.xml"})
public class BaseTest {

}
