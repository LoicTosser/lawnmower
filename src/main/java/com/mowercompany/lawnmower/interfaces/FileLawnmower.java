package com.mowercompany.lawnmower.interfaces;

import com.mowercompany.lawnmower.application.MowTheLawn;
import com.mowercompany.lawnmower.domain.Lawn;
import com.mowercompany.lawnmower.domain.Mower;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileLawnmower {

    private final MowTheLawn mowTheLawn = new MowTheLawn();

    public void mowLawn(String inputFilePath) {
        if (inputFilePath == null || inputFilePath.isEmpty()) {
            throw new IllegalArgumentException("Input file path can't be null or empty");
        } else if (!Files.exists(Path.of(inputFilePath))) {
            throw new IllegalArgumentException("Input file invalid: " + inputFilePath);
        }

        Lawn lawn = InputFileParser.toLawn(new File(inputFilePath));

        List<Mower> mowers = mowTheLawn.execute(lawn);

        for (Mower mower : mowers) {
            System.out.println(mower.toString());
        }
    }

}
