/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simopuve.rest;

import com.simopuve.helper.ExcelWrapperHelper;
import static com.simopuve.helper.FlowDataCreator.FillFlowBaseSheet;
import com.simopuve.helper.POIHelper;
import static com.simopuve.helper.ReadPVDFromFile.getPDVSurveyFromFile;
import com.simopuve.model.BrandDevices;
import com.simopuve.model.PDVHeader;
import com.simopuve.model.PDVSurvey;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author Aldo Rangel
 */
@Path("/api")
@Stateless
public class SimopuveRESTServices {

    private static final String SIMOPUVE_APP_USER_AGENT = "SIMOPUVE-USERAGENT-SIMOPUVE";

    @Path("/dummy")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDummyResponse(@HeaderParam("user-agent") String userAgent, @HeaderParam("Authorization") String authorization) {
        return Response.ok("User Agent: " + userAgent + "\n" + "Authorization: " + authorization).build();
    }

    @Path("/helloworld")
    @GET
    @Produces("text/plain")
    public String getClichedMessage() {
        
        String filePath = new StringBuilder( System.getProperty("jboss.server.data.dir")).append( "/PDV/pdv2.xlsx").toString();
        PDVSurvey survey = null;
        try {
            survey = getPDVSurveyFromFile(filePath, false);
            survey.getHeader();
            
            List<PDVSurvey> surveyList = new ArrayList<>();
            surveyList.add(survey);

            
            
            filePath = new StringBuilder( System.getProperty("jboss.server.data.dir")).append( "/PDV/testFlow.xlsx").toString();
            
            Workbook flowWorkbook = POIHelper.getWorkbookFromLocalReource("plantilla-base-flujo.xlsx");
            FillFlowBaseSheet(surveyList, flowWorkbook.getSheetAt(0));
            POIHelper.writeWorkbookInPath(flowWorkbook, filePath);
            
            
        } catch (IOException ex) {
            Logger.getLogger(SimopuveRESTServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        return "ewwe: " ;
    }
    

    @Path("/brandDevices")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBrandDevices(@HeaderParam("user-agent") String userAgent, @HeaderParam("Authorization") String authorization) {
        if (!userAgent.contentEquals(SIMOPUVE_APP_USER_AGENT)) {
            return Response.status(404).build();
        }
        List<BrandDevices> l = new ArrayList<>();
        BrandDevices b = new BrandDevices("ALCATEL", Arrays.asList("1050", "2051", "5010 PIXI", "5016 POP 5", "5017 PIXI 3", "5045 PIXI 4", "5056 POP 4 PLUS", "6039 IDOL 3 4,7", "6045 IDOL 3 5,5", "6055 IDOL 4", "7048 GO PLAY", "7055 HERO 2C", "8050 PIXI 4 6.0", "OTROS"));
        BrandDevices b1 = new BrandDevices("AZUMI", Arrays.asList("IRO A5Q", "IRO A5QL", "AC24 LITE", "AC24G", "A40 STYLE PLUS", "A50 STYLE PLUS", "A35C LITE", "L2Z", "SPEED 5.5", "OTROS"));
        BrandDevices b2 = new BrandDevices("HTC", Arrays.asList("ONE A9S", "ESIRE 10 LIFESTYLE A56", "DESIRE 530", "10", "ONE M9", "DESIRE 626S", "OTROS"));
        BrandDevices b3 = new BrandDevices("HUAWEI", Arrays.asList("P10 VTR-L09", "ROUTER B310", "MATE 9 MHA-L09", "MODEM WINGLE E8372", "MODEM MIFI E5573", "GR3 TANGO TAG-L03", "P9 LITE VNS-L23", "Y5 II CUN-L03", "Y3 II LUA-L03", "E3372", "P9 PLUS VIE-L09", "P9 EVA-L09", "GR5 KII L23", "MATE 8 NXT-L09", "Y6 LTE", "P8 LITE", "OTROS"));
        BrandDevices b4 = new BrandDevices("IPHONE", Arrays.asList("4", "4S", "5", "5S", "5C", "6", "6 PLUS", "7", "7 PLIS", "SE", "OTROS"));
        BrandDevices b5 = new BrandDevices("LG", Arrays.asList("K8 LTE K350F", "STYLUS II K530", "G5 SE H840�", "G5 SE H840", "�K10 LTE K430T", "H960A V10 LTE", "G4 STYLUS H635", "LEON CK H340F", "G4 LTE H815P", "L LIFT D290", "OTROS"));
        BrandDevices b6 = new BrandDevices("MOTOROLA", Arrays.asList("MOTO Z PLAY", "MOTO Z PLAY", "MOTO G 4TA GEN", "MOTO G 2DA GEN LTE", "MOTO X PLAY", "MOTO G 3RA GEN", "OTROS"));
        BrandDevices b7 = new BrandDevices("ZTE", Arrays.asList("MODEM MIFI MF920T", "MODEM WINGLE MF79S", "BLADE A610", "Blade L110", "BLADE V6 PLUS", "BLADE L5 PLUS", "BLADE A510", "BLADE A460", "BLADE L3", "OTROS"));
        BrandDevices b8 = new BrandDevices("SAMSUNG", Arrays.asList("GALAXY A720F", "GALAXY J2 PRIME G532M", "GALAXY J5 PRIME G570M", "GALAXY J7 PRIME G610M", "GALAXY J7 2016 J710MN", "GALAXY J5 2016 J510MN", "GALAXY J3 LTE J320", "GALAXY J1 ACE VE LTE J111M", "GALAXY S7 EDGE G935F", "GALAXY S7 G930F", "GALAXY J2 LTE J200M", "GALAXY J5 LTE J500M", "E7 LTE E700M", "GALAXY A5 A500Y", "GALAXY CORE PRIME LTE G360GY", "OTROS"));
        BrandDevices b9 = new BrandDevices("LANIX", Arrays.asList("X110", "X500B", "ILIUM L820", "OTROS"));
        BrandDevices b10 = new BrandDevices("SONY_ERICSSON", Arrays.asList("XPERIA XZ F8331", "XPERIA XA ULTRA F3213", "XPERIA XA F3113", "XPERIA X F5121", "XPERIA E5 F3313", "E6553 Z3 PLUS", "XPERIA M5 E5606", "XPERIA Z5 PREMIUM E6853", "XPERIA Z5 E6603", "XPERIA C5 ULTRA E5506", "XPERIA C4 E5353", "M4 AQUA E2306", "XPERIA E4 LTE E2053", "XPERIA M2 D2306", "OTROS"));
        BrandDevices b11 = new BrandDevices("NO_COMPRO_TELEFONO", Arrays.asList("OTROS"));
        BrandDevices b12 = new BrandDevices("OWN", Arrays.asList("FUN PLUS", "ONE", "S4035 3G REACONDICIONADO", "S4035 4G", "F1035", "OTROS"));
        BrandDevices b13 = new BrandDevices("OTROS", Arrays.asList("BMOBILE C240", "RT880", "QX610C", "OTROS"));
        l.add(b);
        l.add(b1);
        l.add(b2);
        l.add(b3);
        l.add(b4);
        l.add(b5);
        l.add(b6);
        l.add(b7);
        l.add(b8);
        l.add(b9);
        l.add(b10);
        l.add(b11);
        l.add(b12);
        l.add(b13);
        return Response.status(200).entity(l).build();
    }

    @Path("/survey")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveSurvey(@HeaderParam("user-agent") String userAgent, @HeaderParam("Authorization") String authorization, PDVSurvey survey) {
        if (userAgent.contentEquals(SIMOPUVE_APP_USER_AGENT)) {

            ExcelWrapperHelper.WritePDVToExcell(survey);
            return Response.status(201).entity(survey.getHeader()).build();

        } else {
            return Response.status(404).build();
        }
    }

    @Path("/user")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response userAgentInfo(@HeaderParam("user-agent") String userAgent, @HeaderParam("Authorization") String authorization) {

        return Response.status(200).build();
    }

    @Path("/login")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response logUserIn(@HeaderParam("user-agent") String userAgent, @HeaderParam("Authorization") String authorization) {
        Logger.getLogger(SimopuveRESTServices.class.getName()).log(Level.INFO, "Received UA: " + userAgent);
        Logger.getLogger(SimopuveRESTServices.class.getName()).log(Level.INFO, "Received Auth: " + authorization);
        if (userAgent.contentEquals(SIMOPUVE_APP_USER_AGENT)) {
            if (authorization != null && authorization.startsWith("Basic")) {
                // Authorization: Basic base64credentials
                String base64Credentials = authorization.substring("Basic".length()).trim();
                String credentials = new String(Base64.getDecoder().decode(base64Credentials),
                        Charset.forName("UTF-8"));
                // credentials = username:password
                final String[] values = credentials.split(":", 2);
                if (values[0].contentEquals("Prueba") && values[1].contentEquals("Prueba")) {

                    return Response.status(200).entity(new PDVHeader("Terapaca", "dirección chilena", "comuna bonita", 0, 0, 0, new Date(), "Usuario Pruebas", 0)).build();
                }
                return Response.status(200).entity(new PDVHeader("Terapaca", "dirección chilena", "comuna bonita", 0, 0, 0, new Date(), "Pepe Perez", 0)).build();
            }
        }
        return Response.status(404).build();

    }
}
