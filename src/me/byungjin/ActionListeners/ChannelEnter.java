package me.byungjin.ActionListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import me.byungjin.Managers.ChannelManager;
import me.byungjin.Managers.Chef;

public class ChannelEnter implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {		
		ChannelManager.setChannelName(((JTextField)e.getSource()).getText());
		Chef.join();
		Chef.setRunning(true);
		Chef.startLoop();;		
	}

}
