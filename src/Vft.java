import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

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
			File file2 = new File("resources/sanityCheck_2.html");
			HashMap<String, String> newFailures = new HashMap<>();

			Document doc1 = Jsoup.parse(file1, "UTF-8", "http://example.com/");
			Document doc2 = Jsoup.parse(file2, "UTF-8", "http://example.com/");
			Vft v = new Vft();
			HashMap<String,Integer> failures_doc1 = v.findFailuresInDoc(doc1);
			HashMap<String,Integer> failures_doc2 = v.findFailuresInDoc(doc2);
			
			//Compare the failures
			for(Entry<String, Integer> e : failures_doc1.entrySet()){
				if(failures_doc2.get(e.getKey())!=null){
					int doc2_fail_val = failures_doc2.get(e.getKey());
					if(doc2_fail_val>e.getValue()){
						newFailures.put(e.getKey(), "Old Value:"+failures_doc2.get(e.getKey()+" New Value:"+e.getValue()));
					}
				}else{
					newFailures.put(e.getKey(), "Old Value:0"+" New Value:"+e.getValue());
				}
			}
			//print the values
			System.out.println("Total number of new failures:"+newFailures.size()+". Details below:");
			for(Entry<String,String> f : newFailures.entrySet()){
				System.out.println(f.getKey());
				System.out.println(f.getValue());
			}
			
			System.out.println("fin.");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public HashMap<String, Integer> findFailuresInDoc(Document doc1){
		HashMap<String,Integer> failures_doc = new HashMap<>();

		try{

			Elements resultList1= doc1.select("body > table:nth-child(4) > tbody > tr");
			//Elements resultElements = null; 
			String resultData="";
			int i=0,r;
			for(Element e : resultList1){
				if(i==0){i++;continue;}
				if(i==resultList1.size()-1){continue;}
				i++;
				Elements resultElements = 
						e.select("td:nth-child(4)");
				resultData = resultElements.get(0).text();
				r = Integer.parseInt(resultData);
				if(r>0){
					resultElements = e.getElementsByIndexEquals(0);
					resultData = resultElements.get(0).text();
//					System.out.println(resultData);
					failures_doc.put(resultData, r);
				}
			}

		}catch(Exception e){
			e.printStackTrace();
		}

		return failures_doc;


	}


}
