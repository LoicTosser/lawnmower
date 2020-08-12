package com.mowercompany.lawnmower.interfaces;

import com.mowercompany.lawnmower.domain.Direction;
import com.mowercompany.lawnmower.domain.Lawn;
import com.mowercompany.lawnmower.domain.MoveType;
import com.mowercompany.lawnmower.domain.Mower;
import com.mowercompany.lawnmower.domain.Position;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import static com.mowercompany.lawnmower.application.MowTheLawn.MowTheLawnRequest;

public class InputFileParser {

    private static final Pattern SEPARATOR_PATTERN = Pattern.compile(" ");

    public static MowTheLawnRequest toLawn(File inputFile) {
        if (inputFile == null) {
            throw new IllegalArgumentException("Input file can't be null");
        }
        try(Scanner scanner = new Scanner(inputFile)) {
            if (!scanner.hasNextLine()) {
                throw new IllegalArgumentException("Input file is empty");
            }
            Position upperRightCornerPosition = mapToPosition(scanner.nextLine());
            List<Mower> mowers = new ArrayList<>();
            while(scanner.hasNextLine()) {
                mowers.add(mapToMower(scanner));
            }
            return new MowTheLawnRequest(new Lawn(upperRightCornerPosition), mowers);
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("Input file " + inputFile + " does not exist");
        }

    }

    private static Position mapToPosition(String positionLine) {
        if (positionLine == null || positionLine.isEmpty()) {
            throw new IllegalArgumentException("First line of input file must not be null or empty");
        }
        String[] lawnData = SEPARATOR_PATTERN.split(positionLine);
        if (lawnData.length != 2) {
            throw new IllegalArgumentException("First line is not valid: " + positionLine);
        }
        try {
            return new Position(Integer.valueOf(lawnData[0]), Integer.valueOf(lawnData[1]));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Error while parsing lawn");
        }
    }

    private static Mower mapToMower(Scanner scanner) {
        Mower.MowerBuilder builder = new Mower.MowerBuilder();
        builder = parsePosition(builder, scanner.nextLine());
        if (!scanner.hasNextLine()) {
            throw new IllegalArgumentException("Missing second line of mower's input data");
        }
        builder = parseMoves(builder, scanner.nextLine());
        return builder.build();
    }

    private static Mower.MowerBuilder parsePosition(Mower.MowerBuilder builder, String positionLine) {
        if (positionLine == null || positionLine.isEmpty()) {
            throw new IllegalArgumentException("Line for mower position can't be null or empty");
        }
        String[] initialPositionData = SEPARATOR_PATTERN.split(positionLine);
        if (initialPositionData.length != 3) {
            throw new IllegalArgumentException("Invalid line: " + positionLine + ". Line for mower position must contain 3 entries");
        }
        try {
            builder = builder.withPosition(new Position(Integer.valueOf(initialPositionData[0]),
                                                        Integer.valueOf(initialPositionData[1])));
        } catch(NumberFormatException e) {
            throw new IllegalArgumentException("Error while parsing mower position", e);
        }

        return builder.withDirection(Direction.valueOf(initialPositionData[2]));
    }

    private static Mower.MowerBuilder parseMoves(Mower.MowerBuilder builder, String movesLine) {
        return builder.withMoves(movesLine.chars().mapToObj(c -> MoveType.valueOf(String.valueOf((char) c))));
    }

}
