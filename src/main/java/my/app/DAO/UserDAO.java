package my.app.DAO;

import my.app.entities.User;
import my.app.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;

public class UserDAO {
    public static User findUserByOpenid(String openid){
        SqlSession sqlSession = SqlSessionFactoryUtils.openSession();
        User userForCheck = new User();
        userForCheck.setOpenid(openid);
        User user = sqlSession.selectOne("findUserByOpenid",userForCheck);
        SqlSessionFactoryUtils.closeSession(sqlSession);
        return user;
    }

    public static User findUserById(int id){
        SqlSession sqlSession = SqlSessionFactoryUtils.openSession();
        User userForCheck = new User();
        userForCheck.setId(id);
        User user = sqlSession.selectOne("findUserById",userForCheck);
        SqlSessionFactoryUtils.closeSession(sqlSession);
        return user;
    }

    public static int insertUser(User user){
        SqlSession sqlSession = SqlSessionFactoryUtils.openSession();
        int row = sqlSession.insert("insertUser",user);
        sqlSession.commit();
        SqlSessionFactoryUtils.closeSession(sqlSession);
        return row;
    }

    public static int updateUser(User user){
        SqlSession sqlSession = SqlSessionFactoryUtils.openSession();
        System.out.println(user);
        int row = sqlSession.update("updateUser",user);
        sqlSession.commit();
        SqlSessionFactoryUtils.closeSession(sqlSession);
        return row;
    }
}
