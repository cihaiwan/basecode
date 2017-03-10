package com.codezjsos.log.service;

import com.codezjsos.base.IBaseService;
import com.codezjsos.base.utils.JsonUtils;
import com.codezjsos.log.entity.TrackBase;
import com.codezjsos.log.entity.TrackLogEntity;
import com.codezjsos.log.entity.TrackLogError;
import com.codezjsos.log.entity.TrackLogErrorPage;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by zhufang on 2017/3/6.
 */
@Service
public class SysLogService implements ISysLogService{

    public static Logger logger= LoggerFactory.getLogger(SysLogService.class);
    @Resource
    private IBaseService baseService;

    @Override
    public void savelog(Map<String, Object> params) throws Exception {
        Object t=params.get("type");
        if(t==null||((String)t).equals("")){
            logger.error(JsonUtils.toJson(params)+"：未有type参数传入");
        }
        String type=(String) t;
        String data= (String) params.get("data");
        Map<String,Object> dataMap= JsonUtils.fromJson(data);
        TrackBase trackBase=null;
        if(type.equals("a")){
            trackBase=new TrackLogEntity();
        }else if(type.equals("b")){
            trackBase=new TrackLogError();
        }else if(type.equals("c")){
            trackBase=new TrackLogErrorPage();
        }
        BeanUtils.populate(trackBase,dataMap);
        baseService.save(trackBase);
    }
}
