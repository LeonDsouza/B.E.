/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.crce.sw;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

/**
 *
 * @author Leon
 */
public class CSVReaderUtil {

    String pathOfCSV;

    public CSVReaderUtil(String path) {
        pathOfCSV = path;
    }

    public List<String> readCSV() throws IOException {
        File file = new File(pathOfCSV);
        System.out.println("Reached Here");
        List<String> lines = Files.readAllLines(file.toPath(),
                StandardCharsets.UTF_8);
//        for (String line : lines) {
//            String[] array = line.split(",");
//            System.out.println(array[0]);
//        }
        System.out.println(lines);
        lines.remove(0);
        System.out.println(lines.get(0));
        return lines;
    }

}
