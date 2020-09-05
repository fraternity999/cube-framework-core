package com.cube.core.system.aop;

import com.cube.core.common.utils.IpUtil;
import com.cube.core.system.annotation.SysLog;
import com.cube.core.system.entity.SystemLog;
import com.cube.core.system.repository.SystemLogRepository;
import com.google.gson.Gson;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @author YYF
 */
@Aspect
@Component
public class SysLogAspect {

    @Autowired
    private SystemLogRepository systemLogRepository;

    @AfterReturning(value = "@annotation(com.cube.core.system.annotation.SysLog)",returning = "object")
    public void saveSysLog(JoinPoint joinPoint,Object object){

        SystemLog systemLog = new SystemLog();

        //从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取切入点所在的方法
        Method method = signature.getMethod();

        //获取操作
        SysLog myLog = method.getAnnotation(SysLog.class);
        if (myLog != null) {
            String value = myLog.value();
            systemLog.setOperation(value);//保存获取的操作
        }

        //获取请求的类名
        String className = joinPoint.getTarget().getClass().getName();
        //获取请求的方法名
        String methodName = method.getName();
        systemLog.setMethod(className + "." + methodName);

        //请求的参数
        Object[] args = joinPoint.getArgs();
        //将参数所在的数组转换成json
        systemLog.setParams("");

        systemLog.setCreateDate(new Date());
        //获取用户名
        systemLog.setUsername("");
        //获取用户ip地址
        systemLog.setIp(IpUtil.getRemoteAddr(joinPoint));
        //调用service保存SysLog实体类到数据库
        Gson gson = new Gson();
        String ret = gson.toJson(object);
        systemLog.setResult(ret);
        systemLogRepository.save(systemLog);
    }
}
