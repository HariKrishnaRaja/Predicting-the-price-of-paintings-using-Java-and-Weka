import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HTMLParser {
	private static List<String> sites;
	static List<PaintingAttribute> attributes;
	private static final String FILENAME = "output.csv";
	private static final int PAGES = 25;
	private HTMLParser() {}

	public static List<String>extractLinks(String url) throws IOException {
		final ArrayList<String> result = new ArrayList<String>();
		final ArrayList<String> result1 = new ArrayList<String>();

		Document doc = Jsoup.connect(url).get();

		Elements links = doc.select("a[href]");
		//Elements media = doc.select("[src]");
		Elements imports = doc.select("link[href]");
		Elements metaTags = doc.getElementsByTag("meta");

		for(Element metaTag : metaTags){
			String content = metaTag.attr("content");
			String property = metaTag.attr("property");
			result.add(property+" "+content);
			System.out.println(property+" "+content);
			result1.add(property);
		}

		
    // href ...
    for (Element link : links) {
      result.add(link.attr("abs:href"));
      System.out.println(link.attr("abs:href"));
    }

    /*
    // img ...
    for (Element src : media) {
      result.add(src.attr("abs:src"));
    }
		 */
		// js, css, ...
		for (Element link : imports) {
			result.add(link.attr("abs:href"));
		}
		return result;
	}


	public final static void main(String[] args) throws Exception{
		List<String> localLinks = new ArrayList<>();
		List<String> sites = new ArrayList<>();
		String testSite = "https://www.saatchiart.com/art/Painting-Wheels-of-Freedom/410594/2793902/view";
		String baseSite = "https://www.saatchiart.com/paintings/fine-art";
		//List<String> testLinks = HTMLParser.extractLinks(testSite);
		attributes = new ArrayList<>();
		PaintingAttribute testAttribute = HTMLAttributeUtil.HTMLLinksParser.getHTMLLinks(testSite); 
		if(testAttribute!=null)
			System.out.println(testAttribute.toString());
		try{
			for (int i =1; i<=PAGES; i++){
				String param = "?page="+i;
				String site = baseSite+param;
				localLinks = HTMLLinkUtil.HTMLLinksParser.getHTMLLinks(site);
				for(String link: localLinks ){
					if(!sites.contains(link))
						sites.add(link);
				}
					
			}
			
			for (String link : sites) {
				System.out.println(link);
				PaintingAttribute attribute = HTMLAttributeUtil.HTMLLinksParser.getHTMLLinks(link); 
				if(attribute!=null)
					attributes.add(attribute);
			}
			System.out.println(sites.toString());
			System.out.println(attributes.toString());
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		HTMLParser.writeToFile(attributes);
	}
	
	public static void writeToFile(List<PaintingAttribute> attributes){
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME))){
			bw.write("Painting Name, Artist Name, Subject, Medium, Size, Price, Views, Favourites, Date, Materials, ArtWorks, Followers, Availability, paperUsed, canvasUsed, woodUsed, CardboardUsed, otherMaterial\n");
			for(PaintingAttribute attribute: attributes){
				if(attribute!=null)
					bw.write(attribute.toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}