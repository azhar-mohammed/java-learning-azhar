package com.wordcount;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

import static java.nio.file.Files.newDirectoryStream;

/**
 * 
 * @author azharm
 *
 */
public class ForkJoinWordCount extends RecursiveTask<Map<String, Long>> {

	private static final long serialVersionUID = -7609809037445674241L;
	private String folderPath;

	public ForkJoinWordCount(String folderPath) {
		this.folderPath = folderPath;
	}

	/**
	 * Creates several forked tasks to compute the word counts under the folder then
	 * merges them
	 * 
	 * @param folderName
	 * @return
	 * @throws IOException
	 */
	public static Map<String, Long> wordCount(String folderName) throws IOException {
		ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
		/** Each processor could potentially do one task */
		return pool.submit(new ForkJoinWordCount(folderName)).join();
	}

	/**
	 * Merges counts of two maps
	 * 
	 * @param map1
	 * @param map2
	 */
	protected void mergeCounts(Map<String, Long> map1, Map<String, Long> map2) {
		for (Map.Entry<String, Long> entry : map2.entrySet()) {
			Long count = map1.get(entry.getKey());
			count = count == null ? 0 : count;
			map1.put(entry.getKey(), count + entry.getValue());
		}
	}

	/** Fork several jobs one per file */
	@Override
	protected Map<String, Long> compute() {
		List<ForkJoinTask<Map<String, Long>>> tasks = new ArrayList<>();
		File file = new File(folderPath);
		try (DirectoryStream<Path> ds = newDirectoryStream(file.toPath())) {
			for (Path p : ds) {
				tasks.add(new WordCountTask(p).fork());
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		Map<String, Long> totalCount = new HashMap<>();
		for (ForkJoinTask<Map<String, Long>> t : tasks) {
			mergeCounts(totalCount, t.join());
		}
		return totalCount;
	}
}