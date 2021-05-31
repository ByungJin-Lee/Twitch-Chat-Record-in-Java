package me.byungjin.Datas;

public class Viewer {
	private int totalViewers;
	private int activeChatters;
	
	public Viewer(int t, int c) {
		totalViewers = t;
		activeChatters = c;
	}
	
	public int getTotalViewers() {
		return totalViewers;
	}
	public void setTotalViewers(int totalViewers) {
		this.totalViewers = totalViewers;
	}
	public int getActiveChatters() {
		return activeChatters;
	}
	public void setActiveChatters(int activeChatters) {
		this.activeChatters = activeChatters;
	}
	
}
