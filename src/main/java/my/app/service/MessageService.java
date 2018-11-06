package my.app.service;

import my.app.DAO.MessageDAO;
import my.app.entities.Message;

import java.util.Date;
import java.util.List;

public class MessageService {
//    通过user_id和target_id获取消息
    public static List<Message> getMessagesByIdAndTargetId(int user_id, int target_id){
        return MessageDAO.getMessagesByIdAndTargetId(user_id, target_id);
    }

//    通过user_id获取消息
    public static List<Message> getMessagesById(int user_id){
        return MessageDAO.getMessagesById(user_id);
    }

//    添加消息
    public static boolean addMessage(Message message){
        return MessageDAO.insertMessage(message);
    }

//    添加文本消息
    public static boolean addTextMessage(int user_id, int target_id, String value){
        Message message = new Message();
        message.setUser_id(user_id);
        message.setTarget_id(target_id);
        message.setType("text");
        message.setValue(value);
        message.setTime(new Date());
        return addMessage(message);
    }
}
