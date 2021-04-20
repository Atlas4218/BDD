package com.example.bdd;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.room.Room;

import java.util.List;

public class AjoutDialogFragment extends DialogFragment {


    private EditText mEditText;
    EditText taille;
    private Button btn;
    MainActivity main;



    public AjoutDialogFragment() {
        // le fragment est créé par la méthode newInstance
    }

    public static AjoutDialogFragment newInstance(String title) {

        AjoutDialogFragment frag = new AjoutDialogFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        return frag;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,

                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_ajout_dialog, container);


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);


        mEditText = (EditText) view.findViewById(R.id.name);
        taille = (EditText) view.findViewById(R.id.size);
        btn = (Button) view.findViewById(R.id.ok);


        // quand le button est cliqué, l'activité est appellé,
        // la valeur mEditText est passeé à l'activité en paramètre

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread(new Runnable() {
                    AppDatabase db = Room.databaseBuilder(main.getApplicationContext(),
                            AppDatabase.class, "planetesDB").build();

                    PlaneteDao planeteDao = db.planeteDao();
                    @Override
                    public void run() {
                        List<Planete> planetes = planeteDao.getAll();

                        planeteDao.insert(new Planete(planetes.get(planetes.size()-1).getUid()+1, mEditText.getText().toString(), taille.getText().toString()));

                    }
                }).start();
                onStop();
            }
        });

        String title = getArguments().getString("title", "Votre nom");

        getDialog().setTitle(title);

        mEditText.requestFocus();

        getDialog().getWindow().setSoftInputMode(

                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

    }

    @Override
    public void onAttach(@NonNull Activity activity) {
        super.onAttach(activity);
        main = (MainActivity) activity;

    }
}
