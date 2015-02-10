package thiago.com.br.myapplication.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.*;
import thiago.com.br.myapplication.R;

import java.util.*;

/**
 * Created by Samsung on 28/01/2015.
 */
public class ViagemListFragment extends Fragment implements AdapterView.OnItemClickListener{
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

/*    private List<Map<String, Object>> viagens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String[] de = {"imagem", "destino", "data", "totalGasto"};
        int[] para = {R.id.tipoViagem, R.id.destino, R.id.data, R.id.totalGasto};
        SimpleAdapter adapter =
                new SimpleAdapter(this, listarViagens(),R.layout.lista_viagem, de, para);

        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);
    }

    private List<Map<String,Object>> listarViagens() {
        viagens = new ArrayList<Map<String,Object>>();

        HashMap<String, Object> item = new HashMap<String, Object>();
        item.put("imagem",R.drawable.negocios);
        item.put("destino","São Paulo");
        item.put("data","02/02/2012 a 04/02/2012");
        item.put("totalGasto","Gasto total R$ 314,98");
        viagens.add(item);

        item = new HashMap<String, Object>();
        item.put("imagem", R.drawable.lazer);
        item.put("destino", "Maceió");
        item.put("data","14/05/2012 a 22/05/2012");
        item.put("totalGasto","Gasto total R$ 25834,67");
        viagens.add(item);

        return viagens;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Map<String,Object> map = viagens.get(position);
        String destino = (String) map.get("destino");

        Toast.makeText(ViagemListFragment.this,"Viagem Selecionada: "+destino,Toast.LENGTH_SHORT).show();
        startActivity(new Intent(ViagemListFragment.this, GastoListFragment.class));
    }*/
}
