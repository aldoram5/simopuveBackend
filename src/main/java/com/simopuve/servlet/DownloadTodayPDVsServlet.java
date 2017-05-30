/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simopuve.servlet;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Aldo Rangel
 */
@WebServlet(name = "DownloadTodaySevlet", urlPatterns = {"/DownloadPDVs.do"})
public class DownloadTodayPDVsServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String folderName = new StringBuilder().append(new DecimalFormat("00").format(Calendar.getInstance().get(Calendar.DAY_OF_MONTH))).append("-").append(new DecimalFormat("00").format(Calendar.getInstance().get(Calendar.MONTH) + 1)).append("-").append(Calendar.getInstance().get(Calendar.YEAR)).toString();
            String folderPath = new StringBuilder(System.getProperty("jboss.server.data.dir")).append("/PDV/").append(folderName).append("/").toString();

            File directory = new File(folderPath);
            String[] files = directory.list();

            if (files != null && files.length > 0) {

                //
                // Call the zipFiles method for creating a zip stream.
                //
                byte[] zip = zipFiles(directory, files, folderPath);

                //
                // Sends the response back to the user / browser. The
                // content for zip file type is "application/zip". We
                // also set the content disposition as attachment for
                // the browser to show a dialog that will let user 
                // choose what action will he do to the sent content.
                //
                ServletOutputStream sos = response.getOutputStream();
                response.setContentType("application/zip");
                response.setHeader("Content-Disposition", "attachment; filename=\"PDV-"+ folderName+".zip\"");

                sos.write(zip);
                sos.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private byte[] zipFiles(File directory, String[] files, String sourceDir) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ZipOutputStream zos = new ZipOutputStream(baos);
        byte bytes[] = new byte[2048];

	List<String> fileList = new ArrayList<String>();
        generateFileList(directory, fileList, sourceDir);
        
        for (String fileName : fileList) {
            FileInputStream fis = new FileInputStream(directory.getPath()
                    + "/" + fileName);
            BufferedInputStream bis = new BufferedInputStream(fis);

            zos.putNextEntry(new ZipEntry(fileName));

            int bytesRead;
            while ((bytesRead = bis.read(bytes)) != -1) {
                zos.write(bytes, 0, bytesRead);
            }
            zos.closeEntry();
            bis.close();
            fis.close();
        }
        zos.flush();
        baos.flush();
        zos.close();
        baos.close();

        return baos.toByteArray();
    }

    
    private void generateFileList(File node, List<String> fileList, String sourceFolder){

    	//add file only
	if(node.isFile()){
		fileList.add(generateZipEntry(node.getAbsoluteFile().toString(),sourceFolder) );
	}

	if(node.isDirectory()){
		String[] subNote = node.list();
		for(String filename : subNote){
			generateFileList(new File(node, filename), fileList, sourceFolder);
		}
	}

    }
    
    private String generateZipEntry(String file,String sourceFolder){
    	return file.substring(sourceFolder.length()+1, file.length());
    }

    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
