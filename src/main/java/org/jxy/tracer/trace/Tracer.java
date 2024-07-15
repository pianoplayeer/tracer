package org.jxy.tracer.trace;

import java.time.LocalDateTime;
import java.util.Stack;

public class Tracer {
    private static InheritableThreadLocal<TraceContext> localContext = new InheritableThreadLocal<>();
    private static ThreadLocal<Stack<TraceContext>> localContextStack = new ThreadLocal<>();

    public static void createTraceEnv(String methodName) {
        long traceId = TraceIdGenerator.getNewID();
        Stack<TraceContext> stack = new Stack<>();
        TraceContext context = new TraceContext(traceId, methodName);

        stack.push(context);
        localContext.set(context);
        localContextStack.set(stack);
    }

    public static void createContext(String methodName) {
        TraceContext ctx = getCurrentContext().newChildContext(methodName);

        localContextStack.get().push(ctx);
        localContext.set(ctx);
    }

    private static TraceContext getCurrentContext() {
        return localContext.get();
    }

    public static void startTrace() {
        TraceContext ctx = getCurrentContext();
        ctx.setTraceStartTime(LocalDateTime.now());
    }

    public static void stopTrace() {
        TraceContext ctx = getCurrentContext();
        ctx.setTraceEndTime(LocalDateTime.now());
    }

    public static void logTrace() {
        System.out.println(getCurrentContext());
    }

    public static void endCurrentTrace() {
        localContextStack.get().pop();
        localContext.remove();

        if (isRootContext()) {
            localContextStack.remove();
        } else {
            localContext.set(localContextStack.get().peek());
        }
    }

    private static boolean isRootContext() {
        return localContextStack.get().isEmpty();
    }
}
