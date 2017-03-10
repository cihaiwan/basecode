package com.codezjsos.base.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import com.codezjsos.base.IBaseDao;
import com.codezjsos.base.IBaseService;
import com.codezjsos.base.bean.PageBean;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Transactional
public class BaseServiceImpl implements IBaseService {
	protected JdbcTemplate jdbcTemplate;

	private IBaseDao baseDao;

	public <T> void save(final T t) throws Exception {
		baseDao.save(t);
		
	}

	public <T> void update(T t) throws Exception {
		baseDao.update(t);
	}

	public <T> void delete(T t) throws Exception {
		baseDao.delete(t);
	}

	public <T> T findOne(Class<T> clz, Serializable unid) throws Exception {
		// TODO Auto-generated method stub
		return baseDao.findOne(clz, unid);
	}

	public <T> T load(Class<T> clz, Serializable unid) throws Exception {
		// TODO Auto-generated method stub
		return baseDao.load(clz, unid);
	}

	public <T> T findOne(String hql, Object... obj) throws Exception {
		
		return baseDao.findOne(hql, obj);
	}

	public <T> T findOne(String hql, Map<String, Object> obj) throws Exception {
		// TODO Auto-generated method stub
		return baseDao.findOne(hql, obj);
	}

	public <T> List<T> findAll(String hql, Object... obj) throws Exception {
		// TODO Auto-generated method stub
		return baseDao.findAll(hql, obj);
	}

	public <T> List<T> findAll(String hql, Map<String, Object> obj)
			throws Exception {
		// TODO Auto-generated method stub
		return baseDao.findAll(hql, obj);
	}

	
	public <T> List<T> findPage(String hql, PageBean bean, Object... obj)
			throws Exception {
		Long count=count(hql, obj);
		bean.setAllCount(count.intValue());
		List<T> data=baseDao.findPage(hql, bean.getPageNo(),bean.getPageSize(),obj);
		bean.setData(data);
		return data;
	}

	public <T> List<T> findPage(String hql, PageBean bean,
			Map<String, Object> obj) throws Exception {
		Long count=count(hql, obj);
		bean.setAllCount(count.intValue());
		List<T> data=baseDao.findPage(hql, bean.getPageNo(),bean.getPageSize(),obj);
		bean.setData(data);
		return data;
	}

	public <T> T findSql(Class<T> clz, String sql, Object... obj)
			throws Exception {
		
		return baseDao.findSql(clz, sql, obj);
	}

	public <T> T findSql(Class<T> clz, String sql, Map<String, Object> obj)
			throws Exception {
		// TODO Auto-generated method stub
		return baseDao.findSql(clz, sql, obj);
	}

	public <T> List<T> findAllSql(Class<T> clz, String sql, Object... obj)
			throws Exception {
		// TODO Auto-generated method stub
		return baseDao.findAllSql(clz, sql, obj);
	}

	public <T> List<T> findAllSql(Class<T> clz, String sql,
			Map<String, Object> obj) throws Exception {
		// TODO Auto-generated method stub
		return baseDao.findAllSql(clz, sql, obj);
	}

	public <T> List<T> findSqlPage(Class<T> clz, String sql, PageBean bean,
			Object... obj) throws Exception {
		Number count=baseDao.findSqlNum("select count(*) from "+ StringUtils.substringAfter(sql , "from"), obj);
		bean.setAllCount(count.intValue());
		List<T> data=baseDao.findSqlPage(clz,sql, bean.getPageNo(),bean.getPageSize(),obj);
		bean.setData(data);
		return data;
	}

	public <T> List<T> findSqlPage(Class<T> clz, String sql, PageBean bean,
			Map<String, Object> obj) throws Exception {
		Number count=baseDao.findSqlNum("select count(*) from "+ StringUtils.substringAfter(sql , "from"), obj);
		bean.setAllCount(count.intValue());
		List<T> data=baseDao.findSqlPage(clz,sql, bean.getPageNo(),bean.getPageSize(),obj);
		bean.setData(data);
		return data;
	}

	public Long count(String hql, Object... obj) throws Exception {
		if(hql.matches("select\\s+.*count\\([^)]+\\).*from .*")){
			return findOne(hql, obj);
		}else{
			return findOne("select count(*) " +hql, obj);
		}
	}

	public Long count(String hql, Map<String, Object> obj) throws Exception {
		if(hql.matches("select\\s+.*count\\([^)]+\\).*from .*")){
			return findOne(hql, obj);
		}else{
			return findOne("select count(*) " +hql, obj);
		}
	}
	

	public void setJdbcTemplate(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public void deleteListSql(String sql,Object... obj) throws Exception {
		//(sql,obj)jdbcTemplate.execute(sql);
	}

	public void setBaseDao(IBaseDao baseDao) {
		this.baseDao = baseDao;
	}
}
