package thiago.com.br.myapplication.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import thiago.com.br.myapplication.R;

import static thiago.com.br.myapplication.R.layout.nova_viagem;

/**
 * Created by Samsung on 19/01/2015.
 */
public class ViagemFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.nova_viagem,null);
        return view;
    }


}
