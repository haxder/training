package thinhtv.training.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.hibernate.SessionFactory;

import thinhtv.training.Utils.HibernateUtil;

@WebListener
public class SessionFactoryListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("------- start created sessionFactory ----------");
		HibernateUtil.getSessionFactory();
		System.out.println("-------- end created sessionFactory -----------");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		SessionFactory sessionFactory = (SessionFactory) sce.getServletContext().getAttribute("SessionFactory");
    	if(sessionFactory != null && !sessionFactory.isClosed()){
    		HibernateUtil.shutdown();
    		sessionFactory.close();
    		System.out.println("-----close sessionFactory ----");
    	}
	}

}
