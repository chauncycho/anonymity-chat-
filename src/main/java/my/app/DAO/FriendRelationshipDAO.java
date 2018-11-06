package my.app.DAO;

import my.app.entities.FriendRelationship;
import my.app.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class FriendRelationshipDAO {
    public static List<FriendRelationship> findFriendRelationshipByUserid(Integer userid){
        SqlSession sqlSession = SqlSessionFactoryUtils.openSession();
        FriendRelationship rsForCheck = new FriendRelationship();
        rsForCheck.setUser_id(userid);
        List<FriendRelationship> list = sqlSession.selectList("findFriendRelationshipByUserid",rsForCheck);
        SqlSessionFactoryUtils.closeSession(sqlSession);
        return list;
    }

    public static List<FriendRelationship> findFriendRelationshipByFriendid(Integer friendid){
        SqlSession sqlSession = SqlSessionFactoryUtils.openSession();
        FriendRelationship rsForCheck = new FriendRelationship();
        rsForCheck.setFriend_id(friendid);
        List<FriendRelationship> list = sqlSession.selectList("findFriendRelationshipByFriendid",rsForCheck);
        SqlSessionFactoryUtils.closeSession(sqlSession);
        return list;
    }

    public static int insertRelationship(FriendRelationship fr){
        SqlSession sqlSession = SqlSessionFactoryUtils.openSession();
        int row = sqlSession.insert("insertRelationship",fr);
        sqlSession.commit();
        SqlSessionFactoryUtils.closeSession(sqlSession);
        return row;
    }
}
