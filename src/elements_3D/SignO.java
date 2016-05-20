package elements_3D;

import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.Sphere;

public class SignO extends Sphere implements Sign {

	public SignO(double radius, Color color, DrawMode drawmode) {
		super(radius);
		this.setMaterial(new PhongMaterial(Color.RED));
		this.setDrawMode(DrawMode.FILL);
		this.setTranslateZ(-2*radius);
	}
	
}
