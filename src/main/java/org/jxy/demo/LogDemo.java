package org.jxy.demo;

import org.aspectj.lang.annotation.DeclareParents;
import org.jxy.tracer.annotation.TraceInfo;
import org.jxy.tracer.annotation.TraceService;

@TraceService
public class LogDemo {
    public void log(String msg) {
        System.out.println(msg);
    }

    public void say() {
        System.out.println("hhh");
    }

    public static void main(String[] args) {
        LogDemo log = new LogDemo();
        log.log("hello");
        log.log("log");
        log.say();
    }
}
