package ftmk.bitp3453.Helloclass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import ftmk.bitp3453.Helloclass.databinding.ActivityRegistrationBinding;
import ftmk.bitp3453.Helloclass.databinding.ActivitySecond2Binding;


public class SecondActivity extends AppCompatActivity {

    ActivitySecond2Binding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second2);

        binding = ActivitySecond2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();

        User user = (User)intent.getSerializableExtra("objUser");

        binding.edtTextName.setText(user.getFullname());
        binding.edtTextPass.setText(user.getPwd());
        binding.edtTextEmail.setText(user.getEmail());
        binding.edtTextDate.setText(user.getBirthdate());
        binding.edtTextAddress.setText(user.getAddress());



    }
}