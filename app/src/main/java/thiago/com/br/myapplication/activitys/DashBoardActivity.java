package thiago.com.br.myapplication.activitys;


import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import thiago.com.br.myapplication.R;
import thiago.com.br.myapplication.adapter.AdapterDalista;
import thiago.com.br.myapplication.fragments.GastoFragment;
import thiago.com.br.myapplication.fragments.Home;
import thiago.com.br.myapplication.fragments.ViagemFragment;
import thiago.com.br.myapplication.fragments.ViagemListFragment;
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
    private android.support.v4.app.FragmentTransaction ft;
    private OpcaoDashBoard opcaoDashBoard;
    //usado apenas para fechar o drawer e DEPOIS carregar os grafmentus
    private int posicao;

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

        FragmentTransaction fm = getSupportFragmentManager().beginTransaction();
        fm.replace(R.id.fl_content,new Home()).addToBackStack(null).commit();
    }

    private void implementaDrawerToggle() {
        mActBarDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open_drawer,R.string.close_drawer){
            @Override
            public void onDrawerClosed(View drawerView) {
              //  super.onDrawerClosed(drawerView);
                mActionBar.setTitle(opcaoDashBoard.getTitle());
                switch (posicao){
                    case 0:
                        ft = getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.fl_content,new Home()).addToBackStack(null).commit();
                        break;
                    case 1:
                        ft = getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.fl_content,new ViagemFragment()).addToBackStack(null).commit();
                        break;

                    case 2:
                        ft = getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.fl_content, new GastoFragment()).addToBackStack(null).commit();
                        break;
                    case 3:
                        ft = getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.fl_content,new ViagemListFragment()).addToBackStack(null).commit();
                        break;
                    case 4:
                        break;
                    default:
                        break;
                }}

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
        opcaoDashBoard = new OpcaoDashBoard("Home",1);

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

        // atributos usados no metodo do DrawerClosed
        opcaoDashBoard = (OpcaoDashBoard) parent.getItemAtPosition(position);
         posicao = position;
        for(int i =0; i<parent.getCount() ;i++){
            mDrawerLayout.closeDrawer(mListView);
            //busque o evento de verdade no onDrawerClosed.
            // para não travar a animação do drawer.
        }

    }



    @Override
    public void onBackPressed() {
        if(mDrawerLayout.isDrawerOpen(mListView)){
            mDrawerLayout.closeDrawer(mListView);
        }else{
        super.onBackPressed();}
    }
}
