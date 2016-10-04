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
import android.widget.Toast;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.List;

import avmb.com.br.pontoavmb.R;
import avmb.com.br.pontoavmb.model.Grupo;
import avmb.com.br.pontoavmb.model.Item;

public class FragmentCadastro extends Fragment implements View.OnClickListener, Validator.ValidationListener {

    @NotEmpty
    EditText nome, qtd, valor;

    Spinner spinner;
    Button btn;
    Validator validator;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        validator = new Validator(this);
        validator.setValidationListener(this);

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

        createSpinner();

       btn.setOnClickListener(this);

        return view;
    }

    private void cadastrar() {
        Item item = new Item();
        item.setNome(nome.getText().toString());
        item.setGrupo(new Grupo(spinner.getSelectedItem().toString()));
    }

    private void createSpinner() {
        List<Grupo> itens = Grupo.listAll(Grupo.class, "nome");

        if(itens != null){
            ArrayAdapter<Grupo> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, itens);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            assert spinner != null;
            spinner.setAdapter(adapter);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnCadastro:
                validator.validate();

        }
    }


    @Override
    public void onValidationSucceeded() {
        Item item = new Item();
        item.setNome(nome.getText().toString());
        item.setPreco(Float.parseFloat(valor.getText().toString()));
        item.setQtd(Integer.parseInt(qtd.getText().toString()));
        item.setGrupo((Grupo) spinner.getSelectedItem());
        item.save();

        Toast.makeText(getContext(), "Item cadastrado com sucesso", Toast.LENGTH_LONG).show();

        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_main, new index() )
                .commitAllowingStateLoss();
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(getContext());

            // Display error messages ;)
            if (view instanceof EditText) {
                ((EditText) view).setError(getString(R.string.obrigatorio));
            }else {
                Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
            }
        }
    }
}
