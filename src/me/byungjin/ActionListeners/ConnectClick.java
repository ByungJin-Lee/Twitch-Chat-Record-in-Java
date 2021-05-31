package me.byungjin.ActionListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import me.byungjin.Managers.CertificationManager;
import me.byungjin.Managers.Chef;
import me.byungjin.Managers.DBManager;

public class ConnectClick implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {		
		try {		
			if(CertificationManager.isCheck() && DBManager.isCheck()) {
				Chef.connect();					
				DBManager.build();
			}else {
				Chef.pushString("Configuration", "No Config Data", true);
			}				
		}catch(Exception x) {
			Chef.pushString("***Error", x.getMessage(), true);
		}		
	}
}
