package org.jxy.tracer.trace;


import lombok.Data;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

@Data
public class TraceContext {
    private long traceId;

    // spanId: {parent.spanId.x}
    private String spanId;

    private AtomicInteger childSpan;

    private LocalDateTime traceStartTime;
    private LocalDateTime traceEndTime;

    private String methodName;

    public TraceContext(long traceId, String methodName) {
        this.traceId = traceId;
        this.spanId = "" + traceId;
        this.methodName = methodName;

        childSpan = new AtomicInteger(0);
    }

    public TraceContext newChildContext(String curMethod) {
        TraceContext child = new TraceContext(traceId, curMethod);
        child.setSpanId(spanId + "." + childSpan.getAndIncrement());

        return child;
    }

    @Override
    public String toString() {
        return "{" + "trace span id: " + spanId +
                    ", method: " + methodName +
                    ", start time: " + traceStartTime +
                    ", time cost: " + Duration.between(traceStartTime, traceEndTime).toMillis() + " ms" +
                "}";

    }

}
