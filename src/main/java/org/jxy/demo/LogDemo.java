package org.jxy.demo;


import org.jxy.tracer.annotation.TraceInfo;
import org.jxy.tracer.annotation.TraceService;

public class LogDemo {
    @TraceInfo
    public void log(String msg) {
        System.out.println(msg);
    }

    @TraceInfo
    public void say() {
        System.out.println("hhh");
    }

    @TraceService
    public static void main(String[] args) {
        LogDemo log = new LogDemo();
        log.log("sdfsdf");
        log.say();
    }
}
