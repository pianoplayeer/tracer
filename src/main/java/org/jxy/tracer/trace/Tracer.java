package org.jxy.tracer.trace;

import java.time.LocalDateTime;
import java.util.Stack;

public class Tracer {
    private static InheritableThreadLocal<TraceContext> localContext = new InheritableThreadLocal<>();
    private static ThreadLocal<Stack<TraceContext>> localContextStack = new ThreadLocal<>();

    public static void createContext() {
        long traceId = TraceIdGenerator.getNewID();
        Stack<TraceContext> stack = new Stack<>();
        TraceContext context = new TraceContext(traceId);

        stack.push(context);
        localContext.set(context);
        localContextStack.set(new Stack<>());
    }

    public static void startTrace() {
        TraceContext ctx = localContextStack.get().peek();
        ctx.setStartMillisTime(System.currentTimeMillis());
        ctx.setTraceStartTime(LocalDateTime.now());
    }

    public static void stopTrace() {

    }

    public static void logTrace() {
        System.out.println();
    }

    public static void destroyContext() {
        localContextStack.remove();
    }
}
