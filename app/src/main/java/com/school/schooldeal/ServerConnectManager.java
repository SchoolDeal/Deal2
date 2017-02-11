package com.school.schooldeal;

import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * Created by U-nookia on 2017/2/12.
 */

public class ServerConnectManager {
    private ConnectLisenter lisenter;
    private String line,token;

    public ServerConnectManager() {
    }

    public void setServerConnectManager(ConnectLisenter lisenter){
        this.lisenter = lisenter;
    }

    public void getToken(final String id, final String name, final String url){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Log.d("dfsfasdfa","run");
                    Socket socket = new Socket("119.29.58.206",10086);
                    socket.setKeepAlive(true);
                    Log.d("aaaaaaaaaaa","connect success");
                    InputStream input = socket.getInputStream();
                    OutputStream output = socket.getOutputStream();
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output));
                    writer.write(id+"\n"+name+"\n"+url+"\n"+"0\n");
                    writer.flush();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    while (!(line = reader.readLine()).equals("0")){
                        token = line;
                        lisenter.connect(token);
                    }
                    input.close();
                    output.close();
                    writer.close();
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
