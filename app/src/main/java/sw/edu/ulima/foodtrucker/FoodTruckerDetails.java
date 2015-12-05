package sw.edu.ulima.foodtrucker;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class FoodTruckerDetails extends Activity implements View.OnClickListener{

    Button butMenu;
    @Bind(R.id.tviNombre)
    TextView nombre;
    @Bind(R.id.tviDireccion)
    TextView dir;
    @Bind(R.id.tviHorario)
    TextView horario;
    @Bind(R.id.tviTitulo)
    TextView titulo;
    String next="";
    @Bind(R.id.image)
    ImageView img;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodtrucker_details);
        ButterKnife.bind(this);

        butMenu=(Button)findViewById(R.id.butMenu);
        butMenu.setOnClickListener(this);
        Intent i=getIntent();
        String n=i.getStringExtra("id");
        Toast.makeText(this, n, Toast.LENGTH_SHORT).show();
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Camiones");
        query.whereEqualTo("nombre", n);

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                for (final ParseObject object : objects) {
                    ParseFile file = (ParseFile)object.get("foto");
                    file.getDataInBackground(new GetDataCallback() {
                        @Override
                        public void done(byte[] data, ParseException e) {

                            img.setImageBitmap(BitmapFactory.decodeByteArray(data, 0, data.length));

                        }
                    });
                            next = object.getString("nombre");
                    nombre.setText(object.getString("nombre"));
                    titulo.setText(object.getString("tipo"));
                    horario.setText(object.getString("horario"));
                    dir.setText(object.getString("direccion"));
                }
            }
        });

    }

    @Override
    public void onClick(View view) {

        Intent intent;
        if (next.equals("Lima Sabrosa")) {

            intent = new Intent(this, MenuLimaSabrosaActivity.class);
            startActivity(intent);
        }else if(next.equals("Hit'n Run")){
            intent = new Intent(this, MenuHitnRunActivity.class);
            startActivity(intent);
        }else if(next.equals("El Gringo")){
            intent = new Intent(this, MenuElGringoActivity.class);
            startActivity(intent);
        }

    }
}
