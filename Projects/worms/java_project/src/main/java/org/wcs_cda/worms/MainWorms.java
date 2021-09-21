package org.wcs_cda.worms;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class MainWorms extends JFrame {
	private MainLoop mainLoop;
	
    public MainWorms() {
        initUI();
    }
    
    private void initUI() {
        mainLoop = new MainLoop();
        
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
            JFrame ex = new MainWorms();
            ex.setVisible(true);
        });
    }
}
