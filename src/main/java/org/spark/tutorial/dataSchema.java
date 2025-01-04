package org.spark.tutorial;

import java.io.Serializable;

public class dataSchema implements Serializable {
    private String name;
    private String val;

    public dataSchema(String name, String val) {
        this.name = name;
        this.val = val;
    }

    public dataSchema() {
    }

    public String getName() {
        return this.name;
    }

    public String getVal() {
        return this.val;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVal(String val) {
        this.val = val;
    }
}
