
//  TIC-TAC-TOE game using java
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToe {

    private JFrame frame;
    private JButton[][] buttons = new JButton[3][3];

    private String player = "X";
    private String[] players = new String[2];
    private String playerWin = "";
    private Boolean isGameEnd = false;
    private int blockLeft = 9;

    //////////////////////////////////// CLICK THE BUTTON BY PLAYER /////////////////////////////////////////////

    private void buttonClick(int row, int col) {
        buttons[row][col].setText(player);
        blockLeft--;
    }

    //////////////////////////////////// CHECK IF THERE IS WINNER /////////////////////////////////////////////

    private void checkWinner(int row, int col) {

        for (int j = 0; j < 3; j++) {
            if (!buttons[row][j].getText().equals(player)) break;
                
            if (j == 2) {
                isGameEnd = true;
                playerWin = player=="X" ? players[0]:players[1];
            }
        }

        for (int i = 0; i < 3; i++) {
            if (!buttons[i][col].getText().equals(player)) break;
                
            if (i == 2) {
                isGameEnd = true;
                playerWin = player=="X" ? players[0]:players[1];
            }
        }

        if (row == col) {
            for (int i = 0; i < 3; i++) {
                if (!buttons[i][i].getText().equals(player)) break;
                    
                if (i == 2) {
                    isGameEnd = true;
                    playerWin = player=="X" ? players[0]:players[1];
                }
            }
        }

        if (row + col == 2) {
            for (int i = 0; i < 3; i++) {
                if (!buttons[i][2 - i].getText().equals(player)) break;
                    
                if (i == 2) {
                    isGameEnd = true;
                    playerWin = player=="X" ? players[0]:players[1];
                }
            }
        }

        if (blockLeft == 0)
            isGameEnd = true;

        if (player.equals("X"))
            player = "O";
        else
            player = "X";
    }

    //////////////////////////////////// DECLARE THE RESULT /////////////////////////////////////////////

    private void endTheGame() {
        JOptionPane.showMessageDialog(frame, "Game is End");

        JButton restartButton;
        JLabel winner;

        frame.getContentPane().removeAll();

        if (playerWin.equals(""))
            winner = new JLabel("Game is Tie", SwingConstants.CENTER);
        else
            winner = new JLabel("Winner is " + playerWin, SwingConstants.CENTER);

        winner.setFont(new Font("Arial", Font.BOLD, 24));

        restartButton = new JButton("Restart the game");

        frame.add(winner);
        frame.add(restartButton);

        frame.revalidate();
        frame.repaint();

        restartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                restartGame();
            }
        });
    }

    private void restartGame() {
        buttons = new JButton[3][3];
        playerWin = "";
        player = "X";
        blockLeft = 9;
        isGameEnd = false;
        frame.getContentPane().removeAll();
        ticTacToeGUI();
    }

    //////////////////////////////////// TIC TAC TOE GUI /////////////////////////////////////////////
    private void ticTacToeGUI() {

        frame.setLayout(new GridLayout(3, 3));

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("");

                final int row = i, col = j;

                frame.add(buttons[i][j]);

                buttons[i][j].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (buttons[row][col].getText().equals("")) {
                            buttonClick(row, col);
                            checkWinner(row, col);
                        }
                        if (isGameEnd == true) {
                            endTheGame();
                        }
                    }
                });

            }
        }

        frame.setVisible(true);
    }

    //////////////////////////////////// INSERT PLAYER NAME /////////////////////////////////////////////

    void insertPlayerName() {
        String player1 = JOptionPane.showInputDialog("Enter player 1 name: ", "X");
        String player2 = JOptionPane.showInputDialog("Enter player 2 name: ", "Y");
        if(player1.equals("")) player1 = "X";
        if(player2.equals("")) player2 = "X";
        players[0] = player1;
        players[1] = player2;
    }

    //////////////////////////////////// CREATE THE FRAME /////////////////////////////////////////////
    void startGame() {
        frame = new JFrame("TicTacToe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        insertPlayerName();

        ticTacToeGUI();
    }

    public void main(String args[]) {
        
        startGame();

    }
}