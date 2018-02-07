package com.wikigenerator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TextFileParser implements ParserInterface {

	public List<String> parseFile(String filePath) throws IOException {

		File file = new File(filePath);
		List<String> wordsList = new ArrayList<String>();
		Set<String> uniqueWordsSet = Files.readAllLines(file.toPath())
				.stream()
				.filter(word -> word.length()>0)
				.filter(word->!word.contains("}"))
				.collect(Collectors.toSet());
		wordsList.addAll(uniqueWordsSet);
		return wordsList;
		
	}

}
