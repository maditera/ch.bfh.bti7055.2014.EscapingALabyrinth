package escapingalabyrinth;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;


public class Labyrinth extends JComponent {
	
	private int width;
	private int height;
	private Tile[][] structure;
	
	private final int TILE_SIZE = 40;
	private final int TILE_SPACE = 2;
	
	
	// Constructor for building labyrinth on a char based structure
	public Labyrinth(int width, int height, char[][] charStructure) {
		
		this.width = width;
		this.height = height;
		this.structure = new Tile[this.width][this.height];
		
		// Set component size
		Dimension d = new Dimension(this.width*(this.TILE_SIZE+this.TILE_SPACE), 	this.height*(this.TILE_SIZE+this.TILE_SPACE));
		this.setPreferredSize(d);
		
		
		// Fetch char structure array and set tile array
		for (int row = 0; row < charStructure.length; row++) {
			for (int col = 0; col < charStructure[row].length; col++) {
				
				// Add empty tiles
				if (charStructure[row][col] == ' ') {
					this.structure[row][col] = new Tile(TileType.EMPTY);
				} 
				
				// Add wall tiles
				if (charStructure[row][col] == '*') {
					this.structure[row][col] = new Tile(TileType.WALL);
				}
				
			}
		}
		
	}
	
	// Constructor for building labyrinth on a tile based structure
	public Labyrinth(int width, int height, Tile[][] tileStructure) {
		this.width = width;
		this.height = height;
		this.structure = tileStructure;
	}
	
	
	public Tile[][] getLabyrinthStructure() {
		return this.structure;
	}
	
	public int getLabyrinthWidth() {
		return this.width;
	}
	
	public int getLabyrinthHeight() {
		return this.height;
	}
	
	public void paintComponent(Graphics g) {

		// Cast graphics object
		Graphics2D g2 = (Graphics2D) g;

		// Paint tile objects
		for (int row = 0; row < this.structure.length; row++) {
			for (int col = 0; col < this.structure[row].length; col++) {
				structure[row][col].paint(g2, 	this.TILE_SIZE, 	col*(this.TILE_SIZE+this.TILE_SPACE),	row*(this.TILE_SIZE+TILE_SPACE));
			}
		}

	}
	
}
