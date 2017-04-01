package org.Util;

/**
 * 迭代的次数
 * Created by XXH on 2017/4/1.
 */
public enum Iteration {
    ITERATION1(1),
    ITERATION2(2),
    ITERATION3(3);

    private int num;

    Iteration(int num)  {
        this.num = num;
    }

    public int getNum() {
        return num;
    }
}
