package diagnostics;

import java.io.FileNotFoundException;

/**
 * Main class to run text analysis.
 * @author Krista
 *
 */
public class TextAnalyzer {
	public static void main(String[] args) {
		String test = "C:/Users/Krista/workspace/wordDiagnostics/diagnostics/src/diagnostics/test.txt";
		try {
			TextDiagnostics diagnostics = new TextDiagnostics(test);
			SentenceMaker creator = new SentenceMaker(diagnostics);
			for (int i = 0; i < 5; i++) {
				System.out.println(creator.generatePhrase());
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("File not found: " + test);
			System.out.println("Working Directory = " + System.getProperty("user.dir"));
			e.printStackTrace();
		}
		
	}
	
	
}
