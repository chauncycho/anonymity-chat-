package my.app.service;

import com.sun.net.httpserver.HttpsConfigurator;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
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
        session.getBasicRemote().sendText("你刚刚说了："+message);
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
                users.remove(key);
            }
        }
        return userid;
    }
}


