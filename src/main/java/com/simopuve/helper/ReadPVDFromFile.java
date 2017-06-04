package com.simopuve.helper;

import static com.simopuve.helper.POIHelper.getWorkBookFromPath;
import static com.simopuve.helper.PropertiesMap.*;
import static com.simopuve.helper.UsefulConstants.FIRST_ROW;
import com.simopuve.model.PDVHeader;
import com.simopuve.model.PDVRow;
import com.simopuve.model.PDVSurvey;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author neseiza
 */
public class ReadPVDFromFile {

    public static PDVSurvey getPDVSurveyFromFile(String path, Boolean isMall) throws IOException {
        PDVSurvey PDVSurvey = new PDVSurvey();
        PDVHeader PDVHeader = new PDVHeader();
        PDVRow PDVRow = new PDVRow();
        PDVSurvey.setHeader(PDVHeader);

        Workbook book1 = getWorkBookFromPath(path);
        fillHeader(PDVHeader, book1.getSheetAt(0), isMall);
        PDVSurvey.setRows(getRowsFromWorkbook(book1.getSheetAt(0)));
        PDVSurvey.getRows().size();

        return PDVSurvey;
    }

    private static void fillHeader(PDVHeader PDVHeader, Sheet sheet, Boolean mall) {
        PropertyCoordinates tmpCoordinate;
        String text;
        DataFormatter formatter = new DataFormatter();
        Map headerMap = getPDVHeaderPropertiesCoordinatesByName();

        PDVHeader.setMall(mall);

        tmpCoordinate = (PropertyCoordinates) headerMap.get("pointOfSaleName");
        text = getTextFromCell(tmpCoordinate.getRowX(), tmpCoordinate.getCellY(), formatter, sheet, null);
        PDVHeader.setPointOfSaleName(text);

        tmpCoordinate = (PropertyCoordinates) headerMap.get("address");
        text = getTextFromCell(tmpCoordinate.getRowX(), tmpCoordinate.getCellY(), formatter, sheet, null);
        PDVHeader.setAddress(text);

        tmpCoordinate = (PropertyCoordinates) headerMap.get("comuna");
        text = getTextFromCell(tmpCoordinate.getRowX(), tmpCoordinate.getCellY(), formatter, sheet, null);
        PDVHeader.setComuna(text);

        tmpCoordinate = (PropertyCoordinates) headerMap.get("completeName");
        text = getTextFromCell(tmpCoordinate.getRowX(), tmpCoordinate.getCellY(), formatter, sheet, null);
        PDVHeader.setCompleteName(text);

        tmpCoordinate = (PropertyCoordinates) headerMap.get("numberOfPeopleAM");
        text = getTextFromCell(tmpCoordinate.getRowX(), tmpCoordinate.getCellY(), formatter, sheet, null);
        PDVHeader.setNumberOfPeopleAM(Integer.parseInt(text));

        tmpCoordinate = (PropertyCoordinates) headerMap.get("numberOfPeoplePM");
        text = getTextFromCell(tmpCoordinate.getRowX(), tmpCoordinate.getCellY(), formatter, sheet, null);
        PDVHeader.setNumberOfPeoplePM(Integer.parseInt(text));

        tmpCoordinate = (PropertyCoordinates) headerMap.get("peopleWithBags");
        text = getTextFromCell(tmpCoordinate.getRowX(), tmpCoordinate.getCellY(), formatter, sheet, null);
        PDVHeader.setPeopleWithBags(Integer.parseInt(text));

        tmpCoordinate = (PropertyCoordinates) headerMap.get("surveyDate");
        String day = getTextFromCell(tmpCoordinate.getRowX(), tmpCoordinate.getCellY(), formatter, sheet, null);
        String month = getTextFromCell(tmpCoordinate.getRowX(), tmpCoordinate.getCellY() + 1, formatter, sheet, null);
        String year = getTextFromCell(tmpCoordinate.getRowX(), tmpCoordinate.getCellY() + 2 , formatter, sheet, null);
        Date currentDate;
        try {
            currentDate = new SimpleDateFormat("dd/MM/yyyy").parse( day + "/" + month + "/" + year );
            PDVHeader.setSurveyDate(currentDate);
        } catch (ParseException ex) {
            Logger.getLogger(ReadPVDFromFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    private static String getTextFromCell(Integer rowX, Integer cellY, DataFormatter formatter, Sheet sheet, Row row) {
        Row rowL = (row == null) ? sheet.getRow(rowX) : row ;
        Cell cell = rowL.getCell(cellY);
        return formatter.formatCellValue(cell);
    }

    private static List<PDVRow> getRowsFromWorkbook(Sheet sheet) {
        Map rowMap = getPDVRowPropertiesCoordinatesByName();
        List<PDVRow> PDVRowList = new ArrayList<>();
        DataFormatter formatter = new DataFormatter();
        PropertyCoordinates tmpCoordinate;
        String text;
        sheet.getPhysicalNumberOfRows();
        //TODO revisar si es siempre así 
        int longitud = (sheet.getPhysicalNumberOfRows() - FIRST_ROW)-2;
        int endRows = FIRST_ROW+longitud;
        int i = FIRST_ROW;
        String personNum = getTextFromCell(FIRST_ROW, 0, formatter, sheet, sheet.getRow(FIRST_ROW));
        boolean isPersonNumberEmpty = personNum.isEmpty();
        while(!isPersonNumberEmpty){
            Row row = sheet.getRow(i);
            PDVRow PDVRow = new PDVRow();
            
            tmpCoordinate = (PropertyCoordinates) rowMap.get("deviceBrand");
            text = getTextFromCell(tmpCoordinate.getRowX(), tmpCoordinate.getCellY(), formatter, sheet, row);
            PDVRow.setDeviceBrand(text);
            
            tmpCoordinate = (PropertyCoordinates) rowMap.get("deviceModel");
            text = getTextFromCell(tmpCoordinate.getRowX(), tmpCoordinate.getCellY(), formatter, sheet, row);
            PDVRow.setDeviceModel(text);
            
            tmpCoordinate = (PropertyCoordinates) rowMap.get("contractType");
            text = getTextFromCell(tmpCoordinate.getRowX(), tmpCoordinate.getCellY(), formatter, sheet, row);
            PDVRow.setContractType(text);
            
            tmpCoordinate = (PropertyCoordinates) rowMap.get("deviceMode");
            text = getTextFromCell(tmpCoordinate.getRowX(), tmpCoordinate.getCellY(), formatter, sheet, row);
            PDVRow.setDeviceMode(text);
            
            tmpCoordinate = (PropertyCoordinates) rowMap.get("additionalCharacteristics");
            text = getTextFromCell(tmpCoordinate.getRowX(), tmpCoordinate.getCellY(), formatter, sheet, row);
            PDVRow.setAdditionalCharacteristics(text);
            
            tmpCoordinate = (PropertyCoordinates) rowMap.get("planRating");
            text = getTextFromCell(tmpCoordinate.getRowX(), tmpCoordinate.getCellY(), formatter, sheet, row);
            PDVRow.setPlanRating(text);
            
            tmpCoordinate = (PropertyCoordinates) rowMap.get("deviceRating");
            text = getTextFromCell(tmpCoordinate.getRowX(), tmpCoordinate.getCellY(), formatter, sheet, row);
            PDVRow.setDeviceRating(text);
            
            tmpCoordinate = (PropertyCoordinates) rowMap.get("portabilityChange");
            text = getTextFromCell(tmpCoordinate.getRowX(), tmpCoordinate.getCellY(), formatter, sheet, row);
            PDVRow.setPortabilityChange(text);
            
            tmpCoordinate = (PropertyCoordinates) rowMap.get("portabilityChangeReason");
            text = getTextFromCell(tmpCoordinate.getRowX(), tmpCoordinate.getCellY(), formatter, sheet, row);
            PDVRow.setPortabilityChangeReason(text);
            
            tmpCoordinate = (PropertyCoordinates) rowMap.get("personNumber");
            text = getTextFromCell(tmpCoordinate.getRowX(), tmpCoordinate.getCellY(), formatter, sheet, row);
            PDVRow.setPersonNumber(Integer.parseInt(text));
            isPersonNumberEmpty = getTextFromCell(tmpCoordinate.getRowX(), tmpCoordinate.getCellY(), formatter, sheet, sheet.getRow(i+1)).isEmpty();
            
            tmpCoordinate = (PropertyCoordinates) rowMap.get("expressRefillValue");
            text = getTextFromCell(tmpCoordinate.getRowX(), tmpCoordinate.getCellY(), formatter, sheet, row);
            String tmpTxt = text.replace("$", "");
            tmpTxt = tmpTxt.replaceAll("\\s+","");
            tmpTxt = tmpTxt.replaceAll(",","");
            Logger.getLogger(ReadPVDFromFile.class.getName()).log(Level.INFO, "valor a convertir:" + tmpTxt);
            PDVRow.setExpressRefillValue(Integer.parseInt(tmpTxt));
            
            tmpCoordinate = (PropertyCoordinates) rowMap.get("boughtCard");
            text = getTextFromCell(tmpCoordinate.getRowX(), tmpCoordinate.getCellY(), formatter, sheet, row);
            PDVRow.setBoughtCard(!("No".equals(text)));
            
            tmpCoordinate = (PropertyCoordinates) rowMap.get("boughtChip");
            text = getTextFromCell(tmpCoordinate.getRowX(), tmpCoordinate.getCellY(), formatter, sheet, row);
            PDVRow.setBoughtChip(!("No".equals(text)));
            
            tmpCoordinate = (PropertyCoordinates) rowMap.get("boughtAccessory");
            text = getTextFromCell(tmpCoordinate.getRowX(), tmpCoordinate.getCellY(), formatter, sheet, row);
            PDVRow.setBoughtAccessory(!("No".equals(text)));
            PDVRowList.add(PDVRow);
            Logger.getLogger(ReadPVDFromFile.class.getName()).log(Level.INFO, PDVRow.toString());
            i++;
        }
        
        
        Logger.getLogger(ReadPVDFromFile.class.getName()).log(Level.INFO, "Tama\u00f1o de rows: {0}", PDVRowList.size());
        return PDVRowList;
    }
}
