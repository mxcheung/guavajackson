package com.maxcheung.models.guava;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.collect.Table;
import com.maxcheung.models.CellValue;

@JsonDeserialize(using = GuavaCustomTableDeserializer.class)
public class GuavaCustomTable  {

	private Table<String, String, CellValue> table;

	public Table<String, String, CellValue> getTable() {
		return table;
	}

	public void setTable(Table<String, String, CellValue> table) {
		this.table = table;
	}


}
