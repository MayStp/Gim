package gim;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HelloDemo extends JFrame {
    private JLabel characterLabel;
    private JLabel backgroundLabel;
    private int characterX, characterY;
    private int characterSpeedX, characterSpeedY;

    public HelloDemo() {
        setTitle("Movable Character");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 1080);
        setResizable(false);

        // Membuat background
        ImageIcon bg = new ImageIcon("src/gim/assets/bgg.jpg");
        backgroundLabel = new JLabel(bg);
        backgroundLabel.setBounds(0, 0, 500, 1080);
        add(backgroundLabel);

        characterX = 350;
        characterY = 350;
        characterSpeedX = 0;
        characterSpeedY = 0;

        // Membuat karakter
        ImageIcon chara = new ImageIcon("src/gim/assets/ship.png");
        characterLabel = new JLabel(chara);
        characterLabel.setBounds(characterX, characterY, 50, 50);
        backgroundLabel.add(characterLabel);

        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                switch (keyCode) {
                    case KeyEvent.VK_UP:
                        characterSpeedY = -5;
                        break;
                    case KeyEvent.VK_DOWN:
                        characterSpeedY = 5;
                        break;
                    case KeyEvent.VK_LEFT:
                        characterSpeedX = -5;
                        break;
                    case KeyEvent.VK_RIGHT:
                        characterSpeedX = 5;
                        break;
                    default:
                        break;
                }
            }

            public void keyReleased(KeyEvent e) {
                int keyCode = e.getKeyCode();
                switch (keyCode) {
                    case KeyEvent.VK_UP:
                    case KeyEvent.VK_DOWN:
                        characterSpeedY = 0;
                        break;
                    case KeyEvent.VK_LEFT:
                    case KeyEvent.VK_RIGHT:
                        characterSpeedX = 0;
                        break;
                    default:
                        break;
                }
            }
        });

        setFocusable(true);
        requestFocusInWindow();

        setVisible(true);

        startGameLoop();
    }

    public void updateCharacterPosition() {
        characterX += characterSpeedX;
        characterY += characterSpeedY;
        characterLabel.setLocation(characterX, characterY);
    }

    public void startGameLoop() {
        Timer timer = new Timer(10, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateCharacterPosition();
            }
        });
        timer.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                HelloDemo game = new HelloDemo();
            }
        });
    }
}
