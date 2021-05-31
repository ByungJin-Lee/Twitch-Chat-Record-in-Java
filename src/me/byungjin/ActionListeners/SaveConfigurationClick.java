package me.byungjin.ActionListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import me.byungjin.Managers.Chef;
import me.byungjin.Managers.FileManager;

public class SaveConfigurationClick implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		FileManager.saveSetting();
	}
	
}
