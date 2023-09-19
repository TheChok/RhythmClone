package beat_7;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

// DynamicBeat //
public class DynamicBeat extends JFrame {
	
	private int 		mouseX, mouseY;
	private Graphics 	screenGraphic;
	private Image 		screenImage;
	
	// ��׶���(���� ������ ä �̺�Ʈ�� ���� �̹��� ��θ� �ٲ�� ��)
	private Image		background 				= new ImageIcon(Main.class.getResource("../images/introBackground.jpg")).getImage();
	
	// ȭ�� �ֻ�� �޴���
	private JLabel		menuBar 				= new JLabel(new ImageIcon(Main.class.getResource("../images/menuBar.png")));
	
	// ��ư�� ����� �̹���(�޴��ٿ��� ����, �����ϱ�, �����ϱ�)
	// �޴��� ���� ��ư
	private ImageIcon	exitButtonBasicImage 	= new ImageIcon(Main.class.getResource("../images/exitButtonBasic.png"));
	private ImageIcon	exitButtonEnteredImage	= new ImageIcon(Main.class.getResource("../images/exitButtonEntered.png"));
	// �����ϱ�
	private ImageIcon	startButtonBasicImage	= new ImageIcon(Main.class.getResource("../images/startButtonBasic.png"));
	private ImageIcon	startButtonEnteredImage	= new ImageIcon(Main.class.getResource("../images/startButtonEntered.png"));
	// �����ϱ�
	private ImageIcon	quitButtonBasicImage	= new ImageIcon(Main.class.getResource("../images/quitButtonBasic.png"));
	private ImageIcon	quitButtonEnteredImage	= new ImageIcon(Main.class.getResource("../images/quitButtonEntered.png"));
	// ���Ӹ޴� ��, �� ��ư
	private ImageIcon	leftButtonBasicImage	= new ImageIcon(Main.class.getResource("../images/leftButtonBasic.png"));
	private ImageIcon	leftButtonEnteredImage	= new ImageIcon(Main.class.getResource("../images/leftButtonEntered.png"));
	private ImageIcon	rightButtonBasicImage	= new ImageIcon(Main.class.getResource("../images/rightButtonBasic.png"));
	private ImageIcon	rightButtonEnteredImage	= new ImageIcon(Main.class.getResource("../images/rightButtonEntered.png"));
	
	
	// ���� ��ư(�޴��ٿ��� ����, �����ϱ�, �����ϱ�, �¤��� ��ư)
	private JButton		exitButton				= new JButton(exitButtonBasicImage);
	private JButton		startButton				= new JButton(startButtonBasicImage);
	private JButton		quitButton				= new JButton(quitButtonBasicImage);
	private JButton		leftButton				= new JButton(leftButtonBasicImage);
	private JButton		rightButton				= new JButton(rightButtonBasicImage);
	
	private boolean 	isMainScreen			= false; 
	
	ArrayList<Track> 	trackList 				= new ArrayList<Track>();
	
	private Music 		selectedMusic;
	private Image		selectedImage;
	private Image		titleImage;
	private int			nowSelected = 0;
	
	
	
	// �⺻�����ڿ��� Init
	public DynamicBeat() {
		//JFrame, ������ �⺻ ����
		setUndecorated(true);
		setTitle("Dynamic Beat");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true); 
		setBackground(new Color(0, 0, 0, 0));
		setLayout(null);
		
		
		// introMusic - Music Ŭ�������� �������(Thread �۵�)
		Music introMusic = new Music("introMusic.mp3", true);
		introMusic.start();
		
