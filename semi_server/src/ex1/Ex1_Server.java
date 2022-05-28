package ex1;

import java.io.IOException;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

import dto.Chat;
import model.model;

public class Ex1_Server {
	private ServerSocket ss;
	private ArrayList<ServerThread> list;
	private model md;
	public Ex1_Server(int port) {
		try {
			ss = new ServerSocket(port);
			System.out.println("server start!");
			list = new ArrayList<ServerThread>();
			md = new model();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void excute() throws SocketException {
		while (true) {
			try {
				Socket s = ss.accept();
				System.out.println(s.getInetAddress().getAddress());
				System.out.println(s.getInetAddress());
				ServerThread ct = new ServerThread(s,this);
				list.add(ct);
				ct.start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void sendMsg(String str1, String str2, String str3, String str4) {
		String type1 = str1; // talk, draw, enter 등의 프로토콜을 작성
		String type2 = str2; // nickname, color, all 등의 프로토콜을 작성
		String type3 = str3; // non, x, speaker
		String type4 = str4; // none, y, say;
		// 응답(response)
		String str = "";
		Chat ch = new Chat();
		ch.setGroupnum(Integer.parseInt(type2.trim()));
		ch.setTalk(type4);
		md.addChat(ch);
		//talk/all/nickname/say
		if(type2.equals(type2)) {
			str = /* "talk/none/none/"+" */ "["+type3+"]"+type4;
			System.out.println("Message : "+ str);
		}else if(type1.equals("draw")) {
			//ex) draw/color/x/y
			str= type1+"/"+type2+"/"+type3+"/"+type4;
		}else if(type1.equals("enter")) {
			//code
		}

		//모든 유저에게 완성된 str을 스트림을 통해서 보내는 작업
		for(ServerThread e : list) {
			if(e.getGnum().equals(type2))
			e.getPw().println(str);
		}
	}
public static void main(String[] args) {
	try {
		new Ex1_Server(9999).excute();
	} catch (SocketException e) {
		System.out.println("로그아웃 발생");
		e.printStackTrace();
	}
}	
}
	


