package me.byungjin.Windows;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;

import me.byungjin.Datas.Chatter;
import me.byungjin.Managers.ChannelManager;
import me.byungjin.Views.GraphView;
import me.byungjin.Views.LeftView;
import me.byungjin.Views.MenuBarView;
import me.byungjin.Views.RightView;

public class MainWindow extends JFrame {	
	private LeftView left;
	private RightView right;
	private Font f;	
	
	public MainWindow(String title) {		
//		setUndecorated(true);
//		bg = new Color(0,0,0,100);
//		fg = new Color(255,255,255);
		f = new Font("¸¼Àº °íµñ", Font.PLAIN, 14);	
		setTitle(title);		
		setResizable(true);
		setLocationRelativeTo(null);		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);								
		setLayout(new GridBagLayout());
		
		left = new LeftView();
		left.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 0));
		right = new RightView(f);
		right.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
		
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weighty = 1;
		gbc.weightx = 0.2;
		gbc.gridx = 0;
		gbc.gridy = 0;
		this.add(left, gbc);		
		gbc.weightx = 0.1;
		gbc.gridx = 1;
		gbc.gridy = 0;
		this.add(right, gbc);
				
		this.setJMenuBar(new MenuBarView());
		setSize(900,620);
		setVisible(true);	
	}
	public void chat(String u, String c, boolean syst) {
		if(syst) {
			this.left.addChat(u, c);
		}else {
			Chatter r = ChannelManager.isChatter(u, c);
			if(r != null) {
				int total = ChannelManager.getTotalChat();
				int count = r.getCount();
				int length = r.getLength(); 
				this.left.addChat(u, String.format("C : %d - L : %d / AL : %.2f - W : %.2f", count, length, length/(float)count, count/(float)total*100));
			}
			this.right.addChat(u, c);
		}
	}
	public GraphView getGraphicsGraph() {
		return this.left.getGraph();
	}
}
