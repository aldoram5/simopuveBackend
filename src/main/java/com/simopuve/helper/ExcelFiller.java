/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simopuve.helper;

import com.simopuve.model.PDVHeader;
import com.simopuve.model.PDVRow;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

/**
 *
 * @author neseiza
 */
public class ExcelFiller {
    
    public static void fillRows(Sheet sheet, List<PDVRow> PDVRows) {
        try {
            int rowLocation = UsefulConstants.FIRST_ROW;
            Map rowMap = PropertiesMap.getPDVRowPropertiesCoordinatesByName();
            Row tmpRow = sheet.getRow(rowLocation);
            BeanInfo beanInfo = Introspector.getBeanInfo(PDVRow.class);
            for (PDVRow PDVrow : PDVRows) {
                for (PropertyDescriptor propertyDesc : beanInfo.getPropertyDescriptors()) {
                    if (!UsefulConstants.T_CLASS.equals(propertyDesc.getName()) && !"rowNumber".equals(propertyDesc.getName())) {
                        PropertyCoordinates tmpCoordinate = (PropertyCoordinates) rowMap.get(propertyDesc.getName());                        
                        Object value = propertyDesc.getReadMethod().invoke(PDVrow);                                                
                        Cell cell = tmpRow.getCell(tmpCoordinate.getCellY());
                        if (null != value.getClass().getName()) {
                            switch (value.getClass().getName()) {
                                case UsefulConstants.T_STRING:                                   
                                    cell.setCellValue((String) value);
                                    break;
                                case UsefulConstants.T_INTEGER:
                                    cell.setCellValue((Integer) value);
                                    break;
                                case UsefulConstants.T_BOOLEAN:
                                    cell.setCellValue((boolean) value);
                                    break;
                                default:
                                    break;
                            }
                        }
                    }
                }
                rowLocation++;
                tmpRow = sheet.getRow(rowLocation);
            }
        } catch (IntrospectionException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(ExcelFiller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void fillHeader(Sheet sheet) {
        Map headerMap = PropertiesMap.getPDVHeaderPropertiesCoordinatesByName();
        try {
            PDVHeader bean = getFilledHeader();
            BeanInfo beanInfo = Introspector.getBeanInfo(PDVHeader.class);
            DataFormatter formatter = new DataFormatter();
            for (PropertyDescriptor propertyDesc : beanInfo.getPropertyDescriptors()) {
                String propertyName = propertyDesc.getName();
                if (!UsefulConstants.T_CLASS.equals(propertyName)) {
                    PropertyCoordinates tmpCoordinate = (PropertyCoordinates) headerMap.get(propertyName);
                    Object value = propertyDesc.getReadMethod().invoke(bean);                    
                    Row row = sheet.getRow(tmpCoordinate.getRowX());
                    Cell cell = row.getCell(tmpCoordinate.getCellY());

                    Class valueClass = value.getClass();
                    valueClass.cast(value);

                    if (null != valueClass.getName()) {
                        switch (valueClass.getName()) {
                            case UsefulConstants.T_STRING:
                                cell.setCellValue((String) value);
                                break;
                            case UsefulConstants.T_INTEGER:
                                cell.setCellValue((Integer) value);
                                break;
                            case UsefulConstants.T_DATE:
                                Date date = (Date) value; 
                                Calendar cal = Calendar.getInstance();
                                cal.setTime(date);
                                int year = cal.get(Calendar.YEAR);
                                int month = cal.get(Calendar.MONTH);
                                int day = cal.get(Calendar.DAY_OF_MONTH);
                                cell.setCellValue((Integer) day);
                                cell = row.getCell(tmpCoordinate.getCellY() + 1);
                                cell.setCellValue((Integer) month);
                                cell = row.getCell(tmpCoordinate.getCellY() + 2);
                                cell.setCellValue((Integer) year);
                                break;
                            default:
                                break;
                        }
                    }

                }
            }
        } catch (IntrospectionException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(ExcelFiller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void fillHeader(PDVHeader header,Sheet sheet) {
        Map headerMap = PropertiesMap.getPDVHeaderPropertiesCoordinatesByName();
        try {
            PDVHeader bean = header;
            BeanInfo beanInfo = Introspector.getBeanInfo(PDVHeader.class);
            DataFormatter formatter = new DataFormatter();
            for (PropertyDescriptor propertyDesc : beanInfo.getPropertyDescriptors()) {
                String propertyName = propertyDesc.getName();
                if (!UsefulConstants.T_CLASS.equals(propertyName)) {
                    PropertyCoordinates tmpCoordinate = (PropertyCoordinates) headerMap.get(propertyName);
                    if(tmpCoordinate == null)continue;
                    Object value = propertyDesc.getReadMethod().invoke(bean);                    
                    Row row = sheet.getRow(tmpCoordinate.getRowX());
                    Cell cell = row.getCell(tmpCoordinate.getCellY());

                    Class valueClass = value.getClass();
                    valueClass.cast(value);

                    if (null != valueClass.getName()) {
                        switch (valueClass.getName()) {
                            case UsefulConstants.T_STRING:
                                cell.setCellValue((String) value);
                                break;
                            case UsefulConstants.T_INTEGER:
                                cell.setCellValue((Integer) value);
                                break;
                            case UsefulConstants.T_DATE:
                                Date date = (Date) value; 
                                Calendar cal = Calendar.getInstance();
                                cal.setTime(date);
                                int year = cal.get(Calendar.YEAR);
                                int month = cal.get(Calendar.MONTH);
                                int day = cal.get(Calendar.DAY_OF_MONTH);
                                cell.setCellValue((Integer) day);
                                cell = row.getCell(tmpCoordinate.getCellY() + 1);
                                cell.setCellValue((Integer) month);
                                cell = row.getCell(tmpCoordinate.getCellY() + 2);
                                cell.setCellValue((Integer) year);
                                break;
                            default:
                                break;
                        }
                    }

                }
            }
        } catch (IntrospectionException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(ExcelFiller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static PDVHeader getFilledHeader() {
        return new PDVHeader("prueba 1", "prueba 1", "prueba 1", 0, 0, 0, new Date(), "prueba 1", 0);
    }

    public static PDVRow getFilledRow() {
        return new PDVRow(0, 0, "prueba 1", "prueba 1", "prueba 1", "prueba 1", "prueba 1", true, true, true, "prueba 1", "prueba 1", 0, "prueba 1", "prueba 1");
    }
}
