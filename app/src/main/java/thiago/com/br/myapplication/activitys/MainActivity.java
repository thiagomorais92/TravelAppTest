package thiago.com.br.myapplication.activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import thiago.com.br.myapplication.R;

public class MainActivity extends Activity {

    private EditText email;
    private EditText senha;
    private Button btLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = (EditText) findViewById(R.id.etEmail);
        senha = (EditText) findViewById(R.id.etSenha);
        btLogin = (Button) findViewById(R.id.btLogar);

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuarioInformado = email.getText().toString();
                String SenhaInformada = senha.getText().toString();

                if ("thiagomorais92@live.com".equals(usuarioInformado) && "12345".equals(SenhaInformada)){
                    Log.i("if","dentro da condição");
                    Intent ir = new Intent(MainActivity.this,DashBoardActivity.class);
                    startActivity(ir);
                }else {String mensagemDeErro = getString(R.string.erro_autenticacao);
                    Toast.makeText(MainActivity.this,mensagemDeErro,Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
