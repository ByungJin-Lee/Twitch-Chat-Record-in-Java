package me.byungjin.ActionListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import me.byungjin.Managers.Chef;

public class QuitClick implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {		
		Chef.part();
		Chef.pushString("***System", "Leave Channel", true);
		Chef.setRunning(false);
	}

}
