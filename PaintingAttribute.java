import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PaintingAttribute {
	String name, artist, subject, medium, materials, category;
	double price, size;
	long views, favourites, followers, artWork, following;
	Date date;
	boolean availability, paperUsed, canvasUsed, woodUsed, CardboardUsed, otherMaterial;
	static DecimalFormat df = new DecimalFormat("########00.00");

	
	public PaintingAttribute() {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		int index = name.lastIndexOf("Painting");
		if(index > 0){
			try{
				String tobeRemoved = name.substring(index);
				int lentobeRemoved = tobeRemoved.length();
				int lenRequired = name.length() - lentobeRemoved;
				name = name.substring(0, lenRequired);
				name = name.replaceAll("[^a-zA-Z0-9 ]","");
				name = name.replaceFirst("SaatchiArt", "");
				name = name.replaceFirst("Saatchi Art ", "");
			}finally{
				this.name = name;
			}
		}
		else
			name =null;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMedium() {
		return medium;
	}

	public void setMedium(String medium) {
		this.medium = medium;
	}

	public String getMaterials() {
		return materials;
	}

	public void setMaterials(String materials) {
		this.materials = materials;
		this.populateMaterialsUsed(materials);
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public long getFollowers() {
		return followers;
	}

	public void setFollowers(long followers) {
		this.followers = followers;
	}

	public long getArtWork() {
		return artWork;
	}

	public void setArtWork(long artWork) {
		this.artWork = artWork;
	}

	public long getFollowing() {
		return following;
	}

	public void setFollowing(long following) {
		this.following = following;
	}

	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public long getViews() {
		return views;
	}

	public void setViews(long views) {
		this.views = views;
	}

	public long getFavourites() {
		return favourites;
	}

	public void setFavourites(long favourites) {
		this.favourites = favourites;
	}	

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public boolean isAvailability() {
		return availability;
	}

	public void setAvailability(boolean availability) {
		this.availability = availability;
	}

	@Override
	public String toString() {
		if(this!=null && this.name!=null && this.artist!=null && this.subject!=null && this.materials!=null && this.date!=null){
			try{
			return name.replaceAll("[^a-zA-Z0-9 ]", "") + ", " + artist.replaceAll("[^a-zA-Z0-9 ]", "") + ", " + subject.replaceAll("[^a-zA-Z0-9 ]", "") + ", " + medium.replaceAll("[^a-zA-Z0-9 ]", "")
					+ ", " + df.format(size) + ", " + price +", "+ views
					+", "+ favourites + ", " + new SimpleDateFormat("ddMMMyyyy").format(date)+ ", " + materials.replaceAll("[^a-zA-Z0-9 ]", "") 
					+ ", " + artWork + ", " + followers  
					//+", " + following 
					+ ", " + Boolean.valueOf(availability) 
					+ ", " + paperUsed + ", " + canvasUsed + ", " + woodUsed + ", " + CardboardUsed +", "+ otherMaterial
					+"\n";
			}catch(Exception e){
				System.out.println("size= "+ size);
				e.printStackTrace();
			}
		}
		return " ";
	}


	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (CardboardUsed ? 1231 : 1237);
		result = prime * result + (int) (artWork ^ (artWork >>> 32));
		result = prime * result + ((artist == null) ? 0 : artist.hashCode());
		result = prime * result + (availability ? 1231 : 1237);
		result = prime * result + (canvasUsed ? 1231 : 1237);
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + (int) (favourites ^ (favourites >>> 32));
		result = prime * result + (int) (followers ^ (followers >>> 32));
		result = prime * result + (int) (following ^ (following >>> 32));
		result = prime * result + ((materials == null) ? 0 : materials.hashCode());
		result = prime * result + ((medium == null) ? 0 : medium.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + (otherMaterial ? 1231 : 1237);
		result = prime * result + (paperUsed ? 1231 : 1237);
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(size);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((subject == null) ? 0 : subject.hashCode());
		result = prime * result + (int) (views ^ (views >>> 32));
		result = prime * result + (woodUsed ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PaintingAttribute other = (PaintingAttribute) obj;
		if (CardboardUsed != other.CardboardUsed)
			return false;
		if (artWork != other.artWork)
			return false;
		if (artist == null) {
			if (other.artist != null)
				return false;
		} else if (!artist.equals(other.artist))
			return false;
		if (availability != other.availability)
			return false;
		if (canvasUsed != other.canvasUsed)
			return false;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (favourites != other.favourites)
			return false;
		if (followers != other.followers)
			return false;
		if (following != other.following)
			return false;
		if (materials == null) {
			if (other.materials != null)
				return false;
		} else if (!materials.equals(other.materials))
			return false;
		if (medium == null) {
			if (other.medium != null)
				return false;
		} else if (!medium.equals(other.medium))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (otherMaterial != other.otherMaterial)
			return false;
		if (paperUsed != other.paperUsed)
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		if (Double.doubleToLongBits(size) != Double.doubleToLongBits(other.size))
			return false;
		if (subject == null) {
			if (other.subject != null)
				return false;
		} else if (!subject.equals(other.subject))
			return false;
		if (views != other.views)
			return false;
		if (woodUsed != other.woodUsed)
			return false;
		return true;
	}

	public void populateMaterialsUsed(String materials){
		this.paperUsed = materials.contains("Paper");
		this.canvasUsed = materials.contains("Canvas");
		this.CardboardUsed = materials.contains("Cardboard");
		this.woodUsed = materials.contains("Wood");
		if(!paperUsed && !canvasUsed && !CardboardUsed && !woodUsed){
			this.otherMaterial = true;
		}
		
	}
	
}
