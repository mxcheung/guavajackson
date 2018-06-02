package com.maxcheung.models;

import java.util.Arrays;
import java.util.List;

public class CellValueHighChartBar extends AbsCellValue   {

	// Bar Chart return list of list
	/*
	 * {"highchartbar":[["Microsoft Internet Explorer",56.33],["Chrome",24.03],
	 * ["Firefox",10.38],["Safari",4.77],["Opera",0.91],["Proprietary or Undetectable",0.2]]}
	 */
//	@JsonIgnore
	public List<Object> getPairValue() {
		return Arrays.asList(getRowKey(), super.getBigDecimalValue());
	}

	
	
//    name: "Firefox",
 //   y: 10.38

	
}
