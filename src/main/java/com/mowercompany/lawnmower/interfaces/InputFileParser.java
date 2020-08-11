package com.mowercompany.lawnmower.interfaces;

import com.mowercompany.lawnmower.domain.Lawn;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Scanner;
import java.util.regex.Pattern;

import static com.mowercompany.lawnmower.application.MowTheLawn.MowTheLawnRequest;

public class InputFileParser {

    private static final Pattern SEPARATOR_PATTERN = Pattern.compile(" ");

    public static MowTheLawnRequest parseFileToRequest(File inputFile) {
        if (inputFile == null) {
            throw new IllegalArgumentException("Input file can't be null");
        }
        try(Scanner scanner = new Scanner(inputFile)) {
            if (!scanner.hasNextLine()) {
                throw new IllegalArgumentException("Input file is empty");
            }
            Lawn lawn = mapToLawn(scanner.nextLine());
            return new MowTheLawnRequest(lawn, Collections.emptySet());
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("Input file " + inputFile + " does not exist");
        }

    }

    private static Lawn mapToLawn(String lawnLine) {
        if (lawnLine == null || lawnLine.isEmpty()) {
            throw new IllegalArgumentException("First line of input file must not be null or empty");
        }
        String[] lawnData = SEPARATOR_PATTERN.split(lawnLine);
        if (lawnData.length != 2) {
            throw new IllegalArgumentException("First line is not valid: " + lawnLine);
        }
        return new Lawn(Integer.valueOf(lawnData[0]), Integer.valueOf(lawnData[1]));
    }

}
