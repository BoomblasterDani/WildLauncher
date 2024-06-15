package com.example.wildlauncher;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadProperties {
    public ReadProperties () {

    }

    public static String readFromFile(File filePath, String startString, String endString) {
        String result = "Not set";
        StringBuilder resultStringBuilder = new StringBuilder();
        try {
            File myObj = filePath;
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                //System.out.println(data);
                resultStringBuilder.append(data);
            }
            String sBResult = resultStringBuilder.toString();
            result = sBResult.substring(sBResult.lastIndexOf(startString), sBResult.indexOf(endString)).replaceAll(startString, "");
            System.out.println("lhh");
            myReader.close();
        } catch (FileNotFoundException e) {
            //System.out.println("An error occurred.");
            //e.printStackTrace();
        }

        return result;
    }
}
