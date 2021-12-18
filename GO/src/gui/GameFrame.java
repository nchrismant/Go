package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.log4j.Logger;

import config.GobanConfiguration;
import goban.map.Goban;
import goban.map.Intersection;
import goban.process.GameBuilder;
import goban.process.StonesManager;
import goban.stones.BlackStone;
import goban.stones.GobanElementFactory;
import goban.stones.RedStone;
import goban.stones.Stones;
import goban.stones.WhiteStone;
import log.LoggerUtility;

/**
 * This class contains the Game Frame
 * 
 * @author afatchawo junior
 * @author dacruz mathis
 * @author chriqui nathan
 *
 */
public class GameFrame extends JFrame{
	
private static final long serialVersionUID = 1L;
	
	private Goban goban;
	 
	private StonesManager manager;
	
	private GameDisplay dashboard;
	
	private final static Dimension preferredSize = new Dimension(GobanConfiguration.WINDOW_WIDTH, GobanConfiguration.WINDOW_HEIGHT);
	

	private static final Font TURN_FONT = new Font(Font.DIALOG, Font.BOLD, 20);
	private static final Font SCORE_FONT = new Font(Font.DIALOG, Font.BOLD, 14);
	private static final Font SCORE_FONT2 = new Font(Font.SANS_SERIF, Font.BOLD, 16);
		
	private static final Color TURN_RED = Color.RED;
	private static final Color TURN_WHITE = Color.WHITE;
	private static final Color TURN_BLACK = Color.BLACK;
	
	private static Logger logger = LoggerUtility.getLogger(GobanElementFactory.class, "html");
	
	protected JButton helpButton = new JButton("Help");
	protected JButton megapionButton = new JButton("Megapion");
	protected JButton passButton = new JButton("Pass");
	protected JButton resignButton = new JButton("Resign");
	
	protected JLabel tourLabel = new JLabel("Au tour de: ");
	protected JLabel colorLabel = new JLabel("BLACK");
	protected JLabel scoreLabel = new JLabel("Nombre de pierres capturées: ");
	protected JLabel blackLabel = new JLabel("BLACK:");
	protected JLabel whiteLabel = new JLabel("WHITE:");
	protected JLabel redLabel = new JLabel("RED:");
	protected JLabel blackvalueLabel = new JLabel("");
	protected JLabel whitevalueLabel = new JLabel("");
	protected JLabel redvalueLabel = new JLabel("");
	
	private JPanel tourPanel;
	private JPanel scorevaluePanel;
	private JPanel scorePanel;
	private JPanel menuPanel;
	private JPanel gamePanel;
	
	private int passcount = 0;
	private int pres = 0;
	int help;
	int botactive;
	public static int valueRed = 0;
	public static int valueBlack = 0;
	public static int valueWhite = 0;
	
	private List<Color> colors = new ArrayList<Color>();

	private EndFrame end;
	
	public GameFrame(int bot) {
		this.botactive = bot;
		init();
	}
	
	private void init() {
		
		setTitle("Jeu de Go");
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());

		goban = GameBuilder.buildGoban();
		manager = GameBuilder.InitStones(goban);
		dashboard = new GameDisplay(goban, manager);
		
		MouseControls mouseControls = new MouseControls();
		dashboard.addMouseListener(mouseControls);
		
		dashboard.setPreferredSize(preferredSize);
		
		tourPanel = new JPanel();
		tourPanel.setLayout(new FlowLayout(FlowLayout.LEFT));		
		tourLabel.setFont(TURN_FONT);
		colorLabel.setFont(TURN_FONT);
		colorLabel.setForeground(TURN_BLACK);
		
		tourPanel.add(tourLabel);
		tourPanel.add(colorLabel);
		contentPane.add(tourPanel,BorderLayout.WEST);
		
		scoreLabel.setFont(SCORE_FONT2);
		blackLabel.setFont(SCORE_FONT);		
		whiteLabel.setFont(SCORE_FONT);
		redLabel.setFont(SCORE_FONT);
		
		scorevaluePanel = new JPanel();
		scorevaluePanel.setLayout(new GridLayout(3,2));
		scorevaluePanel.add(blackLabel);
		scorevaluePanel.add(blackvalueLabel);
		scorevaluePanel.add(whiteLabel);
		scorevaluePanel.add(whitevalueLabel);
		scorevaluePanel.add(redLabel);
		scorevaluePanel.add(redvalueLabel);			
		
