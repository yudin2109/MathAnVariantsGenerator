package com.trololesha.mathanvariantsgenerator;

import android.util.Pair;

import java.util.ArrayList;
import java.util.TreeMap;

public final class VariantsGenerator {
    private final int nGroup;
    private final int nStudent;
    private byte[] definingByteArray;

    private int GetCharIndex(int nTask) throws VGException {
        if (nTask < 1 || nTask > 100)
            throw new VGException(VGException.errorCodeType.NON_CORRECT_BOUNDS);
        return (nTask - 1) * 300
                + (this.nGroup - 183) *  35
                + this.nStudent;
    }

    public VariantsGenerator(int ngroup, int nstudent) throws VGException {
        if (ngroup < 183 || ngroup > 189)
            throw new VGException(VGException.errorCodeType.NON_CORRECT_GROUP);
        if (nstudent < 1 || nstudent > 30)
            throw new VGException(VGException.errorCodeType.NON_CORRECT_STUDENT);
        this.nGroup = ngroup;
        this.nStudent = nstudent;
    }

    public void SetGeneratorArray(byte[] str) {
        definingByteArray = str;
    }

    private int GetVariant(int nTask) throws VGException {
        if (nTask < 1 || nTask > 100)
            throw new VGException(VGException.errorCodeType.NON_CORRECT_BOUNDS);
        int charIndex = GetCharIndex(nTask);
        if (charIndex > definingByteArray.length)
            throw  new VGException(VGException.errorCodeType.SHORT_DEFINING_ARRAY);
        return definingByteArray[charIndex - 1];
    }

    public int[] GetVariants(int from, int to) throws VGException {
        if (from > to)
            throw new VGException(VGException.errorCodeType.NON_CORRECT_BOUNDS);
        int[] result = new int[to - from + 1];
        for (int i = from; i <= to; ++i) {
            result[i - from] = GetVariant(i);
        }
        return result;
    }
}
