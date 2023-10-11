package com.example.ticketingapp.models;

public class LoginRequest {
    private String mail;
    private String password;

    public LoginRequest(String mail, String password) {
        this.mail = mail;
        this.password = password;
    }

    // Getters and setters (or use Lombok for auto-generating them)

    public String getEmail() {
        return mail;
    }

    public void setEmail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
