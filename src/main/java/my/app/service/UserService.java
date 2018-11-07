package my.app.service;

import my.app.DAO.DetailDAO;
import my.app.DAO.UserDAO;
import my.app.entities.Detail;
import my.app.entities.FriendRelationship;
import my.app.entities.User;
import my.app.utils.UserUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class UserService {
    User user;
    Detail detail;
    String[] con = new String[]{"motto","name"};

    public UserService(int id){
        user = UserDAO.findUserById(id);
        detail = DetailDAO.findDetailById(id);
    }

    /**
     * @param flag 判断是否关联user表
     */
    private void update(User user, boolean flag){
        if (flag && (user.getMotto() != null || user.getName() != null)){
            Detail detail = new Detail();
            detail.setId(user.getId());
            detail.setMotto(user.getMotto());
            detail.setName(user.getName());
            updateDetail(detail,false);
        }

        if(UserDAO.updateUser(user) != 0){
            this.user = user;
        }
    }

    public void update(User user){
        update(user,true);
    }

    /**
     * @param detail
     * @param flag 判断是否关联user表
     */
    private void updateDetail(Detail detail, boolean flag){
        if(flag && (detail.getMotto() != null || detail.getName() != null)){
            User user = new User();
            user.setId(detail.getId());
            user.setMotto(detail.getMotto());
            user.setName(detail.getName());
            update(user,false);
        }

        if (DetailDAO.updateDetail(detail) != 0){
            this.detail = detail;
        }
    }

    public void updateDetail(Detail detail){
        updateDetail(detail,true);
    }

    public List<FriendRelationship> getRelationships(){
        int id = user.getId();
        RelationshipService rs = new RelationshipService(id);
        return rs.getRelationships();
    }

    public List getUsersInfoByRelationships(List<FriendRelationship> relationships){
        List<Map> res = new Vector<Map>();
        for (FriendRelationship fr : relationships){
            int id = fr.getFriend_id();//好友id

            User friend = UserDAO.findUserById(id);//好友

            Map map = new HashMap();//好友信息
            if ("friend".equals(fr.getType())) {//是否匿名
                Detail detail = DetailDAO.findDetailById(id);
                map.put("name",friend.getName());
                map.put("profile",friend.getProfilePath());
                map.put("type","friend");
                map.put("age",detail.getAge());
                map.put("star",detail.getStar());
            }else if("anonymity".equals(fr.getType())){
                map.put("name",friend.getFackName());
                map.put("profile",friend.getMaskPath());
                map.put("type","anonymity");
            }
            map.put("id", UserUtil.formatId(fr.getFriend_id()));
            map.put("motto",friend.getMotto());
            map.put("remark",fr.getRemark());
            map.put("intimacy",fr.getIntimacy());

            res.add(map);
        }

        return res;
    }

    public List getFriends(){
        return getUsersInfoByRelationships(getRelationships());
    }

    //处理返回消息
    public List getMessages(){
        List res = new Vector();



        return res;
    }

    public static void main(String[] args) {
//        UserService userService = new UserService(13);
//        User user = new User();
//        user.setId(userService.user.getId());
//        user.setName("hacker");
//        userService.update(user);
        UserService userService = new UserService(13);
        System.out.println(userService.user);
    }
}
