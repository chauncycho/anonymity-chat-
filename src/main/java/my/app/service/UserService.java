package my.app.service;

import my.app.DAO.DetailDAO;
import my.app.DAO.UserDAO;
import my.app.entities.Detail;
import my.app.entities.User;

public class UserService {
    User user;
    Detail detail;
    String[] con = new String[]{"motto","name"};

    public UserService(int id){
        user = UserDAO.findUserById(id);
        detail = DetailDAO.findDetailById(id);
    }

    /**
     * @param detail
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

    public static void main(String[] args) {
        UserService userService = new UserService(13);
        User user = new User();
        user.setId(userService.user.getId());
        user.setName("hacker");
        userService.update(user);
    }
}
