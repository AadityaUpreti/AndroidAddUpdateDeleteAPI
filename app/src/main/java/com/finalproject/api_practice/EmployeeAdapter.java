package com.finalproject.api_practice;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.finalproject.api_practice.model.Employee;

import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder> {

    private List<Employee> employeeList;
    private Context context;

    public EmployeeAdapter(List<Employee> employeeList, Context context) {
        this.employeeList = employeeList;
        this.context = context;
    }

    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.sample_row,
                viewGroup,false);
        return new EmployeeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final EmployeeViewHolder employeeViewHolder, int i) {
        Employee employee = employeeList.get(i);
        employeeViewHolder.txtEmployeeId.setText(""+employeeList.get(i).getId());
        employeeViewHolder.txtEmployeeName.setText(employeeList.get(i).getEmployee_name());
        employeeViewHolder.txtEmployeeSalary.setText(employeeList.get(i).getEmployee_salary());
        employeeViewHolder.txtEmployeeAge.setText(employeeList.get(i).getEmployee_age());

        employeeViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = Integer.parseInt(employeeViewHolder.txtEmployeeId.getText().toString());
                Intent intent = new Intent(context,SearchById.class);
                intent.putExtra("id",id);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return employeeList.size();
    }

    public class EmployeeViewHolder extends RecyclerView.ViewHolder{
        TextView txtEmployeeId,txtEmployeeName,txtEmployeeSalary,txtEmployeeAge;
        //Button btnUpdate,btnDelete;
        public EmployeeViewHolder(@NonNull View itemView) {
            super(itemView);
            txtEmployeeId = itemView.findViewById(R.id.txtViewId);
            txtEmployeeName = itemView.findViewById(R.id.txtViewName);
            txtEmployeeSalary = itemView.findViewById(R.id.txtViewSalary);
            txtEmployeeAge = itemView.findViewById(R.id.txtViewAge);
//
//            btnUpdate = itemView.findViewById(R.id.btnUpdate);
//            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}
