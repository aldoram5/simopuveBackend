/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simopuve.rest;

import com.simopuve.model.DummyContent;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
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

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDummyResponse() {
        return Response.ok(new DummyContent("dummytest", 1)).build();
    }

}
