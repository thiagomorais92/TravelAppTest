package thiago.com.br.myapplication.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import thiago.com.br.myapplication.R;

/**
 * Created by Samsung on 11/02/2015.
 */
public class Home extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home,null);

        return view;
    }
}
