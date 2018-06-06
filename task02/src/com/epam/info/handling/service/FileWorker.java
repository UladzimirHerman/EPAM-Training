package com.epam.info.handling.service;

import org.apache.log4j.Logger;

import java.io.*;

public class FileWorker {

    private static final Logger LOG = Logger.getLogger(FileWorker.class);

    private static final String SOURCE_FILE = "resource\\source_text.txt";
    private static final String OUTPUT_PATH = "output_files";

    public String readFile() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(SOURCE_FILE));
        String fileLine;

        while ((fileLine = bufferedReader.readLine()) != null) {
            stringBuilder.append(fileLine);
            stringBuilder.append("\n");
        }

        bufferedReader.close();

        LOG.info("Method readFile successfully completed");

        return stringBuilder.toString();
    }

    public void writeFile(String fileName, String text) throws IOException {
        File directory = new File(OUTPUT_PATH);

        if (!directory.exists()) {
            directory.mkdir();
        }

        BufferedWriter bufferedWriter =
                new BufferedWriter(new FileWriter(new File(OUTPUT_PATH + "\\" + fileName)));

        bufferedWriter.write(text);
        bufferedWriter.flush();

        LOG.info("Method writeFile successfully completed");
    }

}