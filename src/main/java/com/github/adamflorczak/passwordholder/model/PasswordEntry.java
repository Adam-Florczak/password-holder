package com.github.adamflorczak.passwordholder.model;


import java.util.Arrays;

public class PasswordEntry {

    private Integer id;
    private String serviceName;
    private String login;
    private char[] password;

    public PasswordEntry() {
    }

    public PasswordEntry(Integer id, String serviceName, String login, String password) {

        this.id = id;
        this.serviceName = serviceName;
        this.login = login;
        this.password = password.toCharArray();
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
