import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.JSOUP;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HTMLLinkUtil {
	public static class HTMLLinksParser{
		public static List<String> getHTMLLinks(String site) throws IOException{
			List<String> sites = new ArrayList<>();
			Document doc = Jsoup.connect(site).get();
			
			 Elements links = doc.select("a[href]");
			 for (Element link : links) {
				 if((link.attr("abs:href")!=null) && link.attr("abs:href").length()>4){
					 if(link.attr("abs:href").substring(link.attr("abs:href").length()-4,link.attr("abs:href").length()).equals("view")){
						 if(!sites.contains(link.attr("abs:href")))
							 sites.add(link.attr("abs:href"));
					      	//System.out.println(link.attr("abs:href"));
					 }
				 }			      
			    }			 
			return sites;			
		}
	}
}
