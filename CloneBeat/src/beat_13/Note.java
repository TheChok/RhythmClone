package beat_13;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

// Note //
public class Note extends Thread {
	
	private Image 	noteBasicImage = new ImageIcon(Main.class.getResource("../images/noteBasic.png")).getImage();
	private int 	x, y = 580 - 1000 / Main.SLEEP_TIME * Main.NOTE_SPEED;
	private String 	noteType;
	
	
	// 기본 생성자에서 초기화
	public Note(int x, String noteType) {
		this.x 		  = x;
		this.noteType = noteType;
	}
	
	// screenDraw
	public void screenDraw(Graphics2D g) {
		if("short".equals(noteType)) {
			g.drawImage(noteBasicImage, x, y, null);
			
		} else if("long".equals(noteType)) {
			g.drawImage(noteBasicImage, x, y, null);
			g.drawImage(noteBasicImage, x+100, y, null);
		}
	}
	
	// drop
	public void drop() {
		y += Main.NOTE_SPEED;
	}
	
	// run
	@Override
	public void run() {
		try {
			while(true) {
				drop();
				Thread.sleep(Main.SLEEP_TIME);
			}
			
		} catch(Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	
	
} // End - Note
