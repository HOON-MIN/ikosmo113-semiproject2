package dao;

import conn.TestConn;
import dto.AGroup;

import dto.AJoin;
import dto.AMember;
import dto.Board;
import dto.BoardComm;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    // 회원가입 
    // 회원가입 
    public void joinMember(AMember vo) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String res = null;
        
        try {
            con = TestConn.getConn();
            String query = "insert into AMember values(membernum_seq.nextVal,?,?,?,?,?,?,sysdate,usergender(?))";
            ps = con.prepareStatement(query);
            ps.setString(1, vo.getMid());
            ps.setString(2, vo.getMpwd());
            ps.setString(3, vo.getMname());
            ps.setString(4, vo.getMloc());
            ps.setInt(5, vo.getMhobby());
            ps.setString(6, vo.getMjumin());
            ps.setString(7, vo.getMjumin());
            ps.executeUpdate();
            System.out.println("가입 완료!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    //로그인
    public AMember login(AMember vo, String id) {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {

            con = TestConn.getConn();
            String query = "select membernum,mid,mpwd,mname,mloc,mjumin,mhobby,mdate,usergender(mjumin) as gender from AMember where mid = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();

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
            }
            System.out.println("로그인 성공");
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {

            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ADao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return vo;
    }

    //로그인id - pwd 체크
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

                    System.out.println("로그인 성공");
                    return 1;
                } else {
                    System.out.println("아이디와 비밀번호가 불일치합니다");
                }
                return 0;
            }
            return -1;

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {

            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return i;
    }

    public void addGroup(AMember ref, AGroup ref2) {
        Connection con = null;
        
        PreparedStatement ps1 = null, ps2 = null, ps3=null;

        //그룹만들기
        String path = "insert into Agroup values(groupnum_seq.nextVal,?,?,?,?,sysdate)";
        String path2 = "insert into Ajoin values(?,groupnum_seq.currVal,sysdate,1)";
        String path3 = "insert into chat values(groupnum_seq.currVal,sysdate)";
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
            ps3 = con.prepareStatement(path3);
            ps3.executeUpdate();
            System.out.println("그룹 생성 완료!");
            con.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (ps2 != null) {
                    ps2.close();
                }
                if (ps1 != null) {
                    ps1.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
 
         
    //메인페이지 모임 리스트
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
                a.setGroupnum(rs.getInt("groupnum"));
                a.setGdate(rs.getNString("gdate"));
                a.setGhobby(rs.getInt("ghobby"));
                a.setGinfo(rs.getString("ginfo"));
                a.setGname(rs.getNString("gname"));
                a.setGloc(rs.getString("gloc"));
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
                Logger.getLogger(ADao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return alist;
    }
    //모임검색하기
    public ArrayList<AGroup> detailGroup(String gname){
        
        ArrayList<AGroup> alist = new ArrayList<AGroup>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String path = "select gname,ghobby,gloc,groupnum from AGroup where gname like '%'||?||'%' ";
        
        try {
            con = TestConn.getConn();
            ps = con.prepareStatement(path);
            ps.setString(1,gname);
            rs = ps.executeQuery();
            while(rs.next()){
             AGroup a = new AGroup();
                a.setGname(rs.getString("gname"));
                a.setGhobby(rs.getInt("ghobby"));
                a.setGloc(rs.getString("gloc"));
                a.setGroupnum(rs.getInt("groupnum"));
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
    //모임가입
    public void join(AMember mem,AGroup group) { // loginUserInfo - iuinInfo // contected Group - conGroup
        Connection con = null;
        PreparedStatement ps = null;
        String path = "insert into aJoin values(?,?,sysdate,f10(?))";
        try {
            con = TestConn.getConn(); 
            ps = con.prepareStatement(path);
            ps.setInt(1, mem.getMembernum());
            ps.setInt(2, group.getGroupnum());
            ps.setInt(3, group.getGroupnum());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            try{
                if(ps != null)ps.close();
                if(con != null)con.close();
                    
                
            }catch(SQLException ex){
                ex.printStackTrace();
                        
            }
        }
    }
        //모임 번호 입력후 버튼 클릭 후 모임페이지에 대한 
        //상세 정보를  가져오는 Dao 
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
        // 모임내 가입한 멤버 내역
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
                ajoin.setGroupnum(rs.getInt("membernum"));
                ajoin.setMembernum(rs.getInt("groupnum"));
                ajoin.setJdate(rs.getString("jdate"));
               
                AMember member = new AMember();
                member.setMname(rs.getString("mname"));
                
                
                AGroup group = new AGroup();
                group.setGname(rs.getString("gname"));
                
                ajoin.setAmember(member);
                ajoin.setAgroup(group);
                alist.add(ajoin);
                
                //j.groupnum,m.mname,g.gname
            }
        } catch (SQLException ex) {
            Logger.getLogger(ADao.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                if(rs!=null) rs.close();
                if(cs!=null) cs.close();
                if(con!=null) con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ADao.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
            return alist;
        } 
        // 나의 정보 수정(지역 및 취미)
        public void updateMyInfo(AMember ref, String mloc, int mhobby){
            Connection con = null;
            PreparedStatement ps = null; 
            
        try {
            con = TestConn.getConn();
            String sql = "update amember set mloc = ?, mhobby = ? where mid = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, mloc);
            ps.setInt(2, mhobby);
            ps.setString(3, ref.getMid());
            ps.executeUpdate();
            // 현재 세션의 로그인 정보도 수정
            ref.setMloc(mloc);
            ref.setMhobby(mhobby);
            
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
        // 내가 가입한 모임 리스트 가져오기
        public ArrayList<AJoin> listMyGroup(int num) {
           ArrayList<AJoin> alist = new ArrayList<>();
            
            Connection con = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            String sql = "select g.gname, g.gloc, g.ghobby, j.jdate from agroup g, ajoin j where g.groupnum = j.groupnum and j.membernum =?";
        try {
            con = TestConn.getConn();
            ps = con.prepareStatement(sql);
            ps.setInt(1, num);
            rs = ps.executeQuery();
            
            while(rs.next()){
                AJoin ajoin = new AJoin();
                ajoin.setJdate(rs.getString("jdate"));

                AGroup group = new AGroup();
                group.setGname(rs.getString("gname"));
                group.setGloc(rs.getString("gloc"));
                group.setGhobby(rs.getInt("ghobby"));
                
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
        // 게시글 작성 -병렬
        public void addBoard(Board b,AJoin aj){
            Connection con = null;
            PreparedStatement ps = null;
            String sql = "insert into board values(board_seq.nextVal,?,?,?,?,sysdate,f11(?))";
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
        // 게시글 출력 - 병렬
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
    // 상세 게시글 보기
    public Board getDetailBoard(Board a) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = TestConn.getConn();
            String sql ="select b.wlist,b.subject,a.mname,b.content from board b, amember a where b.membernum = a.membernum and b.boardnum = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, a.getBoardnum());
            rs = ps.executeQuery();
           
            if (rs.next()) {
                a.setBoardnum(rs.getInt("wlist"));
                a.setSubject(rs.getString("subject"));
                AMember b = new AMember();
                b.setMname(rs.getString("mname"));
                a.setContent(rs.getString("content"));
                a.setAmember(b);
            }
            System.out.println("로그인 성공");
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {

            try {
                if (rs != null){
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return a;
    } 
    //댓글출력하기 - 김훈민
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
    
    
    
    //댓글내용저장하기 - 김훈민
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
            //내가 가입한 모임을 탈퇴
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
                JOptionPane.showMessageDialog(null, a.getGroupnum()+"번 모임을 탈퇴하였습니다.", "알림창", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "가입한 모임번호가 아닙니다.","알림창", JOptionPane.ERROR_MESSAGE);
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
            //검색한 취미를 관심사로 가지는 모임리스트 가져오기
            ArrayList<AGroup> alist = new ArrayList<>();
            
            Connection con = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            String sql = "select groupnum, gname, ghobby, gloc from agroup  where ghobby = ?";
        try {
            con = TestConn.getConn();
            ps = con.prepareStatement(sql);
            ps.setInt(1, num);
            rs = ps.executeQuery();
            
            while(rs.next()){
                AGroup group = new AGroup();
                group.setGroupnum(rs.getInt("groupnum"));
                group.setGname(rs.getString("gname"));
                group.setGhobby(rs.getInt("ghobby"));
                group.setGloc(rs.getString("gloc"));

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
}

    

