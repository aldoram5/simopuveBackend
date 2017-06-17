/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simopuve.websocket;

import com.simopuve.model.EmployeeLocationStatus;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author aldorangel
 */
@ApplicationScoped
@ServerEndpoint("/monitor")
public class EmployeesLocationWebSocketServer {

    @Inject
    private EmployeesLocationSessionHandler sessionHandler;

    @OnOpen
    public void open(Session session) {
        sessionHandler.addSession(session);
    }

    @OnClose
    public void close(Session session) {
        sessionHandler.removeSession(session);
    }

    @OnError
    public void onError(Throwable error) {
        Logger.getLogger(EmployeesLocationWebSocketServer.class.getName()).log(Level.SEVERE, null, error);

    }

    @OnMessage
    public void handleMessage(String message, Session session) {
        String[] splitMessage = message.split("\\|", -1);
        if (splitMessage.length == 5) {
            EmployeeLocationStatus status = new EmployeeLocationStatus();
            status.setLastUpdated(new Date());
            status.setName(splitMessage[0]);
            status.setPointOfSaleAssigned(splitMessage[1]);
            status.setLatitude(splitMessage[2]);
            status.setLongitude(splitMessage[3]);
            status.setLatitudeLongitudeAdress(splitMessage[4]);
            sessionHandler.addStatus(status);
        }
    }
}
