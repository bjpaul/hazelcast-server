package com.getting.started.hazelcast.listner;

import com.hazelcast.core.MemberAttributeEvent;
import com.hazelcast.core.MembershipEvent;
import com.hazelcast.core.MembershipListener;

/**
 * Created by bijoy on 11/6/16.
 */
public class CustomMemberListner implements MembershipListener {
    @Override
    public void memberAdded(MembershipEvent membershipEvent) {
        System.out.println("##################### Member added -- "+ membershipEvent.getMember().getAddress());
    }

    @Override
    public void memberRemoved(MembershipEvent membershipEvent) {
        System.out.println("##################### Member removed -- "+ membershipEvent.getMember().getAddress());
    }

    @Override
    public void memberAttributeChanged(MemberAttributeEvent memberAttributeEvent) {
        System.out.println("##################### Member "+ memberAttributeEvent.getMember().getAddress()+", attribute "+memberAttributeEvent.getValue());
    }
}
