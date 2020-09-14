package com.squad.auth.security.filter;

import com.squad.auth.util.CorsHelper;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class MyCorsFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req , ServletResponse res , @NotNull FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = CorsHelper.addResponseHeaders(res);
        chain.doFilter(req , response);
    }

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void destroy() {
    }

}
