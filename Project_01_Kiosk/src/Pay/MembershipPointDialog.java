package Pay;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import Main.MainFrame;

public class MembershipPointDialog extends JDialog implements ActionListener, KeyListener {
	Color isColor = Color.WHITE;

	private MainFrame mainFrame;
	ServiceOptionPanel serviceOptionPanel;
	DefaultTableModel model;
	JTable tbl;
	JPanel btnPnl, pricePnl;
	JLabel inputCodeLbl, totalPriceLbl, membershipCheckLbl;
	JButton cancelBtn, okBtn, inputCodeBtn;
	JPanel pnlS, tablePnl, inputPnl;
	JTextField tf;

	int isTotalPrice;

	public MembershipPointDialog(MainFrame mainFrame, ServiceOptionPanel serviceOptionPanel) {
		super(mainFrame, "MembershipPoint", true);

		this.mainFrame = mainFrame;
		this.serviceOptionPanel = serviceOptionPanel;

		this.setLayout(new BorderLayout());
		this.setSize(550, 350);



		inputPnl = new JPanel(new GridLayout(0, 1));
		inputPnl.setBackground(isColor);

		JPanel membershipCheckPnl = new JPanel(new FlowLayout(FlowLayout.CENTER));
		membershipCheckLbl = new JLabel();	// 적립될 내용
		membershipCheckLbl.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		setMembershipPoint();	
		membershipCheckPnl.add(membershipCheckLbl);
		inputPnl.add(membershipCheckPnl);

		JPanel curMemPnl = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel lbl = new JLabel("회원님의 현재 적립금은 ");
		lbl.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		JLabel lbl1 = new JLabel(String.valueOf(this.mainFrame.getMembershipPoint()));
		lbl1.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		JLabel lbl2 = new JLabel("점 입니다.");
		lbl2.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		curMemPnl.add(lbl);
		curMemPnl.add(lbl1);
		curMemPnl.add(lbl2);
		inputPnl.add(curMemPnl);

		JPanel inputCodePnl = new JPanel(new FlowLayout(FlowLayout.CENTER));
		inputCodeLbl = new JLabel("적립금");
		inputCodeLbl.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		tf = new JTextField(15);
		inputCodeBtn = new JButton("사용");
		inputCodeBtn.setFocusPainted(false);
		inputCodeBtn.addActionListener(this);
		inputCodePnl.add(inputCodeLbl);
		inputCodePnl.add(tf);
		inputCodePnl.add(inputCodeBtn);
		inputPnl.add(inputCodePnl);


		this.add(inputPnl, "Center");

		pnlS = new JPanel(new BorderLayout());
		pnlS.setBackground(isColor);

		pricePnl = new JPanel(new FlowLayout(FlowLayout.CENTER));
		pricePnl.setBackground(isColor);	
		JLabel totalPriceStr = null;
		totalPriceStr = setPriceLbl(totalPriceStr, "총 결제금액  ", Color.BLACK);
		totalPriceLbl = setPriceLbl(totalPriceLbl, String.valueOf(this.serviceOptionPanel.totalPrice), Color.RED);
		isTotalPrice = this.serviceOptionPanel.totalPrice;
		JLabel won = null;
		won = setPriceLbl(won, "원", Color.RED);
		pnlS.add(pricePnl, "North");

		btnPnl = new JPanel();
		btnPnl.setBackground(isColor);
		cancelBtn = setBtnInit(cancelBtn, "취소");
		okBtn = setBtnInit(okBtn, "확인");
		pnlS.add(btnPnl, "South");
		this.add(pnlS, "South");

		//this.setUndecorated(true); 	// 타이틀바 삭제
		this.setBackground(isColor);
		this.setLocationRelativeTo(this.mainFrame);	// 중앙 배치. size를 설정한후에 설정해야한다.
		this.setVisible(true);	
	}

	public JLabel setPriceLbl(JLabel lbl, String str, Color color) {
		lbl = new JLabel(str);
		lbl.setFont(new Font("맑은 고딕", Font.BOLD, 28));
		lbl.setForeground(color);
		pricePnl.add(lbl);
		return lbl;
	}

	public JButton setBtnInit(JButton btn, String str) {
		btn = new JButton(str);
		btn.setPreferredSize(new Dimension(200,50));
		btn.setFont(new Font("맑은 고딕", Font.BOLD, 20));		// 수정 ()
		btn.addActionListener(this);
		btn.setFocusPainted(false);
		btnPnl.add(btn);
		return btn;
	}

	public void setMembershipPoint() {
		int point = (int)(this.serviceOptionPanel.totalPrice * 0.01);
		membershipCheckLbl.setText(point + "점이 적립될 예정입니다.");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		File f = new File("DataFile/Login.txt");
		FileWriter fw = null;
		PrintWriter pw = null;
		FileReader fr = null;
		BufferedReader br = null;

		int memPoint = 0;
		if(e.getSource() == okBtn) {
			this.serviceOptionPanel.setTotalPrice(isTotalPrice);
			int addrPoint = this.mainFrame.getMembershipPoint() - memPoint;


			/*
			try {
				fr = new FileReader(f);
				br = new BufferedReader(fr);
				fw = new FileWriter(f);
				pw = new PrintWriter(fw);

				// 번호, 이름, id, pw, 폰번호, 생일, 이메일, 성별
				String s = null;
				while((s=br.readLine()) != null){
					if(s.split("/")[2].equals(this.mainFrame.getMemberID())){	
					pw.println(this.mainFrame.getMemberInfo(Integer.parseInt(s.split("/")[0]), 0)+
								"/"+this.mainFrame.getMemberInfo(Integer.parseInt(s.split("/")[0]), 1)+
								"/"+this.mainFrame.getMemberInfo(Integer.parseInt(s.split("/")[0]), 2)+
								"/"+this.mainFrame.getMemberInfo(Integer.parseInt(s.split("/")[0]), 3)+
								"/"+this.mainFrame.getMemberInfo(Integer.parseInt(s.split("/")[0]), 4)+
								"/"+this.mainFrame.getMemberInfo(Integer.parseInt(s.split("/")[0]), 5)+
								"/"+this.mainFrame.getMemberInfo(Integer.parseInt(s.split("/")[0]), 6)+
								"/"+this.mainFrame.getMemberInfo(Integer.parseInt(s.split("/")[0]), 7)+
								addrPoint);
					}
				}



			}catch (FileNotFoundException e1) {				
				e1.printStackTrace();
			}catch (IOException e1) {					
				e1.printStackTrace();
			} finally {
				if(pw != null){
					pw.close();
				}
			}
			*/
			this.dispose();
			
		} else if(e.getSource() == cancelBtn) {
			this.dispose();
		} else if(e.getSource() == inputCodeBtn) {
			memPoint = Integer.parseInt(tf.getText());
			isTotalPrice -= memPoint;
			if(isTotalPrice < 0)
				isTotalPrice = 0;
			totalPriceLbl.setText(String.valueOf(isTotalPrice));
		}	
	}

	@Override
	public void keyPressed(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {
		/*	      int p = Integer.parseInt(tf.getText());
	      if (p > a - b) {
	         tf.setText(Integer.toString(a - b));
	      }*/

	}

	@Override
	public void keyTyped(KeyEvent e) {
		char c = e.getKeyChar();
		if (!Character.isDigit(c)) {
			e.consume();
			return;
		}

	}
}
