package my.app.service;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpsConfigurator;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint(value="/websocket/{userid}")
public class WebSocketService {
    private static int onlineCount = 0;
    private static Map<String,Session> users = new ConcurrentHashMap<String, Session>();

    @OnOpen
    public void onOpen(@PathParam("userid") String userid, Session session, EndpointConfig endpointConfig) throws IOException {
        WebSocketService.users.put(userid, session);
        countAdd();

        //通知登录成功
        session.getBasicRemote().sendText(userid+"连接成功");
        System.out.println(userid+"登录");
        System.out.println("当前用户数:"+onlineCount);
    }

    @OnClose
    public void onClose(Session session){
        String userid = getUseridBySession(session);

        countMinus();

        System.out.println(userid+"登出");
        System.out.println("当前用户数:"+onlineCount);
    }

    @OnMessage
    public void onMessage(String message ,Session session) throws IOException {
        System.out.println(getUseridBySession(session)+"说了:"+message);
        Map map = new Gson().fromJson(message, Map.class);
        System.out.println(map);
        String target_id = (String) map.get("target_id");
        String value = (String)map.get("value");
//        System.out.println(user_id+" "+target_id+" "+value);
        //发送
        sendTextMessage(session,target_id,value);
    }

    @OnError
    public void onError(Session session, Throwable error){
        String id = getUseridBySession(session);
        if (id != null){
            System.out.print(id+" ");
        }
        System.out.println("发生错误");
        error.printStackTrace();
    }

    private void countAdd(){
        onlineCount ++;
    }

    private void countMinus(){
        onlineCount --;
    }

    public Session getSessionByUserid(String userid){
        return users.get(userid);
    }

    public String getUseridBySession(Session session){
        String userid = null;
        for (String key : users.keySet()){
            if (users.get(key).equals(session)){
                userid = key;
            }
        }
        return userid;
    }

    public void sendTextMessage(Session session, String str_target_id, String value){
        String strUserid = getUseridBySession(session);//自己id
        int userid = Integer.parseInt(strUserid);//自己id
        int target_id = Integer.parseInt(str_target_id);//对方id
        MessageService.addTextMessage(userid,target_id,value);//入数据库

        //消息处理
        Session targetSession = getSessionByUserid(str_target_id);//对方session

        if (targetSession == null){
            System.out.println("对方不在线，发送失败");
            return;
        }

        Map mapMessage = new HashMap();
        mapMessage.put("userId",strUserid);
        mapMessage.put("targetId",str_target_id);
        mapMessage.put("type","text");
        mapMessage.put("value",value);
        mapMessage.put("time",new Date());
        String jsonMessage = new Gson().toJson(mapMessage);

        try {
            targetSession.getBasicRemote().sendText(jsonMessage);//发送
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


