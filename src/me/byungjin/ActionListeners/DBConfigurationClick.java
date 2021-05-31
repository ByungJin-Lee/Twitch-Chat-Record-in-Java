package me.byungjin.ActionListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import me.byungjin.Windows.DBWindow;

public class DBConfigurationClick implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		new DBWindow();
	}
}
