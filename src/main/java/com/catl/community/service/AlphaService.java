package com.catl.community.service;

import com.catl.community.dao.AlphaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service
//@Scope("prototype")
public class AlphaService {

    @Autowired
    private AlphaDao alphaDao;

    public AlphaService(){
        System.out.println("实例化AlphaService");
    }

    @PostConstruct
    public void init(){
        System.out.println("初始化AlphaService");
    }

    @PreDestroy
    public void destory(){
        System.out.println("销毁AlphaService");
    }

    //Bean的实例化确实是在构造器之后 创建的
    //Bean的销毁的方法是咋程序结束之前，对象销毁之前调用的
    //在初始化和销毁之间，我们打印出了Bean的实例 com.catl.community.service.AlphaService@7dd611c8
    //Bean只被实例化一次，销毁一次，在程序中只有一个实例——单例模式
    //web程序只启动一次

    public String find(){
        return alphaDao.select();
    }
}
