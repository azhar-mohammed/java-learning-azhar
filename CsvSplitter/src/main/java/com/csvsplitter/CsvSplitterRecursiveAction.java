package com.csvsplitter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 
 * @author azharm
 *
 */
public class CsvSplitterRecursiveAction extends RecursiveAction {

	private static final long serialVersionUID = -2566991059506418202L;
	private static final int threshold = 1000;
	private String outputDirectory;
	private List<String> linesList;
	private int noOfLines;
	private static final AtomicInteger atomicInt = new AtomicInteger();

	public CsvSplitterRecursiveAction(final String outputDirectory, final List<String> fileLinesList) {
		super();
		this.outputDirectory = outputDirectory;
		this.linesList = fileLinesList;
		this.noOfLines = linesList.size();
	}
/**
 * The main recursive computation performed by this task. Here if the no of lines is less than
 * threshold than we write the content to a file ,else we go ahead with a divide and conquer approach and 
 * we try to recursively call this method.
 */
	@Override
	protected void compute() {
		
		if(noOfLines <= threshold)
		{
			writeToFile(linesList);
		}
		else
		{
			RecursiveAction first = new CsvSplitterRecursiveAction(outputDirectory,linesList.subList(0,threshold));
			RecursiveAction second = new CsvSplitterRecursiveAction(outputDirectory,linesList.subList(threshold,linesList.size())); 
			invokeAll(first,second);
		}

	}
/**
 *  @param lines
 *  This method writes the provided input lines to an output csv file and increments the atomic integer value
 */
	private void writeToFile(List<String> lines) {

		try (FileWriter fw = new FileWriter(outputDirectory + atomicInt.getAndIncrement() + ".txt")) {
			for (String s : lines) {
				fw.write(s);
				fw.write("\n");

			}
		}

		catch (IOException e) {
			e.printStackTrace();
		}
	}

}
