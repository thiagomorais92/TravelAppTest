package thiago.com.br.myapplication.fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;

import java.util.Calendar;

import thiago.com.br.myapplication.R;

/**
 * Created by Samsung on 19/01/2015.
 */
public class GastoFragment extends Fragment {


    Calendar calendar = Calendar.getInstance();
    int ano = calendar.get(Calendar.YEAR);
    int mes = calendar.get(Calendar.MONTH);
    int dia = calendar.get(Calendar.DAY_OF_MONTH);
    Spinner spinnerCategoria;
    Button btDataGasto;



    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.gasto,null);

        String[] opcoesGasto = {"Alimentação","Combustível","Hospedagem","Transporte","Entretenimento","Saúde","Outros"};
        spinnerCategoria = (Spinner) view.findViewById(R.id.spinnerCategoria);
        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(getActivity(),R.array.opcoes_categoria,android.R.layout.simple_spinner_dropdown_item);

        spinnerCategoria.setAdapter(adapter);
        btDataGasto = (Button) view.findViewById(R.id.data);
        btDataGasto.setText(dia + "/" + (mes+1) + "/" + ano);



        return view;
    }
    private DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            ano = year;
            mes = monthOfYear;
            dia = dayOfMonth;
            btDataGasto.setText(dia+"/"+(mes+1)+"/"+ano);
        }
};
    public void selecionarData(View view){
        getActivity().showDialog(view.getId());
    }
}
    /*Calendar calendar =  Calendar.getInstance();
    int ano = calendar.get(Calendar.YEAR);
    int mes = calendar.get(Calendar.MONTH);
    int dia = calendar.get(Calendar.DAY_OF_MONTH);
    Spinner spinnerCategoria;
    Button btDataGasto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gasto);
        String[] opcoesGasto = {"Alimentação","Combustível","Hospedagem","Transporte","Entretenimento","Saúde","Outros"};
        spinnerCategoria = (Spinner) findViewById(R.id.spinnerCategoria);
        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(this,R.array.opcoes_categoria,android.R.layout.simple_spinner_dropdown_item);

        spinnerCategoria.setAdapter(adapter);
        btDataGasto = (Button) findViewById(R.id.data);
        btDataGasto.setText(dia + "/" + (mes+1) + "/" + ano);


    }


    private DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            ano = year;
            mes = monthOfYear;
            dia = dayOfMonth;
            btDataGasto.setText(dia+"/"+(mes+1)+"/"+ano);
        }
    };

    @Override
    protected Dialog onCreateDialog(int id) {
        if(id == R.id.data)
            return new DatePickerDialog(this,listener,ano,mes,dia);
        else return null;
    }*/

