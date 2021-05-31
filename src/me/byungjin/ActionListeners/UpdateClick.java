package me.byungjin.ActionListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import me.byungjin.Managers.Chef;

public class UpdateClick implements ActionListener{
	 @Override
	public void actionPerformed(ActionEvent e) {
		 Chef.onScroll = !Chef.onScroll;
	}
}
