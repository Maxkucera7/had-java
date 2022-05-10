package had;

public class Kolize {
    private Kolize(){} //nepotřebujeme aby se vytvářeli instance, proto privátní konstruktor

    /**
     * Metoda zjišťuje jestli had nezajel mimo hrací pole, nebo nenarazil sám do sebe.
     * @param snake instance třídy Snake
     * @param maxX Maximální šířka hracího pole
     * @param maxY Maximální výška hracího pole
     * @return Vrací true jestli se souřadnice jeho hlavy rovnají nějakým souřadnicím jeho těla.
     * Dále vrací true jestli souřadnice jeho hlavy jsou mimo herní pole. Pokud se žádná z těchto
     * podmínek nepotvrdí, vrací false.
     */
    public static boolean checkCollision(Had had, int maxX, int maxY){
        for(GameObject obj : had.getBody()){
            if((had.getX()==obj.getX()) && (had.getY()==obj.getY())){
                return true;
            }
        }

        if(had.getX()<0){
            return true;
        }
        if(had.getX()>=maxX){
            return true;
        }
        if(had.getY()<0){
            return true;
        }
        if(had.getY()>=maxY){
            return true;
        }

        return false;
    }

    /**
     * Kontroluje zda had najel na bonus
     * @param snake Objekt hada
     * @param bonus Objekt bonusu
     * @return Vrací true, když se souřadnice hlavy hada a bonusu rovnají. Vrací false, když ne.
     */
    public static boolean checkBonus(Had had, Jablko jablko){
        if(had.getX()==jablko.getX() && had.getY()==jablko.getY()){
            return true;
        }
        else{
            return false;
        }
    }
}
