package thiago.com.br.myapplication.fragments;


import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

import thiago.com.br.myapplication.DAO.BoaViagemDAO;
import thiago.com.br.myapplication.DAO.DatabaseHelper;
import thiago.com.br.myapplication.R;
import thiago.com.br.myapplication.util.Constantes;

/**
 * Created by Samsung on 19/01/2015.
 */
public class GastoFragment extends Fragment  {

    private Calendar calendar;
    private Button btData;
    private Button btSalvar;
    private Spinner spinnerCategoria;
    private EditText etValor;
    private EditText etDescricao;
    private EditText etLocal;
    private Date dataGasto;
    private Calendar calendario;
    // para adicionar um gasto a viagem selecionada
    private DatabaseHelper helper;
    private String id;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //view que será retornada no lugar do Framelayout, aqui é o nosso fragmento
        View view = inicializaComponentes(inflater);

        //verifica se o gasto é de alguma viagem
        Bundle params = getArguments();
        if(params != null) {
            id = (String) params.get(Constantes.VIAGEM_ID);
            if(id != null) ;
        }


        return view;
    }

    private View inicializaComponentes(LayoutInflater inflater) {
        helper = new DatabaseHelper(getActivity());
        View view = inflater.inflate(R.layout.gasto,null);
        spinnerCategoria = (Spinner) view.findViewById(R.id.spinnerCategoria);
        calendario = Calendar.getInstance();
        dataGasto = new Date(calendario.getTimeInMillis());
                ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(getActivity(), R.array.opcoes_categoria, android.R.layout.simple_spinner_dropdown_item);
            spinnerCategoria.setAdapter(adapter);
        etValor = (EditText)view.findViewById(R.id.etValor);
        etDescricao = (EditText)view.findViewById(R.id.etDescricao);
        etLocal = (EditText)view.findViewById(R.id.etLocal);
        calendar = Calendar.getInstance();
        btSalvar = (Button) view.findViewById(R.id.btSalvarGasto);
            btSalvar.setOnClickListener(new OuvinteDoBotao());
        btData = (Button) view.findViewById(R.id.btData);
            btData.setOnClickListener(new OuvinteDoBotao());
            // não sei bem o motivo mas tive que adicionar +1 no mês, estava bugado
            btData.setText(calendar.get(Calendar.DAY_OF_MONTH) + "/"
                          + (calendar.get(Calendar.MONTH) + 1) + "/"
                          + calendar.get(Calendar.YEAR));
        return view;
    }



    DatePickerDialog.OnDateSetListener dateListener = new DatePickerDialog.OnDateSetListener(){

       @Override
       public void onDateSet(DatePicker view, int ano, int mes, int dia) {
           btData.setText(dia + "/" + (mes + 1) + "/" + ano);
            calendario.set(ano,mes,dia);
            dataGasto = new Date(calendario.getTimeInMillis());
       }
   };

    private class OuvinteDoBotao implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btSalvarGasto:
                    SQLiteDatabase db = helper.getWritableDatabase();
                    long restult = db.insert(helper.getTableGasto(), null, monstaGasto());
                        if(restult == -1)Toast.makeText(getActivity(),"Problema ao salvar.",Toast.LENGTH_SHORT).show();
                        else Toast.makeText(getActivity(),"Gasto registrado.",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btData:
                    new DatePickerDialog(getActivity(),dateListener,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH))
                            .show();
            }
        }
    }

    private ContentValues monstaGasto() {
        ContentValues cv = new ContentValues();
        cv.put("categoria",spinnerCategoria.getSelectedItem().toString());
        cv.put("data",dataGasto.getTime());
        cv.put("valor",Integer.valueOf(etValor.getText().toString()));
        cv.put("descricao",etDescricao.getText().toString());
        cv.put("local",etLocal.getText().toString());
            if(id != null)
                cv.put("viagem_id",id);
        return cv;
    }
}


