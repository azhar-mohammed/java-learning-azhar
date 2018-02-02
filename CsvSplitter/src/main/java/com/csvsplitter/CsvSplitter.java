package com.csvsplitter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

/**
 * 
 * @author azharm
 *
 */
public class CsvSplitter {

	final static String inputFilePath = "/home/azharm/git/java-learning-azhar/CsvSplitter/src/main/resources/Multithreading_Task1_Books.csv";
	final static String outputDirectoryPath = "/home/azharm/Documents/OutputDirectory/";

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();

		File file = new File(inputFilePath);
		try {
			List<String> linesList = Files.readAllLines(file.toPath());
			CsvSplitterRecursiveAction csvAction = new CsvSplitterRecursiveAction(outputDirectoryPath, linesList);
			ForkJoinPool forkJoinPool = new ForkJoinPool();
			forkJoinPool.invoke(csvAction);
		} catch (IOException e) {

			e.printStackTrace();
		}

		long endTime = System.currentTimeMillis();

		System.out.println("Processing time in milli sec : " + (endTime - startTime));
	}

}
