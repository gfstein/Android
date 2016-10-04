package avmb.com.br.pontoavmb.views;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.orm.SugarContext;

import avmb.com.br.pontoavmb.R;
import avmb.com.br.pontoavmb.model.Grupo;
import avmb.com.br.pontoavmb.service.GrupoService;


public class GrupoFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SugarContext.init(getContext());
    }

    public GrupoFragment() {
        // Required empty public constructor
    }

    EditText nome;
    EditText descricao;
    Button btn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_grupo, container, false);
        nome = (EditText) view.findViewById(R.id.edNome);
        descricao = (EditText) view.findViewById(R.id.edDesc);
        btn = (Button) view.findViewById(R.id.btnGrupo);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
                if(nome.getError() == null){
                    cadastrar();
                }
            }
        });

        return view;
    }

    private void validate() {

        if(nome.getText().toString().isEmpty()){
            nome.setError(getString(R.string.obrigatorio));
        }

    }

    private void cadastrar() {
        Grupo grupo = new Grupo();
        grupo.setNome(nome.getText().toString());
        grupo.setDescricao(descricao.getText().toString());

        try{
            GrupoService.save(grupo);
            Toast.makeText(getContext(), getString(R.string.grupo_done), Toast.LENGTH_LONG).show();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_main, new index() )
                    .commitAllowingStateLoss();
        } catch (Exception e){
            e.printStackTrace();
        }
    }


}
