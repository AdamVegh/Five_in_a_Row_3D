package elements_3D;

import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.DrawMode;

public class SignX extends Box implements Sign{

	public SignX(double width, double height, double depth, Color color, DrawMode drawmode) {
		super(width, height, depth);
		this.setMaterial(new PhongMaterial(color));
		this.setDrawMode(drawmode);
		this.setTranslateZ(-depth);
	}
	
	
}
