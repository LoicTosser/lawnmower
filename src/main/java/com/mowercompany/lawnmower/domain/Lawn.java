package com.mowercompany.lawnmower.domain;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class Lawn {

    private final Position upperRightCorner;

    private final List<Mower> mowers;

    private final ForkJoinPool customThreadPool = new ForkJoinPool(4);

    public Lawn(Position upperRightCorner, List<Mower> mowers) {
        this.upperRightCorner = upperRightCorner;
        this.mowers = Collections.synchronizedList(mowers);
    }

    public List<Mower> getMowers() {
        return mowers;
    }

    public Position getUpperRightCorner() {
        return upperRightCorner;
    }

    public void mow() {
        customThreadPool.submit(() -> mowers.parallelStream().forEach(mower -> mower.mow(this))).join();
    }

    boolean canMowerMoveTo(Mower mower, Position position) {
        if (!isContained(position)) {
            return false;
        }

        return mowers.stream()
                .filter(aMower -> !aMower.equals(mower))
                .noneMatch(aMower -> aMower.isAtSamePosition(mower));
    }

    private boolean isContained(final Position position) {
        return position.getX() >= 0 && position.getX() <= this.upperRightCorner.getX()
                && position.getY() >= 0 && position.getY() <= this.upperRightCorner.getY();
    }
}
