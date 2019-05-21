package com.github.brotherlogic.graphplotter;

public class StraightLine extends Line {

    long m;
    long c;

    public StraightLine(long start, long end, int startV, int endV) {
        m =(end-start)  / (endV - startV);
        c = startV * m;
    }

    public int getValue(long timestamp) {
        return (int)(m*timestamp + c);
    }
}
