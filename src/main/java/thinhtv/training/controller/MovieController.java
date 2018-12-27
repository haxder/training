package thinhtv.training.controller;

import java.io.Serializable;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.hibernate.Session;
import org.primefaces.model.LazyDataModel;

import thinhtv.training.Utils.HibernateUtil;
import thinhtv.training.models.Movie;
import thinhtv.training.models.lazyDataModels.MoviesLazyDataModel;

@ManagedBean(name = "movieController")
@ViewScoped
public class MovieController implements Serializable {
	private static final long serialVersionUID = -2788199977054986476L;

	// private List<Movie> movies = null;
	private LazyDataModel<Movie> movies = new MoviesLazyDataModel();
	
	// edit action
	private Movie selectedMovie;
	private String selectedMovieID;
	private boolean editMovie;

	/**
	 * init data movies list view
	 */
	public void init() {
		/*
		 * if ((FacesContext.getCurrentInstance().getCurrentPhaseId() ==
		 * PhaseId.RENDER_RESPONSE)) { Session session =
		 * HibernateUtil.getSessionFactory().openSession(); movies =
		 * session.createQuery("From MOVIES", Movie.class).list(); session.close(); }
		 */
	}

	public void editInit() {
		Map<String, String> rqParameters = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap();
		String selectMVID = rqParameters.get("selectedMovieID");
		try {
			Session ss = HibernateUtil.getSessionFactory().openSession();
			selectedMovie = ss.createQuery("From MOVIES where MOVIE_ID = :selectId", Movie.class)
					.setParameter("selectId", selectMVID).getSingleResult();
		} catch (Exception e) {
			
		}
	}
	
	/**
	 * thay ddooir trạng thái button  = ajax
	 */
	public void changeStatus() {
		editMovie = !editMovie;
	}

	public LazyDataModel<Movie> getMovies() {
		return movies;
	}

	public void setMovies(LazyDataModel<Movie> movies) {
		this.movies = movies;
	}

	public String getSelectedMovieID() {
		return selectedMovieID;
	}

	public void setSelectedMovieID(String selectedMovieID) {
		this.selectedMovieID = selectedMovieID;
	}

	public Movie getSelectedMovie() {
		return selectedMovie;
	}

	public void setSelectedMovie(Movie selectedMovie) {
		this.selectedMovie = selectedMovie;
	}

	public boolean isEditMovie() {
		return editMovie;
	}

	public void setEditMovie(boolean editMovie) {
		this.editMovie = editMovie;
	}

	/**
	 * lấy data theo sql thuần
	 */
/*		
	 * public String selectAllMovies() throws SQLException, ClassNotFoundException {
	 * 
	 * MoviesDataManager manager = new MoviesDataManager(); movies =
	 * manager.selectAllMovies(); return ""; }
	 * 
	 * public List<Movie> getMovies() { return movies; }
	 * 
	 * public void setMovies(List<Movie> movies) { this.movies = movies; }
	 */

}
