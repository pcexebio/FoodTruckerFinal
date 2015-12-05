package sw.edu.ulima.foodtrucker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;

import java.util.Arrays;
import java.util.List;

import Dao.Utils;


public class MainActivity extends Activity implements View.OnClickListener {

    Button registrar, ingresar;
    String nombre, url;
    //agregado
    EditText eteUsuario, etePassword;
    TextView tviMensaje;
    private ImageButton loginButton;
    private TextView info;
    private CallbackManager callbackManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Facebook LogIn
        Parse.enableLocalDatastore(this);
        // Add your initialization code here
        Parse.initialize(this, getResources().getString(R.string.parse_app_id), getResources().getString(R.string.parse_client_key));
        ParseFacebookUtils.initialize(this);
        setContentView(R.layout.activity_main);

        loginButton = (ImageButton) findViewById(R.id.login_button);
        ParseAnalytics.trackAppOpenedInBackground(getIntent());
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                List<String> permissions = Arrays.asList("public_profile", "email");
                ParseFacebookUtils.logInWithReadPermissionsInBackground(MainActivity.this, permissions, new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException err) {
                        if (err == null) {

                            if (user == null) {
                                Log.d("MyApp", "Uh oh. The user cancelled the Facebook login.");

                            } else if (user.isNew()) {
                                Log.d("MyApp", "User signed up and logged in through Facebook!");
                                Utils.parseUser=user;
                                Intent intent = new Intent(MainActivity.this, RegistroActivity.class);
                                startActivity(intent);
                                Utils.user.setFacebook(true);
                                Toast t=Toast.makeText(MainActivity.this,"Login correcto",Toast.LENGTH_LONG);
                                t.show();

                            } else {
                                Log.d("MyApp", "User logged in through Facebook!");
                                Intent intent = new Intent(MainActivity.this, InicioActivity.class);
                                startActivity(intent);
                                Toast t=Toast.makeText(MainActivity.this,"Login de Fb correcto",Toast.LENGTH_LONG);
                                t.show();
                                Utils.user.setFacebook(true);
                            }
                        } else {
                            Log.e("ErrorFB", err.getMessage().toString());
                            Toast t=Toast.makeText(MainActivity.this,err.toString(),Toast.LENGTH_LONG);
                            t.show();
                        }


                    }
                });
            }
        });
        info = (TextView) findViewById(R.id.info);
        //Normal LogIn
        registrar = (Button) findViewById(R.id.butregistrar);
        ingresar = (Button) findViewById(R.id.butingresar);
        //agregado
        eteUsuario = (EditText) findViewById(R.id.eteUsuario);
        etePassword = (EditText) findViewById(R.id.etePassword);
        tviMensaje = (TextView) findViewById(R.id.tviMensaje);

        registrar.setOnClickListener(this);
        ingresar.setOnClickListener(this);

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
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ParseFacebookUtils.onActivityResult(requestCode, resultCode, data);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.butregistrar:
                Intent intent = new Intent(this, RegistroActivity.class);
                startActivity(intent);
                break;
            case R.id.butingresar:
                //agregado
                String usuario = eteUsuario.getText().toString();
                String password = etePassword.getText().toString();
                ParseUser.logInInBackground(usuario, password, new LogInCallback() {
                    public void done(ParseUser user, ParseException e) {
                        if (user != null) {
                            Toast.makeText(MainActivity.this, "Login", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this, InicioActivity.class);
                            startActivity(intent);

                        } else {
                            // Signup failed. Look at the ParseException to see what happened.
                            Toast.makeText(MainActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            default:
                break;
            }
        }
    }

