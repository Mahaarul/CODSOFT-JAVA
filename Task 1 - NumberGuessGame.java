import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NumberGuessGameGUI extends JFrame {
    private int generatedNumber;
    private int maxAttempts = 20;
    private int attemptsLeft;
    private int score;

    private JTextField guessField;
    private JButton guessButton;
    private JLabel resultLabel;

    public NumberGuessGameGUI() {
        setTitle("Number Guessing Game");
        setSize(500,350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initializeGame();

        guessField = new JTextField(10);
        guessButton = new JButton("Guess ?");
        resultLabel = new JLabel("Enter your guess:");

        setLayout(new FlowLayout());
        add(resultLabel);
        add(guessField);
        add(guessButton);

        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkGuess();
            }
        });
    }

    private void initializeGame() {
        generatedNumber = generateRandomNumber();
        attemptsLeft = maxAttempts;
        score = 0;
    }

    private int generateRandomNumber() {
        return (int) (Math.random() * 100) + 1;
    }

    private void checkGuess() {
        try {
            int userGuess = Integer.parseInt(guessField.getText());

            if (userGuess == generatedNumber) {
                displayResult("Congratulations! You guessed the number.");
                score += attemptsLeft;
                int option = JOptionPane.showConfirmDialog(this, "Do you want to play again?", "Play Again", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    initializeGame();
                    resultLabel.setText("Enter your guess:");
                } else {
                    displayScore();
                    System.exit(0);
                }
            } else {
                attemptsLeft--;
                if (attemptsLeft == 0) {
                    displayResult("Sorry, you've run out of attempts. The correct number was: " + generatedNumber);
                    int option = JOptionPane.showConfirmDialog(this, "Do you want to play again?", "Play Again", JOptionPane.YES_NO_OPTION);
                    if (option == JOptionPane.YES_OPTION) {
                        initializeGame();
                        resultLabel.setText("Enter your guess:");
                    } else {
                        displayScore();
                        System.exit(0);
                    }
                } else {
                    String feedback = userGuess < generatedNumber ? "Too low! " : "Too high! ";
                    feedback += "Attempts left: " + attemptsLeft;
                    displayResult(feedback);
                }
            }
        } catch (NumberFormatException ex) {
            displayResult("Invalid input. Please enter a number.");
        }
    }

    private void displayResult(String message) {
        resultLabel.setText(message);
        guessField.setText("");
    }

    private void displayScore() {
        JOptionPane.showMessageDialog(this, "Your final score is: " + score, "Game Over", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new NumberGuessGameGUI().setVisible(true);
            }
        });
    }
}
