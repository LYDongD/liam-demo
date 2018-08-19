package com.liam.demo.fileIO;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Logger;

/*
 *  FileReader or FileWriter
 *
 *  1 assume string has been encode
 *  2 use buffer to read or write
 *
 */
public class FileUtils {

    private static final Logger logger = Logger.getGlobal();

    //assume that content has benn encode
    public static void stringToFile(String content, String fileName) throws IOException {


        //write to tmp
        File file = new File(fileName);
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(content);
        }
    }


    public static String fileToString(String fileName) throws IOException {

        File file = new File(fileName);

        boolean readALl = true;
        char[] content = new char[(int) file.length()];
        try (FileReader fileReader = new FileReader(fileName)) {
            int length = fileReader.read(content);
            if (length != file.length()) {
                logger.info("fileToString -- read uncompletable");
                readALl = false;
            }
        }

        if (readALl) {
            return new String(content);
        }

        return null;
    }

    public static void main(String args[]) throws IOException{

        String fileName = "/Users/lee/Desktop/a.txt";
        stringToFile("test", fileName);
        String content = fileToString(fileName);
        logger.info("file to string -- " + content);
    }


}
