package Pay;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import etc.Payment;

class Panel05 extends JPanel implements ActionListener, Runnable { // 결제창 4번 -
	// 카드
	JLabel lbl1, lbl2, lbl3, lbl4, lbl5, lbl6;
	JButton btn1; // 이전, 다음
	JPanel pnl1, pnl2, pnl3, pnl4, pnl5;
	Payment pm;
	Boolean thread_start = false;
	Font font = new Font("Serif", Font.BOLD, 50);
	ImageIcon btn1Icon;

	PayMainPanel panel00;
	
	int x = 550;
	int y = 80;

	public Panel05(PayMainPanel panel00) {
		this.setLayout(new GridLayout(0, 1));
		this.setBackground(new Color(240, 235, 229));
		this.panel00 = panel00;

		pnl4 = new JPanel(null);
		pnl4.setBackground(new Color(240, 235, 229));
		lbl5 = new JLabel("카드를 투입해주세요");
		lbl5.setBounds(200, 190, 200, 30);
		lbl5.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		lbl6 = new JLabel("");
		lbl6.setBounds(275, 230, 100, 120);
		lbl6.setFont(font); // 폰트 변경
		pnl4.add(lbl5);
		pnl4.add(lbl6);

		pnl3 = new JPanel(null);
		pnl3.setBackground(new Color(240, 235, 229));
		pnl3.setSize(600, 100);
		lbl3 = new JLabel("최종 결제 금액      ");
		lbl3.setBounds(150, 126, 180, 50);
		lbl3.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		lbl4 = new JLabel(Integer.toString(this.panel00.totalPrice));
		lbl4.setBounds(300, 120, 130, 50);
		lbl4.setFont(new Font("맑은 고딕", Font.BOLD, 40));
		pnl3.add(lbl3);
		pnl3.add(lbl4);

		pnl5 = new JPanel(null);
		pnl5.setBackground(new Color(240, 235, 229));
		pnl5.setSize(600, 100);

		// =============================================
		btn1 = new JButton("이전");
		btn1.setBounds(190, 80, 210, 80);
		btn1.addActionListener(this);
		btn1.setPreferredSize(new Dimension(x, y));
		btn1Icon = new ImageIcon("ImageFile/PAY/이전.png");
		Image img = btn1Icon.getImage();
		Image ch = img.getScaledInstance(x + 10, y, Image.SCALE_SMOOTH);
		ImageIcon chgIcon = new ImageIcon(ch);
		btn1.setIcon(chgIcon);
		// ========================================================

		pnl5.add(btn1);

		this.add(pnl4);
		this.add(pnl3);
		this.add(pnl5);

		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btn1) {
			this.panel00.changePanel(3);
			thread_start = false;
		}
	}

	@Override
	public void run() {
		try {
			for (int i = Integer.parseInt(Thread.currentThread().getName()); i >= 0; i--) {
				lbl6.setText(Integer.toString(i));
				for (int j = 0; j < 10; j++) {
					if (thread_start == false) {
						return;
					}
					Thread.sleep(100);
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(this, "결제가 완료되었습니다", "결제", JOptionPane.PLAIN_MESSAGE);
		if (this.panel00.pnl06.point != 0) {
			File f = new File("FileDataEx3.txt");
			PrintWriter pw = null;
			try {
				FileWriter fw = new FileWriter(f, true);
				pw = new PrintWriter(fw);
				pw.println(this.panel00.pnl06.point + "/t");
			} catch (IOException e1) {
				e1.printStackTrace();
			} finally {
				if (pw != null) {
					pw.close();
				}
			}
		}
	}
}