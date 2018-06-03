package com.epam.info.handling.util;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import java.io.*;

public class FileWorker {

    static {
        new DOMConfigurator().doConfigure("resource/log4j.xml",
                LogManager.getLoggerRepository());
    }

    private static final Logger LOG = Logger.getLogger(FileWorker.class);

    private static final String SOURCE_TEXT = "resource\\source_text.txt";
    private static final String SOURCE_PATH = "output_files";

    public String readFile() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(SOURCE_TEXT));
        String fileLine;

        while ((fileLine = bufferedReader.readLine()) != null) {
            stringBuilder.append(fileLine);
            stringBuilder.append("\n");
        }

        bufferedReader.close();

        LOG.info("method readFile successfully completed");

        return stringBuilder.toString();
    }

    public void writeFile(String fileName, String text) throws IOException {
        File directory = new File(SOURCE_PATH);

        if (!directory.exists()) {
            directory.mkdir();
        }

        BufferedWriter bufferedWriter =
                new BufferedWriter(new FileWriter(new File(SOURCE_PATH + "\\" + fileName)));

        bufferedWriter.write(text);
        bufferedWriter.flush();

        LOG.info("method writeFile successfully completed");
    }

}