package org.example;
import javax.swing.*;
import java.awt.*;

public class SwingApp {
    private JFrame frame;
    private JLabel label;

    public SwingApp() {
        frame = new JFrame("ZooKeeper Watcher");
        label = new JLabel("Children count: 0", SwingConstants.CENTER);
        frame.getContentPane().add(label, BorderLayout.CENTER);
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void show() {
        SwingUtilities.invokeLater(() -> frame.setVisible(true));
    }

    public void updateCount(int count) {
        SwingUtilities.invokeLater(() -> label.setText("Children count: " + count));
    }

    public void close() {
        SwingUtilities.invokeLater(() -> frame.dispose());
    }
}
