package thiago.com.br.myapplication.fragments;


import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

import thiago.com.br.myapplication.R;

/**
 * Created by Samsung on 19/01/2015.
 */
public class GastoFragment extends Fragment  {


    private Calendar calendar;
    private int ano;
    private int mes;
    private int dia;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //por padrão a ada é a atual.
        calendar = Calendar.getInstance();
        int ano = calendar.get(Calendar.YEAR);
        int mes = calendar.get(Calendar.MONTH);
        int dia = calendar.get(Calendar.DAY_OF_MONTH);

        //verificando se o usuario mudou a data, pois agora muda.
        if(getArguments()!= null) {
            Bundle bundle = getArguments();
            ano = bundle.getInt("ano");
            mes = bundle.getInt("mes");
            dia = bundle.getInt("dia");
        }


        //view que será retornada no lugar do Framelayout, aqui é o nosso fragmento
        View view = inflater.inflate(R.layout.gasto, null);

        //lidando com o Date picker, lembrando que no btDataGasto, já temos o OnClick definido no xml
       Button btData;
        btData = (Button) view.findViewById(R.id.btData);
        btData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dateDialog = new DatePickerFragment();
                android.app.FragmentManager manager = getActivity().getFragmentManager();
                dateDialog.show(manager,"datePicker");
            }
        });
        // não sei bem o motivo mas tive que adicionar +1 no mês, estava bugado
        btData.setText(dia + "/" + (mes + 1) + "/" + ano);

        Spinner spinnerCategoria;
        spinnerCategoria = (Spinner) view.findViewById(R.id.spinnerCategoria);
        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(getActivity(), R.array.opcoes_categoria, android.R.layout.simple_spinner_dropdown_item);

        spinnerCategoria.setAdapter(adapter);
        return view;
    }



}


