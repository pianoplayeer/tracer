package org.jxy.demo;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.jxy.tracer.annotation.*;

import java.time.LocalDateTime;
import java.util.function.Supplier;

@Aspect
public class LogAspect {
    private static ThreadLocal<Integer> cnt = ThreadLocal.withInitial(() -> 0);
    static InheritableThreadLocal<String> abc = (InheritableThreadLocal<String>) InheritableThreadLocal.withInitial(String::new);
    @Before("within(@TraceService *) && execution(* *(..)) && !staticinitialization(*) && !execution(static * *(..))")
    public void addTemplate(JoinPoint point) {
        Thread t = new Thread(() -> System.out.println(1));

        System.out.printf("[LOG] %s: ", LocalDateTime.now());
        System.out.print(" " + cnt.get());
        cnt.set(cnt.get() + 1);
    }
}
