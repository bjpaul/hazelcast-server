package com.getting.started.hazelcast.listner;

import com.hazelcast.core.Client;
import com.hazelcast.core.ClientListener;

/**
 * Created by bijoy on 11/6/16.
 */
public class CustomClientListner implements ClientListener {
    @Override
    public void clientConnected(Client client) {
        System.out.println("^^^^^^^^^^ clientConnected "+client.getUuid()+", of type "+client.getClientType());
    }

    @Override
    public void clientDisconnected(Client client) {
        System.out.println("^^^^^^^^^^ clientDisconnected "+client.getUuid()+", of type "+client.getClientType());
    }
}
