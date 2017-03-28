package org.bean;

import org.springframework.context.annotation.Bean;

/**
 * test class
 */

public class School {
    private int ucode;
    private String abbreviation;
    private String name;


    public int getUcode() {
        return ucode;
    }

    public void setUcode(int ucode) {
        this.ucode = ucode;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbrevation) {
        this.abbreviation = abbrevation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



}
