package me.byungjin.Windows;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;

import me.byungjin.Managers.FileManager;

public class XlSXWindow extends JDialog {
	private JTextField channelTxt;
	private JButton downloadXlsxBTN;
	
	public XlSXWindow() {
		setLayout(new GridLayout(2,1));
		channelTxt = new JTextField();
		this.add(channelTxt);
		downloadXlsxBTN = new JButton("Download");
		downloadXlsxBTN.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				FileManager.downloadXLSX(channelTxt.getText());
			}
		});
		this.add(downloadXlsxBTN);
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
		setSize(200,100);
	}
}
