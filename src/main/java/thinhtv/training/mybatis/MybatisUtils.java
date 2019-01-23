package thinhtv.training.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;
import org.mybatis.dynamic.sql.render.RenderingStrategy;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import thinhtv.training.entity.Movie;
import thinhtv.training.mybatis.mapper.MovieSQLDynamicMapper;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.JDBCType;
import java.util.List;


@ManagedBean(name="mybastis")
@ApplicationScoped
public class MybatisUtils implements Serializable{
	private static final long serialVersionUID = -1181338832300478317L;
	private SqlSessionFactory ssFactory;
	
	public SqlSessionFactory getSqlSessionFactory(){
		if (ssFactory ==null ) {
			try {
			InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
			ssFactory = new SqlSessionFactoryBuilder().build(inputStream);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return ssFactory;
	}
	
	public SqlSession openSqlSession(){
		return getSqlSessionFactory().openSession();
	}
	
	public <T> T getMapper(Class<T> type) {
		return openSqlSession().getMapper(type);
	}

	public static void main(String[] args) {
        /** test hoạt động của sql dynamic*/
	    SqlTable table = SqlTable.of("MOVIES");
        SqlColumn<Integer> id = table.column("MOVIE_ID", JDBCType.INTEGER);

		SqlSession ss = new MybatisUtils().openSqlSession();
		MovieSQLDynamicMapper mp = ss.getMapper(MovieSQLDynamicMapper.class);
		SelectStatementProvider selectStatement =
                SqlBuilder.select(id)
                        .from(table)
                        .build().render(RenderingStrategy.MYBATIS3);
		List<Movie> mvs = mp.getAllxxxxxxx(selectStatement);
        System.out.println(mvs.size());
	}
}
