package thinhtv.training.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

@Entity(name = "MOVIES")
@DynamicUpdate(value = true)

@NamedQueries({
	@NamedQuery(name="GET_TOTALL_MOVIES", query="Select count(1) From MOVIES"),
	@NamedQuery(name="GET_ALL_MOVIES", query="From MOVIES")
})

public class Movie implements Serializable {

	private static final long serialVersionUID = 6383578742528861934L;

	@Id
	@Column(name = "MOVIE_ID")
	@Generated(GenerationTime.INSERT)
	/* id auto increment */
	private Integer movieId;

	@Column(name = "TITLE")
	private String title;

	@Column(name = "RELEASE_DATE")
	@Temporal(TemporalType.DATE)
	private Date releaseDate;

	@Column(name = "GENRE")
	private String genre;

	@Column(name = "PRICE")
	private String price;

	@Column(name = "IMAGE")
	private String image;
	
	@Column(name = "RATE")
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
