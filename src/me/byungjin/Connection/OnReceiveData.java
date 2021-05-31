package me.byungjin.Connection;

public interface OnReceiveData {
	/**
	 * Type [true : getStream, false : getChannel]
	 * @param type
	 * @param json
	 */
	public void onReceiveData(boolean type, String json);	
	public void onError(String error);
}
