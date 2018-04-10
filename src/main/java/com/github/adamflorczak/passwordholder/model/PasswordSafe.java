package com.github.adamflorczak.passwordholder.model;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PasswordSafe {



    private Map<Integer, PasswordEntry> entries = new HashMap<>();
    private PasswordEntry pe = new PasswordEntry();


    public char[] getPassword(final String serviceName) {
        return entries.values().stream()
                .filter(pe -> pe.getServiceName().equals(serviceName))
                .findFirst()
                .orElseThrow(RuntimeException::new)
                .getPassword();
    }

    public void addEntries(Integer id, String serviceName, String login, String password){

        if(entries.containsKey(id)){
            throw new RuntimeException("This id is already in the system. Choose different one");
        }

        PasswordEntry pe = new PasswordEntry(id, serviceName, login, password);
        entries.put(id, pe);
        System.out.println("Potwierdzam - dodano");

    }

    public void removeEntries(Integer id){

        if(!entries.containsKey(id)){
            throw new RuntimeException("This id is not in the system. Choose different one");
        }

        entries.remove(id);
    }

    public PasswordEntry getValueFromMap(Integer id){

        PasswordEntry object = entries.get(id);

        return object;
    }

    public void printMap(){

        for (Map.Entry<Integer, PasswordEntry> passwords : entries.entrySet()){
            System.out.println(passwords.getValue());
        }
    }

    public Collection<PasswordEntry> getUserData() {

        return entries.values();
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

    private static String convertCharsArrayToString(char[] password){

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < password.length; i++) {
            sb.append(password[i]);
        }
        return sb.toString();
    }

    public static void copyingPasswordToTheClipboard(char[] toConvert) {

        String converted = convertCharsArrayToString(toConvert);
        StringSelection selection = new StringSelection(converted);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, selection);


    }


}
