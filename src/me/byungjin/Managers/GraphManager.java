package me.byungjin.Managers;

import java.util.Vector;

import me.byungjin.Datas.Viewer;
import me.byungjin.Views.GraphView;

public class GraphManager {	
	/**
	 * 그래프에 데이터 갱신 주기
	 */
	private static int graphInterval = 1;
	/**
	 * 그래프가 DB에 저장되는 주기
	 */
	private static int saveInterval = 1;
	/**
	 * Graph를 그리는데 사용되는 자료 집합	 
	 */
	private static Vector<Viewer> viewers = new Vector<Viewer>();
	/**
	 * 화면에 그래프를 그리는 View
	 */
	private static GraphView graph = null;
	/**
	 * 그래프의 자료 집합에 자료를 추가함
	 * @param Viewer
	 */
	public static void add(Viewer d) {
		viewers.add(d);
	}		
	public static int size() {
		if(viewers != null) {
			return viewers.size();
		}else {
			return -1;
		}
	}
	/**
	 * 그래프 View를 설정함
	 * @param g
	 */
	public static void setGraphView(GraphView g) {
		graph = g;
	}
	/**
	 * 그래프 View를 반환함
	 * @return
	 */
	public static GraphView getGraph() {
		return graph;
	}
	/**
	 * 그래프 View를 다시 그림
	 */
	public static void updateGraph() {
		if(graph != null) {
			graph.update();
		}
	}
	/**
	 * 그래프 뷰의 자료 집합을 반환함
	 * @return
	 */
	public static Vector<Viewer> getViewers() {
		return viewers;
	}	

	public static int getGraphInterval() {
		return graphInterval;
	}

	public static void setGraphInterval(int s) {
		graphInterval = s;
	}
	public static int getSaveInterval() {
		return saveInterval;
	}
	public static void setSaveInterval(int saveInterval) {
		GraphManager.saveInterval = saveInterval;
	}
	public static int getLastTotalViewer() {
		return viewers.get(viewers.size() - 1).getTotalViewers();
	}
}
