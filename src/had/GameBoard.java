package had;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import javax.swing.JPanel;

public class GameBoard extends JPanel implements Runnable{
    
    private final int WIDTH;
    /**
     * Výška herního pole
     */
    private final int HEIGHT;
    /**
     * Instance třídy Snake
     */
    private Had had;
    /**
     * Instance třídy Bonus
     */
    private Jablko jablko;
    /**
     * Informace zda jsme ještě ve hře, nebo jsme prohráli
     */
    private boolean inGame;

    /**
     * instance třídy BufferStrategy na vykreslování s metodou double-buffer
     */
    private BufferStrategy bs;

    /**
     * FPS (1000/FRAME_DELAY), ovlivňuje rychlost hry
     */
    private final int FRAME_DELAY = 100;
    /**
     * Jak dlouho běžel jeden cyklus, pomocná proměnná při synchronizaci FPS
     */
    private long cycleTime;

    /**
     *
     * @param width šířka herního pole
     * @param height výška herního pole
     * @param bs instance třídy bufferstrategy na vykreslování
     */
    public GameBoard(int width, int height, BufferStrategy bs){
        addKeyListener(new TAdapter());
        setFocusable(true);
        setIgnoreRepaint(true);
        WIDTH = width;
        HEIGHT = height;
        this.bs = bs;

        gameInit();
    }

    /**
     * Nastavení hry a její zapnutí
     */
    private void gameInit(){
        inGame = true;
        had = new Had(50, 50, 10, Color.GREEN, Color.GRAY);
        jablko = new Jablko(10, Color.YELLOW, WIDTH, HEIGHT);
        jablko.locateJablko();

        Thread animace = new Thread(this, "Game");
        animace.start();
    }


    /**
     * Hlavní smyčka, kde probíhá furt dokola aktualizace herní logiky, překreslování a synchronizace FPS
     */
    @Override
    public void run() {

        cycleTime = System.currentTimeMillis();

        while(inGame){

            updateLogic();

            updateGui();

            synchFrameRate();

        }

        gameOver();

    }

    /**
     * Synchronizace FPS
     */
    private void synchFrameRate() {
        cycleTime += FRAME_DELAY;
        long difference = cycleTime-System.currentTimeMillis();
        try {
            Thread.sleep(Math.max(0, difference));
        }
        catch(InterruptedException e) {
            e.printStackTrace();
        }
        cycleTime = System.currentTimeMillis();
    }

    /**
     * Vykreslení herní plochy
     */
    private void updateGui() {
        Graphics2D g2 = (Graphics2D)bs.getDrawGraphics();

        g2.setColor(Color.BLACK); //vyčištění
        g2.fillRect(0, 0, WIDTH, HEIGHT);

        had.draw(g2);
        jablko.draw(g2);

        g2.dispose();

        bs.show();

        Toolkit.getDefaultToolkit().sync();
    }

    /**
     * Kontrola kolizí a posun hada
     */
    private void updateLogic() {
        if(Kolize.checkCollision(had, WIDTH, HEIGHT)){
            inGame=false;
        }else if(Kolize.checkBonus(had, jablko)){
            had.expandBody();
            jablko.locateJablko();
        }else{
            had.move();
        }
    }

    /**
     * Zobrazení obrazovky s nápisem prohry a se skórem
     */
    private void gameOver(){
        Graphics2D g2 = (Graphics2D)bs.getDrawGraphics();

        String zprava="Prohrál jsi!";
        String skore="Dosáhl jsi skóre: " + had.getBody().size();
        Font font = new Font("Helvetica", Font.BOLD, 20);
        FontMetrics metr = this.getFontMetrics(font);

        g2.setColor(Color.BLACK); //vyčištění
        g2.fillRect(0, 0, WIDTH, HEIGHT);

        g2.setColor(Color.WHITE);
        g2.setFont(font);
        g2.drawString(zprava, (WIDTH - metr.stringWidth(zprava))/2, (HEIGHT/2)+25);
        g2.drawString(skore, (WIDTH - metr.stringWidth(skore))/2, (HEIGHT/2)-25);

        g2.dispose();

        bs.show();

        Toolkit.getDefaultToolkit().sync();

        try {
            Thread.sleep(2000);
        }
        catch(InterruptedException e) {
            e.printStackTrace();
        }

        System.exit(0);
    }

    /**
     * Soukromá třída která zpracovává zmáčknuté klávesy
     * @author Fugiczek
     * @version 1.1
     */
    private class TAdapter extends KeyAdapter{
        public void keyPressed(KeyEvent e){
            //int hodnota zmáčknuté klávesy
            int key=e.getKeyCode();
            if ((key == KeyEvent.VK_UP || key==KeyEvent.VK_W) && (had.getDirect()!=Smer.DOWN)) {
                had.setDirect(Smer.UP);
            }
            if ((key == KeyEvent.VK_RIGHT || key==KeyEvent.VK_D) && (had.getDirect()!=Smer.LEFT)) {
                had.setDirect(Smer.RIGHT);
            }
            if ((key == KeyEvent.VK_DOWN || key==KeyEvent.VK_S) && (had.getDirect()!=Smer.UP)) {
                had.setDirect(Smer.DOWN);
            }
            if ((key == KeyEvent.VK_LEFT || key==KeyEvent.VK_A) && (had.getDirect()!=Smer.RIGHT)) {
                had.setDirect(Smer.LEFT);
            }
        }
    }
}
