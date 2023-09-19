package beat_16;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

// Note //
public class Note extends Thread {
	
	private Image 	noteBasicImage 	= new ImageIcon(Main.class.getResource("../images/noteBasic.png")).getImage();
	private int 	x; 
	private int 	y 				= 580 - (1000 / Main.SLEEP_TIME * Main.NOTE_SPEED) * Main.REACH_TIME;
	private String 	noteType;
	private boolean proceeded		= true;
	
	
	// �⺻ �����ڿ��� �ʱ�ȭ - ��Ʈ ��ġ ����
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
	
	
	// getNoteType
	public String getNoteType() {
		return noteType;
	}
	
	
	// ��Ʈ�� ������� ����
	public boolean isProceeded() {
		return proceeded;
	}
	
	// �Է� â���� Ű�� ������ �� ��Ʈ ó��
	public void close() {
		proceeded = false;
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
		
		if(y > 620) {
			System.out.println("Miss");
			close();
		}
	}
	
	// run
	@Override
	public void run() {
		try {
			while(true) {
				drop();
				
				if(proceeded) {
					Thread.sleep(Main.SLEEP_TIME);
				} else {
					interrupt();
					break;
				}
			}
			
		} catch(Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	
	// ����
	public String judge() {
		if(y >= 613) {
			System.out.println("Late");
			close();
			return "Late";
			
		} else if(y >= 600) {
			System.out.println("Good");
			close();
			return "Good";
			
		} else if(y >= 587) {
			System.out.println("Great");
			close();
			return "Great";
			
		} else if(y >= 573) {
			System.out.println("Perfect");
			close();
			return "Perfect";
			
		} else if(y >= 565) {
			System.out.println("Great");
			close();
			return "Great";
			
		} else if(y >= 550) {
			System.out.println("Good");
			close();
			return "Good";
			
		} else if(y >= 535) {
			System.out.println("Early");
			close();
			return "Early";
			
		}
		return "None";
	}
	
	public int getY() {
		return y;
	}
	
	
	
} // End - Note