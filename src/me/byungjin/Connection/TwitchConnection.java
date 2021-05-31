package me.byungjin.Connection;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import me.byungjin.Managers.CertificationManager;
import me.byungjin.Managers.ChannelManager;
import me.byungjin.Managers.Chef;

public class TwitchConnection{
	private OnReceiveData process = null;
	private BufferedReader br = null;
	private String json = "";		
	private HttpURLConnection conn = null;
	
	public TwitchConnection(OnReceiveData e) {
		process = e;
	}		
	
	public void getOAuth() {
		//TODO say Error message and Check this methods is Working
		if(process == null) return;
				
		String json = "";		
		try {
			URL url = new URL(
					String.format("https://id.twitch.tv/oauth2/token?client_id=%s&client_secret=%s&grant_type=client_credentials"
					,CertificationManager.getClientAuth()
					,CertificationManager.getClientSecretAuth()));
			conn = (HttpURLConnection)url.openConnection();
			conn.setRequestMethod("POST");					
			
			br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));			
			String line;
			json = "";
			while((line = br.readLine()) != null) {
				json = json + line + "\n";
			}
		}catch(Exception x) {
			process.onError(x.getMessage());
		}finally {
			try {
				br.close();
				process.onReceiveData(true, json);
			}catch(Exception x) {
				process.onError(x.getMessage());
			}
		}
	}
	
	public void getStreamming() {
		//TODO say Error message
		if(process == null) return;		
		
		try {
			URL url = new URL("https://api.twitch.tv/helix/streams?user_login="+ChannelManager.getChannelName());
			conn = (HttpURLConnection)url.openConnection();			
			conn.setRequestProperty("Accept", "application/vnd.twitchtv.v5+json");
			conn.setRequestProperty("Client-ID", CertificationManager.getClientAuth());
			conn.setRequestProperty("Authorization", CertificationManager.getAuth());
			
			br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));			
			String line;			
			json = "";
			while((line = br.readLine()) != null) {
				json = json + line + "\n";
			}			
		}catch(Exception x) {
			process.onError(x.getMessage());
		}finally {
			try {
				br.close();
				conn.disconnect();
				process.onReceiveData(false, json);
			}catch(Exception x) {
				process.onError(x.getMessage());
			}
		}
	}	
}
