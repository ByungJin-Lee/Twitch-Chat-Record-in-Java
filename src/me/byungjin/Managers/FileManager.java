package me.byungjin.Managers;

import me.byungjin.Files.ConfigFile;
import me.byungjin.Files.XFile;

public class FileManager {
	private static ConfigFile config = null;
	private static XFile xlsxFile = null;
	
	public static void init() {
		//Configuration
		config = new ConfigFile();
		config.completeConfig();
		//XLSX
		xlsxFile = new XFile();
	}
	
	public static void downloadXLSX(String channel) {
		if(xlsxFile != null) {
			xlsxFile.saveXlse(channel);
		}
	}
	
	public static void saveSetting() {
		if(config != null) {
			config.save();
		}
	}
	
}
