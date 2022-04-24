package ma.projet.sqlite;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import ma.projet.sqlite.adapter.ListeMachinesSallesAdapter;
import ma.projet.sqlite.bean.Salle;
import ma.projet.sqlite.service.MachineService;
import ma.projet.sqlite.service.SalleService;


public class MachineBySalle extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner spinner;
    private SalleService salleService;
    private MachineService machineService;
    private ListView liste;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_machi_salle);
        salleService = new SalleService(getApplicationContext());
        spinner = (Spinner) findViewById(R.id.spinner);
        liste = (ListView) findViewById(R.id.listView);
        ArrayAdapter<String> adapter;
        List<String> list = new ArrayList<String>();
        for (Salle salle : salleService.findAll()){
            list.add(salle.getCode());
        }
        adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_item, list);
        adapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        machineService = new MachineService(getApplicationContext());
        int id = salleService.findByCode(spinner.getSelectedItem().toString()).getId();
        ListeMachinesSallesAdapter as = new ListeMachinesSallesAdapter(getApplicationContext(), machineService.findMachines(id));
        liste.setAdapter(as);
    }
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

}