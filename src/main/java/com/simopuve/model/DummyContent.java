/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simopuve.model;

/**
 *
 * @author Aldo Rangel
 */
public class DummyContent {
    private String dummyString;
    private int dummyInt;

    public DummyContent(String dummyString, int dummyInt) {
        this.dummyString = dummyString;
        this.dummyInt = dummyInt;
    }

    public int getDummyInt() {
        return dummyInt;
    }

    public void setDummyInt(int dummyInt) {
        this.dummyInt = dummyInt;
    }

    public String getDummyString() {
        return dummyString;
    }

    public void setDummyString(String dummyString) {
        this.dummyString = dummyString;
    }
    
    
}
