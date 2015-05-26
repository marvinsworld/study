package com.marvin.study.serial;

import com.marvin.study.serial.domain.Param;

import java.io.*;

/**
 * <strong><br><br></strong>
 *
 * @author gezhiqiang
 * @since 2015-03-17 15:28
 */
public class Serial {


    public static void main(String[] args) throws IOException, ClassNotFoundException {

        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("data"));
        out.writeObject(new Param(2,1));
        out.close();

        ObjectInputStream in = new ObjectInputStream(new FileInputStream("data"));

        Param param = (Param)in.readObject();

        System.out.println(param);

    }

}
