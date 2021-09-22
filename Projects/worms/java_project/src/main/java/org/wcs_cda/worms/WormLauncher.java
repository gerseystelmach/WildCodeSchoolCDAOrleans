package org.wcs_cda.worms;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class WormLauncher extends JFrame {
	private TimeController mainLoop;
	
    public WormLauncher() {
        initUI();
    }
    
    private void initUI() {
        mainLoop = new TimeController();
        
        add(mainLoop.getBoard());
               
        setResizable(false);
        pack();
        
        setTitle("WCS - CDA - Worms");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
    	try {
    		Config.loadConfig();
    	}
    	catch(Exception ex) {
    		System.err.println(
    			"Could not load configuration, please check ..."
    		);
    		System.err.println(ex.getMessage());
    		ex.printStackTrace();
    		System.exit(1);
    	}
    	
        EventQueue.invokeLater(() -> {
            JFrame ex = new WormLauncher();
            ex.setVisible(true);
        });
    }
}
