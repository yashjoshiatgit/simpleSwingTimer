import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class MyWindow1 {

    Dimension defaultScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
    ImageIcon backImage;
    JFrame timerFrame;
    JLayeredPane layeredPane = new JLayeredPane();
    JTextField inputField;
    JLabel textLabel,textLabel1;
    Timer countdownTimer;
    int secondsLeft;
    JTextField textLabel2;

    JButton button;

    MyWindow1() {
        timerFrame = new JFrame("Feel The Focus");
        timerFrame.setTitle("Feel The Focus");
        timerFrame.setLayout(new BorderLayout());
        timerFrame.setSize((int) defaultScreenSize.getWidth(), (int) defaultScreenSize.getHeight());
        timerFrame.setUndecorated(true);
        timerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setImageByMySelf();
        timerFrame.setVisible(true);
    }

    public void setImageByMySelf() {
        // Image Section
        backImage = new ImageIcon(ClassLoader.getSystemResource("Wallpaper/clockWallpaper.jpg"));
        Image I = backImage.getImage().getScaledInstance(1920, 1080, Image.SCALE_DEFAULT);
        ImageIcon originalBackGSetOnLabel = new ImageIcon(I);

        // Background Label
        JLabel oneLabel = new JLabel(originalBackGSetOnLabel);
        oneLabel.setBounds(0, 0, (int) defaultScreenSize.getWidth(), (int) defaultScreenSize.getHeight());

        // Countdown Label
        textLabel = new JLabel("00:00", SwingConstants.CENTER);
        textLabel.setForeground(Color.WHITE);
        textLabel.setFont(new Font("Arial", Font.BOLD, 120));
        textLabel.setBounds(0, 0, (int) defaultScreenSize.getWidth(), (int) defaultScreenSize.getHeight());

        textLabel1 = new JLabel("ENTER MINUTES", SwingConstants.CENTER);
        textLabel1.setForeground(Color.WHITE);
        textLabel1.setFont(new Font("Arial", Font.BOLD, 25));
        textLabel1.setBounds((((int) defaultScreenSize.getWidth()/2)-((int) defaultScreenSize.getWidth()/12)), 285, (int) defaultScreenSize.getWidth()/6, (int) defaultScreenSize.getHeight());

        // Input Field
        textLabel2 = new JTextField("00", SwingConstants.CENTER);
        textLabel2.setBorder(null);
        textLabel2.setOpaque(false);
        textLabel2.setForeground(Color.WHITE);
        textLabel2.setFont(new Font("Arial", Font.BOLD, 20));
        textLabel2.setBounds(((int)defaultScreenSize.getWidth()/2)-((int)defaultScreenSize.getWidth()/100)-35, (int) defaultScreenSize.getHeight() - 50, (int)defaultScreenSize.getWidth()/50, 40);

        // Button
        button = new JButton("START");
        button.addActionListener(e -> {
                startCountdown();
                button.setVisible(false);
                textLabel2.setVisible(false);
                textLabel1.setVisible(false);
        });
        button.setBounds(((int)defaultScreenSize.getWidth()/2)-((int)defaultScreenSize.getWidth()/100)+(int)defaultScreenSize.getWidth()/50+10-35, (int) defaultScreenSize.getHeight() - 50, (int)defaultScreenSize.getWidth()/14, 40);
        button.setOpaque(false);
        button.setForeground(Color.white);
        button.setFont(new Font("Arial", Font.BOLD, 15));
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);

        // Layer Section
        layeredPane.setSize((int) defaultScreenSize.getWidth(), (int) defaultScreenSize.getHeight());
        layeredPane.add(oneLabel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(textLabel, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(textLabel2, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(textLabel1, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(button, JLayeredPane.PALETTE_LAYER);
        timerFrame.add(layeredPane);
    }

    public void startCountdown() {
        try {
            String minutes1 = textLabel2.getText();
            int minutes = Integer.parseInt(minutes1);
            secondsLeft = minutes * 60;

            if (countdownTimer != null) {
                countdownTimer.cancel();
            }

            countdownTimer = new Timer();
            countdownTimer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    if (secondsLeft > 0) {
                        secondsLeft--;
                        updateClock();
                    } else {
                        countdownTimer.cancel();
                        JOptionPane.showMessageDialog(timerFrame, "Time's up!");
                    }
                }
            }, 0, 1000);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(timerFrame, "Please enter a valid number of minutes.");
        }
    }

    public void updateClock() {
        int minutes = secondsLeft / 60;
        int seconds = secondsLeft % 60;
        String timeString = String.format("%02d:%02d", minutes, seconds);
        textLabel.setText(timeString);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MyWindow1::new);
    }
}
