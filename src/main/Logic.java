package main;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

import coordinates_comparators.*;
import elements_3D.*;

public class Logic {

	public static final int SIGNS_IN_A_ROW = 5;
	private HashMap<Coordinates, Sign> usedPos;
	private ArrayList<Coordinates> keyRowsList = new ArrayList<>();

	public Logic(HashMap<Coordinates, Sign> usedPos) {
		this.usedPos = usedPos;
	}

	public HashMap<Coordinates, Sign> getUsedPos() {
		return usedPos;
	}

	public void putToUsedPos(Coordinates key, Sign value) {
		usedPos.put(key, value);
		keyRowsList.add(key);
	}

	public boolean areSignsNeighbours(Coordinates currentCoord,Coordinates lastCoord,Comparator<Coordinates> comparator){
		return comparator instanceof HorizontalCoordinatesCompatator ?
				(currentCoord.getX() == lastCoord.getX() + 1) && (currentCoord.getY() == lastCoord.getY()) :
				comparator instanceof VerticalCoordinatesComparator ?
				(currentCoord.getX() == lastCoord.getX()) && (currentCoord.getY() == lastCoord.getY() + 1) :
				comparator instanceof TopLeftBottomRightDiagComparator ?
				(currentCoord.getX() == lastCoord.getX() + 1) && (currentCoord.getY() == lastCoord.getY() + 1) :
				comparator instanceof BottomLeftTopRightDiagComparator ?
				(currentCoord.getX() == lastCoord.getX() + 1) && (currentCoord.getY() == lastCoord.getY() - 1) : null;
	}

	public boolean anybodyWin(Comparator<Coordinates> coordinatesComparator) {
		if (usedPos.keySet().size() < SIGNS_IN_A_ROW)
			return false;

		keyRowsList.sort(coordinatesComparator);

		int counter = 0;
		Coordinates currentCoord;
		Coordinates lastCoord = keyRowsList.get(0);
		boolean sameSignX;
		boolean sameSignO;
		Sign lastSign = usedPos.get(lastCoord);

		for (int i = 1; i < keyRowsList.size(); i++) {
			currentCoord = keyRowsList.get(i);
			sameSignX = (lastSign instanceof SignX) && (usedPos.get(currentCoord) instanceof SignX);
			sameSignO = (lastSign instanceof SignO) && (usedPos.get(currentCoord) instanceof SignO);

			if (areSignsNeighbours(currentCoord, lastCoord, coordinatesComparator) && (sameSignX || sameSignO)) {
				counter += 1;
			} else
				counter = 0;
			if (counter == (SIGNS_IN_A_ROW - 1))
				return true;

			lastCoord = keyRowsList.get(i);
			lastSign = usedPos.get(lastCoord);
		}
		return false;
	}
}
