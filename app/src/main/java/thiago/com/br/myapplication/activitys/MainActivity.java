package thiago.com.br.myapplication.activitys;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import thiago.com.br.myapplication.R;

import static android.content.SharedPreferences.Editor;

public class MainActivity extends Activity {

    private EditText email;
    private EditText senha;
    private Button btLogin;
    private static final String MANTER_CONECTADO = "manter-conectado";
    private CheckBox manterConectado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindaLayout();

        final SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        boolean conectado = preferences.getBoolean(MANTER_CONECTADO,false);
        if(conectado){startActivity(new Intent(this, DashBoardActivity.class));}

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuarioInformado = email.getText().toString();
                String SenhaInformada = senha.getText().toString();

                if ("thiagomorais92@live.com".equals(usuarioInformado) && "12345".equals(SenhaInformada)){

                    SharedPreferences preferencias =
                            getPreferences(MODE_PRIVATE);
                    Editor editor = preferences.edit();
                    editor.putBoolean(MANTER_CONECTADO,manterConectado.isChecked());
                    editor.commit();

                    Intent ir = new Intent(MainActivity.this,DashBoardActivity.class);
                    startActivity(ir);
                }else {String mensagemDeErro = getString(R.string.erro_autenticacao);
                    Toast.makeText(MainActivity.this,mensagemDeErro,Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void bindaLayout() {
        email = (EditText) findViewById(R.id.etEmail);
        senha = (EditText) findViewById(R.id.etSenha);
        btLogin = (Button) findViewById(R.id.btLogar);
        manterConectado =(CheckBox) findViewById(R.id.manterConectado);
    }

}
