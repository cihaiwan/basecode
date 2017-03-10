package com.codezjsos.base.factory;

import com.codezjsos.base.IBaseDao;
import com.codezjsos.base.IBaseService;
import com.codezjsos.base.entity.TestUser;
import com.codezjsos.base.impl.BaseDaoImpl;
import com.codezjsos.base.impl.BaseServiceImpl;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.List;

/**
 * Created by zhufang on 2017/3/2.
 */

public abstract  class FactoryBaseDataAccess implements FactoryBean<IBaseService>{
    protected DataSource dataSource;
    protected SessionFactory sessionFactory;

    public abstract void setDataSource(DataSource dataSource);

    public abstract  void setSessionFactory(SessionFactory sessionFactory);

    private IBaseDao baseDao=new BaseDaoImpl();;
    private IBaseService baseService=new BaseServiceImpl();

    @Override
    public IBaseService getObject() throws Exception {
        baseDao.setSessionFactory2(sessionFactory);
        baseService.setBaseDao(baseDao);
        baseService.setJdbcTemplate(dataSource);
        return baseService;
    }

    @Override
    public Class<?> getObjectType() {
        return IBaseService.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public static void main(String[] args) throws Exception {
//        ApplicationContext ac=new ClassPathXmlApplicationContext("baseContext.xml");
//        IBaseService baseService= (IBaseService) ac.getBean("baseService");
//        baseService.findAll("from TestUser");
//        IBaseService baseService2= (IBaseService) ac.getBean("baseService");
//        baseService2.findAll("from TestUser");
        ApplicationContext ac=new ClassPathXmlApplicationContext("logContext.xml");
        IBaseService baseService= (IBaseService) ac.getBean("baseServiceLog");
        List<Object> obj= baseService.findAll("from TrackLogEntity");
        System.out.println(obj.size());
    }
}
