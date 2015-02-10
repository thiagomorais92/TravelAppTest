package thiago.com.br.myapplication.activitys;

import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import thiago.com.br.myapplication.R;
import thiago.com.br.myapplication.adapter.AdapterDalista;
import thiago.com.br.myapplication.fragments.GastoFragment;
import thiago.com.br.myapplication.model.OpcaoDashBoard;

/**
 * Created by Samsung on 18/01/2015.
 */
public class DashBoardActivity extends ActionBarActivity implements ListView.OnItemClickListener {

    private DrawerLayout mDrawerLayout;
    private FrameLayout mFrameLayout;
    private ListView mListView;
    private ActionBar mActionBar;
    private List<OpcaoDashBoard> opcoesDashBoard;
    private ActionBarDrawerToggle mActBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_dashboard);

        inicializaComponentes();
        montaOsElementosDaDashboard();
        implementaDrawerToggle();
        mListView.setAdapter(new AdapterDalista(this,opcoesDashBoard));
        mListView.setOnItemClickListener(this);
        mDrawerLayout.setDrawerListener(mActBarDrawerToggle);
    }

    private void implementaDrawerToggle() {
        mActBarDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open_drawer,R.string.close_drawer){
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                Toast.makeText(DashBoardActivity.this,"fechou",Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onConfigurationChanged(Configuration newConfig) {
                super.onConfigurationChanged(newConfig);
                mActBarDrawerToggle.onConfigurationChanged(newConfig);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mActBarDrawerToggle.onOptionsItemSelected(item)){return true;}
        return super.onOptionsItemSelected(item);
    }

    private void inicializaComponentes() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mFrameLayout = (FrameLayout) findViewById(R.id.fl_content);
        mListView = (ListView) findViewById(R.id.lv_opcoes);
        mActionBar = getSupportActionBar();
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setHomeButtonEnabled(true);

    }



    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mActBarDrawerToggle.syncState();
    }



    private void montaOsElementosDaDashboard() {
        opcoesDashBoard = new ArrayList<OpcaoDashBoard>();
        opcoesDashBoard.add(new OpcaoDashBoard("Home",R.drawable.home_icon));
        opcoesDashBoard.add(new OpcaoDashBoard("Nova viagem",R.drawable.nova_viagem));
        opcoesDashBoard.add(new OpcaoDashBoard("Novo gasto",R.drawable.novo_gasto));
        opcoesDashBoard.add(new OpcaoDashBoard("Minhas viagens",R.drawable.minhas_viagens));
        opcoesDashBoard.add(new OpcaoDashBoard("Configurações",R.drawable.configuracoes));
    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        OpcaoDashBoard opcaoDashBoard = (OpcaoDashBoard) parent.getItemAtPosition(position);

        switch (position){
            case 0:
                mListView.setItemChecked(position,true);
                mActionBar.setTitle(opcaoDashBoard.getTitle());
                mDrawerLayout.closeDrawers();
                break;
            case 1:
                mListView.setItemChecked(position,true);
                mActionBar.setTitle(opcaoDashBoard.getTitle());
                mDrawerLayout.closeDrawers();
                AlertDialog al = new AlertDialog.Builder(DashBoardActivity.this).create();
                al.setTitle("teste");
                al.setIcon(R.drawable.travellogo);
                al.show();
                break;
            case 2:
                android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.fl_content, new GastoFragment()).addToBackStack(null).commit();
                mActionBar.setTitle(opcaoDashBoard.getTitle());
                mDrawerLayout.closeDrawers();
                break;
            case 3:
                mListView.setItemChecked(position,true);
                mActionBar.setTitle(opcaoDashBoard.getTitle());
                mDrawerLayout.closeDrawers();
                break;
            case 4:
                mListView.setItemChecked(position,true);
                mActionBar.setTitle(opcaoDashBoard.getTitle());
                mDrawerLayout.closeDrawers();
                break;
            default:break;
        }

    }
}
