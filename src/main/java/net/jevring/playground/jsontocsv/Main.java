package net.jevring.playground.jsontocsv;

import net.jevring.playground.jsontocsv.csv.CsvBuilder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Launcher and clue class.
 *
 * @author markus@jevring.net
 */
public class Main {
	public static final String URL_PREFIX = "http://api.goeuro.com/api/v2/position/suggest/en/";
	private final String city;

	public Main(String city) {
		this.city = city;
	}

	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Please provide the name of a city");
		} else {
			String city = args[0];
			Main main = new Main(city);
			main.readAndWrite();
		}
	}

	private void readAndWrite() {
		try {
			URL url = generateUrl();
			JsonTransformerStructure jsonTransformerStructure = new JsonTransformerStructure(url);
			CsvBuilder csvBuilder = jsonTransformerStructure.transform(new GoEuroLocationJsonToCsvTransformer());
			String csvAsString = csvBuilder.build();
			File outputFile = new File("output.csv");
			try (FileOutputStream fos = new FileOutputStream(outputFile)) {
				fos.write(csvAsString.getBytes());
			}
		} catch (IOException e) {
			System.err.println("Could not read city information for city: " + city);
			e.printStackTrace();
		}
	}

	private URL generateUrl() throws MalformedURLException, UnsupportedEncodingException {
		return new URL(URL_PREFIX + URLEncoder.encode(city, "UTF-8"));
	}
}
