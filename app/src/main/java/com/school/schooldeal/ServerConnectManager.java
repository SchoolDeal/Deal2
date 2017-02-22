package com.school.schooldeal;

import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by U-nookia on 2017/2/12.
 */

public class ServerConnectManager {
    private ConnectLisenter lisenter;
    private String line,token;
    private Socket socket;

    public ServerConnectManager() {
    }

    public void setServerConnectManager(ConnectLisenter lisenter){
        this.lisenter = lisenter;
    }

    //服务器119.29.58.206
    //本机内网192.168.1.102
    public void getToken(final String id, final String name, final String url){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Log.d("bbb","run");
                    socket = new Socket("119.29.58.206",10086);
                    socket.setReuseAddress(true);
                    socket.setKeepAlive(true);
                    Log.d("bbb","connect success");

                    //写入数据
                    ObjectOutputStream objectOutput = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
                    ServerConnectBean bean = new ServerConnectBean(id,name,url);
                    objectOutput.writeObject(bean);
                    objectOutput.flush();

                    //读取数据
                    InputStream input = socket.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    while (!(line = reader.readLine()).equals("0")){
                        token = line;
                        Log.d("bbb",token);
                        if (token!=null)lisenter.connect(token);
                    }
                    objectOutput.close();
                    input.close();
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
