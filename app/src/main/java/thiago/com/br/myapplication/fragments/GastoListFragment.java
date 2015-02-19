package thiago.com.br.myapplication.fragments;

import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import thiago.com.br.myapplication.DAO.DatabaseHelper;
import thiago.com.br.myapplication.R;
import thiago.com.br.myapplication.model.Gasto;
import thiago.com.br.myapplication.util.Constantes;

/**
 * Created by Samsung on 28/01/2015.
 */
public class GastoListFragment extends Fragment implements AdapterView.OnItemClickListener {
    private ListView mListView;
    private List<Map<String,Object>> gastos;
    private String dataAnterior = "";
    private DatabaseHelper helper;
    private String id_param;
    private static final String[] de = { "data", "descricao", "valor", "categoria" };
    private static final int[] para = { R.id.data_gasto, R.id.descricao_gasto,
            R.id.valor_gasto, R.id.categoria_gasto};
    private SimpleDateFormat sdf;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mListView = inicializaComponentes();



        SimpleAdapter adapter = new SimpleAdapter(getActivity(),listarGastos(),R.layout.lista_gasto,de,para);
        adapter.setViewBinder(new GastoViewBinder());
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(this);
       // registerForContextMenu(mListView);
        return mListView;
    }

    private ListView inicializaComponentes() {
        Bundle bundle = getArguments();
            if(bundle!=null)
                id_param = bundle.getString(Constantes.VIAGEM_ID);
                if(id_param != null)

        mListView  = new ListView(getActivity());
        helper = new DatabaseHelper(getActivity());
        sdf = new SimpleDateFormat("dd/MM/yyyy");

        return mListView;
    }

    private class GastoViewBinder implements SimpleAdapter.ViewBinder {
        @Override
        public boolean setViewValue(View view, Object data, String textRepresentation) {
            if(view.getId() == R.id.data_gasto){
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
                String id =  data.toString();
               //view.setBackgroundColor(getResources().getColor());
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
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT categoria, data, valor,descricao,local,viagem_id FROM gasto where viagem_id = ?", new String[]{id_param});
       cursor.moveToFirst();
        for(int i = 0;i< cursor.getCount();i++){
        Map<String, Object> item = new HashMap<String, Object>();

            String categoria = cursor.getString(0);
            Date data = new Date(cursor.getLong(1));
            String descricao = cursor.getString(2);
            Double valor = cursor.getDouble(3);



        item.put("descricao", descricao);
        item.put("valor", valor);
        item.put("categoria", categoria);

            item.put("data", sdf.format(data));
            gastos.add(item);
        cursor.moveToNext();
        }
// pode adicionar mais informações de viagens
        return gastos;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }


}
