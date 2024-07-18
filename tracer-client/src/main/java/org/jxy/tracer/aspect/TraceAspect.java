package org.jxy.tracer.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.jxy.tracer.annotation.TraceInfo;
import org.jxy.tracer.annotation.TraceService;
import org.jxy.tracer.trace.Tracer;

import java.lang.reflect.Method;

@Aspect
public class TraceAspect {
    @Pointcut("execution(* *(..)) && " +
            "(@annotation(org.jxy.tracer.annotation.TraceInfo)" +
            "|| @annotation(org.jxy.tracer.annotation.TraceService))")
    public void scope() {}

    @Around("scope()")
    public void trace(ProceedingJoinPoint point) {
        Method m = ((MethodSignature) point.getSignature()).getMethod();
        TraceService traceService = m.getAnnotation(TraceService.class);

        String className = ((MethodSignature) point.getSignature()).getMethod().getDeclaringClass().toString();
        String methodName = className + "#" + point.getSignature().getName();

        if (traceService != null) {
            Tracer.createTraceEnv(methodName);
        } else {
            Tracer.createContext(methodName);
        }

        try {
            Tracer.startTrace();
            point.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            Tracer.stopTrace();
            Tracer.logTrace();

            Tracer.endCurrentTrace();
        }
    }
}
