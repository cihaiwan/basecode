package com.codezjsos.base.factory;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * Created by zhufang on 2017/3/2.
 */
@Component("baseService" )
public class FactoryBaseService  extends FactoryBaseDataAccess{
    @Resource(name="dataSourceBase")
    public void setDataSource(DataSource dataSource) {
        this.dataSource=dataSource;
    }

    @Resource(name="sessionFactoryBase")
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory=sessionFactory;
    }
}
