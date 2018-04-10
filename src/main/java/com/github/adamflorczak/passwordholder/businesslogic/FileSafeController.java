package com.github.adamflorczak.passwordholder.businesslogic;

import com.github.adamflorczak.passwordholder.model.PasswordEntry;
import com.github.adamflorczak.passwordholder.model.PasswordSafe;
import com.google.gson.Gson;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


public class FileSafeController {

    PasswordSafe passwordSafe;

    public FileSafeController(PasswordSafe passwordSafe) {
        this.passwordSafe = passwordSafe;
    }

    public void saveToFile(PasswordEntry passwordEntry, String fileName) {

        Gson gson = new Gson();
        String passwordEntryJson = gson.toJson(passwordEntry) + "\n";

        File file = new File(fileName);

        try {
            FileUtils.writeStringToFile(file, passwordEntryJson, "UTF-8", true);
        } catch (IOException e) {
            //File not accessible
            e.printStackTrace();
        }
    }

    public void saveToFilePro (String fileName){

        Gson gson = new Gson();

        List<String> gsons = passwordSafe
                .getUserData()
                .stream()
                .map(gson::toJson)
                .collect(Collectors.toList());

        File file = new File(fileName);

        try{
            FileUtils.writeLines(file,gsons);
        }catch(IOException io){
            io.printStackTrace();
        }
    }

    public void readFromFilePrint (String fileToRead) {

        Gson gson = new Gson();
        PasswordEntry passwordEntry = new PasswordEntry();
        try {
            List<PasswordEntry> collect = FileUtils.readLines(new File(fileToRead), "UTF-8")
                    .stream().map(s -> gson.fromJson(s, PasswordEntry.class)).collect(Collectors.toList());
            System.out.println(collect.size());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
