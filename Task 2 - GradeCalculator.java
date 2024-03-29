import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GradeCalculatorAWT extends Frame {
    private TextField[] subjectFields;
    private Button calculateButton;
    private TextArea resultArea;

    public GradeCalculatorAWT() {
        setTitle("Grade Calculator");
        setSize(500, 500);
        setLayout(new FlowLayout());
        setResizable(false);

        initializeComponents();

        addComponents();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                System.exit(0);
            }
        });
    }

    private void initializeComponents() {
        int numSubjects = 5; // You can modify this value based on your requirement

        subjectFields = new TextField[numSubjects];
        calculateButton = new Button("Calculate");
        resultArea = new TextArea("", 10, 40, TextArea.SCROLLBARS_VERTICAL_ONLY);
        resultArea.setEditable(false);

        for (int i = 0; i < numSubjects; i++) {
            subjectFields[i] = new TextField(3);
            add(new Label("Subject " + (i + 1) + ":"));
            add(subjectFields[i]);
        }

        add(calculateButton);
        add(resultArea);

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateResults();
            }
        });
    }

    private void addComponents() {
        // Components are added in initializeComponents() method
    }

    private void calculateResults() {
        int totalMarks = 0;

        for (TextField subjectField : subjectFields) {
            try {
                int marks = Integer.parseInt(subjectField.getText());
                if (marks < 0 || marks > 100) {
                    resultArea.setText("Invalid marks! Please enter marks between 0 and 100.");
                    return;
                }
                totalMarks += marks;
            } catch (NumberFormatException ex) {
                resultArea.setText("Invalid input. Please enter valid numbers.");
                return;
            }
        }

        double averagePercentage = (double) totalMarks / subjectFields.length;

        resultArea.setText("Results:\n" +
                "Total Marks: " + totalMarks + "\n" +
                String.format("Average Percentage: %.2f%%\n", averagePercentage) +
                "Grade: " + calculateGrade(averagePercentage));
    }

    private String calculateGrade(double averagePercentage) {
        if (averagePercentage >= 90) {
            return "A+";
        } else if (averagePercentage >= 80) {
            return "A";
        } else if (averagePercentage >= 70) {
            return "B";
        } else if (averagePercentage >= 60) {
            return "C";
        } else if (averagePercentage >= 50) {
            return "D";
        } else {
            return "F";
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            new GradeCalculatorAWT().setVisible(true);
        });
    }
}
