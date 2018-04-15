package com.github.adamflorczak.passwordholder.businesslogic;

import com.github.adamflorczak.passwordholder.model.PasswordEntry;
import com.github.adamflorczak.passwordholder.model.PasswordSafe;
import com.google.gson.Gson;
import org.apache.commons.io.FileUtils;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class FileSafeController {

    private TextIO textIO = TextIoFactory.getTextIO();
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
            e.printStackTrace();
        }
    }

    public void saveToFilePro (PasswordSafe passwordSafe,File file){

        Gson gson = new Gson();

        List<String> gsons = passwordSafe
                .getUserData()
                .stream()
                .map(gson::toJson)
                .collect(Collectors.toList());
        try{
            FileUtils.writeLines(file,gsons);
        }catch(IOException io){
            io.printStackTrace();
        }
    }

    public void readFromFilePrint (File fileToRead) {

        Gson gson = new Gson();
        List<PasswordEntry> collect = new ArrayList<>();

        try {
            collect = FileUtils.readLines( fileToRead, "UTF-8")
                    .stream()
                    .map(s -> gson.fromJson(s, PasswordEntry.class))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (PasswordEntry passwordEntries : collect) {
            String password = passwordEntries.toString();
            textIO.getTextTerminal().println(password);
        }

    }

}
