package thiago.com.br.myapplication.fragments;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import thiago.com.br.myapplication.R;

import java.util.*;

/**
 * Created by Samsung on 28/01/2015.
 */
public class ViagemListFragment extends Fragment implements AdapterView.OnItemClickListener,DialogInterface.OnClickListener{

    private List<Map<String, Object>> viagens;
    private FragmentTransaction tx;
    private AlertDialog alertDialog;
    private ListView lvListaDeViagens;
    private int viagemSelecionada;
    private AlertDialog dialogConfirmacao;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.alertDialog = criaAlertDialog();
        this.dialogConfirmacao = criaDialogConfirmacao();
        View view = inflater.inflate(R.layout.lista_viagens_fragments, null);
        lvListaDeViagens = (ListView) view.findViewById(R.id.lv_listaDeViagens);

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
        registerForContextMenu(lvListaDeViagens);
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
        this.viagemSelecionada = position;
        alertDialog.show();
        //tx = getActivity().getSupportFragmentManager().beginTransaction();
        //tx.replace(R.id.fl_content,new GastoListFragment()).addToBackStack(null).commit();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        //super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.viagens_menu_context,menu);
    }

    private List<Map<String,Object>> listarViagens() {
        viagens = new ArrayList<Map<String,Object>>();

        HashMap<String, Object> item = new HashMap<String, Object>();
        item.put("imagem",R.drawable.negocios);
        item.put("destino","São Paulo");
        item.put("data","02/02/2012 a 04/02/2012");
        item.put("totalGasto","Gasto total R$ 314,98");
        item.put("barraProgresso", new Double[]{ 500.0, 450.0, 314.98});
        viagens.add(item);

        item = new HashMap<String, Object>();
        item.put("imagem", R.drawable.lazer);
        item.put("destino", "Maceió");
        item.put("data","14/05/2012 a 22/05/2012");
        item.put("totalGasto","Gasto total R$ 25834,67");
        item.put("barraProgresso", new Double[]{ 500.0, 450.0, 314.98});
        viagens.add(item);

        return viagens;
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

    @Override
    public void onClick(DialogInterface dialog, int which) {
           switch (which){
               case 0:
                   tx = getActivity().getSupportFragmentManager().beginTransaction();
                   tx.replace(R.id.fl_content,new ViagemFragment());
                   tx.commit();
                   break;
               case 1:
                   tx = getActivity().getSupportFragmentManager().beginTransaction();
                   tx.replace(R.id.fl_content,new GastoFragment());
                   tx.commit();
                   break;
               case 2:
                   tx = getActivity().getSupportFragmentManager().beginTransaction();
                   tx.replace(R.id.fl_content,new GastoListFragment());
                   tx.commit();
                   break;
               case 3:
                   dialogConfirmacao.show();
                   break;
               case DialogInterface.BUTTON_POSITIVE:
                   viagens.remove(this.viagemSelecionada);
                   lvListaDeViagens.invalidateViews();
                   break;
               case DialogInterface.BUTTON_NEGATIVE:
                   dialogConfirmacao.dismiss();
                    break;
           }
    }
}
