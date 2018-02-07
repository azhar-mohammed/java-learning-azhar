package com.wikigenerator;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WikiGenerator {
	private static final String inputTextFilePath="/home/azharm/git/java-learning-azhar/WikiGenerator/src/main/resources/Multithreading_Task2_ProgrammingLanguages.txt";

	public static void main(String[] args) {
		
		TextFileParser parser = new TextFileParser();
		
		try {
			List<String> wordsList=parser.parseFile(inputTextFilePath);
			ExecutorService executor =Executors.newFixedThreadPool(10);
			for(String word:wordsList)
			{
				System.out.println("word is "+word);
				WikiThread wikiThread = new WikiThread(word);
				executor.execute(wikiThread);
			}
		} catch (IOException e) {
			// TODO Add logging mechanism
			e.printStackTrace();
		}
		
		

	}

}
