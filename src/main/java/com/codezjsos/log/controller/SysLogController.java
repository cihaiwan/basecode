package com.codezjsos.log.controller;

import com.codezjsos.log.service.ISysLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by zhufang on 2017/3/6.
 */
@Controller
@RequestMapping("log")
public class SysLogController {

    public static Logger logger= LoggerFactory.getLogger(SysLogController.class);

    private ISysLogService sysLogService;

    @RequestMapping("savelog")
    @ResponseBody
    public void savelog(@RequestParam Map<String,Object> params) throws Exception {
        sysLogService.savelog(params);
    }
}
