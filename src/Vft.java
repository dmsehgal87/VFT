import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


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
			
			Elements resultList1= doc1.select("body > table:nth-child(4) > tbody > tr");
			
			Elements resultElements = null;
			String resultData="";
			int i=0,r;
			for(Element e : resultList1){
				if(i==0){i++;continue;}
				if(i==resultList1.size()){continue;}
				resultElements = e.getElementsByIndexEquals(3);
						//select("td:nth-child(4)");
				resultData = resultElements.get(0).text();
				r = Integer.parseInt(resultData);
				if(r>0){
					resultElements = e.getElementsByIndexEquals(0);
					resultData = resultElements.get(0).text();
					System.out.println(resultData);
				}
			}
			
			System.out.println("fin.");
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
