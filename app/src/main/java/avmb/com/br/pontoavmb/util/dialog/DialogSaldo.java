package avmb.com.br.pontoavmb.util.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import avmb.com.br.pontoavmb.R;
import avmb.com.br.pontoavmb.model.User;
import avmb.com.br.pontoavmb.views.Index;

/**
 * Created by guilh on 06/10/2016.
 */

public class DialogSaldo extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(final Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.cadastro_saldo, null);

        final EditText edSaldo = (EditText) view.findViewById(R.id.edSaldo);
        builder.setView(view);

        builder.setTitle("Cadastrar novo Saldo")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if(edSaldo.getText() != null && !edSaldo.getText().toString().equals("")){
                            User user = User.last(User.class);
                            if (user != null){
                                user.setSaldo(Double.parseDouble(edSaldo.getText().toString()));
                                user.save();
                            } else {
                                new User(Double.parseDouble(edSaldo.getText().toString())).save();
                            }
                            Index index = (Index) getFragmentManager().getFragments().get(0);
                            index.calculaSaldoGasto();
                        }
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        DialogSaldo.this.getDialog().cancel();
                    }
                });

        return builder.create();
    }
}
