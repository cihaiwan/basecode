package com.codezjsos.log.service;

import java.util.Map;

/**
 * Created by zhufang on 2017/3/6.
 */
public interface ISysLogService {

    public void savelog(Map<String,Object> params) throws Exception;
}
