package thinhtv.training.mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


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
}
