package com.codezjsos.log.error;

import com.codezjsos.log.entity.TrackLogError;
import com.codezjsos.log.entity.TrackLogErrorPage;
import org.springframework.stereotype.Component;


import sun.security.jgss.TokenTracker;
@Component
public class ErrorLogPage extends AbsErrorLog{
	private String url;

	@Override
	public TrackLogError getError() {
		TrackLogErrorPage p= new TrackLogErrorPage();
		p.setUrl(url);
		url="";
		return p;
	}

	
	public void setUrl(String url) {
		this.url = url;
	}
	
}
