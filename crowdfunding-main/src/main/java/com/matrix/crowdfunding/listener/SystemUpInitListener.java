package com.matrix.crowdfunding.listener;

import com.matrix.crowdfunding.util.Const;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 需要在web.xml 中进行配置listener
 * 监听application对象的创建和销毁
 */

public class SystemUpInitListener implements ServletContextListener {


    Logger log = LoggerFactory.getLogger(SystemUpInitListener.class);


    // 当Application创建时执行初始化操作
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        String contextPath = servletContext.getContextPath();
        log.debug("当前应用上下文路径:{}", contextPath);
        servletContext.setAttribute(Const.PATH, contextPath);  // 方到域里面去 给整个应用程序使用
    }

    // 当Application销毁时执行初始化操作
    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        log.debug("当前对象用application销毁 退出的时候执行");
    }
}
