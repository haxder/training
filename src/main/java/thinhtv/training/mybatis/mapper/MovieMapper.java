package thinhtv.training.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import thinhtv.training.entity.Movie;

@Mapper
public interface MovieMapper {
	@Select("SELECT * FROM Movies")
	public List<Movie> getAll();
	
	@Select("SELECT * FROM Movies WHERE MOVIE_ID = #{findID}")
	public Movie findMovieBy(String findID);
	
	@Select("SELECT count(1) FROM Movies")
	public long countAll();
	
	@Select("SELECT * FROM Movies OFFSET #{offset} ROWS FETCH NEXT #{limit} ROWS ONLY")
	public List<Movie> lazyLoad(@Param("offset") int first, @Param("limit") int pageSize); // mybatis sẽ bỏ tất cả @param vào map -> @Param("offset") phải trùng với tên trong SQL
	
	@Update("UPDATE MOVIES SET "
			+ "TITLE = #{title,jdbcType=VARCHAR}, "
			+ "RELEASE_DATE = #{releaseDate,jdbcType=TIMESTAMP}, "
			+ "GENRE = #{genre,jdbcType=VARCHAR}, "
			+ "PRICE = #{price,jdbcType=VARCHAR}, "
			+ "IMAGE = #{image,jdbcType=VARCHAR}, "
			+ "RATE = #{rate,jdbcType=INTEGER} "
			+ "WHERE MOVIE_ID = #{movieId}")
	// genkey tự động -> phải thiết định selectkey nếu ko sẽ báo lỗi!
	@SelectKey(statement="select movies_sq.nextval from dual", resultType= int.class , keyProperty= "movieId" , keyColumn ="MOVIE_ID", before = false)
	public void updateMovie(Movie mv);
	
	@Delete("DELETE from MOVIES WHERE MOVIE_ID = #{movieId}")
	public void deleteMovie(Movie mv);
	
	@Insert("INSERT INTO MOVIES (TITLE, RELEASE_DATE, GENRE, PRICE, IMAGE, RATE) VALUES (#{title,jdbcType=VARCHAR}, #{releaseDate,jdbcType=TIMESTAMP}, #{genre,jdbcType=VARCHAR}, #{price,jdbcType=VARCHAR}, #{image,jdbcType=VARCHAR}, #{rate,jdbcType=INTEGER})")
	// genkey tự động -> phải thiết định selectkey nếu ko sẽ báo lỗi!
	// muon insert duoc gia tri null thif can thiet dinh jdbcType=VARCHAR
	@SelectKey(statement="select movies_sq.nextval from dual", resultType= int.class , keyProperty= "movieId" , keyColumn ="MOVIE_ID", before = false)
	public void insertMovie(Movie mv);
}
