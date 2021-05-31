package me.byungjin.Windows;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import me.byungjin.Managers.CertificationManager;
import me.byungjin.Managers.DBManager;

public class DBWindow extends JDialog {
	private JTextField dbNameTxt, destinationTxt, portTxt, collectionNameTxt;
	
	public DBWindow() {
		setTitle("Setting Configuration");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);		
		
		dbNameTxt = new JTextField(DBManager.getDataBaseName());
		collectionNameTxt = new JTextField(DBManager.getCollectionName());
		destinationTxt = new JTextField(DBManager.getDestination());
		portTxt = new JTextField(String.format("%d", DBManager.getPORT()));
		
		JButton save = new JButton("Change");
		JButton exit = new JButton("Not Change");				
		
		GridLayout layout = new GridLayout(5,2,3,15);
		JPanel inner = new JPanel();
		inner.setPreferredSize(new Dimension(350,250));
		inner.setLayout(layout);	
		inner.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		
		inner.add(new JLabel("DB"));
		inner.add(dbNameTxt);
		inner.add(new JLabel("Collection"));
		inner.add(collectionNameTxt);
		inner.add(new JLabel("Destination"));
		inner.add(destinationTxt);
		inner.add(new JLabel("PORT"));
		inner.add(portTxt);
		
		save.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {				
					saveSetting();
					dispose();				
			}
		});
		exit.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});		
		inner.add(save);
		inner.add(exit);		
		add(inner);
		setSize(350,250);						
		setVisible(true);
	}
	public void saveSetting() {
		DBManager.setCollectionName(collectionNameTxt.getText());
		DBManager.setDataBaseName(dbNameTxt.getText());
		DBManager.setDestination(destinationTxt.getText());
		DBManager.setPORT(Integer.parseInt(portTxt.getText()));		
	}
}
