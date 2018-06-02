package com.maxcheung.models;

import java.math.BigDecimal;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class AbsCellValue implements CellValue {

	@JsonIgnore
	private String rowKey;
	@JsonIgnore
	private String columnKey;

	@JsonIgnore
	private String value;
	
	@JsonIgnore
	private CellType cellType;

	@Override
	public String getRowKey() {
		return rowKey;
	}

	@Override
	public void setRowKey(String rowKey) {
		this.rowKey = rowKey;
	}

	@Override
	public String getColumnKey() {
		return columnKey;
	}

	@Override
	public void setColumnKey(String columnKey) {
		this.columnKey = columnKey;
	}

	@JsonIgnore
	@Override
	public BigDecimal getBigDecimalValue() {
		 switch(getCellType()) {
		 case CELLTYPE_BIGDECIMAL:
			 return new BigDecimal(value);
		 default:
			 return BigDecimal.ZERO;
		 }
	}

	@JsonIgnore
	@Override
	public String getStringCellValue() {
		return value;
	}
	
	@Override
	public void setCellValue(BigDecimal value) {
		this.cellType = CellType.CELLTYPE_BIGDECIMAL;
		this.value = value != null ? value.toPlainString() : BigDecimal.ZERO.toPlainString();
	}

	@Override
	public void setCellValue(String value) {
		this.cellType = CellType.CELLTYPE_STRING;
		this.value = value != null ? value : "";
	}

	@Override
	public CellType getCellType() {
		return cellType != null ? cellType : CellType.CELLTYPE_STRING;
	}
	
	public void setCellType(CellType cellType) {
		this.cellType = cellType;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	
	
	
}