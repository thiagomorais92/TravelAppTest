package thiago.com.br.myapplication.fragments;

import android.app.ListActivity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Samsung on 28/01/2015.
 */
public class GastoListFragment extends Fragment implements AdapterView.OnItemClickListener {
    private ListView lista;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        lista = new ListView(getActivity());
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, listarGastos());
        lista.setAdapter(adapter);
        return lista;
    }

    private List<String> listarGastos() {
        return Arrays.asList("Sanduíche R$ 19,90",
                "Táxi Aeroporto - Hotel R$ 34,00",
                "Revista R$ 12,00");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        TextView tv = (TextView) view;
        Toast.makeText(getActivity(),"Gasto selecionado foi:"+tv.getText(),Toast.LENGTH_SHORT).show();
    }


}
