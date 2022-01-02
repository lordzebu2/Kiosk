package Member;

import Main.MainFrame;
import etc.TranslucenceDialog;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

class FindPW extends JDialog implements ActionListener{
	public static final String FILE_NAME = "DataFile/Login.txt";
	JLabel titlelb, name, monthday, id;
	JTextField nameTf, monthdayTf, idTf;
	JButton find;
	Color color;
	ImageIcon bt;
	
	private MainFrame mainFrame;
	private TranslucenceDialog tranDialog;
	
	Font fontTf = new Font("Consolas", Font.PLAIN, 16);
	Font fontTitle = new Font("맑은 고딕", Font.BOLD, 13);
	Font fontInfo = new Font("맑은 고딕", Font.BOLD, 15);

	FindPW(MainFrame mainFrame, TranslucenceDialog tranDialog){
		super(mainFrame, "", true);
		this.mainFrame = mainFrame;
		this.tranDialog = tranDialog;
		
		setSize(340, 400);
		setLayout(new GridLayout(0,1));
		color = new Color(255,244,216);
		bt = new ImageIcon("비밀번호찾기.png");

		titlelb = new JLabel("가입 시 등록한 정보를 입력해 주세요.");
		titlelb.setFont(fontTitle);
		titlelb.setPreferredSize(new Dimension(225, 70));
		
		name = new JLabel("이름");
		name.setFont(fontInfo);
		name.setPreferredSize(new Dimension(100, 40));
		monthday = new JLabel("생년월일");
		monthday.setFont(fontInfo);
		monthday.setPreferredSize(new Dimension(100, 40));
		id = new JLabel("ID");
		id.setFont(fontInfo);
		id.setPreferredSize(new Dimension(100, 40));

		nameTf = new JTextField();
		nameTf.setFont(fontTf);
		nameTf.setPreferredSize(new Dimension(200, 30));
		monthdayTf = new JTextField();
		monthdayTf.setFont(fontTf);
		monthdayTf.setPreferredSize(new Dimension(200, 30));
		idTf = new JTextField();
		idTf.setFont(fontTf);
		idTf.setPreferredSize(new Dimension(200, 30));

		find = new JButton("비밀번호 찾기");
		Image img = bt.getImage();
		Image ch = img.getScaledInstance(460, 80, Image.SCALE_SMOOTH);
		ImageIcon chgIcon = new ImageIcon(ch);
		find.setIcon(chgIcon);
		
		find.setPreferredSize(new Dimension(310, 55));
		find.addActionListener(this);		

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

		pl5.add(titlelb);

		pl1.add(name);
		pl1.add(nameTf);

		pl2.add(monthday);
		pl2.add(monthdayTf);

		pl3.add(id);
		pl3.add(idTf);
		
		pl4.add(find);

		this.add(pl5);
		this.add(pl3);
		this.add(pl1);
		this.add(pl2);
		this.add(pl4);

		//this.setUndecorated(true); 	// 타이틀바 삭제
		this.setLocationRelativeTo(this.mainFrame);	// 중앙 배치. size를 설정한후에 설정해야한다.
		this.setVisible(true);	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		File f = new File(FILE_NAME);
		boolean idCheck = false;
		JLabel label = new JLabel();
		label.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		
		// 번호, 이름, id, pw, 폰번호, 생일, 이메일, 성별
		
		if(nameTf.getText().equals("") || monthdayTf.getText().equals("") || idTf.getText().equals("")) {
			label.setText("모든 정보를 입력해 주세요.");
			JOptionPane.showMessageDialog(this, label);
		}
		else {
			try {
				FileReader fr = new FileReader(f);
				BufferedReader br = new BufferedReader(fr);
				if(e.getSource()==find){
					if(!nameTf.getText().equals("") && !monthdayTf.getText().equals("") 
							&& !idTf.getText().equals("")){
						
						String s = null;
						while((s=br.readLine()) != null){
							if(s.split("/")[1].equals(nameTf.getText())&&
									s.split("/")[5].equals(monthdayTf.getText())&&
									s.split("/")[2].equals(idTf.getText())){
								
								idCheck = true;
								break;
							}
						}	
						if(idCheck) {
							label.setText("비밀번호는 "+s.split("/")[3]+" 입니다.");
							JOptionPane.showMessageDialog(this, label);
						} else if(!idCheck) {
							label.setText("잘못된 입력 이거나 가입이력이 없습니다.");
							JOptionPane.showMessageDialog(this, label);
						}
					}
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}		
		}
	}	
}

