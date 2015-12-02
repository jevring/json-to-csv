package net.jevring.playground.jsontocsv.csv;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a CSV file.
 *
 * @author markus@jevring.net
 */
public class CsvBuilder {
	private final List<CsvRow> rows = new ArrayList<>();
	private final String separator;
	private final LineBreak lineBreak;

	/**
	 * Creates a CSV file builder.
	 *
	 * @param separator the separator to use
	 * @param lineBreak the line break style to use
	 */
	public CsvBuilder(String separator, LineBreak lineBreak) {
		this.separator = separator;
		this.lineBreak = lineBreak;
	}

	/**
	 * Creates a new row of values.
	 *
	 * @return the row on which {@link CsvRow#add(Object)} can be called to add values
	 */
	public CsvRow newRow() {
		CsvRow row = new CsvRow(separator);
		rows.add(row);
		return row;
	}

	/**
	 * Generates the string representation of this file.
	 *
	 * @return the string representation ready to be written to file
	 */
	public String build() {
		return rows.stream().map(CsvRow::toLine).collect(Collectors.joining(lineBreak.getChars()));
	}

	/**
	 * Line break styles.
	 */
	public enum LineBreak {
		Unix("\n"), Windows("\r\n");
		private final String chars;

		LineBreak(String chars) {
			this.chars = chars;
		}

		public String getChars() {
			return chars;
		}
	}
}
