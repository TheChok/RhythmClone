package beat_15;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
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
	
	private Image 		screenImage;
	private Graphics 	screenGraphic;
	private int 		mouseX, mouseY;
	
	// 화면 최상단 메뉴바
	private JLabel		menuBar 				= new JLabel(new ImageIcon(Main.class.getResource("../images/menuBar.png")));
	
	// 백그라운드(변수 유지한 채 이벤트에 따라 이미지 경로만 바뀌게 됨)
	private Image		background 				= new ImageIcon(Main.class.getResource("../images/introBackground.jpg")).getImage();
	
	
	// 버튼에 사용할 이미지
	// 메뉴바 종료 버튼 이미지
	private ImageIcon	exitButtonBasicImage 	= new ImageIcon(Main.class.getResource("../images/exitButtonBasic.png"));
	private ImageIcon	exitButtonEnteredImage	= new ImageIcon(Main.class.getResource("../images/exitButtonEntered.png"));
	// 시작하기
	private ImageIcon	startButtonBasicImage	= new ImageIcon(Main.class.getResource("../images/startButtonBasic.png"));
	private ImageIcon	startButtonEnteredImage	= new ImageIcon(Main.class.getResource("../images/startButtonEntered.png"));
	// 종료하기
	private ImageIcon	quitButtonBasicImage	= new ImageIcon(Main.class.getResource("../images/quitButtonBasic.png"));
	private ImageIcon	quitButtonEnteredImage	= new ImageIcon(Main.class.getResource("../images/quitButtonEntered.png"));
	// 게임메뉴 좌, 우 버튼 이미지
	private ImageIcon	leftButtonBasicImage	= new ImageIcon(Main.class.getResource("../images/leftButtonBasic.png"));
	private ImageIcon	leftButtonEnteredImage	= new ImageIcon(Main.class.getResource("../images/leftButtonEntered.png"));
	private ImageIcon	rightButtonBasicImage	= new ImageIcon(Main.class.getResource("../images/rightButtonBasic.png"));
	private ImageIcon	rightButtonEnteredImage	= new ImageIcon(Main.class.getResource("../images/rightButtonEntered.png"));
	// easy, hard 버튼 이미지
	private ImageIcon	easyButtonBasicImage	= new ImageIcon(Main.class.getResource("../images/easyButtonBasic.png"));
	private ImageIcon	easyButtonEnteredImage	= new ImageIcon(Main.class.getResource("../images/easyButtonEntered.png"));
	private ImageIcon	hardButtonBasicImage	= new ImageIcon(Main.class.getResource("../images/hardButtonBasic.png"));
	private ImageIcon	hardButtonEnteredImage	= new ImageIcon(Main.class.getResource("../images/hardButtonEntered.png"));
	// back 버튼 이미지
	private ImageIcon	backButtonBasicImage	= new ImageIcon(Main.class.getResource("../images/backButtonBasic.png"));
	private ImageIcon	backButtonEnteredImage	= new ImageIcon(Main.class.getResource("../images/backButtonEntered.png"));
	
	
	// 각종 버튼(메뉴바에서 종료, 시작하기, 종료하기, 좌ㆍ우 버튼, easyㆍhard 버튼)
	private JButton		exitButton				= new JButton(exitButtonBasicImage);
	private JButton		startButton				= new JButton(startButtonBasicImage);
	private JButton		quitButton				= new JButton(quitButtonBasicImage);
	private JButton		leftButton				= new JButton(leftButtonBasicImage);
	private JButton		rightButton				= new JButton(rightButtonBasicImage);
	private JButton		easyButton				= new JButton(easyButtonBasicImage);
	private JButton		hardButton				= new JButton(hardButtonBasicImage);
	private JButton		backButton				= new JButton(backButtonBasicImage);
	
	private boolean 	isMainScreen			= false;
	private boolean 	isGameScreen			= false;
	
	ArrayList<Track> 	trackList 				= new ArrayList<Track>();
	
	private Image		titleImage;
	private Image		selectedImage;
	private Music 		selectedMusic;
	private Music 		introMusic 				= new Music("introMusic.mp3", true);
	private int			nowSelected 			= 0;
	
	
	// game 변수를 유일한 인스턴스로 만듬 
	public static Game 	game;
	
	
	// 기본생성자에서 Init
	public DynamicBeat() {
		
		trackList.add(new Track("Picnic Title Image.png", "Picnic Start Image.jpg", "Picnic Game Image.jpg", 
								"Picnic selected.mp3", "HYP - Picnic.mp3", "HYP - Picnic"));
		trackList.add(new Track("Sunshine Title Image.png", "Sunshine Start Image.jpg", "Sunshine Game Image.jpg", 
								"Sunshine selected.mp3", "HYP - Full Of Sunshine.mp3", "HYP - Full Of Sunshine"));
		
		
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
		
		
		addKeyListener(new KeyListener());
		
		
		// introMusic - Music 클래스에서 음악재생(Thread 작동)
		introMusic.start();
		
		
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
				Music buttonPressedMusic = new Music("buttonPressedMusic.mp3", false);
				buttonPressedMusic.start();
				enterMain();

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
		
		
		// leftButton - 게임화면 좌측 버튼
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
		
		
		// rightButton - 게임화면 우측 버튼
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
		
		
		// easyButton
		easyButton.setVisible(false);
		easyButton.setBounds(375, 580, 250, 67);
		easyButton.setBorderPainted(false);
		easyButton.setContentAreaFilled(false);
		easyButton.setFocusPainted(false);
		easyButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				easyButton.setIcon(easyButtonEnteredImage);
				easyButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				//Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				//buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				easyButton.setIcon(easyButtonBasicImage);
				easyButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				//Music buttonPressedMusic = new Music("buttonPressedMusic.mp3", false);
				//buttonPressedMusic.start();
				
				// easyButton Event
				gameStart(nowSelected, "Easy");
			}
		});
		add(easyButton);
		
		
		// hardButton
		hardButton.setVisible(false);
		hardButton.setBounds(655, 580, 250, 67);
		hardButton.setBorderPainted(false);
		hardButton.setContentAreaFilled(false);
		hardButton.setFocusPainted(false);
		hardButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				hardButton.setIcon(hardButtonEnteredImage);
				hardButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				//Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				//buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				hardButton.setIcon(hardButtonBasicImage);
				hardButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				//Music buttonPressedMusic = new Music("buttonPressedMusic.mp3", false);
				//buttonPressedMusic.start();
				
				// hardButton Event
				gameStart(nowSelected, "Hard");
			}
		});
		add(hardButton);
		
		
		// backButton
		backButton.setVisible(false);
		backButton.setBounds(20, 50, 60, 60);
		backButton.setBorderPainted(false);
		backButton.setContentAreaFilled(false);
		backButton.setFocusPainted(false);
		backButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				backButton.setIcon(backButtonEnteredImage);
				backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				//Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				//buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				backButton.setIcon(backButtonBasicImage);
				backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				//Music buttonPressedMusic = new Music("buttonPressedMusic.mp3", false);
				//buttonPressedMusic.start();
				
				// 메인화면으로 돌아가는 이벤트
				try {
					Thread.sleep(300);
				} catch(InterruptedException ex) {
					ex.printStackTrace();
				}
				backMain();
			}
		});
		add(backButton);
		
		
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
		
	} // End - init
	
	
	// paint
	public void paint(Graphics g) {
		screenImage	  = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		screenGraphic = screenImage.getGraphics();
		screenDraw((Graphics2D) screenGraphic);
		g.drawImage(screenImage, 0, 0, null);
		
	} // End - paint
	
	
	// screenDraw - 이미지 상시 로딩하기 위한 설정
	public void screenDraw(Graphics2D g) {
		
		g.drawImage(background, 0, 0, null);
		
		// 메인 화면 진입
		if(isMainScreen) {
			g.drawImage(selectedImage, 340, 140, null);
			g.drawImage(titleImage, 340, 70, null);
		}
		
		// 게임 화면 진입
		if(isGameScreen) {	
			game.screenDraw(g);
		}
		
		paintComponents(g);
		
		try {
			Thread.sleep(5);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
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
	
	
	// gameStart //
	public void gameStart(int nowSelected, String difficulty) {
		if(selectedMusic != null) {
			selectedMusic.close();
		}
		isMainScreen = false;
		leftButton.setVisible(false);
		rightButton.setVisible(false);
		easyButton.setVisible(false);
		hardButton.setVisible(false);
		
		background = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getGameImage())).getImage();
		
		backButton.setVisible(true);
		isGameScreen = true;	
		
		game = new Game(trackList.get(nowSelected).getTitleName(), difficulty, trackList.get(nowSelected).getGameMusic());
		game.start();
		
		// 키 이벤트 컴포넌트가 여럿일 경우 우선 입력받기 위한 설정
		setFocusable(true);
		requestFocus();
		
	} // End - gameStart
	
	
	// backMain //
	public void backMain() {
		isMainScreen = true;
		leftButton.setVisible(true);
		rightButton.setVisible(true);
		easyButton.setVisible(true);
		hardButton.setVisible(true);
		
		background = new ImageIcon(Main.class.getResource("../images/mainBackground.jpg")).getImage();
		
		backButton.setVisible(false);
		selectTrack(nowSelected);
		isGameScreen = false;
		
		game.close();
		
	} // End - backMain
	
	
	public void enterMain() {
		
		startButton.setVisible(false);
		quitButton.setVisible(false);
		
		background 	 = new ImageIcon(Main.class.getResource("../images/mainBackground.jpg")).getImage();
		isMainScreen = true;
		
		leftButton.setVisible(true);
		rightButton.setVisible(true);
		easyButton.setVisible(true);
		hardButton.setVisible(true);
		
		introMusic.close();
		
		selectTrack(0);
	}
	
	
} // End - DynamicBeat









