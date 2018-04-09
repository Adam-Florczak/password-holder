package com.github.adamflorczak.passwordholder.model;


import java.util.Arrays;

public class PasswordEntry {

    private int id;

    private String serviceName;
    private String login;
    private char[] password;
    private static int allIds = 1;

    public PasswordEntry() {
    }

    public PasswordEntry(String serviceName, String login, char[] password) {
        this.serviceName = serviceName;
        this.login = login;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getLogin() {
        return login;
    }

    public char[] getPassword() {
        return password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static int getAllIds() {
        return allIds;
    }

    public void setAllIds() {
        this.allIds++;
    }

    @Override
    public String toString() {
        return "PasswordEntry{" +
                "id=" + id +
                ", serviceName='" + serviceName + '\'' +
                ", login='" + login + '\'' +
                ", password=" + Arrays.toString(password) +
                '}';
    }
}
