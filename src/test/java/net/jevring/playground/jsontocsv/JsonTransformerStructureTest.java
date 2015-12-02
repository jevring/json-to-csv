package net.jevring.playground.jsontocsv;

import net.jevring.playground.jsontocsv.csv.CsvBuilder;
import org.junit.Test;

import java.net.URL;

import static org.junit.Assert.assertEquals;

/**
 * Tests the json reader.
 *
 * @author markus@jevring.net
 */
public class JsonTransformerStructureTest {
	private static final String expectedBerlinCsv = "" +
			"\"376217\",\"Berlin\",\"location\",\"52.52437\",\"13.41053\"\n" +
			"\"448103\",\"Berlingo\",\"location\",\"45.50298\",\"10.04366\"\n" +
			"\"425332\",\"Berlingerode\",\"location\",\"51.45775\",\"10.2384\"\n" +
			"\"425326\",\"Bernau bei Berlin\",\"location\",\"52.67982\",\"13.58708\"\n" +
			"\"314826\",\"Berlin Tegel\",\"airport\",\"52.5548\",\"13.28903\"\n" +
			"\"314827\",\"Berlin Schönefeld\",\"airport\",\"52.3887261\",\"13.5180874\"\n" +
			"\"334196\",\"Berlin Hbf\",\"station\",\"52.525589\",\"13.369548\"\n" +
			"\"333977\",\"Berlin Ostbahnhof\",\"station\",\"52.510972\",\"13.434567\"";
	private static final String expectedEmptyCsv = "";
	
	@Test
	public void testBerlin() throws Exception {
		URL resource = getClass().getResource("/berlin.json");
		JsonTransformerStructure jsonTransformerStructure = new JsonTransformerStructure(resource);
		CsvBuilder csvBuilder = jsonTransformerStructure.transform(new GoEuroLocationJsonToCsvTransformer());
		String csvAsString = csvBuilder.build();
		assertEquals(csvAsString, expectedBerlinCsv);
	}

	@Test
	public void testEmpty() throws Exception {
		URL resource = getClass().getResource("/empty.json");
		JsonTransformerStructure jsonTransformerStructure = new JsonTransformerStructure(resource);
		CsvBuilder csvBuilder = jsonTransformerStructure.transform(new GoEuroLocationJsonToCsvTransformer());
		String csvAsString = csvBuilder.build();
		assertEquals(csvAsString, expectedEmptyCsv);

	}
}