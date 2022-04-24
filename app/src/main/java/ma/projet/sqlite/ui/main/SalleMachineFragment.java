package ma.projet.sqlite.ui.main;

import androidx.lifecycle.ViewModelProvider;

import android.app.Service;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import javax.security.auth.Destroyable;

import ma.projet.sqlite.R;
import ma.projet.sqlite.bean.Machine;
import ma.projet.sqlite.bean.Salle;
import ma.projet.sqlite.service.MachineService;
import ma.projet.sqlite.service.SalleService;

public class SalleMachineFragment extends Fragment {
    private BarChart barChart;

    private MainViewModel mViewModel;

    public static SalleMachineFragment newInstance() {
        return new SalleMachineFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment4, container, false);
        barChart = (BarChart) view.findViewById(R.id.barChart);
        List<BarEntry> barEntries = new ArrayList<>();
        SalleService ss = new SalleService(view.getContext());
        List<Salle> salles = ss.findAll();
        MachineService ms = new MachineService(view.getContext());
        List<Machine> machines = ms.findAll();
        List<String> salleNames = new ArrayList<>();

        for (Salle salle : salles){
            salleNames.add(salle.getCode());
        }


        for (Salle salle : salles){
            int i = 0;
            int count = 0;
            for (Machine machine : machines){
                if (salle.getCode().contains(machine.getSalle().getCode())){
                    count++;
                }
            }
            barEntries.add(new BarEntry(count, count));
        }
        BarDataSet barDataSet = new BarDataSet(barEntries, "Salle");
        Description description = new Description();
        description.setText("Salles");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        barChart.setDescription(description);
        BarData barData = new BarData(barDataSet);
        barChart.setData(barData);
        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(salleNames));
        xAxis.setLabelCount(salleNames.size());
        xAxis.setDrawAxisLine(false);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f);

        barChart.animateY(2000);




        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        // TODO: Use the ViewModel
    }

}