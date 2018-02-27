package cn.acyou.iblogdata.conf;

import cn.acyou.iblog.service.SortService;
import com.alibaba.dubbo.config.*;
import com.alibaba.dubbo.config.spring.ReferenceBean;
import com.alibaba.dubbo.config.spring.ServiceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
@Lazy
public class DubboConfigurer {

    @Bean
    public Dubbo dubbo() {
        return new Dubbo();
    }

    @Bean
    public RegistryConfig registry(Dubbo dubbo) {
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress(dubbo.getZookeeperUrl());
        registryConfig.setProtocol("zookeeper");
        registryConfig.setDefault(false);
        registryConfig.setGroup(dubbo.getGroup());
        return registryConfig;
    }

    @Bean
    public ApplicationConfig applicationConfig(Dubbo dubbo) {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName(dubbo.getApplication());
        return applicationConfig;
    }


    @Bean
    public ProtocolConfig protocol(Dubbo dubbo) {
        ProtocolConfig protocolConfig = new ProtocolConfig();
        protocolConfig.setPort(dubbo.getPort());
        protocolConfig.setSerialization(dubbo.getSerialization());
        return protocolConfig;
    }

    @Bean
    public ProviderConfig provider(Dubbo dubbo) {
        ProviderConfig providerConfig = new ProviderConfig();
        providerConfig.setTimeout(dubbo.getTimeOut());
        providerConfig.setGroup(dubbo.getGroup());
        return providerConfig;
    }

    @Bean
    public ConsumerConfig consumer(Dubbo dubbo) {
        ConsumerConfig consumerConfig = new ConsumerConfig();
        consumerConfig.setTimeout(dubbo.getTimeOut());
        consumerConfig.setOwner(dubbo.getOwner());
        consumerConfig.setGroup(dubbo.getGroup());
        return consumerConfig;
    }

    @Bean
    public ReferenceBean<SortService> sortService(RegistryConfig registryConfig, ApplicationConfig applicationConfig, ConsumerConfig consumerConfig, Dubbo dubbo) {
        return registerReference(registryConfig, applicationConfig, consumerConfig, dubbo, SortService.class);
    }


    protected <T> ReferenceBean<T> registerReference(RegistryConfig registryConfig, ApplicationConfig applicationConfig, ConsumerConfig consumerConfig, Dubbo dubbo, Class<T> interfaceClazz) {
        ReferenceBean<T> ref = new ReferenceBean<>();
        ref.setInterface(interfaceClazz);
        ref.setRegistry(registryConfig);
        ref.setApplication(applicationConfig);
        ref.setConsumer(consumerConfig);
        ref.setGroup(dubbo.getGroup());
        ref.setCheck(dubbo.isStartUpCheck());
        ref.setUrl(dubbo.getRefUrl());
        return ref;
    }

    protected <T> ServiceBean<T> registerService(RegistryConfig registryConfig, ApplicationConfig applicationConfig, ProviderConfig providerConfig, ProtocolConfig protocolConfig, Dubbo dubbo, Class<T> interfaceClazz, T t) {
        ServiceBean<T> ref = new ServiceBean<>();
        ref.setInterface(interfaceClazz);
        ref.setRegistry(registryConfig);
        ref.setApplication(applicationConfig);
        ref.setProvider(providerConfig);
        ref.setRef(t);
        ref.setProtocol(protocolConfig);
        ref.setGroup(dubbo.getGroup());
        return ref;
    }
}

