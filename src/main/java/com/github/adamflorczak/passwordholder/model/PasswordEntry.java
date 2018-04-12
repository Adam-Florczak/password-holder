package com.github.adamflorczak.passwordholder.model;


import java.util.Arrays;

public class PasswordEntry {

    private Integer id;
    private String serviceName;
    private String login;
    private char[] password;

    public PasswordEntry(){

    }

    public PasswordEntry(Integer id, String service, String login, char[] password) {
        this(id, service, login, new String(password));
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

    public static class Builder {

        private Integer id;
        private String service;
        private String login;
        private char[] password;

        private Builder(){}

        public static Builder create(){
            return new Builder();
        }

        public Builder withId(Integer id){
            this.id = id;
            return this;
        }
        public Builder withService (String service){
            this.service = service;
            return this;
        }
        public Builder withLogin(String login){
            this.login = login;
            return this;
        }
        public Builder withPassword(String password){
            this.password = password.toCharArray();
            return this;
        }

        public PasswordEntry build() {
            return new PasswordEntry(id, service, login, password);
        }

    }
}
