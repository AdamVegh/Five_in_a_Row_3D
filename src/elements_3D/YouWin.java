package elements_3D;

import java.util.ArrayList;
import java.util.List;

import coordinates_comparators.Coordinates;
import javafx.scene.paint.Color;
import javafx.scene.shape.DrawMode;
import main.Main;

public class YouWin {

	private Sign sign;
	private List<Sign> youWinSigns;
	
	public YouWin(Sign sign, List<Sign> youWinSigns) {
		this.sign = sign;
		this.youWinSigns = youWinSigns;
		buildText();
	}

	private void buildText() {
		youWinSigns.addAll(verticalSigns(1, new Coordinates(-1, -7)));
		youWinSigns.addAll(verticalSigns(1, new Coordinates(0, -7)));
		youWinSigns.addAll(verticalSigns(1, new Coordinates(-1, -2)));
		youWinSigns.addAll(verticalSigns(1, new Coordinates(0, -2)));
		youWinSigns.addAll(verticalSigns(1, new Coordinates(5, -2)));
		youWinSigns.addAll(verticalSigns(1, new Coordinates(6, -2)));
		youWinSigns.addAll(verticalSigns(1, new Coordinates(-6, 6)));
		youWinSigns.addAll(verticalSigns(1, new Coordinates(-4, 6)));
		youWinSigns.addAll(verticalSigns(1, new Coordinates(0, 1)));
		youWinSigns.addAll(verticalSigns(1, new Coordinates(1, 1)));
		youWinSigns.addAll(verticalSigns(1, new Coordinates(0, 6)));
		youWinSigns.addAll(verticalSigns(1, new Coordinates(1, 6)));
		
		youWinSigns.addAll(verticalSigns(2, new Coordinates(5, 2)));
		youWinSigns.addAll(verticalSigns(2, new Coordinates(6, 4)));
		
		youWinSigns.addAll(verticalSigns(3, new Coordinates(-7, -7)));
		youWinSigns.addAll(verticalSigns(3, new Coordinates(-5, -7)));
		youWinSigns.addAll(verticalSigns(3, new Coordinates(-6, -4)));
		youWinSigns.addAll(verticalSigns(3, new Coordinates(-5, 3)));
		
		youWinSigns.addAll(verticalSigns(4, new Coordinates(-2, -6)));
		youWinSigns.addAll(verticalSigns(4, new Coordinates(1, -6)));
		youWinSigns.addAll(verticalSigns(4, new Coordinates(-1, 2)));
		youWinSigns.addAll(verticalSigns(4, new Coordinates(2, 2)));
		
		youWinSigns.addAll(verticalSigns(5, new Coordinates(4, -7)));
		youWinSigns.addAll(verticalSigns(5, new Coordinates(7, -7)));
		youWinSigns.addAll(verticalSigns(5, new Coordinates(-7, 1)));
		youWinSigns.addAll(verticalSigns(5, new Coordinates(-3, 1)));
		
		youWinSigns.addAll(verticalSigns(6, new Coordinates(4, 1)));
		youWinSigns.addAll(verticalSigns(6, new Coordinates(7, 1)));
	}
	
	private List<Sign> verticalSigns(int n, Coordinates coord) {
		List<Sign> listSign = new ArrayList<>();
		if (sign instanceof SignX) {
			SignX signX;
			for (int i = 0; i < n; i++) {
				signX = new SignX(0.8*Main.oneGridSize, 0.8*Main.oneGridSize, 0.8*Main.oneGridSize, Color.BLUE, DrawMode.FILL);
				signX.setTranslateX(coord.getX());
				signX.setTranslateY(coord.getY()+i);
				listSign.add(signX);
			}
		}
		else {
			SignO signO;
			for (int i = 0; i < n; i++) {
				signO = new SignO(0.5*Main.oneGridSize, Color.RED, DrawMode.FILL);
				signO.setTranslateX(coord.getX());
				signO.setTranslateY(coord.getY()+i);
				listSign.add(signO);
			}
		}
		return listSign;
	}
	
	public Sign getSign() {
		return sign;
	}

	public void setSign(Sign sign) {
		this.sign = sign;
	}

	public List<Sign> getYouWinSigns() {
		return youWinSigns;
	}

	public void setYouWinSigns(List<Sign> youWinSigns) {
		this.youWinSigns = youWinSigns;
	}
	
	
	
	
}
