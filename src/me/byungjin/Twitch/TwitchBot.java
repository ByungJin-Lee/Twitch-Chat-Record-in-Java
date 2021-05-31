package me.byungjin.Twitch;

import java.io.IOException;

import org.jibble.pircbot.IrcException;
import org.jibble.pircbot.NickAlreadyInUseException;
import org.jibble.pircbot.PircBot;

import me.byungjin.Connection.OnReceiveData;
import me.byungjin.Connection.TwitchConnection;
import me.byungjin.Managers.CertificationManager;
import me.byungjin.Managers.Chef;
import me.byungjin.Managers.DBManager;
import me.byungjin.Windows.MainWindow;
import me.byungjin.Works.Loop;

public class TwitchBot extends PircBot {	
	public TwitchBot() throws NickAlreadyInUseException, IOException, IrcException {				
		setName("youngmen");
		setEncoding("utf-8");		
		setVerbose(true);						
	}
	
	public void join(String id) {		
		joinChannel("#" + id);		
	}
	public void connectToServer() throws Exception {		
		this.connect("irc.chat.twitch.tv", 6667, CertificationManager.getAuthIRC());		
	}
	
	@Override
	protected void onConnect() {		
		Chef.pushString("***System", "Connect!", true);
		if(Chef.getLoopTask() == null) {
			Chef.setLoopTask(new Loop());
		}
	}
	@Override
	protected void onDisconnect() {
		Chef.pushString("***System", "Disconnect...", true);
		if(Chef.getLoopTask() != null) {
			Chef.stopLoop();
		}
	}
	
	@Override
	protected void onMessage(String channel, String sender, String login, String hostname, String message) {
		Chef.pushString(sender, message, false);
	}
}
