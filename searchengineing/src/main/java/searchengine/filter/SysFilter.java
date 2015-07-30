package searchengine.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SysFilter implements Filter{
	
	private static final Logger log = LoggerFactory.getLogger(SysFilter.class);
	@Override
	public void destroy() {
	}
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request  = (HttpServletRequest) req;
		String uri = request.getRequestURI();
		if(uri.endsWith(".js")||uri.endsWith(".css")||uri.endsWith(".jpg")||uri.endsWith(".png")||uri.endsWith(".ico")){
			chain.doFilter(req, res);
		}else{
			log.info(request.getRequestURI());
			log.debug(request.getRemoteAddr());
			chain.doFilter(req, res);
		}
	}
	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}
}
