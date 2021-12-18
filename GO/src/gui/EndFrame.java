package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import goban.process.StonesManager;


/**
 * This is the End Frame of the Game
 * 
 * @author chriqui nathan 
 *
 */
public class EndFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private static final Font MENU_FONT = new Font(Font.SANS_SERIF, Font.BOLD, 50);
	private static final Font WINNER_FONT = new Font(Font.MONOSPACED, Font.CENTER_BASELINE, 20);

	protected JButton exitButton = new JButton("Exit");;
	protected JButton menuButton = new JButton("Menu");
	
	protected JLabel endLabel = new JLabel("Fin de Partie");
	protected JLabel and = new JLabel(" ET ");
	protected JLabel equal = new JLabel(" Égalité parfaite");
	protected JLabel winnerIs = new JLabel(" Le vainqueur est : ");
	protected JLabel winnerAre = new JLabel(" Les vainqueurs sont : ");
	protected JLabel blackLabel = new JLabel("BLACK");
	protected JLabel whiteLabel = new JLabel("WHITE");
	protected JLabel redLabel = new JLabel("RED");
	
	private static final Color TURN_RED = Color.RED;
	private static final Color TURN_WHITE = Color.WHITE;
	private static final Color TURN_BLACK = Color.BLACK;
	
	private JPanel endtextPanel;
	private JPanel winnerPanel;
	private JPanel endPanel;
	private JPanel endbuttonPanel;
	private JPanel endtablePanel;
	
	private MainGUI menuFrame;
	
	public EndFrame() {
		endgame();
	}
	
	private void endgame() {
		
		setTitle("End Game");
		Container contentPaneEnd = getContentPane();
		contentPaneEnd.setLayout(new BorderLayout());
		
		int scoreBlack = GameFrame.valueBlack + StonesManager.interBlack;
		int scoreWhite = GameFrame.valueWhite + StonesManager.interWhite;
		int scoreRed = GameFrame.valueRed + StonesManager.interRed;
		
		endtablePanel = new JPanel();
		//Creating table
		String[][] values = {
				   { "Black", "" + GameFrame.valueBlack, "" + StonesManager.interBlack, "" + scoreBlack },
				   { "White", "" + GameFrame.valueWhite, "" + StonesManager.interWhite, "" + scoreWhite },
				   { "Red", "" + GameFrame.valueRed, "" + StonesManager.interRed, "" + scoreRed },
				};
		String[] header = { "Color", "Captured Stones", "Intersections", "Total" };
		JTable table = new JTable(values, header);
		//JSrollPane is needed to set header of the table
	    endtablePanel.add(new JScrollPane(table));
	    contentPaneEnd.add(endtablePanel, BorderLayout.CENTER);
	    
		endtextPanel = new JPanel();
		endtextPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		endLabel.setFont(MENU_FONT);
		endtextPanel.add(endLabel);
		
		winnerPanel = new JPanel();
		winnerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
	    
	    int max = Math.max(scoreBlack,Math.max(scoreWhite,scoreRed));
	    if(max==scoreBlack && max==scoreWhite && max==scoreRed) {
	    	winnerPanel.add(equal).setFont(WINNER_FONT);
	    }
	    else if(max==scoreBlack && max==scoreWhite) {
	    	blackLabel.setForeground(TURN_BLACK);
	    	whiteLabel.setForeground(TURN_WHITE);
	    	winnerPanel.add(winnerAre).setFont(WINNER_FONT);	    	
	    	winnerPanel.add(blackLabel).setFont(WINNER_FONT);
	    	winnerPanel.add(and).setFont(WINNER_FONT);
	    	winnerPanel.add(whiteLabel).setFont(WINNER_FONT);
	    }
	    else if(max==scoreBlack && max==scoreRed) {
	    	blackLabel.setForeground(TURN_BLACK);
	    	redLabel.setForeground(TURN_RED);
	    	winnerPanel.add(winnerAre).setFont(WINNER_FONT);	    	
	    	winnerPanel.add(blackLabel).setFont(WINNER_FONT);
	    	winnerPanel.add(and).setFont(WINNER_FONT);
	    	winnerPanel.add(redLabel).setFont(WINNER_FONT);
	    }
	    else if(max==scoreWhite && max==scoreRed) {
	    	whiteLabel.setForeground(TURN_WHITE);
	    	redLabel.setForeground(TURN_RED);
	    	winnerPanel.add(winnerAre).setFont(WINNER_FONT);	    	
	    	winnerPanel.add(whiteLabel).setFont(WINNER_FONT);
	    	winnerPanel.add(and).setFont(WINNER_FONT);
	    	winnerPanel.add(redLabel).setFont(WINNER_FONT);
	    }
	    else if(max==scoreBlack) {
	    	blackLabel.setForeground(TURN_BLACK);
	    	winnerPanel.add(winnerIs).setFont(WINNER_FONT);
	    	winnerPanel.add(blackLabel).setFont(WINNER_FONT);
	    }
	    else if(max==scoreWhite) {
	    	whiteLabel.setForeground(TURN_WHITE);
	    	winnerPanel.add(winnerIs).setFont(WINNER_FONT);
	    	winnerPanel.add(whiteLabel).setFont(WINNER_FONT);
	    }
	    else /*if(max==scoreRed)*/ {
	    	redLabel.setForeground(TURN_RED);
	    	winnerPanel.add(winnerIs).setFont(WINNER_FONT);
	    	winnerPanel.add(redLabel).setFont(WINNER_FONT);
	    }
	    
		endPanel = new JPanel();
		endPanel.setLayout(new BorderLayout());
		endPanel.add(endtextPanel,BorderLayout.NORTH);
		endPanel.add(winnerPanel,BorderLayout.CENTER);
	    
	    contentPaneEnd.add(endPanel,BorderLayout.NORTH);
	      
		endbuttonPanel = new JPanel();
		endbuttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		endbuttonPanel.add(menuButton);
		endbuttonPanel.add(exitButton);
		
		contentPaneEnd.add(endbuttonPanel,BorderLayout.SOUTH);
		
		menuButton.addActionListener(new MenuAction(this));
		exitButton.addActionListener(new ExitAction(this));
				
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setVisible(true);
		setResizable(false);
		
	}
	
	//Clear all counters to start a new game
	public void resetvalues() {
		GameFrame.valueBlack = 0;
		GameFrame.valueWhite = 0;
		GameFrame.valueRed = 0;
		StonesManager.interBlack = 0;
		StonesManager.interWhite = 0;
		StonesManager.interRed = 0;
	}

	private class MenuAction implements ActionListener {
		//Window to be closed.
		private JFrame window;
	
		public MenuAction(JFrame window) {
			this.window = window;
		}
	
		@Override
		public void actionPerformed(ActionEvent e) {
			window.dispose();
			resetvalues();
			//Back to the Menu Frame
			menuFrame = new MainGUI("Menu");
		}
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
	
}
