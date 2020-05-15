package com.example.setprojects;

import androidx.fragment.app.FragmentActivity;

import android.animation.IntEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;

import com.arsy.maps_library.MapRipple;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.setprojects.constants.Constants;
import com.example.setprojects.interfaces.JsonApiHolder;
import com.example.setprojects.model.Projects;
import com.example.setprojects.model.RestApi;
import com.example.setprojects.model.TestClas;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.Dash;
import com.google.android.gms.maps.model.Gap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PatternItem;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
//import com.nostra13.universalimageloader.core.ImageLoader;
import com.squareup.picasso.Picasso;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private static final String TAG = "MapsActivity";
    private Unbinder unbinder;
    private List<Projects> list;
    private GoogleMap mMap;
    private Bitmap bmp;
    Context context;



//    @BindView(R.id.buttonForm)
//    FloatingActionButton buttonForm;
//
//    @BindView(R.id.image)
//    ImageView image;
    @BindView(R.id.ChangeMap)
    Button ChangeMap;
    @BindView(R.id.PreviousMap)
    Button PreviousMap;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        if(unbinder == null){
            unbinder = ButterKnife.bind(this);
        }

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
//        mMap.setOnMarkerClickListener((GoogleMap.OnMarkerClickListener) this);

//        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
//            @Override
//            public boolean onMarkerClick(Marker marker) {
//                if (marker!=null) {
//                    Log.d(TAG, "onMarkerClick: " + marker.getSnippet());
//                if (mMap!=null&&list!=null){
//                    for (int i = 0; i < list.size(); i++) {
//                        if (marker.getPosition().equals(Double.parseDouble(list.get(i).getLat()) + Double.parseDouble(list.get(i).getLng()))) {
////                                    Picasso.get().load(Constants.IMAGE_FILE + list.get(i).getImage_url()).fit().into(image);
////                            Log.d(TAG, "onMarkerClick: "+marker.getPosition()+"  space "+Double.parseDouble(list.get(i).getLat()) + Double.parseDouble(list.get(i).getLng()));
//                        }
//                    }}
//                }
//                return true;}
//        });


    }

@OnClick(R.id.ChangeMap)
public void Map1(){
        mMap.clear();
        getAreaData(mMap);

    }

    private void getAreaData(GoogleMap mMap) {


        JsonApiHolder service = RestApi.getApi();
        Call <List<Projects>> call = service.getDepartment();

        call.enqueue(new Callback<List<Projects>>() {
            //            image loader not working for marker icon
//            ImageLoader imageLoader = ImageLoader.getInstance();
            @Override
            public void onResponse(Call<List<Projects>> call, Response<List<Projects>> response) {
                if(response.isSuccessful()){
//                    bmp =imageLoader.loadImageSync(Constants.IMAGE_FILE+list.get(1).getImage_url());
                    List<Projects> list = response.body();
                    for (int i = 0; i < list.size(); i++) {
//icon not working comments
                        //if (bmp!=null){
//                         bmp =imageLoader.loadImageSync(Constants.IMAGE_FILE+list.get(i).getImage_url());}
//                        Bitmap bitmap=BitmapFactory.decodeFile(Constants.IMAGE_FILE +list.get(i).getImage_url());
                        LatLng projects = new LatLng(Double.parseDouble(list.get(i).getLat()), Double.parseDouble(list.get(i).getLng()));


//                        mMap.addMarker(new MarkerOptions().position(projects).title("PSDP NO: "+list.get(i).getProject_id_psdp()).snippet("Detail: "+list.get(i).getDetail()

                                /*+"\n"+list.get(i).getImage_url())
//                        .icon(BitmapDescriptorFactory.fromBitmap( bitmap)).icon(BitmapDescriptorFactory.fromBitmap(bmp)));*/
//                        MapRipple mapRipple = new MapRipple(mMap, projects,context)
//                                .withNumberOfRipples(3)
//                                .withFillColor(Color.BLUE)
//                                .withStrokeColor(Color.BLACK)
//                                .withStrokewidth(10)      // 10dp
//                                .withDistance(2000)      // 2000 metres radius
//                                .withRippleDuration(12000)    //12000ms
//                                .withTransparency(0.5f);
//
//                        mapRipple.startRippleMapAnimation();

                        for (int rad=100;rad<=1500;rad+=100){
                            Log.d(TAG, "onResponse: "+rad);
                            CircleOptions circleOptions = new CircleOptions();

                            circleOptions.center(projects);


                            // Radius of the circle
                            circleOptions.radius(rad);

                            // Border color of the circle
                            circleOptions.strokeColor(0x11110000);

                            // Fill color of the circle 0x11110000

                            circleOptions.fillColor(Color.rgb(226,230,124));
                            // Border width of the circle
                            circleOptions.strokeWidth(10);


                            // Adding the circle to the GoogleMap
                            mMap.addCircle(circleOptions);
//                        Log.d(TAG, "icon111: "+Constants.IMAGE_FILE+list.get(0).getImage_url());

                        }

                        // draw polyline



                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(projects,10));
//                      Picasso.get().load(Constants.IMAGE_FILE +list.get(0).getImage_url()).fit().into(image);
//                        Log.d(TAG, "onResponse: "+list.get(i).getImage_url());



//                       final MarkerOptions markerOptions = new MarkerOptions();
//                        markerOptions.position(projects);
//                        markerOptions.icon(BitmapDescriptorFactory.fromAsset(list.get(i).getImage_url()));
//

                    }



                }

            }

            @Override
            public void onFailure(Call<List<Projects>> call, Throwable t) {

            }
        });

    }

    @OnClick(R.id.PreviousMap)
