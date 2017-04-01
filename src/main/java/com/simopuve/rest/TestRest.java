/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simopuve.rest;

import com.simopuve.model.DummyContent;
import com.simopuve.model.PDVHeader;
import com.simopuve.model.PDVSurvey;
import java.util.Date;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Aldo Rangel
 */
@Path("/tests")
@Stateless
public class TestRest {

    @Path("/dummy")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDummyResponse(@HeaderParam("user-agent") String userAgent, @HeaderParam("Authorization") String authorization) {
        return Response.ok("User Agent: "+ userAgent +"\n" + "Authorization: "+ authorization).build();
    }
    
    @Path("/survey")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response saveSurvey(PDVSurvey survey){
        
        return Response.status(201).entity(survey.getHeader()).build();
    }

    
    @Path("/user")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response userAgentInfo(@HeaderParam("user-agent") String userAgent, @HeaderParam("Authorization") String authorization){
               
        return Response.status(200).build();
    }
    @Path("/login")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response logUserIn(@HeaderParam("user-agent") String userAgent, @HeaderParam("Authorization") String authorization){
               
        return Response.status(200).entity( new PDVHeader("Terapaca", "dirección chilena", "comuna bonita", 0, 0, 0, new Date(), "Pepe Perez", 0)).build();
    }
}
