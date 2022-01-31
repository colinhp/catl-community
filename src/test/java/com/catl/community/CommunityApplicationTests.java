package com.catl.community;

import com.catl.community.dao.AlphaDao;
import com.catl.community.service.AlphaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.event.annotation.AfterTestClass;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
class CommunityApplicationTests implements ApplicationContextAware {

//	@Test
//	void contextLoads() {
//	}

	private ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	@Test
	public void testApplicationContext() {
		System.out.println(applicationContext);
		//打印出来的是 一个实现类的 类名 GenericWebApplicationContext@4f0100a7 类名@哈希code
		//ApplicationContext是个接口 测试的目的是这个容器是存在的,可见的
		//怎么用容器去管理Bean，首先你要有Bean

		//成功的获取到了Bean并查到了结果
		//按照类型去获取Bean这个类型是个接口,这个接口满足条件的Bean有两个,Spring容器给你哪一个Bean呢？奇异
		//面向接口的编程思想,依赖的是Bean的接口，不是Bean本身，依赖的是接口，实现类变了，这里是不需要改的
		//Spring用该方式降低了Bean之间的耦合度 调用方和实现类不发生直接的关系
		AlphaDao alphaDao = applicationContext.getBean(AlphaDao.class);
		System.out.println(alphaDao.select());

		//通过Bean名字强制容器返回给我们指定的Bean
		alphaDao = applicationContext.getBean("alphaHibernate", AlphaDao.class);
		//alphaDao = applicationContext.getBean("alphaDaoMybatisImpl",AlphaDao.class);
		//默认返回Object需要强制转换

		System.out.println(alphaDao.select());
	}


	@Test
	public void testBeanManagement(){
		AlphaService alphaService = applicationContext.getBean(AlphaService.class);
		System.out.println(alphaService);
		//确实打印出这个对象没有问题，Spring容器说明能够实例化

		//是同一个Bean，只实例化一次，是个单例模式
		//com.catl.community.service.AlphaService@e042c99
		//com.catl.community.service.AlphaService@e042c99
		alphaService = applicationContext.getBean(AlphaService.class);
		System.out.println(alphaService);
	}

	@Test
	public void testBeanConfig(){
		SimpleDateFormat simpleDateFormat = applicationContext.getBean(SimpleDateFormat.class);
		System.out.println(simpleDateFormat.format(new Date()));
	}

	@Autowired
	//@Qualifier("alphaHibernate")
	private AlphaDao alphaDao;

	@Autowired
	private AlphaService alphaService;

	@Autowired
	private SimpleDateFormat simpleDateFormat;

	@Test
	public void TestDI(){
		System.out.println(alphaDao);
		System.out.println(alphaService);
		System.out.println(simpleDateFormat);
	}

}