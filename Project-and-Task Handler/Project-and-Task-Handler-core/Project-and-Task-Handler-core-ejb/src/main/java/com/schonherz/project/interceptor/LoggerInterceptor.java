package com.schonherz.project.interceptor;

import com.schonherz.project.interceptor.annotations.DoNotLogParamValues;
import com.schonherz.project.interceptor.annotations.DoNotLogReturnValue;
import com.schonherz.project.interceptor.binding.Logging;
import com.schonherz.project.util.MessageUtil;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by darvasr on 2016.09.11..
 */
@Logging
@Interceptor
public class LoggerInterceptor {

    private Logger logger;

    @AroundInvoke
    public Object intercept(InvocationContext context) throws Exception {
        Method method = context.getMethod();
        String methodName = method.getName();
        String className = method.getDeclaringClass().getName();

        if (isMethodOrDeclaringClassAnnotated(method,
                DoNotLogParamValues.class)) {
            logBefore(methodName, className);
        } else {
            logBefore(methodName, className,
                    MessageUtil.createLogMessage(context));
        }

        Object result = context.proceed();

        if (isMethodOrDeclaringClassAnnotated(method,
                DoNotLogReturnValue.class)) {
            logAfter(methodName, className);
        } else {
            logAfter(methodName, className, result);
        }

        return result;
    }

    private boolean isMethodOrDeclaringClassAnnotated(Method method, Class<? extends Annotation> annotation) {
        return method.getDeclaringClass().isAnnotationPresent(annotation)
                || method.isAnnotationPresent(annotation);
    }

    private void logAfter(String methodName, String className) {
        logger.log(Level.INFO, "LoggerInterceptor: Logging after calling methodName: {0} in class: {1} return value: *******", new Object[]{methodName, className});
    }

    private void logAfter(String methodName, String className, Object result) {
        logger.log(Level.INFO, "LoggerInterceptor: Logging after calling methodName: {0} in class: {1} return value: {2}", new Object[]{methodName, className, Objects.toString(result)});
    }

    private void logBefore(String methodName, String className) {
        logger.log(Level.INFO, "LoggerInterceptor: Logging before calling methodName: {0} in class: {1} parameter value(s): ******************", new Object[]{methodName, className});
    }

    private void logBefore(String methodName, String className, String params) {
        logger.log(Level.INFO, "LoggerInterceptor: Logging before calling methodName: {0} in class: {1} parameter value(s): {2}", new Object[]{methodName, className, params});
    }

    @Inject
    public void setLogger(Logger logger) {
        this.logger = logger;
    }
}
