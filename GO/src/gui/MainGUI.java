/**
 * 
 */
package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import config.GobanConfiguration;


/**
 * This class contains the Menu Frame (first enter in the game)
 * 
 * @author chriqui nathan
 *
 */
public class MainGUI extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	private static final Font MENU_FONT = new Font(Font.SANS_SERIF, Font.BOLD, 50);	
	
	protected JButton vsButton = new JButton("Mode 3 adversaires réels");
	protected JButton botButton = new JButton("Mode 2 adversaires réels, 1 bot");
	protected JButton exitButton = new JButton("Exit");
	
	protected JLabel goLabel = new JLabel("Jeu de GO");
	
	private JPanel menutextPanel;
	private JPanel menubuttonPanel;

	private GameFrame gameFrame;
	
	public MainGUI(String title) {
		super(title);
		initMenu();
	}
	
	//Menu
	private void initMenu() {
		
		Container contentPaneMenu = getContentPane();
		contentPaneMenu.setLayout(new BorderLayout());		
		
		menutextPanel = new JPanel();
		menutextPanel.setLayout(new FlowLayout(FlowLayout.CENTER));		
		goLabel.setFont(MENU_FONT);
		menutextPanel.add(goLabel);
		
		contentPaneMenu.add(menutextPanel,BorderLayout.NORTH);
		
		menubuttonPanel = new JPanel();
		menubuttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		menubuttonPanel.setLayout(new BoxLayout(menubuttonPanel, BoxLayout.Y_AXIS));				
		vsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		menubuttonPanel.add(vsButton);
		menubuttonPanel.add(Box.createVerticalStrut(20));
		botButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		menubuttonPanel.add(botButton);
		menubuttonPanel.add(Box.createVerticalStrut(40));
		exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		menubuttonPanel.add(exitButton);
		
		contentPaneMenu.add(menubuttonPanel,BorderLayout.CENTER);
		
		exitButton.addActionListener(new ExitAction(this));
		vsButton.addActionListener(new VSAction(this));
		botButton.addActionListener(new vsBotAction(this));
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setVisible(true);
		setResizable(false);
		
	}
	

	private class ExitAction implements ActionListener {
		//Window to be closed.
		private JFrame window;

		public ExitAction(JFrame window) {
			this.window = window;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			window.dispose();
			
		}

	}
	
	private class VSAction implements ActionListener {
		//Window to be closed.
		private JFrame window;

		public VSAction(JFrame window) {
			this.window = window;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			window.dispose();	
			GobanConfiguration.TURN=0;
			//Launch GameFrame (frame to play) without the bot
			gameFrame = new GameFrame(0);
		}		
	}
	
	private class vsBotAction implements ActionListener {
			
			private JFrame secondWindows;
			
			public vsBotAction(JFrame secondWindows) {
				this.secondWindows = secondWindows;
			}
			
			public void actionPerformed(ActionEvent e) {
				secondWindows.dispose();
				GobanConfiguration.TURN=0;
				//Launch GameFrame (frame to play) with the bot
				gameFrame = new GameFrame(1);
			}
		}
}
