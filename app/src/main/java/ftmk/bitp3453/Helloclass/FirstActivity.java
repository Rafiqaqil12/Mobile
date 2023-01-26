package ftmk.bitp3453.Helloclass;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import ftmk.bitp3453.Helloclass.databinding.ActivityFirstBinding;
import ftmk.bitp3453.Helloclass.databinding.ActivityMainBinding;

public class FirstActivity extends AppCompatActivity {

    TextView txtvwAge;
    EditText edtName,edtYear;
    Button btnClick;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        txtvwAge = (TextView) findViewById(R.id.txtvwAge);
        edtName = (EditText) findViewById(R.id.edtName);
        edtYear = (EditText) findViewById(R.id.edtYear);

    }

    public void fnGreet(View view){
        String strName = edtName.getText().toString();
        String strYear = edtYear.getText().toString();
        int age, year = Integer.parseInt(strYear);
        age = 2022 - year;
        txtvwAge.setText("Hello and welcome  " + strName + " You are " + age + " years old");

    }

    public void fnThreadActivity(View vw){
        Intent intent = new Intent(this,ThreadedActivity.class);
        String strMsg = ((EditText) findViewById(R.id.edtName)).getText().toString();
        intent.putExtra("varStr1",strMsg);
        startActivity(intent);
    }

    public void fnGoNext(View view){
        Intent intent = new Intent(getApplicationContext(),SecondActivity.class);
        startActivity(intent);
    }

    public void getImage(View vw) {
        Bitmap bitmap = (Bitmap) this.getIntent().getParcelableExtra("data");
        ImageView viewBitmap = (ImageView) findViewById(R.id.imageView);
        viewBitmap.setImageBitmap(bitmap);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }



    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "This happen when app is about to pause"
        ,Toast.LENGTH_SHORT) .show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "This happen when app is about to stop"
                ,Toast.LENGTH_SHORT) .show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "This happen when app is about to destroy"
                ,Toast.LENGTH_SHORT) .show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this, "This happen when app is about to restarting"
                ,Toast.LENGTH_SHORT) .show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "This happen when app is about to resume"
                ,Toast.LENGTH_SHORT) .show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "This happen when app is about to start"
                ,Toast.LENGTH_SHORT) .show();
    }

}