package com.itheima.controller;

import com.itheima.domain.SysLog;
import com.itheima.service.ISysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect
//切面类
public class LogAop {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private ISysLogService sysLogService;

    @Pointcut("execution(* com.itheima.controller.*.*(..))")
    public void pointCut() {
    }

    private Date startTime;
    private Class executionClass;
    private Method executionMethod;

    @Before("pointCut()")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {
        //初始时间
        startTime = new Date();
        //获取初始类
        executionClass = jp.getTarget().getClass();
        //获取方法
        String methodName = jp.getSignature().getName();
        Object[] args = jp.getArgs();
        Class[] classes = new Class[args.length];
        for (int i = 0; i < args.length; i++) {
            classes[i] = args[i].getClass();
        }
        executionMethod = executionClass.getMethod(methodName, classes);
    }

    @After("pointCut()")
    public void doAfter(JoinPoint jp) {
        //获取访问时长
        Long executionTime = new Date().getTime() - startTime.getTime();
        //获取url
        String url="";
        if (executionClass != null && executionMethod != null && executionClass != LogAop.class && executionClass!=ControllerSysLog.class) {
            RequestMapping classAnnotation = (RequestMapping) executionClass.getAnnotation(RequestMapping.class);
            if (classAnnotation != null) {
                String[] classs = classAnnotation.value();
                RequestMapping methodAnnotation = executionMethod.getAnnotation(RequestMapping.class);
                if (methodAnnotation != null) {
                    String[] methods = methodAnnotation.value();
                    url = classs[0]+methods[0];
                }
            }
        }
        //获取ip
        String ip = request.getRemoteAddr();
        //获取用户姓名
        SecurityContext context = SecurityContextHolder.getContext();
        User user = (User) context.getAuthentication().getPrincipal();
        String username = user.getUsername();
        //获取方法
        String method="[类名 ]"+executionClass.getName()+"[方法名 ]"+executionMethod.getName();

        SysLog sysLog = new SysLog();
        sysLog.setVisitTime(startTime);
        sysLog.setExecutionTime(executionTime);
        sysLog.setUrl(url);
        sysLog.setUsername(username);
        sysLog.setMethod(method);
        sysLog.setIp(ip);

        sysLogService.save(sysLog);
    }


}
