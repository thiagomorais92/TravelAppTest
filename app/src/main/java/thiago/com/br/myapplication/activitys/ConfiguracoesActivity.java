package thiago.com.br.myapplication.activitys;

import android.os.Bundle;
import android.preference.PreferenceActivity;

import thiago.com.br.myapplication.R;

/**
 * Created by Samsung on 14/02/2015.
 */
public class ConfiguracoesActivity  extends PreferenceActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferencias);
    }
}
