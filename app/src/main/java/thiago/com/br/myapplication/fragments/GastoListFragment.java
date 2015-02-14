package thiago.com.br.myapplication.fragments;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import thiago.com.br.myapplication.R;

/**
 * Created by Samsung on 28/01/2015.
 */
public class GastoListFragment extends Fragment implements AdapterView.OnItemClickListener {
    private ListView mListView;
    private List<Map<String,Object>> gastos;
    private String dataAnterior = "";

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mListView = new ListView(getActivity());

        String[] de = { "data", "descricao", "valor", "categoria" };
        int[] para = { R.id.data_gasto, R.id.descricao_gasto,
                R.id.valor_gasto, R.id.categoria_gasto};


        SimpleAdapter adapter = new SimpleAdapter(getActivity(),listarGastos(),R.layout.lista_gasto,de,para);
        adapter.setViewBinder(new GastoViewBinder());
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(this);
        registerForContextMenu(mListView);
        return mListView;
    }

    private class GastoViewBinder implements SimpleAdapter.ViewBinder {
        @Override
        public boolean setViewValue(View view, Object data, String textRepresentation) {
            if(view.getId() == R.id.data){
                if(!dataAnterior.equals(data)){
                    TextView textView = (TextView) view;
                    textView.setText(textRepresentation);
                    dataAnterior = textRepresentation;
                    view.setVisibility(View.VISIBLE);
                } else {
                    view.setVisibility(View.GONE);
                }
                return true;
            }
            if(view.getId() == R.id.categoria_gasto){
                Integer id = (Integer) data;
                view.setBackgroundColor(getResources().getColor(id));
                return true;
            }
            if (view.getId() == R.id.barraProgresso) {
                Double valores[] = (Double[]) data;
                ProgressBar progressBar = (ProgressBar) view;
                progressBar.setMax(valores[0].intValue());
                progressBar.setSecondaryProgress(valores[1].intValue());
                progressBar.setProgress(valores[2].intValue());
                return true;
            }
            return false;
        }
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        //super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.gasto_menu_context,menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        if( item.getItemId() == R.id.remover){
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            gastos.remove(info.position);
            mListView.invalidateViews();
            dataAnterior = "";
            //remove do banco de dados
            return true;
        }
        return super.onContextItemSelected(item);
    }



    private List<Map<String, Object>> listarGastos(){
        gastos = new ArrayList<Map<String, Object>>();
        Map<String, Object> item = new HashMap<String, Object>();
        item.put("data", "04/02/2012");
        item.put("descricao", "Diária Hotel");
        item.put("valor", "R$ 260,00");
        item.put("categoria", R.color.categoria_hospedagem);
        gastos.add(item);
// pode adicionar mais informações de viagens
        return gastos;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Map<String,Object> map = gastos.get(position);
        String descricao = (String) map.get("descricao");
        String mensagem = "Gasto selecionada: " + descricao;
        Toast.makeText(getActivity(), mensagem,Toast.LENGTH_SHORT).show();
    }


}
