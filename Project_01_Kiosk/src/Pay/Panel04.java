package Pay;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import etc.Payment;

class Panel04 extends JPanel implements ActionListener, KeyListener { // 결제창 4번
	// - 현금
	JLabel lbl1, lbl2, lbl3, lbl4;
	JButton btn1, btn2; // 이전, 다음
	JPanel pnl1, pnl2, pnl3, pnl4, pnl5;
	Payment pm;
	JTextField tf;
	ImageIcon btn1Icon, btn2Icon;

	PayMainPanel payMainPanel;
	
	int x = 550;
	int y = 80;

	public Panel04(PayMainPanel payMainPanel) {
		this.setLayout(new GridLayout(0, 1));
		this.setBackground(Color.WHITE);
		this.payMainPanel = payMainPanel;

		pnl4 = new JPanel(null);
		pnl4.setBackground(new Color(240, 235, 229));
		pnl4.setSize(600, 250);
		lbl2 = new JLabel("결제할 금액을 입력해주세요");
		lbl2.setBounds(160, 210, 300, 30);
		lbl2.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		tf = new JTextField(30);
		tf.setBounds(233, 260, 100, 30);
		tf.addKeyListener(this);
		pnl4.add(lbl2);
		pnl4.add(tf);

		pnl3 = new JPanel(null);
		pnl3.setBackground(new Color(240, 235, 229));
		pnl3.setSize(600, 100);
		lbl3 = new JLabel("최종 결제 금액      ");
		lbl3.setBounds(150, 126, 180, 50);
		lbl3.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		lbl4 = new JLabel(Integer.toString(this.payMainPanel.totalPrice));
		lbl4.setBounds(300, 120, 130, 50);
		lbl4.setFont(new Font("맑은 고딕", Font.BOLD, 40));
		pnl3.add(lbl3);
		pnl3.add(lbl4);

		pnl5 = new JPanel(null);
		pnl5.setBackground(new Color(240, 235, 229));
		pnl5.setSize(600, 100);

		// =============================================
		btn1 = new JButton("이전");
		btn1.setBounds(55, 80, 210, 80);
		btn1.addActionListener(this);
		btn1.setPreferredSize(new Dimension(x, y));
		btn1Icon = new ImageIcon("ImageFile/PAY/이전.png");
		Image img = btn1Icon.getImage();
		Image ch = img.getScaledInstance(x + 10, y, Image.SCALE_SMOOTH);
		ImageIcon chgIcon = new ImageIcon(ch);
		btn1.setIcon(chgIcon);

		btn2 = new JButton("다음");
		btn2.setBounds(320, 80, 210, 80);
		btn2.addActionListener(this);
		btn2.setPreferredSize(new Dimension(x, y));
		btn2Icon = new ImageIcon("ImageFile/PAY/다음.png");
		Image img2 = btn2Icon.getImage();
		Image ch2 = img2.getScaledInstance(x + 10, y, Image.SCALE_SMOOTH);
		ImageIcon chgIcon2 = new ImageIcon(ch2);
		btn2.setIcon(chgIcon2);
		// =============================================

		pnl5.add(btn1);
		pnl5.add(btn2);

		this.add(pnl4);
		this.add(pnl3);
		this.add(pnl5);

		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btn1) {
			this.payMainPanel.changePanel(3);
		} else if (e.getSource() == btn2) { // 입력받은 tf와 결제 금액이 같을 때 아래의 메시지
			if (Integer.parseInt(tf.getText()) >= this.payMainPanel.totalPrice) {
				JOptionPane.showMessageDialog(this, "결제가 완료되었습니다", "결제", JOptionPane.PLAIN_MESSAGE);
				if (this.payMainPanel.pnl06.point != 0) {
					File f = new File("FileDataEx3.txt");
					PrintWriter pw = null;
					try {
						FileWriter fw = new FileWriter(f, true);
						pw = new PrintWriter(fw);
						pw.println(this.payMainPanel.pnl06.point + "/t");
					} catch (IOException e1) {
						e1.printStackTrace();
					} finally {
						if (pw != null) {
							pw.close();
						}
					}
				}
			} else {
				JOptionPane.showMessageDialog(this, "결제 금액이 부족합니다.");
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
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