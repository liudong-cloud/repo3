package com.itheima.service;

import com.itheima.domain.SysLog;

import java.util.List;

public interface ISysLogService {

    /**
     * 存储日志
     * @param sysLog
     */
    void save(SysLog sysLog);


    /**
     * 查询所有
     * @return
     */
    List<SysLog> findAll();
}
