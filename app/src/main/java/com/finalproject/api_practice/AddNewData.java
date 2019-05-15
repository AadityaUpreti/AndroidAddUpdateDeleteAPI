package com.finalproject.api_practice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.finalproject.api_practice.model.EmployeeCUD;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddNewData extends AppCompatActivity {
    EditText txtNewName,txtNewSalary,txtNewAge;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_data);

        txtNewName = findViewById(R.id.txtNewName);
        txtNewSalary = findViewById(R.id.txtNewSalary);
        txtNewAge = findViewById(R.id.txtNewAge);
        btnAdd = findViewById(R.id.btnAdd);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://dummy.restapiexample.com/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final EmpAPI empAPI = retrofit.create(EmpAPI.class);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = txtNewName.getText().toString();
                String salary = txtNewSalary.getText().toString();
                String age = txtNewAge.getText().toString();

                EmployeeCUD employeeCUD = new EmployeeCUD(name,salary,age);


                Call<Void> addNewEmp = empAPI.addNewEmployee(employeeCUD);

                addNewEmp.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Toast.makeText(getApplicationContext(),"Data added successfully",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(AddNewData.this,Choice.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),"Error :"+t.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
