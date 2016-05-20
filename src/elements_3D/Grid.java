package elements_3D;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;

public class Grid {

	private int oneGridSize;
	private int gridNumber;

	private int minX;
	private int maxX;
	private int minY;
	private int maxY;

	private List<List<Box>> gridBoxes = new ArrayList<>();

	public Grid(int gridNumber, int oneGridSize) {
		this.gridNumber = gridNumber;
		this.oneGridSize = oneGridSize;
		buildGridBoxes();
		minX = calculateCoordinate(0);
		maxX = calculateCoordinate(gridNumber-1);
		minY = calculateCoordinate(0);
		maxY = calculateCoordinate(gridNumber-1);
	}

	public int getOneGridSize() {
		return oneGridSize;
	}

	public void setOneGridSize(int oneGridSize) {
		this.oneGridSize = oneGridSize;
	}

	public int getGridNumber() {
		return gridNumber;
	}

	public void setGridNumber(int gridNumber) {
		this.gridNumber = gridNumber;
	}

	public int getMinX() {
		return minX;
	}

	public int getMaxX() {
		return maxX;
	}

	public int getMinY() {
		return minY;
	}

	public int getMaxY() {
		return maxY;
	}

	public List<List<Box>> getGridBoxes() {
		return gridBoxes;
	}

	public void setGridBoxes(List<List<Box>> gridBoxes) {
		this.gridBoxes = gridBoxes;
	}

	private int calculateCoordinate(int index) {
		int coord = (int) (Math.rint(-(gridNumber + 1) * oneGridSize / 2) + oneGridSize * (index + 1));
		if (gridNumber % 2 == 0) {
			coord += oneGridSize / 2;
		}
		return coord;
	};
	
	private void buildGridBoxes() {
		Box oneGrid;
		List<Box> rowBoxes;
		
		for (int i = 0; i < gridNumber; i++) {

			rowBoxes = new ArrayList<>();

			for (int j = 0; j < gridNumber; j++) {

				oneGrid = new Box(oneGridSize, oneGridSize, 0.5);
				oneGrid.setMaterial(new PhongMaterial(Color.BISQUE));

				oneGrid.setTranslateX(calculateCoordinate(j));
				oneGrid.setTranslateY(calculateCoordinate(i));

				rowBoxes.add(oneGrid);
			}
			gridBoxes.add(rowBoxes);
		}
	}
}
