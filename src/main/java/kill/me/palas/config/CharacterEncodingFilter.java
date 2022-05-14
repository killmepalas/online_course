package kill.me.palas.config;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

public class CharacterEncodingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        chain.doFilter(request, response);
    }
}
