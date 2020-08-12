package com.mowercompany.lawnmower.application;

import com.mowercompany.lawnmower.domain.Lawn;
import com.mowercompany.lawnmower.domain.Mower;
import com.mowercompany.lawnmower.domain.MowerController;

import java.util.List;
import java.util.stream.Stream;

public class MowTheLawn {

    public Stream<Mower> execute(MowTheLawnRequest mowTheLawnRequest) {
        MowerController mowerController = new MowerController(mowTheLawnRequest.lawn, mowTheLawnRequest.mowers);
        mowerController.mowLawn();
        return mowerController.getMowers();
    }

    public static class MowTheLawnRequest {

        private final Lawn lawn;
        private final List<Mower> mowers;


        public MowTheLawnRequest(Lawn lawn, List<Mower> mowers) {
            this.lawn = lawn;
            this.mowers = mowers;
        }

        public Lawn getLawn() {
            return lawn;
        }

        public List<Mower> getMowers() {
            return mowers;
        }
    }

}
