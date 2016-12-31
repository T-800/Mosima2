package sma.structures;

import org.lwjgl.Sys;

import java.util.HashSet;

public class EnsPointDInteret {
    private HashSet<PointDInteret> ens;


    public EnsPointDInteret(){
        this.ens = new HashSet<>();
    }

    public boolean add(PointDInteret p){
        boolean add = true;
        for (PointDInteret pi : ens) {
            if (p.estDomine(pi)) return false;
        }

        this.removeDom(p);
        System.out.println("ajouté aux points non-dominés "+p);
        return true;

    }

    /**
     * On retire de l'ens tout les élément qui sont dominé par le Point d'interet p
     * @param p
     */
    public void removeDom(PointDInteret p){
        HashSet<PointDInteret> s = new HashSet<>();
        for (PointDInteret pi : ens) {
            if (pi.estDomine(p)) s.add(pi);
        }
        for (PointDInteret elt : s){
            System.out.println("suppr de l'ens " + elt);
            ens.remove(elt);
        }
    }



}
