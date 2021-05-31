package me.byungjin.ActionListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import me.byungjin.Windows.XlSXWindow;

public class DownloadDBRecordClick implements ActionListener{
	@Override
	public void actionPerformed(ActionEvent e) {
		new XlSXWindow();
		
	}
}
