package com.autosuggest;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.RecursiveTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TextParsingTask extends RecursiveTask<Set<String>> {

	private static final long serialVersionUID = 6192399251556483953L;
	private Path filePath;
	private static final Logger logger = Logger.getLogger(TextParsingTask.class.getName());

	public TextParsingTask(Path p) {
		this.filePath = p;
	}

	@Override
	protected Set<String> compute() {
		// TODO Auto-generated method stub
		Set<String> uniqueWordsSet;
		try {
			uniqueWordsSet = Files.readAllLines(filePath, Charset.defaultCharset()).stream()
					.flatMap(line -> Stream.of(line.split("\\s+"))).filter(word->word.length()>0)
					.collect(Collectors.toSet());
		} catch (IOException e) {
			logger.log(Level.SEVERE, "IO Exception occured", e);
			return Collections.emptySet();
		}
		// logger.log(Level.INFO,"keywords are "+uniqueWordsSet);
		return uniqueWordsSet;
	}
}
