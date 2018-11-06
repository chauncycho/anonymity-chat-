package my.app.service;

import my.app.DAO.FriendRelationshipDAO;
import my.app.entities.FriendRelationship;

public class RelationshipService {
    private int id;

    public RelationshipService(int id){
        this.id = id;
    }

    public boolean insertRelationship(Integer target_id,Integer intimacy, String type, String remark){
        FriendRelationship fr = new FriendRelationship();
        fr.setUser_id(id);
        fr.setFriend_id(target_id);
        fr.setIntimacy(intimacy);
        fr.setType(type);
        fr.setRemark(remark);

        FriendRelationshipDAO.insertRelationship(fr);

        return true;//返回判断有空再改
    }

    public boolean setRelationship(Integer target_id,Integer intimacy, String type, String remark){

        return true;
    }
}
