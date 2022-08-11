package com.example.demo.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@WebFilter(filterName="MyFilter")
public class MyFilter implements Filter {

    ConcurrentHashMap<String, HttpSession> hashMap = new ConcurrentHashMap<>();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("filter start");
        if (!(servletRequest instanceof HttpServletRequest)){
            return;
        }

        HttpServletRequest req = (HttpServletRequest)servletRequest;
        HttpSession session = req.getSession();
        String sessionId = session.getId();
        if (hashMap.containsKey(sessionId)){
            log.info("老客户");
        }else {
            hashMap.put(sessionId, session);
            log.info("新小子");
        }
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        resp.addCookie(new Cookie("sessionId", sessionId));
        log.info("filter end");
        filterChain.doFilter(req, servletResponse);
    }
}