		trackList.add(new Track("Picnic Title Image.png", "Picnic Start Image.jpg", "Picnic Game Image.jpg", 
								"Picnic selected.mp3", "HYP - Picnic.mp3"));
		trackList.add(new Track("Sunshine Title Image.png", "Sunshine Start Image.jpg", "Sunshine Game Image.jpg", 
								"Sunshine selected.mp3", "HYP - Full Of Sunshine.mp3"));
		
		
		// exitButton - �޴��ٿ��� ���� ��ư
		exitButton.setBounds(1253, 5, 21, 21);
		exitButton.setBorderPainted(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				exitButton.setIcon(exitButtonEnteredImage);
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				//Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				//buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				exitButton.setIcon(exitButtonBasicImage);
				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				//Music buttonPressedMusic = new Music("buttonPressedMusic.mp3", false);
				//buttonPressedMusic.start();
				try {
					Thread.sleep(1000);
				} catch(InterruptedException ex) {
					ex.printStackTrace();
				}
				System.exit(0);
			}
		});
		add(exitButton);
		
		
		// startButton - �����ϱ� ��ư
		startButton.setBounds(40, 200, 400, 100);
		startButton.setBorderPainted(false);
		startButton.setContentAreaFilled(false);
		startButton.setFocusPainted(false);
		startButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				startButton.setIcon(startButtonEnteredImage);
				startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				//Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				//buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				startButton.setIcon(startButtonBasicImage);
				startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonPressedMusic = new Music("buttonPressedMusic.mp3", false);
				buttonPressedMusic.start();
				introMusic.close();
				selectTrack(0);
				startButton.setVisible(false);
				quitButton.setVisible(false);
				leftButton.setVisible(true);
				rightButton.setVisible(true);
				
				background 	 = new ImageIcon(Main.class.getResource("../images/mainBackground.jpg")).getImage();
				isMainScreen = true;
			}
		});
		add(startButton);
		
		
		// quitButton - �����ϱ� ��ư
		quitButton.setBounds(40, 330, 400, 100);
		quitButton.setBorderPainted(false);
		quitButton.setContentAreaFilled(false);
		quitButton.setFocusPainted(false);
		quitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				quitButton.setIcon(quitButtonEnteredImage);
				quitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				//Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				//buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				quitButton.setIcon(quitButtonBasicImage);
				quitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				//Music buttonPressedMusic = new Music("buttonPressedMusic.mp3", false);
				//buttonPressedMusic.start();
				try {
					Thread.sleep(1000);
				} catch(InterruptedException ex) {
					ex.printStackTrace();
				}
				System.exit(0);
			}
		});
		add(quitButton);
		
		
		// leftButton - ����ȭ�� ���� ��ư
		leftButton.setVisible(false);
		leftButton.setBounds(140, 310, 60, 60);
		leftButton.setBorderPainted(false);
		leftButton.setContentAreaFilled(false);
		leftButton.setFocusPainted(false);
		leftButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				leftButton.setIcon(leftButtonEnteredImage);
				leftButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				//Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				//buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				leftButton.setIcon(leftButtonBasicImage);
				leftButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				//Music buttonPressedMusic = new Music("buttonPressedMusic.mp3", false);
				//buttonPressedMusic.start();
				selectLeft();
			}
		});
		add(leftButton);
		
		
		// rightButton - ����ȭ�� ���� ��ư
		rightButton.setVisible(false);
		rightButton.setBounds(1080, 310, 60, 60);
		rightButton.setBorderPainted(false);
		rightButton.setContentAreaFilled(false);
		rightButton.setFocusPainted(false);
		rightButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				rightButton.setIcon(rightButtonEnteredImage);
				rightButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				//Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				//buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				rightButton.setIcon(rightButtonBasicImage);
				rightButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				//Music buttonPressedMusic = new Music("buttonPressedMusic.mp3", false);
				//buttonPressedMusic.start();
				selectRight();
			}
		});
		add(rightButton);
		
		
		// menuBar - �ֻ�� �޴���
		menuBar.setBounds(0, 0, 1280, 30);
		menuBar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
				
			}
		});
		menuBar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				setLocation(x - mouseX, y - mouseY);
			}
		});
		add(menuBar);
		
		
		
		
	} // End - init
	
	
	// paint
	public void paint(Graphics g) {
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		screenGraphic = screenImage.getGraphics();
		screenDraw(screenGraphic);
		g.drawImage(screenImage, 0, 0, null);
		
	} // End - paint
	
	
	// screenDraw - �̹��� ��� �ε��ϱ� ���� ����
	public void screenDraw(Graphics g) {
		g.drawImage(background, 0, 0, null);
		if(isMainScreen) {
			g.drawImage(selectedImage, 340, 140, null);
			g.drawImage(titleImage, 340, 70, null);
		}
		paintComponents(g);
		this.repaint();
		
	} // End - screenDraw
	
	
	// selectTrack
	public void selectTrack(int nowSelected) {
		if(selectedMusic != null) {
			selectedMusic.close();
		}
		
		titleImage 		= new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getTitleImage())).getImage();
		selectedImage 	= new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getStartImage())).getImage();
		selectedMusic	= new Music(trackList.get(nowSelected).getStartMusic(), true);
		selectedMusic.start();
		
	} // End - selectTrack
	
	
	public void selectLeft() {
		if(nowSelected == 0) {
			nowSelected = trackList.size() - 1;
		} else {
			nowSelected--;
		}
		selectTrack(nowSelected);
	}
	
	public void selectRight() {
		if(nowSelected == trackList.size() - 1) {
			nowSelected = 0;
		} else {
			nowSelected++;
		}
		selectTrack(nowSelected);
	}
	
} // End - DynamicBeat








