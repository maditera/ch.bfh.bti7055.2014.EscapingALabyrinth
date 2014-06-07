package escapingalabyrinth;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class LabyrinthViewer implements ActionListener {

	private Labyrinth labyrinth;
	private int labyrinthWidth;
	private int labyrinthHeight;

	private JFrame frame;
	private JPanel controlPanel;
	private JPanel drawPanel;
	private JLabel lblStartPosX;
	private JLabel lblStartPosY;
	private JLabel lblStatus;
	private JComboBox<Integer> cbStartPosX;
	private JComboBox<Integer> cbStartPosY;
	private JButton btnSolve;
	private JButton btnReset;

	public static void main(String[] args) throws IOException {
		String fileName = args[0];
		new LabyrinthViewer(fileName);
	}

	public LabyrinthViewer(String fileName) throws IOException {

		// Load labyrinth definition file with name given in first parameter
		LabyrinthFileReader fileReader = new LabyrinthFileReader();
		fileReader.readFile(fileName);

		// Get definitions from file reader
		char[][] structure = fileReader.getLabyrinthStructure();
		this.labyrinthWidth = fileReader.getLabyrinthWidth();
		this.labyrinthHeight = fileReader.getLabyrinthHeigth();

		// Print definitions in console
		System.out.println("Labyrinth width: " + labyrinthWidth);
		System.out.println("Labyrinth height: " + labyrinthHeight);

		for (int row = 0; row < structure.length; row++) {
			for (int col = 0; col < structure[row].length; col++) {
				System.out.print(structure[row][col]);
			}
			System.out.print("\n");
		}

		// Create labyrinth object
		labyrinth = new Labyrinth(labyrinthWidth, labyrinthHeight, structure);

		// Display GUI
		this.displayGUI();

	}

	public void displayGUI() {

		// Set frame and panels
		this.frame = new JFrame();
		this.frame.setTitle("Escaping a Labyrinth");
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.frame.setResizable(false);

		BoxLayout boxLayout = new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS);
		this.frame.setLayout(boxLayout);

		this.controlPanel = new JPanel();
		this.controlPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

		this.drawPanel = new JPanel();
		this.frame.add(controlPanel);
		this.frame.add(drawPanel);

		// Create control components
		this.lblStartPosX = new JLabel("X-Pos");
		this.cbStartPosX = new JComboBox<Integer>();
		this.lblStartPosY = new JLabel("Y-Pos");
		this.cbStartPosY = new JComboBox<Integer>();
		this.btnSolve = new JButton("Solve");
		this.btnReset = new JButton("Reset");
		this.lblStatus = new JLabel("Choose a start position!");

		// Add action listener to buttons
		this.btnSolve.addActionListener(this);
		this.btnReset.addActionListener(this);
		
		// Fill the ComboBoxes with possible options
		for (int i = 0; i < this.labyrinthWidth; i++) {
			cbStartPosX.addItem(new Integer(i));
		}
		for (int i = 0; i < this.labyrinthHeight; i++) {
			cbStartPosY.addItem(new Integer(i));
		}

		// Add control components
		this.controlPanel.add(this.lblStartPosX);
		this.controlPanel.add(this.cbStartPosX);
		this.controlPanel.add(this.lblStartPosY);
		this.controlPanel.add(this.cbStartPosY);
		this.controlPanel.add(this.btnSolve);
		this.controlPanel.add(this.btnReset);
		this.controlPanel.add(this.lblStatus);

		// Add labyrinth component
		this.drawPanel.add(this.labyrinth);

		// Finish up
		this.frame.pack();
		this.frame.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		final JButton source = (JButton) e.getSource();

		// If solve button has been pressed
		if (source.equals(this.btnSolve)) {

			System.out.println("Solving labyrinth...");

			// Get values from ComboBoxes
			int startX = (int) this.cbStartPosX.getSelectedItem();
			int startY = (int) this.cbStartPosY.getSelectedItem();

			System.out.println("Start-X: " + startX);
			System.out.println("Start-Y: " + startY);

			// Create solver instance and solve labyrinth
			LabyrinthSolver solver = new LabyrinthSolver(this.labyrinth);
			boolean solvable = solver.solve(startX, startY);

			System.out.println("Solvable: " + solvable);

			if (solvable) {

				// Set status label text
				this.lblStatus.setText("Could solve labyrinth!");

				// Create new labyrinth object based on solved structure
				Labyrinth solvedLabyrinth = new Labyrinth(this.labyrinthWidth, this.labyrinthHeight, solver.getSolvedStructure());

				// Add solved labyrinth;
				this.drawPanel.add(solvedLabyrinth);
				this.drawPanel.repaint();

			} else {

				// Set status label text
				this.lblStatus.setText("Cannot solve labyrinth!");
			}

		}
		
		// If reset button has been pressed
		if (source.equals(this.btnReset)) {
			
			System.out.println("Reset...");

			
//			// Set status label text
//			this.lblStatus.setText("Choose a start position!");
//			
//			// Add original labyrinth;
//			this.drawPanel.add(this.labyrinth);
//			this.drawPanel.repaint();
		}
	}
}