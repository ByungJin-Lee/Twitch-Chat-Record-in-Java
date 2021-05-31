package me.byungjin.Views;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import me.byungjin.ActionListeners.AuthConfigurationClick;
import me.byungjin.ActionListeners.DBConfigurationClick;
import me.byungjin.ActionListeners.DownloadDBRecordClick;
import me.byungjin.ActionListeners.SaveConfigurationClick;

public class MenuBarView extends JMenuBar {
	public MenuBarView() {		
		createMenu();
	}	
	public void createMenu() {
		JMenu configuration = new JMenu("Config");
		
		JMenuItem database = new JMenuItem("DB Configuration");
		JMenuItem auth = new JMenuItem("Auth Configuration");	
		JMenuItem save = new JMenuItem("Save Configuration...");
		JMenuItem download = new JMenuItem("Download DB Records");
		
		auth.addActionListener(new AuthConfigurationClick());
		database.addActionListener(new DBConfigurationClick());		
		save.addActionListener(new SaveConfigurationClick());
		download.addActionListener(new DownloadDBRecordClick());
		
		configuration.add(auth);
		configuration.add(database);	
		configuration.add(save);
		configuration.add(download);
		
		this.add(configuration);
	}
}
