
package model;


import dao.ADao;
import dto.AGroup;
import dto.AJoin;
import dto.AMember;
import dto.Board;
import dto.BoardComm;
import java.util.ArrayList;



public class Model {

     // ȸ������ 
    public int joinMemberMd(AMember mem) {
        return ADao.getDao().joinMember(mem);
    }
    
    // ȸ�����Խ� ID �ߺ� üũ
    public boolean chkUsedIdMd(String mid){
        return ADao.getDao().chkUsedId(mid);
    }
    
    // �α���
    public AMember loginMd( AMember vo,String id) {
       return ADao.getDao().login(vo,id);
    }
    // �α���id - pwd üũ
    public int checkIdMd(String id, String pwd){
        return ADao.getDao().checkId(id, pwd);
    }
    
    // �׷츸���
     public void addGroupMd(AMember ref , AGroup ref2){
         ADao.getDao().addGroup(ref, ref2);
     }
     
    // �׷츸��� �� �׷�� �ߺ� üũ
    public boolean chkUsedGroupNm(String gname){
        return ADao.getDao().chkUsedGroupNm(gname);
    }
     
    // ���������� �׷� ����Ʈ
    public ArrayList<AGroup> groupListMd(AMember ref) {
        return ADao.getDao().groupList(ref);
    }
       //���� �̸��˻��ϱ�
    public ArrayList<AGroup> detailGroup(String gname){
        return ADao.getDao().detailGroup(gname);
    }
    //���� ���Խ�û
    public void joinMd(AJoin aj){
        ADao.getDao().joinDelay(aj);
    }
    // ���� ��û ����
    public int ckJoinDelay(AJoin aj){
        return ADao.getDao().ckJoinDelay(aj);
    }
    // ���� ���翩��
    public int ckGroup(AGroup g){
        return ADao.getDao().ckGroup(g);
    }
    // ���� ���� ����
    public void acceptJoin(AJoin aj){
        ADao.getDao().acceptJoin(aj);
    }
    // ���� ��û�� �׷��ȣ ��������
    public AJoin getAplicantInfo(int mnum){
        return ADao.getDao().getAplicantInfo(mnum);
    }
    // ���� ���Խ�û �ź�
    public void denyJoin(AJoin aj){
        ADao.getDao().denyJoin(aj);
    }
    // ���� ��û�� ��� ����
    public ArrayList<AMember> getApplicantList(AGroup a){
        return ADao.getDao().getApplicantList(a);
    }
    // ������ ���� üũ�ϱ� 
    public int ckMoimjang(AJoin aj){
    return ADao.getDao().ckMoimjang(aj);
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

     //���� �Ұ��� ����
     public void changeGinfo(AGroup group){
         ADao.getDao().changeGinfo(group);
     }
     // �� ���� ����
     public void updateMyInfoMd(AMember ref, String mloc, int mhobby){
         ADao.getDao().updateMyInfo(ref, mloc, mhobby);
     }
     
     // ���� ������ ���� ����Ʈ �ҷ����� 
     public ArrayList<AJoin> listMyGroupMd(int num) {
         return ADao.getDao().listMyGroup(num);
     }
     // �Խñ� �Է�
     public void addBoardMd(Board b,AJoin aj){
         ADao.getDao().addBoard(b,aj);
     }
     // �Խ��� �� ��� ���
     public ArrayList<Board> listBoardMd(AGroup group){
         return ADao.getDao().listBoard(group);
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
       // ���� ������ ���� Ż���ϱ�
     public void deleteMyMoimMd(AJoin a){
         ADao.getDao().deleteMyMoim(a);
     }
     // ������ ��̰˻�
      public ArrayList<AGroup> selectGroupHobby(int num) {
        return  ADao.getDao().selectGroupHobby(num);
      }
      public int ckGJlList(AJoin aj){
          return ADao.getDao().ckGjlist(aj);
      }
      //���ӿ��� �߹�
      public int kickPpl(AJoin aj){
          return ADao.getDao().kickPpl(aj);
      }
}
