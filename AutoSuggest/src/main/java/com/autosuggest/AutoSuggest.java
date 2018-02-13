package com.autosuggest;

import java.util.Collection;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AutoSuggest {
	final static String inputDirectoryPath = "/home/azharm/Documents/AutoSuggestInputDirectory";
	private static final Logger logger = Logger.getLogger(AutoSuggest.class.getName());

	public static void main(String[] args) {
		logger.log(Level.INFO, "Parsing the files and building a trie");
		Set<String> wordsSet = ForkJoinParseTextFiles.createDistinctKeyWordsSet(inputDirectoryPath);
		Trie trie = new Trie();
		createTrie(trie, wordsSet);
		logger.log(Level.INFO,"Trie create successfully");
		Scanner sc = new Scanner(System.in);
		logger.log(Level.INFO, "Please enter a keyword:");
		String string = sc.nextLine();
		Collection<String> col = trie.autoComplete(string);
		logger.log(Level.INFO, col.toString());
		sc.close();
	}

	public static void createTrie(Trie tr, Set<String> wordsSet) {
		for (String word : wordsSet) {
			tr.insert(word);
		}
	}

}
