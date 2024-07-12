package org.jxy.demo;

import org.aspectj.lang.annotation.DeclareParents;
import org.jxy.tracer.annotation.TraceInfo;
import org.jxy.tracer.annotation.TraceMethod;
import org.jxy.tracer.annotation.TraceService;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicLong;

@TraceService
public class LogDemo {
    @TraceMethod
    public void log(String msg) {
        System.out.println(msg);
    }

    @TraceMethod
    public void say() {
        System.out.println("hhh");
    }


    public static void main(String[] args) {
        LogDemo log = new LogDemo();
        log.log("sdfsdf");
        log.say();
    }
}
