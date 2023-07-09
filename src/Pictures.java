import java.awt.*;
import javax.swing.*;

import java.util.*;



public class Pictures {



    public static void addPiecesWhite() {
        ArrayList<Integer> positions = new ArrayList<Integer>();
        String[] pieces = {"R", "N", "B", "Q", "K", "B", "N", "R", "P", "P", "P", "P", "P", "P", "P", "P"};
        for (int i = 0; i < 64; i++) {
            positions.add(i);
        }
        Collections.shuffle(positions);

        for (int i = 0; i < pieces.length; i++) {
            String piece = pieces[i];
            int position = positions.get(i);
            int row = position / 8;
            int col = position % 8;

            JPanel squarePanel = (JPanel) ChessBoard.boardPanel.getComponent(position);
            JButton button = (JButton) squarePanel.getComponent(0);
            if (button.getText().equals("")) { // check if the square is empty
                button.setText(piece);
                button.setForeground(Color.CYAN);
            } else { // if the square is not empty, find another square to place the piece
                for (int j = position + 1; j < 64; j++) {
                    JPanel nextSquarePanel = (JPanel) ChessBoard.boardPanel.getComponent(j);
                    JButton nextButton = (JButton) nextSquarePanel.getComponent(0);
                    if (nextButton.getText().equals("")) {
                        nextButton.setText(piece);
                        nextButton.setForeground(Color.CYAN);
                        break;
                    }
                }
            }
        }
    }

    public static void addPiecesblack() {
        String[] pieces1 = {"R", "N", "B", "Q", "K", "B", "N", "R", "P", "P", "P", "P", "P", "P", "P", "P"};
        ArrayList<Integer> positions1 = new ArrayList<Integer>();
        for (int i = 0; i < 64; i++) {
            positions1.add(i);
        }
        Collections.shuffle(positions1);

        for (int i = 0; i < pieces1.length; i++) {
            String piece = pieces1[i];
            int position = positions1.get(i);
            int row = position / 8;
            int col = position % 8;

            JPanel squarePanel = (JPanel) ChessBoard.boardPanel.getComponent(position);
            JButton button = (JButton) squarePanel.getComponent(0);
            if (button.getText().equals("")) { // check if the square is empty
                button.setText(piece);
                button.setForeground(Color.BLACK);
            } else { // if the square is not empty, find another square to place the piece
                for (int j = position + 1; j < 64; j++) {
                    JPanel nextSquarePanel = (JPanel) ChessBoard.boardPanel.getComponent(j);
                    JButton nextButton = (JButton) nextSquarePanel.getComponent(0);
                    if (nextButton.getText().equals("")) {
                        nextButton.setText(piece);
                        nextButton.setForeground(Color.BLACK);
                        break;
                    }
                }
            }
        }
    }
}