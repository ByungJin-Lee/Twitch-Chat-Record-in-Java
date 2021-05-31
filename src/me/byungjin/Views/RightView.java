package me.byungjin.Views;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import me.byungjin.Managers.Chef;

public class RightView extends JPanel {
	private JTextArea console = null;
	
	public RightView(Font f) {		
		console = new JTextArea();
		console.setEditable(false);				
		console.setLineWrap(true);
		console.setFont(f);
		init();
	}
	
	private void init() {
		this.setLayout(new BorderLayout());
		JLabel chat = new JLabel("Chatting...");
		this.add(chat, BorderLayout.NORTH);
		this.add(new JScrollPane(console), BorderLayout.CENTER);		
		this.setVisible(true);
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
}
