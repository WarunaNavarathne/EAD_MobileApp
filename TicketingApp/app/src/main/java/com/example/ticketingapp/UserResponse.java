package com.example.ticketingapp;

import com.google.gson.annotations.SerializedName;

public class UserResponse {
    @SerializedName("isSuccess")
    private boolean isSuccess;

    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private UserData data;

    public boolean isSuccess() {
        return isSuccess;
    }

    public String getMessage() {
        return message;
    }

    public UserData getData() {
        return data;
    }

    public class UserData {
        @SerializedName("id")
        private String id;

        @SerializedName("name")
        private String name;

        @SerializedName("nic")
        private String nic;

        @SerializedName("mail")
        private String mail;

        @SerializedName("phoneNo")
        private String phoneNo;

        @SerializedName("joinDate")
        private String joinDate;

        @SerializedName("password")
        private String password;

        @SerializedName("isActive")
        private boolean isActive;

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getNic() {
            return nic;
        }

        public String getMail() {
            return mail;
        }

        public String getPhoneNo() {
            return phoneNo;
        }

        public boolean isActive() {
            return isActive;
        }

        public String getJoinDate() {
            return joinDate;
        }

        public String getPassword() {
            return password;
        }

    }
}
