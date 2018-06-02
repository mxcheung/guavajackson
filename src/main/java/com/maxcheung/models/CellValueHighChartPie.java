package com.maxcheung.models;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CellValueHighChartPie extends AbsCellValue   {

	@JsonProperty("name")
	public String getName() {
		return getRowKey();
	}
	
	@JsonProperty("y")
	public BigDecimal getY() {
		return getBigDecimalValue();
	}
	
	
//    name: "Firefox",
 //   y: 10.38

	
}
