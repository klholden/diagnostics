package diagnostics;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * A set of stats based on a text file.
 * @author Krista
 *
 */
public class TextDiagnostics {
	private WordCount wordCount;
	private Map<String, WordCount> followingWord;
	
	public TextDiagnostics(String fileName) throws FileNotFoundException {
		wordCount = new WordCount();
		followingWord = new HashMap<String, WordCount>();
		String prevWord = "";
		
		Scanner scan = new Scanner(new File(fileName));
		while (scan.hasNext()) {
			String next = scan.next();			
			String[] wordList = removeNoise(next.split(" "));
			
			for (String word : wordList) {
				word = word.trim();
				if (!word.equals(" ") && !word.equals("\n")) {
					wordCount.add(word);
				
					WordCount otherFollow;
					if (followingWord.containsKey(prevWord)) {
						otherFollow = followingWord.get(prevWord);
					} else {
						otherFollow = new WordCount();
					}
					otherFollow.add(word);
					followingWord.put(prevWord, otherFollow);
				
					prevWord = word;
				}
			}
		}
		scan.close();
	}
	
	private String[] removeNoise(String[] list) {
		for (int i = 0; i < list.length; i++) {
			list[i] = list[i].replaceAll("[^A-Za-z' ]+|(?<=^|\\W)'|'(?=\\W|$)", "")
				    .replaceAll(" +", " ").trim().toLowerCase();
		}
		return list;
	}
	
	public WordCount getWordCount() {
		return wordCount;
	}
	
	public int getWordCount(String word) {
		return wordCount.get(word);
	}
	
	public Map<String, WordCount> getFollowers() {
		return followingWord;
	}
	
	public WordCount getFollowers(String word) {
		return getFollowers().get(word);
	}
	
	public int getCountOfFollows(String first, String second) {
		return getFollowers(first).get(second);
	}
	
	public double getFollowProbability(String first, String second) {
		WordCount followers = getFollowers(first);
		return (double) followers.get(second) / (double) followers.getTotalCount();
	}
	
	public String toString() {
		String out = "";
		for (String word: wordCount.getOrderedWords()) {
			out += word + "(" + wordCount.get(word) + ") => ";
			if (followingWord.containsKey(word)) {
				WordCount followCount = followingWord.get(word);
				for (String followWord: followCount.getOrderedWords()) {
					out += followWord + "(" + followCount.get(followWord) + "), ";
				}
			}
			out += "\n";
		}
		return out;
	}
}
