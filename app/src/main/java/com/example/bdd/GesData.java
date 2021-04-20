package com.example.bdd;

import java.util.List;


public class GesData {
    //Planetes du système solaire
    static String[] tailles = {"7.4E7 km²", "4.6E8 km²", "5.1E8 km²", "1.4E8 km²", "6.1E10 km²", "4.3E10 km²", "8.1E9 km²", "7.6E9 km²"};
    static String[] noms = {"Mercure", "Venus", "Terre", "Mars", "Jupiter", "Saturne", "Uranus", "Neptune"};
    static int[] images = {R.drawable.mercure, R.drawable.venus, R.drawable.terre, R.drawable.mars, R.drawable.jupiter, R.drawable.saturne, R.drawable.uranus, R.drawable.neptune};




    public static void ajout(List<Planete> list){
        for (int i = 0; i < Math.min(tailles.length, Math.min(noms.length, images.length)); i++) {
            list.add(new Planete(i, noms[i], tailles[i]));
        }
    }
}
