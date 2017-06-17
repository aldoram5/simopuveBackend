/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simopuve.websocket;

import com.simopuve.model.EmployeeLocationStatus;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.json.JsonObject;
import javax.json.spi.JsonProvider;
import javax.websocket.Session;

/**
 *
 * @author aldorangel
 */
@ApplicationScoped
public class EmployeesLocationSessionHandler {
    
    private final Set<Session> sessions = new HashSet<>();
    private final Set<EmployeeLocationStatus> statuses = new HashSet<>();
    
    public void addSession(Session session) {
        sessions.add(session);
        for (EmployeeLocationStatus status : statuses) {
            JsonObject addMessage = createAddStatusMessage(status);
            sendToSession(session, addMessage);
        }
    }

    public void removeSession(Session session) {
        sessions.remove(session);
    }
    public List<EmployeeLocationStatus> getStatus() {
        return new ArrayList<>(statuses);
    }

    public void addStatus(EmployeeLocationStatus status) {
        if(statuses.contains(status))statuses.remove(status);
        statuses.add(status);
        JsonObject addMessage = createAddStatusMessage(status);
        sendToAllConnectedSessions(addMessage);
    }

    public void removeStatus(int id) {
    }

    public void toggleStatus(int id) {
    }

    private EmployeeLocationStatus getStatusById(int id) {
        return null;
    }
    
    private JsonObject createAddStatusMessage(EmployeeLocationStatus status) {
        JsonProvider provider = JsonProvider.provider();
        JsonObject addMessage = provider.createObjectBuilder()
                .add("hashId", status.hashCode())
                .add("name", status.getName())
                .add("pointOfSaleAssigned", status.getPointOfSaleAssigned())
                .add("latitude", status.getLatitude())
                .add("longitude", status.getLongitude())
                .add("address", status.getLatitudeLongitudeAdress())
                .add("description", status.getLastUpdated().getTime())
                .build();
        return addMessage;
    }

    private void sendToAllConnectedSessions(JsonObject message) {
        for (Session session : sessions) {
            sendToSession(session, message);
        }
    }

    private void sendToSession(Session session, JsonObject message) {
         try {
            session.getBasicRemote().sendText(message.toString());
        } catch (IOException ex) {
            sessions.remove(session);
            Logger.getLogger(EmployeesLocationSessionHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
