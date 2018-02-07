package com.wikigenerator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CsvFileParser implements ParserInterface {

	@Override
	public List<String> parseFile(String filePath) throws IOException {
		File file = new File(filePath);
		List<String> wordsList = new ArrayList<String>();
		Set<String> uniqueWordsSet = Files.readAllLines(file.toPath())
				.stream()
				.flatMap(word->Stream.of(word.split(",")))
				.map(word->word.trim())
				.filter(word->word.length()>2)
				.collect(Collectors.toSet());
		wordsList.addAll(uniqueWordsSet);
			
		return wordsList;
	}

}
