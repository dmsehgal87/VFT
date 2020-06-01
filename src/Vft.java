import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


public class Vft {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Starting the Verify Test run project!");
		try {
			File file1 = new File("resources/sanityCheck_1.html");
			File file2 = new File("resources/sanityCheck_1.html");

			Document doc1 = Jsoup.parse(file1, "UTF-8", "http://example.com/");
			Document doc2 = Jsoup.parse(file2, "UTF-8", "http://example.com/");
			
			System.out.println("fin.");
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
