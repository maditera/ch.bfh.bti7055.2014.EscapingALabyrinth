package escapingalabyrinth;

import java.awt.Color;
import java.awt.Graphics2D;

import javax.swing.JComponent;

public class Tile extends JComponent {
	
	private TileType tileType;
	
	public Tile(TileType type) {
		this.tileType = type;		
	}
	
	public TileType getTileType() {
		return this.tileType;
	}
	
	// Paint tile as a rectangle according to their type
	public void paint(Graphics2D g, int size, int xpos, int ypos) {
		
		if (this.tileType == TileType.EMPTY) {
			g.setColor(Color.WHITE);
			g.fillRect(xpos, ypos, size, size);
		}
		
		if (this.tileType == TileType.WALL) {
			g.setColor(Color.BLACK);
			g.fillRect(xpos, ypos, size, size);
		}
		
		if (this.tileType == TileType.PATH) {
			g.setColor(Color.GREEN);
			g.fillRect(xpos, ypos, size, size);
		}
	}

}
