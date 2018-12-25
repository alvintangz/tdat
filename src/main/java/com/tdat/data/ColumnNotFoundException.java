package com.tdat.data;

public class ColumnNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;

	public ColumnNotFoundException(String column){
        super(column + " not found.");
    }
}
