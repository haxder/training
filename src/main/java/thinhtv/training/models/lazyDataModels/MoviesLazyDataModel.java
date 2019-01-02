package thinhtv.training.models.lazyDataModels;
import java.util.List;
import java.util.Map;
import org.hibernate.Session;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import thinhtv.training.Utils.HibernateUtil;
import thinhtv.training.models.Movie;

public class MoviesLazyDataModel extends LazyDataModel<Movie> {
	
	private static final long serialVersionUID = -4836640077175973670L;
	private Session session = HibernateUtil.getSessionFactory().openSession();
	public MoviesLazyDataModel() {
		Long maxRow = session.createQuery("Select count(*) From MOVIES", Long.class).getSingleResult();
		// bắt buộc phải khởi tạo được max row trong db! không thì sẽ ko tính được vị trí cần lấy
		
		this.setRowCount(maxRow.intValue());
		System.out.println("☆☆" + maxRow + "☆☆");
	}

	@Override
	public List<Movie> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
		List<Movie> movies = session.createQuery("From MOVIES", Movie.class)
				.setFirstResult(first)
				.setMaxResults(pageSize)
				.getResultList();
		return movies;
	}
}
