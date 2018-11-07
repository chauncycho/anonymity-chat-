package my.app.service;

import com.google.gson.Gson;
import my.app.DAO.DetailDAO;
import my.app.DAO.UserDAO;
import my.app.entities.Detail;
import my.app.entities.FriendRelationship;
import my.app.entities.User;
import my.app.utils.UUIDmaker;
import my.app.utils.UserUtil;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class LoginService {
    private String res;//微信服务器返回的openID等
    private User user;//用户信息
    private Detail detail;//详细信息
    private UserService userService;//用户服务

    public LoginService(){}

    public LoginService(String res){
        this.res = res;
    }


    public String sendCode(String code) {
        String url = "https://api.weixin.qq.com/sns/jscode2session";
        String appid = "wxf966ba14721f0435";
        String secret = "e3e703d5ed4c8d12a4dce3507318cdef";
        String jsCode = code;
        System.out.println(code);

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url + "?appid=" + appid + "&secret=" + secret + "&js_code=" + jsCode + "&grant_type=authorization_code");
        String res = null;
        try {
            HttpResponse response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();
                String result = EntityUtils.toString(entity);
                System.out.println(result);
                res = result;
                this.res = res;
            } else {
                System.out.println(response.getStatusLine().getStatusCode());
                res = "error : " + response.getStatusLine().getStatusCode();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * 根据openid初始化user并加入数据库
     *
     * @param openid
     */
    public void initUser(String openid) {
        User user = new User();
        user.setOpenid(openid);
        user.setName(UUIDmaker.getUUID());
        user.setMaskPath("/images/masks/mask1.png");
        user.setProfilePath("/images/profiles/profile1.png");
        user.setFackName("宇智波佐助");

        UserDAO.insertUser(user);
    }

    public void initDetail(int id){
        if (this.user != null) {
            Detail detail = new Detail();
            detail.setId(id);
            detail.setName(this.user.getName());

            DetailDAO.insertDetail(detail);
        }else{
            this.user = check(res);
            initDetail(this.user.getId());
        }
    }


    /**
     * 判断openID对应的用户是否存在
     *
     * @param openid
     * @return
     */
    public boolean isContainUser(String openid) {
        return getUserByOpenId(openid) != null;
    }

    /**
     * 根据openID获取用户
     *
     * @param openid
     * @return
     */
    public User getUserByOpenId(String openid) {
        return UserDAO.findUserByOpenid(openid);
    }

    public Detail getDetailById(int id){
        return DetailDAO.findDetailById(id);
    }
    /**
     * 根据小程序CAS服务器返回的数据
     * 查询数据库是否存在该用户
     * 不存在则创建
     * 返回该用户
     *
     * @param res
     */
    public User check(String res) {
        Map map = getMapFromJson(res);
        System.out.println(map);
        String openid = (String) map.get("openid");
        User user = getUserByOpenId(openid);

        if (user != null) {
            this.user = user;
        } else {
            initUser(openid);
            this.user = getUserByOpenId(openid);
        }

        this.userService = new UserService(this.user.getId());
        return this.user;
    }

    public User check(){
        return check(this.res);
    }

    public Detail checkDetail(){
        if (user == null){
            check(res);
        }

        Detail detail = getDetailById(user.getId());

        if (detail != null){
            this.detail = detail;
        }else{
            initDetail(user.getId());
            this.detail = getDetailById(user.getId());
        }
        return this.detail;
    }

    public Map getMapFromJson(String json) {

        Gson gson = new Gson();
        Map map = gson.fromJson(json, Map.class);
        return map;
    }


    /**
     * 获取要返回给请求的字符串
     *
     * @return
     */
    public String getUserReturn() {
        Map user = new HashMap();
        //基本信息
        user.put("id", UserUtil.formatId(this.user.getId()));
        user.put("name", this.user.getName());
        user.put("openId", this.user.getOpenid());
        user.put("codePath", this.user.getCodePath());
        user.put("motto", this.user.getMotto());
        user.put("maskPath", this.user.getMaskPath());
        user.put("profilePath", this.user.getProfilePath());
        user.put("fackName", this.user.getFackName());

        //详细信息
        user.put("detail",this.detail);
        user.put("friends",userService.getFriends());
        user.put("messages",userService.getMessages());
        String res = new Gson().toJson(user,Map.class);
        System.out.println(res);
        return res;
    }

    /**
     * 添加用户进行测试
     */
    public void test(){
        String openid = "1240";
        User user = new User();
        user.setOpenid(openid);
        user.setName("hacker");
        user.setMaskPath("/images/masks/mask1.png");
        user.setProfilePath("/images/profiles/profile5.png");
        user.setFackName("维克托");
        user.setMotto("听说你想被黑？");

        UserDAO.insertUser(user);

        user = getUserByOpenId(openid);

        Detail detail = new Detail();
        detail.setGender("男");
        detail.setName(user.getName());
        detail.setId(user.getId());
        detail.setProfession("无业游民");
        detail.setPosition("北京");
        detail.setSchool(null);

        DetailDAO.insertDetail(detail);

    }

    /**
     * 添加用户关系进行测试
     */
    public void test2(){

    }

    public static void main(String[] args) {
//        Gson gson = new Gson();
//
//        Map map = new HashMap();
//        Map map2 = new HashMap();
//
//        map.put("colour", "red");
//
//        map.put("weight", "10kg");
//
//        String mapJson = gson.toJson(map);
//
//        map2 = gson.fromJson(mapJson, Map.class);
//        System.out.println(map2);

        LoginService ls = new LoginService();
        System.out.println(ls.check("{\"session_key\":\"+bHVeBQHVTmuKwnpWKyIHw==\",\"openid\":\"oaf8I46loW-gu1cy3FC_nvI8n8Wo\"}"));
        System.out.println(ls.checkDetail());
        System.out.println(ls.getUserReturn());
//        ls.test();
        ls.test2();
    }
}
