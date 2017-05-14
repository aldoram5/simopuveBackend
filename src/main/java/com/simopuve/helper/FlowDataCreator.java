/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simopuve.helper;

import static com.simopuve.helper.POIHelper.espBoolean;
import static com.simopuve.helper.POIHelper.getFlowBaseFormatDate;
import com.simopuve.model.PDVHeader;
import com.simopuve.model.PDVRow;
import com.simopuve.model.PDVSurvey;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

/**
 *
 * @author neseiza
 */
public class FlowDataCreator {

    public static void FillDetailBaseSheet(List<PDVSurvey> surveyList, Sheet detailBaseSheet) {
        int rowIndex = 1;
        for (PDVSurvey survey : surveyList) {
            Row row = null;
            Cell cell = null;
            PDVHeader header = survey.getHeader();

            for (PDVRow encRow : survey.getRows()) {
                row = detailBaseSheet.createRow(rowIndex);
                cell = row.createCell(0);
                cell.setCellValue(header.getPointOfSaleName());

                cell = row.createCell(1);
                cell.setCellValue(header.getAddress());
                
                //TODO obtener info necesaria
                cell = row.createCell(2);
		cell.setCellValue("Comuna");
                
                //TODO obtener info
                cell = row.createCell(3);
		cell.setCellValue("Zona");
                
                cell = row.createCell(4);
		cell.setCellValue(getFlowBaseFormatDate(header.getSurveyDate()));
                
                cell = row.createCell(5);
		cell.setCellValue(header.getCompleteName());
                
                cell = row.createCell(6);
		cell.setCellValue(encRow.getPersonNumber());
                
                cell = row.createCell(7);
		cell.setCellValue(encRow.getDeviceBrand());
                
                cell = row.createCell(8);
		cell.setCellValue(encRow.getDeviceModel());
                
                cell = row.createCell(9);
		cell.setCellValue(encRow.getContractType());
                
                cell = row.createCell(10);
		cell.setCellValue(encRow.getDeviceMode());
                
                cell = row.createCell(11);
		cell.setCellValue(espBoolean(encRow.isBoughtCard()));
                
                cell = row.createCell(12);
		cell.setCellValue(espBoolean(encRow.isBoughtChip()));
                
                cell = row.createCell(13);
		cell.setCellValue(espBoolean(encRow.isBoughtAccessory()));
                
                cell = row.createCell(14);
		cell.setCellValue(encRow.getPlanRating());
                
                //TODO
                cell = row.createCell(15);
		cell.setCellValue("Semana");
                
                //TODO
                cell = row.createCell(16);
		cell.setCellValue("mes");
                
                cell = row.createCell(17);
		cell.setCellValue((header.isMall()) ? "Mall" : "Oficina");
                
                //TODO
                cell = row.createCell(18);
		cell.setCellValue("operador");
                
                cell = row.createCell(19);
		cell.setCellValue(encRow.getDeviceRating());
                
                cell = row.createCell(20);
		cell.setCellValue(encRow.getExpressRefillValue());
                
                cell = row.createCell(21);
		cell.setCellValue(encRow.getAdditionalCharacteristics());
                              
                //TODO
                cell = row.createCell(22);
		cell.setCellValue("Total ventas");
                
                cell = row.createCell(23);
		cell.setCellValue(encRow.getPortabilityChange());
                
                cell = row.createCell(24);
		cell.setCellValue(encRow.getPortabilityChangeReason());
                rowIndex++;
            }
        }
    }

    public static void FillFlowBaseSheet(List<PDVSurvey> surveyList, Sheet flowBaseSheet) {
        int rowIndex = 1;
        for (PDVSurvey survey : surveyList) {
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
            cell.setCellValue(header.getNumberOfPeopleAM() + header.getNumberOfPeoplePM());

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
