package me.byungjin.Datas;

public class Chatter {
	private String name = "";
	private int count;
	private int length;
	
	public Chatter(String n, String l) {
		count = 1;
		length = l.length();
		name = n;
	}
	
	public void add(String l) {
		count++;
		length += l.length();
	}
	
	public int getCount() {
		return count;
	}
	public int getLength() {
		return length;
	}
	public String getName() {
		return name;
	}
}
