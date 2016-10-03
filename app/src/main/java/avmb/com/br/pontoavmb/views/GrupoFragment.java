package avmb.com.br.pontoavmb.views;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import avmb.com.br.pontoavmb.R;

/**
 * A fragment with a Google +1 button.
 */
public class GrupoFragment extends Fragment {

    public GrupoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_grupo, container, false);



        return view;
    }



}
