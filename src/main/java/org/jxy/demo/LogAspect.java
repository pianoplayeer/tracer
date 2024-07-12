package org.jxy.demo;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.jxy.tracer.annotation.*;

import java.time.LocalDateTime;
import java.util.function.Supplier;

@Aspect
public class LogAspect {
    private static InheritableThreadLocal<Integer> cnt = new InheritableThreadLocal<>();

    static {
        cnt.set(0);
    }

    @Before("within(@TraceService *) && execution(* *(..)) && !staticinitialization(*) && !execution(static * *(..))")
    public synchronized void addTemplate(JoinPoint point) {
        Thread t = new Thread(() -> {
            System.out.println(Thread.currentThread().toString() + cnt.get() + " ");
            cnt.set(cnt.get() + 2);
        });
        t.start();

        // System.out.printf("[LOG] %s: ", LocalDateTime.now());
        System.out.print(Thread.currentThread().toString() + cnt.get() + " ");
        // cnt.set(cnt.get() + 1);
        try {
            Thread.sleep(1000);
            System.out.print(Thread.currentThread().toString() + cnt.get() + " ");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
