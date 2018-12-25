package com.tdat.data;

public class YearNotFoundException extends Exception {
    public YearNotFoundException(String year){
        super(year + " not found.");
    }
}
