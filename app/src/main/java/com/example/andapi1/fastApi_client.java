package com.example.andapi1;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface fastApi_client {
    @GET("/users/{user}/repos")
    Call< List<FastAPIRepo>> repoforUser(@Path("user") String user);
}
