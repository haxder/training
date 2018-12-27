package thinhtv.training.manager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import oracle.jdbc.OracleTypes;
import thinhtv.training.models.Movie;
public class MoviesDataManager extends DatabaseManager {
	
	/**
	 *  select all data
	 * @return
	 */
	public List<Movie> selectAllMovies() {

		List<Movie> movies = new ArrayList<Movie>();
		try {
			databaseConnect();
			stm = connect.prepareCall("{ ? = call select_all_movies }");
			stm.registerOutParameter(1, OracleTypes.CURSOR);
			stm.executeQuery();
			ResultSet rset = (ResultSet) stm.getObject(1);
			while (rset.next()) {
				Integer movieId = rset.getInt("MOVIE_ID");
				String title = rset.getString("TITLE");
				Date releaseDate = rset.getDate("RELEASE_DATE");
				String genre = rset.getString("GENRE");
				String price = rset.getString("PRICE");
				Movie mv = new Movie(movieId, title, releaseDate, genre, price);
				movies.add(mv);
			}
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		} finally {
			databaseDisconnect();
		}
		return movies;
	}

}
