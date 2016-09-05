/**
 * @(#)PE.java V1.2.0 16-8-20 
 * Copyright (c) 2014-2016 The inspur Software Foundation.All rights reserved 
 */
package cc.IOI.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.client.coprocessor.Batch;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.nio.ByteBuffer;
import java.util.*;


/**
 * @author Chen Hao
 * @version V1.2.0 16-8-20
 */
public class PE {

    private Random random;
    private static Configuration myConf =  HBaseConfiguration.create();
    private static Connection connection;

    public static final int ROW_LENGTH = 26;

    private static final String string = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private Table table;

    public PE(String tableName) throws IOException {
        random = new Random(System.currentTimeMillis());
        myConf.set("hbase.zookeeper.quorum", "192.168.1.86");
        myConf.set("hbase.zookeeper.property.clientPort", "2181");
        myConf.set("zookeeper.znode.parent", "/hbase-unsecure");
        connection = ConnectionFactory.createConnection(myConf);

        try {
            table = connection.getTable(TableName.valueOf(tableName));
            System.out.println(table);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public void testRandom(){
        int[] list = new int[10];
        for (int i = 0; i<100000; i++ ){
            int j = random.nextInt(10);
            list[j] = ++list[j];
        }

        for (int i=0; i<10; i++){
            System.out.println(i+":"+list[i]/100000.0);
        }

    }

    public long testGet(){
        byte[] key = generateRandomKey();
        try {
            long start = System.nanoTime();
            Result result = table.get(new Get(key));
            long end = System.nanoTime();
            long time = end - start;
            return time/1000000;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public long testRandomMultiGet(int length){
        List<Get> gets = new ArrayList<>(length);
        for (int i=0; i<length; i++){
            byte[] key = generateRandomKey();
            Get get = new Get(key);
            gets.add(get);
        }
        try {
            long start = System.nanoTime();
            Result[] results = table.get(gets);
            long end = System.nanoTime();
            long time = end - start;
            return time/1000000;
//            for (int i=0; i<results.length; i++){
//                System.out.println(results[i].isEmpty());
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;

    }

    public long testPut(int columns, int length) throws IOException {
        byte[] key = generateRandomKey();

        //System.out.println(Bytes.toBytes(value).length);
        Put put = new Put(key);
        for(int i=0; i<columns; i++){
            String value = generateValue(length);
            put.addColumn(Bytes.toBytes("info"), Bytes.toBytes(i + ""), Bytes.toBytes(value));
        }
        try {
            long start = System.nanoTime();
            table.put(put);
            long end = System.nanoTime();
            long time = end - start;
            return time/1000000;
        } catch (InterruptedIOException e) {
            e.printStackTrace();
        } catch (RetriesExhaustedWithDetailsException e) {
            e.printStackTrace();
        }
        return -1;

    }

    public long testMultiPut(int columns, int valueLength, int length) throws IOException {
        List<byte[]> keys = generateSequentialKeys(length);
        List<Put> puts = new ArrayList<>(length);
        for (byte[] key:keys){
            Put put = generatePut(key, columns, valueLength);
            puts.add(put);
        }
        try {
            long start = System.nanoTime();
            table.put(puts);
            long end = System.nanoTime();
            long time = end - start;
            return time/1000000;
        } catch (InterruptedIOException e) {
            e.printStackTrace();
        } catch (RetriesExhaustedWithDetailsException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public long testSequentialPut(int columns, int valueLength, int length) {
        List<byte[]> keys = generateSequentialKeys(length);
        List<Put> puts = new ArrayList<>(length);
        for (byte[] key:keys){
            Put put = generatePut(key, columns, valueLength);
            puts.add(put);
        }
        try {
            long start = System.nanoTime();
            for(Put put: puts){
                table.put(put);
            }
            long end = System.nanoTime();
            long time = end - start;
            return time/1000000;
        } catch (InterruptedIOException e) {
            e.printStackTrace();
        } catch (RetriesExhaustedWithDetailsException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }


    public Map<String, Long> mixTest(int rows, int units) throws IOException {
        int readMark = 0;
        int nextOp = random.nextInt(10);
        int loops = rows/units;
        if(rows%units != 0)
            loops++;
        long getTime = 0;
        long putTime = 0;
        for (int i=0; i<loops; i++){
            if(nextOp == readMark){
                System.out.println("getTest");
                getTime += testRandomMultiGet(units);
            }else{
                System.out.println("putTest");
                putTime += testMultiPut(32, 10, units);
            }
            nextOp = random.nextInt(10);
        }
        Map<String, Long> result = new HashMap<>();
        result.put("getTime", getTime);
        result.put("putTime", putTime);
        return result;

    }

    public Put generatePut(int columns, int length){
        byte[] key = generateRandomKey();
        return generatePut(key, columns, length);
    }

    public Put generatePut(byte[] key, int columns, int length){
        Put put = new Put(key);
        for(int i=0; i<columns; i++){
            String value = generateValue(length);
            put.addColumn(Bytes.toBytes("info"), Bytes.toBytes(i + ""), Bytes.toBytes(value));
        }
        return put;
    }

    public  byte [] format(final int number) {
        byte [] b = new byte[ROW_LENGTH];
        int d = Math.abs(number);
        for (int i = b.length - 1; i >= 0; i--) {
            b[i] = (byte)((d % 10) + '0');
            d /= 10;
        }
        return b;
    }

    public byte[] generateRandomKey(){
        int key = random.nextInt(Integer.MAX_VALUE);
        return format(key);
    }

    public List<byte[]> generateSequentialKeys(int length){
        List<byte[]> result = new ArrayList<>(length);
        int key = random.nextInt(Integer.MAX_VALUE);
        while(key >Integer.MAX_VALUE-length)
            key = random.nextInt();
        for (int i=0; i<length; i++){
            result.add(format(key));
        }
        return result;
    }

    public String generateValue(int length){
        char[] value = new char[length];
        char x = string.charAt(random.nextInt(26));
        for(int i=0; i<length; i++){
            value[i] = x;
        }
        return new String(value);
    }

    public long batchTest() throws IOException, InterruptedException {
        int length = 1000;
        List<Get> gets = new ArrayList<>(length);
        for (int i=0; i<length; i++){
            byte[] key = generateRandomKey();
            Get get = new Get(key);
            gets.add(get);
        }
        Object[] results = new Object[1000];
        long start = System.nanoTime();
        table.batch(gets, results);
        long time = (System.nanoTime() - start)/1000000;
        return time;
    }

    public static void main(String args[]) throws IOException, InterruptedException {
        PE pe = new PE("table32");
        byte[] key =  pe.generateRandomKey();
        //pe.testGet();
        //pe.testRandomMultiGet(100);
        long time = 0;
        for(int i=0; i<10000; i++){
            time += pe.testPut(32, 10);

        }
        System.out.println(time);
//
//        time = pe.testSequentialPut(32, 10, 10000);
//        System.out.println(time);
//
//
//        time = pe.testMultiPut(32, 10, 10000);
//        System.out.println(time);
//
//
////        time = 0;
////        for(int i=0; i<10000; i++){
////            time += pe.testGet();
////        }
////        System.out.println(time);

//        time = pe.batchTest();
//        System.out.println(time);
//        time = pe.testRandomMultiGet(1000);
//        System.out.println(time);
        System.out.println("test");
//
        Map<String, Long> result = pe.mixTest(100000, 1000);
        System.out.println(result.get("getTime"));
        System.out.println(result.get("putTime"));



//        Result resutl = pe.table.get(new Get(key));
//        byte[] value = resutl.getValue(Bytes.toBytes("info"), Bytes.toBytes("0"));
//        System.out.println(new String(value));
//
//        System.out.println(resutl.isEmpty());
        //pe.testRandom();



    }
}
