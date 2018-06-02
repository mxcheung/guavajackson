package com.maxcheung.models.guava;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.google.common.collect.Table;
import com.google.common.collect.Tables;
import com.maxcheung.models.CellValue;
import com.maxcheung.models.CellValueDefault;
import com.maxcheung.service.DataTableServiceImpl;

public class GuavaDataTableTest {

	private DataTableServiceImpl dataTableService;

	@Before
	public void setUp() {
		dataTableService = new DataTableServiceImpl();
	}

	@Test
	public void shouldSerialise() throws Exception {
		GuavaDataTable dataTable = new GuavaDataTable();
		Table<String, String, CellValue> table = Tables.newCustomTable(new LinkedHashMap<>(), LinkedHashMap::new);
		CellValue cellValue = new CellValueDefault();
		cellValue.setRowKey("rowKey");
		cellValue.setColumnKey("columnKey");
		cellValue.setCellValue(BigDecimal.TEN);
		List<String> rowIds = new ArrayList<String>();
		rowIds.add("APAC");
		rowIds.add("ALPHA");
		rowIds.add("ACME");
		for (String rowId : rowIds) {
			table.put(rowId, cellValue.getColumnKey(), cellValue);
		}
		GuavaCustomTable guavaCustomTable = new GuavaCustomTable();
		guavaCustomTable.setTable(table);
		dataTable.setGuavaCustomTable(guavaCustomTable);
		dataTable.setColumnTotals(dataTableService.getColumnTotal(table));
		dataTable.setRowTotals(dataTableService.getRowTotal(table));
		dataTable.setGrandTotal(dataTableService.calcTotal(dataTable.getRowTotals()));
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new GuavaModule());
		String json = mapper.writeValueAsString(dataTable);
		json = mapper.writeValueAsString(dataTable);
		GuavaDataTable dataTable2 = mapper.readValue(json, GuavaDataTable.class);
		Table<String, String, CellValue> before = dataTable.getGuavaCustomTable().getTable();
		Set<String> beforeKey = before.rowKeySet();
		Table<String, String, CellValue> after = dataTable2.getGuavaCustomTable().getTable();
		after = dataTable2.getGuavaCustomTable().getTable();
		Set<String> afterKey = after.rowKeySet();
		assertEquals(afterKey, beforeKey);
		assertEquals(mapper.writeValueAsString(dataTable2), mapper.writeValueAsString(dataTable));
	}

	@Test
	public void mapguava_table_example() throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new GuavaModule());
		String jsonInput = "{\"key\": \"value\"}";
		TypeReference<HashMap<String, String>> typeRef = new TypeReference<HashMap<String, String>>() {
		};
		Map<String, String> map = mapper.readValue(jsonInput, typeRef);

	}
}
