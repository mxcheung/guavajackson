package com.maxcheung.models;

import java.util.Map;

public class DataTable extends AbsDataTable {

	private Map<String, CellValue> rowTotals;

	private Map<String, CellValue> columnTotals;

	private CellValue grandTotal;

	public Map<String, CellValue> getRowTotals() {
		return rowTotals;
	}

	public void setRowTotals(Map<String, CellValue> rowTotals) {
		this.rowTotals = rowTotals;
	}

	public Map<String, CellValue> getColumnTotals() {
		return columnTotals;
	}

	public void setColumnTotals(Map<String, CellValue> columnTotals) {
		this.columnTotals = columnTotals;
	}

	public CellValue getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(CellValue grandTotal) {
		this.grandTotal = grandTotal;
	}

}
