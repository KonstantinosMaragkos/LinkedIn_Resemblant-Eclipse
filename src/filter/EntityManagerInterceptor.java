package filter;

import javax.persistence.EntityTransaction;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import jpautils.EntityManagerHelper;

/**
 * Servlet Filter implementation class EntityManagerInterceptor
 */
@WebFilter("/EntityManagerInterceptor")
public class EntityManagerInterceptor implements Filter {

    /**
     * Default constructor. 
     */
    public EntityManagerInterceptor() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		try {
			EntityManagerHelper.beginTransaction();
			chain.doFilter(request, response);
			EntityManagerHelper.commit();
		} 
		catch (RuntimeException e) 
		{
			EntityTransaction tx = EntityManagerHelper.getTransaction();
			if (tx != null && tx.isActive()) 
				EntityManagerHelper.rollback();
		    throw e;
			
		} 
		finally {
			EntityManagerHelper.closeEntityManager();
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
