package com.wordcount;

import static java.nio.file.Files.readAllLines;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.concurrent.RecursiveTask;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WordCountTask extends RecursiveTask<Map<String, Long>> {

	private static final long serialVersionUID = -810634633659131063L;
	private Path filePath;
    private static final Logger logger = Logger.getLogger(WordCountTask.class.getName());

    WordCountTask(Path filePath) {
        this.filePath = filePath;
    }

    /**
     * Simple program to compute the word count for a FILE
     * @return
     */
    @Override
    protected Map<String, Long> compute() {
        try {
            List<String> strings = readAllLines(filePath, Charset.defaultCharset());
            Map<String, Long> wordCount = new HashMap<>();
            for(String line : strings) {
                StringTokenizer tk = new StringTokenizer(line);
                while(tk.hasMoreTokens()) {
                    String token = tk.nextToken();
                    Long count = wordCount.get(token);
                    count = count == null? 0 : count;
                    wordCount.put(token, count+1);
                }
            }
            logger.info("File [" + filePath + "] has word count [" + wordCount.size() + "]");
            return wordCount;
        } catch (IOException e) {
            logger.log(Level.SEVERE, "IO Exception occured", e);
            return Collections.emptyMap();
        }
    }


}
