/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simopuve.model;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author aldorangel
 */
public class EmployeeLocationStatus {
    private String name;
    private String pointOfSaleAssigned;
    private String latitude;
    private String longitude;
    private String latitudeLongitudeAdress;
    private Date lastUpdated;

    public EmployeeLocationStatus() {
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPointOfSaleAssigned() {
        return pointOfSaleAssigned;
    }

    public void setPointOfSaleAssigned(String pointOfSaleAssigned) {
        this.pointOfSaleAssigned = pointOfSaleAssigned;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitudeLongitudeAdress() {
        return latitudeLongitudeAdress;
    }

    public void setLatitudeLongitudeAdress(String latitudeLongitudeAdress) {
        this.latitudeLongitudeAdress = latitudeLongitudeAdress;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.name);
        hash = 67 * hash + Objects.hashCode(this.pointOfSaleAssigned);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final EmployeeLocationStatus other = (EmployeeLocationStatus) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.pointOfSaleAssigned, other.pointOfSaleAssigned)) {
            return false;
        }
        return true;
    }
    
}
