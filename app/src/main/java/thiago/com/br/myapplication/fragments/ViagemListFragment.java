package thiago.com.br.myapplication.fragments;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import thiago.com.br.myapplication.DAO.DatabaseHelper;
import thiago.com.br.myapplication.R;
import thiago.com.br.myapplication.util.Constantes;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Samsung on 28/01/2015.
 */
public class ViagemListFragment extends Fragment implements AdapterView.OnItemClickListener,DialogInterface.OnClickListener{

    private List<Map<String, Object>> viagens;
    private FragmentTransaction tx;
    private AlertDialog alertDialog;
    private ListView lvListaDeViagens;
    private int viagemSelecionadaID;
    private AlertDialog dialogConfirmacao;
    //about Database
    private DatabaseHelper helper;
    private SimpleDateFormat dateFormat;
    private Double valorLimite;
    SharedPreferences preferencias;
    private String id;
    private int tipoViagem;
    private long dataChegada;
    private long dataSaida;
    private double orcamento;
    private String destino;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         View view = inicializaComponentes(inflater);

        String valor = preferencias.getString("valor_limite","-1");
        valorLimite = Double.valueOf(valor);

        String[] de = {"imagem", "destino", "data", "totalGasto","barraProgresso"};
        int[] para = {R.id.tipoViagem, R.id.destino, R.id.data, R.id.totalGasto,R.id.barraProgresso};
        SimpleAdapter adapter =
                new SimpleAdapter(getActivity(), listarViagens(),R.layout.lista_viagem, de, para);
        adapter.setViewBinder(new SimpleAdapter.ViewBinder() {
            @Override
            public boolean setViewValue(View view, Object data, String textRepresentation) {
                if(view.getId()== R.id.barraProgresso){
                    Double valores[] = (Double[]) data;
                    ProgressBar mProgressBar = (ProgressBar) view;
                    mProgressBar.setMax(valores[0].intValue());
                    mProgressBar.setSecondaryProgress(valores[1].intValue());
                    mProgressBar.setProgress(valores[2].intValue());
                    return true;
                }
                return false;
            }
        });
        lvListaDeViagens.setAdapter(adapter);
        lvListaDeViagens.setOnItemClickListener(this);
        //registerForContextMenu(lvListaDeViagens);
        return view;
    }

    private View inicializaComponentes(LayoutInflater inflater) {
        alertDialog = criaAlertDialog();
        dialogConfirmacao = criaDialogConfirmacao();
        helper = new DatabaseHelper(getActivity());
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        preferencias = PreferenceManager.getDefaultSharedPreferences(getActivity());

        View view = inflater.inflate(R.layout.lista_viagens_fragments, null);
        lvListaDeViagens = (ListView) view.findViewById(R.id.lv_listaDeViagens);
        return view;
    }

    private AlertDialog criaDialogConfirmacao() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.confirmacao_exclusao_viagem);
        builder.setPositiveButton(getString(R.string.sim),this);
        builder.setNegativeButton(getString(R.string.nao),this);
        return builder.create();
    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Map<String,Object> map = viagens.get(position);
        String destino = (String) map.get("destino");
        this.viagemSelecionadaID = position;
        alertDialog.show();
        //tx = getActivity().getSupportFragmentManager().beginTransaction();
        //tx.replace(R.id.fl_content,new GastoListFragment()).addToBackStack(null).commit();
    }



    private List<Map<String,Object>> listarViagens() {
        SQLiteDatabase db = helper.getReadableDatabase();
        String sql = "Select _id, tipo_viagem,destino,data_chegada,data_saida,orcamento from viagem";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();

        viagens = new ArrayList<Map<String,Object>>();

        for(int i =0;i<cursor.getCount();i++){
        Map<String, Object> item = new HashMap<String, Object>();

         id = cursor.getString(0);
         tipoViagem = cursor.getInt(1);
         destino = cursor.getString(2);
         dataChegada = cursor.getLong(3);
         dataSaida = cursor.getLong(4);
         orcamento = cursor.getDouble(5);
            item.put("id",id);
            if(tipoViagem == Constantes.VIAGEM_LAZER)item.put("imagem",R.drawable.lazer);
            else item.put("imagem",R.drawable.negocios);

            item.put("destino",destino);
            Date dataChegadaDate = new Date(dataChegada);
            Date dataSaidaDate = new Date(dataSaida);
            String periodo =  dateFormat.format(dataSaidaDate) +" a "+ dateFormat.format(dataChegadaDate);
            item.put("data",periodo);
            double totalGasto = calculaTotalGasto(db,id);
            item.put("total","Gasto toral R$: "+ totalGasto);
            double alerta = orcamento * valorLimite/ 100;
            Double[] valores = new Double[]{orcamento,alerta,totalGasto};
            item.put("barraProgresso",valores);

            viagens.add(item);
            cursor.moveToNext();
        }
        cursor.close();
        return viagens;
    }

    private double calculaTotalGasto(SQLiteDatabase db, String id) {
        String[] args = {id};
        Cursor cursor = db.rawQuery("SELECT SUM(valor) from gasto where viagem_id = ?", args);
        cursor.moveToFirst();
        double total = cursor.getDouble(0);
        cursor.close();
        return total;
    }


    private AlertDialog criaAlertDialog(){
        final CharSequence[] items = {getString(R.string.editar),
                                      getString(R.string.novo_gasto),
                                      getString(R.string.gastos_realizados),
                                      getString(R.string.remover) };
    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.opcoes);
        builder.setItems(items,this);
        return builder.create();
    }
    private void removerViagem(String id){
        SQLiteDatabase db = helper.getWritableDatabase();
         String where [] = new String[]{ id };
         db.delete("gasto", "viagem_id = ?", where);
         db.delete("viagem", "_id = ?", where);
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
           switch (which){
               case 0:
                   id = (String) viagens.get(viagemSelecionadaID).get("id");
                   ViagemFragment mViagemFrag = new ViagemFragment();
                   Bundle bundle = new Bundle();
                   bundle.putString(Constantes.VIAGEM_ID,id);
                   mViagemFrag.setArguments(bundle);
                   tx = getActivity().getSupportFragmentManager().beginTransaction();
                   tx.replace(R.id.fl_content,mViagemFrag).addToBackStack(null);
                   tx.commit();
                   break;
               case 1:
                   id = (String) viagens.get(viagemSelecionadaID).get("id");
                   GastoFragment mGastoFrag = new GastoFragment();
                   bundle = new Bundle();
                   bundle.putString(Constantes.VIAGEM_ID,id);
                   mGastoFrag.setArguments(bundle);
                   tx = getActivity().getSupportFragmentManager().beginTransaction();
                   tx.replace(R.id.fl_content,mGastoFrag);
                   tx.commit();
                   break;
               case 2:
                   String id = (String) viagens.get(viagemSelecionadaID).get("id");
                   GastoListFragment listGasto = new GastoListFragment();
                   Bundle params = new Bundle();
                   params.putString(Constantes.VIAGEM_ID,id);
                   listGasto.setArguments(params);
                   tx = getActivity().getSupportFragmentManager().beginTransaction();
                   tx.replace(R.id.fl_content,listGasto).addToBackStack(null).commit();
                   break;
               case 3:
                   dialogConfirmacao.show();
                   break;
               case DialogInterface.BUTTON_POSITIVE:
                   id = (String) viagens.get(viagemSelecionadaID).get("id");
                   viagens.remove(viagemSelecionadaID);
                   removerViagem(id);
                   lvListaDeViagens.invalidateViews();
                   break;
               case DialogInterface.BUTTON_NEGATIVE:
                   dialogConfirmacao.dismiss();
                    break;
           }
    }
}
