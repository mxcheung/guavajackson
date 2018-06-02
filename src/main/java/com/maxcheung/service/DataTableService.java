package com.maxcheung.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.Table;
import com.maxcheung.models.CellValue;
import com.maxcheung.models.DataTable;
import com.maxcheung.models.FormatType;

public interface DataTableService {

	List<CellValue> createDefaults(Set<String> rowKeys, String columnKey, BigDecimal value);

	List<CellValue> filterValuesByRowIds(List<CellValue> moneys, Set<String> rowIds);

	DataTable createDataTableFromList(List<CellValue> moneyCells);

	List<CellValue> replaceRowKeys(List<CellValue> moneys, Map<String, String> rowIdMap);

	CellValue calcTotal(Map<String, CellValue> totals);

	Map<String, CellValue> getColumnTotal(Table<String, String, CellValue> table);

	Map<String, CellValue> getRowTotal(Table<String, String, CellValue> table);

	List<BigDecimal> convertToListAmounts(Table<String, String, CellValue> table);


}
