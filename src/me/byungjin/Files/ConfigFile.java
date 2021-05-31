package me.byungjin.Files;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import me.byungjin.Managers.CertificationManager;
import me.byungjin.Managers.Chef;
import me.byungjin.Managers.DBManager;

public class ConfigFile {
	private File config = null;
	private String configString = null;
	private final String path = "./config.txt";
	
	public ConfigFile() {
		config = new File(this.path);		
		try {
			this.readProp();
		}catch(IOException e) {
			Chef.pushString("Configuration", "Read Error", true);
		}
	}
	public void readProp() throws IOException{
		configString = "";
		if(config == null) return;
		
		if(config.exists()) {
			BufferedReader reader = new BufferedReader(new FileReader(config));			
			String line;
			while((line = reader.readLine()) != null) {
				configString += (line + "\n");
			}
			reader.close();
			this.completeConfig();
		}
	}
	public void completeConfig() {
		/*
		auth
		client_auth
		client_secret_auth
		auth_irc
		database_name
		collection_name
		destination
		port
		 */
		try {
			//CT
			CertificationManager.setAuth(this.get("auth"));
			CertificationManager.setClientAuth(this.get("client_auth"));
			CertificationManager.setClientSecretAuth(this.get("client_secret_auth"));
			CertificationManager.setAuthIRC(this.get("auth_irc"));
			//DB
			DBManager.setDataBaseName(this.get("database_name"));
			DBManager.setCollectionName(this.get("collection_name"));
			DBManager.setDestination(this.get("destination"));
			DBManager.setPORT(Integer.parseInt(this.get("port")));
		}catch(Exception x) {
			Chef.pushString("Configuration", x.getMessage(), true);
		}
	}
	public String get(String key) throws Exception {
		int pos = configString.indexOf(key);
		
		if(pos == -1)
			throw new Exception("Can't find Key : " + key);
		
		return configString.substring(pos + key.length() + 1, configString.indexOf("\n", pos)).trim();
	}
	public void set(String key, String value) {
		int pos = configString.indexOf(key);
		
		try {
			if(pos != -1) {
				configString = configString.replace(this.get(key), value);
			}else {
				configString = configString.concat(String.format("%s=%s\n", key, value));
			}
		}catch(Exception x) {
			Chef.pushString("Configuration", x.getMessage(), true);
		}
	}
	public void save() {
		this.set("auth", CertificationManager.getAuth());
		this.set("client_auth", CertificationManager.getClientAuth());
		this.set("client_secret_auth", CertificationManager.getClientSecretAuth());
		this.set("auth_irc", CertificationManager.getAuthIRC());
		
		this.set("database_name", DBManager.getDataBaseName());
		this.set("collection_name", DBManager.getCollectionName());
		this.set("destination", DBManager.getDestination());
		this.set("port", ""+DBManager.getPORT());
		
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(config, false));
			bw.write(configString);
			bw.flush();
			bw.close();
		}catch(Exception x){
			Chef.pushString("Configuration", "Save Error :" + x.getMessage(), true);
		}
	}
}
