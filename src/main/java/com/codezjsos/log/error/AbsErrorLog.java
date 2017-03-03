package com.codezjsos.log.error;

import java.util.Date;

import javax.annotation.Resource;

import com.codezjsos.log.entity.TrackLogError;
import com.codezjsos.log.entity.TrackLogErrorPage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;



public abstract class AbsErrorLog {

	private String remark;

	
	public  abstract TrackLogError getError();
	
	public void execution(Exception e){
		TrackLogErrorPage error=(TrackLogErrorPage) getError();
		error.setCreatetime(new Date());
		error.setErrormsg(e.fillInStackTrace().toString());
		error.setRemark(StringUtils.isEmpty(e.getMessage())?remark:e.getMessage());
	//	trackDao.save(error);
		remark="";
	}

	public AbsErrorLog(String remark) {
		super();
		this.remark = remark;
	}

	public AbsErrorLog() {
		super();
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public static void main(String[] args) {
		ApplicationContext context=new ClassPathXmlApplicationContext("net/zjsos/log/config/applicationContext-track.xml");
//		AbsErrorLog errorLog=(AbsErrorLog) context.getBean("errorLog");
		ErrorLogPage errorLog=(ErrorLogPage) context.getBean("errorLogPage");
		errorLog.setRemark("hello world");
//		errorLog.setUrl("11123456");
		errorLog.execution(new NullPointerException("2222"));
	}
	
}
