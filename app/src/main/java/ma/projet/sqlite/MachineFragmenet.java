package ma.projet.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MachineFragmenet extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.machine_fragmenet_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, ma.projet.sqlite.ui.main.MachineFragmenet.newInstance())
                    .commitNow();
        }
    }
}