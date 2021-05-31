package me.byungjin.Views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import me.byungjin.ActionListeners.ChannelEnter;
import me.byungjin.ActionListeners.ConnectClick;
import me.byungjin.ActionListeners.DisconnectClick;
import me.byungjin.ActionListeners.QuitClick;
import me.byungjin.ActionListeners.UpdateClick;
import me.byungjin.Managers.Chef;

public class LeftView extends JPanel {
	//0 1 2 3 - connect, disconnect, quit, setting
	private JButton[] buttons = new JButton[4];	
	private JTextField channelTxt; 
	private GraphView grape;
	//console
	private JTextArea console;
	
	//bg, fg, font
	private Color bg, fg;
	private Font font;
	
	public LeftView() {
		this.setBackground(bg);		
		this.setLayout(new BorderLayout());
									
		bg = new Color(0,0,0,60);
		fg = new Color(0,0,0);
		font = new Font("¸¼Àº °íµñ", Font.PLAIN, 14);
				
		String[] btns = {"Quit","Connect", "Disconnect", "Un"};
		for(int i = 0; i < buttons.length; i++) {
			buttons[i] = new JButton(btns[i]);
			buttons[i].setForeground(fg);
			buttons[i].setFont(font);
		}							
		JPanel top = new JPanel();
		
		channelTxt = new JTextField(15);
		channelTxt.setForeground(fg);
		channelTxt.setFont(font);
			
		top.add(new JLabel("Channel"));
		top.add(channelTxt);				
		
		for(int i = 0; i < buttons.length; i++) {
			top.add(buttons[i]);
		}		
		add(top, BorderLayout.NORTH);
		
		GridBagConstraints gbc = new GridBagConstraints();		
		JPanel middle = new JPanel();		
		middle.setLayout(new GridBagLayout());
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weighty = 1;
		grape = new GraphView();
		JScrollPane gsc = new JScrollPane(grape);				
		gbc.weightx = 0.7;
		gbc.gridx = 0;
		gbc.gridy = 0;		
		middle.add(gsc, gbc);			
		JPanel ml = new JPanel();
		ml.add(new JLabel("Grape"));
		gbc.weightx = 0.3;
		gbc.gridx = 1;
		gbc.gridy = 0;
		middle.add(ml, gbc);		
		console = new JTextArea();
		console.setEditable(false);	
		console.setLineWrap(true);					
		JScrollPane gcs = new JScrollPane(console);
		gbc.weightx = 0.7;
		gbc.gridx = 0;
		gbc.gridy = 1;
		JPanel m2l = new JPanel();
		m2l.add(new JLabel("Console"));
		middle.add(gcs, gbc);
		gbc.weightx = 0.3;
		gbc.gridx = 1;
		gbc.gridy = 1;		
		middle.add(m2l, gbc);		
		this.add(middle, BorderLayout.CENTER);		
		bindEvents();
	}		
	
	public void bindEvents() {
		//0 1 2 3 - connect, disconnect, quit, setting
		channelTxt.addActionListener(new ChannelEnter());
		buttons[1].addActionListener(new ConnectClick());
		buttons[2].addActionListener(new DisconnectClick());
		buttons[0].addActionListener(new QuitClick());	
		buttons[3].addActionListener(new UpdateClick());
	}
	
	
	public void setConnectButtonAction(ActionListener l) {
		this.buttons[0].addActionListener(l);
	}
	public void setDisconnectButtonAction(ActionListener l) {
		this.buttons[1].addActionListener(l);
	}
	public void setQuitButtonAction(ActionListener l) {
		this.buttons[2].addActionListener(l);
	}
	public void setUpdateButtonAction(ActionListener l) {
		this.buttons[3].addActionListener(l);
	}	
	public String getChannel() {
		if(channelTxt != null) {
			return this.channelTxt.getText();
		}
		return null;
	}
	public void setChannel(String s) {
		if(channelTxt != null) {
			channelTxt.setText(s);
		}
	}	
	public void addChat(String user, String content) {
		try {
			this.console.append("["+user+"] "+ content + "\n");
			if(Chef.onScroll) {
				this.console.setCaretPosition(this.console.getDocument().getLength());
			}
		}catch(Exception x){
			System.out.println(x.getMessage());
		}
	}
	public GraphView getGraph() {
		return this.grape;
	}
}
