package my.app.entities;

import java.util.Date;

public class Message {
    private int user_id;
    private int target_id;
    private String type;
    private String value;
    private Date time;

    @Override
    public String toString() {
        return "Message{" +
                "user_id=" + user_id +
                ", target_id=" + target_id +
                ", type='" + type + '\'' +
                ", value='" + value + '\'' +
                ", time=" + time +
                '}';
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getTarget_id() {
        return target_id;
    }

    public void setTarget_id(int target_id) {
        this.target_id = target_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
