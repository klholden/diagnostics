package diagnostics;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * A count of words.
 * @author Krista
 *
 */
public class WordCount {
	private Map<String, Integer> counts;
	private int totalCount;
	
	public WordCount() {
		counts = new HashMap<String, Integer>();
		totalCount = 0;
	}
	
	public void add(String word) {
		if (!counts.containsKey(word)) {
			counts.put(word, 0);
		}
		counts.put(word, counts.get(word) + 1);
		totalCount++;
	}
	
	public int get(String word) {
		return counts.get(word);
	}
	
	public int getTotalCount() {
		return totalCount;
	}
	
	public Map<String, Integer> getCounts() {
		return counts;
	}
	
	public Set<String> getWords() {
		return counts.keySet();
	}
	
	public PriorityQueue<String> getOrderedWords() {
		PriorityQueue<String> ordered = new PriorityQueue<String>();
		for (String word: counts.keySet()) {
			ordered.add(word);
		}
		return ordered;
	}
}
