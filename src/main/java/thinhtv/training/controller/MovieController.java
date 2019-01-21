package thinhtv.training.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import common.DownloadUtils;
import common.ExcelUtils;
import thinhtv.training.entity.Movie;
import thinhtv.training.mybatis.MybatisUtils;
import thinhtv.training.mybatis.mapper.MovieMapper;

@ManagedBean(name = "movieController")
@ViewScoped
public class MovieController implements Serializable {
	private static final long serialVersionUID = -2788199977054986476L;
	
	@ManagedProperty("#{mybastis}")
	private MybatisUtils mybatisUtils;
	
	// private List<Movie> movies = null;
	private LazyDataModel<Movie> movies = createMovieLazyModel();

	// edit action
	private Movie selectedMovie;
	private String selectedMovieID;
	private boolean editMovie;

	// upload file
	private Part imgfileUpload;

	// create Movie
	private Movie createMovie;

	public void editInit() {
		if (selectedMovie == null) {
			Map<String, String> rqParameters = FacesContext.getCurrentInstance().getExternalContext()
					.getRequestParameterMap();
			String selectMVID = rqParameters.get("selectedMovieID");
			MovieMapper mapper = mybatisUtils.getMapper(MovieMapper.class);
			selectedMovie = mapper.findMovieBy(selectMVID);
		}
	}

	/**
	 * thay ddooir trạng thái button  = ajax, save data
	 */
	public void changeStatus() {
		if (editMovie) {
			if(imgfileUpload != null) {
				saveFile(imgfileUpload);
				selectedMovie.setImage(imgfileUpload.getSubmittedFileName());
			}
			MovieMapper mapper = mybatisUtils.getMapper(MovieMapper.class);
			mapper.updateMovie(selectedMovie);
		}
		editMovie = !editMovie;
	}

	/**
	 * xoa doi tuong torng db
	 * @param mv
	 */
	public void delete (Movie mv) {
		MovieMapper mapper = mybatisUtils.getMapper(MovieMapper.class);
		mapper.deleteMovie(mv);
		// update lại model
		movies = createMovieLazyModel();
	}
	
	public void createMovieInit() {
		createMovie = new Movie();
	}
	
	public String createMovieAction() {
		if(imgfileUpload != null) {
			saveFile(imgfileUpload);
			createMovie.setImage(imgfileUpload.getSubmittedFileName());
		}
		MovieMapper mapper = mybatisUtils.getMapper(MovieMapper.class);
		mapper.insertMovie(createMovie);
		return "INDEX";
	}
	
	public void exportExcel() {
		try {
			DownloadUtils.downloadExcel(ExcelUtils.createXSSFWorkbook(movies.getWrappedData()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 *  lưu file vào địa chỉ vùng nhớ thực trên server
	 * @param file
	 */
	private void saveFile(Part file) {
		String dirPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/trainingExtensionSource/images");
		try {
			file.write(dirPath + "/" + file.getSubmittedFileName());
		} catch (IOException e) {
		}
	}

	public Part getImgfileUpload() {
		return imgfileUpload;
	}

	public void setImgfileUpload(Part imgfileUpload) {
		this.imgfileUpload = imgfileUpload;
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

	public Movie getCreateMovie() {
		return createMovie;
	}

	public void setCreateMovie(Movie createMovie) {
		this.createMovie = createMovie;
	}
	public MybatisUtils getMybatisUtils() {
		return mybatisUtils;
	}

	public void setMybatisUtils(MybatisUtils mybatisUtils) {
		this.mybatisUtils = mybatisUtils;
	}
	
	private LazyDataModel<Movie> createMovieLazyModel() {
		return new LazyDataModel<Movie>() {
			private static final long serialVersionUID = -7234634978113645736L;
			@Override
			public List<Movie> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
				MovieMapper mapper = mybatisUtils.getMapper(MovieMapper.class);
				Long count = mapper.countAll();
				this.setRowCount(count.intValue());
				return mapper.lazyLoad(first, pageSize);
			}
		};
	}
}
