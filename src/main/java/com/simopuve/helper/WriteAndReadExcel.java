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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author neseiza
 */
public class WriteAndReadExcel {

    public void test() {
//        FileInputStream isr = null;
//        try {
//            File filename = new File("/home/neseiza/proyecto-chile/docs/Planilla_Actualizada_01-03-17.xlsx");
//            isr = new FileInputStream(filename);
//            Workbook book1 = new XSSFWorkbook(isr);
//            isr.close();
//            //leer una hoja del libro template
//            Sheet sheet = book1.getSheetAt(0);
//            DataFormatter formatter = new DataFormatter();
////            for (Row row : sheet) {
////                for (Cell cell : row) {
////                    String text = formatter.formatCellValue(cell);
////                    System.out.println(text);
////                }
////            }
//
//            //llenar una celda especifica
//            Row row = sheet.getRow(8);
//            Cell cell = row.getCell(9);
//            cell.setCellValue("Use with word wrap on to create a new line");
//
//            //llenar columna de datos
//            row = sheet.getRow(15);
//            for (int i = 0; i < 14; i++) {
//                Cell cell1 = row.getCell(i);
//                cell1.setCellValue(i + "a");
//            }
//
//            //crear nuevo excel             
//            FileOutputStream fileOut = new FileOutputStream("/home/neseiza/proyecto-chile/docs/" + System.currentTimeMillis() + "modificado.xls");
//            book1.write(fileOut);
//            fileOut.close();
//
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(ExcelResdAndWrite.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(ExcelResdAndWrite.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            try {
//                isr.close();
//            } catch (IOException ex) {
//                Logger.getLogger(ExcelResdAndWrite.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
    }

}
