/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simopuve.helper;

import static com.simopuve.helper.POIHelper.getFlowBaseFormatDate;
import com.simopuve.model.PDVHeader;
import com.simopuve.model.PDVSurvey;
import java.util.List;
import java.util.function.Consumer;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

/**
 *
 * @author neseiza
 */
public class FlowDataCreator {
    
    public static void FillFlowBaseSheet(List<PDVSurvey> surveyList, Sheet flowBaseSheet){
        int rowIndex = 1;
        for(PDVSurvey survey : surveyList) {
            Row row = null;
            Cell cell = null;
            row = flowBaseSheet.createRow(rowIndex);
            PDVHeader header = survey.getHeader();
            
            cell = row.createCell(0);
            cell.setCellValue(header.getPointOfSaleName());
            
            cell = row.createCell(1);
            cell.setCellValue(header.getAddress());
            
            cell = row.createCell(2);
            cell.setCellValue(header.getComuna());
            
            //Campo zona en base flujo
            cell = row.createCell(3);
            cell.setCellValue("Por definir");
            
            cell = row.createCell(4);
            cell.setCellValue(getFlowBaseFormatDate(header.getSurveyDate()));
            
            cell = row.createCell(5);
            cell.setCellValue(header.getCompleteName());
            
            cell = row.createCell(6);
            cell.setCellValue(header.getNumberOfPeopleAM());
            
            cell = row.createCell(7);
            cell.setCellValue(header.getNumberOfPeoplePM());
            
            cell = row.createCell(8);
            cell.setCellValue(header.getPeopleWithBags());
            
            //Campo Compañia de punto de venta
            cell = row.createCell(9);
            cell.setCellValue("Por definir");
            
            cell = row.createCell(10);
            cell.setCellValue(header.getNumberOfPeopleAM()+header.getNumberOfPeoplePM());
            
            //TODO Semana
            cell = row.createCell(11);
            cell.setCellValue("Por definir");
            //TODO Mes
            cell = row.createCell(12);
            cell.setCellValue("Por definir");
            
            cell = row.createCell(13);
            cell.setCellValue((header.isMall()) ? "Mall" : "Oficina");
            
            rowIndex++;
        }
    }
    
}
