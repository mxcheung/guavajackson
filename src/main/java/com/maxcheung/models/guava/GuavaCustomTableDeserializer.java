package com.maxcheung.models.guava;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Table;
import com.google.common.collect.Tables;
import com.maxcheung.models.CellValue;

public class GuavaCustomTableDeserializer extends JsonDeserializer<GuavaCustomTable> {

	@Override
	public GuavaCustomTable deserialize(JsonParser p, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		GuavaCustomTable guavaCustomTable = new GuavaCustomTable();
		Map<String, Map<String, CellValue>> map = this.deserialize(p, ctxt, new HashMap<>());
		guavaCustomTable.setTable(tableFromMap(map));
		return guavaCustomTable;
	}

	public Map<String, Map<String, CellValue>> deserialize(JsonParser p, DeserializationContext ctxt,
			Map<String, Map<String, CellValue>> intoValue) throws IOException, JsonProcessingException {
		JsonNode node = p.readValueAsTree();
		String json = node.get("table").toString();
		ObjectMapper mapper = new ObjectMapper();
		intoValue = mapper.readValue(json, new TypeReference<Map<String, Map<String, CellValue>>>(){});
		return intoValue;
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
