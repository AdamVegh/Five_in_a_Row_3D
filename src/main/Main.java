package main;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import coordinates_comparators.Coordinates;
import elements_3D.Grid;
import elements_3D.Sign;
import elements_3D.SignO;
import elements_3D.SignX;
import elements_3D.YouWin;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
//import javafx.scene.input.MouseEvent;
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
	
	private int startcameraDistance = -40;
	private int startcameraRotateX = 0;
	private int startcameraRotateY = 0;
	
	private int cameraDistance = startcameraDistance;
	private int cameraRotateX = startcameraRotateX;
	private int cameraRotateY = startcameraRotateY;

	public static int gridNumber = 15;
	public static final int oneGridSize = 1;

	String redName = "Ádám";
	String blueName = "Éva";

	private int redWin = 0;
	private int blueWin = 0;

	ScoreText scoreText;
	SubScene textScene;

	Sign currentSign;

	Logic logic = new Logic();
	Color sceneColor = Color.DARKSLATEGREY;
	Grid grid;
	Group group;
	YouWin youWin;

	boolean isThereWinner = false;
	
	String moveSound = "moveSound.wav";
	String putSound = "putSound.wav";
	String winSound = "winSound.wav";
	String notSound = "notSound.wav";
	

	public void clearGrid(Group group) {
		logic.getUsedPos().clear();
		group.getChildren().clear();
		for (List<Box> rowBoxes : grid.getGridBoxes()) {
			for (Box box : rowBoxes) {
				group.getChildren().add(box);
			}
		}
	}

	private void soundPlayer(String filepath) {
		MediaPlayer mp = new MediaPlayer(new Media(new File(filepath).toURI().toString()));
		mp.play();
	}
	
	public Parent setStageContent() {
		currentSign = new SignX(0.8 * oneGridSize, 0.8 * oneGridSize, 0.8 * oneGridSize, Color.BLUE, DrawMode.FILL);
		grid = new Grid(gridNumber, oneGridSize);
		scoreText = new ScoreText(redName, blueName, redWin, blueWin, MAX_X);
		textScene = new SubScene(scoreText.getTextBox(), MAX_X, MAX_Y);

		PerspectiveCamera cam = new PerspectiveCamera(true);
		cam.getTransforms().addAll(new Rotate(cameraRotateX, Rotate.X_AXIS),
								   new Rotate(cameraRotateY, Rotate.Y_AXIS),
								   new Translate(0, 0, cameraDistance));

		group = new Group();
		for (List<Box> rowBoxes : grid.getGridBoxes()) {
			for (Box box : rowBoxes) {
				group.getChildren().add(box);
			}
		}
		group.getChildren().addAll((Node) currentSign, cam);

		SubScene scene = new SubScene(group, MAX_X, MAX_Y, true, SceneAntialiasing.BALANCED);
		scene.setCamera(cam);
		scene.setFill(sceneColor);

		scene.setFocusTraversable(true);
//		scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
//			@Override
//			public void handle(MouseEvent event) {
//
//			}
//		});

		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				
				switch (event.getCode()) {
				case LEFT:
					sign_X -= oneGridSize;
					if (sign_X < grid.getMinX()) {
						sign_X += oneGridSize;
					}
					((Node) currentSign).setTranslateX(sign_X);
					soundPlayer(moveSound);
					break;

				case RIGHT:
					sign_X += oneGridSize;
					if (sign_X > grid.getMaxX()) {
						sign_X -= oneGridSize;
					}
					((Node) currentSign).setTranslateX(sign_X);
					soundPlayer(moveSound);
					break;

				case UP:
					sign_Y -= oneGridSize;
					if (sign_Y < grid.getMinY()) {
						sign_Y += oneGridSize;
					}
					((Node) currentSign).setTranslateY(sign_Y);
					soundPlayer(moveSound);
					break;

				case DOWN:
					sign_Y += oneGridSize;
					if (sign_Y > grid.getMaxY()) {
						sign_Y -= oneGridSize;
					}
					((Node) currentSign).setTranslateY(sign_Y);
					soundPlayer(moveSound);
					break;

				case W:
					cam.getTransforms().add(new Translate(0, 0, -cameraDistance));
					cam.getTransforms().add(new Rotate(-cameraRotateX, Rotate.X_AXIS));
					cameraRotateX += 5;
					cam.getTransforms().add(new Rotate(cameraRotateX, Rotate.X_AXIS));
					cam.getTransforms().add(new Translate(0, 0, cameraDistance));
					break;
				
				case S:
					cam.getTransforms().add(new Translate(0, 0, -cameraDistance));
					cam.getTransforms().add(new Rotate(-cameraRotateX, Rotate.X_AXIS));
					cameraRotateX -= 5;
					cam.getTransforms().add(new Rotate(cameraRotateX, Rotate.X_AXIS));
					cam.getTransforms().add(new Translate(0, 0, cameraDistance));
					break;
					
				case A:
					cam.getTransforms().add(new Translate(0, 0, -cameraDistance));
					cam.getTransforms().add(new Rotate(-cameraRotateY, Rotate.Y_AXIS));
					cameraRotateY -= 5;
					cam.getTransforms().add(new Rotate(cameraRotateY, Rotate.Y_AXIS));
					cam.getTransforms().add(new Translate(0, 0, cameraDistance));
					break;
				
				case D:
					cam.getTransforms().add(new Translate(0, 0, -cameraDistance));
					cam.getTransforms().add(new Rotate(-cameraRotateY, Rotate.Y_AXIS));
					cameraRotateY += 5;
					cam.getTransforms().add(new Rotate(cameraRotateY, Rotate.Y_AXIS));
					cam.getTransforms().add(new Translate(0, 0, cameraDistance));
					break;
					
				case Q:
					cam.getTransforms().add(new Translate(0, 0, -cameraDistance));
					cameraDistance += 5;
					cam.getTransforms().add(new Translate(0, 0, cameraDistance));
					break;
				
				case E:
					cam.getTransforms().add(new Translate(0, 0, -cameraDistance));
					cameraDistance -= 5;
					cam.getTransforms().add(new Translate(0, 0, cameraDistance));
					break;
					
				case ENTER:
					if (isThereWinner) {
						clearGrid(group);
						logic = new Logic();
						sign_X = 0;
						sign_Y = 0;
						currentSign = new SignX(0.8 * oneGridSize, 0.8 * oneGridSize, 0.8 * oneGridSize, Color.BLUE,
								DrawMode.FILL);
						group.getChildren().add((Node) currentSign);
						isThereWinner = false;
						return;
					}
					for (Coordinates coord : logic.getUsedPos().keySet()) {
						if ((coord.getX() == sign_X) && (coord.getY() == sign_Y)) {
							scene.setFill(Color.RED);
							soundPlayer(notSound);
							return;
						}
					}
					scene.setFill(Color.GREEN);
					logic.addEngagedPlace(new Coordinates(sign_X, sign_Y), currentSign);
					((Shape3D) currentSign).setTranslateZ(0);

					if (logic.hasWinner()) {
						if (currentSign instanceof SignX)
							++blueWin;
						else if (currentSign instanceof SignO)
							++redWin;

						scoreText.setScoreOnTextBox(redWin, blueWin);
						clearGrid(group);

						youWin = new YouWin(currentSign, new ArrayList<>());

						for (Sign sign : youWin.getYouWinSigns()) {
							group.getChildren().add((Node) sign);
						}
						soundPlayer(winSound);
						isThereWinner = true;
						return;
					}
					soundPlayer(putSound);
					
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
				scene.setFill(sceneColor);
			}
		});

		Group g2 = new Group();
		g2.getChildren().addAll(scene, textScene);
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
