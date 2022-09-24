package com.bookstore.apigatewayservice.configs;

/**
 * @author: Phuc Tran
 */

import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

public class AddTraceIdToHttpResponseConfig extends GenericFilterBean {

//    private final Tracer tracer;
//
//    AddTraceIdToHttpResponseConfig(Tracer tracer) {
//        this.tracer = tracer;
//    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        Span currentSpan = this.tracer.currentSpan();
//        if (currentSpan == null) {
//            chain.doFilter(request, response);
//            return;
//        }
//        ((HttpServletResponse) response).addHeader("ZIPKIN-TRACE-ID",
//                currentSpan.context().traceIdString());
//        // we can also add some custom tags
//        currentSpan.tag("custom", "tag");
//        chain.doFilter(request, response);
    }
}
