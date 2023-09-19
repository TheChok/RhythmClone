package beat_14;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.ImageIcon;

// Game //
public class Game extends Thread {
	
	// ���ӿ��� ����� �̹���
	private Image		gameInfoImage			= new ImageIcon(Main.class.getResource("../images/gameInfo.png")).getImage();
	private Image		judgementLineImage		= new ImageIcon(Main.class.getResource("../images/judgementLine.png")).getImage();
	private Image		noteRouteLineImage		= new ImageIcon(Main.class.getResource("../images/noteRouteLine.png")).getImage();
	
	// ��Ʈ �̹���(A,S,D, SPACE, J,K,L)
	private Image		noteRouteSImage			= new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image		noteRouteDImage			= new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image		noteRouteFImage			= new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image		noteRouteSpace1Image	= new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image		noteRouteSpace2Image	= new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image		noteRouteJImage			= new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image		noteRouteKImage			= new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image		noteRouteLImage			= new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	
	// ȭ�鿡�� �������� ��Ʈ���� ���� ����Ʈ
	ArrayList<Note> noteList = new ArrayList<Note>();

	
	private String		titleName;
	private	String		difficulty;
	private String		musicTitle;
	private Music		gameMusic;
	
	public Game(String titleName, String difficulty, String musicTitle) {
		this.titleName  = titleName;
		this.difficulty = difficulty;
		this.musicTitle = musicTitle;
		gameMusic = new Music(this.musicTitle, false);
		
	}
	
	
	// screenDraw //
	public void screenDraw(Graphics2D g) {

		// ��Ʈ ����
		g.drawImage(noteRouteLineImage, 224, 30, null);
		g.drawImage(noteRouteSImage, 	228, 30, null);		// S
		g.drawImage(noteRouteLineImage, 328, 30, null);
		g.drawImage(noteRouteDImage, 	332, 30, null);		// D
		g.drawImage(noteRouteLineImage, 432, 30, null);
		g.drawImage(noteRouteFImage, 	436, 30, null);		// F
		g.drawImage(noteRouteLineImage, 536, 30, null);
		
		g.drawImage(noteRouteSpace1Image, 540, 30, null);	// Space
		g.drawImage(noteRouteSpace2Image, 640, 30, null);	// Space
		
		g.drawImage(noteRouteLineImage, 740, 30, null);
		g.drawImage(noteRouteJImage, 	744, 30, null);		// J
		g.drawImage(noteRouteLineImage, 844, 30, null);
		g.drawImage(noteRouteKImage, 	848, 30, null);		// K
		g.drawImage(noteRouteLineImage, 948, 30, null);
		g.drawImage(noteRouteLImage, 	952, 30, null);		// L
		g.drawImage(noteRouteLineImage, 1052, 30, null);
		
		
		for(int i = 0; i < noteList.size(); i++) {
			Note note = noteList.get(i);
			note.screenDraw(g);
		}
		
		
		// ���� ���� 
		g.drawImage(gameInfoImage, 0, 660, null);
		g.drawImage(judgementLineImage, 0, 580, null);
		
		// Ÿ��Ʋ ǥ��
		g.setColor(Color.white);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD, 30));
		g.drawString(titleName, 20, 702);
		g.drawString(difficulty, 1190, 702);
		
		// Ű�е� ǥ��
		g.setFont(new Font("Arial", Font.PLAIN, 26));
		g.setColor(Color.DARK_GRAY);
		g.drawString("S", 270, 609);
		g.drawString("D", 374, 609);
		g.drawString("F", 478, 609);
		
		g.drawString("Space Bar", 580, 609);
		
		g.drawString("J", 784, 609);
		g.drawString("K", 889, 609);
		g.drawString("L", 993, 609);
		
		// ���� ǥ��
		g.setColor(Color.LIGHT_GRAY);
		g.setFont(new Font("Elephant", Font.BOLD, 30));
		g.drawString("000000", 565, 702);
		
		
	} // End - screenDraw
	
	
	// Ű �̺�Ʈ //
	// S
	public void pressS() {
		new Music("drum1.mp3", false).start();
		noteRouteSImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
	}
	public void releaseS() {
		noteRouteSImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	}
	
	// D
	public void pressD() {
		new Music("drum1.mp3", false).start();
		noteRouteDImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
	}
	public void releaseD() {
		noteRouteDImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	}
	
	// F
	public void pressF() {
		new Music("drum1.mp3", false).start();
		noteRouteFImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
	}
	public void releaseF() {
		noteRouteFImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	}

	// Space
	public void pressSpace() {
		new Music("drum2.mp3", false).start();
		noteRouteSpace1Image = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		noteRouteSpace2Image = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
	}
	public void releaseSpace() {
		noteRouteSpace1Image = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
		noteRouteSpace2Image = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	}
	
	// J
	public void pressJ() {
		new Music("drum1.mp3", false).start();
		noteRouteJImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
	}
	public void releaseJ() {
		noteRouteJImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	}
	
	// K
	public void pressK() {
		new Music("drum1.mp3", false).start();
		noteRouteKImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
	}
	public void releaseK() {
		noteRouteKImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	}
	
	// L
	public void pressL() {
		new Music("drum1.mp3", false).start();
		noteRouteLImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
	}
	public void releaseL() {
		noteRouteLImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	}
	// End - keyEvent
	
	
	// run //
	@Override
	public void run() {
		dropNotes();
	} // End - run
	
	
	public void close() {
		gameMusic.close();
		this.interrupt();
	}
	
	
	// dropNotes
	public void dropNotes() {
		Beat[] beats = null;
		
		if("HYP - Picnic".equals(titleName)) {
			int startTime = 4460 - Main.REACH_TIME * 1000;
			int gap = 125;
			
			beats = new Beat[] {
				new Beat(startTime, "S"),
				new Beat(startTime + gap * 2, "D"),
				new Beat(startTime + gap * 4, "S"),
				new Beat(startTime + gap * 6, "D"),
				new Beat(startTime + gap * 8, "S"),
				new Beat(startTime + gap * 10, "D"),
				new Beat(startTime + gap * 12, "S"),
				new Beat(startTime + gap * 14, "D"),
				new Beat(startTime + gap * 18, "J"),
				new Beat(startTime + gap * 20, "K"),
				new Beat(startTime + gap * 22, "J"),
				new Beat(startTime + gap * 24, "K"),
				new Beat(startTime + gap * 26, "J"),
				new Beat(startTime + gap * 28, "K"),
				new Beat(startTime + gap * 30, "J"),
				new Beat(startTime + gap * 32, "K"),
				new Beat(startTime + gap * 36, "S"),
				new Beat(startTime + gap * 38, "D"),
				new Beat(startTime + gap * 40, "S"),
				new Beat(startTime + gap * 42, "D"),
				new Beat(startTime + gap * 44, "S"),
				new Beat(startTime + gap * 46, "D"),
				new Beat(startTime + gap * 48, "J"),
				new Beat(startTime + gap * 49, "K"),
				new Beat(startTime + gap * 50, "L"),
				new Beat(startTime + gap * 52, "F"),
				new Beat(startTime + gap * 52, "Space"),
				new Beat(startTime + gap * 52, "J"),
			};
			
		} else if("HYP - Full Of Sunshine".equals(titleName)) {
			int startTime = 1000;
			beats = new Beat[] {
				new Beat(startTime, "Space"),	
			};
			
		}
		
		int i = 0;
		
		gameMusic.start();
		
		while(i < beats.length && !isInterrupted()) {
			boolean dropped = false;
			
			if(beats[i].getTime() <= gameMusic.getTime()) {
				Note note = new Note(beats[i].getNoteName());
				note.start();
				noteList.add(note);
				i++;
				dropped = true;
			}
			
			if(!dropped) {
				try {
					Thread.sleep(5);
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		
	}
	
	
} // End - Game