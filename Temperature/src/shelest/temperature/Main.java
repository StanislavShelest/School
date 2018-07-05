package shelest.temperature;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame();

                frame.setVisible(true);
                frame.setTitle("Перевод температур");

                int widthWindow = 500;
                int heightWindow = 400;
                frame.setSize(widthWindow, heightWindow);


                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                int width = screenSize.width;
                int height = screenSize.height;
                frame.setLocation((width-widthWindow)/2,(height-heightWindow)/2);

                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            }

        });
    }
}
