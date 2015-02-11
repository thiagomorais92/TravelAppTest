package thiago.com.br.myapplication.fragments;


import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
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
    private Button btData;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //por padrão a data é a atual.
        calendar = Calendar.getInstance();
        int ano = calendar.get(Calendar.YEAR);
        int mes = calendar.get(Calendar.MONTH);
        int dia = calendar.get(Calendar.DAY_OF_MONTH);


        //view que será retornada no lugar do Framelayout, aqui é o nosso fragmento
        View view = inflater.inflate(R.layout.gasto, null);

        //lidando com o Date picker, lembrando que no btDataGasto, já temos o OnClick definido no xml

        btData = (Button) view.findViewById(R.id.btData);
        btData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getActivity(),dateListener,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH))
                        .show();
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

   DatePickerDialog.OnDateSetListener dateListener = new DatePickerDialog.OnDateSetListener(){

       @Override
       public void onDateSet(DatePicker view, int ano, int mes, int dia) {
           btData.setText(dia + "/" + (mes + 1) + "/" + ano);
       }
   };

}


