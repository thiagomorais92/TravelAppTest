package thiago.com.br.myapplication.activitys;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
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
public class ViagemListActivity extends ListActivity implements AdapterView.OnItemClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listarViagens()));
        ListView listView = getListView();
        listView.setOnItemClickListener(this);
    }

    private List<String> listarViagens() {
        return Arrays.asList("São Paulo","Bonito","Maceió");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        TextView tvView = (TextView) view;
        Toast.makeText(ViagemListActivity.this,"Viagem Selecionada: "+tvView.getText(),Toast.LENGTH_SHORT).show();
        startActivity(new Intent(ViagemListActivity.this, GastoListActivity.class));
    }
}
