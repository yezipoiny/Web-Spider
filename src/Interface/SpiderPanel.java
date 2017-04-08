package Interface;

import java.awt.Choice;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

import data.FileIO;
import spider.SpiderRun;
import spider.nullHostException;

/**
 * Interface: Spider panel
 * 
 * @author Tomahawkd
 */

public class SpiderPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private FileIO file;
	private JTextField site;
	private Choice protocol;
	private JLabel lblQueue;
	private SiteMapPanel siteMap;
	
	SpiderPanel(FileIO file) {
		
		this.file = file;
		
		setLayout(null);
		
		//UI Component
		JLabel lblHost = new JLabel("Host:");
		lblHost.setBounds(110, 97, 40, 16);
		add(lblHost);
		
		JLabel lblTipInvalid_Spider = new JLabel("Input invalid");
		lblTipInvalid_Spider.setBounds(286, 308, 79, 16);
		add(lblTipInvalid_Spider);
		lblTipInvalid_Spider.setVisible(false);
		
		JLabel lblProtocol = new JLabel("Protocol:");
		lblProtocol.setBounds(110, 45, 61, 16);
		add(lblProtocol);
		
		JLabel lblInQueue = new JLabel("In queue:");
		lblInQueue.setBounds(110, 154, 61, 16);
		add(lblInQueue);
		
		lblQueue = new JLabel("0");
		lblQueue.setBounds(200, 154, 61, 16);
		add(lblQueue);
		
		//Spider Setting Component
		protocol = new Choice();
		protocol.setBounds(200, 45, 118, 21);
		add(protocol);
		protocol.add("http");
		protocol.add("https");
		
		site = new JTextField();
		site.setBounds(200, 92, 290, 26);
		add(site);
		site.setColumns(10);
		
		//Spider Runner Toggle Button
		SpiderRun spr = new SpiderRun(file.getDataSet().getSpiderData(), this);
		JToggleButton tglbtn_Start_Spider = new JToggleButton("Session Start");
		tglbtn_Start_Spider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//UI Update
				if(tglbtn_Start_Spider.getText().equals("Session Start")) {
					tglbtn_Start_Spider.setText("Session Stop");
					try {
						
				//Spider Settings Update		
						file.getDataSet().getSpiderOption().setHost(site.getText());
						file.getDataSet().getSpiderOption().setProtocol(protocol.getSelectedItem());
						spr.setOption(file.getDataSet().getSpiderOption());
						
				//Run Spider
						new Thread(new Runnable() {
							public void run() {
								try {
									spr.start();
								} catch (nullHostException e) {
									lblTipInvalid_Spider.setVisible(true);
								}
								spr.stop();
							}
						}).start();
						
				//Update data
						siteMap.updateData();
					} catch (NumberFormatException e1) {
						lblTipInvalid_Spider.setVisible(true);
					}
				} else {
					
				//Stop Spider	
					spr.stop();
					
				//Update data	
					siteMap.updateData();
					
				//UI Update	
					tglbtn_Start_Spider.setText("Session Start");
					lblTipInvalid_Spider.setVisible(false);
				}
			}
		});
		tglbtn_Start_Spider.setBounds(248, 335, 161, 29);
		add(tglbtn_Start_Spider);
	}
	
	public void refreshQueue(int queue) {
		lblQueue.setText("" + queue);
	}
	
	/**
	 * Update data after loading a file.
	 * 
	 * @author Tomahawkd
	 */
	
	void updateData() {
		this.site.setText(file.getDataSet().getSpiderOption().getHost());
		this.protocol.select(file.getDataSet().getSpiderOption().getProtocol());
	}
	
	/**
	 * Hold the site map class to update site map data.
	 * 
	 * @param siteMap
	 * 
	 * @see {@link SiteMapPanel}
	 * 
	 * @author Tomahawkd
	 */
	
	void setSiteMap(SiteMapPanel siteMap) {
		this.siteMap = siteMap;
	}
}
