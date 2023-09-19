package beat_14;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

// Note //
public class Note extends Thread {
	
	private Image 	noteBasicImage = new ImageIcon(Main.class.getResource("../images/noteBasic.png")).getImage();
	private int 	x, y = 580 - (1000 / Main.SLEEP_TIME * Main.NOTE_SPEED) * Main.REACH_TIME;
	private String 	noteType;
	
	
	// 기본 생성자에서 초기화
	public Note(String noteType) {
		if("S".equals(noteType)) {
			x = 228;
		} else if("D".equals(noteType)) {
			x = 332;
		} else if("F".equals(noteType)) {
			x = 436;
		} else if("Space".equals(noteType)) {
			x = 540;
		} else if("J".equals(noteType)) {
			x = 744;
		} else if("K".equals(noteType)) {
			x = 848;
		} else if("L".equals(noteType)) {
			x = 952;
		}
		
		this.noteType = noteType;
	}
	
	// screenDraw
	public void screenDraw(Graphics2D g) {
		if(!"Space".equals(noteType)) {
			g.drawImage(noteBasicImage, x, y, null);
			
		} else {
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
