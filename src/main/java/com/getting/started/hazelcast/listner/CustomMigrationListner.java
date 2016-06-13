package com.getting.started.hazelcast.listner;

import com.hazelcast.core.MigrationEvent;
import com.hazelcast.core.MigrationListener;

/**
 * Created by bijoy on 12/6/16.
 */
public class CustomMigrationListner implements MigrationListener {
    @Override
    public void migrationStarted(MigrationEvent migrationEvent) {
        System.out.println("migrationStarted"+migrationEvent);
    }

    @Override
    public void migrationCompleted(MigrationEvent migrationEvent) {
        System.out.println("migrationCompleted"+migrationEvent);
    }

    @Override
    public void migrationFailed(MigrationEvent migrationEvent) {
        System.out.println("migrationFailed"+migrationEvent);
    }
}
