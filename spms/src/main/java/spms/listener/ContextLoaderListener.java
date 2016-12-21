package spms.listener;


import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.commons.dbcp.BasicDataSource;

import spms.dao.MemberDao;
import spms.util.DBConnectionPool;

@WebListener
public class ContextLoaderListener implements ServletContextListener {
	Connection conn;
    public ContextLoaderListener() {
      
    }

    public void contextInitialized(ServletContextEvent event)  { 
    	
    	ServletContext sc = event.getServletContext();
    	try {
			String driver = sc.getInitParameter("driver");
			String url = sc.getInitParameter("url");
			String username = sc.getInitParameter("username");
			String password = sc.getInitParameter("password");
			
			BasicDataSource ds = new BasicDataSource();
			ds.setDriverClassName(driver);
			ds.setUrl(url);
			ds.setUsername(username);
			ds.setPassword(password);
    		
			MemberDao memberDao = new MemberDao();
			memberDao.setDs(ds);
			sc.setAttribute("memberDao", memberDao);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
    public void contextDestroyed(ServletContextEvent sce)  { 
        try{
        	conn.close();
        } catch (Exception e) {	
       }
    }
 }
	

