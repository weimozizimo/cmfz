package com.baizhi.cmfz.util;

import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;

import java.io.IOException;

/**
 * @Description
 * @Author weizimo
 * @Time 2018/7/18 16:42.
 */
public class FastDfs {

    public static StorageClient client = null;

    static{
        try {
            ClientGlobal.init("fdfs_client.conf");
            TrackerClient trackerClient = new TrackerClient();
            try {
                TrackerServer trackerServer = trackerClient.getConnection();
                client = new StorageClient(trackerServer,null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }

    }

}
