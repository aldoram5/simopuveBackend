/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simopuve.helper;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author neseiza
 * 
 * Returns cell position of property according to Excel template 
 */
public class PropertiesMap {
    public static Map getPDVHeaderPropertiesCoordinatesByName() {
        Map tmpMap = new HashMap();
        tmpMap.put("pointOfSaleName", new PropertyCoordinates(3, 2));
        tmpMap.put("address", new PropertyCoordinates(3, 6));
        tmpMap.put("comuna", new PropertyCoordinates(3, 10));
        tmpMap.put("numberOfPeopleAM", new PropertyCoordinates(8, 5));
        tmpMap.put("numberOfPeoplePM", new PropertyCoordinates(9, 5));
        tmpMap.put("peopleWithBags", new PropertyCoordinates(8, 9));
        tmpMap.put("surveyDate", new PropertyCoordinates(6, 2));
        tmpMap.put("completeName", new PropertyCoordinates(6, 7));
        tmpMap.put("numberOfPeopleDidNotAnswer", new PropertyCoordinates(11, 9));
        return tmpMap;
    }
    
    public static Map getPDVRowPropertiesCoordinatesByName() {
        Map tmpMap = new HashMap();
        tmpMap.put("personNumber", new PropertyCoordinates(0, 0));
        tmpMap.put("deviceBrand", new PropertyCoordinates(0, 1));
        tmpMap.put("deviceModel", new PropertyCoordinates(0, 2));
        tmpMap.put("contractType", new PropertyCoordinates(0, 3));
        tmpMap.put("deviceMode", new PropertyCoordinates(0, 4));
        tmpMap.put("additionalCharacteristics", new PropertyCoordinates(0, 5));
        tmpMap.put("boughtCard", new PropertyCoordinates(0, 6));
        tmpMap.put("boughtChip", new PropertyCoordinates(0, 7));
        tmpMap.put("boughtAccessory", new PropertyCoordinates(0, 8));
        tmpMap.put("planRating", new PropertyCoordinates(0, 9));
        tmpMap.put("deviceRating", new PropertyCoordinates(0, 10));
        tmpMap.put("expressRefillValue", new PropertyCoordinates(0, 11));
        tmpMap.put("portabilityChange", new PropertyCoordinates(0, 12));
        tmpMap.put("portabilityChangeReason", new PropertyCoordinates(0, 13));
        return tmpMap;
    }
    
    public static Map getPDVHeaderPositonsForFlowBase() {
        Map tmpMap = new HashMap();
        tmpMap.put("pointOfSaleName", new PropertyCoordinates(null, 0));
        tmpMap.put("address", new PropertyCoordinates(null, 1));
        tmpMap.put("comuna", new PropertyCoordinates(null, 2));
        tmpMap.put("numberOfPeopleAM", new PropertyCoordinates(null, 6));
        tmpMap.put("numberOfPeoplePM", new PropertyCoordinates(null, 7));
        tmpMap.put("peopleWithBags", new PropertyCoordinates(null, 8));
        tmpMap.put("surveyDate", new PropertyCoordinates(null, 4));
        tmpMap.put("completeName", new PropertyCoordinates(null, 5));
        tmpMap.put("numberOfPeopleDidNotAnswer", new PropertyCoordinates(null, 9));
        tmpMap.put("mall", new PropertyCoordinates(null, 13));
        return tmpMap;
    }
}
