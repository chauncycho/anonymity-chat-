package my.app.service;

import my.app.DAO.FriendRelationshipDAO;
import my.app.entities.FriendRelationship;

import java.util.List;

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

    public List<FriendRelationship> getRelationships(){
        return FriendRelationshipDAO.findFriendRelationshipByUserid(id);
    }

    public boolean setRelationship(Integer target_id,Integer intimacy, String type, String remark){
        FriendRelationship fr = new FriendRelationship();
        fr.setUser_id(id);
        fr.setFriend_id(target_id);
        fr.setIntimacy(intimacy);
        fr.setType(type);
        fr.setRemark(remark);

        FriendRelationshipDAO.updateRelationship(fr);
        return true;
    }

    //test
    public static void main(String[] args) {
        int id1 = 5;//我
        int id2 = 13;//对方

        RelationshipService rs1 = new RelationshipService(id1);
        rs1.insertRelationship(id2,233,"anonymity",null);

        RelationshipService rs2 = new RelationshipService(id2);
        rs2.insertRelationship(id1,233,"anonymity",null);


        System.out.println(rs1.getRelationships());
        System.out.println(rs2.getRelationships());
    }
}
