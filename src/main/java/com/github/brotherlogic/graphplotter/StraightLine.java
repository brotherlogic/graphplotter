package com.github.brotherlogic.graphplotter;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class StraightLine extends Line {

    double m;
    long c;

    public StraightLine(long start, long end, int startV, int endV) {
        m =   (endV - startV+0.0)/(end-start);
        c = startV - (int)(start * m);
    }

    public int getValue(long timestamp) {
        return (int)(m*timestamp + c);
    }


}
