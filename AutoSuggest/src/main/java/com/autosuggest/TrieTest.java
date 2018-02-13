package com.autosuggest;

public class TrieTest {
	
	public static void main(String args[])
	{
		Trie trie = new Trie();
		trie.insert("az");
		trie.insert("azh");
		trie.insert("azhar");
		trie.insert("maz");
		trie.insert("mazh");
		trie.insert("mazhar");
		
	   System.out.println(trie.autoComplete("az"));
	   System.out.println(trie.autoComplete("mazh"));
	}

}
