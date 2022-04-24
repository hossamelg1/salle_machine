package ma.projet.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import ma.projet.sqlite.ui.main.SalleMachineFragment;

public class SalleMachine extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.salle_machine_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, SalleMachineFragment.newInstance())
                    .commitNow();
        }
    }
}