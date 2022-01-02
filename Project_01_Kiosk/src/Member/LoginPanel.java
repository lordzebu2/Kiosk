package Member;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Main.MainFrame;
import AD.ADPanel;
import etc.TranslucenceDialog;

public class LoginPanel extends JPanel implements ActionListener{
	public static final String FILE_NAME = "DataFile/Login.txt";
	private static final int MENUMAIN_PNL = 2;	

	private MainFrame mainFrame;

	JPanel titlePanel,idPanel,pwPanel,loginpnl,createpnl,findIDPWpnl,createIDbnpnl,nonmemberpnl,mainPnl;
	JButton newMember, findID, findPW, nonMember, login;
	JLabel title, id, pw, createImagelb, center;
	JTextField idInput;
	JPasswordField pwInput;
	ImageIcon createImage, loginImage, newMemberImage, nonMemberImage ;	
	Font fontTf = new Font("Consolas", Font.BOLD, 33);
	Font fontTitle = new Font("맑은 고딕", Font.BOLD, 37);
	Font fontidpw = new Font("맑은 고딕", Font.BOLD, 23);

	private ADPanel adPanel;

	Color color = Color.WHITE;
	int x = 550;
	int y = 80;

	public LoginPanel(MainFrame mainFrame){
		this.mainFrame = mainFrame;
		this.setLayout(new BorderLayout());

		adPanel = new ADPanel("행잉");

		title = new JLabel("로그인");
		title.setFont(fontTitle);
		Font font = title.getFont();
		Map attributes = font.getAttributes();
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		title.setFont(font.deriveFont(attributes));
		
		titlePanel = new JPanel();		
		titlePanel.setBackground(color);	
		titlePanel.add(title);

		loginImage = new ImageIcon("ImageFile/MEMBER/로그인버튼.png");
		newMemberImage = new ImageIcon("ImageFile/MEMBER/회원가입버튼.png");
		nonMemberImage = new ImageIcon("ImageFile/MEMBER/비회원주문.png");
		createImage = new ImageIcon("ImageFile/MEMBER/버거킹회원광고.png");

		// 광고
		this.add(adPanel, "North");

		//
		id = new JLabel("아이디");
		id.setFont(fontidpw);
		id.setPreferredSize(new Dimension(100, y));

		idInput = new JTextField();	
		idInput.setFont(fontTf);
		idInput.setPreferredSize(new Dimension(430, y));

		idPanel = new JPanel();
		idPanel.setBackground(color);
		idPanel.add(id);
		idPanel.add(idInput);

		pw = new JLabel("비밀번호");
		pw.setFont(fontidpw);
		pw.setPreferredSize(new Dimension(100, y));

		pwInput = new JPasswordField();
		pwInput.setFont(fontTf);
		pwInput.setEchoChar('*');
		pwInput.setPreferredSize(new Dimension(430, y));

		pwPanel = new JPanel();
		pwPanel.setBackground(color);	
		pwPanel.add(pw);
		pwPanel.add(pwInput);

		login = new JButton("로그인");		
		login.setPreferredSize(new Dimension(x, y));
		login.addActionListener(this);		
		Image img2 = loginImage.getImage();
		Image ch2 = img2.getScaledInstance(x+10, y, Image.SCALE_SMOOTH);
		ImageIcon chgIcon2 = new ImageIcon(ch2);
		login.setIcon(chgIcon2);
		login.setBorderPainted(false);
		//login.setBorderPainted(true); //버튼 테두리 
		//login.setContentAreaFilled(true);// 버튼 배경 표시 설정

		loginpnl = new JPanel();
		loginpnl.setBackground(color);	
		loginpnl.add(login);

		newMember = new JButton("회원가입");
		newMember.addActionListener(this);
		newMember.setPreferredSize(new Dimension(x, y));		
		Image img = newMemberImage.getImage();
		Image ch = img.getScaledInstance(x+10, y, Image.SCALE_SMOOTH);
		ImageIcon chgIcon = new ImageIcon(ch);
		newMember.setIcon(chgIcon);
		newMember.setBorderPainted(false);

		createpnl = new JPanel();
		createpnl.setBackground(color);
		createpnl.add(newMember);

		findID = new JButton("아이디 찾기");
		findID.addActionListener(this);
		findID.setPreferredSize(new Dimension(200, 30));
		findID.setBorderPainted(false); //버튼 테두리 
		findID.setFocusPainted(false);
		findID.setContentAreaFilled(false);// 버튼 배경 표시 설정

		findPW = new JButton("비밀번호 찾기");
		findPW.addActionListener(this);
		findPW.setPreferredSize(new Dimension(200, 30));
		findPW.setBorderPainted(false); //버튼 테두리 
		findPW.setFocusPainted(false);
		findPW.setContentAreaFilled(false);// 버튼 배경 표시 설정

		center = new JLabel("|");

		findIDPWpnl = new JPanel();
		findIDPWpnl.setBackground(color);
		findIDPWpnl.add(findID);
		findIDPWpnl.add(center);
		findIDPWpnl.add(findPW);

		// 5000원쿠폰
		createImagelb = new JLabel();
		createImagelb.setIcon(createImage);

		createIDbnpnl = new JPanel();
		createIDbnpnl.setBackground(color);
		createIDbnpnl.add(createImagelb);

		nonMember = new JButton("비회원 주문");
		nonMember.setPreferredSize(new Dimension(x, y));
		nonMember.addActionListener(this);
		Image img3 = nonMemberImage.getImage();
		Image ch3 = img3.getScaledInstance(x+10, y, Image.SCALE_SMOOTH);
		ImageIcon chgIcon3 = new ImageIcon(ch3);
		nonMember.setIcon(chgIcon3);
		nonMember.setBorderPainted(false);

		nonmemberpnl = new JPanel();
		nonmemberpnl.setBackground(color);	
		nonmemberpnl.add(nonMember);

		mainPnl = new JPanel();//1
		mainPnl.setBackground(color);		

		mainPnl.add(titlePanel);
		mainPnl.add(idPanel);
		mainPnl.add(pwPanel);
		mainPnl.add(loginpnl);
		mainPnl.add(createpnl);
		mainPnl.add(findIDPWpnl);
		mainPnl.add(createIDbnpnl);
		mainPnl.add(nonmemberpnl);		

		this.add(mainPnl, "Center");	

		setVisible(true);
	}

