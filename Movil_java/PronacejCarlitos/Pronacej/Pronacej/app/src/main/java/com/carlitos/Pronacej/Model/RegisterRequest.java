package com.carlitos.Pronacej.Model;

import androidx.annotation.StringRes;

import com.google.gson.annotations.SerializedName;

public class RegisterRequest {

    @SerializedName("typeUserId")
    Integer typeUserId;
    @SerializedName("name")
    String name;
    @SerializedName("lastName")
    String lastName;
    @SerializedName("email")
    String email;
    @SerializedName("password")
    String password;

    public RegisterRequest() {
    }

    public RegisterRequest(Integer typeUserId, String name, String lastName, String email, String password) {
        this.typeUserId = typeUserId;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public Integer getTypeUserId() {
        return typeUserId;
    }

    public void setTypeUserId(Integer typeUserId) {
        this.typeUserId = typeUserId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
