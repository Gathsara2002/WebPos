package listener;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @author : Gathsara
 * created : 9/19/2023 -- 8:14 PM
 **/

@WebListener
public class DBCP implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        BasicDataSource pool = new BasicDataSource();
        pool.setDriverClassName("com.mysql.jdbc.Driver");
        pool.setUrl("jdbc:mysql://localhost:3306/webPos");
        pool.setUsername("root");
        pool.setPassword("1234");
        pool.setInitialSize(10);
        pool.setMaxTotal(10);
        servletContext.setAttribute("dbcp", pool);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
