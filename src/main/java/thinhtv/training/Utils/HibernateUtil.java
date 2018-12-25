package thinhtv.training.Utils;

import java.util.Set;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.reflections.Reflections;

public class HibernateUtil {
	private static SessionFactory sessionFactory;

	public HibernateUtil() {
	}

	public static SessionFactory getSessionFactory() {
		
		Configuration configuration = new Configuration().configure();
		//configuration.addAnnotatedClass(thinhtv.training.models.Movie.class);
		
		Reflections reflections = new Reflections("thinhtv.training.models");

		Set<Class<?>> classes = reflections.getTypesAnnotatedWith(javax.persistence.Entity.class);

		for(Class<?> clazz : classes)
		{
		    configuration.addAnnotatedClass(clazz);
		}
		
		StandardServiceRegistryBuilder srb = new StandardServiceRegistryBuilder();
		srb.applySettings(configuration.getProperties());
		ServiceRegistry service = srb.build();
		sessionFactory = configuration.buildSessionFactory(service);
		
		return sessionFactory;
	}
}
