package com.maxcheung.models;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Table;
import com.google.common.collect.Tables;

public abstract class AbsDataTable {

	@JsonIgnore
	private Table<String, String, CellValue> table;

	public Table<String, String, CellValue> getTable() {
		return table;
	}

	public void setTable(Table<String, String, CellValue> table) {
		this.table = table;
	}

	@JsonIgnore
	public Set<String> getRowHeaders() {
		return table.rowKeySet();
	}

	@JsonIgnore
	public Set<String> getColumnHeaders() {
		return table.columnKeySet();
	}

	@JsonProperty("table")
	public Map<String, Map<String, CellValue>> getBackingMap() {
		return table != null ? table.rowMap() : null;
	}

	public void setBackingMap(Map<String, Map<String, CellValue>> backingMap) {
		this.table = tableFromMap(backingMap);
	}

	public static <R, C, V> Table<R, C, V> tableFromMap(Map<R, Map<C, V>> fromTable) {
		Table<R, C, V> table = Tables.newCustomTable(new LinkedHashMap<>(), LinkedHashMap::new);
		for (R rowKey : fromTable.keySet()) {
			Map<C, V> rowMap = fromTable.get(rowKey);
			for (C columnKey : rowMap.keySet()) {
				V value = rowMap.get(columnKey);
				table.put(rowKey, columnKey, value);
			}
		}
		return table;
	}

}
