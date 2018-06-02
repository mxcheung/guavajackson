package com.maxcheung.models;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.google.common.collect.Table;
import com.google.common.collect.Tables;
import com.maxcheung.service.DataTableService;
import com.maxcheung.service.DataTableServiceImpl;

public class DataTableTest {

	private final static String JSON_STRING = "{\"rowTotals\":{\"AANL\":{\"value\":\"10\"},\"AACB\":{\"value\":\"10\"},\"APAC\":{\"value\":\"10\"}},\"columnTotals\":{\"columnKey\":{\"value\":\"30\"}},\"grandTotal\":{\"value\":\"30\"},\"table\":{\"APAC\":{\"columnKey\":{\"value\":\"10\"}},\"AACB\":{\"columnKey\":{\"value\":\"10\"}},\"AANL\":{\"columnKey\":{\"value\":\"10\"}}}}";

	private ObjectMapper mapper;
	private DataTableService dataTableService;

	@Before
	public void setUp() {
		mapper = getMapper();
		dataTableService = new DataTableServiceImpl();
	}

	@Test
	public void shouldSerialize() throws JsonProcessingException {
		DataTable dataTable = getDataTable();
		String json = this.mapper.writeValueAsString(dataTable);
		assertEquals(JSON_STRING, json);
	}

	@Test
	public void shouldDeserialize() throws JsonParseException, JsonMappingException, IOException {
		DataTable dataTable = mapper.readValue(JSON_STRING, DataTable.class);
		assertEquals("30", dataTable.getGrandTotal().getStringCellValue());
		CellValue cellValue = dataTable.getTable().get("APAC", "columnKey");
		assertEquals("10", cellValue.getStringCellValue());
		assertEquals(CellType.CELLTYPE_STRING, cellValue.getCellType());
	}

	private ObjectMapper getMapper() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, true);
		mapper.registerModule(new GuavaModule());
		return mapper;
	}

	@Test
	public void shouldSerialiseDataAbstract() throws Exception {
		DataTable dataTable = getDataTable();
		// dataTable.setRowTotals(table.rowMap().get("rowKey"));
		// dataTable.setColumnTotals(table.rowMap().get("rowKey"));
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new GuavaModule());
		String json = mapper.writeValueAsString(dataTable);
		json = mapper.writeValueAsString(dataTable);
		DataTable dataTable2 = mapper.readValue(json, DataTable.class);
		Table<String, String, CellValue> before = dataTable.getTable();
		Set<String> beforeKey = before.rowKeySet();
		Table<String, String, CellValue> after = dataTable2.getTable();
		after = dataTable2.getTable();
		Set<String> afterKey = after.rowKeySet();
		assertEquals(afterKey, beforeKey);

	}

	private DataTable getDataTable() {
		DataTable dataTable = new DataTable();
		Table<String, String, CellValue> table = Tables.newCustomTable(new LinkedHashMap<>(), LinkedHashMap::new);
		CellValue cellValue = new CellValueDefault();
		cellValue.setRowKey("rowKey");
		cellValue.setColumnKey("columnKey");
		cellValue.setCellValue(BigDecimal.TEN);

		Collection<CellValue> values = table.values();
		for (CellValue value : values) {

		}
		// HashBasedTable table = HashBasedTable.create();

		List<String> rowIds = new ArrayList<String>();
		rowIds.add("APAC");
		rowIds.add("AACB");
		rowIds.add("AANL");
		for (String rowId : rowIds) {
			table.put(rowId, cellValue.getColumnKey(), cellValue);

		}
		dataTable.setTable(table);
		
		dataTable.setColumnTotals(dataTableService.getColumnTotal(table));
		dataTable.setRowTotals(dataTableService.getRowTotal(table));
		dataTable.setGrandTotal(dataTableService.calcTotal(dataTable.getRowTotals()));
		
		return dataTable;
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
