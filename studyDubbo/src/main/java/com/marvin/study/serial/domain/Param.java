package com.marvin.study.serial.domain;

import java.io.Serializable;

/**
 * <strong><br><br></strong>
 *
 * @author gezhiqiang
 * @since 2015-03-17 15:24
 */
public class Param implements Serializable {

    private int a;
    private int b;

    public Param() {

    }

    public Param(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }
}
