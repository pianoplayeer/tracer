package org.jxy.tracer.trace;

import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.bootstrap.DubboBootstrap;
import org.jxy.api.TraceIdService;


public class TraceIdGenerator {
    private static TraceIdService traceIdService;

    static {
        ReferenceConfig<TraceIdService> referenceConfig = new ReferenceConfig<>();
        referenceConfig.setInterface(TraceIdService.class);

        DubboBootstrap.getInstance()
                .application("tracer-client")
                .registry(new RegistryConfig("zookeeper://127.0.0.1:2181"))
                .reference(referenceConfig);

        traceIdService = referenceConfig.get();
    }

    public static long getNewID() {
        return traceIdService.getNewID();
    }
}