public void Map2(){
        getData(mMap);

    }

    public void form(){
        Intent intent = new Intent(this, MainActivity.class);

        startActivity(intent);
        Animatoo.animateShrink(this);
//        Animatoo.animateZoom(context);
//        Animatoo.animateFade(context);
//        Animatoo.animateWindmill(context);
//        Animatoo.animateSpin(context);
//        Animatoo.animateDiagonal(context);
//        Animatoo.animateSplit(context);
//        Animatoo.animateShrink(context);
//        Animatoo.animateCard(context);
//        Animatoo.animateInAndOut(context);
//        Animatoo.animateSwipeLeft(context);
//        Animatoo.animateSwipeRight(context);
//        Animatoo.animateSlideLeft(context);
//        Animatoo.animateSlideRight(context);
//        Animatoo.animateSlideDown(context);
//        Animatoo.animateSlideUp(context);
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

        getData(mMap);



    }

    private void getData(GoogleMap mMap) {
        mMap.clear();
        JsonApiHolder service = RestApi.getApi();
        Call <List<Projects>> call = service.getDepartment();

        call.enqueue(new Callback<List<Projects>>() {
//            image loader not working for marker icon
//            ImageLoader imageLoader = ImageLoader.getInstance();
            @Override
            public void onResponse(Call<List<Projects>> call, Response<List<Projects>> response) {
                if(response.isSuccessful()){
//                    bmp =imageLoader.loadImageSync(Constants.IMAGE_FILE+list.get(1).getImage_url());
                    List<Projects> list = response.body();
                    for (int i = 0; i < list.size(); i++) {
//icon not working comments
                        //if (bmp!=null){
//                         bmp =imageLoader.loadImageSync(Constants.IMAGE_FILE+list.get(i).getImage_url());}
//                        Bitmap bitmap=BitmapFactory.decodeFile(Constants.IMAGE_FILE +list.get(i).getImage_url());
                        LatLng projects = new LatLng(Double.parseDouble(list.get(i).getLat()), Double.parseDouble(list.get(i).getLng()));

                        mMap.addMarker(new MarkerOptions().position(projects).title("PSDP NO: "+list.get(i).getProject_id_psdp()).snippet("Detail: "+list.get(i).getDetail()

                                /*+"\n"+list.get(i).getImage_url())
                        .icon(BitmapDescriptorFactory.fromBitmap( bitmap)).icon(BitmapDescriptorFactory.fromBitmap(bmp)*/));
//                        MapRipple mapRipple = new MapRipple(mMap, projects,context)
//                                .withNumberOfRipples(3)
//                                .withFillColor(Color.BLUE)
//                                .withStrokeColor(Color.BLACK)
//                                .withStrokewidth(10)      // 10dp
//                                .withDistance(2000)      // 2000 metres radius
//                                .withRippleDuration(12000)    //12000ms
//                                .withTransparency(0.5f);
//
//                        mapRipple.startRippleMapAnimation();

                        for (int rad=50;rad<=500;rad+=50) {
                            Log.d(TAG, "onResponse: " + rad);
                            CircleOptions circleOptions = new CircleOptions();

                            circleOptions.center(projects);


                            // Radius of the circle
                            circleOptions.radius(rad);

                            // Border color of the circle
                            circleOptions.strokeColor(Color.RED);

                            // Fill color of the circle 0x11110000

//                            circleOptions.fillColor(0x10000000);


                            // Border width of the circle
circleOptions.strokeWidth(1);

                            // Adding the circle to the GoogleMap
                            mMap.addCircle(circleOptions);
//                        Log.d(TAG, "icon111: "+Constants.IMAGE_FILE+list.get(0).getImage_url());
                        }



                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(projects,15));
//                      Picasso.get().load(Constants.IMAGE_FILE +list.get(0).getImage_url()).fit().into(image);
//                        Log.d(TAG, "onResponse: "+list.get(i).getImage_url());



//                       final MarkerOptions markerOptions = new MarkerOptions();
//                        markerOptions.position(projects);
//                        markerOptions.icon(BitmapDescriptorFactory.fromAsset(list.get(i).getImage_url()));
//

                    }



                }

            }

            @Override
            public void onFailure(Call<List<Projects>> call, Throwable t) {

            }
        });

    }
}
