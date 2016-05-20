package coordinates_comparators;

import java.util.Comparator;

public class VerticalCoordinatesComparator implements Comparator<Coordinates> {

	@Override
	public int compare(Coordinates o1, Coordinates o2) {
		return o1.getX() < o2.getX() ? -1
				: o1.getX() > o2.getX() ? 1 : o1.getY() < o2.getY() ? -1 : o1.getY() > o2.getY() ? 1 : 0;
	}
	

}
