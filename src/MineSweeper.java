import sweeper.Box;
import sweeper.Coordinates;
import sweeper.Game;
import sweeper.Ranges;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;


public class MineSweeper extends JFrame {

    private final int COLUMNS = 9;
    private final int ROWS = 9;
    private final int BOMBS = 10;
    private final int IMG_SIZE = 50;
    private Game game;
    private JLabel jLabel;
    private JPanel panel;

    private MineSweeper() {
        game = new Game(COLUMNS, ROWS, BOMBS);
        game.start();
        Ranges.setSize(new Coordinates(COLUMNS, ROWS));
        setImages();
        initLabel();
        initPanel();
        initFrame();
    }

    public static void main(String[] args) {

        new MineSweeper().setVisible(true);
    }

    private void initLabel() {
        jLabel = new JLabel("Welcome!!!");
        add(jLabel, BorderLayout.SOUTH);
    }

    private void initPanel() {
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                for (Coordinates coord : Ranges.getAllCoords()) {
                    g.drawImage((Image) game.getBox(coord).image,
                            coord.x * IMG_SIZE, coord.y * IMG_SIZE, this);
                }
            }
        };

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX() / IMG_SIZE;
                int y = e.getY() / IMG_SIZE;
                Coordinates coord = new Coordinates(x, y);
                if (e.getButton() == MouseEvent.BUTTON1) {
                    game.pressLeftButton(coord);
                }
                if (e.getButton() == MouseEvent.BUTTON3) {
                    game.pressRightButton(coord);
                }
                if (e.getButton() == MouseEvent.BUTTON2) {
                    game.start();
                }
                jLabel.setText(getMessage());
                panel.repaint();
            }
        });

        panel.setPreferredSize(new Dimension(
                Ranges.getSize().x * IMG_SIZE,
                Ranges.getSize().y * IMG_SIZE));
        add(panel);
    }

    private String getMessage() {
        switch (game.getGameState()) {
            case PLAYED:
                return "Try to find a bomb";
            case BOMBED:
                return "You are bombed!!!";
            case WINNER:
                return "Congratulations!!!";
            default:
                return "WELCOME";
        }
    }

    private void initFrame() {
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Mine Sweeper");
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        setIconImage(getImage("icon"));
    }

    private void setImages() {
        for (Box box : Box.values()) {
            box.image = getImage(box.name().toLowerCase());
        }
    }

    private Image getImage(String name) {
        String filename = "img" + File.separator + name + ".png";
        ImageIcon icon = new ImageIcon(getClass().getResource(filename));
        return icon.getImage();
    }
}
