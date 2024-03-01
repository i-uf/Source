package com.i_uf;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.nio.file.Files;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.print("INPUT FILE: ");
        JsonObject object = JsonParser.parseReader(new FileReader(new Scanner(System.in).nextLine())).getAsJsonObject();
        System.out.print("INPUT FOLDER: ");
        String directory = new Scanner(System.in).nextLine();
        System.out.print("OUTPUT FOLDER: ");
        String output = new Scanner(System.in).nextLine();
        object.get("objects").getAsJsonObject().entrySet().forEach(entry -> {
            try {
                String hash = entry.getValue().getAsJsonObject().get("hash").getAsString();
                File in = new File(directory, hash.substring(0, 2) + "/" + hash);
                File out = new File(output + "/" + entry.getKey());
                out.getParentFile().mkdirs();
                out.createNewFile();
                Files.copy(in.toPath(), new FileOutputStream(out));
            } catch (IOException e) {
                System.out.println("에러났따!!");
            }
        });
    }
}