/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simopuve.helper;

import com.simopuve.model.PDVRow;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author neseiza
 */
public class WriteAndReadExcel {

    public void writePoll() {
        FileInputStream isr = null;
        try {
            File filename = new File("/home/neseiza/proyecto-chile/docs/pseudo-platilla.xlsx");
            isr = new FileInputStream(filename);
            Workbook book1 = new XSSFWorkbook(isr);
            isr.close();
            Sheet sheet = book1.getSheetAt(0);      //leer una hoja del libro template

            ExcelFiller.fillHeader(sheet);
            List<PDVRow> PDVRows = new ArrayList();
            PDVRows.add(ExcelFiller.getFilledRow());
            PDVRows.add(ExcelFiller.getFilledRow());
            ExcelFiller.fillRows(sheet, PDVRows);

            //crear nuevo excel             
            FileOutputStream fileOut = new FileOutputStream("/home/neseiza/proyecto-chile/docs/" + System.currentTimeMillis() + "-modificado.xls");
            book1.write(fileOut);
            fileOut.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(WriteAndReadExcel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(WriteAndReadExcel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                isr.close();
            } catch (IOException ex) {
                Logger.getLogger(WriteAndReadExcel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
