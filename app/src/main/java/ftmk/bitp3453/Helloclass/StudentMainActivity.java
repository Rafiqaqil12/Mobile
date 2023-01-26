package ftmk.bitp3453.Helloclass;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import ftmk.bitp3453.Helloclass.databinding.ActivityStudentMainBinding;

public class StudentMainActivity extends AppCompatActivity {

    private ActivityStudentMainBinding binding;
    private Student student;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private List<Student> students;
    private StudentAdapter adapter;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private DbStudent dbStudent;
    private DatePickerDialog datePicker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityStudentMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        binding.fabAdd.setOnClickListener(this:: fnAdd );


        drawerLayout = findViewById(R.id.my_drawer_layout);


        binding.edtBirthdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);

                int mHour = cldr.get(Calendar.HOUR_OF_DAY);
                int mMinute = cldr.get(Calendar.MINUTE);
                String strDay ="";
                // date picker dialog
                datePicker = new DatePickerDialog(StudentMainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                                binding.edtBirthdate.setText(year + "/" + (monthOfYear + 1) + "/" + dayOfMonth);
                            }
                        }, year, month, day);
                datePicker.show();
            }
        });


        dbStudent = new DbStudent(this);
        students = dbStudent.fnGetAllExpenses();
        adapter = new StudentAdapter(getLayoutInflater(),students);

        new  ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT ) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                deleteStudent(students.get(viewHolder.getAdapterPosition()));

            }
        }).attachToRecyclerView(binding.rcvStud);

        binding.rcvStud.setAdapter(adapter);
        binding.rcvStud.setLayoutManager(new LinearLayoutManager(this));

        actionBarDrawerToggle = new ActionBarDrawerToggle
                (this, drawerLayout, R.string.nav_open, R.string.nav_close);
        actionBarDrawerToggle.syncState();
        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView = findViewById(R.id.nav_menu);

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


    private void fnAdd(View view) {
        String fullname = binding.edtFullName.getText().toString();
        String studNo = binding.edtStudNum.getText().toString();
        String email = binding.edtEmail.getText().toString();
        String birth = binding.edtBirthdate.getText().toString();
        String gender = "";
        String state = binding.spnState.getSelectedItem().toString();

        if (binding.rbMale.isChecked())
            gender = binding.rbMale.getText().toString();
        else if (binding.rbFemale.isChecked())
            gender = binding.rbFemale.getText().toString();

        student = new Student(fullname, studNo, email, gender, birth, state);

        students.add(student);
        adapter.notifyItemInserted(students.size());

        String strURL = "http://192.168.93.178/RESTAPI/rest_api.php";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, strURL,
                new Response.Listener<String>(){

            public void onResponse(String response) {
                JSONObject jsonObject = null;
                Log.e("error:", response);
                try {
                    jsonObject = new JSONObject(response);
                    Toast.makeText(getApplicationContext(), "Respond from server " +
                            jsonObject.getString("respond"), Toast.LENGTH_SHORT).show();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                Log.e("error:", error.toString());
            }
        })
        {
            protected Map<String, String> getParams() throws AuthFailureError{
                String fullname = binding.edtFullName.getText().toString();
                String studNo = binding.edtStudNum.getText().toString();
                String email = binding.edtEmail.getText().toString();
                String birth = binding.edtBirthdate.getText().toString();
                String gender = "";
                String state = binding.spnState.getSelectedItem().toString();

                if (binding.rbMale.isChecked())
                    gender = binding.rbMale.getText().toString();
                else if (binding.rbFemale.isChecked())
                    gender = binding.rbFemale.getText().toString();

                Map<String, String> params = new HashMap<>();
                params.put("selectFn", "fnSaveData");
                params.put("studName", fullname);
                params.put("studGender", gender);
                params.put("studEmail" , email);
                params.put("studDob", birth);
                params.put("studNo", studNo);
                params.put("studState", state);
                return params;
            }
        };
        requestQueue.add(stringRequest);
        dbStudent = new DbStudent(this);
        dbStudent.fnInsertExpense(student);
    }



    private void deleteStudent(Student student){
        students.remove(student);
        adapter.notifyDataSetChanged();


    }



    public boolean onOptionsItemSelected(MenuItem item){

        if (actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }






}