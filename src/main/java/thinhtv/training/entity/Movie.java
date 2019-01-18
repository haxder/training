package thinhtv.training.entity;

import java.io.Serializable;
import java.util.Date;

public class Movie implements Serializable {

	private static final long serialVersionUID = 6383578742528861934L;
	private Integer movieId;
	private String title;
	private Date releaseDate;
	private String genre;
	private String price;
	private String image;
	private int rate;
	
	public Movie() {
	}

	public Movie(Integer movieId, String title, Date releaseDate, String genre, String price) {
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

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
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
	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}
}