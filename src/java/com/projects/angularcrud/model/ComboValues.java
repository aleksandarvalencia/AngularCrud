/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projects.angularcrud.model;

/**
 *
 * @author bd101009
 */
public class ComboValues 
{
    private String label;
    private Integer value;

    public ComboValues(Integer value,String label)
    {
        this.label=label;
        this.value=value;
    }
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

}
