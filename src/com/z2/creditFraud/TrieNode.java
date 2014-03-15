package com.z2.creditFraud;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public final class TrieNode<T> {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Builder<String> myBuilder = builder();
		myBuilder.put("111122234455", "1");
		myBuilder.put("1234", "2");
		myBuilder.put("22334455", "3");
		myBuilder.put("11111111", "4");
		myBuilder.put("234567", "5");
		TrieNode<String> trie = myBuilder.build();
		System.out.println(trie.get("23456334", 0, 8));
		System.out.println(trie.get("234567", 0, 6));
		
	}

	//start is the start char of the character set.
	//not the same for different TrieNode.
	private final char start;
	private TrieNode<T>[] children;
	public final T value;
	//value stored at the end of the TrieNode.
	//can be null


	private TrieNode(char start, TrieNode<T>[] arr, T value) {
		this.start = start;
		this.children = arr;
		this.value = value;
	}

	public TrieNode<T> get(char ch) {
		int i = ch - start;
		return 0 <= i && i <= children.length ? children[i] : null;
	}

	public TrieNode<T> getIgnoreCase(char ch) {
		int i = Character.toLowerCase(ch) - start;
		return 0 <= i && i <= children.length ? children[i] : null;
	}

	/**
	 * get value corresponding to s[off:end]
	 * trie includes part of the substring.
	 * 
	 * @param s
	 * @param off offset from start of string.
	 * @param end end of the string.
	 * @return return the t value stored in trie node.
	 */
	public T get(String s, int off, int end) {
		TrieNode<T> t = this;
		while (off < end) {
			t = t.get(s.charAt(off++));
			if (t == null) {
				return null;
			}
		}
		return t.value;
	}

	T get(char[] s, int off, int end) {
		TrieNode<T> t = this;
		while (off < end) {
			t = t.get(s[off++]);
			if (t == null) {
				return null;
			}
		}
		return t.value;
	}

	T getIgnoreCase(String s, int off, int end) {
		TrieNode<T> t = this;
		while (off < end) {
			t = t.getIgnoreCase(s.charAt(off++));
			if (t == null) {
				return null;
			}
		}
		return t.value;
	}

	T getIgnoreCase(char[] s, int off, int end) {
		TrieNode<T> t = this;
		while (off < end) {
			t = t.getIgnoreCase(s[off++]);
			if (t == null) {
				return null;
			}
		}
		return t.value;
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		toString(sb, 0);
		return sb.toString();
	}

	//depth formatter for padding space.
	private void toString(StringBuilder sb, int depth) {
		sb.append(value).append(" {");
		++depth;
		for(int i=0; i<children.length; ++i){
			if(children[i] == null) continue;
			sb.append('\n');
			for(int j= depth; --j >=0; ){
				sb.append("  ");
			}
			sb.append('\'').append((char) (i + start)).append("':");
			children[i].toString(sb, depth);
		}
		sb.append("}");
	}
	
	static final class Builder<S>{//builder of the builders with current char.
		//current value and list of sub builder.
		S value;
		final char ch;
		final List<Builder<S>> children = new ArrayList<Builder<S>>();
		
		Builder(S value, char ch){
			this.value = value;
			this.ch = ch;
		}
		
		Builder<S> put(String s, S newValue){
			put(this, s, newValue);
			return this;
		}
		
		static <S> void put(Builder<S> b, String s, S newValue){
			for(int i = 0, n= s.length(); i< n; i++){
				//for each char in string.
				char ch = s.charAt(i);
				Builder<S> next = null;
				//find the next builder
				for(Builder<S> child: b.children){
					if(child.ch == ch){
						next = child;
						break;
					}
				}
				if(next == null){
					next = new Builder<S>(null, ch);
					b.children.add(next);
				}
				b = next;
				
			}
			b.value = newValue;
		}
		
		/**
		 * recursively build the Trie.
		 * @return
		 */
		TrieNode<S> build(){
			TrieNode<S>[] arr;
			int n = children.size();
			char min = 0;
			if( n == 0){
				arr = (TrieNode<S>[]) NO_TRIES;
			}else{
				//sort the children builders.
				Collections.sort(children, new Comparator<Builder<?>>(){
					public int compare(Builder<?> a, Builder<?> b){
						return a.ch - b.ch;
					}
				});
				
				min = children.get(0).ch;
				int max = children.get(n-1).ch;
				arr = (TrieNode<S>[]) new TrieNode<?>[max-min+1]; //range of the trieNode array.
				//doesn't have to get value for each ch.
				
				for(int i=0; i< n; ++i){
					Builder<S> b = children.get(i);
					arr[b.ch - min] = b.build();
				}
			}
			
			return new TrieNode<S> (min, arr, value);
		}
	}

	private static final TrieNode<?>[] NO_TRIES = new TrieNode<?>[0];
	
	static <S> Builder<S> builder(){
		return new Builder<S>(null, (char)0);
	}
}
