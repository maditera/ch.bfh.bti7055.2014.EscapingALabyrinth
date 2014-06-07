package escapingalabyrinth;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LabyrinthFileReader {

	private int labyrinthWidth;
	private int labyrinthHeight;
	private char[][] labyrintStructure;

	public void readFile(String fileName) throws IOException {

		int lineCounter = 0;
		String line = null;
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;

		try {
			// FileReader reads text files in the default encoding
			fileReader = new FileReader(fileName);

			// Always wrap FileReader in BufferedReader
			bufferedReader = new BufferedReader(fileReader);

			// Reference one line at i time
			while ((line = bufferedReader.readLine()) != null) {
				lineCounter++;

				// Interpret first line as labyrinth dimensions
				if (lineCounter == 1) {
					this.setLabyrinthDimensions(line);
				} 
				
				// Interpret all following lines as labyrinth structure
				else {
					this.setLabyrinthStructure(line, lineCounter - 2);
				}
			}

		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + fileName + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + fileName + "'");
		} finally {
			if (fileReader != null) {
				bufferedReader.close();
			}
		}
	}

	public int getLabyrinthWidth() {
		return this.labyrinthWidth;
	}

	public int getLabyrinthHeigth() {
		return this.labyrinthHeight;
	}

	public char[][] getLabyrinthStructure() {
		return this.labyrintStructure;
	}

	private void setLabyrinthDimensions(String dimensions) {
		
		// Split dimension numbers
		String[] parts = dimensions.split(",");
		
		// Parse dimensions to Integers
		this.labyrinthWidth = Integer.parseInt(parts[0]);
		this.labyrinthHeight = Integer.parseInt(parts[1]);
		
		// Initialize array for the labyrinth's structure depending on it's size
		this.labyrintStructure = new char[this.labyrinthHeight][this.labyrinthWidth];
	}

	private void setLabyrinthStructure(String row, int rowNumber) {
		
		// Split content of a line
		String[] parts = row.split(",");
		
		// Add structure definition of each row to structure array
		for (int i = 0; i < parts.length; i++) {
			this.labyrintStructure[rowNumber][i] = parts[i].charAt(0);
		}
	}
}
