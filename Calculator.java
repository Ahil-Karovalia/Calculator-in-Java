import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator {
    private JFrame frame;
    private JTextField display;
    private JButton[] numberButtons;
    private JButton[] operationButtons;
    private JButton clearButton;
    private JButton equalsButton;
    private JButton sqrtButton;
    private JButton powButton;
    private JButton logButton;
    private JButton sinButton;
    private JButton cosButton;
    private JButton tanButton;
    private JButton expButton;
    private JButton lnButton;
    private JButton piButton;
    private JButton eButton;

    private double num1, num2, result;// Stores operands and results for calculations
    private char operation;// Stores the selected operation
    // Constructor initializes the calculator interface
    public Calculator() {
        createUI();
    }

    // Creates and configures the calculator's user interface
    private void createUI() {
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        // Display for calculator input and results
        display = new JTextField(40);
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.BOLD, 48)); 
        frame.add(display, BorderLayout.NORTH);
        // Panel for calculator buttons in a 6x4 grid layout
        JPanel buttonPanel = new JPanel(new GridLayout(6, 4));
        buttonPanel.setPreferredSize(new Dimension(800, 600));// Set preferred panel size
        frame.add(buttonPanel, BorderLayout.CENTER);
        // Number buttons (0-9) setup
        numberButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(new NumberButtonListener());
            numberButtons[i].setFont(new Font("Arial", Font.BOLD, 24)); // Font size for buttons
            buttonPanel.add(numberButtons[i]);
        }
        
        // Operation buttons (+, -, *, /)
        operationButtons = new JButton[4];
        operationButtons[0] = new JButton("+");
        operationButtons[1] = new JButton("-");
        operationButtons[2] = new JButton("*");
        operationButtons[3] = new JButton("/");
        for (JButton button : operationButtons) {
            button.addActionListener(new OperationButtonListener());
            button.setFont(new Font("Arial", Font.BOLD, 24)); 
            buttonPanel.add(button);
        }
        // Special buttons (Square root, Power, Trigonometric, Logarithmic, etc.)
        sqrtButton = new JButton("v");
        sqrtButton.addActionListener(new SqrtButtonListener());
        sqrtButton.setFont(new Font("Arial", Font.BOLD, 24)); 
        buttonPanel.add(sqrtButton);

        powButton = new JButton("^");
        powButton.addActionListener(new PowButtonListener());
        powButton.setFont(new Font("Arial", Font.BOLD, 24)); 
        buttonPanel.add(powButton);
        // Function buttons for scientific calculations
        logButton = new JButton("log");
        logButton.addActionListener(new LogButtonListener());
        logButton.setFont(new Font("Arial", Font.BOLD, 24));
        buttonPanel.add(logButton);

        sinButton = new JButton("sin");
        sinButton.addActionListener(new SinButtonListener());
        sinButton.setFont(new Font("Arial", Font.BOLD, 24));
        buttonPanel.add(sinButton);

        cosButton = new JButton("cos");
        cosButton.addActionListener(new CosButtonListener());
        cosButton.setFont(new Font("Arial", Font.BOLD, 24));
        buttonPanel.add(cosButton);

        tanButton = new JButton("tan");
        tanButton.addActionListener(new TanButtonListener());
        tanButton.setFont(new Font("Arial", Font.BOLD, 24));
        buttonPanel.add(tanButton);

        expButton = new JButton("exp");
        expButton.addActionListener(new ExpButtonListener());
        expButton.setFont(new Font("Arial", Font.BOLD, 24)); 
        buttonPanel.add(expButton);

        lnButton = new JButton("ln");
        lnButton.addActionListener(new LnButtonListener());
        lnButton.setFont(new Font("Arial", Font.BOLD, 24)); 
        buttonPanel.add(lnButton);

        piButton = new JButton("p");
        piButton.addActionListener(new PiButtonListener());
        piButton.setFont(new Font("Arial", Font.BOLD, 24)); 
        buttonPanel.add(piButton);

        eButton = new JButton("e");
        eButton.addActionListener(new EButtonListener());
        eButton.setFont(new Font("Arial", Font.BOLD, 24)); 
        buttonPanel.add(eButton);
        // Clear button to reset the display
        clearButton = new JButton("C");
        clearButton.addActionListener(new ClearButtonListener());
        clearButton.setFont(new Font("Arial", Font.BOLD, 24)); 
        buttonPanel.add(clearButton);
        // Equals button to perform the calculation
        equalsButton = new JButton("=");
        equalsButton.addActionListener(new EqualsButtonListener());
        equalsButton.setFont(new Font("Arial", Font.BOLD, 24)); 
        buttonPanel.add(equalsButton);
		// Set frame size and visibility
        frame.pack();
        frame.setSize(800, 600); 
        frame.setVisible(true);
    }

    // Listener classes for each button
    private class NumberButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();
            display.setText(display.getText() + button.getText()); // Append number to display
        }
    }

    private class OperationButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();
            num1 = Double.parseDouble(display.getText()); // Store first operand
            operation = button.getText().charAt(0); // Store operation
            display.setText(""); // Clear display for second operand
        }
    }

    private class EqualsButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            num2 = Double.parseDouble(display.getText()); // Get second operand
            switch (operation) {
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case '*':
                    result = num1 * num2;
                    break;
                case '/':
                    if (num2 != 0) {
                        result = num1 / num2;
                    } else {
                        display.setText("Error: Division by zero!");
                        return;
                    }
                    break;
                case '^':
                    result = Math.pow(num1, num2);
                    break;
            }
            display.setText(String.valueOf(result)); // Display result
        }
    }

    // Listener for the Clear button to reset the display
    private class ClearButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            display.setText("");
        }
    }

    // Listener for the Square Root button
    private class SqrtButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            double num = Double.parseDouble(display.getText());
            result = Math.sqrt(num);
            display.setText(String.valueOf(result));
        }
    }
    // Listener for the Power (^) button
    private class PowButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            num1 = Double.parseDouble(display.getText());
            display.setText("");
            operation = '^';
        }
    }
    // Listener for Logarithm (log) button
    private class LogButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            double num = Double.parseDouble(display.getText());
            result = Math.log(num);
            display.setText(String.valueOf(result));
        }
    }

    // Listener for Sine (sin) button
    private class SinButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            double num = Double.parseDouble(display.getText());
            result = Math.sin(num);
            display.setText(String.valueOf(result));
        }
    }
    // Listener for Cosine (cos) button
    private class CosButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            double num = Double.parseDouble(display.getText());
            result = Math.cos(num);
            display.setText(String.valueOf(result));
        }
    }

    // Listener for Tangent (tan) button
    private class TanButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            double num = Double.parseDouble(display.getText());
            result = Math.tan(num);
            display.setText(String.valueOf(result));
        }
    }
    // Listener for Exponential (exp) button
    private class ExpButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            double num = Double.parseDouble(display.getText());
            result = Math.exp(num);
            display.setText(String.valueOf(result));
        }
    }
    // Listener for Natural Logarithm (ln) button
    private class LnButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            double num = Double.parseDouble(display.getText());
            result = Math.log(num);
            display.setText(String.valueOf(result));
        }
    }
    // Listener for Pi (p) button
    private class PiButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            display.setText(String.valueOf(Math.PI));
        }
    }
    // Listener for Euler's Number (e) button
    private class EButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            display.setText(String.valueOf(Math.E));
        }
    }
    
	//Main Method to run the calculator application
    public static void main(String[] args) {
        new Calculator(); ///starts the app
    }
}