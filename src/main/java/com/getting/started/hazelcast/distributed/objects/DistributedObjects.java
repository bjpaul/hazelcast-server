package com.getting.started.hazelcast.distributed.objects;

import com.getting.started.hazelcast.instance.factory.ClusterInstance;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IQueue;
import com.hazelcast.core.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by bijoy on 12/6/16.
 */
@Component
public class DistributedObjects {
    @Autowired
    private ClusterInstance clusterInstance;

    public IQueue getQueue(String queue){
        return clusterInstance.memberInstance().getQueue(queue);
    }

    public IdGenerator getIdGenerator(String idGenerator){
        return clusterInstance.memberInstance().getIdGenerator(idGenerator);
    }

    public HazelcastInstance memberInstance(){
        return clusterInstance.memberInstance();
    }
}
