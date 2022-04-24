package ma.projet.sqlite.ui.main;

import androidx.lifecycle.ViewModelProvider;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import ma.projet.sqlite.ConfDialog;
import ma.projet.sqlite.HomeActivity;
import ma.projet.sqlite.ListSalleActivity;
import ma.projet.sqlite.R;
import ma.projet.sqlite.bean.Machine;
import ma.projet.sqlite.bean.Salle;
import ma.projet.sqlite.service.MachineService;
import ma.projet.sqlite.service.SalleService;

public class SalleFragment2 extends Fragment {

    private EditText code;
    private EditText libelle;
    private Button add;
    private Button menu;
    private Button list;

    SalleService db = null;

    private MainViewModel mViewModel;

    public static SalleFragment2 newInstance() {
        return new SalleFragment2();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.salle_activity, container, false);


        code = (EditText) view.findViewById(R.id.code);
        libelle = (EditText) view.findViewById(R.id.libelle);
        add = (Button) view.findViewById(R.id.add);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (code.getText().length() != 0 && libelle.getText().length() != 0) {
                    db =  new SalleService(view.getContext());
                    db.add(new Salle(code.getText().toString(), libelle.getText().toString()));
                    code.setText("");
                    libelle.setText("");
                    openDialog();
                }
                else
                    Toast.makeText(view.getContext(), "All fields are required", Toast.LENGTH_SHORT).show();
            }
        });

        list = (Button) view.findViewById(R.id.list);
        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), ListSalleActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        // TODO: Use the ViewModel
    }

    public void openDialog(){
        ConfDialog confDialog = new ConfDialog("Salle");
        confDialog.show(getActivity().getSupportFragmentManager(), "Salle");
    }
}