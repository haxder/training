package thinhtv.training.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
import thinhtv.training.entity.jizenGaiKeizu;
import thinhtv.training.entity.kenToIrai;
import thinhtv.training.entity.kessai_jyouhou;
import thinhtv.training.entity.test;
import thinhtv.training.mybatis.MybatisUtils;
import thinhtv.training.mybatis.mapper.MovieMapper;

@ManagedBean(name = "movieController")
@ViewScoped
public class MovieController implements Serializable {
	private static final long serialVersionUID = -2788199977054986476L;
	private List<test> list101 = getList101();
	
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
		OutputStream out = null;
		InputStream input = null;
		try {
			String dirPath = new File(FacesContext.getCurrentInstance()
					.getExternalContext()
					.getResource("/trainingExtensionSource/images").toURI())
					.getAbsolutePath();
			File f = new File(dirPath +"/"+ file.getSubmittedFileName());
			out = new FileOutputStream(f);
			input = file.getInputStream();
			byte[] buffer = new byte[1024];
			int length;

			while ((length = input.read(buffer)) > 0) {
				out.write(buffer, 0, length);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
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
	public List<test> getList101() {
		List<test> xxx = new ArrayList<test>();
		List<test> ls = new ArrayList<test>();
		test x = new test(new kessai_jyouhou("決裁中", "xxxxxxxx-xx", "xxxxxxxx-xxxxxx-xxx", "XXXXXXXXXXXXXXXX"),
				new kenToIrai(null, null, null, null, null, null, null),
				new jizenGaiKeizu(null,null, null, null, null, null));		
		
		ls.add(x);
		x = new test(new kessai_jyouhou("決裁済", "xxxxxxxx-xx", "xxxxxxxx-xxxxxx-xxx", "XXXXXXXXXXXXXXXX"),
				new kenToIrai("回答済", "xxxxxxxx", new Date(), "xxxxxxxxx", "0", "xxxxxxxxx", "xxxxxxxxx"),
				new jizenGaiKeizu("依頼済", new Date(), "xxxxxxxxx", "0", "xxxxxxxxx", "xxxxxxxxx"));
		
		ls.add(x);
		
		for (int i = 0; i < 40; i++) {
			xxx.addAll(ls);
		}
		return  xxx;
	}

	public void setList101(List<test> list101) {
		this.list101 = list101;
	}
}
