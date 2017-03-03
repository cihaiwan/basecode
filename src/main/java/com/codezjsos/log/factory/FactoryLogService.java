package com.codezjsos.log.factory;

import com.codezjsos.base.factory.FactoryBaseDataAccess;
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
@Component("baseServiceKLog")
public class FactoryLogService extends FactoryBaseDataAccess{
    @Resource(name="dataSourceLog")
    public void setDataSource(DataSource dataSource) {
        this.dataSource=dataSource;
    }

    @Resource(name="sessionFactoryLog")
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory=sessionFactory;
    }
}
