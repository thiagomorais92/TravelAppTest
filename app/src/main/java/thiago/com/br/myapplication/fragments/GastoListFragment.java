package thiago.com.br.myapplication.fragments;

import android.app.ListActivity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
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
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listarGastos()));
        ListView lv = this.getListView();


    }


    private List<String> listarGastos() {
        return Arrays.asList("Sanduíche R$ 19,90",
                             "Táxi Aeroporto - Hotel R$ 34,00",
                             "Revista R$ 12,00");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        TextView tv = (TextView) view;
        Toast.makeText(this,"Gasto selecionado foi:"+tv.getText(),Toast.LENGTH_SHORT).show();
    }*/
}
