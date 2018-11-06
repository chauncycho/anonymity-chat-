package my.app.entities;

public class FriendRelationship {
    Integer user_id;
    Integer friend_id;
    Integer intimacy;
    String type;
    String remark;

    @Override
    public String toString() {
        return "FriendRelationship{" +
                "user_id=" + user_id +
                ", friend_id=" + friend_id +
                ", intimacy=" + intimacy +
                ", type='" + type + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getFriend_id() {
        return friend_id;
    }

    public void setFriend_id(Integer friend_id) {
        this.friend_id = friend_id;
    }

    public Integer getIntimacy() {
        return intimacy;
    }

    public void setIntimacy(Integer intimacy) {
        this.intimacy = intimacy;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
