package com.codezjsos.log.error;

import com.codezjsos.log.entity.TrackLogError;
import org.springframework.stereotype.Component;


@Component
public class ErrorLog extends AbsErrorLog{

	@Override
	public TrackLogError getError() {
		return new TrackLogError();
	}
}
