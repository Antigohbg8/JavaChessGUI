import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Main {
    private static JButton selectedButton = null;

    public static void main(String[] args) {
        ChessBoard board = new ChessBoard();
        Pictures.addPiecesWhite();
        Pictures.addPiecesblack();
        JFrame frame = new JFrame();

        // Add mouse listener to all buttons
        for (Component squarePanel : ChessBoard.boardPanel.getComponents()) {
            JButton button = (JButton) ((JPanel) squarePanel).getComponent(0);
            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (selectedButton == null) {
                        // Select a piece
                        if (!button.getText().equals("")) {
                            selectedButton = button;
                            highlightPossibleMoves(button);
                        } else {
                            // Display the rules
                            JOptionPane.showMessageDialog(frame, "Chess rules:\n\n" +
                                            "Pawn: Can move forward one square, two squares if it's their first move. Can only capture diagonally.\n" +
                                            "Knight: Can move in any direction two squares and then one square perpendicular to the original direction (an L shape).\n" +
                                            "Bishop: Can move diagonally any number of squares.\n" +
                                            "Rook: Can move horizontally or vertically any number of squares.\n" +
                                            "Queen: Can move horizontally, vertically, or diagonally any number of squares.\n" +
                                            "King: Can move in any direction one square.",
                                    "Chess Rules", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } else {
                        // Move the selected piece
                        if (button.getBackground() == Color.GREEN) {
                            button.setText(selectedButton.getText());
                            button.setForeground(selectedButton.getForeground());
                            selectedButton.setText("");
                            removeHighlights();
                            selectedButton = null;
                        } else if (!button.getText().equals("")) {
                            // Select a different piece
                            removeHighlights();
                            selectedButton = button;
                            highlightPossibleMoves(button);
                        }
                    }
                }
            });
        }
    }

    private static void highlightPossibleMoves(JButton button) {
        switch (button.getText()) {
            case "P":
                highlightPawnMoves(button);
                break;
            case "B":
                highlightBishopMoves(button);
                break;
            case "Q":
                highlightQueenMoves(button);
                break;
            case "N":                           //cases for each of the pieces
                highlightKnightMoves(button);
                break;
            case "R":
                highlightRookMoves(button);
                break;
            case "K":
                highlightKingMoves(button);
                break;
        }
    }

    private static void highlightPawnMoves(JButton button) {
        int position = getPosition(button);
        int row = position / 8;

        // Pawns can move one or two steps forward from the second or seventh row, one step forward or backward otherwise
        int[] possibleMoves = row == 1 || row == 6 ? new int[]{-8, 8, 16, -16} : new int[]{-8, 8};

        // Highlight the possible moves
        for (int move : possibleMoves) {
            int newPosition = position + move;
            if (newPosition >= 0 && newPosition < 64) { // Check if the position is within the board
                JButton newButton = getButton(newPosition);
                if (newButton.getText().equals("")) { // Check if the destination square is empty
                    newButton.setBackground(Color.GREEN);
                }
            }
        }
    }


    private static void highlightBishopMoves (JButton button){
                    int position = getPosition(button);

                    // Highlight the squares diagonally
                    highlightSquares(position, -9);
                    highlightSquares(position, -7);
                    highlightSquares(position, 7);
                    highlightSquares(position, 9);
    }

                private static void highlightQueenMoves (JButton button){
                    int position = getPosition(button);

                    // Highlight the squares horizontally, vertically, and diagonally
                    highlightSquares(position, -8);
                    highlightSquares(position, -1);
                    highlightSquares(position, 1);
                    highlightSquares(position, 8);
                    highlightSquares(position, -9);
                    highlightSquares(position, -7);
                    highlightSquares(position, 7);
                    highlightSquares(position, 9);
    }
                private static void highlightKnightMoves(JButton button) {
                int position = getPosition(button);

                int[] possibleDirections = {-17, -15, -10, -6, 6, 10, 15, 17};
                for (int direction : possibleDirections) {
                int newPosition = position + direction;
                if (newPosition >= 0 && newPosition < 64 && isKnightMove(position, newPosition)) {
                    JButton newButton = getButton(newPosition);
                    if (newButton.getText().equals("")) {
                        newButton.setBackground(Color.GREEN);
                    }
                }
                }
    }
                 private static boolean isKnightMove(int position1, int position2) {
                        int row1 = position1 / 8;
                        int col1 = position1 % 8;
                        int row2 = position2 / 8;
                        int col2 = position2 % 8;
                        int rowDiff = Math.abs(row1 - row2);
                        int colDiff = Math.abs(col1 - col2);

                // Check if the knight is moving in an L-shape (two steps in one direction, one step in the other)
                    if ((rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2)) {
                        // Check that the knight is not "jumping" over the edge of the board
                    if ((col1 < 2 && col2 < col1) || (col1 > 5 && col2 > col1) || (row1 < 2 && row2 < row1) || (row1 > 5 && row2 > row1)) {
                        return false;
                    }
                        return true;
                    }

                        return false;
                    }
                private static void highlightRookMoves(JButton button) {
                    int position = getPosition(button);

                    // Highlight the squares horizontally and vertically
                    highlightSquares(position, -8); // up
                    highlightSquares(position, -1); // left
                    highlightSquares(position, 1);  // right
                    highlightSquares(position, 8);  // down
    }
                private static void highlightKingMoves(JButton button) {
                    int position = getPosition(button);
                //checks the possible boxes and moves the king if a correct box is selected
                    int[] possibleDirections = {-9, -8, -7, -1, 1, 7, 8, 9};
                    for (int direction : possibleDirections) {
                    int newPosition = position + direction;
                    if (newPosition >= 0 && newPosition < 64 && isKingMove(position, newPosition)) {
                        JButton newButton = getButton(newPosition);
                    if (newButton.getText().equals("")) {
                        newButton.setBackground(Color.GREEN);
                }
            }
        }
    }

    private static boolean isKingMove(int position1, int position2) {
        int row1 = position1 / 8;
        int col1 = position1 % 8;
        int row2 = position2 / 8;
        int col2 = position2 % 8;
        return Math.abs(row1 - row2) <= 1 && Math.abs(col1 - col2) <= 1;
    } //Finds the position on the board of the king



    private static void highlightSquares(int position, int direction){
        //highlights all the boxes that can be gone to by each piece on the board will not allow going off the screen or hit other pieces
                    int newPosition = position + direction;
                    while (newPosition >= 0 && newPosition < 64 && isSameRowOrColumnOrDiagonal(position, newPosition)) {
                        JButton newButton = getButton(newPosition);
                        if (!newButton.getText().equals("")) {
                            // Stop if there is a piece in the square
                            break;
                        }
                        newButton.setBackground(Color.GREEN);
                        newPosition += direction;
                    }
                }

                private static boolean isSameRowOrColumnOrDiagonal ( int position1, int position2){
                    int row1 = position1 / 8;
                    int col1 = position1 % 8;
                    int row2 = position2 / 8;
                    int col2 = position2 % 8;
                    return row1 == row2 || col1 == col2 || Math.abs(row1 - row2) == Math.abs(col1 - col2);
                }

                private static void removeHighlights () {
                    for (int i = 0; i < 64; i++) {
                        JButton button = getButton(i);
                        if (button.getBackground() == Color.GREEN) {
                            if ((i / 8 + i % 8) % 2 == 0) {
                                button.setBackground(Color.white);
                            } else {
                                button.setBackground(Color.darkGray);
                            }
                        }
                    }
                } //once you select a spot to move the piece to it gets rid of the highlights

                private static int getPosition (JButton button){
                    for (int i = 0; i < 64; i++) {
                        JButton currentButton = getButton(i);
                        if (currentButton == button) { //finds the current position of the pieces
                            return i;
                        }
                    }
                    return -1; // Return -1 if the button is not found
                }

                private static JButton getButton ( int position){
                    JPanel squarePanel = (JPanel) ChessBoard.boardPanel.getComponent(position);
                    return (JButton) squarePanel.getComponent(0);  //finds what button was pushed
                }
            }



