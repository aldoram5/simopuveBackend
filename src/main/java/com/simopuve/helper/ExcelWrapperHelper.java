/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simopuve.helper;

import com.simopuve.model.PDVSurvey;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Aldo Rangel
 */
public class ExcelWrapperHelper {
    public static void WritePDVToExcell(PDVSurvey survey){
        String pointOfSaleName = survey.getHeader().getPointOfSaleName().replace(" ", "");
        String folderName = new StringBuilder().append(new DecimalFormat("00").format(Calendar.getInstance().get(Calendar.DAY_OF_MONTH))).append(new DecimalFormat("00").format(Calendar.getInstance().get(Calendar.MONTH) + 1)).append(Calendar.getInstance().get(Calendar.YEAR)).toString();
        String filePath = new StringBuilder( System.getProperty("jboss.server.data.dir")).append( "/PDV/").append(folderName).append("/").append(pointOfSaleName).append(".xls").toString();
        InputStream is = Thread.currentThread ().getContextClassLoader ()
                .getResourceAsStream ( "pseudo-platilla.xlsx" );
        try {
            Workbook book1 = new XSSFWorkbook(is);
            is.close();
            Sheet sheet = book1.getSheetAt(0); 
            ExcelFiller.fillHeader(survey.getHeader(),sheet);
            ExcelFiller.fillRows(sheet, survey.getRows());
            FileOutputStream fileOut = new FileOutputStream(filePath);
            book1.write(fileOut);
            fileOut.close();
        } catch (IOException ex) {
            Logger.getLogger(ExcelWrapperHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
