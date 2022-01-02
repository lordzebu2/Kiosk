package Member;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import Main.MainFrame;
import etc.TranslucenceDialog;

class NewID extends JPanel implements ActionListener{
	private static final int LOGIN_PNL = 1;
	public static final String FILE_NAME = "DataFile/Login.txt";

	JLabel join, name, id, pw, phon, monthday, email, menlb, womenlb, center;
	JTextField nameTf, idTf, pwTf, phonTf, monthdayTf, emailTf;
	JButton createbt, goLogin, check;
	JRadioButton men, women;

	ImageIcon loginIcon = new ImageIcon("ImageFile/MEMBER/로그인버튼검.png");
	ImageIcon creat = new ImageIcon("ImageFile/MEMBER/회원가입빨.png");
	Font fontTf = new Font("Consolas", Font.PLAIN, 28);
	Font fontTitle = new Font("맑은 고딕", Font.BOLD, 28);
	Font fontInfo = new Font("맑은 고딕", Font.BOLD, 20);

	Color color = new Color(255,244,216);

	File f = null;
	FileWriter fwriter = null;
	PrintWriter pwriter = null;
	FileReader fr = null;
	BufferedReader br = null;
	boolean isExsit;

	int memberNum;//회원번호
	int y = 60;//컴포넌트위아래
	int x = 350;//좌우

	LoginPanel loginPanel;
	MainFrame mainFrame;

	NewID(MainFrame mainFrame, LoginPanel loginPanel){
		this.mainFrame = mainFrame;
		this.loginPanel = loginPanel;		
		this.setBackground(color);

		join = new JLabel("회원가입");
		join.setFont(fontTitle);
		join.setPreferredSize(new Dimension(x-210, y));
		name = new JLabel("이름");
		name.setFont(fontInfo);
		name.setPreferredSize(new Dimension(x-230, y));
		id = new JLabel(" ID");
		id.setFont(fontInfo);
		id.setPreferredSize(new Dimension(x-227, y));
		pw = new JLabel("PW");
		pw.setFont(fontInfo);
		pw.setPreferredSize(new Dimension(x-230, y));
		phon = new JLabel("핸드폰번호");
		phon.setFont(fontInfo);
		phon.setPreferredSize(new Dimension(x-230, y));
		monthday = new JLabel("생년월일");
		monthday.setFont(fontInfo);
		monthday.setPreferredSize(new Dimension(x-230, y));
		email = new JLabel("e-mail");
		email.setFont(fontInfo);
		email.setPreferredSize(new Dimension(x-230, y));

		menlb = new JLabel("남");
		menlb.setFont(fontInfo);
		menlb.setPreferredSize(new Dimension(60, y));		
		center = new JLabel("          |          ");
		center.setFont(fontInfo);
		womenlb = new JLabel("여");
		womenlb.setFont(fontInfo);
		womenlb.setPreferredSize(new Dimension(60, y));

		nameTf = new JTextField();
		nameTf.setFont(fontTf);
		nameTf.setPreferredSize(new Dimension(x, y));
		idTf = new JTextField();
		idTf.setFont(fontTf);
		idTf.setPreferredSize(new Dimension(x-100, y));
		pwTf = new JTextField();
		pwTf.setFont(fontTf);
		pwTf.setPreferredSize(new Dimension(x, y));
		phonTf = new JTextField();
		phonTf.setFont(fontTf);
		phonTf.setPreferredSize(new Dimension(x, y));
		monthdayTf = new JTextField();
		monthdayTf.setFont(fontTf);
		monthdayTf.setPreferredSize(new Dimension(x, y));
		emailTf = new JTextField();
		emailTf.setFont(fontTf);
		emailTf.setPreferredSize(new Dimension(x, y));

		men = new JRadioButton();
		women = new JRadioButton();
		ButtonGroup gr = new ButtonGroup();
		gr.add(men);
		gr.add(women);
		men.setBackground(color);
		women.setBackground(color);

		createbt = new JButton("가입");
		Image img = creat.getImage();
		Image ch = img.getScaledInstance(x+130, y+20, Image.SCALE_SMOOTH);
		ImageIcon chgIcon = new ImageIcon(ch);
		createbt.setIcon(chgIcon);
		createbt.setPreferredSize(new Dimension(x+120, y));
		createbt.addActionListener(this);
		createbt.setBorderPainted(false);

		goLogin = new JButton("로그인");
		Image img2 = loginIcon.getImage();
		Image ch2 = img2.getScaledInstance(x+130, y+20, Image.SCALE_SMOOTH);
		ImageIcon chgIcon2 = new ImageIcon(ch2);
		goLogin.setIcon(chgIcon2);
		goLogin.setPreferredSize(new Dimension(x+120, y));
		goLogin.addActionListener(this);
		goLogin.setBorderPainted(false);

		check = new JButton("ID중복확인");		
		check.setPreferredSize(new Dimension(x-250, y));
		check.setBorderPainted(false);
		check.setFocusPainted(false);
		check.setContentAreaFilled(false);
		check.addActionListener(this);

		JPanel pl1 = new JPanel();
		pl1.setBackground(color);
		JPanel pl2 = new JPanel();
		pl2.setBackground(color);
		JPanel pl3 = new JPanel();
		pl3.setBackground(color);
		JPanel pl4 = new JPanel();
		pl4.setBackground(color);
		JPanel pl5 = new JPanel();
		pl5.setBackground(color);
		JPanel pl6 = new JPanel();
		pl6.setBackground(color);
		JPanel pl7 = new JPanel();
		pl7.setBackground(color);
		JPanel pl8 = new JPanel();		
		pl8.setBackground(color);
		JPanel pl10 = new JPanel();
		pl10.setBackground(color);
		JPanel pl11 = new JPanel();
		pl11.setBackground(color);

		pl8.add(join);

		pl1.add(id);
		pl1.add(idTf);
		pl1.add(check);

		pl7.add(name);
		pl7.add(nameTf);		

		pl2.add(pw);
		pl2.add(pwTf);

		pl3.add(phon);
		pl3.add(phonTf);

		pl4.add(monthday);
		pl4.add(monthdayTf);

		pl5.add(email);
		pl5.add(emailTf);

		pl6.add(menlb);
		pl6.add(men);
		pl6.add(center);
		pl6.add(womenlb);
		pl6.add(women);

		pl10.add(createbt);

		pl11.add(goLogin);

		JPanel mainpp = new JPanel(new GridLayout(0,1));
		//this.setBackground(Color.BLACK);

		mainpp.add(pl8);
		mainpp.add(pl1);		
		mainpp.add(pl7);		
		mainpp.add(pl2);
		mainpp.add(pl3);
		mainpp.add(pl4);
		mainpp.add(pl5);
		mainpp.add(pl6);
		mainpp.add(pl10);
		mainpp.add(pl11);

		add(mainpp);

		setVisible(true);
	}

