package sw.edu.ulima.foodtrucker;

import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.io.IOException;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback,GoogleMap.OnMarkerClickListener{

    private GoogleMap mMap;
    @Bind(R.id.toolbar_maps)
    Toolbar toolbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        ButterKnife.bind(this);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    Intent i = new Intent(MapsActivity.this, InicioActivity.class);
                    startActivity(i);
                    MapsActivity.this.finish();


            }
        });

    }

    public void onSearch(View view) {
        EditText location_tf = (EditText) findViewById(R.id.eteAddress);
        String location = location_tf.getText().toString();
        List<Address> addressList = null;

        if (location != null || !location.equals("")) {
            Geocoder geocoder = new Geocoder(this);
            try {
                addressList = geocoder.getFromLocationName(location, 1);

            } catch (IOException e) {
                e.printStackTrace();
            }

            Address address = addressList.get(0);
            LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
            mMap.addMarker(new MarkerOptions().position(latLng).title("Marker"));
            mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(total, 11));
        mMap.setMyLocationEnabled(true);
        mMap.setTrafficEnabled(true);
        mMap.setOnMarkerClickListener(this);

        UiSettings u = mMap.getUiSettings();
        u.setZoomControlsEnabled(true);
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Camiones");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(final List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    for ( ParseObject object: objects){

                        ParseGeoPoint geo = (ParseGeoPoint) object.get("location");
                        mMap.addMarker(new MarkerOptions().position(new LatLng(geo.getLatitude(), geo.getLongitude()))
                                .title(object.getString("nombre"))
                                .snippet(object.getString("nombre"))
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.truck_map)));

                    }
               }
            }
        });
    }

    @Override
    public void onBackPressed() {
        MapsActivity.this.finish();
    }


    @Override
    public boolean onMarkerClick(Marker marker) {
        Intent i=new Intent(MapsActivity.this,FoodTruckerDetails.class);
        i.putExtra("id",marker.getTitle());
        startActivity(i);
        return false;
    }
}
