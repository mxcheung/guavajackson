package com.maxcheung.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.collect.Table;

@JsonDeserialize(using = DataTableXPDeserializer.class)
public class DataTableXP  {
	
	private Table<String, String, String> table;

	public Table<String, String, String> getTable() {
		return table;
	}

	public void setTable(Table<String, String, String> table) {
		this.table = table;
	}



}
