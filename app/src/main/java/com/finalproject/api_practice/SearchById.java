package com.finalproject.api_practice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.finalproject.api_practice.model.Employee;
import com.finalproject.api_practice.model.EmployeeCUD;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchById extends AppCompatActivity {
EditText txtId,txtName,txtSalary,txtAge;
Button btnSearch,btnUpdate,btnDelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_id);

        txtId = findViewById(R.id.txtId);
        txtName = findViewById(R.id.txtName);
        txtSalary = findViewById(R.id.txtSalary);
        txtAge = findViewById(R.id.txtAge);
        btnSearch = findViewById(R.id.btnSearch);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://dummy.restapiexample.com/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final EmpAPI empAPI = retrofit.create(EmpAPI.class);
        if (getIntent().getExtras() != null){
            txtId.setText(""+getIntent().getIntExtra("id",0));
            searchEmployee(empAPI);
        }

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchEmployee(empAPI);
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = Integer.parseInt(txtId.getText().toString());
                String name = txtName.getText().toString();
                String salary = txtSalary.getText().toString();
                String age = txtAge.getText().toString();

                EmployeeCUD employeeCUD = new EmployeeCUD(name,salary,age);


                Call<Void> updateEmp = empAPI.updateEmployee(id,employeeCUD);

                updateEmp.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Toast.makeText(getApplicationContext(),"Data updated successfully",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(SearchById.this,Choice.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),"Error :"+t.getMessage(),Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = Integer.parseInt(txtId.getText().toString());

                Call<Void> deleteEmp = empAPI.deleteEmployee(id);

                deleteEmp.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Toast.makeText(getApplicationContext(),"Data deleted successfully",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(SearchById.this,Choice.class);
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

private void searchEmployee(EmpAPI empAPI){
    Call<Employee> listCall = empAPI.getEmployeeById(Integer.parseInt(txtId.getText().toString()));

    listCall.enqueue(new Callback<Employee>() {
        @Override
        public void onResponse(Call<Employee> call, Response<Employee> response) {
            if (!response.isSuccessful()){
                Toast.makeText(SearchById.this,"Code: "+response.code(),Toast.LENGTH_SHORT).show();
                return;
            }
            Employee employee = response.body();

            txtName.setText(employee.getEmployee_name());
            txtSalary.setText(employee.getEmployee_salary());
            txtAge.setText(employee.getEmployee_age());
        }

        @Override
        public void onFailure(Call<Employee> call, Throwable t) {
            Toast.makeText(SearchById.this,"Error: "+t.getMessage(),Toast.LENGTH_SHORT).show();
        }
    });
}
}
