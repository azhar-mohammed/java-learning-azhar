package com.autosuggest;

import static java.nio.file.Files.newDirectoryStream;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class ForkJoinParseTextFiles extends RecursiveTask<Set<String>> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5316338989358807692L;
	private String directoryPath;

	public ForkJoinParseTextFiles(String inptDirectPath) {
		this.directoryPath = inptDirectPath;
	}

	public static Set<String> createDistinctKeyWordsSet(String inptDirectPath) {
		ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
		/** Each processor could potentially do one task */
		return pool.submit(new ForkJoinParseTextFiles(inptDirectPath)).join();

	}

	public static void mergeSets(Set<String> set1, Set<String> set2) {

		set1.addAll(set2);
	}

	@Override
	protected Set<String> compute() {

		List<ForkJoinTask<Set<String>>> tasks = new ArrayList<>();
		File file = new File(directoryPath);
		try (DirectoryStream<Path> ds = newDirectoryStream(file.toPath())) {
			for (Path p : ds) {
				tasks.add(new TextParsingTask(p).fork());
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		Set<String> distinctWords = new HashSet<>();
		for (ForkJoinTask<Set<String>> t : tasks) {
			mergeSets(distinctWords, t.join());
		}

		return distinctWords;
	}

}
