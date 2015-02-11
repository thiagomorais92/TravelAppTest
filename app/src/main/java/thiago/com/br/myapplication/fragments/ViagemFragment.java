package thiago.com.br.myapplication.fragments;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;

import thiago.com.br.myapplication.R;
import thiago.com.br.myapplication.impl.OuvinteDaMudancaDeData;

import static thiago.com.br.myapplication.R.layout.nova_viagem;

/**
 * Created by Samsung on 19/01/2015.
 */
public class ViagemFragment extends Fragment {

    Calendar c;
    Button btDataSaida;
    Button btDataChegada;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.nova_viagem,null);
        inicializaComponentes();


        return view;
    }

    private void inicializaComponentes() {
        c = Calendar.getInstance();
        btDataSaida = (Button) view.findViewById(R.id.btDataSaida);
        btDataSaida.setOnClickListener(new ouvinteDoBotao());

        btDataChegada = (Button) view.findViewById(R.id.btDataChegada);
        btDataChegada.setOnClickListener(new ouvinteDoBotao());
    }


    public  class ouvinteDoBotao implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            new DatePickerDialog(getActivity(),new OuvinteDaMudancaDeData(v),c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH))
                    .show();
        }
    }


}
