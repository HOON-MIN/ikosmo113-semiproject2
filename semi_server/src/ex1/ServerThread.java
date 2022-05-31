package ex1;
// 내부 클래스로 만들어도 되지만 주로 서버는 thread로하고 관리를 gui에 연결한다

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ServerThread extends Thread{
// 서러의 주소를 가져야 한다
	private Socket socket;
	private Ex1_Server server;
	private PrintWriter pw;
	private BufferedReader in;
	private String groupnum;
	public ServerThread(Socket s, Ex1_Server ex1_Server) {
			this.socket = s;
			this.server = ex1_Server;
			try {
				pw = new PrintWriter(s.getOutputStream(),true);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	public String getGnum() {
		return groupnum;
	}
	//서버가 각 소켓에게 전송할 객체를 제공
	public PrintWriter getPw() {
	
		return pw;
	

	}
	// 스레드에게 할일 작성하기
	@Override

	public void run() {
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			System.out.println(groupnum);
			while (true) {
				String msg = in.readLine();
				System.out.println("Client Msg : "+msg);
				transMsg(msg);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	public void transMsg(String msg) {
		StringTokenizer stn = new StringTokenizer(msg,"/");
		String str1 = stn.nextToken();
		String str2 = stn.nextToken();
		groupnum = str2.trim();
		String str3 = stn.nextToken();
		String str4 = stn.nextToken();
		System.out.println("Log Token : "+str1);
		System.out.println("Log Token : "+str2);
		System.out.println("Log Token : "+str3);
		System.out.println("Log Token : "+str4);
		server.sendMsg(str1, str2, str3, str4);
		
	}
	
}
