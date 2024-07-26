import javax.swing.*;
import javax.swing.text.DateFormatter;
import java.awt.*;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.util.Date;

public class MyWindow extends JFrame {

    JLabel heading ;
    JLabel clockLabel;

    Font font = new Font("",Font.BOLD,20);

    MyWindow()
    {
        super.setTitle("My Clock");
        super.setBounds(100,100,420,420);
        this.setLayout(new GridLayout(2,1));
        this.createGUI();
        this.startClock();
        super.setVisible(true);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void createGUI() {
        heading = new JLabel("My clock");
        clockLabel = new JLabel("Clock");

        heading.setFont(font);
        clockLabel.setFont(font);


        heading.setAlignmentX(Component.CENTER_ALIGNMENT);
        clockLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));


        panel.add(Box.createVerticalGlue());
        panel.add(heading);
        panel.add(Box.createVerticalGlue());
        panel.add(clockLabel);
        panel.add(Box.createVerticalGlue());

        this.add(panel, BorderLayout.CENTER);
    }

    public void startClock()
    {
        Timer timer = new Timer(1000, e -> {
            String dataTime = new Date().toString();
            clockLabel.setText(dataTime);

        });

        timer.start();

    }
}
