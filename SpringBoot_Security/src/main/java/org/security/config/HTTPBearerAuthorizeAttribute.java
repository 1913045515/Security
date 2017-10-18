package org.security.config;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.security.jwt.JwtHelper;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@Component
public class HTTPBearerAuthorizeAttribute {//implements Filter {

//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
//                filterConfig.getServletContext());
//    }
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//            throws IOException, ServletException {
//        String resultMsg;
//        HttpServletRequest httpRequest = (HttpServletRequest) request;
//        String auth = httpRequest.getHeader("Authorization");
//        System.out.println("auth:"+auth);
////        if ((auth != null) && (auth.length() > 7)) {
////            String HeadStr = auth.substring(0, 6).toLowerCase();
////            if (HeadStr.compareTo("bearer") == 0) {
////
////                auth = auth.substring(7, auth.length());
////                if (JwtHelper.parseJWT(auth, "mySecret") != null) {
//                    chain.doFilter(request, response);
//                    return;
////                }
////            }
////        }
////        HttpServletResponse httpResponse = (HttpServletResponse) response;
////        httpResponse.setCharacterEncoding("UTF-8");
////        httpResponse.setContentType("application/json; charset=utf-8");
////        httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
////        ObjectMapper mapper = new ObjectMapper();
////        resultMsg = "error";
////        httpResponse.getWriter().write(mapper.writeValueAsString(resultMsg));
////        return;
//    }
//
//    @Override
//    public void destroy() {
//
//    }
}