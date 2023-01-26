package ftmk.bitp3453.Helloclass;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import ftmk.bitp3453.Helloclass.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

   /* private ActivityMainBinding binding; //declare -- 1
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        drawerLayout = findViewById(R.id.my_drawer_layout);

        actionBarDrawerToggle = new ActionBarDrawerToggle
                (this, binding.myDrawerLayout, R.string.nav_open, R.string.nav_close);
        actionBarDrawerToggle.syncState();
        binding.myDrawerLayout.addDrawerListener(actionBarDrawerToggle);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView = findViewById(R.id.nav_menu);


        navigationView.setNavigationItemSelectedListener
                (new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Intent intent;
                        switch (item.getItemId()){
                            case R.id.nav_main_activity:
                                intent =  new Intent(getApplicationContext(),MainActivity.class);
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

    public boolean onOptionsItemSelected(MenuItem item){

        if (actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

*/

}

