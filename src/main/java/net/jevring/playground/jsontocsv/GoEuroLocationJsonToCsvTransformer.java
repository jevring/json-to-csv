package net.jevring.playground.jsontocsv;

import net.jevring.playground.jsontocsv.csv.CsvBuilder;
import net.jevring.playground.jsontocsv.csv.CsvRow;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;

/**
 * Transforms an array of JSON values into a CSV file.
 * The values and the resulting format is specified <a href="https://github.com/goeuro/dev-test">here</a>
 *
 * @author markus@jevring.net
 */
public class GoEuroLocationJsonToCsvTransformer implements JsonTransformer<CsvBuilder> {
	@Override
	public CsvBuilder transform(JsonArray array) {
		// in many places in Europe, the separator is ";"
		// in the US it's ","
		CsvBuilder csvBuilder = new CsvBuilder(",", CsvBuilder.LineBreak.Unix);
		for (JsonValue jsonValue : array) {
			CsvRow row = csvBuilder.newRow();
			if (jsonValue.getValueType() == JsonValue.ValueType.OBJECT) {
				JsonObject o = (JsonObject) jsonValue;
				row.add(o.getJsonNumber("_id").toString());
				row.add(o.getString("name"));
				row.add(o.getString("type"));
				JsonObject geoPosition = o.getJsonObject("geo_position");
				row.add(geoPosition.getJsonNumber("latitude").toString());
				row.add(geoPosition.getJsonNumber("longitude").toString());
			} else {
				throw new IllegalStateException("Expected a JSON object but got a JSON array");
			}
		}
		return csvBuilder;
	}
}
