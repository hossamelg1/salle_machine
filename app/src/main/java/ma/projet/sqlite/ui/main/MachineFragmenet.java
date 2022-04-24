package ma.projet.sqlite.ui.main;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ma.projet.sqlite.ConfDialog;
import ma.projet.sqlite.HomeActivity;
import ma.projet.sqlite.ListeMachines;
import ma.projet.sqlite.R;
import ma.projet.sqlite.bean.Machine;
import ma.projet.sqlite.bean.Salle;
import ma.projet.sqlite.service.MachineService;
import ma.projet.sqlite.service.SalleService;

public class MachineFragmenet extends Fragment {

    private EditText marque ;
    private EditText reference ;
    private Button create ;
    private Button listeMachines ;

    private Spinner spinner ;
    private SalleService salleService;

    private MainViewModel mViewModel;

    public static MachineFragmenet newInstance() {
        return new MachineFragmenet();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment2, container, false);

        salleService = new SalleService(view.getContext());
        marque = (EditText) view.findViewById(R.id.marque);
        reference = (EditText) view.findViewById(R.id.reference);
        create = (Button) view.findViewById(R.id.btnCreate);
        listeMachines = (Button) view.findViewById(R.id.btnListeMachines);
        spinner = (Spinner) view.findViewById(R.id.Spinner);

        ArrayAdapter<String> adapter;
        List<String> liste = new ArrayList<String>();
        for (Salle salle : salleService.findAll()) {
            liste.add(salle.getCode());
        }
        adapter = new ArrayAdapter<String>(view.getContext(), R.layout.spinner_item, liste);
        adapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(adapter);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MachineService machineService = new MachineService(view.getContext());
                salleService = new SalleService(view.getContext());
                Salle salle = salleService.findByCode(spinner.getSelectedItem().toString());
                machineService.add(new Machine(marque.getText().toString(), reference.getText().toString(), salle));
                marque.setText("");
                reference.setText("");
                openDialog();
            }
        });

        listeMachines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ListeMachines.class);
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
        ConfDialog confDialog = new ConfDialog("Machine");
        confDialog.show(getActivity().getSupportFragmentManager(), "Machine");
    }

}