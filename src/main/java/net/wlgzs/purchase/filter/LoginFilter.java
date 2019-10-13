package net.wlgzs.purchase.filter;

import net.wlgzs.purchase.entity.User;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author:胡亚星
 * @createTime 2018-05-13 9:53
 * @description:
 **/
public class LoginFilter implements Filter {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(LoginFilter.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("前台过滤器启动");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        logger.info("前台过滤");
        System.out.println("httpRequest=="+httpRequest.getRequestURI());
        String url = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());
        System.out.println(url);
        if (url.startsWith("/") && url.length() > 1) {
            url = url.substring(1);
        }
        System.out.println(url);


        HttpSession session = httpRequest.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null){
            System.out.println("前台通过");
            // session存在
            chain.doFilter(httpRequest, httpResponse);
            return;
        } else {
            System.out.println("前台未通过");
            // session不存在 准备跳转失败
            System.out.println(httpRequest.getContextPath()+"/login/toLogin");
            httpResponse.sendRedirect(httpRequest.getContextPath()+"/login/toLogin");
            return;
        }
    }

    @Override
    public void destroy() {

    }
}