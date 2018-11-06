package my.app.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class SqlSessionFactoryUtils {
    private static String resource = "mybatis-config.xml";
    private static SqlSessionFactory sqlSessionFactory;

//    测试用
    static{
        initSqlSessionFactory();
    }

    public static void initSqlSessionFactory(){
        try {
            InputStream is = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static SqlSession openSession(){
        return sqlSessionFactory.openSession();
    }

    public static void closeSession(SqlSession session){
        if (session != null){
            session.close();
        }
    }
}
