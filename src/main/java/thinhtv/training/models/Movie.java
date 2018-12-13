package thinhtv.training.models;

import java.io.Serializable;

public class Movie implements Serializable {

	private static final long serialVersionUID = 6383578742528861934L;
	
	/* id auto increment*/
	private Integer movieId;
	private String title;
	private String releaseDate;
	private String genre;
	private String price;
	private	String image;
	
	public Movie() {}
	public Movie(Integer movieId, String title, String releaseDate, String genre, String price) {
		this.movieId = movieId;
		this.title = title;
		this.releaseDate = releaseDate;
		this.genre = genre;
		this.price = price;
	}
	
	public Integer getMovieId() {
		return movieId;
	}
	public void setMovieId(Integer movieId) {
		this.movieId = movieId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
}
