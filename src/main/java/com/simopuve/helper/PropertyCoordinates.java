/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simopuve.helper;

/**
 *
 * @author neseiza
 */
public class PropertyCoordinates {    
    Integer rowX;
    Integer cellY;

    public Integer getRowX() {
        return rowX;
    }

    public void setRowX(Integer rowX) {
        this.rowX = rowX;
    }

    public Integer getCellY() {
        return cellY;
    }

    public void setCellY(Integer cellY) {
        this.cellY = cellY;
    }        

    public PropertyCoordinates(Integer rowX, Integer cellY) {        
        this.rowX = rowX;
        this.cellY = cellY;
    }
    
}
