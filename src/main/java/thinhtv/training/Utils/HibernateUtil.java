package thinhtv.training.Utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
	private static SessionFactory sessionFactory;

	public HibernateUtil() {
	}

	public static SessionFactory getSessionFactory() {
		
		Configuration configuration = new Configuration().configure();
		configuration.addAnnotatedClass(thinhtv.training.models.Movie.class);
		
		StandardServiceRegistryBuilder srb = new StandardServiceRegistryBuilder();
		srb.applySettings(configuration.getProperties());
		ServiceRegistry service = srb.build();
		sessionFactory = configuration.buildSessionFactory(service);
		
		return sessionFactory;
	}
}
