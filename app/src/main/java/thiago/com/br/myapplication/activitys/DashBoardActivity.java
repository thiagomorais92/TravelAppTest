package thiago.com.br.myapplication.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import thiago.com.br.myapplication.R;
import thiago.com.br.myapplication.adapter.AdapterDalista;
import thiago.com.br.myapplication.model.OpccaoDashBoard;

/**
 * Created by Samsung on 18/01/2015.
 */
public class DashBoardActivity extends ActionBarActivity {

    private DrawerLayout mDrawerLayout;
    private FrameLayout mFrameLayout;
    private ListView mListView;
    private ActionBar mActtionBar;
    private List<OpccaoDashBoard> opcoesDashBoard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_dashboard);

        inicializaComponentes();
        montaOsElementosDaDashboard();
        mListView.setAdapter(new AdapterDalista(this,opcoesDashBoard));
    }

    private void inicializaComponentes() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mFrameLayout = (FrameLayout) findViewById(R.id.fl_content);
        mListView = (ListView) findViewById(R.id.lv_opcoes);
        mActtionBar = getSupportActionBar();
        mActtionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void montaOsElementosDaDashboard() {
        opcoesDashBoard = new ArrayList<OpccaoDashBoard>();
        opcoesDashBoard.add(new OpccaoDashBoard("Nova viagem",R.drawable.nova_viagem));
        opcoesDashBoard.add(new OpccaoDashBoard("Novo gasto",R.drawable.novo_gasto));
        opcoesDashBoard.add(new OpccaoDashBoard("Minhas viagens",R.drawable.minhas_viagens));
        opcoesDashBoard.add(new OpccaoDashBoard("Configurações",R.drawable.configuracoes));
    }

    public void selecionarOpcao(View view){
       switch (view.getId()){
           case R.id.nova_viagem:
               startActivity(new Intent(this, ViagemActivity.class));
            break;
           case R.id.novo_gasto:
               startActivity(new Intent(this,GastoActivity.class));
               break;
           case R.id.minhas_viagens:
               startActivity(new Intent(this,ViagemListActivity.class));
           default: break;
       }
    }
}
