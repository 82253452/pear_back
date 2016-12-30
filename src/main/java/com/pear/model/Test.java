package com.pear.model;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * Created by admin on 2016/12/23.
 */
@Table
public class Test {
    @Column
    private String test;

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }
}
