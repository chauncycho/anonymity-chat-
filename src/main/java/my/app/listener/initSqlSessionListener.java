package my.app.listener;

import my.app.utils.SqlSessionFactoryUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class initSqlSessionListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent sce) {
//        SqlSessionFactoryUtils.initSqlSessionFactory();
        System.out.println("容器加载中");
    }

    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("容器销毁");
    }
}
