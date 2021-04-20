package com.example.bdd;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MonRecyclerViewAdapter extends RecyclerView.Adapter<com.example.bdd.MonRecyclerViewAdapter.ConteneurDeDonnee> {
    private List<Planete> donnees;
    private static DetecteurDeClicSurRecycler detecteurDeClicSurRecycler;

    public MonRecyclerViewAdapter(List<Planete> donnees) {
        this.donnees = donnees;
    }

    @Override
    public ConteneurDeDonnee onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new ConteneurDeDonnee(view);
    }

    @Override
    public void onBindViewHolder(ConteneurDeDonnee conteneur, int position) {
        conteneur.tv_principal.setText(donnees.get(position).getNom());
        conteneur.tv_auxiliaire.setText(donnees.get(position).getTaille());
        switch (position){
            case 0:
                conteneur.imageView.setImageResource(R.drawable.mercure);
                break;
            case 1:
                conteneur.imageView.setImageResource(R.drawable.venus);
                break;
            case 2:
                conteneur.imageView.setImageResource(R.drawable.terre);
                break;
            case 3:
                conteneur.imageView.setImageResource(R.drawable.mars);
                break;
            case 4:
                conteneur.imageView.setImageResource(R.drawable.jupiter);
                break;
            case 5:
                conteneur.imageView.setImageResource(R.drawable.saturne);
                break;
            case 6:
                conteneur.imageView.setImageResource(R.drawable.uranus);
                break;
            case 7:
                conteneur.imageView.setImageResource(R.drawable.neptune);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return donnees.size();
    }


    public void setDetecteurDeClicSurRecycler(DetecteurDeClicSurRecycler detecteurDeClicSurRecycler) {
        this.detecteurDeClicSurRecycler = detecteurDeClicSurRecycler;
    }



    public static class ConteneurDeDonnee extends RecyclerView.ViewHolder implements View.OnClickListener  {
        TextView tv_principal;
        TextView tv_auxiliaire;
        ImageView imageView;

        public ConteneurDeDonnee(View itemView) {
            super(itemView);
            tv_principal = (TextView) itemView.findViewById(R.id.tv_principal);
            tv_auxiliaire = (TextView) itemView.findViewById(R.id.tv_auxiliaire);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            //detecteurDeClicSurRecycler.clicSurRecyclerItem(getAdapterPosition(), v);
        }
    }
}


