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

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.google.common.collect.Table;
import com.google.common.collect.Tables;
import com.maxcheung.models.CellValue;
import com.maxcheung.models.CellValueDefault;
import com.maxcheung.service.DataTableService;
import com.maxcheung.service.DataTableServiceImpl;

public class GuavaDataTableTest {

    private final static String JSON_STRING = "{\"guavaCustomTable\":{\"table\":{\"AXEL\":{\"columnKey\":{\"value\":\"10\"}},\"ALPHA\":{\"columnKey\":{\"value\":\"10\"}},\"ACME\":{\"columnKey\":{\"value\":\"10\"}}}},\"rowTotals\":{\"AXEL\":{\"value\":\"10\"},\"ACME\":{\"value\":\"10\"},\"ALPHA\":{\"value\":\"10\"}},\"columnTotals\":{\"columnKey\":{\"value\":\"30\"}},\"grandTotal\":{\"value\":\"30\"}}";

    private ObjectMapper mapper;

	private DataTableService dataTableService;

	@Before
	public void setUp() {
		mapper = new ObjectMapper();
		mapper.registerModule(new GuavaModule());
		dataTableService = new DataTableServiceImpl();
	}

	
    @Test
    public void shouldSerialize() throws JsonProcessingException {
    	GuavaDataTable guavaDataTable = getGuavaDataTable();
        String json = this.mapper.writeValueAsString(guavaDataTable);
        assertEquals(JSON_STRING, json);
    }

    @Test
    public void shouldDeserialize() throws JsonParseException, JsonMappingException, IOException  {
    	GuavaDataTable guavaDataTable = mapper.readValue(JSON_STRING, GuavaDataTable.class);
        assertEquals("30", guavaDataTable.getGrandTotal().getStringCellValue());
    }

	
	@Test
	public void shouldSerialiseWitCustomDeserialiser() throws Exception {
		GuavaDataTable dataTable = getGuavaDataTable();
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


	private GuavaDataTable getGuavaDataTable() {
		GuavaDataTable dataTable = new GuavaDataTable();
		Table<String, String, CellValue> table = Tables.newCustomTable(new LinkedHashMap<>(), LinkedHashMap::new);
		CellValue cellValue = new CellValueDefault();
		cellValue.setRowKey("rowKey");
		cellValue.setColumnKey("columnKey");
		cellValue.setCellValue(BigDecimal.TEN);
		List<String> rowIds = new ArrayList<String>();
		rowIds.add("AXEL");
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
