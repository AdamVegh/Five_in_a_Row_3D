package main;

import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class ScoreText {
	
	private int maxX;
	private int textPosX;
	private String scoreString = "   %s - %s   ";
	private Text player1Text;
	private Text player2Text;
	private Text scoreText = new Text(scoreString);
	private HBox textBox = new HBox();
	
	
	public ScoreText(String player1Name, String player2Name, int player1Score, int player2Score, int maxX) {
		this.maxX = maxX;
		player1Text = new Text(player1Name);
		player2Text = new Text(player2Name);
		setText(player1Text, Color.RED);
		setText(player2Text, Color.BLUE);
		textBox.getChildren().addAll(player1Text, scoreText, player2Text);
		setScoreOnTextBox(player1Score, player2Score);
	}

	
	public HBox getTextBox() {
		return textBox;
	}
	
	
	public void setScoreOnTextBox(int player1Score, int player2Score) {
		scoreText = new Text(String.format(scoreString, player1Score, player2Score));
		setText(scoreText, Color.GOLDENROD);
		calcPositionX();
		player1Text.setTranslateX(textPosX);
		scoreText.setTranslateX(textPosX);
		player2Text.setTranslateX(textPosX);
		textBox.getChildren().set(1, scoreText);
	}
	
	
	private void setText(Text text, Color color) {
		text.setFont(new Font("Arial Black", 30));
		text.setFill(color);
		text.setEffect(new DropShadow(25, color));
	}
	

	private void calcPositionX() {
		textPosX = (maxX - calcTextLength()) / 2;
	}
	
	
	private int calcTextLength() {
		return (int)(player1Text.getLayoutBounds().getWidth() +
					scoreText.getLayoutBounds().getWidth() +
					player2Text.getLayoutBounds().getWidth());
	}
}
