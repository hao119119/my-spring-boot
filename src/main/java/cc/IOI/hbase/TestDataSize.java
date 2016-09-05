/**
 * @(#)TestDataSize.java V1.2.0 16-8-19 
 * Copyright (c) 2014-2016 The inspur Software Foundation.All rights reserved 
 */
package cc.IOI.hbase;

import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.util.Bytes;

/**
 * @author Chen Hao
 * @version V1.2.0 16-8-19
 */
public class TestDataSize {

    public static void main(String[] args){

        byte[]  rowKey = new byte[64];
        byte[]  value1 = new byte[10];
        byte[]  value2 = new byte[300];

        Put singleColumnPut = new Put(rowKey);
        singleColumnPut.addColumn(Bytes.toBytes("t"), Bytes.toBytes("col"), value2);
        System.out.println("single column Put size: "+singleColumnPut.heapSize());


        Put multipleColumnPut = new Put(rowKey);
        for (int i = 0; i<30; i++){
            multipleColumnPut.addColumn(Bytes.toBytes("t"), Bytes.toBytes("col"+i),value1);
        }
        System.out.println("multiple column Put size: "+multipleColumnPut.heapSize());

        Get get = new Get(rowKey);
    }
}
