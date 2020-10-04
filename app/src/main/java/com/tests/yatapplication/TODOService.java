package com.tests.yatapplication;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TODOService {
    @GET("employees")
    Call<GetEmployeeResult> getEmployees();

    @GET("employee")
    Call<GetEmployeeIdResult> getEmployeeById( int id);

    @POST("employees")
    Call<CreateEmployeeResult> createEmployee(@Body CreateEmployeeRequest createEmployeeRequest);
}
//http://dummy.restapiexample.com/employees/1

