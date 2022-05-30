package dao;

import conn.TestConn;
import dto.AGroup;

import dto.AJoin;
import dto.AMember;
import dto.Board;
import dto.BoardComm;
import dto.Hobby;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.GroupLayout;
import javax.swing.JOptionPane;
import oracle.jdbc.OracleTypes;

public class ADao {

    private static ADao dao;

    private ADao() {
    }

    public static ADao getDao() {
        if (dao == null) {
            dao = new ADao();
        }
        return dao;
    }

    // ȸ������
    public int joinMember(AMember vo) {
        Connection con = null;
        PreparedStatement ps1 = null, ps2 = null;
        ResultSet rs = null;
        int i = 0;
        try {
            con = TestConn.getConn();
            String res = "select mid from amember where mid = ?";
            ps1 = con.prepareStatement(res);
            ps1.setString(1, vo.getMid());
            rs = ps1.executeQuery();
            while(rs.next()){
                if(rs.getString("mid").equals(vo.getMid())){
                    i = 1;
                }
            }
            if(i == 0){
            String query = "insert into AMember values(membernum_seq.nextVal,?,?,?,?,?,?,sysdate,usergender(?))";
            ps2 = con.prepareStatement(query);
            ps2.setString(1, vo.getMid());
            ps2.setString(2, vo.getMpwd());
            ps2.setString(3, vo.getMname());
            ps2.setString(4, vo.getMloc());
            ps2.setInt(5, vo.getMhobby());
            ps2.setString(6, vo.getMjumin());
            ps2.setString(7, vo.getMjumin());
            ps2.executeUpdate();
            System.out.println("���� �Ϸ�!");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps1 != null) ps1.close();
                if (ps2 != null) ps2.close();
                if (con != null) con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return i;
    }
    
    //�α���
    public AMember login(AMember vo, String id) {
        Connection con = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            con = TestConn.getConn();
            String query = "begin userprofile(?,?); end;";
            cs = con.prepareCall(query);
            cs.setString(1, id);
            cs.registerOutParameter(2, OracleTypes.CURSOR);
            cs.execute();
            rs = (ResultSet) cs.getObject(2);
            if (rs.next()) {
                vo.setMembernum(rs.getInt("membernum"));
                vo.setMid(rs.getString("mid"));
                vo.setMpwd(rs.getString("mpwd"));
                vo.setMname(rs.getString("mname"));
                vo.setMloc(rs.getString("mloc"));
                vo.setMjumin(rs.getString("mjumin"));
                vo.setMhobby(rs.getInt("mhobby"));
                vo.setMdate(rs.getString("mdate"));
                vo.setMjumin(rs.getString("gender"));
                Hobby h = new Hobby();
                h.setHname(rs.getString("hname"));
                vo.setHobby(h);
                vo.setJoindate(rs.getString("fj"));
            }
            System.out.println("�α��� ����");
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if(rs!=null) rs.close();
                if (cs != null) cs.close();
                if (con != null) con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return vo;
    }
    
    // �α��� �� ID �ߺ�üũ
    public boolean chkUsedId(String mid){
            Connection con = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
        try {
            con = TestConn.getConn();
            String sql = "select count(mid) cnt from amember where mid = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, mid);
            rs = ps.executeQuery();
            if (rs.next()) {
                 System.out.println(rs.getInt("cnt"));
                int cnt = rs.getInt("cnt");
                if(cnt > 0){
                    return true;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
                try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }

    //�α���id - pwd üũ
    public int checkId(String id, String pwd) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int i = 0;
        try {
            con = TestConn.getConn();
            String query = "select mpwd from AMember where mid = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                if (rs.getString("mpwd").equals(pwd)) {
                    System.out.println("�α��� ����");
                    return 1;
                } else {
                    System.out.println("���̵�� ��й�ȣ�� ����ġ�մϴ�");
                }
                return 0;
            }
            return -1;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return i;
    }

    public void addGroup(AMember ref, AGroup ref2) {
        Connection con = null;
        PreparedStatement ps1 = null, ps2 = null, ps3 = null;

        //�׷츸���
        String path = "insert into Agroup values(groupnum_seq.nextVal,?,?,?,?,sysdate)";
        String path2 = "insert into Ajoin values(?,groupnum_seq.currVal,sysdate,1)";
        try {
            con = TestConn.getConn();
            con.setAutoCommit(false);
            ps1 = con.prepareStatement(path);
            ps1.setString(1, ref2.getGname());
            ps1.setInt(2, ref.getMhobby());
            ps1.setString(3, ref.getMloc());
            ps1.setString(4, ref2.getGinfo());
            ps1.executeUpdate();
            ps2=con.prepareStatement(path2);
            ps2.setInt(1, ref.getMembernum());
            ps2.executeUpdate();
            System.out.println("�׷� ���� �Ϸ�!");
            con.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (ps2 != null) ps2.close();
                if (ps1 != null) ps1.close();
                if (con != null) con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
 
    public boolean chkUsedGroupNm(String gname){
            Connection con = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
        try {
            con = TestConn.getConn();
            String sql = "select count(gname) cnt from agroup where gname = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, gname);
            rs = ps.executeQuery();
             
             if (rs.next()) {
                 System.out.println(rs.getInt("cnt"));
                int cnt = rs.getInt("cnt");
                if(cnt > 0){
                    return true;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
                try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }
    
    //���������� ���� ����Ʈ
    public ArrayList<AGroup> groupList(AMember ref) {
        ArrayList<AGroup> alist = new ArrayList<>();
        Connection con = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            con = TestConn.getConn();
            String sql = "begin glist(?,?); end; ";
            cs = con.prepareCall(sql);
            cs.setInt(1, ref.getMhobby());
            cs.registerOutParameter(2, OracleTypes.CURSOR);
            cs.execute();
            rs = (ResultSet) cs.getObject(2);
            while(rs.next()){
                AGroup a = new AGroup();
                Hobby h = new Hobby();
                a.setGroupnum(rs.getInt("groupnum"));
                a.setGdate(rs.getNString("gdate"));
                a.setGhobby(rs.getInt("ghobby"));
                a.setGinfo(rs.getString("ginfo"));
                a.setGname(rs.getNString("gname"));
                a.setGloc(rs.getString("gloc"));
                h.setHname(rs.getString("hname"));
                a.setHobby(h);
                alist.add(a);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally{
            try {
                if(rs!=null) rs.close();
                if(cs!=null) cs.close();
                if(con!=null) con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return alist;
    }
    
    //���Ӱ˻��ϱ�
    public ArrayList<AGroup> detailGroup(String gname){
        ArrayList<AGroup> alist = new ArrayList<AGroup>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String path = "select g.gname,g.ghobby,g.gloc,g.groupnum,g.ginfo,h.hname from AGroup g, hobby h where g.ghobby = h.hobbynum and( gname like '%'||?||'%' or ginfo like '%'||?||'%' )";
        try {
            con = TestConn.getConn();
            ps = con.prepareStatement(path);
            ps.setString(1,gname);
            ps.setString(2, gname);
            rs = ps.executeQuery();
            while(rs.next()){
            AGroup a = new AGroup();
            Hobby b = new Hobby();
                a.setGname(rs.getString("gname"));
                a.setGhobby(rs.getInt("ghobby"));
                a.setGloc(rs.getString("gloc"));
                a.setGinfo(rs.getString("ginfo"));
                a.setGroupnum(rs.getInt("groupnum"));
                b.setHname(rs.getString("hname"));
                a.setHobby(b);
                alist.add(a);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            try{
                if(rs != null)rs.close();
                if(ps != null)ps.close();
                if(con != null)con.close();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
     return alist;
       
    }

        //���� ��ȣ �Է��� ��ư Ŭ�� �� ������������ ���� 
        //�� ������  �������� Dao 
        public AGroup EnterGroup(AGroup a) {
            
            Connection con = null;
            PreparedStatement ps = null; 
            ResultSet rs = null;
        try {
            con = TestConn.getConn();
            String sql = "select groupnum,gname,ginfo from agroup where groupnum = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, a.getGroupnum());
            rs = ps.executeQuery();
           
            if(rs.next()){
            a.setGroupnum(rs.getInt("groupnum"));
            a.setGname(rs.getString("gname"));
            a.setGinfo(rs.getString("ginfo"));
            }
                
        } catch (SQLException ex) {
          ex.printStackTrace();
        }finally{
            try {
                if(rs!=null) rs.close();
                if(ps!=null) ps.close();
                if(con!=null) con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return a;
        }
        
        // ���ӳ� ������ ��� ����
        public ArrayList<AJoin> listGroup(int num) {
           ArrayList<AJoin> alist = new ArrayList<>();
            
            Connection con = null;
            CallableStatement cs = null;
            ResultSet rs = null;
            String sql = "begin p1(?,?); end;";
        try {
            con = TestConn.getConn();
            cs = con.prepareCall(sql);
            cs.setInt(1, num);
            cs.registerOutParameter(2, OracleTypes.CURSOR);
            cs.execute();
            rs = (ResultSet) cs.getObject(2);
            while(rs.next()){
                AJoin ajoin = new AJoin();
                ajoin.setGroupnum(rs.getInt("groupnum"));
                ajoin.setMembernum(rs.getInt("membernum"));
                ajoin.setJdate(rs.getString("jdate"));
                
                AMember member = new AMember();
                member.setMname(rs.getString("mname"));
                member.setMgender(rs.getString("gender"));
                
                AGroup group = new AGroup();
                group.setGname(rs.getString("gname"));
                
                ajoin.setAmember(member);
                ajoin.setAgroup(group);
                alist.add(ajoin);
                //j.groupnum,m.mname,g.gname
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally{
            try {
                if(rs!=null) rs.close();
                if(cs!=null) cs.close();
                if(con!=null) con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
            return alist;
        } 
        
        // ���� ���� ����(���� �� ���)
        public void updateMyInfo(AMember ref, String mloc, int mhobby){
            Connection con = null;
            PreparedStatement ps = null; 
            CallableStatement cs = null;
            ResultSet rs = null;
        try {
            con = TestConn.getConn();
            con.setAutoCommit(false);
            String sql = "update amember set mloc = ?, mhobby = ? where mid = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, mloc);
            ps.setInt(2, mhobby);
            ps.setString(3, ref.getMid());
            ps.executeUpdate();
            String sql2 = "begin userprofile(?,?); end;";
            cs= con.prepareCall(sql2);
            cs.setString(1, ref.getMid());
            cs.registerOutParameter(2, OracleTypes.CURSOR);
            cs.execute();
            rs = (ResultSet) cs.getObject(2);
            if(rs.next()){
            Hobby h = new Hobby();
            h.setHname(rs.getString("hname"));
            ref.setJoindate(rs.getString("fj"));
            ref.setHobby(h);
            }
            // ���� ������ �α��� ������ ����
            ref.setMloc(mloc);
            ref.setMhobby(mhobby);
            con.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            try {
                if(rs!=null) rs.close();
                if(cs!=null) cs.close();
                if(ps!=null) ps.close();
                if(con!=null) con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        }
        
        // ���� ������ ���� ����Ʈ ��������
        public ArrayList<AJoin> listMyGroup(int num) {
            ArrayList<AJoin> alist = new ArrayList<>();
            Connection con = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            String sql = "select g.groupnum,g.gname, g.gloc, g.ghobby, j.jdate,h.hname from agroup g, ajoin j,hobby h where g.groupnum = j.groupnum and g.ghobby = h.hobbynum and j.membernum =?";
        try {
            con = TestConn.getConn();
            ps = con.prepareStatement(sql);
            ps.setInt(1, num);
            rs = ps.executeQuery();
            
            while(rs.next()){
                AJoin ajoin = new AJoin();
                ajoin.setJdate(rs.getString("jdate"));
                AGroup group = new AGroup();
                group.setGroupnum(rs.getInt("groupnum"));
                group.setGname(rs.getString("gname"));
                group.setGloc(rs.getString("gloc"));
                group.setGhobby(rs.getInt("ghobby"));
                Hobby h = new Hobby();
                h.setHname(rs.getString("hname"));
                group.setHobby(h);
                ajoin.setAgroup(group);
                alist.add(ajoin);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally{
            try {
                if(rs!=null) rs.close();
                if(ps!=null) ps.close();
                if(con!=null) con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
            return alist;
        } 

        // �Խñ� �ۼ� -����
        public void addBoard(Board b,AJoin aj){
            Connection con = null;
            PreparedStatement ps = null;
            String sql = "insert into board values(board_seq.nextVal,?,?,?,?,sysdate,getWlist(?))";
        try {
            con = TestConn.getConn();
            ps = con.prepareStatement(sql);
            ps.setInt(1, aj.getAmember().getMembernum());
            ps.setString(2, b.getSubject());
            ps.setString(3, b.getContent());
            ps.setInt(4, aj.getAgroup().getGroupnum() );
            ps.setInt(5, aj.getAgroup().getGroupnum());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally{
            try {
                if(ps != null)  ps.close();
                if(con != null) con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }   
        }
        
        // �Խñ� ��� - ����
        public ArrayList<Board> listBoard(AGroup group){
            Connection con = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            String sql ="select b.wlist, b.subject, a.mname, b.bdate from board b, amember a where a.membernum = b.membernum and b.groupnum = ? ";
            ArrayList<Board> arlist = new ArrayList<>();           
        try {
            con = TestConn.getConn();
            ps = con.prepareStatement(sql);
            ps.setInt(1, group.getGroupnum());
            rs = ps.executeQuery();       
            while(rs.next()){
                Board b = new Board();            
                b.setBoardnum(rs.getInt("wlist"));
                b.setSubject(rs.getString("subject"));
                b.setBdate(rs.getString("bdate"));
                AMember a = new AMember();
                a.setMname(rs.getString("mname"));
                b.setAmember(a);
                arlist.add(b);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally{
            try {
                if(rs != null) rs.close();
                if(ps != null)  ps.close();
                if(con != null) con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return arlist;
        }
        
    // �� �Խñ� ����
    public Board getDetailBoard(Board a) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = TestConn.getConn();
            String sql ="select b.boardnum,b.wlist,b.subject,a.mname,b.content from board b, amember a where b.membernum = a.membernum and b.wlist = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, a.getWlist());
            rs = ps.executeQuery();
           
            if (rs.next()) {
                a.setBoardnum(rs.getInt("boardnum"));
                a.setWlist(rs.getInt("wlist"));
                a.setSubject(rs.getString("subject"));
                AMember b = new AMember();
                b.setMname(rs.getString("mname"));
                a.setContent(rs.getString("content"));
                a.setAmember(b);
            }
            System.out.println("�α��� ����");
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return a;
    } 
    
    //�������ϱ� - ���ƹ�
     public ArrayList<BoardComm> getDetailBoardComm(BoardComm bc) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<BoardComm> alist = new ArrayList<>();
        try {
            con = TestConn.getConn();
            String sql ="select a.mname,bc.content ,bc.cdate from amember a , boardcomm bc where bc.membernum = a.membernum and bc.boardnum = ? order by cdate asc";
            ps = con.prepareStatement(sql);
            ps.setInt(1, bc.getBo().getBoardnum());
            rs = ps.executeQuery();
           
            while(rs.next()) {
                bc = new BoardComm();
                AMember b = new AMember();
                b.setMname(rs.getString("mname"));
                bc.setContent(rs.getString("content"));
                bc.setCdate(rs.getString("cdate"));
                bc.setAmember(b);
                alist.add(bc);
                System.out.println(bc.getContent());
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (rs != null){rs.close();}
                if (ps != null){ps.close();}
                if (con != null){con.close();}
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return alist;
    } 
    
    //��۳��������ϱ� - ���ƹ�
      public void addComment(BoardComm bc,AJoin aj){
            Connection con = null;
            PreparedStatement ps = null;
            String sql = "insert into boardcomm values(boardcomm_seq.nextVal,?,?,?,sysdate)";
        try {
            con = TestConn.getConn();
            ps = con.prepareStatement(sql);
            ps.setInt(1, bc.getBo().getBoardnum());
            ps.setInt(2, aj.getAmember().getMembernum());
            ps.setString(3, bc.getContent());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally{
            try {
                if(ps != null)  ps.close();
                if(con != null) con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
      }
      
      public void deleteMyMoim(AJoin a){
            //���� ������ ������ Ż��
            Connection con = null;
            PreparedStatement ps = null; 
            
        try {
            con = TestConn.getConn();
            String sql = "delete from ajoin where membernum = ? and groupnum = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, a.getMembernum());
            ps.setInt(2, a.getGroupnum());
            if(ps.executeUpdate() == 1){
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, a.getGroupnum()+"�� ������ Ż���Ͽ����ϴ�.", "�˸�â", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "������ ���ӹ�ȣ�� �ƴմϴ�.","�˸�â", JOptionPane.ERROR_MESSAGE);
            };
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            try {
                if(ps!=null) ps.close();
                if(con!=null) con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
      
      public ArrayList<AGroup> selectGroupHobby(int num) {
            //�˻��� ��̸� ���ɻ�� ������ ���Ӹ���Ʈ ��������
            ArrayList<AGroup> alist = new ArrayList<>();
            
            Connection con = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            String sql = "select g.groupnum, g.gname, g.ghobby, g.gloc,g.ginfo, h.hname from agroup g,hobby h  where  g.ghobby = h.hobbynum and ghobby = ?";
        try {
            con = TestConn.getConn();
            ps = con.prepareStatement(sql);
            ps.setInt(1, num);
            rs = ps.executeQuery();
            
            while(rs.next()){
                AGroup group = new AGroup();
                Hobby h = new Hobby();
                group.setGroupnum(rs.getInt("groupnum"));
                group.setGname(rs.getString("gname"));
                group.setGinfo(rs.getString("ginfo"));
                group.setGhobby(rs.getInt("ghobby"));
                group.setGloc(rs.getString("gloc"));
                h.setHname(rs.getString("hname"));
                group.setHobby(h);
                alist.add(group);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally{
            try {
                if(rs!=null) rs.close();
                if(ps!=null) ps.close();
                if(con!=null) con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
            return alist;
        }
      
    // ���� ����Ʈ
    public int ckGjlist(AJoin aj){
          Connection con = null;
          CallableStatement cs = null;
          ResultSet rs = null;
          String sql = "begin ckgjlist(?,?); end; ";
        try {
            con = TestConn.getConn();
            cs = con.prepareCall(sql);
            cs.setInt(1, aj.getAgroup().getGroupnum());
            cs.registerOutParameter(2, OracleTypes.CURSOR);
            cs.execute();
            rs=(ResultSet) cs.getObject(2);
            while(rs.next()){
                if(rs.getInt("membernum")==aj.getAmember().getMembernum()){
                    return 1;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally{
              try {
                  if(rs != null) rs.close();
                  if(cs != null) cs.close();
                  if(con != null) con.close();
              } catch (SQLException ex) {
                  ex.printStackTrace();
              }
        }
          return 0;
      }
      
      // ���� ��û�ߴ��� ����
      public int ckJoinDelay(AJoin aj){
        Connection con = null;
        CallableStatement cs = null, cs2 =null;
        ResultSet rs = null;
        try {
            con = TestConn.getConn();
            String sql = "begin ckJoinDelay(?,?); end;";
            cs = con.prepareCall(sql);
            cs.setInt(1, aj.getAgroup().getGroupnum());
            cs.registerOutParameter(2, OracleTypes.CURSOR);
            cs.execute();
            rs = (ResultSet) cs.getObject(2);
            while(rs.next()){
                if(rs.getInt("membernum")==aj.getAmember().getMembernum()){
                return 0;
                }
            }
            String sql2 = "begin ckJoinDelay2(?,?); end;";      
            cs2 = con.prepareCall(sql2);
            cs2.setInt(1, aj.getAgroup().getGroupnum());
            cs2.registerOutParameter(2, OracleTypes.CURSOR);
            cs2.execute();
            rs = (ResultSet) cs2.getObject(2);
            while(rs.next()){
                if(rs.getInt("membernum")==aj.getAmember().getMembernum()){
                return -1;
                }    
            }
        } catch (SQLException ex) {
            try {
                con.rollback();
                ex.printStackTrace();
            } catch (SQLException ex1) {
                ex1.printStackTrace();
            }
            
        } finally{
            try {
                if(rs!=null) rs.close();
                if(cs!=null) cs.close();
                if(con!=null) con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            
        }          
         return 1;
      }
      
      //���Խ�û
      public void joinDelay(AJoin aj){
          Connection con = null;
          CallableStatement cs = null;
          String sql= "begin join_pkg.joindelay(?,?); end;";
        try {
            con = TestConn.getConn();
            cs = con.prepareCall(sql);
            cs.setInt(1, aj.getAmember().getMembernum());
            cs.setInt(2, aj.getAgroup().getGroupnum());
            cs.execute();
        } catch (SQLException ex) {
              try {
                  con.rollback();
              } catch (SQLException ex1) {
                  ex1.printStackTrace();
              }
                ex.printStackTrace();
        } finally{
            try{
                if(cs != null)cs.close();
                if(con != null)con.close();
           }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
    }
      
    //���Լ���  
    public void acceptJoin(AJoin aj) { // loginUserInfo - iuinInfo // contected Group - conGroup
        Connection con = null;
        CallableStatement cs = null;
        String path = "begin join_pkg.accetjoin(?,?); end; ";
        try {
            con = TestConn.getConn(); 
            cs = con.prepareCall(path);
            cs.setInt(1, aj.getAmember().getMembernum());
            cs.setInt(2, aj.getAgroup().getGroupnum());
            cs.executeUpdate();
        } catch (SQLException ex) {
           try {
                ex.printStackTrace();
                con.rollback();
            } catch (SQLException ex1) {
                ex1.printStackTrace();
            }
        }finally{
            try{
                if(cs != null)cs.close();
                if(con != null)con.close();
           }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
    }
    
    // ��û�� ���� ������
    public AJoin getAplicantInfo(int mnum){
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        AJoin aj = new AJoin();
        try {
            con = TestConn.getConn();
            String sql = "select membernum, groupnum from ajoin_delay where membernum = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, mnum);
            rs = ps.executeQuery();
            if(rs.next()){
            AMember mem = new AMember();
            AGroup a = new AGroup();
            mem.setMembernum(rs.getInt("membernum"));
            a.setGroupnum(rs.getInt("groupnum"));
            aj.setAmember(mem);
            aj.setAgroup(a);
            }
        } catch (SQLException ex) {
            try {
                con.rollback();
                ex.printStackTrace();
            } catch (SQLException ex1) {
                 ex1.printStackTrace();
            }
            
        } finally{
            try {
                if(rs!=null) rs.close();
                if(ps!=null) ps.close();
                if(con!=null) con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            
        }
        return aj;
    }
    
    //�׷� ���翩��
    public int ckGroup(AGroup g){
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = TestConn.getConn();
            String sql = "select groupnum from gview";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                if(rs.getInt("groupnum")==g.getGroupnum()){
                return 1;
                }
            }
        } catch (SQLException ex) {
            try {
                con.rollback();
                ex.printStackTrace();
            } catch (SQLException ex1) {
                 ex1.printStackTrace();
            }
            
        } finally{
            try {
                if(rs!=null) rs.close();
                if(ps!=null) ps.close();
                if(con!=null) con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            
        }
        return 0;
    
    }
    
    //���� ��û ����
    public void denyJoin(AJoin aj){
        Connection con = null;
        CallableStatement cs = null;
        String path = "begin join_pkg.denyjoin(?,?); end; ";
        try {
            con = TestConn.getConn(); 
            cs = con.prepareCall(path);
            cs.setInt(1, aj.getAmember().getMembernum());
            cs.setInt(2, aj.getAgroup().getGroupnum());
            cs.executeUpdate();
        } catch (SQLException ex) {
           try {
                 ex.printStackTrace();
                con.rollback();
            } catch (SQLException ex1) {
                ex1.printStackTrace();
            }
        }finally{
            try{
                if(cs != null)cs.close();
                if(con != null)con.close();
           }catch(SQLException ex){
                ex.printStackTrace();
            }
        }    
    }
    
    // ������ ���� üũ
    public int ckMoimjang(AJoin aj){
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = TestConn.getConn();
            String sql = "select membernum from ajoin where joinmember =1 and groupnum = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, aj.getAgroup().getGroupnum());
            rs = ps.executeQuery();
            if(rs.next()){
                if(rs.getInt("membernum")==aj.getAmember().getMembernum()){
                return 1;
                }
            }
        } catch (SQLException ex) {
            try {
                con.rollback();
                ex.printStackTrace();
            } catch (SQLException ex1) {
                 ex1.printStackTrace();
            }
            
        } finally{
            try {
                if(rs!=null) rs.close();
                if(ps!=null) ps.close();
                if(con!=null) con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            
        }
    return 0;
    }
    
    // ��û�� ���
    public ArrayList<AMember> getApplicantList(AGroup a){
        ArrayList<AMember> alist = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = TestConn.getConn();
            String sql = "select m.membernum,m.mid,m.mname,getage(m.mjumin) age,m.gender,m.mloc from amember m, ajoin_delay d where m.membernum = d.membernum and d.groupnum = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, a.getGroupnum());
            rs = ps.executeQuery();
            while(rs.next()){
            AMember mem = new AMember();
            mem.setMembernum(rs.getInt("membernum"));
            mem.setMid(rs.getString("mid"));
            mem.setMname(rs.getString("mname"));
            mem.setAge(rs.getInt("age"));
            mem.setMgender(rs.getString("gender"));
            mem.setMloc(rs.getString("mloc"));
            alist.add(mem);
            }
        } catch (SQLException ex) {
            try {
                con.rollback();
                ex.printStackTrace();
            } catch (SQLException ex1) {
                 ex1.printStackTrace();
            }
            
        } finally{
            try {
                if(rs!=null) rs.close();
                if(ps!=null) ps.close();
                if(con!=null) con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return alist;
    }
    
    // ���ӿ��� �߹�
     public int kickPpl(AJoin aj){
        Connection con = null;
        PreparedStatement ps = null, ps2 =null;
        ResultSet rs = null;
        try {
            con = TestConn.getConn();
            String sql = "select membernum from ajoin where groupnum = ? and joinmember = 1";
            ps = con.prepareStatement(sql);
            ps.setInt(1, aj.getAgroup().getGroupnum());
            rs = ps.executeQuery();
            int a = 0;
            while(rs.next()){
                if(rs.getInt("membernum") == aj.getAmember().getMembernum()){
                    a=1;
                }
            }
            if(a==0){
            String sql2 = "delete from ajoin where groupnum = ? and membernum = ?";
            ps2 = con.prepareStatement(sql2);
            ps2.setInt(1, aj.getAgroup().getGroupnum());
            ps2.setInt(2, aj.getAmember().getMembernum());
            ps2.executeUpdate();
            return 1;
            }
        } catch (SQLException ex) {
            try {
                con.rollback();
                ex.printStackTrace();
            } catch (SQLException ex1) {
                 ex1.printStackTrace();
            }
        } finally{
            try {
                if(ps!=null) ps.close();
                if(con!=null) con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return 0;
    }
    
    // ���� �Ұ��� ����
    public void changeGinfo(AGroup g){
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = TestConn.getConn();
            String sql = "update agroup set ginfo = ? where groupnum = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(2, g.getGroupnum());
            ps.setString(1, g.getGinfo());
            ps.executeUpdate();
        } catch (SQLException ex) {
            try {
                con.rollback();
                ex.printStackTrace();
            } catch (SQLException ex1) {
                 ex1.printStackTrace();
            }
        } finally{
            try {
                if(ps!=null) ps.close();
                if(con!=null) con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}

    

