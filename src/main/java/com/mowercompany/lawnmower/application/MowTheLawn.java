package com.mowercompany.lawnmower.application;

import com.mowercompany.lawnmower.domain.Lawn;
import com.mowercompany.lawnmower.domain.Mower;

import java.util.Collections;
import java.util.Set;

public class MowTheLawn {

    public Set<Mower> execute(Lawn lawn, Set<Mower> mowers) {
        return Collections.emptySet();
    }

    public static class MowTheLawnRequest {

        private final Lawn lawn;
        private final Set<Mower> mowers;

        public MowTheLawnRequest(Lawn lawn, Set<Mower> mowers) {
            this.lawn = lawn;
            this.mowers = mowers;
        }

        public Lawn getLawn() {
            return lawn;
        }

        public Set<Mower> getMowers() {
            return mowers;
        }
    }

}
