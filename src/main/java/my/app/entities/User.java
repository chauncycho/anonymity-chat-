package my.app.entities;

public class User {
    private Integer id;
    private String name;
    private String openid;
    private String codePath;
    private String motto;
    private String maskPath;
    private String profilePath;
    private String fackName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getCodePath() {
        return codePath;
    }

    public void setCodePath(String codePath) {
        this.codePath = codePath;
    }

    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto = motto;
    }

    public String getMaskPath() {
        return maskPath;
    }

    public void setMaskPath(String maskPath) {
        this.maskPath = maskPath;
    }

    public String getProfilePath() {
        return profilePath;
    }

    public void setProfilePath(String profilePath) {
        this.profilePath = profilePath;
    }

    public String getFackName() {
        return fackName;
    }

    public void setFackName(String fackName) {
        this.fackName = fackName;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", openid='" + openid + '\'' +
                ", codePath='" + codePath + '\'' +
                ", motto='" + motto + '\'' +
                ", maskPath='" + maskPath + '\'' +
                ", profilePath='" + profilePath + '\'' +
                ", fackName='" + fackName + '\'' +
                '}';
    }
}
