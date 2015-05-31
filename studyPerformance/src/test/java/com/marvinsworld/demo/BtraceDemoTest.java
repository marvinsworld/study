package com.marvinsworld.demo;

import com.sun.btrace.annotations.*;
import static com.sun.btrace.BTraceUtils.*;

/**
 * .
 * Created by zhiqiang.ge on 2015/5/31 16:36.
 */
@BTrace
public class BtraceDemoTest {
    @TLS
    private static long startTime = 0;

    @OnMethod(clazz = "com.marvinsworld.demo.BtraceDemo", method = "execute")
    public static void startMethod() {
        startTime = timeMillis();
    }

    @OnMethod(clazz = "com.marvinsworld.demo.BtraceDemo", method = "execute", location = @Location(Kind.RETURN))
    public static void endMethod() {
        println(strcat("the class method execute time=>", str(timeMillis() - startTime)));
        println("-------------------------------------------");
    }

    @OnMethod(clazz = "com.marvinsworld.demo.BtraceDemo", method = "execute", location = @Location(Kind.RETURN))
    public static void traceExecute(@ProbeClassName String name, @ProbeMethodName String method, int sleepTime) {
        println(strcat("the class name=>", name));
        println(strcat("the class method=>", method));
        println(strcat("the class method params=>", str(sleepTime)));

    }
}
