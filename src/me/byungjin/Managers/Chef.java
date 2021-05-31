package me.byungjin.Managers;

import me.byungjin.Files.ConfigFile;
import me.byungjin.Twitch.TwitchBot;
import me.byungjin.Windows.MainWindow;
import me.byungjin.Works.Loop;

public class Chef {			
	private static TwitchBot bot;
	private static MainWindow view;
	private static Loop loopTask;		
	
	private static boolean running = false;
	private static boolean onStreamming = false;
	
	public static boolean onScroll = true;	
	
	public static boolean isCheck() {
		if(bot == null) return false;
		if(view == null) return false;
		return true;
	}
	
	public static void stopLoop() {
		if(loopTask != null) {
			loopTask.stop();
		}
	}
	
	public static void start() throws Exception {
		bot = new TwitchBot();
		view = new MainWindow("Twitch An");
		FileManager.init();
		
		if(view != null) {
			GraphManager.setGraphView(view.getGraphicsGraph());
		}
	}
	public static void startLoop() {
		if(loopTask != null) {
			loopTask.start();
		}
	}
	public static void join() {
		if(bot!=null) {
			bot.join(ChannelManager.getChannelName());
		}
	}
	public static void part() {
		if(bot!=null) {
			bot.partChannel("#"+ChannelManager.getChannelName());
		}
	}
	public static void disconnect() {
		if(bot!= null) {
			bot.disconnect();
		}
	}
	public static void connect() throws Exception {
		if(bot!= null) {
			bot.connectToServer();
		}
	}
	public static void pushString(String title, String content, boolean type) {
		if(view != null) {
			view.chat(title, content, type);
		}
	}
	public static TwitchBot getTwitchBot() {
		return bot;
	}
	public static void setTwitchBot(TwitchBot bot) {
		Chef.bot = bot;
	}
	public static MainWindow getView() {
		return view;
	}
	public static void setView(MainWindow view) {
		Chef.view = view;
	}
	public static boolean isRunning() {
		return running;
	}
	public static void setRunning(boolean running) {
		Chef.running = running;
	}
	public static boolean isOnStreamming() {
		return onStreamming;
	}
	public static void setOnStreamming(boolean onStreamming) {
		Chef.onStreamming = onStreamming;
	}	
	public static Loop getLoopTask() {
		return loopTask;
	}
	public static void setLoopTask(Loop loopTask) {
		Chef.loopTask = loopTask;
	}		
}
