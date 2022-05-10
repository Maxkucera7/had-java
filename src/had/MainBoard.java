package had;

import java.awt.Dimension;
import javax.swing.JFrame;

public class MainBoard extends JFrame {
    
    public MainBoard( int width, int height){
        setSize(new Dimension(width+3,height+3));
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        createBufferStrategy(2);
        add(new GameBoard(width, height, getBufferStrategy()));
    }
}
