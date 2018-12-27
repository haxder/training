package thinhtv.training.controller;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;

import org.hibernate.Session;

import thinhtv.training.Utils.HibernateUtil;
import thinhtv.training.manager.MoviesDataManager;
import thinhtv.training.models.Movie;

@ManagedBean(name = "movieController")
@ViewScoped
public class MovieController implements Serializable{
	private static final long serialVersionUID = -2788199977054986476L;
	private List<Movie> movies = null;

	public void init() {
		if ((FacesContext.getCurrentInstance().getCurrentPhaseId() == PhaseId.RENDER_RESPONSE)) {
			Session session = HibernateUtil.getSessionFactory().openSession();
			movies = session.createQuery("From MOVIES", Movie.class).list();
			session.close();
		}
	}
	
	/** 
	 * lấy data theo sql thuần 
	 */
	public String selectAllMovies() throws SQLException, ClassNotFoundException {

		MoviesDataManager manager = new MoviesDataManager();
		movies = manager.selectAllMovies();
		return "";
	}

	public List<Movie> getMovies() {
		return movies;
	}

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}
}
