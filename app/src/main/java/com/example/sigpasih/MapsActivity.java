package com.example.sigpasih;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Debug;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.MalformedJsonException;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private String [] nama, alamat, keterangan, gambar, id;
    int jumdata;
    private Double[] latitude,longitude;
    Boolean MarkerD[];
    LatLng latling[];




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private void getlokasi(){
        String url ="https://pasih.000webhostapp.com/pasih.php";
        JsonArrayRequest request = new JsonArrayRequest
                (Request.Method.GET, url,
                        new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        jumdata = response.length();
                        Log.d("Debug", "Parse Json ");
                        latling = new LatLng[jumdata];
                        MarkerD = new Boolean[jumdata];
                        nama = new String[jumdata];
                        alamat = new String[jumdata];
                        keterangan = new String[jumdata];
                        gambar = new String[jumdata];
                        id = new String[jumdata];
                        latitude = new Double[jumdata];
                        longitude = new Double[jumdata];
                        for (int i = 0; i < jumdata; i++) {
                            try {
                                JSONObject data = response.getJSONObject(i);
                                id[i] = data.getString("id");
                                latling[i] = new LatLng(data.getDouble("latitude"), data.getDouble("longitude"));
                                nama[i] = data.getString("nama");
                                alamat[i] = data.getString("alamat");
                                keterangan[i] = data.getString("keterangan");
                                gambar[i] = data.getString("gambar");
                                latitude[i] = data.getDouble("latitude");
                                longitude[i] = data.getDouble("longitude");
                                MarkerD[i] = false;
                                mMap.addMarker(new MarkerOptions()
                                        .position(latling[i])
                                        .snippet(alamat[i])
                                        .snippet(keterangan[i])
                                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.palem))

                                        .title(nama[i]));


                            } catch (JSONException je) {

                            }
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latling[i], 15.5f));
                        }

                        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                            @Override
                            public boolean onMarkerClick(Marker marker) {
                                for (int i = 0;i<jumdata; i++){
                                    if (marker.getTitle().equals(nama[i])) {
                                        if (MarkerD[i]) {
                                            Detail_Activity.id = id[i];
                                            Detail_Activity.nama = nama[i];
                                            Detail_Activity.alamat = alamat[i];
                                            Detail_Activity.keterangan = keterangan[i];
                                            Detail_Activity.gambar = gambar[i];
                                            Detail_Activity.Latitude = latitude[i];
                                            Detail_Activity.Longitude = longitude[i];
                                            Intent pindah = new Intent(MapsActivity.this, Detail_Activity.class);
                                            startActivity(pindah);
                                            MarkerD[i] = false;
                                        } else {
                                            MarkerD[i] = true;
                                            marker.showInfoWindow();
                                            Toast pesan = Toast.makeText(MapsActivity.this, "Mohon Kilik Lagi !", Toast.LENGTH_LONG);
                                            TextView tv = pesan.getView().findViewById(R.id.message);
                                            if (tv != null)
                                                tv.setGravity(Gravity.CENTER);

                                            pesan.show();
                                        }
                                    }else {
                                            MarkerD[i]=false;
                                        }
                                    }
                                return false;
                                }

                        });

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(MapsActivity.this);
                        builder.setTitle("eror");
                        builder.setMessage("Conection Failed");
                        builder.setIcon(android.R.drawable.ic_dialog_alert);
                        builder.setPositiveButton("Reload", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                getlokasi();
                            }
                        });
                        AlertDialog alert = builder.create();
                        alert.show();
                    }
                });

        Volley.newRequestQueue(this).add(request);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        getlokasi();
        mMap.getUiSettings().setZoomControlsEnabled(true);
    }
}
