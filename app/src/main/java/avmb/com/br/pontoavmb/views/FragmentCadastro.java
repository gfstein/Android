package avmb.com.br.pontoavmb.views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import avmb.com.br.pontoavmb.R;
import avmb.com.br.pontoavmb.model.Grupo;
import avmb.com.br.pontoavmb.model.Item;

public class FragmentCadastro extends Fragment {

    EditText nome, qtd, valor;
    Spinner spinner;
    Button btn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_cadastro, container, false);
        nome = (EditText) view.findViewById(R.id.nomeItem);
        spinner = (Spinner) view.findViewById(R.id.spinner);
        qtd = (EditText) view.findViewById(R.id.qtdItem);
        valor = (EditText) view.findViewById(R.id.valorItem);
        btn = (Button) view.findViewById(R.id.btnCadastro);

        createSpinner(view);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadastrar();
            }
        });

        return view;
    }

    private void cadastrar() {
        Item item = new Item();
        item.setNome(nome.getText().toString());
        item.setGrupo(new Grupo(spinner.getSelectedItem().toString()));
    }

    private void createSpinner(View view) {
        //TODO: pegar do banco depois
        List<Grupo> itens = new ArrayList<>();
        itens.add(new Grupo("Leite"));
        itens.add(new Grupo("Carne"));
        itens.add(new Grupo("Frutas"));

        ArrayAdapter<Grupo> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, itens);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        assert spinner != null;
        spinner.setAdapter(adapter);

    }

}