	public void infoReset(){
		nameTf.setText("");
		idTf.setText("");
		idTf.setBackground(Color.WHITE);
		idTf.setEditable(true);
		pwTf.setText("");
		phonTf.setText("");
		monthdayTf.setText("");
		emailTf.setText("");		
	}

	public boolean idOverlapCheck(boolean check) {
		boolean idOverlapCheck = false;
		String s = null;
		try {
			fr = new FileReader(f);
			br = new BufferedReader(fr);				

			while((s=br.readLine()) != null){
				if(s.split("/")[2].equals(idTf.getText())){							
					isExsit = true;	
					idOverlapCheck = true;
					break;
				}
				memberNum = Integer.parseInt(s.split("/")[0]);
			}
			if(isExsit == false && check){
				JLabel label = new JLabel("생성가능한 ID입니다.");
				label.setFont(new Font("맑은 고딕", Font.BOLD, 18));
				JOptionPane.showMessageDialog(this, label);
			}										
			memberNum++;

		}catch (FileNotFoundException e1) {				
			e1.printStackTrace();
		}catch (IOException e1) {					
			e1.printStackTrace();
		}

		return idOverlapCheck;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		f = new File(FILE_NAME);
		isExsit = false;		
		JLabel label = new JLabel();
		label.setFont(new Font("맑은 고딕", Font.BOLD, 18));

		if(e.getSource()==check){
			String s = null;

			if(idTf.getText().equals("")){
				label.setText("ID를 입력해 주세요.");
				JOptionPane.showMessageDialog(this, label);
			}else if(!idTf.getText().equals("")){			
				idOverlapCheck(true);
			}
		}else if(e.getSource()==goLogin){
			this.removeAll();
			this.mainFrame.pnlManeger(LOGIN_PNL);
		}

		if(isExsit == false){
			try {			
				if(e.getSource()==createbt){
					if(idTf.getText().equals("")) {
						label.setText("ID를 입력해 주세요.");
						JOptionPane.showMessageDialog(this, label);
					} else {
						if(!idOverlapCheck(false)) {
							fwriter = new FileWriter(f, true);
							pwriter = new PrintWriter(fwriter);
							String gender = null;
							if(men.isSelected()){
								gender = "남";
							}else{
								gender = "여";
							}					
							// 번호, 이름, id, pw, 폰번호, 생일, 이메일, 성별
							pwriter.println(memberNum+"/"+nameTf.getText()+"/"+idTf.getText()+"/"+pwTf.getText()+"/"
									+phonTf.getText()+"/"+monthdayTf.getText()+"/"
									+emailTf.getText()+"/"+gender+"/"+"5000");
							label.setText("환영합니다! 가입이 완료되었습니다.");
							JOptionPane.showMessageDialog(this, label);
							infoReset();
						} else {
							label.setText("중복된 ID입니다.");
							JOptionPane.showMessageDialog(this, label);
						}
					}
				}
			}catch (IOException e1) {
				e1.printStackTrace();

			}finally {
				if(pwriter != null){
					pwriter.close();
				}
			}		
		} else {
			label.setText("중복된 ID입니다.");
			JOptionPane.showMessageDialog(this, label);
		}
	}	
}
