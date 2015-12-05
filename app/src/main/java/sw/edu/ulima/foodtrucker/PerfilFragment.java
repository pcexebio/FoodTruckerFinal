package sw.edu.ulima.foodtrucker;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseUser;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Kevin on 19/11/2015.
 */
public class PerfilFragment extends Fragment {

    @Bind(R.id.butMapa)
    Button butMapa;
    //agregado
    @Bind(R.id.tviUsuario)
    TextView tviUsuario;
    @Bind(R.id.fotoPerfil)
    ImageView img;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_perfil,container,false);
        ButterKnife.bind(this,v);

        butMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity(),MapsActivity.class);
                getActivity().finish();
                startActivity(i);
            }
        });

        final ParseUser currentUser = ParseUser.getCurrentUser();

        if (currentUser != null) {
            tviUsuario.setText(currentUser.getString("name"));
            ParseFile applicantResume = (ParseFile) currentUser.get("foto");
            applicantResume.getDataInBackground(new GetDataCallback() {
                public void done(byte[] data, ParseException e) {
                    if (e == null) {

                        img.setImageBitmap(BitmapFactory.decodeByteArray(data, 0, data.length));

                    } else {
                        // something went wrong

                        Toast t = Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_LONG);
                        t.show();
                    }
                }
            });

        } else {
            // show the signup or login screen

        }

        return v;
    }

}
