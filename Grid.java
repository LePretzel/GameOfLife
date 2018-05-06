
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

class Grid extends JPanel {

	/**
	* visual representation of an array of cells
	*/
		
	private static final long serialVersionUID = 1L;
	private int height_;
	private int width_;
	private int cellWidth;
	private int cellHeight;
	private int cellsPerRow;
	private int cellsPerCol;
	
	//constructors
	public Grid(int width, int height) {
		width_ = width;
		height_ = height;
		cellWidth = width/50;
		cellHeight = height/50;
		cellsPerRow = height/10;
		cellsPerCol = height/10;
	}
	public Grid(int size) {
		width_ = size;
		height_ = size;
		cellHeight = size/50;
		cellWidth = size/50;
		cellsPerRow = size/10;
		cellsPerCol = size/10;
	}
	
	// accessors
	public int getCellsPerRow() {
		return cellsPerRow;
	}
	public int getCellsPerCol() {
		return cellsPerCol;
	}
	
	//draws a cell for each 1 in the 2d input array
	void drawGrid(Graphics g, int[][] arr) {
		for (int y = 0; y < cellsPerCol; y++) {
			for (int x = 0; x < cellsPerRow; x++) {
				if (arr[y][x] == 1) {
					g.fillRect(x * cellWidth, y * cellHeight, cellWidth, cellHeight);
				}
			}
		}
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(width_,height_);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.black);
		drawGrid(g, Game.getDrawArray());
	}
}

