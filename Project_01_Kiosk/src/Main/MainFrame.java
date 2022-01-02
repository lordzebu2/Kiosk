package Main;

import Menu.*;
import Pay.*;
import Member.*;

import javax.swing.JFrame;

public class MainFrame extends JFrame {
	private static final int WIDTH = 600;
	private static final int HEIGHT= 1000;	
	private static final int START_PNL = 0;	
	private static final int LOGIN_PNL = 1;
	private static final int MENUMAIN_PNL = 2;	
	private static final int PAY_PNL = 3;

	private static MainFrame mainFrame;
	private StartPanel startPanel;
	private LoginPanel loginPanel;
	private MenuMainPanel menuMainPanel;
	private PayMainPanel payPanel;

	private int totalPrice;
	
	private String memberID = "";
	private int membershipPoint;

	public MainFrame() {			
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		startPanel = new StartPanel(this);
		
		//this.setResizable(false);
		this.setSize(WIDTH, HEIGHT);
		this.setLocationRelativeTo(null);	// 프레임을 윈도우화면의 중앙에 배치. size를 설정한후에 설정해야한다.
		this.setVisible(true);
	}
	// 번호, 이름, id, pw, 폰번호, 생일, 이메일, 성별
	public void setTotalPrice(int totalPrice) 			{	this.totalPrice = totalPrice;			}
//	public void setMembershipPoint(int membershipPoint) {	this.setMemberInfo(8, String.valueOf(membershipPoint));	}
	public void setMemberID(String memberID)			{	this.memberID = memberID;				}
	
	public int getMembershipPoint() {	return membershipPoint;	}
	public String getMemberID()		{	return memberID;		}
	
	// 광고는 Thread로 돌려서 따로 실행
	public void pnlManeger(int selPnl) {
		this.getContentPane().removeAll();
		if(selPnl == START_PNL) {
			this.add(startPanel);	// 패널 담기
			startPanel.startAD();	// 광고 실행 (Thread)
		} else if(selPnl == LOGIN_PNL) {
			loginPanel = new LoginPanel(this);
			this.add(loginPanel);
			loginPanel.startAD();
		} else if(selPnl == MENUMAIN_PNL) {
			menuMainPanel = new MenuMainPanel(this);
			this.add(menuMainPanel);
			menuMainPanel.startAD();
		} else if(selPnl == PAY_PNL) {
			payPanel = new PayMainPanel(this, totalPrice);
			this.add(payPanel);
			payPanel.startAD();
		}
		this.revalidate();
		this.repaint();
	}	
	
	// 전환 화면 선택 
	public void nextPnl(int selPnl) {
		mainFrame.pnlManeger(selPnl);
	}
	
	public static void main(String[] args) {
		mainFrame = new MainFrame();
		//mainFrame.pnlManeger(START_PNL);		
		
		//test
		mainFrame.pnlManeger(START_PNL);		
	}
}
