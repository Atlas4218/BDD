package com.example.bdd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    final String PREFS_NAME = "preferences_file";
    List<Planete> planetes;
    RecyclerView mRecyclerView;
    FloatingActionButton ajouter;

    MonRecyclerViewAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ajouter = (FloatingActionButton) findViewById(R.id.floatingActionButton);


        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "planetesDB").build();

        PlaneteDao planeteDao = db.planeteDao();

        ajouter.setOnClickListener(v -> {
            FragmentManager fm = getSupportFragmentManager();
            AjoutDialogFragment simpleDialogFragment = AjoutDialogFragment.newInstance("Titre");
            simpleDialogFragment.onAttach(this);
            simpleDialogFragment.show(fm, "fragment_ajout_dialog");
        });



        loadData(planeteDao);



    }
    private void initData(PlaneteDao planeteDao) {

        planetes = new ArrayList<>();
        GesData.ajout(planetes);
        /*planetes.add(new Planete(1,"Mercure",4900));
        planetes.add(new Planete(2,"Venus",12000));
        planetes.add(new Planete(3,"Terre",12800));
        planetes.add(new Planete(4,"Mars",6800));
        planetes.add(new Planete(5,"Jupiter",144000));
        planetes.add(new Planete(6,"Saturne",120000));
        planetes.add(new Planete(7,"Uranus",52000));
        planetes.add(new Planete(8,"Neptune",50000));
        planetes.add(new Planete(9,"Pluton",2300));*/


        for (int index = 0; index < planetes.size(); index++) {
            Planete planete = planetes.get(index);
            planeteDao.insert(planete);
        }
    }
    void loadData(PlaneteDao planeteDao) {

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);

        new Thread(new Runnable() {
            @Override
            public void run() {

                if (settings.getBoolean("is_data_loaded", true)) {
                    initData(planeteDao);
                    settings.edit().putBoolean("is_data_loaded", false).commit();
                }

                planetes = planeteDao.getAll();

                mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
                CoordinatorLayout mcoordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
                mRecyclerView.setHasFixedSize(true);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                mRecyclerView.setLayoutManager(mLayoutManager);
                mAdapter = new MonRecyclerViewAdapter(planetes);
                mRecyclerView.setAdapter(mAdapter);



            }
        }).start();

    }
    private List<Planete> getDataSource() {
        return planetes;
    }
    @Override
    protected void onResume() {
        super.onResume();
//        mAdapter.setDetecteurDeClicSurRecycler(this);
    }

}