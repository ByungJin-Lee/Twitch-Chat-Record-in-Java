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
	 * ���� ä�ο��� �ִ� Chatter(ä���� ģ ���)���� ����
	 */
	private static HashMap<String, Chatter> chatters = new HashMap<String, Chatter>();
	/**
	 * ���� ä�ο� �ִ� ä���� ��
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
	 * ���� Chatter ������ ũ��
	 * @return integer
	 */
	public static int size() {
		return chatters.size();
	}
	/**
	 * ���� Chatter ������ ���
	 */
	public static void clear() {
		chatters.clear();
		totalChat = 0;
	}
	/**
	 * ���� Channel���� �߻��� ä�� ���� ��ȯ��
	 * @return
	 */
	public static int getTotalChat() {
		return totalChat;
	}
	/**
	 * ���� Channel�� �̸��� ��ȯ��
	 * @return
	 */
	public static String getChannelName() {
		return channelName;
	}
	/**
	 * ���� Channel�� �̸��� ������
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
