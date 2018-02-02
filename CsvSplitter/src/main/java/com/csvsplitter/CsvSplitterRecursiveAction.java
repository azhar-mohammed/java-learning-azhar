package com.csvsplitter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.ThreadLocalRandom;

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

	public CsvSplitterRecursiveAction(final String outputDirectory, final List<String> fileLinesList) {
		super();
		this.outputDirectory = outputDirectory;
		this.linesList = fileLinesList;
		this.noOfLines = linesList.size();
	}

	@Override
	protected void compute() {
		
		if(noOfLines <= threshold)
		{
			writeToFile(linesList);
		}
		else
		{
			RecursiveAction first = new CsvSplitterRecursiveAction(outputDirectory,linesList.subList(0,linesList.size()/2));
			RecursiveAction second = new CsvSplitterRecursiveAction(outputDirectory,linesList.subList(linesList.size()/2,linesList.size())); 
			invokeAll(first,second);
		}

	}

	private void writeToFile(List<String> lines) {

		try (FileWriter fw = new FileWriter(outputDirectory + ThreadLocalRandom.current().nextLong() + ".txt")) {
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
