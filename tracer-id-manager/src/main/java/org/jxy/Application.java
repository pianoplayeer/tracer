package org.jxy;

import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;
import org.apache.dubbo.config.bootstrap.DubboBootstrap;
import org.jxy.api.TraceIdService;
import org.jxy.service.TraceIdServiceImpl;

public class Application {
    public static void main(String[] args) {
        ServiceConfig<TraceIdService> serviceConfig = new ServiceConfig<>();
        serviceConfig.setInterface(TraceIdService.class);
        serviceConfig.setRef(new TraceIdServiceImpl());

        DubboBootstrap.getInstance()
                .application("id-manager")
                .registry(new RegistryConfig("zookeeper://127.0.0.1:2181"))
                .protocol(new ProtocolConfig("dubbo", -1))
                .service(serviceConfig)
                .start()
                .await();
    }
}
