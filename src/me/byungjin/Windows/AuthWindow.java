package me.byungjin.Windows;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import me.byungjin.Main;
import me.byungjin.Managers.CertificationManager;
import me.byungjin.Managers.Chef;

public class AuthWindow extends JDialog{
	private JTextField clientTxt, clientSecretTxt, authIRCTxt, authTxt;	
	
	public AuthWindow() {
		setTitle("Authentication Configuration");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);		
		
		clientTxt = new JTextField(CertificationManager.getClientAuth());
		clientSecretTxt = new JTextField(CertificationManager.getClientSecretAuth());
		authIRCTxt = new JTextField(CertificationManager.getAuthIRC());		
		authTxt = new JTextField(CertificationManager.getAuth());
		
		JButton save = new JButton("Save");
		JButton exit = new JButton("Not Save");				
		
		GridLayout layout = new GridLayout(5,2,3,15);
		JPanel inner = new JPanel();
		inner.setPreferredSize(new Dimension(300,250));
		inner.setLayout(layout);	
		inner.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		
		inner.add(new JLabel("Client ID"));
		inner.add(clientTxt);
		inner.add(new JLabel("Client Secret"));
		inner.add(clientSecretTxt);
		inner.add(new JLabel("Auth(IRC)"));
		inner.add(authIRCTxt);
		inner.add(new JLabel("Auth"));
		inner.add(authTxt);								
		
		save.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(isCheck()) {
					saveSetting();
					dispose();
				}
			}
		});
		exit.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});		
		inner.add(save);
		inner.add(exit);		
		add(inner);
		setSize(300,250);						
		setVisible(true);
	}	
	public boolean isCheck() {
		if(clientTxt == null) return false;
		if(clientSecretTxt == null) return false;
		if(authIRCTxt == null) return false;
		if(authTxt == null) return false;
		return true;
	}		
	public void saveSetting() {
		CertificationManager.setClientAuth(clientTxt.getText());
		CertificationManager.setClientSecretAuth(clientSecretTxt.getText());
		CertificationManager.setAuth(authTxt.getText());
		CertificationManager.setAuthIRC(authIRCTxt.getText());		
	}
}
