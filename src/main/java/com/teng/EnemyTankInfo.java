package com.teng;

import java.io.Serializable;

public class EnemyTankInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    int index = 0;
    int nums = 0;
    int[] x, y, direct;

    public EnemyTankInfo(int nums) {
        this.nums = nums;
        x = new int[nums];
        y = new int[nums];
        direct = new int[nums];
    }

    public void setInfo(int x, int y, int direct) {
        this.x[index] = x;
        this.y[index] = y;
        this.direct[index] = direct;
        index++;
    }
}