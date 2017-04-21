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
public class BrandDevices {
    private String brandName;
    private List<String>models;

    public BrandDevices() {
    }

    public BrandDevices(String brandName, List<String> models) {
        this.brandName = brandName;
        this.models = models;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public List<String> getModels() {
        return models;
    }

    public void setModels(List<String> models) {
        this.models = models;
    }
    
    
}
