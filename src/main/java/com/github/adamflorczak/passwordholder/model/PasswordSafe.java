package com.github.adamflorczak.passwordholder.model;


import com.github.adamflorczak.passwordholder.businesslogic.WrongUserAndServiceException;

import java.util.*;
import java.util.stream.Collectors;

public class PasswordSafe {

    private Integer allIds = 1;

    private Map<String, ArrayList<PasswordEntry>> entries = new HashMap<>();
    private PasswordEntry pe = new PasswordEntry();


    public char[] getPassword(final String serviceName, final String login) {
        return entries.values().stream()
                .filter(pe -> pe.listIterator()
                        .next()
                        .getServiceName().equals(serviceName)
                        && pe.listIterator().next().getLogin()
                        .equals(login)).findFirst()
                        .orElseThrow(RuntimeException::new)
                        .listIterator()
                        .next()
                       .getPassword();
    }



    public void addEntries(Integer id, String serviceName, String login, String password) {
        if (exists(serviceName, login)) {
            throw new WrongUserAndServiceException(login + " user in " + serviceName + " already exist. Try another one.");
        }
        PasswordEntry pe = PasswordEntry.Builder.create().withId(id)
                                                         .withService(serviceName)
                                                         .withLogin(login)
                                                         .withPassword(password)
                                                         .build();

        if (!entries.containsKey(serviceName.toLowerCase())) {
            entries.put(serviceName.toLowerCase(), new ArrayList<PasswordEntry>());
        }
        entries.get(serviceName.toLowerCase()).add(pe);
        System.out.println("User added to the Password Holder");
    }

    public boolean exists(String serviceName, String login) {
        if (!entries.containsKey(serviceName.toLowerCase())) {
            return false;
        } else {
            for (PasswordEntry pe : entries.get(serviceName.toLowerCase())) {
                if (pe.getLogin().equals(login)) {
                    return true;
                }
            }
            return false;
        }
    }


    public void removeEntries(String serviceName, String login) {
        boolean isRemoved = false;
        if (!entries.containsKey(serviceName)) {
            System.err.println("Not found this service. Try another one.");
        } else {
            for (PasswordEntry pe : entries.get(serviceName.toLowerCase())) {
                if (pe.getLogin().equals(login)) {
                    entries.get(serviceName).remove(pe);
                    System.out.println("Removed");
                    isRemoved = true;
                }
            }
            if(!isRemoved) {
                System.err.println("Not found this user. ");
            }
        }
    }

//    public PasswordEntry getValueFromMap(String serviceName) {
//
//        PasswordEntry object = entries.get(serviceName).;
//
//        return object;
//    }

    public void printMap() {

        for (Map.Entry<String, ArrayList<PasswordEntry>> passwords : entries.entrySet()) {
            System.out.println(passwords.getValue());
        }
    }

    public Collection<PasswordEntry> getUserData() {

        return entries.values()
                .stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PasswordSafe that = (PasswordSafe) o;
        return Objects.equals(entries, that.entries) &&
                Objects.equals(pe, that.pe);
    }

    @Override
    public int hashCode() {

        return Objects.hash(entries, pe);
    }

    public String convertCharsArrayToString(char[] password) {

        String pass = new String(password);

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < password.length; i++) {
            sb.append(password[i]);
        }
        return sb.toString();
    }


}
