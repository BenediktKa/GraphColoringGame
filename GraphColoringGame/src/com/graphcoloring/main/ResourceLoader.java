package com.graphcoloring.main;

import java.io.InputStream;

// TODO: Auto-generated Javadoc
/**
 * The Class ResourceLoader.
 */
final public class ResourceLoader {
	
	/**
	 * Load.
	 *
	 * @param path the path
	 * @return the input stream
	 */
	public static InputStream load(String path) {
		InputStream input = ResourceLoader.class.getResourceAsStream(path);
		if (input == null) {
			input = ResourceLoader.class.getResourceAsStream("/" + path);
		}
		return input;
	}
}
