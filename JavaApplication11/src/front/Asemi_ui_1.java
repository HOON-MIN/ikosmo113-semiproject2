package front;
//�ٽ��ѹ� �ǽ��غ��ϵ�
import dto.AGroup;
import dto.AJoin;
import model.Model;
import dto.AMember;
import dto.Board;
import dto.BoardComm;
import java.awt.CardLayout;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Asemi_ui_1 extends javax.swing.JFrame {
    private CardLayout card;
    private Model md;
    private AMember mem;
    private AGroup group;
    private Board board;
    private Socket s;
    private PrintWriter pw;
    private int hobbynum;
    
    public Asemi_ui_1() {
        initComponents();
        card = (CardLayout) pp.getLayout();
        md = new Model();
    }
    //ä�� ����
    public void chatConn(){
            
        try {
            s= new Socket("localhost",9999);
            pw = new PrintWriter(s.getOutputStream(), true);
            pw.println("�ٺ�/"+group.getGroupnum()+"/�ٺ�/�ٺ�");
             new Thread(new Runnable() {
                BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
                @Override
                public void run() {
                    while(true){
                        try {
                            String msg = br.readLine();
                            System.out.println(msg);
                            chatScreen.append(msg+"\n");
                        } catch (IOException ex) {
                            Logger.getLogger(Asemi_ui_1.class.getName()).log(Level.SEVERE, null, ex);
                        }
                      
                        
                    }
                }
            }).start();
            card.show(pp, "c7");
                 
        } catch (IOException ex) {
            Logger.getLogger(Asemi_ui_1.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    //���������� �׷� ����Ʈ jtable �޼���
    public void groupList(ArrayList<AGroup> b) {
        ArrayList<AGroup> a = b;
        String[] columnNames = {"���ӹ�ȣ", "�̸�","���","����"};
        Object[][] data = new Object[a.size()][];
        int i = 0;
        for(AGroup e : a){
            data[i] = new Object[4];
            data[i][0] = e.getGroupnum();
            data[i][1] = e.getGname();
            data[i][2] = e.getGhobby();
            data[i][3] = e.getGloc();
            i++;
        }
        MyTable mt = new MyTable(columnNames, data);
        mainPrintTable.setModel(mt);
    }
    //���ӿ� ���Ե� ȸ�� ���� �� ����Ʈ jtable �޼���
         public void groupdetailList(ArrayList<AJoin> b) {
        ArrayList<AJoin> a = b;
        String[] columnNames = {"�̸�", "����"};
        Object[][] data = new Object[a.size()][];
        int i = 0;
        for(AJoin e : a){
            data[i] = new Object[2];
            data[i][0] = e.getAmember().getMname();
            data[i][1] = e.getAgroup().getGdate();
           
            i++;
        }
        MyTable mt = new MyTable(columnNames, data);
        moimlist.setModel(mt);
    }
         
         // ���� ������ ���� ����Ʈ ǥ�� ���
        public void myGroupList(ArrayList<AJoin> b) {
        ArrayList<AJoin> a = b;
        String[] columnNames = {"���� �̸�", "���� ����", "��� ��ȣ", "���� ����"};
        Object[][] data = new Object[a.size()][];
        int i = 0;
        for(AJoin e : a){
            data[i] = new Object[4];
            data[i][0] = e.getAgroup().getGname();
            data[i][1] = e.getAgroup().getGloc();
            data[i][2] = e.getAgroup().getGhobby();
            data[i][3] = e.getJdate();
            i++;
        }
        MyTable mt = new MyTable(columnNames, data);
        mymoimList_table.setModel(mt);
    }
        
        // jtable - �Խ��� �� ��� ���̺�
        public void listContent(){
            
        ArrayList<Board> list = md.listBoardMd(group);
        String[] cname = {"�Խñ۹�ȣ","����","�ۼ���","��¥"};
        Object[][] data = new Object[list.size()][];
        int i =0;
        for(Board e : list){
            data[i] = new Object[4];
            data[i][0] = e.getBoardnum();
            data[i][1] = e.getSubject();
            data[i][2] = e.getAmember().getMname();
            data[i][3] = e.getBdate();
            i++;
        }
        MyTable mt = new MyTable(cname, data);
        board_Table.setModel(mt);
        }
        
        //p9 - ��� ��� ���
        public void printCommentlist(BoardComm bc){
            
            ArrayList<BoardComm> alist = md.getDetailBoardCommMd(bc);
            StringBuilder sb = new StringBuilder();
            String res = "";
            for (BoardComm e : alist) {
                 sb.append(e.getAmember().getMname()).append("���� ��� : ").append(e.getContent()).append("\n");  
                 sb.append("------------------------------------------------\n");
           }
        res = sb.toString();
        comment_area.setText(res);
        }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jLabel7 = new javax.swing.JLabel();
        pp = new javax.swing.JPanel();
        p1_login = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        log_id = new javax.swing.JTextField();
        log_pwd = new javax.swing.JTextField();
        loginBtn = new javax.swing.JButton();
        joinBtn = new javax.swing.JButton();
        p2_adduser = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        prevBtn = new javax.swing.JButton();
        nextBtn = new javax.swing.JButton();
        join_pwd = new javax.swing.JTextField();
        join_id = new javax.swing.JTextField();
        join_locCom = new javax.swing.JComboBox<>();
        join_hobbyCom = new javax.swing.JComboBox<>();
        join_name1 = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jumin_front = new javax.swing.JTextField();
        jumin_behind = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        p3_createmoim = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        cmoim_name = new javax.swing.JTextField();
        cmoim_info = new javax.swing.JTextField();
        cmoimBtn = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel21 = new javax.swing.JLabel();
        main_page_Btn2 = new javax.swing.JButton();
        p4_mainpanal = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        searchword = new javax.swing.JTextField();
        searchBtn = new javax.swing.JButton();
        moim_crea_Btn = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        mainPrintTable = new javax.swing.JTable();
        tomainPanalBtn = new javax.swing.JButton();
        tomymoimPanalBtn = new javax.swing.JButton();
        hobby1Btn = new javax.swing.JButton();
        hobby2Btn = new javax.swing.JButton();
        hobby3Btn = new javax.swing.JButton();
        hobby4Byn = new javax.swing.JButton();
        hobby5Btn = new javax.swing.JButton();
        logout_btn = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        searchnum = new javax.swing.JTextField();
        moim_detail_Btn = new javax.swing.JButton();
        p5_moimpage = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        moim_join_Btn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        moim_info = new javax.swing.JTextArea();
        mainpageBtn = new javax.swing.JButton();
        moimTitle = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        moimlist = new javax.swing.JTable();
        tochatingPanalBtn = new javax.swing.JButton();
        tomoiminfoBtn = new javax.swing.JButton();
        toboardpanalBtn = new javax.swing.JButton();
        p6_boardPanal = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        board_Table = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        search_detail_board = new javax.swing.JTextField();
        detail_board_show_Btn = new javax.swing.JButton();
        create_board_Btn = new javax.swing.JButton();
        board_pageBtn1 = new javax.swing.JButton();
        tochatingPanalBtn1 = new javax.swing.JButton();
        tomoiminfoBtn1 = new javax.swing.JButton();
        toboardpanalBtn1 = new javax.swing.JButton();
        p7_chatingPanal = new javax.swing.JPanel();
        tochatingPanalBtn2 = new javax.swing.JButton();
        tomoiminfoBtn2 = new javax.swing.JButton();
        toboardpanalBtn2 = new javax.swing.JButton();
        talkBar = new javax.swing.JTextField();
        send_Btn = new javax.swing.JButton();
        chating_pageBtn2 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        chatScreen = new javax.swing.JTextArea();
        p8_createdocument_Panal = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        addBoardBtn = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        glename = new javax.swing.JTextField();
        gle = new javax.swing.JTextField();
        p9_detailGlePanal = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        board_content = new javax.swing.JTextArea();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        content_name = new javax.swing.JLabel();
        board_writer_name = new javax.swing.JLabel();
        to_moimBtn = new javax.swing.JButton();
        comment_v = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        comment_area = new javax.swing.JTextArea();
        p10_mypagePanel = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        myname_v = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        myloc_v = new javax.swing.JLabel();
        update_loc_combobox = new javax.swing.JComboBox<>();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        myhobby_v = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        update_hobby_combobox = new javax.swing.JComboBox<>();
        jLabel37 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        mymoimList_table = new javax.swing.JTable();
        update_mypage_Btn = new javax.swing.JButton();
        tomainPanalBtn1 = new javax.swing.JButton();
        tomymoimPanalBtn1 = new javax.swing.JButton();
        jLabel30 = new javax.swing.JLabel();
        moimnum_v = new javax.swing.JTextField();
        moim_delete_Btn = new javax.swing.JButton();

        jLabel7.setText("jLabel7");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pp.setLayout(new java.awt.CardLayout());

        p1_login.setBackground(new java.awt.Color(247, 228, 228));

        jLabel11.setFont(new java.awt.Font("����", 0, 24)); // NOI18N
        jLabel11.setText("LOGIN");

        jLabel1.setText("ID : ");

        jLabel2.setText("PWD : ");

        loginBtn.setText("�α���");
        loginBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginBtnActionPerformed(evt);
            }
        });

        joinBtn.setText("ȸ������");
        joinBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                joinBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout p1_loginLayout = new javax.swing.GroupLayout(p1_login);
        p1_login.setLayout(p1_loginLayout);
        p1_loginLayout.setHorizontalGroup(
            p1_loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p1_loginLayout.createSequentialGroup()
                .addGroup(p1_loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(p1_loginLayout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addGroup(p1_loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(p1_loginLayout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(log_pwd, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(p1_loginLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(30, 30, 30)
                                .addComponent(log_id, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(p1_loginLayout.createSequentialGroup()
                        .addGap(144, 144, 144)
                        .addComponent(loginBtn)
                        .addGap(18, 18, 18)
                        .addComponent(joinBtn))
                    .addGroup(p1_loginLayout.createSequentialGroup()
                        .addGap(153, 153, 153)
                        .addComponent(jLabel11)))
                .addContainerGap(740, Short.MAX_VALUE))
        );
        p1_loginLayout.setVerticalGroup(
            p1_loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p1_loginLayout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jLabel11)
                .addGap(67, 67, 67)
                .addGroup(p1_loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(log_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(p1_loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(log_pwd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(p1_loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(loginBtn)
                    .addComponent(joinBtn))
                .addContainerGap(451, Short.MAX_VALUE))
        );

        pp.add(p1_login, "c1");

        p2_adduser.setBackground(new java.awt.Color(255, 243, 253));

        jLabel3.setText("ID : ");

        jLabel4.setText("password : ");

        jLabel8.setText("���� : ");

        jLabel10.setFont(new java.awt.Font("����", 1, 24)); // NOI18N
        jLabel10.setText("ȸ������ �г�");

        jLabel20.setText("��� :");

        prevBtn.setText("< ���� ȭ��");
        prevBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prevBtnActionPerformed(evt);
            }
        });

        nextBtn.setText("ȸ�����ԿϷ�");
        nextBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextBtnActionPerformed(evt);
            }
        });

        join_locCom.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "����", "���", "��õ", "�׿�" }));

        join_hobbyCom.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "���1", "���2", "���3", "���4", "���5" }));

        jLabel22.setText(" �̸� : ");

        jLabel23.setText("�ֹι�ȣ : ");

        jLabel24.setText("-");

        javax.swing.GroupLayout p2_adduserLayout = new javax.swing.GroupLayout(p2_adduser);
        p2_adduser.setLayout(p2_adduserLayout);
        p2_adduserLayout.setHorizontalGroup(
            p2_adduserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, p2_adduserLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(prevBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(nextBtn)
                .addGap(40, 40, 40))
            .addGroup(p2_adduserLayout.createSequentialGroup()
                .addGroup(p2_adduserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(p2_adduserLayout.createSequentialGroup()
                        .addGroup(p2_adduserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel23)
                            .addComponent(jLabel22))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(p2_adduserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(p2_adduserLayout.createSequentialGroup()
                                .addComponent(jumin_front, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel24)
                                .addGap(7, 7, 7)
                                .addComponent(jumin_behind))
                            .addComponent(join_name1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(p2_adduserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(p2_adduserLayout.createSequentialGroup()
                            .addGap(99, 99, 99)
                            .addComponent(jLabel3)
                            .addGap(18, 18, 18)
                            .addComponent(join_id, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(p2_adduserLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, p2_adduserLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel4)
                            .addGap(18, 18, 18)
                            .addComponent(join_pwd, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(199, 199, 199)
                .addGroup(p2_adduserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel20))
                .addGap(24, 24, 24)
                .addGroup(p2_adduserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(join_hobbyCom, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(join_locCom, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(383, Short.MAX_VALUE))
        );
        p2_adduserLayout.setVerticalGroup(
            p2_adduserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p2_adduserLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addGroup(p2_adduserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(join_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(join_locCom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(p2_adduserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(join_pwd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20)
                    .addComponent(join_hobbyCom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(p2_adduserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(join_name1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(p2_adduserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(jumin_front, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jumin_behind, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 283, Short.MAX_VALUE)
                .addGroup(p2_adduserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nextBtn)
                    .addComponent(prevBtn))
                .addGap(101, 101, 101))
        );

        pp.add(p2_adduser, "c2");

        p3_createmoim.setBackground(new java.awt.Color(255, 254, 237));

        jLabel14.setBackground(new java.awt.Color(251, 248, 212));
        jLabel14.setFont(new java.awt.Font("����", 0, 24)); // NOI18N
        jLabel14.setText("���� ����");

        jLabel16.setText("���� �̸� : ");

        jLabel15.setText("�Ұ��� :");

        cmoimBtn.setText("���� �����");
        cmoimBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmoimBtnActionPerformed(evt);
            }
        });

        jLabel17.setText("���ɻ� ���� : ");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "���1", "���2", "���3", "���4", "���5" }));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "����", "���", "��õ", "�� ��" }));

        jLabel21.setText("���� ���� : ");

        main_page_Btn2.setText("���� ȭ������");
        main_page_Btn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                main_page_Btn2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout p3_createmoimLayout = new javax.swing.GroupLayout(p3_createmoim);
        p3_createmoim.setLayout(p3_createmoimLayout);
        p3_createmoimLayout.setHorizontalGroup(
            p3_createmoimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, p3_createmoimLayout.createSequentialGroup()
                .addGroup(p3_createmoimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(p3_createmoimLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(main_page_Btn2))
                    .addGroup(p3_createmoimLayout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(p3_createmoimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(p3_createmoimLayout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addGap(18, 18, 18)
                                .addComponent(cmoim_info, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(p3_createmoimLayout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cmoim_name))
                            .addGroup(p3_createmoimLayout.createSequentialGroup()
                                .addGap(140, 140, 140)
                                .addComponent(jLabel14)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 373, Short.MAX_VALUE)
                        .addGroup(p3_createmoimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(p3_createmoimLayout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(p3_createmoimLayout.createSequentialGroup()
                                .addComponent(jLabel21)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(42, 42, 42)
                .addComponent(cmoimBtn)
                .addGap(60, 60, 60))
        );
        p3_createmoimLayout.setVerticalGroup(
            p3_createmoimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p3_createmoimLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel14)
                .addGroup(p3_createmoimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(p3_createmoimLayout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addGroup(p3_createmoimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(cmoim_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(p3_createmoimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(cmoim_info, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(p3_createmoimLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(p3_createmoimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17))
                        .addGap(18, 18, 18)
                        .addGroup(p3_createmoimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(137, 137, 137)
                .addGroup(p3_createmoimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmoimBtn)
                    .addComponent(main_page_Btn2))
                .addContainerGap(371, Short.MAX_VALUE))
        );

        pp.add(p3_createmoim, "c3");

        p4_mainpanal.setBackground(new java.awt.Color(225, 229, 251));

        jLabel18.setText("�˻��ܾ� :");

        searchBtn.setText("�˻�");
        searchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBtnActionPerformed(evt);
            }
        });

        moim_crea_Btn.setText("+���Ӱ���");
        moim_crea_Btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moim_crea_BtnActionPerformed(evt);
            }
        });

        mainPrintTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(mainPrintTable);

        tomainPanalBtn.setText("�Ҹ���");

        tomymoimPanalBtn.setText("�� ����");
        tomymoimPanalBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tomymoimPanalBtnActionPerformed(evt);
            }
        });

        hobby1Btn.setText("���1");
        hobby1Btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hobby1BtnActionPerformed(evt);
            }
        });

        hobby2Btn.setText("���2");
        hobby2Btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hobby2BtnActionPerformed(evt);
            }
        });

        hobby3Btn.setText("���3");
        hobby3Btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hobby3BtnActionPerformed(evt);
            }
        });

        hobby4Byn.setText("���4");
        hobby4Byn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hobby4BynActionPerformed(evt);
            }
        });

        hobby5Btn.setText("���5");
        hobby5Btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hobby5BtnActionPerformed(evt);
            }
        });

        logout_btn.setText("�α׾ƿ�");
        logout_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logout_btnActionPerformed(evt);
            }
        });

        jLabel6.setText("���� ��ȣ : ");

        moim_detail_Btn.setText("���� �󼼺���");
        moim_detail_Btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moim_detail_BtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout p4_mainpanalLayout = new javax.swing.GroupLayout(p4_mainpanal);
        p4_mainpanal.setLayout(p4_mainpanalLayout);
        p4_mainpanalLayout.setHorizontalGroup(
            p4_mainpanalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p4_mainpanalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(p4_mainpanalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(p4_mainpanalLayout.createSequentialGroup()
                        .addGap(0, 197, Short.MAX_VALUE)
                        .addComponent(hobby1Btn, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addComponent(hobby2Btn)
                        .addGap(18, 18, 18)
                        .addComponent(hobby3Btn)
                        .addGap(29, 29, 29)
                        .addComponent(hobby4Byn)
                        .addGap(18, 18, 18)
                        .addComponent(hobby5Btn)
                        .addGap(45, 45, 45)
                        .addComponent(jLabel18)
                        .addGap(18, 18, 18)
                        .addComponent(searchword, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(moim_crea_Btn))
                    .addComponent(jScrollPane3)))
            .addGroup(p4_mainpanalLayout.createSequentialGroup()
                .addGap(323, 323, 323)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(searchnum, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(p4_mainpanalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(p4_mainpanalLayout.createSequentialGroup()
                        .addGap(18, 401, Short.MAX_VALUE)
                        .addComponent(logout_btn)
                        .addGap(41, 41, 41))
                    .addGroup(p4_mainpanalLayout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(moim_detail_Btn)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(p4_mainpanalLayout.createSequentialGroup()
                .addGap(172, 172, 172)
                .addComponent(tomainPanalBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(tomymoimPanalBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        p4_mainpanalLayout.setVerticalGroup(
            p4_mainpanalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p4_mainpanalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(p4_mainpanalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(searchBtn)
                    .addComponent(moim_crea_Btn)
                    .addComponent(hobby1Btn)
                    .addComponent(hobby2Btn)
                    .addComponent(hobby3Btn)
                    .addComponent(hobby4Byn)
                    .addComponent(hobby5Btn))
                .addGap(52, 52, 52)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(p4_mainpanalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(searchnum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(moim_detail_Btn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(p4_mainpanalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(tomymoimPanalBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tomainPanalBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addComponent(logout_btn)
                .addGap(41, 41, 41))
        );

        pp.add(p4_mainpanal, "c4");

        p5_moimpage.setBackground(new java.awt.Color(229, 252, 249));

        jLabel12.setText("���Ӹ� : ");

        jLabel13.setText("�������Ʈ");

        moim_join_Btn.setText("���� �����ϱ�");
        moim_join_Btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moim_join_BtnActionPerformed(evt);
            }
        });

        moim_info.setColumns(20);
        moim_info.setRows(5);
        moim_info.setText("����� ���� ������ �����ִ� �ڸ� ");
        jScrollPane1.setViewportView(moim_info);

        mainpageBtn.setText("���� �������� ���ư���");
        mainpageBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mainpageBtnActionPerformed(evt);
            }
        });

        moimTitle.setText("���Ӹ� ����ڸ�");

        moimlist.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(moimlist);

        tochatingPanalBtn.setFont(new java.awt.Font("����", 0, 48)); // NOI18N
        tochatingPanalBtn.setText("ȸ������ä��");
        tochatingPanalBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tochatingPanalBtnActionPerformed(evt);
            }
        });

        tomoiminfoBtn.setFont(new java.awt.Font("����", 0, 48)); // NOI18N
        tomoiminfoBtn.setText("����");
        tomoiminfoBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tomoiminfoBtnActionPerformed(evt);
            }
        });

        toboardpanalBtn.setFont(new java.awt.Font("����", 0, 48)); // NOI18N
        toboardpanalBtn.setText("�Խ���");
        toboardpanalBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toboardpanalBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout p5_moimpageLayout = new javax.swing.GroupLayout(p5_moimpage);
        p5_moimpage.setLayout(p5_moimpageLayout);
        p5_moimpageLayout.setHorizontalGroup(
            p5_moimpageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p5_moimpageLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(p5_moimpageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(p5_moimpageLayout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, p5_moimpageLayout.createSequentialGroup()
                        .addGroup(p5_moimpageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1)
                            .addGroup(p5_moimpageLayout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(p5_moimpageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(p5_moimpageLayout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(moim_join_Btn))
                                    .addGroup(p5_moimpageLayout.createSequentialGroup()
                                        .addComponent(moimTitle)
                                        .addGap(0, 0, Short.MAX_VALUE)))))
                        .addGap(45, 45, 45))
                    .addGroup(p5_moimpageLayout.createSequentialGroup()
                        .addComponent(jScrollPane4)
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, p5_moimpageLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(mainpageBtn)
                .addGap(35, 35, 35))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, p5_moimpageLayout.createSequentialGroup()
                .addComponent(tomoiminfoBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(toboardpanalBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tochatingPanalBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        p5_moimpageLayout.setVerticalGroup(
            p5_moimpageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p5_moimpageLayout.createSequentialGroup()
                .addGroup(p5_moimpageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tomoiminfoBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(toboardpanalBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tochatingPanalBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 102, Short.MAX_VALUE)
                .addGroup(p5_moimpageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(moim_join_Btn)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, p5_moimpageLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(p5_moimpageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(moimTitle))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(jLabel13)
                .addGap(35, 35, 35)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(mainpageBtn)
                .addGap(91, 91, 91))
        );

        pp.add(p5_moimpage, "c5");

        board_Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(board_Table);

        jLabel5.setText("�� �Խñ� ���� â :");

        detail_board_show_Btn.setText("�󼼰Խñۺ���");
        detail_board_show_Btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                detail_board_show_BtnActionPerformed(evt);
            }
        });

        create_board_Btn.setText("+�Խñ��ۼ�");
        create_board_Btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                create_board_BtnActionPerformed(evt);
            }
        });

        board_pageBtn1.setText("���� �������� ���ư���");
        board_pageBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                board_pageBtn1ActionPerformed(evt);
            }
        });

        tochatingPanalBtn1.setFont(new java.awt.Font("����", 0, 48)); // NOI18N
        tochatingPanalBtn1.setText("ȸ������ä��");
        tochatingPanalBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tochatingPanalBtn1ActionPerformed(evt);
            }
        });

        tomoiminfoBtn1.setFont(new java.awt.Font("����", 0, 48)); // NOI18N
        tomoiminfoBtn1.setText("����");
        tomoiminfoBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tomoiminfoBtn1ActionPerformed(evt);
            }
        });

        toboardpanalBtn1.setFont(new java.awt.Font("����", 0, 48)); // NOI18N
        toboardpanalBtn1.setText("�Խ���");
        toboardpanalBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toboardpanalBtn1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout p6_boardPanalLayout = new javax.swing.GroupLayout(p6_boardPanal);
        p6_boardPanal.setLayout(p6_boardPanalLayout);
        p6_boardPanalLayout.setHorizontalGroup(
            p6_boardPanalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p6_boardPanalLayout.createSequentialGroup()
                .addGroup(p6_boardPanalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, p6_boardPanalLayout.createSequentialGroup()
                        .addComponent(tomoiminfoBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(toboardpanalBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tochatingPanalBtn1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, p6_boardPanalLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(p6_boardPanalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(p6_boardPanalLayout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(search_detail_board, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(detail_board_show_Btn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(create_board_Btn)
                                .addGap(3, 3, 3))
                            .addComponent(board_pageBtn1)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 869, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(183, Short.MAX_VALUE))
        );
        p6_boardPanalLayout.setVerticalGroup(
            p6_boardPanalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p6_boardPanalLayout.createSequentialGroup()
                .addGroup(p6_boardPanalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tomoiminfoBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(toboardpanalBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tochatingPanalBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addGroup(p6_boardPanalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(search_detail_board, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(detail_board_show_Btn)
                    .addComponent(create_board_Btn))
                .addGap(18, 18, 18)
                .addComponent(board_pageBtn1)
                .addContainerGap(222, Short.MAX_VALUE))
        );

        pp.add(p6_boardPanal, "c6");

        tochatingPanalBtn2.setFont(new java.awt.Font("����", 0, 48)); // NOI18N
        tochatingPanalBtn2.setText("ȸ������ä��");
        tochatingPanalBtn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tochatingPanalBtn2ActionPerformed(evt);
            }
        });

        tomoiminfoBtn2.setFont(new java.awt.Font("����", 0, 48)); // NOI18N
        tomoiminfoBtn2.setText("����");
        tomoiminfoBtn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tomoiminfoBtn2ActionPerformed(evt);
            }
        });

        toboardpanalBtn2.setFont(new java.awt.Font("����", 0, 48)); // NOI18N
        toboardpanalBtn2.setText("�Խ���");

        talkBar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                talkBarActionPerformed(evt);
            }
        });

        send_Btn.setText("����");

        chating_pageBtn2.setText("���� �������� ���ư���");
        chating_pageBtn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chating_pageBtn2ActionPerformed(evt);
            }
        });

        chatScreen.setColumns(20);
        chatScreen.setRows(5);
        jScrollPane5.setViewportView(chatScreen);

        javax.swing.GroupLayout p7_chatingPanalLayout = new javax.swing.GroupLayout(p7_chatingPanal);
        p7_chatingPanal.setLayout(p7_chatingPanalLayout);
        p7_chatingPanalLayout.setHorizontalGroup(
            p7_chatingPanalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p7_chatingPanalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(p7_chatingPanalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, p7_chatingPanalLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(chating_pageBtn2)
                        .addGap(25, 25, 25))
                    .addGroup(p7_chatingPanalLayout.createSequentialGroup()
                        .addGroup(p7_chatingPanalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, p7_chatingPanalLayout.createSequentialGroup()
                                .addComponent(talkBar, javax.swing.GroupLayout.PREFERRED_SIZE, 723, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(send_Btn, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14))
                            .addComponent(jScrollPane5)
                            .addGroup(p7_chatingPanalLayout.createSequentialGroup()
                                .addComponent(tomoiminfoBtn2, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(toboardpanalBtn2, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tochatingPanalBtn2)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        p7_chatingPanalLayout.setVerticalGroup(
            p7_chatingPanalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p7_chatingPanalLayout.createSequentialGroup()
                .addGroup(p7_chatingPanalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tomoiminfoBtn2, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(toboardpanalBtn2, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tochatingPanalBtn2, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 161, Short.MAX_VALUE)
                .addGroup(p7_chatingPanalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(talkBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(send_Btn))
                .addGap(18, 18, 18)
                .addComponent(chating_pageBtn2)
                .addGap(22, 22, 22))
        );

        pp.add(p7_chatingPanal, "c7");

        jButton1.setText("<�Խ�������");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        addBoardBtn.setText("���");
        addBoardBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBoardBtnActionPerformed(evt);
            }
        });

        jLabel9.setText("���� :");

        jLabel19.setText("���� :");

        javax.swing.GroupLayout p8_createdocument_PanalLayout = new javax.swing.GroupLayout(p8_createdocument_Panal);
        p8_createdocument_Panal.setLayout(p8_createdocument_PanalLayout);
        p8_createdocument_PanalLayout.setHorizontalGroup(
            p8_createdocument_PanalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p8_createdocument_PanalLayout.createSequentialGroup()
                .addGap(89, 89, 89)
                .addGroup(p8_createdocument_PanalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, p8_createdocument_PanalLayout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(330, 330, 330)
                        .addComponent(addBoardBtn))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, p8_createdocument_PanalLayout.createSequentialGroup()
                        .addGroup(p8_createdocument_PanalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel19))
                        .addGap(27, 27, 27)
                        .addGroup(p8_createdocument_PanalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(gle, javax.swing.GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE)
                            .addComponent(glename))))
                .addContainerGap(332, Short.MAX_VALUE))
        );
        p8_createdocument_PanalLayout.setVerticalGroup(
            p8_createdocument_PanalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p8_createdocument_PanalLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(p8_createdocument_PanalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(addBoardBtn))
                .addGap(58, 58, 58)
                .addGroup(p8_createdocument_PanalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(glename, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(55, 55, 55)
                .addGroup(p8_createdocument_PanalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19)
                    .addComponent(gle, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(142, Short.MAX_VALUE))
        );

        pp.add(p8_createdocument_Panal, "c8");

        board_content.setColumns(20);
        board_content.setRows(5);
        jScrollPane6.setViewportView(board_content);

        jLabel25.setText("�ۼ��� : ");

        jLabel26.setText("�Խñ� ���� : ");

        content_name.setText("�Խñ� ������ ���� ��");

        board_writer_name.setText("�ۼ��� �̸�");

        to_moimBtn.setText("< �ڷΰ���");
        to_moimBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                to_moimBtnActionPerformed(evt);
            }
        });

        comment_v.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comment_vActionPerformed(evt);
            }
        });

        jLabel27.setText("���â");

        comment_area.setColumns(20);
        comment_area.setRows(5);
        jScrollPane9.setViewportView(comment_area);

        javax.swing.GroupLayout p9_detailGlePanalLayout = new javax.swing.GroupLayout(p9_detailGlePanal);
        p9_detailGlePanal.setLayout(p9_detailGlePanalLayout);
        p9_detailGlePanalLayout.setHorizontalGroup(
            p9_detailGlePanalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p9_detailGlePanalLayout.createSequentialGroup()
                .addGroup(p9_detailGlePanalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(p9_detailGlePanalLayout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(board_writer_name)
                        .addGap(104, 104, 104)
                        .addComponent(jLabel26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(content_name))
                    .addGroup(p9_detailGlePanalLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(p9_detailGlePanalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel27)
                            .addComponent(to_moimBtn)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 658, Short.MAX_VALUE)
                            .addComponent(jScrollPane9)
                            .addComponent(comment_v))))
                .addContainerGap(380, Short.MAX_VALUE))
        );
        p9_detailGlePanalLayout.setVerticalGroup(
            p9_detailGlePanalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p9_detailGlePanalLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(to_moimBtn)
                .addGap(89, 89, 89)
                .addGroup(p9_detailGlePanalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(jLabel26)
                    .addComponent(content_name)
                    .addComponent(board_writer_name))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(jLabel27)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(comment_v, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(79, Short.MAX_VALUE))
        );

        pp.add(p9_detailGlePanal, "c9");

        jLabel28.setFont(new java.awt.Font("����", 0, 36)); // NOI18N
        jLabel28.setText("���� ����");

        jLabel29.setText("�� �̸� : ");

        myname_v.setText("[ �� �� ]");

        jLabel31.setText("���� ������ ���� : ");

        myloc_v.setText("[ �� �� ]");

        update_loc_combobox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "����", "���", "��õ", "�� ��" }));

        jLabel33.setText("���� �� ���� : ");

        jLabel34.setText("���� ������ ��� : ");

        myhobby_v.setText("[ �� �� ]");

        jLabel36.setText("���� �� ��� : ");

        update_hobby_combobox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "���1", "���2", "���3", "���4", "���5" }));

        jLabel37.setText("���� ������ ����");

        mymoimList_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane8.setViewportView(mymoimList_table);

        update_mypage_Btn.setText("���� ���� ����");
        update_mypage_Btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                update_mypage_BtnActionPerformed(evt);
            }
        });

        tomainPanalBtn1.setFont(new java.awt.Font("����", 0, 36)); // NOI18N
        tomainPanalBtn1.setText("�Ҹ���");
        tomainPanalBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tomainPanalBtn1ActionPerformed(evt);
            }
        });

        tomymoimPanalBtn1.setFont(new java.awt.Font("����", 0, 36)); // NOI18N
        tomymoimPanalBtn1.setText("�� ���� ����");
        tomymoimPanalBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tomymoimPanalBtn1ActionPerformed(evt);
            }
        });

        jLabel30.setText("���� ��ȣ");

        moim_delete_Btn.setText("���� Ż��");
        moim_delete_Btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moim_delete_BtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout p10_mypagePanelLayout = new javax.swing.GroupLayout(p10_mypagePanel);
        p10_mypagePanel.setLayout(p10_mypagePanelLayout);
        p10_mypagePanelLayout.setHorizontalGroup(
            p10_mypagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p10_mypagePanelLayout.createSequentialGroup()
                .addGroup(p10_mypagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(p10_mypagePanelLayout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(jLabel28)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(update_mypage_Btn))
                    .addGroup(p10_mypagePanelLayout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addGroup(p10_mypagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(p10_mypagePanelLayout.createSequentialGroup()
                                .addGroup(p10_mypagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(p10_mypagePanelLayout.createSequentialGroup()
                                        .addComponent(jLabel31)
                                        .addGap(18, 18, 18)
                                        .addComponent(myloc_v))
                                    .addGroup(p10_mypagePanelLayout.createSequentialGroup()
                                        .addComponent(jLabel34)
                                        .addGap(18, 18, 18)
                                        .addComponent(myhobby_v))
                                    .addComponent(jLabel37))
                                .addGap(143, 143, 143)
                                .addGroup(p10_mypagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(p10_mypagePanelLayout.createSequentialGroup()
                                        .addComponent(jLabel36)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(update_hobby_combobox, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(p10_mypagePanelLayout.createSequentialGroup()
                                        .addComponent(jLabel33)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(update_loc_combobox, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 756, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(p10_mypagePanelLayout.createSequentialGroup()
                                .addComponent(jLabel29)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(myname_v))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, p10_mypagePanelLayout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(tomainPanalBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tomymoimPanalBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)))
                .addContainerGap(255, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, p10_mypagePanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel30)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(moimnum_v, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(moim_delete_Btn)
                .addGap(299, 299, 299))
        );
        p10_mypagePanelLayout.setVerticalGroup(
            p10_mypagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p10_mypagePanelLayout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addGroup(p10_mypagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(update_mypage_Btn))
                .addGap(38, 38, 38)
                .addGroup(p10_mypagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(myname_v))
                .addGap(31, 31, 31)
                .addGroup(p10_mypagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(myloc_v)
                    .addComponent(update_loc_combobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel33))
                .addGap(35, 35, 35)
                .addGroup(p10_mypagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(myhobby_v)
                    .addComponent(jLabel36)
                    .addComponent(update_hobby_combobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addComponent(jLabel37)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
                .addGroup(p10_mypagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(moimnum_v, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(moim_delete_Btn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(p10_mypagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tomainPanalBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tomymoimPanalBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25))
        );

        pp.add(p10_mypagePanel, "c10");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // p1 - ȸ������ ������ �̵���ư
    private void joinBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_joinBtnActionPerformed
        card.show(pp, "c2");
    }//GEN-LAST:event_joinBtnActionPerformed
    
    private void searchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtnActionPerformed
        // p4 - ���� �˻��ϱ�
        String res = searchword.getText();
        groupList(md.detailGroup(res));

    }//GEN-LAST:event_searchBtnActionPerformed
    
    private void moim_crea_BtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moim_crea_BtnActionPerformed
       //p4 - ���� ���� �������� �̵� ��ư
        card.show(pp, "c3");
    }//GEN-LAST:event_moim_crea_BtnActionPerformed
    
    private void loginBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginBtnActionPerformed
      //p1 - id, pwd �Է� �� �α����ϴ� ��ư
        mem = new AMember();
       String id = log_id.getText();
       String pwd = log_pwd.getText();
       //checkIdMd ( id,pwd üũ true = return 1 , false = return 0 , üũ�� ���� ���ٸ� -1 ��ȯ)
        int a = md.checkIdMd(id, pwd);        
        if(a==1){
            //checkIdMd -> return 1�ϰ��
        mem = md.loginMd(mem,id); //loginMd (memberdto�� id�� �ش��ϴ� ������ ������ �޾� �����մϴ�) 
        JOptionPane.showMessageDialog(this, "ȯ���մϴ�","�α��� ����!" , JOptionPane.INFORMATION_MESSAGE);
        groupList(md.groupListMd(mem));//groupListMd -> ������ ���� �� ��̿� �ش��ϴ� ��ȣ�� �켱������ ������������ ����ϴ� �޼����Դϴ�
        card.show(pp, "c4");
        }else if(a ==0){
            JOptionPane.showMessageDialog(this, "���̵�� ��й�ȣ�� ����ġ�մϴ�.","�α��� ����!",JOptionPane.ERROR_MESSAGE);
        }else{
             JOptionPane.showMessageDialog(this, "���̵� �����ϴ�.","???",JOptionPane.ERROR_MESSAGE);
        }
  
    }//GEN-LAST:event_loginBtnActionPerformed
   
    private void prevBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prevBtnActionPerformed
        //p2 - ȸ������ ���������� �α��� ȭ������ �̵��ϴ� ȭ�� ��ư
        card.show(pp, "c1");
        log_id.setText("");
        log_pwd.setText("");
    }//GEN-LAST:event_prevBtnActionPerformed

    
    private void nextBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextBtnActionPerformed
       //p2 - ȸ�����ԿϷ� ��ư
        try {        
        if(join_id.getText().trim().split(" ").length !=1 || join_pwd.getText().trim().split(" ").length !=1
           || join_name1.getText().split(" ").length !=1 || jumin_front.getText().split(" ").length !=1
           || jumin_behind.getText().split(" ").length !=1
                ){ // �Էµ� ������� �������� ����ĭ�� �������
            throw new NotSpace(); // ������ �߻���Ű�� NotSpace�� throw �ϴ� �޼����Դϴ� 
        }
        Integer.parseInt(jumin_front.getText());
        Integer.parseInt(jumin_behind.getText());
        if(jumin_front.getText().length()!=6 || jumin_behind.getText().length()!=7){ // �ֹι�ȣ�� ���ڸ� 6 ���ڸ� 7�� �ƴҰ��
        throw new Jumin();// ������ �߻���Ű�� Jumin�� throw �ϴ� �޼����Դϴ� 
        }
        AMember mem = new AMember();
        mem.setMid(join_id.getText().trim());
        mem.setMpwd(join_pwd.getText().trim());
        mem.setMname(join_name1.getText().trim());
        mem.setMloc((String) join_locCom.getSelectedItem());
        mem.setMjumin(jumin_front.getText().trim()+ jLabel24.getText()+jumin_behind.getText().trim());
        mem.setMhobby(join_hobbyCom.getSelectedIndex()+1);
        md.joinMemberMd(mem); //������� ������ DB�� �Է½�Ű�� �޼����Դϴ�
        JOptionPane.showMessageDialog(rootPane, "ȸ�������� �Ϸ�Ǿ����ϴ�!","ȯ���մϴ�",JOptionPane.INFORMATION_MESSAGE);
        card.show(pp,"c1"); //������ �Ϸ�Ǹ� �α����������� ���ư�
        } catch (NumberFormatException e) {
           JOptionPane.showMessageDialog(rootPane, "�ֹι�ȣ�� ����� �Է��ϼ���","���!!",JOptionPane.ERROR_MESSAGE);
        } catch(Jumin e){
           JOptionPane.showMessageDialog(rootPane, "�ֹι�ȣ�ڸ����� ���� �ʽ��ϴ�","���!!",JOptionPane.ERROR_MESSAGE);
        } catch(NotSpace e){
           JOptionPane.showMessageDialog(rootPane, "������ �Է��Ҽ� �����ϴ�","���!!",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_nextBtnActionPerformed
   
    private void mainpageBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mainpageBtnActionPerformed
        //���ӻ����������� ������������ �̵��ϴ� ��ư
        card.show(pp, "c4");
        
    }//GEN-LAST:event_mainpageBtnActionPerformed

    private void cmoimBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmoimBtnActionPerformed
       //���� �����ϱ� ��ư
        AGroup a = new AGroup();
        
        a.setGname(cmoim_name.getText());
        a.setGinfo(cmoim_info.getText());

        md.addGroupMd(mem, a);
        //addGroupMd -> ���� �����ϱ� �޼���(ref = ������ ����� ȸ���� ������ ������ )
        
        groupList(md.groupListMd(mem));

        card.show(pp,"c4");
    }//GEN-LAST:event_cmoimBtnActionPerformed
   
    private void moim_join_BtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moim_join_BtnActionPerformed
         //ȸ���� ������ ������ �� �ִ� ��ư
         
        md.joinMd(mem,group );
        
        groupdetailList(md.listGroupMd(Integer.parseInt(searchnum.getText()))); //���Ե� ��� jtable�� ���
        System.out.println(group.getGroupnum());
    }//GEN-LAST:event_moim_join_BtnActionPerformed
    // �� ���� Ȯ�� ������ �̵� ��ư(mypage)
    private void tomymoimPanalBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tomymoimPanalBtnActionPerformed
        myname_v.setText(mem.getMname());
        myloc_v.setText(mem.getMloc());
        myhobby_v.setText(String.valueOf(mem.getMhobby()));
        
        myGroupList(md.listMyGroupMd(mem.getMembernum()));
        card.show(pp, "c10");
    }//GEN-LAST:event_tomymoimPanalBtnActionPerformed
    //�������������� �α׾ƿ� ��ư
    private void logout_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logout_btnActionPerformed
        
        card.show(pp, "c1");
    }//GEN-LAST:event_logout_btnActionPerformed
    private void moim_detail_BtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moim_detail_BtnActionPerformed
            //���� ��ȣ�� search�� ���� �������� �̵���ư
        int moimnum = Integer.parseInt(searchnum.getText().trim()) ;
        group = new AGroup();
        group.setGroupnum(moimnum);
        group = md.enterGroupMd(group);
        moimTitle.setText(group.getGname());
        moim_info.setText(group.getGinfo());
        groupdetailList(md.listGroupMd(Integer.parseInt(searchnum.getText())));
        
        
        card.show(pp, "c5");
        
    }//GEN-LAST:event_moim_detail_BtnActionPerformed

    private void main_page_Btn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_main_page_Btn2ActionPerformed
        card.show(pp, "c4");
    }//GEN-LAST:event_main_page_Btn2ActionPerformed

    private void tomoiminfoBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tomoiminfoBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tomoiminfoBtnActionPerformed
// �������������� ���θ�������
    private void board_pageBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_board_pageBtn1ActionPerformed
        card.show(pp, "c4");
        
    }//GEN-LAST:event_board_pageBtn1ActionPerformed

    private void tomoiminfoBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tomoiminfoBtn1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tomoiminfoBtn1ActionPerformed

    private void tomoiminfoBtn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tomoiminfoBtn2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tomoiminfoBtn2ActionPerformed

    private void chating_pageBtn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chating_pageBtn2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chating_pageBtn2ActionPerformed

    private void tomymoimPanalBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tomymoimPanalBtn1ActionPerformed
        card.show(pp, "c10");
    }//GEN-LAST:event_tomymoimPanalBtn1ActionPerformed
    // �� ���� ���� ��ư
    private void update_mypage_BtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_update_mypage_BtnActionPerformed
        String mloc = (String) update_loc_combobox.getSelectedItem();
        int mhobby = update_hobby_combobox.getSelectedIndex()+1;       
        
       int a = JOptionPane.showConfirmDialog(rootPane, "�����Ͻðڽ��ϱ�??", "Ȯ��!!", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
       if(a == 0){
           md.updateMyInfoMd(mem, mloc, mhobby);
        
        myname_v.setText(mem.getMname());
        myloc_v.setText(mem.getMloc());
        myhobby_v.setText(String.valueOf(mem.getMhobby()));
       }
           
       
       
    }//GEN-LAST:event_update_mypage_BtnActionPerformed

    private void tomainPanalBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tomainPanalBtn1ActionPerformed
        card.show(pp, "c4");
    }//GEN-LAST:event_tomainPanalBtn1ActionPerformed

    private void create_board_BtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_create_board_BtnActionPerformed
       // �Խñ� �ۼ� ������ �̵�
       card.show(pp, "c8");
    }//GEN-LAST:event_create_board_BtnActionPerformed

    private void addBoardBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBoardBtnActionPerformed
        // �Խñ� �ۼ� ��ư
        AJoin aj = new AJoin();
        aj.setAmember(mem);
        aj.setAgroup(group);
        Board b = new Board();
        b.setSubject(glename.getText());
        b.setContent(gle.getText());
        md.addBoardMd(b,aj);
        JOptionPane.showMessageDialog(rootPane, "�Խñ��� �ۼ��Ǿ����ϴ�.","!!?",JOptionPane.INFORMATION_MESSAGE);
        listContent();
        card.show(pp, "c6");
    }//GEN-LAST:event_addBoardBtnActionPerformed
    // �Խ��� ������� �̵��ϴ� ��ư
    private void toboardpanalBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toboardpanalBtnActionPerformed
        listContent();
        card.show(pp, "c6");
    }//GEN-LAST:event_toboardpanalBtnActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // �Խ��� ���� �������� ���� ��ư
        card.show(pp, "c6");
    }//GEN-LAST:event_jButton1ActionPerformed
    // �� �Խñ� ����
    private void detail_board_show_BtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_detail_board_show_BtnActionPerformed
        BoardComm bc = new BoardComm();
        board = new Board();
        board.setBoardnum(Integer.parseInt(search_detail_board.getText()));
        board = md.getDetail(board);
        bc.setBo(board);
        board_writer_name.setText(board.getAmember().getMname());
        content_name.setText(board.getSubject());
        board_content.setText(board.getContent());
        
        printCommentlist(bc);
        
        
        card.show(pp, "c9");
        
    }//GEN-LAST:event_detail_board_show_BtnActionPerformed
 // �Խñ� �󼼺��⿡�� �Խñ� ������� �ڷΰ��� ��ư
    private void to_moimBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_to_moimBtnActionPerformed
        card.show(pp, "c6");
    }//GEN-LAST:event_to_moimBtnActionPerformed

    private void comment_vActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comment_vActionPerformed
       //��� ���,��� 
        AJoin aj = new AJoin();
        aj.setAmember(mem);
        aj.setAgroup(group);
        BoardComm bc = new BoardComm();
        bc.setBo(board);
        bc.setContent(comment_v.getText());
        md.addCommMd(bc, aj);
        printCommentlist(bc);
        comment_v.setText("");
    }//GEN-LAST:event_comment_vActionPerformed

    private void toboardpanalBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toboardpanalBtn1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_toboardpanalBtn1ActionPerformed

    private void tochatingPanalBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tochatingPanalBtn1ActionPerformed
        chatConn();
    }//GEN-LAST:event_tochatingPanalBtn1ActionPerformed

    private void tochatingPanalBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tochatingPanalBtnActionPerformed
        chatConn();
    }//GEN-LAST:event_tochatingPanalBtnActionPerformed

    private void talkBarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_talkBarActionPerformed
        String msg = talkBar.getText();
        StringBuilder sb = new StringBuilder();
        sb.append("talk").append("/").append(group.getGroupnum()).append("/").append(mem.getMid()).append("/").append(msg);
        pw.println(sb.toString());
        talkBar.setText("");
    }//GEN-LAST:event_talkBarActionPerformed

    private void moim_delete_BtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moim_delete_BtnActionPerformed
         // ���������������� ���� Ż���ϱ� ��ư
        
        AJoin a = new AJoin();
        a.setMembernum(mem.getMembernum());
        a.setGroupnum(Integer.parseInt(moimnum_v.getText()));
        
        md.deleteMyMoimMd(a);
        myGroupList(md.listMyGroupMd(mem.getMembernum()));
    }//GEN-LAST:event_moim_delete_BtnActionPerformed

    private void tochatingPanalBtn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tochatingPanalBtn2ActionPerformed
       chatConn();
    }//GEN-LAST:event_tochatingPanalBtn2ActionPerformed

    private void hobby1BtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hobby1BtnActionPerformed
        // ��̹�ư 1
        hobbynum = 1;
        groupList(md.selectGroupHobby(hobbynum));
    }//GEN-LAST:event_hobby1BtnActionPerformed

    private void hobby2BtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hobby2BtnActionPerformed
       // ��̹�ư 1
        hobbynum = 2;
        groupList(md.selectGroupHobby(hobbynum));
    }//GEN-LAST:event_hobby2BtnActionPerformed

    private void hobby3BtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hobby3BtnActionPerformed
        // ��̹�ư 1
        hobbynum = 3;
        groupList(md.selectGroupHobby(hobbynum));
    }//GEN-LAST:event_hobby3BtnActionPerformed

    private void hobby4BynActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hobby4BynActionPerformed
        // ��̹�ư 1
        hobbynum = 4;
        groupList(md.selectGroupHobby(hobbynum));
    }//GEN-LAST:event_hobby4BynActionPerformed

    private void hobby5BtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hobby5BtnActionPerformed
        // ��̹�ư 1
        hobbynum = 5;
        groupList(md.selectGroupHobby(hobbynum));
    }//GEN-LAST:event_hobby5BtnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Asemi_ui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Asemi_ui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Asemi_ui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Asemi_ui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Asemi_ui_1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBoardBtn;
    private javax.swing.JTable board_Table;
    private javax.swing.JTextArea board_content;
    private javax.swing.JButton board_pageBtn1;
    private javax.swing.JLabel board_writer_name;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JTextArea chatScreen;
    private javax.swing.JButton chating_pageBtn2;
    private javax.swing.JButton cmoimBtn;
    private javax.swing.JTextField cmoim_info;
    private javax.swing.JTextField cmoim_name;
    private javax.swing.JTextArea comment_area;
    private javax.swing.JTextField comment_v;
    private javax.swing.JLabel content_name;
    private javax.swing.JButton create_board_Btn;
    private javax.swing.JButton detail_board_show_Btn;
    private javax.swing.JTextField gle;
    private javax.swing.JTextField glename;
    private javax.swing.JButton hobby1Btn;
    private javax.swing.JButton hobby2Btn;
    private javax.swing.JButton hobby3Btn;
    private javax.swing.JButton hobby4Byn;
    private javax.swing.JButton hobby5Btn;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JButton joinBtn;
    private javax.swing.JComboBox<String> join_hobbyCom;
    private javax.swing.JTextField join_id;
    private javax.swing.JComboBox<String> join_locCom;
    private javax.swing.JTextField join_name1;
    private javax.swing.JTextField join_pwd;
    private javax.swing.JTextField jumin_behind;
    private javax.swing.JTextField jumin_front;
    private javax.swing.JTextField log_id;
    private javax.swing.JTextField log_pwd;
    private javax.swing.JButton loginBtn;
    private javax.swing.JButton logout_btn;
    private javax.swing.JTable mainPrintTable;
    private javax.swing.JButton main_page_Btn2;
    private javax.swing.JButton mainpageBtn;
    private javax.swing.JLabel moimTitle;
    private javax.swing.JButton moim_crea_Btn;
    private javax.swing.JButton moim_delete_Btn;
    private javax.swing.JButton moim_detail_Btn;
    private javax.swing.JTextArea moim_info;
    private javax.swing.JButton moim_join_Btn;
    private javax.swing.JTable moimlist;
    private javax.swing.JTextField moimnum_v;
    private javax.swing.JLabel myhobby_v;
    private javax.swing.JLabel myloc_v;
    private javax.swing.JTable mymoimList_table;
    private javax.swing.JLabel myname_v;
    private javax.swing.JButton nextBtn;
    private javax.swing.JPanel p10_mypagePanel;
    private javax.swing.JPanel p1_login;
    private javax.swing.JPanel p2_adduser;
    private javax.swing.JPanel p3_createmoim;
    private javax.swing.JPanel p4_mainpanal;
    private javax.swing.JPanel p5_moimpage;
    private javax.swing.JPanel p6_boardPanal;
    private javax.swing.JPanel p7_chatingPanal;
    private javax.swing.JPanel p8_createdocument_Panal;
    private javax.swing.JPanel p9_detailGlePanal;
    private javax.swing.JPanel pp;
    private javax.swing.JButton prevBtn;
    private javax.swing.JButton searchBtn;
    private javax.swing.JTextField search_detail_board;
    private javax.swing.JTextField searchnum;
    private javax.swing.JTextField searchword;
    private javax.swing.JButton send_Btn;
    private javax.swing.JTextField talkBar;
    private javax.swing.JButton to_moimBtn;
    private javax.swing.JButton toboardpanalBtn;
    private javax.swing.JButton toboardpanalBtn1;
    private javax.swing.JButton toboardpanalBtn2;
    private javax.swing.JButton tochatingPanalBtn;
    private javax.swing.JButton tochatingPanalBtn1;
    private javax.swing.JButton tochatingPanalBtn2;
    private javax.swing.JButton tomainPanalBtn;
    private javax.swing.JButton tomainPanalBtn1;
    private javax.swing.JButton tomoiminfoBtn;
    private javax.swing.JButton tomoiminfoBtn1;
    private javax.swing.JButton tomoiminfoBtn2;
    private javax.swing.JButton tomymoimPanalBtn;
    private javax.swing.JButton tomymoimPanalBtn1;
    private javax.swing.JComboBox<String> update_hobby_combobox;
    private javax.swing.JComboBox<String> update_loc_combobox;
    private javax.swing.JButton update_mypage_Btn;
    // End of variables declaration//GEN-END:variables
}
