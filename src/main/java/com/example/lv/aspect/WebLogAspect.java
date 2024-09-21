package com.example.lv.aspect;

import com.example.lv.dao.entity.base.LoginUser;
import com.example.lv.dao.entity.base.SysLog;
import com.example.lv.dao.mapper.base.SysLogMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lmh
 * @version 1.0
 * @project xiaobai
 * @description WebLogAspect
 * @date 2023/6/16 12:20:59
 */
@Aspect
@Component
@EnableAsync
public class WebLogAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebLogAspect.class);

    /**
     * 注入日志
     */
    @Resource
    private SysLogMapper logMapper;
    /**
     * 请求地址
     */
    private String requestPath = null;
    /**
     * 开始时间
     */
    private long startTimeMillis = 0;
    /**
     * 结束时间
     */
    private long endTimeMillis = 0;
    /**
     * 操作人
     */
    private String operationUser = null;
    /**
     * 请求
     */
    private HttpServletRequest request = null;

    /**
     * 切入点 注解的位置
     */
    @Pointcut("@annotation(com.example.lv.aspect.OperationAnnotation)")
    public void logPointCut() {}

    /**
     * @param joinPoint
     * @Description 前置通知  方法调用前触发   记录开始时间,从session中获取操作人
     */
    @Before(value="logPointCut()")
    public void before(JoinPoint joinPoint){
        startTimeMillis = System.currentTimeMillis();
    }

    /**
     * @param joinPoint
     * @Description 获取入参方法参数
     * @return
     */
    public Map<String, Object> getNameAndValue(JoinPoint joinPoint) {
        Map<String, Object> param = new HashMap<>(16);
        Object[] paramValues = joinPoint.getArgs();
        param.put("params",paramValues[0].toString());
        return param;
    }

    /**
     * @param joinPoint
     * @Description 后置通知    方法调用后触发   记录结束时间 ,操作人 ,入参等
     */
    @After(value="logPointCut()")
    public void after(JoinPoint joinPoint) {
        request = getHttpServletRequest();
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class<?> targetClass = null;
        try {
            targetClass = Class.forName(targetName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Method[] methods = targetClass.getMethods();
        //方法描述
        String remark;
        //操作方法
        String method;
        //系统类型
        String sysType;
        //操作类型
        String opType;
        Class<?>[] clazzs;
        for (Method m : methods) {
            if (m.getName().equals(methodName)) {
                clazzs = m.getParameterTypes();
                if (clazzs!=null&&clazzs.length == arguments.length
                        &&m.getAnnotation(OperationAnnotation.class)!=null) {
                    request = getHttpServletRequest();
                    requestPath=request.getServletPath();
                    HttpSession session = request.getSession();
                    SecurityContext securityContext = (SecurityContext) session.getAttribute("SPRING_SECURITY_CONTEXT");
                    LoginUser loginUser = (LoginUser) securityContext.getAuthentication().getPrincipal();
                    operationUser = loginUser.getUsername();
                    remark = m.getAnnotation(OperationAnnotation.class).content();
                    method = m.getAnnotation(OperationAnnotation.class).action();
                    sysType = m.getAnnotation(OperationAnnotation.class).sysType();
                    opType = m.getAnnotation(OperationAnnotation.class).opType();
                    endTimeMillis = System.currentTimeMillis();

                    SysLog log=new SysLog(operationUser, requestPath, (endTimeMillis-startTimeMillis)+"ms",
                            getNameAndValue(joinPoint).toString(), remark, method,sysType,opType,new Date(),new Date());
                    LOGGER.info("增加参数："+log);
                    logMapper.insert(log);

                }
            }
        }
    }
    /**
     * @Description: 获取request
     */
    public HttpServletRequest getHttpServletRequest(){
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes)ra;
        HttpServletRequest request = sra.getRequest();
        return request;
    }

    /**
     * @param joinPoint
     * @return 环绕通知
     * @throws Throwable
     */
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        return null;
    }
    /**
     * @param joinPoint
     * @Description 异常通知
     */
    public void throwing(JoinPoint joinPoint) {
        System.out.println("异常通知");
    }
}
