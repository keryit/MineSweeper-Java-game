import javax.swing.*;
import java.awt.*;
import java.io.File;
import sweeper.Box;
import sweeper.Coordinates;
import sweeper.Game;
import sweeper.Ranges;


public class MineSweeper extends JFrame {

    private Game game;
    private JPanel panel;
    private final int COLUMNS = 9;
    private final int ROWS = 9;
    private final int IMG_SIZE = 50;

    private MineSweeper() {
        game = new Game(COLUMNS, ROWS);
        game.start();
        Ranges.setSize(new Coordinates(COLUMNS, ROWS));
        setImages();
        initPanel();
        initFrame();
    }

    public static void main(String[] args) {

        new MineSweeper().setVisible(true);
    }

    private void initPanel() {
        panel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                for (Coordinates coord : Ranges.getAllCoords()) {
                    g.drawImage((Image)game.getBox(coord).image,
                            coord.x * IMG_SIZE, coord.y * IMG_SIZE, this);
                }
            }
        };
        panel.setPreferredSize(new Dimension(
                Ranges.getSize().x * IMG_SIZE,
                Ranges.getSize().y * IMG_SIZE));
        add(panel);
    }

    private void initFrame() {
        //comment
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Mine Sweeper");
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        setIconImage(getImage("icon"));
    }

    private void setImages(){
        for (Box box : Box.values()){
            box.image = getImage(box.name().toLowerCase());
        }
    }

    private Image getImage (String name){
        String filename = "img" + File.separator  + name + ".png";
        ImageIcon icon = new ImageIcon(getClass().getResource(filename));
        return  icon.getImage();
    }


}
