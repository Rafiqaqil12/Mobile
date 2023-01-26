package ftmk.bitp3453.Helloclass;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.material.navigation.NavigationView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import ftmk.bitp3453.Helloclass.databinding.ActivityThreadedBinding;


public class ThreadedActivity extends AppCompatActivity {

    ImageView imgVwPic,btnUpPic;
    TextView tvGreet;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;

    Executor executor;
    Handler handler;

    Bitmap bitmap = null;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    ActivityThreadedBinding binding;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityThreadedBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        imgVwPic = findViewById(R.id.imgVwPic);
        Intent intent = getIntent();

        String strMsg = intent.getStringExtra("varStr1");
        tvGreet = findViewById(R.id.tv1);
        tvGreet.setText("Welcome to second activity " + strMsg);

        drawerLayout = findViewById(R.id.my_drawer_layout);

        actionBarDrawerToggle = new ActionBarDrawerToggle
                (this, drawerLayout, R.string.nav_open, R.string.nav_close);
        actionBarDrawerToggle.syncState();
        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView = findViewById(R.id.nav_menu);


        executor= Executors.newSingleThreadExecutor();
        handler = new Handler(Looper.getMainLooper());


        binding.btnUpPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = manager.getActiveNetworkInfo();

                if (networkInfo != null && networkInfo.isConnected()) {
                    executor.execute(new Runnable() {
                        public void run() {
                            try {
                                URL ImageURL = new URL("https://ftmk.utem.edu.my/web/wp-content/uploads/2020/02/cropped-Logo-FTMK.png");
                                HttpURLConnection connection = (HttpURLConnection) ImageURL.openConnection();
                                connection.setDoInput(true);
                                connection.connect();
                                InputStream inputStream = connection.getInputStream();
                                BitmapFactory.Options options = new BitmapFactory.Options();
                                options.inPreferredConfig = Bitmap.Config.RGB_565;
                                bitmap = BitmapFactory.decodeStream(inputStream, null, options);

                            } catch (MalformedURLException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            handler.post(new Runnable() {  // this is to update main thread -- UI
                                @Override
                                public void run() {
                                    imgVwPic.setImageBitmap(bitmap);
                                }
                            });
                        }
                    });
                } else {
                    Toast.makeText(getApplicationContext(), "No Network!! Please add data plan or connect to wifi network!", Toast.LENGTH_SHORT).show();
                }
            }
        });









        navigationView.setNavigationItemSelectedListener
                (new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Intent intent;
                        switch (item.getItemId()){
                            case R.id.nav_main_activity:
                                intent =  new Intent(getApplicationContext(),StudentMainActivity.class);
                                startActivity(intent);
                                return true;
                            case R.id.nav_camera_activity:
                                intent =  new Intent(getApplicationContext(),ThreadedActivity.class);
                                startActivity(intent);
                                return true;
                            case R.id.nav_settings:
                                Toast.makeText(getApplicationContext(),
                                        "You navigated to Setting Screen", Toast.LENGTH_SHORT).show();
                                return true;
                            case R.id.nav_logout:
                                Toast.makeText(getApplicationContext(),
                                        "You are logged out! See ya!", Toast.LENGTH_SHORT).show();
                                return true;
                            default:
                                return false;
                        }

                    }
                });





    }





    public void fnTakePic(View vw){



        Runnable run = new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 0);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvGreet.setText(tvGreet.getText().toString() + " ..This is your picture hehehe");


                    }
                });
            }
        };
        Thread thr = new Thread(run);
        thr.start();
    }



    protected void onActivityResult(int requestCode,int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        Bitmap bp = (Bitmap) data.getExtras().get("data");
        imgVwPic.setImageBitmap(bp);

    }


    public boolean onOptionsItemSelected(MenuItem item){

        if (actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }








}