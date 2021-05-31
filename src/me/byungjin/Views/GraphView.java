package me.byungjin.Views;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Vector;

import javax.swing.JPanel;

import me.byungjin.Datas.Viewer;
import me.byungjin.Managers.GraphManager;

public class GraphView extends JPanel {
	private int move = 15;
	private int block = 0;	
	private int standard = 0;
	private int bottom = 20;
	private int offset = 10;
	private int start = 0;
	private Vector<Viewer> dataset;
	private final int DELTA = 7;	
		
	public GraphView() {
		setBackground(Color.BLACK);
		dataset = GraphManager.getViewers();
	}	
	public void update() {		
		repaint();
	}	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}
	public void draw(Graphics g) {
		
		if(dataset.size() > 9) {
			dataset.remove(0);
			start++;			
		}
		standard = getHeight() - bottom;
		block = getWidth() / 9;	
		//Bottom
		g.setColor(Color.WHITE);
		g.drawLine(0, standard, getWidth(), standard);
		
		if(dataset.size() < 1) return;
		
		Viewer pri = dataset.get(0);	
		int div = pri.getTotalViewers() / getHeight() + DELTA;
		int priTotal = standard - pri.getTotalViewers() / div;
		int priChatter = standard - pri.getActiveChatters();
		boolean direction = true;
		
		//First Dot
		
		g.setColor(Color.RED);
		g.drawString(""+pri.getTotalViewers(), move, standard - pri.getTotalViewers() / div);
		
		g.setColor(Color.GREEN);
		g.drawString(""+pri.getActiveChatters(), move, standard - pri.getActiveChatters());;		
		
		Viewer cur = null;
		for(int i = 1, 
				size = dataset.size(), 
				b_pos,
				of = -2,
				cur_number; i < size; i++,direction = !direction) {
			cur = dataset.get(i);
			b_pos = (i-1) * block + move;			
			//Display Direction
			if(direction) {
				of = offset;			
			}else {
				of = -2;
			}
			//Chatter
			cur_number = standard - cur.getActiveChatters();
			g.setColor(Color.GREEN);
			g.drawLine(b_pos, priChatter, b_pos + block, cur_number);
			g.drawString(""+cur.getActiveChatters(), b_pos + block, cur_number + of);
			priChatter = cur_number;
			//Total
			cur_number = standard - (cur.getTotalViewers() / div);
			g.setColor(Color.RED);
			g.drawLine(b_pos, priTotal, b_pos + block, cur_number);
			g.drawString(""+cur.getTotalViewers(), b_pos + block, cur_number + of);
			priTotal = cur_number;
			//Bottom
			g.setColor(Color.WHITE);
			g.drawString(""+(i+start), b_pos + block, standard + 11);			
		}				
	}	
}
