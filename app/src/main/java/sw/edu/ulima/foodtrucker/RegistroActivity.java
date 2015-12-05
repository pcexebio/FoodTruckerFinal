package sw.edu.ulima.foodtrucker;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

import org.json.JSONException;
import org.json.JSONObject;

import Dao.BitmapToByteArray;
import Dao.DownloadImageTask;
import Dao.Utils;
import butterknife.Bind;
import butterknife.ButterKnife;

public class RegistroActivity extends AppCompatActivity {
    @Bind(R.id.butRegist)
    Button ok;

    @Bind(R.id.nombre)EditText name;
    @Bind(R.id.mail)EditText email;
    @Bind(R.id.contraseña)EditText psw;
    @Bind(R.id.usuario)EditText user;

    @Bind(R.id.imageView1) ImageView profile_image;
    private static final int PICK_IMAGE = 100;

    private void getFbData() {
        GraphRequest request = GraphRequest.newMeRequest(
                AccessToken.getCurrentAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(
                            JSONObject object,
                            GraphResponse response) {
                        Log.d("FbUserData", object.toString());
                        try {
                            Utils.user.setFbid(object.getString("id"));
                            Utils.user.setNombre(object.getString("name"));

                            Utils.user.setEmail(object.getString("email"));
                            Utils.user.setSexo(object.getString("gender"));
                            Utils.user.setUrlFoto("https://graph.facebook.com/" + Utils.user.getFbid() + "/picture?height=400&width=400");

                            name.setText(Utils.user.getNombre());
                            email.setText(Utils.user.getEmail());

                            new DownloadImageTask(profile_image, true).execute(Utils.user.getUrlFoto());

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,link,email,gender");
        request.setParameters(parameters);
        request.executeAsync();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        ButterKnife.bind(this);

        final Bitmap bitmap = ((BitmapDrawable) profile_image.getDrawable()).getBitmap();
        new BitmapToByteArray().execute(bitmap);



        if (Utils.user.isFacebook()) {
            getFbData();
            name.setText(Utils.user.getNombre());
            email.setText(Utils.user.getEmail());
            profile_image.setImageBitmap(bitmap);
        }
        profile_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String pass = psw.getText().toString();
                if (Utils.parseUser == null) {
                    Utils.parseUser = new ParseUser();
                }
                Utils.parseUser.setUsername(user.getText().toString());
                Utils.parseUser.put("username",user.getText().toString());
                Utils.parseUser.setEmail(email.getText().toString());
                Utils.parseUser.setPassword(pass);
                Utils.parseUser.put("name", name.getText().toString());



                final ParseFile foto = new ParseFile("foto.png", Utils.imageBuffer);
                foto.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        Utils.parseUser.put("foto", foto);
                        if (Utils.user.isFacebook()) {
                            Utils.parseUser.saveInBackground(new SaveCallback() {
                                @Override
                                public void done(ParseException e) {
                                    Utils.parseUser.signUpInBackground(new SignUpCallback() {

                                        @Override
                                        public void done(ParseException e) {
                                            Intent intent = new Intent(RegistroActivity.this, InicioActivity.class);
                                            startActivity(intent);
                                            RegistroActivity.this.finish();
                                            Utils.parseUser = null;
                                        }
                                    });
                                }
                            });
                        } else {
                            Utils.parseUser.signUpInBackground(new SignUpCallback() {

                                @Override
                                public void done(ParseException e) {
                                    Intent intent = new Intent(RegistroActivity.this, InicioActivity.class);
                                    startActivity(intent);
                                    RegistroActivity.this.finish();
                                    Utils.parseUser = null;
                                }
                            });
                        }
                    }
                });


            }
        });

    }

    private void openGallery() {
        Intent gallery =
                new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (Utils.parseUser != null) {
            Utils.parseUser.deleteInBackground();
            Utils.parseUser = null;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
            Uri imageUri = data.getData();
            profile_image.setImageURI(imageUri);
            Bitmap bitmap = ((BitmapDrawable) profile_image.getDrawable()).getBitmap();
            new BitmapToByteArray().execute(bitmap);
        }
    }

}
