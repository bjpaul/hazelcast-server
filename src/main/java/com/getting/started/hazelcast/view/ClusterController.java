package com.getting.started.hazelcast.view;

import com.getting.started.hazelcast.operations.CustomOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by bijoy on 13/6/16.
 */
@RestController
public class ClusterController {
    @Autowired
    private CustomOperations customOperations;

    @RequestMapping("/publish/{user}")
    public String publishIntoQueue(@PathVariable("user") String user){
        return customOperations.publishIntoQueue(user);
    }

    @RequestMapping("/clients")
    public String allClientmembers(){
        return customOperations.allClientmembers();
    }

    @RequestMapping("/members")
    public String allClusterMembers(){
        return customOperations.allClusterMembers();
    }

    @RequestMapping("/partition")
    public String partition(){
        return customOperations.allPartitions();
    }

    @RequestMapping("/partition/{key}")
    public String partition(@PathVariable("key") String key){
        return customOperations.getPartition(key);
    }
}
