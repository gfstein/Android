package avmb.com.br.pontoavmb.views;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import avmb.com.br.pontoavmb.R;
import avmb.com.br.pontoavmb.model.Item;
import avmb.com.br.pontoavmb.model.User;


public class index extends Fragment {

    private EditText edSaldo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_index, container, false);
        ListView listItem = (ListView) view.findViewById(R.id.listItens);
        TextView saldo = (TextView) view.findViewById(R.id.tvSaldo);
        edSaldo = (EditText) view.findViewById(R.id.edSaldo);
        TextView gasto = (TextView) view.findViewById(R.id.tvTotalGasto);

        User user = User.last(User.class);
        if(user != null){
            saldo.setText(String.valueOf(user.getSaldo()));
        } else {
            saldo.setText("0");
        }
        saldo.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                creatDialog();
                return true;
            }
        });

        List<Item> list = Item.listAll(Item.class);

        listItem.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, list));

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                FragmentManager fm = getFragmentManager();
                fm.beginTransaction().replace(R.id.content_main, new FragmentCadastro())
                        .commit();

            }
        });

        Double saldoAtual = Double.parseDouble(saldo.getText().toString());
        Double gastoAtual = 0D;
        for (Item item : list){
            gastoAtual += item.getPreco();
        }
        Double total = saldoAtual-gastoAtual;
        gasto.setText(String.valueOf(total));

        return view;
    }

    private void creatDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Cadastrar novo Saldo");
        builder.setView(R.id.view_saldo);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                new User(Double.parseDouble(edSaldo.getText().toString())).save();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.create();
    }
}
