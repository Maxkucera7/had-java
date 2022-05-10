package had;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

public class Had extends GameObject{
    /**
     * List s hadovými částmi těla
     */
    private List<GameObject> body;
    /**
     * Barva těla hada
     */
    private Color colorBody;
    /**
     * Směr kudy had jde
     */
    private Smer smer;

    /**
     * Konstruktor na vytvoření
     * @param x x-ová souřadnice
     * @param y y-ová souřadnice
     * @param sizeInPX velikost v pixelech při vykreslení
     * @param color barva hlavy (na vykreslení)
     * @param colorBody barva těla (na vykreslení)
     */
    public Had(int x, int y, int sizeInPX, Color color, Color colorBody) {
        super(x, y, sizeInPX, color);
        body = new ArrayList<>();
        setColorBody(colorBody);
        setDirect(Smer.DOWN);
    }

    /**
     * Přepsaná třída na vykreslení, vykresluje hlavu a tělo dohromady
     */
    @Override
    public void draw(Graphics2D g2){
        g2.setColor(getColor());
        g2.fillRect(getX(), getY(), getSizeInPX(), getSizeInPX());

        for(GameObject ob : body){
            ob.draw(g2);
        }
    }

    /**
     * @return barva těla
     */
    public Color getColorBody() {
        return colorBody;
    }

    /**
     * @param colorBody nová barva těla
     */
    public void setColorBody(Color colorBody) {
        this.colorBody = colorBody;
    }

    /**
     * @return směr hada
     */
    public Smer getDirect() {
        return smer;
    }

    /**
     * @param direct nový směr hada
     */
    public void setDirect(Smer smer) {
        this.smer = smer;
    }

    /**
     * Rozšíří tělo hada o jednu novou část, dá ji na místo hlavy a hlavu posune
     */
    public void expandBody(){
        body.add(0, new GameObject(getX(), getY(), getSizeInPX(), getColorBody()));
        moveHead();
    }

    /**
     * @return list s tělem
     */
    public List<GameObject> getBody(){
        return body;
    }

    /**
     * Pohne s celým tělem včetně hlavy
     */
    public void move(){
        moveBody(); // první musí být pohyb těla protože vychází ze souřadnic hlavy
        moveHead();
    }

    /**
     * Pohyb hlavy v závislosti na směru
     */
    private void moveHead(){
        switch(getDirect()){
        case LEFT:
            setX(getX()-getSizeInPX());
            break;
        case RIGHT:
            setX(getX()+getSizeInPX());
            break;
        case UP:
            setY(getY()-getSizeInPX());
            break;
        case DOWN:
            setY(getY()+getSizeInPX());
            break;
        }
    }

    /**
     * Posouvá tělem na základě souřadnic minulé části
     */
    private void moveBody(){
        int tmpX=getX(), tmpY=getY(), tmp; // pomocné proměnné

        for(GameObject obj : body){
            tmp = obj.getX();
            obj.setX(tmpX);
            tmpX = tmp;
            tmp = obj.getY();
            obj.setY(tmpY);
            tmpY = tmp;
        }

    }
}
