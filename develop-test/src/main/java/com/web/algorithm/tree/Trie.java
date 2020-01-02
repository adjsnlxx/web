package com.web.algorithm.tree;

/**
 * Trie 字典树
 */
public class Trie {

	private TrieNode root = new TrieNode('/');

	public Trie() {

	}

	public void insert(char[] text) {
		TrieNode node = root;
		int index = 0;
		for (int i = 0; i < text.length; i++) {
			index = text[i] - 'a';

			if (node.children[index] == null) {
				TrieNode temp = new TrieNode(text[i]);
				node.children[index] = temp;
			}

			node = node.children[index];
		}

		node.isEndChar = true;
	}

	public boolean find(char[] text) {
		TrieNode node = root;

		int index = 0;
		for (int i = 0; i < text.length; i++) {
			index = text[i] - 'a';

			if (node.children[index] == null) {
				return false;
			}

			node = node.children[index];
		}

		if (node.isEndChar) {
			return true;
		} else {
			return false;
		}
	}

	class TrieNode {
		public char data;
		public TrieNode[] children;
		public boolean isEndChar;

		public TrieNode(char data) {
			this.data = data;
			children = new TrieNode[26];
		}
	}

	public static void main(String[] args) {
		char[] text = "hello".toCharArray();
		Trie trie = new Trie();
		trie.insert(text);

		System.out.println(trie.find("hell".toCharArray()));
	}
}
