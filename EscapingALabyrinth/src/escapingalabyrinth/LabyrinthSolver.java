package escapingalabyrinth;

public class LabyrinthSolver {
	
	private Tile[][] structure;
	private Tile[][] solved;
	private boolean[][] wasHere;
	private int width;
	private int height;
		
	public LabyrinthSolver(Labyrinth labyrinth) {
		
		// Get labyrinth structure
		this.structure = labyrinth.getLabyrinthStructure();
		this.solved = labyrinth.getLabyrinthStructure();
		this.width = labyrinth.getLabyrinthWidth();
		this.height = labyrinth.getLabyrinthHeight();
		
		
		// Initialize boolean array and set to default values
		this.wasHere = new boolean[this.width][this.height];

		for (int row = 0; row < this.structure.length; row++) {
	        for (int col = 0; col < this.structure[row].length; col++) {
	            this.wasHere[row][col] = false;
	        }
		}
	}

	public boolean solve(int startX, int startY) {
		return this.recursiveSolve(startX, startY);
	}
	
	private boolean recursiveSolve(int x, int y) {
		
		// If you are at an exit
		if ((x == 0 || x == this.width - 1 || y == 0 || y == this.height - 1) && this.structure[y][x].getTileType() == TileType.EMPTY) {
			this.solved[y][x] = new Tile(TileType.PATH);
			return true;
		}
		
		// If you are on a wall 
		if (this.structure[y][x].getTileType() == TileType.WALL) {
			return false;
		}
		
		// If you already were here
		if (this.wasHere[y][x]) {
			return false;
		}
	
		// Set wasHere array
		this.wasHere[y][x] = true;
		
		// Recall method one field to the left as long as not reached left side
		if (x != 0) {
			if (recursiveSolve(x-1, y)) {
				this.solved[y][x] = new Tile(TileType.PATH);
				return true;
			}
		}
		
		// Recall method one field to the right as long as not reached right side
		if (x != this.width - 1) {
			if (recursiveSolve(x+1, y)) {
				this.solved[y][x] = new Tile(TileType.PATH);
				return true;
			}
		}
		
		// Recall method one field up as long as not reached the top side
		if (y != 0) {
			if (recursiveSolve(x, y-1)) {
				this.solved[y][x] = new Tile(TileType.PATH);
				return true;
			}
		}
		
		// Recall method one field down as long as not reached the bottom side
		if (y != this.height - 1) {
			if (recursiveSolve(x, y+1)) {
				this.solved[y][x] = new Tile(TileType.PATH);
				return true;
			}
		}
		
		return false;
	}
	
	public Tile[][] getSolvedStructure() {
		return this.solved;
	}

}