	public void startAD() {
		this.adPanel.startAD();
	}

	public void reset() {
		idInput.setText("");
		pwInput.setText("");
	}

	@Override
	public void actionPerformed(ActionEvent e) {					
		File f = new File(FILE_NAME);
		FileReader fr = null;
		BufferedReader br = null;      
		String s = null;

		// 번호, 이름, id, pw, 폰번호, 생일, 이메일, 성별, 적립금
		
		if(e.getSource()==login){         
			try {
				fr = new FileReader(f);
				br = new BufferedReader(fr);

				while((s=br.readLine()) != null){
					if(s.split("/")[2].equals(idInput.getText()) && s.split("/")[3].equals(pwInput.getText())){
					//	System.out.println("로그인완료");
						
						this.mainFrame.setMemberID(s.split("/")[3]);
						//this.mainFrame.setMembershipPoint(Integer.parseInt(s.split("/")[8]));
						
					//	System.out.println("ID : " + this.mainFrame.getMemberID());
						
						JLabel label = new JLabel("로그인 되었습니다.");
						label.setFont(new Font("맑은 고딕", Font.BOLD, 18));
						JOptionPane.showMessageDialog(this, label);
						
						this.adPanel.stopAD();
						this.mainFrame.nextPnl(MENUMAIN_PNL);
						break;
					} else {
						JLabel label = new JLabel("가입하지 않은 아이디이거나, 잘못된 비밀번호입니다.");
						label.setFont(new Font("맑은 고딕", Font.BOLD, 18));
						JOptionPane.showMessageDialog(this, label);
						reset();
						break;
					}
				}
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} else if(e.getSource()==newMember){
			NewID newid = new NewID(this.mainFrame, this);

			mainPnl.removeAll();	
			mainPnl.add(newid);			
			mainPnl.revalidate();			
			mainPnl.repaint();			


		}else if(e.getSource()==findID){
			TranslucenceDialog tranDialog = new TranslucenceDialog(mainFrame);
			new FindID(this.mainFrame, tranDialog);
			tranDialog.dispose();

		}else if(e.getSource()==findPW){
			TranslucenceDialog tranDialog = new TranslucenceDialog(mainFrame);
			new FindPW(this.mainFrame, tranDialog);		
			tranDialog.dispose();

		}else if(e.getSource()==nonMember){
			this.adPanel.stopAD();
			this.mainFrame.nextPnl(MENUMAIN_PNL);
		}					
	}
}
