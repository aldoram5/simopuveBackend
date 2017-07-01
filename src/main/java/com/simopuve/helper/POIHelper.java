/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simopuve.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.joda.time.DateTime;
import org.joda.time.Months;
import org.joda.time.Weeks;

/**
 *
 * @author neseiza
 */
public class POIHelper {

    private static final DateTime initDate = new DateTime(2009, 6, 1, 0, 0, 0, 0);
    private static final DateTime initDateWeek = new DateTime(2017, 5, 15, 0, 0, 0, 0);

    public static Workbook getWorkbookFromLocalReource(String resourceName) {
        Workbook wb = null;
        try {
            InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(resourceName);
            wb = new XSSFWorkbook(is);
            is.close();
        } catch (IOException ex) {
            Logger.getLogger(POIHelper.class.getName()).log(Level.SEVERE, null, ex);
        }

        return wb;
    }

    public static Workbook getWorkBookFromPath(String path) {
        FileInputStream isr = null;
        File filename = new File(path);
        Workbook book1 = null;
        try {
            isr = new FileInputStream(filename);
            book1 = new XSSFWorkbook(isr);
            isr.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(POIHelper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(POIHelper.class.getName()).log(Level.SEVERE, null, ex);
        }

        return book1;
    }

    public static String getFlowBaseFormatDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        Integer tmpMoth = cal.get(Calendar.MONTH);
        return cal.get(Calendar.DAY_OF_MONTH) + "/" + tmpMoth + "/" + cal.get(Calendar.YEAR);
    }

    public static String getMothSpName(int MonthNmb) {
        String mes = "";
        switch (MonthNmb) {
            case 0:
                mes = "Enero";
                break;
            case 1:
                mes = "Febrero";
                break;
            case 2:
                mes = "Marzo";
                break;
            case 3:
                mes = "Abril";
                break;
            case 4:
                mes = "Mayo";
                break;
            case 5:
                mes = "Junio";
                break;
            case 6:
                mes = "Julio";
                break;
            case 7:
                mes = "Agosto";
                break;
            case 8:
                mes = "Septiembre";
                break;
            case 9:
                mes = "Octubre";
                break;
            case 10:
                mes = "Noviembre";
                break;
            case 11:
                mes = "Diciembre";
                break;
        }
        return mes;
    }

    public static void writeWorkbookInPath(Workbook wb, String path) {
        try {
            FileOutputStream out = new FileOutputStream(path);
            wb.write(out);
            out.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(POIHelper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(POIHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static String espBoolean(Boolean exp) {
        return exp ? "SÍ" : "NO";
    }

    public static Integer getMonthNumber(Date currentDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentDate);
        DateTime nowDate = new DateTime(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0, 0);
        Months m = Months.monthsBetween(initDate, nowDate);
        return m.getMonths();
    }

    public static Integer getWeekNumber(Date currentDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentDate);
        DateTime nowDate = new DateTime(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0, 0);
        Weeks w = Weeks.weeksBetween(initDateWeek, nowDate);
        return w.getWeeks() + 435;
    }

//    public static void tmp() {
//        Date date = (Date) value;
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(date);
//        int year = cal.get(Calendar.YEAR);
//        int month = cal.get(Calendar.MONTH);
//        int day = cal.get(Calendar.DAY_OF_MONTH);
//        cell.setCellValue((Integer) day);
//        cell = row.getCell(tmpCoordinate.getCellY() + 1);
//        cell.setCellValue((Integer) month);
//        cell = row.getCell(tmpCoordinate.getCellY() + 2);
//        cell.setCellValue((Integer) year);
//    }
}
