package me.byungjin.Managers;

import java.util.HashMap;

import me.byungjin.Datas.Chatter;

public class ChannelManager {
	private static boolean isMature = false;
	private static String channelName = null;
	private static String nickName = null;
	private static String playing = null;
	private static boolean onStreaming = false;
	
	public static boolean isOnStreaming() {
		return onStreaming;
	}
	public static void setOnStreaming(boolean onStreaming) {
		ChannelManager.onStreaming = onStreaming;
	}
	/**
	 * 현재 채널에서 있는 Chatter(채팅을 친 사람)들의 집합
	 */
	private static HashMap<String, Chatter> chatters = new HashMap<String, Chatter>();
	/**
	 * 현재 채널에 있는 채팅의 수
	 */
	private static int totalChat = 0;
	
	public static HashMap<String, Chatter> getChatters(){
		if(chatters != null) {
			return chatters;
		}
		return null;
	}
	
	
	public static Chatter isChatter(String user, String chat) {
		totalChat++;
		if(chatters.containsKey(user)) {
			Chatter u = chatters.get(user);
			u.add(chat);
			return u;
		}else {			 
			Chatter u = new Chatter(user,chat);
			chatters.put(user, u);
			return u;
		}		
	}	
	/**
	 * 현재 Chatter 집합의 크기
	 * @return integer
	 */
	public static int size() {
		return chatters.size();
	}
	/**
	 * 현재 Chatter 집합을 비움
	 */
	public static void clear() {
		chatters.clear();
		totalChat = 0;
	}
	/**
	 * 현재 Channel에서 발생한 채팅 수를 반환함
	 * @return
	 */
	public static int getTotalChat() {
		return totalChat;
	}
	/**
	 * 현재 Channel의 이름을 반환함
	 * @return
	 */
	public static String getChannelName() {
		return channelName;
	}
	/**
	 * 현재 Channel의 이름을 설정함
	 * @param String
	 */
	public static void setChannelName(String n) {
		channelName = n;
	}
	public static void setNickName(String n) {
		nickName = n;
	}
	public static String getNickName() {
		return nickName;
	}
	public static String getPlaying() {
		return playing;
	}
	public static void setPlaying(String p) {
		playing = p;
	}
	public static boolean isMature() {
		return isMature;
	}
	public static void setMature(boolean r) {
		isMature = r;
	}	
}
