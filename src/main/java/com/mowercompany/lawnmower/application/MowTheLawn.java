package com.mowercompany.lawnmower.application;

import com.mowercompany.lawnmower.domain.Lawn;
import com.mowercompany.lawnmower.domain.Mower;

import java.util.List;

public class MowTheLawn {

    public List<Mower> execute(Lawn lawn) {
        lawn.mow();
        return lawn.getMowers();
    }

}
