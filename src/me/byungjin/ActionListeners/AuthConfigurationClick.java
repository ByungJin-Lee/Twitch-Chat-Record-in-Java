package me.byungjin.ActionListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import me.byungjin.Windows.AuthWindow;

public class AuthConfigurationClick implements ActionListener{
	@Override
	public void actionPerformed(ActionEvent e) { 
		new AuthWindow();
	}	
}
