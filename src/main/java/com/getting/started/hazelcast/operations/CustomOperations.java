package com.getting.started.hazelcast.operations;

import com.getting.started.hazelcast.distributed.objects.DistributedObjects;
import com.hazelcast.core.Client;
import com.hazelcast.core.Member;
import com.hazelcast.core.Partition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.lang.String;
import java.lang.Thread;
import java.util.Collection;
import java.util.Set;

/**
 * Created by bijoy on 12/6/16.
 */
@Service
public class CustomOperations {
    @Autowired
    private DistributedObjects distributedObjects;

    private Thread publishMsg;

    private volatile String user="Anonymous user";

    @PostConstruct
    public void init(){
        publishMsg = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(500);
                        Long nextId = distributedObjects.getIdGenerator("idGenerator").newId();
                        System.out.println(user+" -> publish :: " + nextId);
                        distributedObjects.getQueue("distributedQueue").put(nextId);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
    public String allClusterMembers(){
        String allClusterMembers = "";
        Set<Member> members = distributedObjects.memberInstance().getCluster().getMembers();
        for (Member member : members) {
            allClusterMembers+="<p>"+member.getAddress() + "</p>";
        }
        return allClusterMembers;
    }

    public String allClientmembers(){
        String allClientmembers = "";
        Collection<Client> clients = distributedObjects.memberInstance().getClientService().getConnectedClients();
        for (Client client : clients) {
            allClientmembers+="<p>"+client.getUuid() + " : "+client.getClientType()+"</p>";
        }
        return allClientmembers;
    }

    public String allPartitions(){
        String allPartition = "";
        for (Partition partition : distributedObjects.memberInstance().getPartitionService().getPartitions()) {
            allPartition+="<p>"+partition+"</p>";
        }
        return allPartition;
    }

    public String getPartition(Object key){
        return distributedObjects.memberInstance().getPartitionService().getPartition(key).toString();
    }

    public String publishIntoQueue(String user){
        if(!publishMsg.isAlive()) {
            this.user = user;
            publishMsg.start();
            return "Started !!!";
        }
        return "Already started...";
    }

}
