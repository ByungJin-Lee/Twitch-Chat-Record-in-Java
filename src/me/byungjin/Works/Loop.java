package me.byungjin.Works;

import java.util.Timer;

import me.byungjin.Managers.Chef;
import me.byungjin.Managers.GraphManager;

public class Loop {
	private Timer timer = null;
	private TTask task = null;
	
	public Loop() {
		this.timer = new Timer();
		task = new TTask();				
	}		
	public void start() {
		if(timer != null && task != null) {			
			timer.schedule(task, 0, 60000 * GraphManager.getGraphInterval());
		}
	}
	public void stop() {
		if(timer != null) {
			timer.cancel();			
		}
	}
	public void restart() {		
		if(timer != null && task != null) {
			timer.schedule(task, 0, 60000 * GraphManager.getGraphInterval());
		}
	}
}
