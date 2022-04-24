package ma.projet.sqlite;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import org.w3c.dom.Text;

public class ConfDialog extends AppCompatDialogFragment {

    private TextView success;
    private String name;
    Button get_list;
    public ConfDialog(String name){
        this.name = name;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog, null);
        success = (TextView) view.findViewById(R.id.success);
        success.setText(name+" "+success.getText());
        get_list = (Button) view.findViewById(R.id.get_list);
        get_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(name.contains("Salle")){
                    Intent intent = new Intent(view.getContext(), ListSalleActivity.class);
                    startActivity(intent);
                }
                else{
                    Intent intent = new Intent(view.getContext(), ListeMachines.class);
                    startActivity(intent);
                }
            }
        });
        builder.setView(view)
                .setTitle(name)
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
        return builder.create();
    }
}
