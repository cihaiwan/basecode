package com.codezjsos.base.factory;

import com.codezjsos.base.IBaseDaoK;
import com.codezjsos.base.IBaseServiceK;
import com.codezjsos.base.entity.TestUser;
import com.codezjsos.base.impl.BaseDaoImplK;
import com.codezjsos.base.impl.BaseServiceImplK;
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

public abstract  class FactoryBaseDataAccess implements FactoryBean<IBaseServiceK>{
    protected DataSource dataSource;
    protected SessionFactory sessionFactory;

    public abstract void setDataSource(DataSource dataSource);

    public abstract  void setSessionFactory(SessionFactory sessionFactory);

    private IBaseDaoK baseDaoK=new BaseDaoImplK();;
    private IBaseServiceK baseServiceK=new BaseServiceImplK();

    @Override
    public IBaseServiceK getObject() throws Exception {
        baseDaoK.setSessionFactory2(sessionFactory);
        baseServiceK.setBaseDaoK(baseDaoK);
        baseServiceK.setJdbcTemplate(dataSource);
        return baseServiceK;
    }

    @Override
    public Class<?> getObjectType() {
        return IBaseServiceK.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public static void main(String[] args) throws Exception {
//        ApplicationContext ac=new ClassPathXmlApplicationContext("baseContext.xml");
//        IBaseServiceK baseServiceK= (IBaseServiceK) ac.getBean("baseServiceK");
//        baseServiceK.findAll("from TestUser");
//        IBaseServiceK baseServiceK2= (IBaseServiceK) ac.getBean("baseServiceK");
//        baseServiceK2.findAll("from TestUser");
        ApplicationContext ac=new ClassPathXmlApplicationContext("logContext.xml");
        IBaseServiceK baseServiceK= (IBaseServiceK) ac.getBean("baseServiceKLog");
        List<Object> obj= baseServiceK.findAll("from TrackLogEntity");
        System.out.println(obj.size());
    }
}
