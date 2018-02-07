package com.wikigenerator;

import java.io.IOException;
import java.util.List;

public interface ParserInterface {
	
	public List<String> parseFile(String filePath) throws IOException;
}
