package org.jxy.tracer.trace;


import lombok.Data;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

@Data
public class TraceContext {
    // ctxId: {[traceId]:[spanId]}
    private String ctxId;

    private long traceId;

    // spanId: {(parentSpanId.)[curSpan]}
    private String spanId;

    private AtomicInteger childSpan;

    private LocalDateTime traceStartTime;
    private long startMillisTime;
    private long endMillisTime;

    private String methodName;

    public TraceContext(long traceId) {
        this.traceId = traceId;
        this.spanId = "0";

        ctxId = traceId + ":" + spanId;
        childSpan = new AtomicInteger(0);
    }

    @Override
    public String toString() {
        return "{" + "id: " + ctxId +
                    ", method: " + methodName +
                    ", start time: " + traceStartTime +
                    ", time cost: " + (endMillisTime - startMillisTime) + " ms" +
                "}";

    }

}
