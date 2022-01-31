package com.catl.community.dao;

import org.springframework.stereotype.Repository;

@Repository("alphaHibernate")//我们可以通过名字强制容器返回这个bean给我们
public class AlphaDaoHibernateImpl implements AlphaDao {

    @Override
    public String select() {
        return "Hibernate";
    }
}
