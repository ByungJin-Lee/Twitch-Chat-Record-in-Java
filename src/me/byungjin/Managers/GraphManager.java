package me.byungjin.Managers;

import java.util.Vector;

import me.byungjin.Datas.Viewer;
import me.byungjin.Views.GraphView;

public class GraphManager {	
	/**
	 * �׷����� ������ ���� �ֱ�
	 */
	private static int graphInterval = 1;
	/**
	 * �׷����� DB�� ����Ǵ� �ֱ�
	 */
	private static int saveInterval = 1;
	/**
	 * Graph�� �׸��µ� ���Ǵ� �ڷ� ����	 
	 */
	private static Vector<Viewer> viewers = new Vector<Viewer>();
	/**
	 * ȭ�鿡 �׷����� �׸��� View
	 */
	private static GraphView graph = null;
	/**
	 * �׷����� �ڷ� ���տ� �ڷḦ �߰���
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
	 * �׷��� View�� ������
	 * @param g
	 */
	public static void setGraphView(GraphView g) {
		graph = g;
	}
	/**
	 * �׷��� View�� ��ȯ��
	 * @return
	 */
	public static GraphView getGraph() {
		return graph;
	}
	/**
	 * �׷��� View�� �ٽ� �׸�
	 */
	public static void updateGraph() {
		if(graph != null) {
			graph.update();
		}
	}
	/**
	 * �׷��� ���� �ڷ� ������ ��ȯ��
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
