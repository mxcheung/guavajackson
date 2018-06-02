package com.maxcheung.models.guava;

import java.util.Map;

import com.maxcheung.models.CellValue;

public class GuavaDataTable  {
	
	private GuavaCustomTable guavaCustomTable;

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

	public GuavaCustomTable getGuavaCustomTable() {
		return guavaCustomTable;
	}

	public void setGuavaCustomTable(GuavaCustomTable guavaCustomTable) {
		this.guavaCustomTable = guavaCustomTable;
	}

	
}
