package beat_9;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javazoom.jl.player.Player;

// Music //
public class Music extends Thread {
	
	private Player 				player;
	private boolean 			isLoop;
	private File 				file;
	private FileInputStream 	fis;
	private BufferedInputStream bis;
	
	// Music
	public Music(String name, boolean isLoop) {
		try {
			
			this.isLoop = isLoop;
			file 	= new File(Main.class.getResource("../music/" + name).toURI());
			fis	 	= new FileInputStream(file);
			bis 	= new BufferedInputStream(fis);
			player	= new Player(bis);
			
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	// getTime
	public int getTime() {
		if(player == null) {
			return 0;
		}
		
		return player.getPosition();
	}
	
	// close
	public void close() {
		isLoop = false;
		player.close();
		this.interrupt();
	}

	// run
	@Override
	public void run() {
		try {
			do {
				player.play();
				fis	 	= new FileInputStream(file);
				bis 	= new BufferedInputStream(fis);
				player	= new Player(bis);
				
			} while(isLoop);
			
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

	
	
	
	
	
	
	
	
} // End - Music
