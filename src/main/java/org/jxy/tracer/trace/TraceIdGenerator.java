package org.jxy.tracer.trace;

import java.util.concurrent.atomic.AtomicLong;

public class TraceIdGenerator {
    private static AtomicLong current = new AtomicLong(0);

    public static long getNewID() {
        return current.getAndIncrement();
    }
}
