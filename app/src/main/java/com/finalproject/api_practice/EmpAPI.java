package com.finalproject.api_practice;

import com.finalproject.api_practice.model.Employee;
import com.finalproject.api_practice.model.EmployeeCUD;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface EmpAPI {

    @POST("create")
    Call<Void> addNewEmployee(@Body EmployeeCUD employeeCUD);

    @GET("employees")
    Call<List<Employee>> getEmployee();

    @GET("employee/{id}")
    Call<Employee> getEmployeeById(@Path("id") int id);

    @PUT("update/{id}")
    Call<Void> updateEmployee(@Path("id") int id, @Body EmployeeCUD employeeCUD);

    @DELETE("delete/{id}")
    Call<Void> deleteEmployee(@Path("id") int id);
}
