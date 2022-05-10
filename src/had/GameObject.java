package had;

import java.awt.Color;
import java.awt.Graphics2D;

public class GameObject {
    /**
     * x-ová souřadnice
     */
    private int x;
    /**
     * y-ová souřadnice
     */
    private int y;
    /**
     * velikost v pixelech při vykreslení
     */
    private int sizeInPX;
    /**
     * barva objektu (na vykreslení)
     */
    private Color color;

    /**
     * Konstruktor na vytvoření
     * @param x x-ová souřadnice
     * @param y y-ová souřadnice
     * @param sizeInPX velikost v pixelech při vykreslení
     * @param color barva objektu (na vykreslení)
     */
    public GameObject(int x, int y, int sizeInPX, Color color){
        setX(x);
        setY(y);
        setSizeInPX(sizeInPX);
        setColor(color);
    }

    /**
     * Vykreslení objektu
     * @param g2 instance třídy Graphics2D na vykreslení
     */
    public void draw(Graphics2D g2){
        g2.setColor(color);
        g2.fillRect(x, y, sizeInPX, sizeInPX);
    }

    /**
     * @return x-ová souřadnice
     */
    public int getX() {
        return x;
    }

    /**
     * @param x nová x-ová souřadnice
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return y-ová souřadnice
     */
    public int getY() {
        return y;
    }

    /**
     * @param y nová y-ová souřadnice
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * @return velikost v PX
     */
    public int getSizeInPX() {
        return sizeInPX;
    }

    /**
     * @param sizeInPX nová velikost v PX
     */
    public void setSizeInPX(int sizeInPX) {
        this.sizeInPX = sizeInPX;
    }

    /**
     * @return barva pro vykreslení
     */
    public Color getColor(){
        return color;
    }

    /**
     * @param color nová barva pro vykreslní
     */
    public void setColor(Color color){
        this.color = color;
    }

}
