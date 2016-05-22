package main;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import coordinates_comparators.*;
import elements_3D.*;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.Shape3D;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

public class Main extends Application {

	final int MAX_X = 800;
	final int MAX_Y = 600;

	private int sign_X = 0;
	private int sign_Y = 0;

	static int gridNumber = 15;
	public static final int oneGridSize = 1;

	Sign currentSign;

	Logic logic = new Logic();
	Grid grid;
	Group group;
	YouWin youWin;
	
	boolean isThereWinner = false;
	
	public void clearGrid(Group group) {
		logic.getUsedPos().clear();
		group.getChildren().clear();
		for (List<Box> rowBoxes : grid.getGridBoxes()) {
			for (Box box : rowBoxes) {
				group.getChildren().add(box);
			}
		}
	}

	public Parent setStageContent() {
		
		currentSign = new SignX(0.8 * oneGridSize, 0.8 * oneGridSize, 0.8 * oneGridSize, Color.BLUE, DrawMode.FILL);
		grid = new Grid(gridNumber, oneGridSize);

		PerspectiveCamera cam = new PerspectiveCamera(true);
		cam.getTransforms().addAll(new Rotate(-23, Rotate.Y_AXIS), new Rotate(-25, Rotate.X_AXIS),
				new Translate(0, 0, -35));

		group = new Group();
		for (List<Box> rowBoxes : grid.getGridBoxes()) {
			for (Box box : rowBoxes) {
				group.getChildren().add(box);
			}
		}

		group.getChildren().addAll((Node) currentSign, cam);

		SubScene scene = new SubScene(group, MAX_X, MAX_Y, true, null);
		scene.setCamera(cam);
		scene.setFill(Color.AQUAMARINE);

		scene.setFocusTraversable(true);
		scene.setOnMouseDragged(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				
			}
		});
		
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if (isThereWinner) {
					clearGrid(group);
					logic = new Logic();
					sign_X = 0;
					sign_Y = 0;
					currentSign = new SignX(0.8 * oneGridSize, 0.8 * oneGridSize, 0.8 * oneGridSize, Color.BLUE, DrawMode.FILL);
					group.getChildren().add((Node) currentSign);
					isThereWinner = false;
					return;
				}
				switch (event.getCode()) {
				case LEFT:
					sign_X -= oneGridSize;
					if (sign_X < grid.getMinX()) {
						sign_X += oneGridSize;
					}
					((Node) currentSign).setTranslateX(sign_X);
					break;

				case RIGHT:
					sign_X += oneGridSize;
					if (sign_X > grid.getMaxX()) {
						sign_X -= oneGridSize;
					}
					((Node) currentSign).setTranslateX(sign_X);
					break;

				case UP:
					sign_Y -= oneGridSize;
					if (sign_Y < grid.getMinY()) {
						sign_Y += oneGridSize;
					}
					((Node) currentSign).setTranslateY(sign_Y);
					break;

				case DOWN:
					sign_Y += oneGridSize;
					if (sign_Y > grid.getMaxY()) {
						sign_Y -= oneGridSize;
					}
					((Node) currentSign).setTranslateY(sign_Y);
					break;

				case ENTER:
					for (Coordinates coord : logic.getUsedPos().keySet()) {
						if ((coord.getX() == sign_X) && (coord.getY() == sign_Y)) {
							scene.setFill(Color.RED);
							return;
						}
					}
					scene.setFill(Color.GREEN);
					logic.addEngagedPlace(new Coordinates(sign_X, sign_Y), currentSign);
					((Shape3D) currentSign).setTranslateZ(0);

					if (logic.hasWinner()) {

						clearGrid(group);

						youWin = new YouWin(currentSign, new ArrayList<>());

						for (Sign sign : youWin.getYouWinSigns()) {
							group.getChildren().add((Node) sign);
						}
						
						isThereWinner = true;
						return;
					}

					sign_X = 0;
					sign_Y = 0;
					if (currentSign instanceof SignX) {
						currentSign = new SignO(oneGridSize / 2.0, Color.RED, DrawMode.FILL);
						group.getChildren().add((Node) currentSign);
					} else {
						currentSign = new SignX(0.8 * oneGridSize, 0.8 * oneGridSize, 0.8 * oneGridSize, Color.BLUE,
								DrawMode.FILL);
						group.getChildren().add((Node) currentSign);
					}
					break;
				default:
					break;
				}
			}
		});

		scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				scene.setFill(Color.AQUAMARINE);
			}
		});

		Group g2 = new Group();
		g2.getChildren().add(scene);

		return g2;
	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.sizeToScene();
		stage.setTitle("TIC-TAC-TOE 3D");
		stage.setMaxWidth(MAX_X);
		stage.setMaxHeight(MAX_Y);
		stage.setScene(new Scene(setStageContent()));
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
