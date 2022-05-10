package had;

import java.awt.Color;
import java.util.Random;

public class Jablko extends GameObject{
    
    private final int maxX;

    private final int maxY;

    private Random rand;

    public Jablko(int sizeInPX, Color color, int maxX, int maxY){
        super(0, 0, sizeInPX, color);
        this.maxX = maxX;
        this.maxY = maxY;
        rand = new Random();
    }

    public void locateJablko(){
        int tmp;
        tmp=rand.nextInt(maxX/getSizeInPX());
        setX(tmp*getSizeInPX());
        tmp=rand.nextInt(maxY/getSizeInPX());
        setY(tmp*getSizeInPX());
    }
}
