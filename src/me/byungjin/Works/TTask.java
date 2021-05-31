package me.byungjin.Works;

import java.util.TimerTask;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import me.byungjin.Managers.GraphManager;
import me.byungjin.Connection.OnReceiveData;
import me.byungjin.Connection.TwitchConnection;
import me.byungjin.Datas.Viewer;
import me.byungjin.Managers.ChannelManager;
import me.byungjin.Managers.Chef;
import me.byungjin.Managers.DBManager;

public class TTask extends TimerTask{	
	private JSONParser parser;
	private TwitchConnection connection;	
	
	public TTask() {
		super();
		parser = new JSONParser();		
		connection = new TwitchConnection(new OnReceiveData() {			
			@Override
			public void onReceiveData(boolean type, String json) {
				try {
					if(type) {
						
					}else {
						JSONObject jObj = (JSONObject) parser.parse(json);						
						workOnStreaming((JSONObject)(((JSONArray)jObj.get("data")).get(0)));
						//Clear						
					}	
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
							
			}		
			@Override
			public void onError(String error) {
				Chef.pushString("Stream", error, true);				
			}
		});		
	}
	
	@Override
	public void run() {
		/* TODO
		  ��� ������ ����(�ð�, ��� ����, ��ü ��û�� ��, Ȱ�� ��û�� ��, ��ü ä�� ��)
		  Ȱ�� ��û�� ����(�ð�, ���̵�, Ȱ�� ä���ڰ� ģ ä�� ��, ��� ä�� ����)
		  
		  ���� �� �ʱ�ȭ �ʿ�
		  
		  DB ����(MySql) �� ����
		 */
		if(Chef.isRunning()) {			
			try {				
				connection.getStreamming();				
			}catch(Exception x) {
				Chef.pushString("***Task", x.getMessage(), true);				
			}
		}
	}
	
	public void workOnStreaming(JSONObject d) throws Exception {
		//About Graph
		GraphManager.add(new Viewer(
				Integer.parseInt(d.get("viewer_count").toString()), 
				ChannelManager.size()));
		GraphManager.updateGraph();
		//About Channel
		ChannelManager.setNickName(d.get("user_name").toString());
		ChannelManager.setPlaying(d.get("game_name").toString());
		ChannelManager.setMature(Boolean.parseBoolean(d.get("is_mature").toString()));
		//Live Stream Check
		if(d.get("type").equals("live")) {
			Chef.setOnStreamming(true);
			//Sava Data
			DBManager.sendDataToCollection();
		}else {
			Chef.setOnStreamming(false);
			//push
			Chef.pushString("Streamming", "False", true);
		}		
		//Clear Chatter
		ChannelManager.clear();
	}
}
