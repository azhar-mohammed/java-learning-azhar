package com.wordcount;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WordCount {

	final static String inputDirectoryPath = "/home/azharm/Documents/WordCountInputDirectory";
	private static final Logger logger = Logger.getLogger(WordCount.class.getName());

	public static void main(String[] args) {

		try {
			Map<String, Long> wordCountMap = ForkJoinWordCount.wordCount(inputDirectoryPath);
			for (Map.Entry<String, Long> entry : wordCountMap.entrySet()) {
				logger.info(entry.getKey() + " : " + entry.getValue());
			}

		} catch (IOException e) {
			logger.log(Level.SEVERE, "IO Exception occured", e);
		}

	}

}
