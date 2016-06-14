package com.getting.started.hazelcast.instance.factory;

import com.getting.started.hazelcast.listner.CustomClientListner;
import com.getting.started.hazelcast.listner.CustomMemberListner;
import com.getting.started.hazelcast.listner.CustomMigrationListner;
import com.hazelcast.config.Config;
import com.hazelcast.config.JoinConfig;
import com.hazelcast.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

/**
 * Created by bijoy on 11/6/16.
 */
@Component
public class ClusterInstance {
    private HazelcastInstance hazelcastInstance;

    @Value("${hazelcast.server.host}")
    private String host;
    @Value("${management.center.base}")
    private String managementBase;
    @Value("${management.center.port}")
    private String managementPort;
    @Value("${management.center.url}")
    private String managementUrl;

    @PostConstruct
    public void init() {
        System.out.println("<================ Init ====================>");
        memberInstance();
    }

    public HazelcastInstance memberInstance() {
        if (hazelcastInstance == null || !hazelcastInstance.getLifecycleService().isRunning()) {
            Config config = new Config()
                /*.setLiteMember(true)*/;
            config.getGroupConfig()
                    .setName("test")
                    .setPassword("test");
            config.setInstanceName("My instance " + UUID.randomUUID());

            JoinConfig joinConfig = config.getNetworkConfig().getJoin();
            joinConfig.getMulticastConfig().setEnabled(false);
            joinConfig.getTcpIpConfig().setEnabled(true).addMember(host);

            config.getManagementCenterConfig()
                    .setEnabled(true)
                    .setUpdateInterval(1)
                    .setUrl(managementBase+":"+managementPort+"/"+managementUrl);

            synchronized (this) {
                if (hazelcastInstance == null || !hazelcastInstance.getLifecycleService().isRunning()) {
                    hazelcastInstance = Hazelcast.newHazelcastInstance(config);
                    addListners();
                }
            }
        }
        return hazelcastInstance;
    }

    private void addListners() {
        hazelcastInstance.getCluster().addMembershipListener(new CustomMemberListner());
        hazelcastInstance.getPartitionService().addMigrationListener(new CustomMigrationListner());
        hazelcastInstance.getClientService().addClientListener(new CustomClientListner());
    }

    @PreDestroy
    public void shutdown() {
        System.out.println("<================ shutdown ====================>");
        hazelcastInstance.shutdown();
    }
}
