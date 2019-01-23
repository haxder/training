package thinhtv.training.mybatis.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;
import thinhtv.training.entity.Movie;

import java.util.List;

@Mapper
public interface MovieSQLDynamicMapper {


    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "movieObject" , value = {
            @Result(column = "MOVIE_ID", property = "movieId", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "TITLE", property = "title", jdbcType = JdbcType.NVARCHAR),
            @Result(column = "RELEASE_DATE", property = "releaseDate", jdbcType = JdbcType.DATE),
            @Result(column = "GENRE", property = "genre", jdbcType = JdbcType.NVARCHAR),
            @Result(column = "PRICE", property = "price", jdbcType = JdbcType.NVARCHAR),
            @Result(column = "IMAGE", property = "image", jdbcType = JdbcType.NVARCHAR),
            @Result(column = "RATE", property = "rate", jdbcType = JdbcType.INTEGER)
    })
    /** đối tượng trả về là 1 list Movie xác định. tạm thời có thẻ bỏ qua khai báo @resulst */
    public List<Movie> getAllxxxxxxx(SelectStatementProvider selectQr);

/*    public Movie findMovieBy(String findID);

    public long countAll();

    public List<Movie> lazyLoad(@Param("offset") int first, @Param("limit") int pageSize);

    public void updateMovie(Movie mv);

    public void deleteMovie(Movie mv);

    public void insertMovie(Movie mv);*/
}
