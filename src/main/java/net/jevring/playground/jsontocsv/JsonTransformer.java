package net.jevring.playground.jsontocsv;

import javax.json.JsonArray;

/**
 * Transforms {@link JsonArray} instances.
 *
 * @author markus@jevring.net
 */
public interface JsonTransformer<T> {
	public T transform(JsonArray array);
}
