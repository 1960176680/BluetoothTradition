package com.zhouwenguang.hz.bluetoothtradition;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;

import java.io.IOException;
import java.util.UUID;

public class AcceptThread extends Thread {
       private final String uuid="7efa3103-6187-49f6-9ae1-97e722cfe6f2";
       private  BluetoothServerSocket serverSocket;
       private BluetoothAdapter bluetoothAdapter;
       public AcceptThread(){
           bluetoothAdapter=BluetoothAdapter.getDefaultAdapter();
           BluetoothServerSocket tmp = null;
           try {
               bluetoothAdapter.listenUsingRfcommWithServiceRecord("test",UUID.fromString(uuid));
           } catch (IOException e) {
               e.printStackTrace();
           }
           serverSocket=tmp;
       }

    @Override
    public void run() {
        BluetoothSocket socket = null;
        while(true){
            try {
                socket=serverSocket.accept();
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
            if (socket!=null){
                try {
//这将释放服务器套接字及其所有资源，但不会关闭 accept()
//所返回的已连接的 BluetoothSocket。
                    manageConnectedSocket(socket);
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    break;
                }
            }
        }
    }

    private void manageConnectedSocket(BluetoothSocket socket) {





    }
}
