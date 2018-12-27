package thinhtv.training.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.LazyDataModel;

import thinhtv.training.models.Movie;
import thinhtv.training.models.lazyDataModels.MoviesLazyDataModel;

@ManagedBean(name = "movieController")
@ViewScoped
public class MovieController implements Serializable{
	private static final long serialVersionUID = -2788199977054986476L;
	//private List<Movie> movies = null;
	
	private LazyDataModel<Movie> movies = new MoviesLazyDataModel();

	public void init() {
/*		if ((FacesContext.getCurrentInstance().getCurrentPhaseId() == PhaseId.RENDER_RESPONSE)) {
			Session session = HibernateUtil.getSessionFactory().openSession();
			movies = session.createQuery("From MOVIES", Movie.class).list();
			session.close();
		}*/
	}

	public LazyDataModel<Movie> getMovies() {
		return movies;
	}

	public void setMovies(LazyDataModel<Movie> movies) {
		this.movies = movies;
	}
	
	/** 
	 * lấy data theo sql thuần 
	 */
/*	public String selectAllMovies() throws SQLException, ClassNotFoundException {

		MoviesDataManager manager = new MoviesDataManager();
		movies = manager.selectAllMovies();
		return "";
	}

	public List<Movie> getMovies() {
		return movies;
	}

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}*/
	
}
