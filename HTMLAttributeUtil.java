import java.io.IOException;
import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HTMLAttributeUtil {
	public static class HTMLLinksParser{
		public static PaintingAttribute getHTMLLinks(String site){
			//List<PaintingAttribute> attributes = new ArrayList<>();
			Document doc =null;
			PaintingAttribute attribute = new PaintingAttribute();
			try {
				doc = Jsoup.connect(site).get();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
			
			Elements metaTags = doc.getElementsByTag("meta");
			Elements favTags = doc.getElementsByTag("div");
			
			
			String[] dimention = new String[3];
			for(Element metaTag : metaTags){
				
				String content = metaTag.attr("content");
		    	String property = metaTag.attr("property");
		    	switch (property){
		    	case "og:title":
		    		//System.out.println(content);
		    		attribute.setName(content.replace(",", " "));
		    		break;
		    	case "bt:artist":
		    		attribute.setArtist(content);
		    		break;
		    	case "bt:subject":
		    		attribute.setSubject(content.replace(",", ""));
		    		break;
		    	case "bt:mediums":
		    		attribute.setMedium(content.replaceAll(","," and "));
		    		break;
		    	case "og:price:amount":
		    		attribute.setPrice(Double.valueOf(content));
		    		break;
		    	case "bt:dimensions":
		    		dimention = content.split("x");
		    		dimention[0] = dimention[0].replace("H", "").trim();
		    		dimention[1] = dimention[1].replace("W", "").trim();
		    		dimention[2] = dimention[2].replace("in", "").trim();
		    		//attribute.setSize(Double.valueOf(dimention[0])*Double.valueOf(dimention[1])*Double.valueOf(dimention[2]));
		    		attribute.setSize(Double.valueOf(dimention[0])*Double.valueOf(dimention[1]));
		    		//System.out.println("size = "+ attribute.getSize());
		    		break;
		    	case "bt:pubDate":
		    		attribute.setDate(new Date(Long.valueOf(content)));
		    		break;
		    	case "bt:materials":
		    		attribute.setMaterials(content.replace(",", " and "));
		    		break;
		    	case "bt:category":
		    		attribute.setCategory(content.replace(",", ""));
		    		break;
//		    	case "bt:isUnavailable":
//		    		if(content.equals("false"))
//		    			attribute.setAvailability(true);
//		    		else
//		    			attribute.setAvailability(false);
		    	default:
		    		break;
		    	}
		    }
			
			for(Element favTag : favTags){
				if(favTag.attr("id").equals("favoriteCount")){
					attribute.setFavourites(Long.parseLong(favTag.text()));
				}
				if(favTag.attr("class").equals("art-detail-stats")){
					//String t = ;
					//String te = "71 Views 6 Favorites";
					//System.out.println(t);
					attribute.setViews(Long.parseLong(favTag.text().split("\\s")[0]));
					attribute.setFavourites(Long.parseLong(favTag.text().split("\\s")[2]));
					//System.out.println(favTag.text().split("\\s")[2]);
				}
				if(favTag.attr("class").equals("small-12 columns breadcrumbs")){
					Elements links = favTag.select("a[href]");
					//System.out.println();
					for (Element link : links) {
					      if(link.text().equals(attribute.getArtist())){
					    	  //System.out.println(link.attr("abs:href"));
					    	  setArtistDetails(link.attr("abs:href"),attribute);
					      }
					    }
					//System.out.println();
					System.out.println(HTMLParser.attributes.size()+1);
				}
				if(favTag.attr("class").equals("buy-prints")){
					if(favTag.text().length()>0){
						attribute.setAvailability(true);
					}
				}
				
			}
			
			
			return attribute;
		}
		public static void setArtistDetails(String url, PaintingAttribute attribute){
			Document doc =null;
			try {
				doc = Jsoup.connect(url).get();
				Elements favTags = doc.getElementsByTag("div");
				Elements artWorksTags = doc.getElementsByTag("i");
				Elements artWorkTags = doc.getElementsByTag("a");
				for(Element favTag : favTags){
					if(favTag.attr("class").equals("about-follow-nav")){
						attribute.setFollowing(Long.parseLong(favTag.text().split("\\s")[0]));
						attribute.setFollowers(Long.parseLong(favTag.text().split("\\s")[2]));
						//System.out.println(favTag.text());
					}
				}
				for(Element artWorksTag : artWorksTags){
					if(artWorksTag.attr("class").equals("fa fa-suitcase")){
						System.out.println(artWorksTag.text());
					}
				}
				for(Element artWorkTag :artWorkTags){
					if(artWorkTag.attr("title").equals("Link to Artworks")){
						//System.out.println(artWorkTag.text());
						attribute.setArtWork(Long.parseLong(artWorkTag.text().split("\\s")[1].replaceAll("[^0-9]", "")));
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
