package thiago.com.br.myapplication.fragments;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import thiago.com.br.myapplication.DAO.DatabaseHelper;
import thiago.com.br.myapplication.R;
import thiago.com.br.myapplication.impl.OuvinteDaMudancaDeData;
import thiago.com.br.myapplication.util.Constantes;

/**
 * Created by Samsung on 19/01/2015.
 */
public class ViagemFragment extends Fragment {

    private DatabaseHelper helper = new DatabaseHelper(getActivity());
    private EditText destino,quantidadePessoas, orcamento;
    private RadioGroup tipoVIagem;
    private Calendar c;
    private Button btDataSaida;
    private Button btDataChegada;
    private Button btSalvarViagem;
    private View view;
    private int ano;
    private int mes;
    private int dia;
    // para verificar o fluxo: salvar nova ou alterar viagem.
    private String id;
    private SQLiteDatabase db;
    private Date dataChegada;
    private Date dataSaida;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inicializaComponentes(inflater);
        // verificação, se é para editar ou criar novo.
        Bundle bundle = this.getArguments();
        if(bundle != null){
            id = bundle.getString(Constantes.VIAGEM_ID);
            if(id != null)prepararEdicao(id);
        }



        return view;
    }

    private View inicializaComponentes(LayoutInflater inflater) {
        view = inflater.inflate(R.layout.nova_viagem, null);
        c = Calendar.getInstance();
        ano = c.get(Calendar.YEAR);
        mes = c.get(Calendar.MONTH);
        dia = c.get(Calendar.DAY_OF_MONTH);
        btDataSaida = (Button) view.findViewById(R.id.btDataSaida);
            btDataSaida.setOnClickListener(new ListenerOfButton());
        btDataChegada = (Button) view.findViewById(R.id.btDataChegada);
            btDataChegada.setOnClickListener(new ListenerOfButton());
        btSalvarViagem = (Button) view.findViewById(R.id.btSalvarViagem);
            btSalvarViagem.setOnClickListener(new ListenerOfButton());
        destino = (EditText) view.findViewById(R.id.destino_nvViagem);
        quantidadePessoas = (EditText) view.findViewById(R.id.quantidadePessoas);
        orcamento = (EditText) view.findViewById(R.id.orcamento);
        tipoVIagem = (RadioGroup) view.findViewById(R.id.tipoViagem);
        helper = new DatabaseHelper(getActivity());

        return view;
    }

    private void prepararEdicao(String id) {
        db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT tipo_viagem,destino,data_chegada, "+
                "data_saida, quantidade_pessoas, orcamento "+
                "FROM viagem WHERE _id = ?", new String[]{id});
        cursor.moveToFirst();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        if(cursor.getInt(0)== Constantes.VIAGEM_LAZER)tipoVIagem.check(R.id.lazer);
        else tipoVIagem.check(R.id.lazer);
        destino.setText(cursor.getString(1));
         dataChegada = new Date(cursor.getLong(2));
         dataSaida = new Date(cursor.getLong(3));
        btDataChegada.setText(dateFormat.format(dataChegada));
        btDataSaida.setText(dateFormat.format(dataSaida));
        quantidadePessoas.setText(cursor.getString(4));
        orcamento.setText(cursor.getString(5));
        cursor.close();
    }

    private ContentValues montaViagem() {
        ContentValues values = new ContentValues();
        values.put("destino", destino.getText().toString());
        values.put("data_chegada", dataChegada.getTime());
        values.put("data_saida", dataSaida.getTime());
        values.put("orcamento", orcamento.getText().toString());
        values.put("quantidade_pessoas",
                quantidadePessoas.getText().toString());
        int tipo = tipoVIagem.getCheckedRadioButtonId();
        if(tipo == R.id.lazer) {
            values.put("tipo_viagem", Constantes.VIAGEM_LAZER);
        } else {
            values.put("tipo_viagem", Constantes.VIAGEM_NEGOCIOS);
        }
        return values;
    }

    public  class ListenerOfButton implements View.OnClickListener,DatePickerDialog.OnDateSetListener {
        private  Date d;
        private View v;

        public ListenerOfButton(){}

        public ListenerOfButton(View v,Date date) {
            this.v = v;
            this.d = date;
        }

        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.btSalvarViagem:
                    db = helper.getWritableDatabase();
                    //verifica se altera ou se salva
                    if (id == null) {
                        long resultado = db.insert(helper.getTableViagem(), null, montaViagem());
                        Toast.makeText(getActivity(), "tudo ok", Toast.LENGTH_SHORT).show();
                    } else {
                        long resultado = db.update(helper.getTableViagem(), montaViagem(), "_id =? ", new String[]{id});
                        Toast.makeText(getActivity(), "tudo caô", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.btDataSaida:
                    new DatePickerDialog(getActivity(),new ListenerOfButton(v,dataSaida),c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH))
                            .show();

                    break;

                case R.id.btDataChegada:

                     new DatePickerDialog(getActivity(),new ListenerOfButton(v,dataChegada),c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH))
                            .show();

                    break;

            }

    }

        @Override
        public void onDateSet(DatePicker dtpkr, int year, int monthOfYear, int dayOfMonth) {
          Button b = (Button)v;
            b.setText(dayOfMonth+"/"+monthOfYear+"/"+year);
           if(v.getId()== R.id.btDataSaida){
               dataSaida = new Date(dtpkr.getYear(),dtpkr.getMonth(),dtpkr.getDayOfMonth());
           }else if(v.getId() == R.id.btDataChegada){
               dataChegada = new Date(year,monthOfYear,dayOfMonth);

           }

        }
    }

    @Override
    public void onDestroy() {
        helper.close();
        super.onDestroy();
    }
}
