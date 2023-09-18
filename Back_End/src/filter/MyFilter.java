package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author : Gathsara
 * created : 9/18/2023 -- 8:24 PM
 **/
@WebFilter(urlPatterns = "/*")
public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        /*check http method*/
        String method = req.getMethod();

        if (method.equals("OPTIONS")) {
            resp.setStatus(200);
            resp.addHeader("Access-Control-Allow-Origin", "*");
            resp.addHeader("Access-Control-Allow-Methods", "PUT, DELETE");
            resp.addHeader("Access-Control-Allow-Headers", "content-type,auth");
            resp.addHeader("Content-Type", "application/json");
            filterChain.doFilter(servletRequest,servletResponse);

        } else {
            resp.addHeader("Access-Control-Allow-Origin", "*");
            resp.addHeader("Content-Type", "application/json");
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
