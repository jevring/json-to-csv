package net.jevring.playground.jsontocsv;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * Reads JSON sources and allows transformers to be used on the result.
 *
 * @author markus@jevring.net
 */
public class JsonTransformerStructure {
	private final JsonArray jsonArray;

	public JsonTransformerStructure(URL url) throws IOException {
		try (InputStream is = url.openStream()) {
			jsonArray = readArray(is);
		}
	}

	public JsonTransformerStructure(InputStream is) throws IOException {
		jsonArray = readArray(is);
	}

	private JsonArray readArray(InputStream is) {
		JsonReader jsonReader = Json.createReader(new InputStreamReader(is, Charset.forName("UTF-8")));
		return jsonReader.readArray();
	}

	public <T> T transform(JsonTransformer<T> transformer) {
		return transformer.transform(jsonArray);
	}
}
