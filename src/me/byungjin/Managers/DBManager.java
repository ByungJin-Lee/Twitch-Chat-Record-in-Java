package me.byungjin.Managers;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.bson.Document;

import com.mongodb.client.FindIterable;

import me.byungjin.Connection.DBConnection;
import me.byungjin.Datas.Chatter;

public class DBManager {
	private static DBConnection conn = new DBConnection();
	
	private static SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static String name = null;
	private static String collectionName = null;
	private static String destination = null;
	private static int port = -1;
	
	public static void build() throws Exception {				
		conn.bulidSetting();			
	}
	public static boolean isCheck() {
		if(name == null) return false;
		if(collectionName == null) return false;
		if(destination == null) return false;
		if(port == -1) return false;
		return true;
	}
	
	public static void sendDataToCollection() {
		if(conn != null) {
			//Object
			Document header = new Document();
			Document records = new Document();
			List<Document> chatters = new ArrayList<Document>();
			//Header
			header.put("channel", ChannelManager.getChannelName());
			header.put("nick", ChannelManager.getNickName());
			//records
			records.put("Time", time.format(new Date(System.currentTimeMillis())));
			records.put("Playing", ChannelManager.getPlaying());
			records.put("Total_counts", GraphManager.getLastTotalViewer());
			records.put("Chatter_counts", ChannelManager.size());
			records.put("Chat_counts", ChannelManager.getTotalChat());
			records.put("mature", ChannelManager.isMature());
			//Chatter
			Chatter o;
			for(Map.Entry<String, Chatter> c : ChannelManager.getChatters().entrySet()) {
				o = c.getValue();
				chatters.add(new Document("user", c.getKey())
						.append("chats", o.getCount())
						.append("len", o.getLength()));							
			}			
			//Append			
			records.put("Chatters", chatters);
			header.put("records", records);
			//Send
			try {				
				conn.insertItem(header);
			} catch (Exception e) {
				Chef.pushString("***DB-Error", e.getMessage(), true);
			}				
		}
	}
	
	public static FindIterable<Document> getCollectionData(String channel) {
		if(conn != null) {
			try {
				return conn.receiveItems(channel);
			} catch (Exception e) {
				Chef.pushString("DBManger", e.getMessage(), true);
			}
		}
		return null;
	}
	
	public static int getPORT() {
		return port;
	}
	public static void setPORT(int p) {
		port = p;
	}
	public static String getDestination() {
		return destination;
	}
	public static void setDestination(String d) {
		destination = d;
	}
	public static void setDataBaseName(String n) {
		name = n;
	}
	public static String getDataBaseName() {
		return name;
	}

	public static String getCollectionName() {
		return collectionName;
	}

	public static void setCollectionName(String collectionName) {
		DBManager.collectionName = collectionName;
	}	
}
