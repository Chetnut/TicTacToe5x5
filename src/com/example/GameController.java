package com.example;

import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;

public class GameController {

    private final int SIZE = 5;
    private Button[][] tiles = new Button[SIZE][SIZE];
    private boolean xTurn = true;

    public Pane createContent() {
        GridPane base = new GridPane();
        base.setPrefSize(600, 600);

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                Button btn = new Button();
                btn.setPrefSize(120, 120);
                btn.setStyle("-fx-font-size: 25");

                int currentRow = row;
                int currentCol = col;

                btn.setOnAction(e -> buttonPressed(currentRow, currentCol));

                tiles[row][col] = btn;
                base.add(btn, col, row);
            }
        }

        return base;
    }



    // carries out a button press
    private void buttonPressed(int row, int col) {
        Button clicked = tiles[row][col];

        if (!clicked.getText().equals("")) {
            return;
        }

        if (xTurn) {
            clicked.setText("X");
        } else {
            clicked.setText("O");
        }

        if (gameOverCheck(row, col)) {
            String winner = xTurn ? "X" : "O";
            showWinMessage(winner);
            resetBoard();
        } else {
            xTurn = !xTurn;
        }
    }
    // checks if the last move won the game
    private boolean gameOverCheck(int row, int col) {
        String symbol = tiles[row][col].getText();

        return checkLine(row, col, 1, 0, symbol) ||  // horizontal
               checkLine(row, col, 0, 1, symbol) ||  // vertical
               checkLine(row, col, 1, 1, symbol) ||  // diagonal
               checkLine(row, col, 1, -1, symbol); }
    



    // checks a direction for 5 in a row
    private boolean checkLine(int row, int col, int rowStep, int colStep, String symbol) {
        int count = 1;

        // forward
        int r = row + rowStep;
        int c = col + colStep;
        while (inBounds(r, c) && tiles[r][c].getText().equals(symbol)) {
            count++;
            r += rowStep;
            c += colStep;
        }
        // backward
        r = row - rowStep;
        c = col - colStep;
        while (inBounds(r, c) && tiles[r][c].getText().equals(symbol)) {
            count++;
            r -= rowStep;
            c -= colStep;
        }

        return count >= 5;
    }
    // Checks if row and column are within board limits
    private boolean inBounds(int row, int col) {
        return row >= 0 && row < SIZE && col >= 0 && col < SIZE;
    }



    // resets the board
    private void resetBoard() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                tiles[row][col].setText("");
            }
        }
        xTurn = true;
    }

    // shows an alert box when someone wins
    private void showWinMessage(String winner) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText(null);
        alert.setContentText(winner + " wins!");
        alert.showAndWait();
    }
}
