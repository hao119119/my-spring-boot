/**
 * @(#)TestMultiGet.java V1.2.0 16-8-19 
 * Copyright (c) 2014-2016 The inspur Software Foundation.All rights reserved 
 */
package cc.IOI.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Chen Hao
 * @version V1.2.0 16-8-19
 */
public class TestMultiGet {

    public static void main(String args[]) throws IOException {
        Configuration myConf=  HBaseConfiguration.create();
        //myConf.set("hbase.zookeeper.quorum", "192.168.1.86");
        HTable table = new HTable(myConf, "gc3");
        List<Get> gets = new ArrayList<>();
        for(int i = 0; i<10; i++){
            Get get = new Get(Bytes.toBytes(i+""));
            gets.add(get);
        }
        table.get(gets);
    }
}
