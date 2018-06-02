package com.maxcheung.models;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/*
 *    see https://google.github.io/guava/releases/23.0/api/docs/com/google/common/collect/Table.Cell.html
 *    Row key / column key / value triplet corresponding to a mapping in a table.
 */
@JsonDeserialize(as = CellValueDefault.class)
public interface CellValue {

	String getRowKey();

	void setRowKey(String rowKey);

	String getColumnKey();

	void setColumnKey(String columnKey);

	BigDecimal getBigDecimalValue();
	
	String getStringCellValue();
	

	void setCellValue(BigDecimal value);

	void setCellValue(String value);

	CellType getCellType();

}