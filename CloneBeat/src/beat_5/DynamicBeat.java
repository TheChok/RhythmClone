package beat_5;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

// DynamicBeat //
public class DynamicBeat extends JFrame {
	
	private int 		mouseX, mouseY;
	private Graphics 	screenGraphic;
	private Image 		screenImage;
	
	// 백그라운드(변수 유지한 채 이벤트에 따라 이미지 경로만 바뀌게 됨)
	private Image		background 				= new ImageIcon(Main.class.getResource("../images/introBackground.jpg")).getImage();
	
	// 화면 최상단 메뉴바
	private JLabel		menuBar 				= new JLabel(new ImageIcon(Main.class.getResource("../images/menuBar.png")));
	
	// 버튼에 사용할 이미지(메뉴바에서 종료, 시작하기, 종료하기)
	private ImageIcon	exitButtonBasicImage 	= new ImageIcon(Main.class.getResource("../images/exitButtonBasic.png"));
	private ImageIcon	exitButtonEnteredImage	= new ImageIcon(Main.class.getResource("../images/exitButtonEntered.png"));
	private ImageIcon	startButtonBasicImage	= new ImageIcon(Main.class.getResource("../images/startButtonBasic.png"));
	private ImageIcon	startButtonEnteredImage	= new ImageIcon(Main.class.getResource("../images/startButtonEntered.png"));
	private ImageIcon	quitButtonBasicImage	= new ImageIcon(Main.class.getResource("../images/quitButtonBasic.png"));
	private ImageIcon	quitButtonEnteredImage	= new ImageIcon(Main.class.getResource("../images/quitButtonEntered.png"));
	
	// 각종 버튼(메뉴바에서 종료, 시작하기, 종료하기)
	private JButton		exitButton				= new JButton(exitButtonBasicImage);
	private JButton		startButton				= new JButton(startButtonBasicImage);
	private JButton		quitButton				= new JButton(quitButtonBasicImage);
	
	
	// 기본생성자에서 Init
	public DynamicBeat() {
		//JFrame, 프레임 기본 설정
		setUndecorated(true);
		setTitle("Dynamic Beat");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true); 
		setBackground(new Color(0, 0, 0, 0));
		setLayout(null);
		
		
		// exitButton - 메뉴바에서 종료 버튼
		exitButton.setBounds(1253, 5, 21, 21);
		exitButton.setBorderPainted(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				exitButton.setIcon(exitButtonEnteredImage);
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				exitButton.setIcon(exitButtonBasicImage);
				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonPressedMusic = new Music("buttonPressedMusic.mp3", false);
				buttonPressedMusic.start();
				try {
					Thread.sleep(1000);
				} catch(InterruptedException ex) {
					ex.printStackTrace();
				}
				System.exit(0);
			}
		});
		add(exitButton);
		
		
		// startButton - 시작하기 버튼
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
				//Music buttonPressedMusic = new Music("buttonPressedMusic.mp3", false);
				//buttonPressedMusic.start();
				
				startButton.setVisible(false);
				quitButton.setVisible(false);
				background = new ImageIcon(Main.class.getResource("../images/mainBackground.jpg")).getImage();
			}
		});
		add(startButton);
		
		
		// quitButton - 종료하기 버튼
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
		
		
		// menuBar - 최상단 메뉴바
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
		
		
		// introMusic - Music 클래스에서 음악재생(Thread 작동)
		Music introMusic = new Music("introMusic.mp3", true);
		introMusic.start();
		
	} // End - init
	
	
	// paint - 이미지 로딩 설정
	public void paint(Graphics g) {
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		screenGraphic = screenImage.getGraphics();
		screenDraw(screenGraphic);
		g.drawImage(screenImage, 0, 0, null);
		
	} // End - paint
	
	
	// screenDraw
	public void screenDraw(Graphics g) {
		g.drawImage(background, 0, 0, null);
		paintComponents(g);
		this.repaint();
		
	} // End - screenDraw
	
	
} // End - DynamicBeat









