package com.marvinsworld.demo;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:实验堆内存溢出,摘自深入理解java虚拟机
 * VM Args：-Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 *
 * @author Eason
 * @since 15-2-3 下午9:46
 */
public class HeapOOM {

    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        while(true){
            list.add("内存溢出呀,内存溢出呀!");
        }
    }
}
