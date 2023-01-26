package ftmk.bitp3453.Helloclass;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;

import java.io.Serializable;
import java.util.Calendar;

import ftmk.bitp3453.Helloclass.databinding.ActivityFirstBinding;
import ftmk.bitp3453.Helloclass.databinding.ActivityRegistrationBinding;

public class RegistrationActivity extends AppCompatActivity implements Serializable {

    DatePickerDialog datePicker;
    ActivityRegistrationBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistrationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.edtBirthdate.setOnFocusChangeListener(new View.OnFocusChangeListener(){

            @Override
            public void onFocusChange(View view, boolean b) {
                fnInvokeDatePicker();
            }
        });
        binding.edtBirthdate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                fnInvokeDatePicker();
            }
        });

        binding.fabAddUser.setOnClickListener(this::fnAddUser);


    }

    private void fnInvokeDatePicker(){

        final Calendar cldr = Calendar.getInstance();
        int day = cldr.get(Calendar.DAY_OF_MONTH);
        int month = cldr.get(Calendar.MONTH);
        int year = cldr.get(Calendar.YEAR);
        //date picker dialog
        datePicker = new DatePickerDialog(RegistrationActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {
                        binding.edtBirthdate.setText
                                (dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                    }
                },year,month,day);
        datePicker.show();
    }

    private void fnAddUser(View view){

        String fullname = binding.edtFullName.getText().toString();
        String pwd = binding.edtPwd.getText().toString();
        String email = binding.edtEmail.getText().toString();
        String birthdate = binding.edtBirthdate.getText().toString();
        String address = binding.edtAddress.getText().toString();
        String gender = "";

        if(binding.rbMale.isChecked())
            gender = binding.rbMale.getText().toString();
        else if(binding.rbFemale.isChecked())
            gender = binding.rbFemale.getText().toString();

        User user = new User(fullname,pwd,address,email,birthdate,gender);

        Intent intent = new Intent(this,  SecondActivity.class);
        intent.putExtra("objUser", user);
        startActivity(intent);

    }
}