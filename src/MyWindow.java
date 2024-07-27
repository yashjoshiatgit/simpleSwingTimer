import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MyWindow extends JFrame {

    JLabel heading;
    JLabel clockLabel;
    JTextField inputField;
    JButton startButton;
    Timer countdownTimer;
    int minutesLeft;

    Dimension defaultScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Font font = new Font("", Font.BOLD, 20);

    MyWindow() {
        super.setTitle("Feel The Focus");
        super.setSize((int) defaultScreenSize.getWidth(), (int) defaultScreenSize.getHeight());
        this.setLayout(new BorderLayout());
        this.setUndecorated(true);
        this.createGUI();
        super.setVisible(true);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void createGUI() {
        heading = new JLabel("Focus Mode Timer");
        clockLabel = new JLabel("Set minutes and start");

        heading.setFont(font);
        clockLabel.setFont(font);

        heading.setForeground(Color.WHITE);
        clockLabel.setForeground(Color.WHITE);

        heading.setAlignmentX(Component.CENTER_ALIGNMENT);
        clockLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        inputField = new JTextField(5);
        startButton = new JButton("Start");

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startCountdown();
            }
        });

        JPanel controlPanel = new JPanel();
        controlPanel.setBackground(new Color(120, 40, 200));
        controlPanel.add(new JLabel("Minutes:"));
        controlPanel.add(inputField);
        controlPanel.add(startButton);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(120, 40, 200));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(Box.createVerticalGlue());
        panel.add(heading);
        panel.add(Box.createVerticalGlue());
        panel.add(clockLabel);
        panel.add(Box.createVerticalGlue());
        panel.add(controlPanel);
        panel.add(Box.createVerticalGlue());

        this.add(panel, BorderLayout.CENTER);
    }

    public void startCountdown() {
        try {
            int minutes = Integer.parseInt(inputField.getText());
            minutesLeft = minutes * 60; // Convert minutes to seconds

            if (countdownTimer != null) {
                countdownTimer.cancel();
            }

            countdownTimer = new Timer();
            countdownTimer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    if (minutesLeft > 0) {
                        minutesLeft--;
                        updateClock();
                    } else {
                        countdownTimer.cancel();
                        JOptionPane.showMessageDialog(MyWindow.this, "Time's up!");
                    }
                }
            }, 0, 1000); // Update every second

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number of minutes.");
        }
    }

    public void updateClock() {
        int minutes = minutesLeft / 60;
        int seconds = minutesLeft % 60;
        String timeString = String.format("%02d:%02d", minutes, seconds);
        clockLabel.setText(timeString);
    }

    public static void main(String[] args) {
        new MyWindow();
    }
}
