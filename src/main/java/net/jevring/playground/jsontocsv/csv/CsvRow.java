package net.jevring.playground.jsontocsv.csv;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a row in a CSV file.
 *
 * @author markus@jevring.net
 */
public class CsvRow {
	private final List<String> values = new ArrayList<>();
	private final String separator;

	public CsvRow(String separator) {
		this.separator = separator;
	}

	/**
	 * Adds a value to the row.
	 *
	 * @param value the value to be added
	 */
	public void add(Object value) {
		values.add("\"" + String.valueOf(value) + "\"");
	}

	/**
	 * Creates a line out of the values. The values will be escaped with quotation marks
	 *
	 * @return a line of csv values
	 */
	String toLine() {
		return values.stream().collect(Collectors.joining(separator));
	}
}
