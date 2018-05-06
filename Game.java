
 import javax.swing.JFrame;

public abstract class Game {
	
	/**
	 * This is where the game is :^)
	 */
	static JFrame window = new JFrame("Game of Life");
	static Grid grid = new Grid(500);
	static private int[][] drawArray = new int[grid.getCellsPerCol()][grid.getCellsPerRow()]; 
	static private int[][] valueArray = new int[grid.getCellsPerCol()][grid.getCellsPerRow()];
	static private boolean isRunning = true;
	
	
	public static void main(String[] args) {
		setDrawArray();
		window.add(grid);
		window.pack();
		window.setVisible(true);
		window.setResizable(false);
		
		//main loop
		double millisecondWaitTime = 100;
		while (isRunning) {
			double start = System.nanoTime()/1000000;
			updateValueArray();
			updateDrawArray();
			grid.repaint();
			try {
				Thread.sleep((int) (start + millisecondWaitTime - System.nanoTime() / 1000000));
			} 
			catch (IllegalArgumentException e) {
				
			} 
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public static int[][] getDrawArray() {
		return drawArray;
	}
	
	//for initialization
	static void setDrawArray() {
		for (int y = 0; y < drawArray.length; y++) {
			for (int x = 0; x < drawArray[0].length; x++) {
				drawArray[y][x] = (int)Math.floor(Math.random() * 2);
			}
		}
	}

	//for updating
	static void updateDrawArray() {
		for (int y = 0; y < drawArray.length; y++) {
			for (int x = 0; x < drawArray[0].length; x++) {
				if (valueArray[y][x] > 1 && valueArray[y][x] < 4 || valueArray[y][x] == -1) {
					drawArray[y][x] = 1;
				}
				else {
					drawArray[y][x] = 0;
				}
			}
		}
	}
	
	static void updateValueArray() {
		int count;
		for (int y = 0; y < drawArray.length; y++) {
			for (int x = 0; x < drawArray[0].length; x++) {
				count = 0;
				for (int i = 0; i < 3; i++) {//check rows above and below
					try { //check row above
						count += drawArray[y - 1][x - 1 + i];
					} catch(ArrayIndexOutOfBoundsException e) {}
					try { //check row below
						count += drawArray[y + 1][x - 1 + i];
					} catch(ArrayIndexOutOfBoundsException e) {}
				}
				try { // check left cell
					count += drawArray[y][x - 1];
				} catch(ArrayIndexOutOfBoundsException e) {}
				try { // check right cell
					count += drawArray[y][x + 1];
				} catch(ArrayIndexOutOfBoundsException e) {}
				if (count == 3 && drawArray[y][x] == 0) {
					count = -1;
				}
				else if (drawArray[y][x] == 0) {
					count = 0;
				}
				valueArray[y][x] = count;
			}
		}
	}
}	
