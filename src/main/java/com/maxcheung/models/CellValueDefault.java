package com.maxcheung.models;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CellValueDefault extends AbsCellValue   {
	
	public CellValueDefault () {
	}
	
	public CellValueDefault (String rowKey, String columnKey, BigDecimal value) {
		setRowKey(rowKey);
		setColumnKey(columnKey);
		setCellValue(value);
	}
	
	public CellValueDefault (String rowKey, String columnKey, String value) {
		setRowKey(rowKey);
		setColumnKey(columnKey);
		setCellValue(value);
	}
	
	
	@JsonProperty("value")
	public String getValue() {
		return super.getValue();
	}


}
