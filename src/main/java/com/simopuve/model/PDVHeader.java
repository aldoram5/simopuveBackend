/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simopuve.model;

import java.util.Date;

/**
 *
 * @author Aldo Rangel
 */
public class PDVHeader {
    private String pointOfSaleName;
    private String address;
    private String comuna;
    private int numberOfPeopleAM;
    private int numberOfPeoplePM;
    private int peopleWithBags;
    private Date surveyDate;
    private String completeName;
    private int numberOfPeopleDidNotAnswer;

    public PDVHeader() {
    }

    public PDVHeader(String pointOfSaleName, String address, String comuna, int numberOfPeopleAM, int numberOfPeoplePM, int peopleWithBags, Date surveyDate, String completeName, int numberOfPeopleDidNotAnswer) {
        this.pointOfSaleName = pointOfSaleName;
        this.address = address;
        this.comuna = comuna;
        this.numberOfPeopleAM = numberOfPeopleAM;
        this.numberOfPeoplePM = numberOfPeoplePM;
        this.peopleWithBags = peopleWithBags;
        this.surveyDate = surveyDate;
        this.completeName = completeName;
        this.numberOfPeopleDidNotAnswer = numberOfPeopleDidNotAnswer;
    }

    public int getNumberOfPeopleDidNotAnswer() {
        return numberOfPeopleDidNotAnswer;
    }

    public void setNumberOfPeopleDidNotAnswer(int numberOfPeopleDidNotAnswer) {
        this.numberOfPeopleDidNotAnswer = numberOfPeopleDidNotAnswer;
    }

    public String getPointOfSaleName() {
        return pointOfSaleName;
    }

    public void setPointOfSaleName(String pointOfSaleName) {
        this.pointOfSaleName = pointOfSaleName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getComuna() {
        return comuna;
    }

    public void setComuna(String comuna) {
        this.comuna = comuna;
    }

    public int getNumberOfPeopleAM() {
        return numberOfPeopleAM;
    }

    public void setNumberOfPeopleAM(int numberOfPeopleAM) {
        this.numberOfPeopleAM = numberOfPeopleAM;
    }

    public int getNumberOfPeoplePM() {
        return numberOfPeoplePM;
    }

    public void setNumberOfPeoplePM(int numberOfPeoplePM) {
        this.numberOfPeoplePM = numberOfPeoplePM;
    }

    public int getPeopleWithBags() {
        return peopleWithBags;
    }

    public void setPeopleWithBags(int peopleWithBags) {
        this.peopleWithBags = peopleWithBags;
    }

    public Date getSurveyDate() {
        return surveyDate;
    }

    public void setSurveyDate(Date surveyDate) {
        this.surveyDate = surveyDate;
    }

    public String getCompleteName() {
        return completeName;
    }

    public void setCompleteName(String completeName) {
        this.completeName = completeName;
    }
    
    
    
}
