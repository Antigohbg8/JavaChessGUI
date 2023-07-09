import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import javax.swing.*;


public class ChessBoard {
    public static JPanel boardPanel;
    public JFrame frame;

    public ChessBoard() {
        frame = new JFrame("Chess Board");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);

        boardPanel = new JPanel(new GridLayout(8, 8));
        boardPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        // Add the squares to the chessboard
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                JPanel squarePanel = new JPanel(new GridLayout(1, 1));
                squarePanel.setSize(100, 100);
                boardPanel.add(squarePanel);


                JButton button = new JButton();
                if ((row + col) % 2 == 0) {
                    button.setBackground(Color.white);
                } else {
                    button.setBackground(Color.darkGray);
                }
                button.setFont(new Font("Arial", Font.PLAIN, 10));
                button.setFocusPainted(false);
                squarePanel.add(button);


            }
        }

        frame.add(boardPanel);
        frame.setVisible(true);
    }

}

