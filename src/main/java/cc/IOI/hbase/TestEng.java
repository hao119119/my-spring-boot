/**
 * @(#)TestEng.java V1.2.0 16-8-22 
 * Copyright (c) 2014-2016 The inspur Software Foundation.All rights reserved 
 */
package cc.IOI.hbase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @author Chen Hao
 * @version V1.2.0 16-8-22
 */
public class TestEng implements Callable{

    private String tableName;

    private int records;
    private int units;

    public TestEng(String tableName, int records, int units){
        this.tableName = tableName;
        this.records = records;
        this.units = units;

    }

    public static void main(String args[]) throws ExecutionException, InterruptedException {
        String tableName = args[0];
        int threadNum = Integer.parseInt(args[1]);
        int records = Integer.parseInt(args[2]);
        int untis =  Integer.parseInt(args[3]);
        ExecutorService pool = Executors.newFixedThreadPool(threadNum);
        List<Callable> callList = new ArrayList<>(threadNum);
        List<Future> results = new ArrayList<>(threadNum);
        for (int i=0; i< threadNum; i++){
            callList.add(new TestEng(tableName, records, untis));
        }
        for (int i=0; i< threadNum; i++){
            results.add(pool.submit(callList.get(i)));
        }
        for (int i=0; i< threadNum; i++){
            Future<Map> future = results.get(i);
            System.out.println(future.get().get("getTime"));
        }

        pool.shutdown();

    }

    @Override
    public Object call() throws Exception {
        PE pe = new PE(tableName);
        return pe.mixTest(records, units);
    }
}
