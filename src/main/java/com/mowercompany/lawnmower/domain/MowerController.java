package com.mowercompany.lawnmower.domain;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Stream;

public class MowerController {

    private final ForkJoinPool customThreadPool = new ForkJoinPool(4);

    private final Lawn lawn;
    private final List<Mower> mowers;

    public MowerController(Lawn lawn, List<Mower> mowers) {
        this.lawn = lawn;
        this.mowers = mowers;
    }

    public void mowLawn() {
        customThreadPool.submit(() -> {
            mowers.parallelStream()
                    .forEach(mower -> {
                        while(mower.hasNextMove()) {
                            move(mower);
                        }
                    });
            }).join();
    }

    private synchronized void move(Mower mower) {
        mower.prepareNextMove();
        if (canMowerMoveToNext(mower)) {
            mower.confirmMove();
        }
    }

    boolean canMowerMoveToNext(Mower mower) {
        Position nextPosition = mower.getNextPosition();
        if (!lawn.isIn(nextPosition.getCoordinates())) {
            return false;
        }

        return mowers.stream()
                .filter(aMower -> !aMower.equals(mower))
                .noneMatch(aMower -> aMower.isAtSamePosition(nextPosition));
    }

    public Stream<Mower> getMowers() {
        return mowers.stream();
    }
}
