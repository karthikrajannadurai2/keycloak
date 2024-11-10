package org.keycloak.customEventListener;

import org.keycloak.events.Event;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.EventType;
import org.keycloak.events.admin.AdminEvent;

public class CustomEventListener implements EventListenerProvider {

    @Override
    public void onEvent(Event event) {
        if (event.getType() == EventType.CLIENT_UPDATE) {
            System.out.println("karthik---client updated.....");
        }
        System.out.println("Karthik" + event.getType());
    }

    @Override
    public void onEvent(AdminEvent adminEvent, boolean b) {
        System.out.println("Karthik--" + adminEvent.getId());
    }

    @Override
    public void close() {
        System.out.println("Karthik--close");
    }
}