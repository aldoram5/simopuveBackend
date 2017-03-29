/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simopuve.model;

import java.util.List;

/**
 *
 * @author Aldo Rangel
 */
public class PDVSurvey {
    private PDVHeader header;
    private List<PDVRow> rows;

    public PDVSurvey() {
    }

    public PDVHeader getHeader() {
        return header;
    }

    public void setHeader(PDVHeader header) {
        this.header = header;
    }

    public List<PDVRow> getRows() {
        return rows;
    }

    public void setRows(List<PDVRow> rows) {
        this.rows = rows;
    }
    
}
