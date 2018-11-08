package my.app.DAO;

import my.app.entities.Message;
import my.app.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.Collections;
import java.util.List;

public class MessageDAO {
    public static List<Message> getMessagesById(int user_id){
        Message temp = new Message();
        temp.setUser_id(user_id);
        SqlSession session = SqlSessionFactoryUtils.openSession();
        List<Message> res = session.selectList("findMessagesById", temp);
        SqlSessionFactoryUtils.closeSession(session);
        return res;
    }

    public static List<Message> getMessagesByIdAndTargetId(int user_id, int target_id){
        Message temp = new Message();
        temp.setUser_id(user_id);
        temp.setTarget_id(target_id);
        SqlSession session = SqlSessionFactoryUtils.openSession();
        List<Message> res = session.selectList("findMessagesByIdAndTarget", temp);
        SqlSessionFactoryUtils.closeSession(session);
        return res;
    }

    public static List<Message> getLast20MessagesByIdAndTargetIdDesc(int user_id,int target_id){
        Message temp = new Message();
        temp.setUser_id(user_id);
        temp.setTarget_id(target_id);
        SqlSession session = SqlSessionFactoryUtils.openSession();
        List<Message> res = session.selectList("findLast20MessagesByIdAndTargetDesc", temp);
        Collections.reverse(res);
        SqlSessionFactoryUtils.closeSession(session);
        return res;
    }

    public static boolean insertMessage(Message message){
        SqlSession session = SqlSessionFactoryUtils.openSession();
        int row = session.insert("insertMessage",message);
        session.commit();
        SqlSessionFactoryUtils.closeSession(session);

        return row>0;
    }
}
