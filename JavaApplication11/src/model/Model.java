
package model;

<<<<<<< HEAD

=======
>>>>>>> e9a8a9e7f86c266649f0a421f6f206c812bd35be
import dao.ADao;
import dto.AGroup;
import dto.AJoin;
import dto.AMember;
import dto.Board;
import dto.BoardComm;
import java.util.ArrayList;



public class Model {

     // ȸ������ 
    public void joinMemberMd(AMember vo) {
        ADao.getDao().joinMember(vo);
    }
     //�α���
    public AMember loginMd( AMember vo,String id) {
       return ADao.getDao().login(vo,id);
    }
    //�α���id - pwd üũ
    public int checkIdMd(String id, String pwd){
        return ADao.getDao().checkId(id, pwd);
    }
    //�׷츸���
     public void addGroupMd(AMember ref , AGroup ref2){
         ADao.getDao().addGroup(ref, ref2);
     }
     // ���������� �׷� ����Ʈ
      public ArrayList<AGroup> groupListMd(AMember ref) {
          return ADao.getDao().groupList(ref);
      }
       //���� �̸��˻��ϱ�
    public ArrayList<AGroup> detailGroup(String gname){
        return ADao.getDao().detailGroup(gname);
    }
    //���� �����ϱ�
    public void joinMd(AMember lUInfo,AGroup conGroup){// loginUserInfo - iuinInfo // contected Group - conGroup
        ADao.getDao().join(lUInfo, conGroup);
    }
    //���� ��ȣ �Է��� ��ư Ŭ�� �� ������������ ���� 
    //�� ������  �������� Dao 
    public AGroup enterGroupMd(AGroup group){
        return ADao.getDao().EnterGroup(group);
    }
    
    //���� ������������ ���ӿ� ������ �ɹ��� ǥ���ϴ� ���̺�
     public ArrayList<AJoin> listGroupMd(int num){
         return ADao.getDao().listGroup(num);
     }
<<<<<<< HEAD
     // �� ���� ����
=======
     // �� ���� ���� (@author ����)
>>>>>>> e9a8a9e7f86c266649f0a421f6f206c812bd35be
     public void updateMyInfoMd(AMember ref, String mloc, int mhobby){
         ADao.getDao().updateMyInfo(ref, mloc, mhobby);
     }
     
<<<<<<< HEAD
     // ���� ������ ���� ����Ʈ �ҷ����� 
=======
     // ���� ������ ���� ����Ʈ �ҷ����� (@author ����)
>>>>>>> e9a8a9e7f86c266649f0a421f6f206c812bd35be
     public ArrayList<AJoin> listMyGroupMd(int num) {
         return ADao.getDao().listMyGroup(num);
     }
     // �Խñ� �Է�
     public void addBoardMd(Board b,AJoin aj){
         ADao.getDao().addBoard(b,aj);
     }
     // �Խ��� �� ��� ���
<<<<<<< HEAD
     public ArrayList<Board> listBoardMd(AGroup group){
         return ADao.getDao().listBoard(group);
=======
     public ArrayList<Board> listBoardMd(AGroup num){
         return ADao.getDao().listBoard(num);
>>>>>>> e9a8a9e7f86c266649f0a421f6f206c812bd35be
     }
     // �Խñ� �󼼳��� ���
     public Board getDetail(Board a) {
         return ADao.getDao().getDetailBoard(a);
     }
     
     //��� �ޱ�
       public void addCommMd(BoardComm bc,AJoin aj){
         ADao.getDao().addComment(bc,aj);
     }
     //��� ���
       public ArrayList<BoardComm> getDetailBoardCommMd(BoardComm bc){
           return ADao.getDao().getDetailBoardComm(bc);
       }
<<<<<<< HEAD
       // ���� ������ ���� Ż���ϱ�
     public void deleteMyMoimMd(AJoin a){
         ADao.getDao().deleteMyMoim(a);
     }
     // ������ ��̰˻�
      public ArrayList<AGroup> selectGroupHobby(int num) {
        return  ADao.getDao().selectGroupHobby(num);
      }
=======
>>>>>>> e9a8a9e7f86c266649f0a421f6f206c812bd35be
}