		scorePanel = new JPanel();
		scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.Y_AXIS));
		scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		scorePanel.add(scoreLabel);
		scorevaluePanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		scorePanel.add(scorevaluePanel);
		
		menuPanel = new JPanel();
		menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
		helpButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		menuPanel.add(helpButton);
		menuPanel.add(Box.createVerticalStrut(50));
		megapionButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		menuPanel.add(megapionButton);
		menuPanel.add(Box.createVerticalStrut(50));
		passButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		menuPanel.add(passButton);
		menuPanel.add(Box.createVerticalStrut(50));
		resignButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		menuPanel.add(resignButton);				
		
		gamePanel = new JPanel();
		gamePanel.setLayout(new FlowLayout());
		gamePanel.add(scorePanel);
		gamePanel.add(dashboard);
		gamePanel.add(menuPanel);
		
		contentPane.add(gamePanel,BorderLayout.CENTER);
				
		passButton.addActionListener(new PassAction(this));
		resignButton.addActionListener(new ResignAction(this));
		megapionButton.addActionListener(new MegapionAction());
		helpButton.addActionListener(new HelpAction());
					
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setVisible(true);
		setResizable(false);
	}

	private class PassAction implements ActionListener {
		//Window to be closed.
		private JFrame window;
	
		public PassAction(JFrame window) {
					this.window = window;
		}

	
	public void actionPerformed(ActionEvent e) {
		GobanConfiguration.TURN++;
		if(GobanConfiguration.TURN%3==0) {
			updateBLACKColorMessage("BLACK");
			}
		if(GobanConfiguration.TURN%3==1) {
			updateWHITEColorMessage("WHITE");
		}
		if(GobanConfiguration.TURN%3==2) {
			updateREDColorMessage(" RED ");
		}
		
		//If 3 consecutive pass then call of End Frame.
		passcount++;
		if(passcount == 3) {
			window.dispose();
			//Count all occupied intersections
			for(int i = 100 ; i < 581 ; i+= 30) {
				for(int j = 100 ; j < 581 ; j+= 30) {
				Intersection position = dashboard.getStonePosition(i,j);
				manager.CountIntersections(position);
				}
			}
			end = new EndFrame();
			}
		}
	}

	private class ResignAction implements ActionListener {
		//Window to be closed.
		private JFrame window;
	
		public ResignAction(JFrame window) {
			this.window = window;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			window.dispose();
			//Count all occupied intersections
			for(int i = 100 ; i < 581 ; i+= 30) {
				for(int j = 100 ; j < 581 ; j+= 30) {
				Intersection position = dashboard.getStonePosition(i,j);
				manager.CountIntersections(position);
				}
			}
			//If 1 player resign, call of End Frame.
			end = new EndFrame();
			}
		
	}
	
	private class MegapionAction implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
		        System.out.println("the button is pressed");
		        pres = 1;		        
			}
		}
	
	private class HelpAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			System.out.println("the button is pressed");
	        help = 1;
		}
	}
	
	//Update message by turn color
	private void updateWHITEColorMessage(String message) {
		colorLabel.setForeground(TURN_WHITE);
		colorLabel.setText(message);
	}

	private void updateREDColorMessage(String message) {
		colorLabel.setForeground(TURN_RED);
		colorLabel.setText(message);
	}

	private void updateBLACKColorMessage(String message) {
		colorLabel.setForeground(TURN_BLACK);
		colorLabel.setText(message);
	}
	
	private void updatescore() {
		blackvalueLabel.setText(valueBlack + "");
		whitevalueLabel.setText(valueWhite + "");
		redvalueLabel.setText(valueRed + "");
	}
	
	public int getRandomNumber() {
		int distance = (GobanConfiguration.BLOCK_SIZE * (GobanConfiguration.ABSCISSE_COUNT-1)) + GobanConfiguration.Abscisse_Start;
		return (int) (Math.random() * (distance + 1 - GobanConfiguration.Abscisse_Start)) + GobanConfiguration.Abscisse_Start;
	}
	
	private class MouseControls implements MouseListener {
		public void mouseClicked(MouseEvent arg0) {
			int x, y;
			if(botactive == 1 && GobanConfiguration.TURN%3==2 || help==1) {
				x = getRandomNumber();
				y = getRandomNumber();				
			}
			else {
				x = arg0.getX()+(GobanConfiguration.BLOCK_SIZE/2);
				y = arg0.getY()+(GobanConfiguration.BLOCK_SIZE/2);
			}
			int xl = x-(GobanConfiguration.BLOCK_SIZE);
			int xr = x+(GobanConfiguration.BLOCK_SIZE);
			
			int yu = y-(GobanConfiguration.BLOCK_SIZE);
			int yd = y+(GobanConfiguration.BLOCK_SIZE);
	
			Intersection stonePosition = dashboard.getStonePosition(x,y);
			System.out.println(stonePosition);

			//Verifie si l'intersection selectionnée est libre (pas encore occupée)
			if(!manager.isOccupied(stonePosition)) {
				int liberties=4; //nb liberté initial (théorique) de notre pierre -> VERSION PIERRE INDIVIDUELLE
				int count=0; //nb de pierres voisines capturables -> VERSION PIERRE INDIVIDUELLE
				int suicide=0; //nb de pierres voisines alliés potentiellement suicidable si on joue sur StonePosition -> VERSION CHAINE DE PIERRES
				int capture=0; //nb de pierres voisines ennemies potentiellement capturables si on joue sur StonePosition -> VERSION CHAINE DE PIERRES
				int contreSuicide=0; //nb de situations où l'on evite une situation de suicide en fusionnant deux chaines par ex
				
				//Verifie si INTERSECTION VOISINE DE GAUCHE existe
				if(!goban.isOnLeftBorder(stonePosition)) {
					
					Intersection left = dashboard.getStonePosition(xl, y);
					Stones l = manager.searchStones(left);
					
					//Verifie SI le boutton MEGAPION est préssée ou non ET si elle est occupée par un ennemi
					if(pres==1 && !isExistColor(manager.turnColor()) && manager.isExist(left) && manager.isOccupied(left) && manager.isEnemy(manager.turnColor(),l.getColor())) {
						manager.remove(l);
					}
					
					//SINON cas classique
					else {
						
						//Si INTERSECTION VOISINE DE GAUCHE existe et est occupée
						if((manager.isExist(left) && manager.isOccupied(left))) {
							
							//Si INTERSECTION VOISINE DE GAUCHE occupée par un ennemi
							if(manager.isEnemy(manager.turnColor(),l.getColor())) {
								liberties--;
								
								//Si PIERRE VOISINE DE GAUCHE capturable
								if(catchStone(l)) {
									count++;
								}
								
								//Si CHAINE VOISINE DE GAUCHE capturable
								if(catchChain(l)) {	
									List<Stones> chainL = new ArrayList<Stones>();
									parcourChain(l, chainL);
									int countL=0;
									int dependentL=0;	
									for(Stones stoneL : chainL) {
										if(isDependent(stoneL)) {
											dependentL++;
										}
										countL++;
									}
									
									//Si effectif total CHAINE VOISINE DE GAUCHE = nb de pierres dependentes CHAINE VOISINE DE GAUCHE + nb ennemies pierre actuel
									if(countL==dependentL+countEnemies(stonePosition, chainL)) {
										capture++;
									}
									logger.info(" countL = "+countL+" dependentL = "+dependentL+" capture = "+capture);
								}
							}
							
							//Si CHAINE ACTUELLE CAPTURABLE 
							if(suicideChain(l)) {	
								List<Stones> chainL = new ArrayList<Stones>();
								parcourChain(l, chainL);
								int free=0;
								int exception=0;
								int countL=0;
								int dependentL=0;	
								for(Stones stoneL : chainL) {
									if(isDependent(stoneL)) {
										dependentL++;
									}
									else {
										if(countLiberties(stoneL.getPosition())-countAllied(stoneL.getPosition())>=1) {
											if(isNeighbour(stonePosition, stoneL.getPosition())) {
												exception++;
											}
											else {
												free++;
											}
										}
									}
									countL++;
								}
								
								//Si effectif total CHAINE VOISINE DE GAUCHE = nb de pierres dependentes CHAINE VOISINE DE GAUCHE + nb alliée pierre actuel OU cas exceptionnel: effectif total CHAINE VOISINE DE GAUCHE = 1
								if(countL==dependentL+exception && (free == 0) || countL==1) {
									suicide++;
								}
								
								else if(countL==dependentL+countAllied(left)) {
									suicide++;
								}
								
								//Sinon "possibilité de fusion" entre une chaine et une pierre ou 2 chaines pour eviter le suicide
								else {
									contreSuicide++;
								}
								logger.info(" countL = "+countL+" dependentL = "+dependentL+" suicide = "+suicide);
							}
						}
					}
				}
				
				//INTERSECTION VOISINE DE DROITE
				if(!goban.isOnRightBorder(stonePosition)) {
					Intersection right = dashboard.getStonePosition(xr, y);
					Stones r = manager.searchStones(right); 
					if(pres==1 && !isExistColor(manager.turnColor()) && manager.isExist(right) && manager.isOccupied(right) && manager.isEnemy(manager.turnColor(),r.getColor())) {
						manager.remove(r);
					}
					else {
						if((manager.isExist(right) && manager.isOccupied(right))) {
							if(manager.isEnemy(manager.turnColor(),r.getColor())) {
							liberties--;
							
								if(catchStone(r)) {
									count++;
								}
								if(catchChain(r)) {		
									List<Stones> chainR = new ArrayList<Stones>();
									parcourChain(r, chainR);
									int countR=0;
									int dependentR=0;	
									for(Stones stoneR : chainR) {
										if(isDependent(stoneR)) {
											dependentR++;
										}
										countR++;
									}
									if(countR==dependentR+countEnemies(stonePosition, chainR)) {
										capture++;
									}
									logger.info(" countR = "+countR+" dependentR = "+dependentR+" capture = "+capture);
								}
							}
							if(suicideChain(r)) {		
								List<Stones> chainR = new ArrayList<Stones>();
								parcourChain(r, chainR);
								int free=0;
								int exception=0;
								int countR=0;
								int dependentR=0;	
								for(Stones stoneR : chainR) {
									if(isDependent(stoneR)) {
										dependentR++;
									}
									else {
										if(countLiberties(stoneR.getPosition())-countAllied(stoneR.getPosition())>=1) {
											if (isNeighbour(stonePosition, stoneR.getPosition())) {
												exception++;
											}
											else {
												free++;
											}
										}
									}
									countR++;
								}
								if(countR==dependentR+exception && (free == 0) || countR==1) {
									suicide++;
								}
								else if(countR==dependentR+countAllied(right)) {
									suicide++;
								}
								else {
									contreSuicide++;
								}
								logger.info(" countR = "+countR+" dependentR = "+dependentR+" suicide = "+suicide);
							}
						}
					}
				}
				
				//INTERSECTION VOISINE DU HAUT
				if(!goban.isOnTopBorder(stonePosition)) {
					Intersection up = dashboard.getStonePosition(x, yu);
					Stones u = manager.searchStones(up);
					if(pres==1 && !isExistColor(manager.turnColor()) && manager.isExist(up) && manager.isOccupied(up) && manager.isEnemy(manager.turnColor(),u.getColor())) {
						manager.remove(u);
					}
					else {
						if((manager.isExist(up)&& manager.isOccupied(up))) {
							if(manager.isEnemy(manager.turnColor(),u.getColor())) {
								liberties--;						
								if(catchStone(u)) {
									count++;
								}
								if(catchChain(u)) {			
									List<Stones> chainU = new ArrayList<Stones>();
									parcourChain(u, chainU);
									int countU=0;
									int dependentU=0;	
									for(Stones stoneU : chainU) {
										if(isDependent(stoneU)) {
											dependentU++;
										}
										countU++;
									}
									if(countU==dependentU+countEnemies(stonePosition, chainU)) {
										capture++;
									}
									logger.info(" countU = "+countU+" dependentU = "+dependentU+" capture = "+capture);
								}
							}	
							if(suicideChain(u)) {			
								List<Stones> chainU = new ArrayList<Stones>();
								parcourChain(u, chainU);
								int free=0;
								int exception=0;
								int countU=0;
								int dependentU=0;	
								for(Stones stoneU : chainU) {
									if(isDependent(stoneU)) {
										dependentU++;
									}
									else {
										if(countLiberties(stoneU.getPosition())-countAllied(stoneU.getPosition())>=1) {
											if(isNeighbour(stonePosition, stoneU.getPosition())) {
												exception++;
											}
											else {
												free++;
											}
											
										}
									}
									countU++;
								}
								if(countU==dependentU+exception && (free == 0) || countU==1) {
									suicide++;
								}
								else if(countU==dependentU+countAllied(up)) {
									suicide++;
								}
								else {
									contreSuicide++;
								}
								logger.info(" countU = "+countU+" dependentU = "+dependentU+" suicide = "+suicide);
							}
						}
					}
				}
				
				//INTERSECTION VOISINE DU BAS
				if(!goban.isOnBottomBorder(stonePosition)) {
					Intersection down = dashboard.getStonePosition(x, yd);
					Stones d = manager.searchStones(down);
					if(pres==1 && !isExistColor(manager.turnColor()) && manager.isExist(down) && manager.isOccupied(down) && manager.isEnemy(manager.turnColor(),d.getColor())) {
						manager.remove(d);
					}
					else {
						if((manager.isExist(down)&& manager.isOccupied(down))) {
							if(manager.isEnemy(manager.turnColor(),d.getColor())) {
								liberties--;
								if(catchStone(d)) {
									count++;
								}
								if(catchChain(d)) {	
									List<Stones> chainD = new ArrayList<Stones>();
									parcourChain(d, chainD);
									int countD=0;
									int dependentD=0;	
									for(Stones stoneD : chainD) {
										if(isDependent(stoneD)) {
											dependentD++;
										}
										countD++;
									}
									if(countD==dependentD+countEnemies(stonePosition, chainD)) {
										capture++;
									}
									logger.info(" countD = "+countD+" dependentD = "+dependentD+" capture = "+capture);
								}
							}
							if(suicideChain(d)) {	
								List<Stones> chainD = new ArrayList<Stones>();
								parcourChain(d, chainD);
								int free=0;
								int exception=0;
								int countD=0;
								int dependentD=0;	
								for(Stones stoneD : chainD) {
									if(isDependent(stoneD)) {
										dependentD++;
									}
									else {
										if(countLiberties(stoneD.getPosition())-countAllied(stoneD.getPosition())>=1) {
											if(isNeighbour(stonePosition, stoneD.getPosition())) {
												exception++;
											}
											else {
												free++;
											}
										}
									}
									countD++;
								}
								if(countD==dependentD+exception && (free==0) || countD==1) {
									suicide++;
								}
								else if(countD==dependentD+countAllied(down)) {
									suicide++;
								}
								else {
									contreSuicide++;
								}
								logger.info(" countD = "+countD+" dependentD = "+dependentD+" suicide = "+suicide);
							}
						}
					}
				}
				
				//Si PIERRE ACTUELLE dans un coin
				if(goban.isOnBottom(stonePosition)) {
					liberties = liberties-2;
				}
				
				//Si PIERRE ACTUELLE sur un bord
				else if(goban.isOnBorder(stonePosition)) {
					liberties--;
				}
				
				logger.info("CAPTURE = "+capture);
				logger.info("SUICIDE = "+suicide);
				logger.info("liberties = "+liberties);
				logger.info("COUNT = "+count);
				logger.info("CONTRESUICIDE = "+contreSuicide);
				
				//Si toutes les regles sont respectées et exceptions pris en comptes alors on peut jouer notre pierre (affichage sur le goban)
				if(((liberties != 0 || count>0 || capture>0 || (liberties==0 && pres==1)) && ( pres==1 || (suicide==0) || (contreSuicide>0) || (capture>0) || ((suicide>0) && !isDependent2(stonePosition)))) || (pres==1) || (suicide<liberties)){
					
					if(GobanConfiguration.TURN%3==0) {				
						Stones blackstones = new BlackStone(stonePosition); 
						logger.info("BlackStone creation on the intersection :  x = " + stonePosition.getAbscisse() + " y = " + stonePosition.getOrdonnee());
						manager.putStones(blackstones);
						updateWHITEColorMessage("WHITE");
					}
					else if(GobanConfiguration.TURN%3==1) {
						Stones whitestones = new WhiteStone(stonePosition);
						logger.info("BlackStone creation on the intersection :  x = " + stonePosition.getAbscisse() + " y = " + stonePosition.getOrdonnee());
						manager.putStones(whitestones);
						updateREDColorMessage(" RED ");
					}
					else {	
						Stones redstones = new RedStone(stonePosition);
						logger.info("BlackStone creation on the intersection :  x = " + stonePosition.getAbscisse() + " y = " + stonePosition.getOrdonnee());
						manager.putStones(redstones);
						updateBLACKColorMessage("BLACK");
					}
					
					//Création de notre pierre actuelle
					Stones actual = manager.searchStones(stonePosition);
					
					if(pres==1) {
						colors.add(manager.turnColor());
					}
					
					if(count>0 || capture>0) {
						liberties+=count;
						isCaptured(stonePosition); //Situation de suicide dans laquelle où on peut jouer notre pierre car elle permet la capture d'une pierre ennemie
					}
					else {
						isCaptured(); //Situation de capture d'UNE pierre classique
					}
					
					if(suicide>0) {
						List<Stones> saveChain = new ArrayList<Stones>();
						parcourChain(actual,saveChain);
						isCapturedChain(saveChain); //Situation de suicide dans laquelle où on peut jouer notre pierre car elle permet la capture d'une chaine ennemie
					}
					else {
						isCapturedChain(); //Situation de capture de chaine classique
					}					
					
					logger.info(manager.toString());
					passcount=0;
					pres=0;
					help=0;
					GobanConfiguration.TURN++;
					updatescore();
					dashboard.repaint();
				}
			}
		}
		
		//pierre dont le nombre de libertées nb d'alliées (chaine) : c-a-d la caputre ou la subsistance de cette pierre dépend de l'état des autres pierres de la chaine 
		public boolean isDependent (Stones stones) {
			return countLiberties(stones.getPosition())==countAllied(stones.getPosition());				
		}
		
		//Pareil que isDependent mais version intersection en parammètre
		public boolean isDependent2 (Intersection intersection) {
			return countLiberties2(intersection)==countAllied2(intersection);				
		}
		
		//Vérifie si il s'agit d'une intersection voisine
		public boolean isNeighbour(Intersection intersection, Intersection neighbour) {
			Intersection result1 = new Intersection(0,0);
			Intersection result2 = new Intersection(0,0);
			Intersection result3 = new Intersection(0,0);
			Intersection result4 = new Intersection(0,0);
					
			int x = intersection.getAbscisse();
			int y = intersection.getOrdonnee();
					
			int xl = intersection.getAbscisse()-(GobanConfiguration.BLOCK_SIZE);
			int xr = intersection.getAbscisse()+(GobanConfiguration.BLOCK_SIZE);
					
			int yu = intersection.getOrdonnee()-(GobanConfiguration.BLOCK_SIZE);
			int yd = intersection.getOrdonnee()+(GobanConfiguration.BLOCK_SIZE);
					
			if(!goban.isOnLeftBorder(intersection)) {
				Intersection left = dashboard.getStonePosition(xl, y);
				Stones l = manager.searchStones(left);
				if(manager.isExist(left)&& manager.isOccupied(left) && !manager.isEnemy(l.getColor(),manager.turnColor())) {
					result1=left;
				}
			}
			if(!goban.isOnRightBorder(intersection)) {
				Intersection right = dashboard.getStonePosition(xr, y);
				Stones r = manager.searchStones(right); 
				if(manager.isExist(right)&& manager.isOccupied(right) && !manager.isEnemy(r.getColor(),manager.turnColor())) {
					result2=right;
				}
			}
			if(!goban.isOnTopBorder(intersection)) {
				Intersection up = dashboard.getStonePosition(x, yu);
				Stones u = manager.searchStones(up);
				if(manager.isExist(up)&& manager.isOccupied(up) && !manager.isEnemy(u.getColor(),manager.turnColor())) {
					result3=up;
				}
			}
			if(!goban.isOnBottomBorder(intersection)) {
				Intersection down = dashboard.getStonePosition(x, yd);
				Stones d = manager.searchStones(down);
				if(manager.isExist(down)&& manager.isOccupied(down) && !manager.isEnemy(d.getColor(),manager.turnColor())) {
					result4=down;
				}
			}				
			return (result1==neighbour || result2==neighbour || result3==neighbour || result4==neighbour);
			}			
		
		public boolean isExistColor (Color color) {
			Color result= null;
			for(Color i : colors) {
				if (i.equals(color)) {
					result = i;
				}
			}
			return result == color;
		}
		
		//Vérifie si la PIERRE voisine possède uniquement 1 liberté et est une pierre ENNEMIE (verfie si elle est capturable ou non) 
		public boolean catchStone(Stones stones) {
			return countLiberties(stones.getPosition())==1 && manager.isEnemy(manager.turnColor(),stones.getColor());			
		}
		
		//Vérifie si la CHAINE voisine possède uniquement 1 liberté et est une CHAINE ALLIEE (verfie si elle est capturable ou non) 
		public boolean suicideChain(Stones stones) {
			return (countLiberties(stones.getPosition())==countAllied(stones.getPosition())+1) && !manager.isEnemy(manager.turnColor(),stones.getColor());			
		}
		
		//Vérifie si la CHAINE voisine possède uniquement 1 liberté et est une CHAINE ENNEMIE (verfie si elle est capturable ou non) 
		public boolean catchChain(Stones stones) {
			return (countLiberties(stones.getPosition())==countAllied(stones.getPosition())+1) && manager.isEnemy(manager.turnColor(),stones.getColor());			
		}
		
		//Capture de PIERRE individuelle classique
		public void isCaptured(/*int count*/) {
			List<Stones> eliminatedStones = new ArrayList<Stones>();
			for(Stones stones : manager.getStonesIntersection()) {
				if (countLiberties(stones.getPosition())==0) {			
					eliminatedStones.add(stones);
					//Updating score
					if(manager.turnColor() == Color.BLACK) {
						valueBlack++;
					}
					else if(manager.turnColor() == Color.WHITE) {
						valueWhite++;
					}
					else {
						valueRed++;
					}
				}
			}	
			for(Stones stones : eliminatedStones) {
				manager.remove(stones);
			}		
		}
		
		//Capture de PIERRE individuelle dans le cas d'une situation de suicide
		public void isCaptured(Intersection savedStones) {
			List<Stones> eliminatedStones = new ArrayList<Stones>();
			for(Stones stones : manager.getStonesIntersection()) {
				if (countLiberties(stones.getPosition())==0 && savedStones!=stones.getPosition()) {			
					eliminatedStones.add(stones);
					//Updating score
					if(manager.turnColor() == Color.BLACK) {
						valueBlack++;
					}
					else if(manager.turnColor() == Color.WHITE) {
						valueWhite++;
					}
					else {
						valueRed++;
					}
				}
			}	
			for(Stones stones : eliminatedStones) {
				manager.remove(stones);
			}		
		}		
		
		//Parcourir une chaine
		public void parcourChain(Stones stones, List<Stones> chain){				
				while(!manager.isExist(stones, chain)) {
					
					chain.add(stones);
					
					int x = stones.getPosition().getAbscisse();
					int y = stones.getPosition().getOrdonnee();
					
					int xl = stones.getPosition().getAbscisse()-(GobanConfiguration.BLOCK_SIZE);
					int xr = stones.getPosition().getAbscisse()+(GobanConfiguration.BLOCK_SIZE);
					
					int yu = stones.getPosition().getOrdonnee()-(GobanConfiguration.BLOCK_SIZE);
					int yd = stones.getPosition().getOrdonnee()+(GobanConfiguration.BLOCK_SIZE);
					
					if(!goban.isOnLeftBorder(stones.getPosition())) {
						Intersection left = dashboard.getStonePosition(xl, y);
						Stones l = manager.searchStones(left);
						if(manager.isExist(left)&& manager.isOccupied(left) && !manager.isEnemy(l.getColor(),stones.getColor())) {
							parcourChain(l, chain);
						}
					}
					if(!goban.isOnRightBorder(stones.getPosition())) {
						Intersection right = dashboard.getStonePosition(xr, y);
						Stones r = manager.searchStones(right); 
						if(manager.isExist(right)&& manager.isOccupied(right) && !manager.isEnemy(r.getColor(),stones.getColor())) {
							parcourChain(r, chain);
						}
					}
					if(!goban.isOnTopBorder(stones.getPosition())) {
						Intersection up = dashboard.getStonePosition(x, yu);
						Stones u = manager.searchStones(up);
						if(manager.isExist(up)&& manager.isOccupied(up) && !manager.isEnemy(u.getColor(),stones.getColor())) {
							parcourChain(u, chain);
						}
					}
					if(!goban.isOnBottomBorder(stones.getPosition())) {
						Intersection down = dashboard.getStonePosition(x, yd);
						Stones d = manager.searchStones(down);
						if(manager.isExist(down)&& manager.isOccupied(down) && !manager.isEnemy(d.getColor(),stones.getColor())) {
							parcourChain(d, chain);
						}
					}
				}
		}
		
		//Capture de chaine de pierre classique
		public void isCapturedChain() {
			List<Stones> eliminatedChains1 = new ArrayList<Stones>();
			List<List<Stones>> chains = new ArrayList<List<Stones>>();		
			for(Stones stones : manager.getStonesIntersection()) {
				List<Stones> chain = new ArrayList<Stones>();
				if(isDependent(stones) && !manager.isExist(stones, chain)) {				
						
						chain.add(stones);
						
						int x = stones.getPosition().getAbscisse();
						int y = stones.getPosition().getOrdonnee();
						
						int xl = stones.getPosition().getAbscisse()-(GobanConfiguration.BLOCK_SIZE);
						int xr = stones.getPosition().getAbscisse()+(GobanConfiguration.BLOCK_SIZE);
						
						int yu = stones.getPosition().getOrdonnee()-(GobanConfiguration.BLOCK_SIZE);
						int yd = stones.getPosition().getOrdonnee()+(GobanConfiguration.BLOCK_SIZE);
						
						if(!goban.isOnLeftBorder(stones.getPosition())) {
							Intersection left = dashboard.getStonePosition(xl, y);
							Stones l = manager.searchStones(left);
							if(manager.isExist(left)&& manager.isOccupied(left) && !manager.isEnemy(l.getColor(),stones.getColor())) {
								parcourChain(l, chain);
							}
						}
						if(!goban.isOnRightBorder(stones.getPosition())) {
							Intersection right = dashboard.getStonePosition(xr, y);
							Stones r = manager.searchStones(right); 
							if(manager.isExist(right)&& manager.isOccupied(right) && !manager.isEnemy(r.getColor(),stones.getColor())) {
								parcourChain(r, chain);
							}
						}
						if(!goban.isOnTopBorder(stones.getPosition())) {
							Intersection up = dashboard.getStonePosition(x, yu);
							Stones u = manager.searchStones(up);
							if(manager.isExist(up)&& manager.isOccupied(up) && !manager.isEnemy(u.getColor(),stones.getColor())) {
								parcourChain(u, chain);
							}
						}
						if(!goban.isOnBottomBorder(stones.getPosition())) {
							Intersection down = dashboard.getStonePosition(x, yd);
							Stones d = manager.searchStones(down);
							if(manager.isExist(down)&& manager.isOccupied(down) && !manager.isEnemy(d.getColor(),stones.getColor())) {
								parcourChain(d, chain);
							}
						}
				}
				chains.add(chain);
			}
				
				for(List<Stones> chain : chains) {
					int count=0;
					int dependent=0;	
					for(Stones stone : chain) {
						if(isDependent(stone)) {
							dependent++;
						}
						count++;
					}
					System.out.println(chain+" count = "+count+" dependent = "+dependent);
					if(count>1 && count==dependent) {
						for(Stones stone : chain) {
							eliminatedChains1.add(stone);
						}
						//Updating score
						if(manager.turnColor() == Color.BLACK) {
							valueBlack++;
						}
						else if(manager.turnColor() == Color.WHITE) {
							valueWhite++;
						}
						else {
							valueRed++;
						}
					}				
				}
				System.out.println(eliminatedChains1);
				
				for(Stones stone : eliminatedChains1) {
					manager.remove(stone);	
				}
		}
		
		//Capture de chaine de pierre dans le cas d'une situation de suicide
		public void isCapturedChain(List<Stones> savedChain) {
			List<Stones> eliminatedChains1 = new ArrayList<Stones>();
			List<List<Stones>> chains = new ArrayList<List<Stones>>();		
			for(Stones stones : manager.getStonesIntersection()) {
				List<Stones> chain = new ArrayList<Stones>();
				if(isDependent(stones) && !manager.isExist(stones, chain)) {				
						
						chain.add(stones);
						
						int x = stones.getPosition().getAbscisse();
						int y = stones.getPosition().getOrdonnee();
						
						int xl = stones.getPosition().getAbscisse()-(GobanConfiguration.BLOCK_SIZE);
						int xr = stones.getPosition().getAbscisse()+(GobanConfiguration.BLOCK_SIZE);
						
						int yu = stones.getPosition().getOrdonnee()-(GobanConfiguration.BLOCK_SIZE);
						int yd = stones.getPosition().getOrdonnee()+(GobanConfiguration.BLOCK_SIZE);
						
						if(!goban.isOnLeftBorder(stones.getPosition())) {
							Intersection left = dashboard.getStonePosition(xl, y);
							Stones l = manager.searchStones(left);
							if(manager.isExist(left)&& manager.isOccupied(left) && !manager.isEnemy(l.getColor(),stones.getColor())) {
								parcourChain(l, chain);
							}
						}
						if(!goban.isOnRightBorder(stones.getPosition())) {
							Intersection right = dashboard.getStonePosition(xr, y);
							Stones r = manager.searchStones(right); 
							if(manager.isExist(right)&& manager.isOccupied(right) && !manager.isEnemy(r.getColor(),stones.getColor())) {
								parcourChain(r, chain);
							}
						}
						if(!goban.isOnTopBorder(stones.getPosition())) {
							Intersection up = dashboard.getStonePosition(x, yu);
							Stones u = manager.searchStones(up);
							if(manager.isExist(up)&& manager.isOccupied(up) && !manager.isEnemy(u.getColor(),stones.getColor())) {
								parcourChain(u, chain);
							}
						}
						if(!goban.isOnBottomBorder(stones.getPosition())) {
							Intersection down = dashboard.getStonePosition(x, yd);
							Stones d = manager.searchStones(down);
							if(manager.isExist(down)&& manager.isOccupied(down) && !manager.isEnemy(d.getColor(),stones.getColor())) {
								parcourChain(d, chain);
							}
						}
				}
				chains.add(chain);
			}
				for(List<Stones> chain : chains) {
					int count=0;
					int dependent=0;
					for(Stones stone : chain) {
						if(isDependent(stone)) {
							dependent++;
						}
						count++;
					}
					
					System.out.println(chain+" count = "+count+" dependent = "+dependent);
					if(count>1 && count==dependent) {
						
						for(Stones stone1 : chain) {
							int verify=0;
							int ok=0;
							for(Stones stone : savedChain) {
								ok++;
								if(!stone.getPosition().equals(stone1.getPosition())) {
									verify++;
								}
							}
							if(ok==verify) {
								eliminatedChains1.add(stone1);
								//Updating score
								if(manager.turnColor() == Color.BLACK) {
									valueBlack++;
								}
								else if(manager.turnColor() == Color.WHITE) {
									valueWhite++;
								}
								else {
									valueRed++;
								}
							}
						}
					}				
				}
				
				System.out.println("///////////////"+savedChain);
				System.out.println("///////////////"+eliminatedChains1);

				for(Stones stone : eliminatedChains1) {
					manager.remove(stone);	
				}
		}
		
		//Compte le nombre de libertées d'une pierre sur une intersection donné
		public int countLiberties(Intersection intersection) {
			
			int liberties=4;
			
			int x = intersection.getAbscisse();
			int y = intersection.getOrdonnee();
			
			int xl = intersection.getAbscisse()-(GobanConfiguration.BLOCK_SIZE);
			int xr = intersection.getAbscisse()+(GobanConfiguration.BLOCK_SIZE);
			
			int yu = intersection.getOrdonnee()-(GobanConfiguration.BLOCK_SIZE);
			int yd = intersection.getOrdonnee()+(GobanConfiguration.BLOCK_SIZE);
			
			Stones stones = manager.searchStones(intersection);
			
			if(!goban.isOnLeftBorder(intersection)) {
				Intersection left = dashboard.getStonePosition(xl, y);
				Stones l = manager.searchStones(left);
				if(( manager.isExist(left) && manager.isOccupied(left)) &&  manager.isEnemy( stones.getColor(),l.getColor())) {
					liberties--;
				}
			}
			if(!goban.isOnRightBorder(intersection)) {
				Intersection right = dashboard.getStonePosition(xr, y);
				Stones r = manager.searchStones(right); 
				if(( manager.isExist(right) && manager.isOccupied(right)) &&  manager.isEnemy( stones.getColor(),r.getColor())) {
					liberties--;
				}
			}
			if(!goban.isOnTopBorder(intersection)) {
				Intersection up = dashboard.getStonePosition(x, yu);
				Stones u = manager.searchStones(up);
				if((manager.isExist(up)&& manager.isOccupied(up)) &&  manager.isEnemy( stones.getColor(),u.getColor())) {
					liberties--;
				}
			}
			if(!goban.isOnBottomBorder(intersection)) {
				Intersection down = dashboard.getStonePosition(x, yd);
				Stones d = manager.searchStones(down);
				if((manager.isExist(down)&& manager.isOccupied(down)) &&  manager.isEnemy( stones.getColor(),d.getColor())) {
					liberties--;
				}
			}
		
			if(goban.isOnBottom(intersection)) {
				liberties = liberties-2;
			}
			else if(goban.isOnBorder(intersection)) {
				liberties--;
			}
			
			return liberties;
		}
		
		//Compte le nombre d'alliés d'une pierre sur une intersection donné
				public int countAllied(Intersection intersection) {
					
					int allies=0;
					
					int x = intersection.getAbscisse();
					int y = intersection.getOrdonnee();
					
					int xl = intersection.getAbscisse()-(GobanConfiguration.BLOCK_SIZE);
					int xr = intersection.getAbscisse()+(GobanConfiguration.BLOCK_SIZE);
					
					int yu = intersection.getOrdonnee()-(GobanConfiguration.BLOCK_SIZE);
					int yd = intersection.getOrdonnee()+(GobanConfiguration.BLOCK_SIZE);
					
					Stones stones = manager.searchStones(intersection);
					
					if(!goban.isOnLeftBorder(intersection)) {
						Intersection left = dashboard.getStonePosition(xl, y);
						Stones l = manager.searchStones(left);
						if(( manager.isExist(left) && manager.isOccupied(left)) &&  !manager.isEnemy( stones.getColor(),l.getColor())) {
							allies++;
						}
					}
					if(!goban.isOnRightBorder(intersection)) {
						Intersection right = dashboard.getStonePosition(xr, y);
						Stones r = manager.searchStones(right); 
						if(( manager.isExist(right) && manager.isOccupied(right)) &&  !manager.isEnemy( stones.getColor(),r.getColor())) {
							allies++;
						}
					}
					if(!goban.isOnTopBorder(intersection)) {
						Intersection up = dashboard.getStonePosition(x, yu);
						Stones u = manager.searchStones(up);
						if((manager.isExist(up)&& manager.isOccupied(up)) &&  !manager.isEnemy( stones.getColor(),u.getColor())) {
							allies++;
						}
					}
					if(!goban.isOnBottomBorder(intersection)) {
						Intersection down = dashboard.getStonePosition(x, yd);
						Stones d = manager.searchStones(down);
						if((manager.isExist(down)&& manager.isOccupied(down)) &&  !manager.isEnemy( stones.getColor(),d.getColor())) {
							allies++;
						}
					}
					
					return allies;
				}
				
				//Compte le nombre de libertées d'une pierre sur une intersection donné (version intersection en paramètre) 
				public int countLiberties2(Intersection intersection) {
					
					int liberties=4;
					
					int x = intersection.getAbscisse();
					int y = intersection.getOrdonnee();
					
					int xl = intersection.getAbscisse()-(GobanConfiguration.BLOCK_SIZE);
					int xr = intersection.getAbscisse()+(GobanConfiguration.BLOCK_SIZE);
					
					int yu = intersection.getOrdonnee()-(GobanConfiguration.BLOCK_SIZE);
					int yd = intersection.getOrdonnee()+(GobanConfiguration.BLOCK_SIZE);
					
					if(!goban.isOnLeftBorder(intersection)) {
						Intersection left = dashboard.getStonePosition(xl, y);
						Stones l = manager.searchStones(left);
						if(( manager.isExist(left) && manager.isOccupied(left)) &&  manager.isEnemy( manager.turnColor(),l.getColor())) {
							liberties--;
						}
					}
					if(!goban.isOnRightBorder(intersection)) {
						Intersection right = dashboard.getStonePosition(xr, y);
						Stones r = manager.searchStones(right); 
						if(( manager.isExist(right) && manager.isOccupied(right)) &&  manager.isEnemy( manager.turnColor(),r.getColor())) {
							liberties--;
						}
					}
					if(!goban.isOnTopBorder(intersection)) {
						Intersection up = dashboard.getStonePosition(x, yu);
						Stones u = manager.searchStones(up);
						if((manager.isExist(up)&& manager.isOccupied(up)) &&  manager.isEnemy( manager.turnColor(),u.getColor())) {
							liberties--;
						}
					}
					if(!goban.isOnBottomBorder(intersection)) {
						Intersection down = dashboard.getStonePosition(x, yd);
						Stones d = manager.searchStones(down);
						if((manager.isExist(down)&& manager.isOccupied(down)) &&  manager.isEnemy( manager.turnColor(),d.getColor())) {
							liberties--;
						}
					}
				
					if(goban.isOnBottom(intersection)) {
						liberties = liberties-2;
					}
					else if(goban.isOnBorder(intersection)) {
						liberties--;
					}
					
					return liberties;
				}
				
				//Compte le nombre d'alliés d'une pierre sur une intersection donné (version intersection en paramètre)
				public int countAllied2(Intersection intersection) {
					
					int allies=0;
					
					int x = intersection.getAbscisse();
					int y = intersection.getOrdonnee();
					
					int xl = intersection.getAbscisse()-(GobanConfiguration.BLOCK_SIZE);
					int xr = intersection.getAbscisse()+(GobanConfiguration.BLOCK_SIZE);
					
					int yu = intersection.getOrdonnee()-(GobanConfiguration.BLOCK_SIZE);
					int yd = intersection.getOrdonnee()+(GobanConfiguration.BLOCK_SIZE);
					
					
					
					if(!goban.isOnLeftBorder(intersection)) {
						Intersection left = dashboard.getStonePosition(xl, y);
						Stones l = manager.searchStones(left);
						if(( manager.isExist(left) && manager.isOccupied(left)) &&  !manager.isEnemy( manager.turnColor(),l.getColor())) {
							allies++;
						}
					}
					if(!goban.isOnRightBorder(intersection)) {
						Intersection right = dashboard.getStonePosition(xr, y);
						Stones r = manager.searchStones(right); 
						if(( manager.isExist(right) && manager.isOccupied(right)) &&  !manager.isEnemy( manager.turnColor(),r.getColor())) {
							allies++;
						}
					}
					if(!goban.isOnTopBorder(intersection)) {
						Intersection up = dashboard.getStonePosition(x, yu);
						Stones u = manager.searchStones(up);
						if((manager.isExist(up)&& manager.isOccupied(up)) &&  !manager.isEnemy( manager.turnColor(),u.getColor())) {
							allies++;
						}
					}
					if(!goban.isOnBottomBorder(intersection)) {
						Intersection down = dashboard.getStonePosition(x, yd);
						Stones d = manager.searchStones(down);
						if((manager.isExist(down)&& manager.isOccupied(down)) &&  !manager.isEnemy( manager.turnColor(),d.getColor())) {
							allies++;
						}
					}
					
					return allies;
				}
				
				//Compte le nombre d'alliés d'une pierre sur une intersection donné (unique version)
				public int countEnemies(Intersection intersection, List <Stones> chain) {
					
					int enemies=0;
					
					int x = intersection.getAbscisse();
					int y = intersection.getOrdonnee();
					
					int xl = intersection.getAbscisse()-(GobanConfiguration.BLOCK_SIZE);
					int xr = intersection.getAbscisse()+(GobanConfiguration.BLOCK_SIZE);
					
					int yu = intersection.getOrdonnee()-(GobanConfiguration.BLOCK_SIZE);
					int yd = intersection.getOrdonnee()+(GobanConfiguration.BLOCK_SIZE);
										
					if(!goban.isOnLeftBorder(intersection)) {
						Intersection left = dashboard.getStonePosition(xl, y);
						Stones l = manager.searchStones(left);
						if(( manager.isExist(left) && manager.isOccupied(left)) &&  manager.isEnemy( manager.turnColor(),l.getColor())) {
							if(manager.isExist(l, chain)) {
								enemies++;
							}						
						}
					}
					if(!goban.isOnRightBorder(intersection)) {
						Intersection right = dashboard.getStonePosition(xr, y);
						Stones r = manager.searchStones(right); 
						if(( manager.isExist(right) && manager.isOccupied(right)) &&  manager.isEnemy( manager.turnColor(),r.getColor())) {
							if(manager.isExist(r, chain)) {
								enemies++;
							}
						}
					}
					if(!goban.isOnTopBorder(intersection)) {
						Intersection up = dashboard.getStonePosition(x, yu);
						Stones u = manager.searchStones(up);
						if((manager.isExist(up)&& manager.isOccupied(up)) &&  manager.isEnemy( manager.turnColor(),u.getColor())) {
							if(manager.isExist(u, chain)) {
								enemies++;
							}
						}
					}
					if(!goban.isOnBottomBorder(intersection)) {
						Intersection down = dashboard.getStonePosition(x, yd);
						Stones d = manager.searchStones(down);
						if((manager.isExist(down)&& manager.isOccupied(down)) &&  manager.isEnemy( manager.turnColor(),d.getColor())) {
							if(manager.isExist(d, chain)) {
								enemies++;
							}
						}
					}
					
					return enemies;
				}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	
		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	
		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	
		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
		
		}
	}
}
