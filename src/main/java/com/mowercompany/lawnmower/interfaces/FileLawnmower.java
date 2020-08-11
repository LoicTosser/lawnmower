package com.mowercompany.lawnmower.interfaces;

import java.nio.file.Files;
import java.nio.file.Path;

public class FileLawnmower {

    public void mowLawn(String inputFilePath) {
        if (inputFilePath == null || inputFilePath.isEmpty()) {
            throw new IllegalArgumentException("Input file path can't be null or empty");
        } else if (!Files.exists(Path.of(inputFilePath))) {
            throw new IllegalArgumentException("Input file invalid: " + inputFilePath);
        }
        System.out.println("Mow the lawn with input file " + inputFilePath + " as interface");
    }

}
