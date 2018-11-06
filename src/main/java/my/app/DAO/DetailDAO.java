package my.app.DAO;

import my.app.entities.Detail;
import my.app.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;

public class DetailDAO {
    public static Detail findDetailById(int id){
        SqlSession sqlSession = SqlSessionFactoryUtils.openSession();
        Detail detailForCheck = new Detail();
        detailForCheck.setId(id);
        Detail detail = sqlSession.selectOne("findDetailById",detailForCheck);
        SqlSessionFactoryUtils.closeSession(sqlSession);
        return detail;
    }

    public static int insertDetail(Detail detail){
        SqlSession sqlSession = SqlSessionFactoryUtils.openSession();
        int row = sqlSession.insert("insertDetail",detail);
        sqlSession.commit();
        SqlSessionFactoryUtils.closeSession(sqlSession);
        return row;
    }

    public static int updateDetail(Detail detail){
        SqlSession sqlSession = SqlSessionFactoryUtils.openSession();
        System.out.println(detail);
        int row = sqlSession.update("updateDetail",detail);
        sqlSession.commit();
        SqlSessionFactoryUtils.closeSession(sqlSession);
        return row;
    }
}
