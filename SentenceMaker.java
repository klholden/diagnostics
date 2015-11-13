package diagnostics;

import java.util.Random;

/**
 * Class to create sentences from the TextDiagnostics stats.
 * @author Krista
 *
 */
public class SentenceMaker {
	public int MAX_RAND_LENGTH = 15;
	public int MIN_RAND_LENGTH = 5;
	
	private TextDiagnostics stats;
	private Random rand;
	
	public SentenceMaker(TextDiagnostics stats) {
		this.stats = stats;
		this.rand = new Random();
	}
	
	public String generatePhrase(String start, int length) {
		String out = start;
		String currWord = start;
		for (int i = 1; i < length; i++) {
			currWord = getRandomFollower(currWord);
			out += " " + currWord;
		}
		return out;
	}
	
	public String generatePhrase(int length) {
		return generatePhrase(pickRandom(stats.getWordCount()), length);		
	}
	
	public String generatePhrase() {
		return generatePhrase(rand.nextInt(MAX_RAND_LENGTH - MIN_RAND_LENGTH) + MIN_RAND_LENGTH);
	}
	
	public String getRandomFollower(String word) {
		return pickRandom(stats.getFollowers(word));
	}
	
	private String pickRandom(WordCount counts) {
		int choice = rand.nextInt(counts.getTotalCount());
		for (String follow : counts.getOrderedWords()) {
			int currentVal = counts.get(follow);
			choice -= currentVal;
			if (choice <= 0) {
				return follow;
			}
		}
		return "";
	}
}
