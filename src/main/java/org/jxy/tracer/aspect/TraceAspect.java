package org.jxy.tracer.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import org.jxy.tracer.annotation.*;
import org.jxy.tracer.trace.Tracer;

@Aspect
public class TraceAspect {
    @Around("@annotation(TraceMethod)")
    public void startTrace(ProceedingJoinPoint point) {
        try {
            Tracer.createContext();
            Tracer.startTrace();
            point.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            Tracer.stopTrace();
            Tracer.logTrace();
            Tracer.destroyContext();
        }
    }
}
