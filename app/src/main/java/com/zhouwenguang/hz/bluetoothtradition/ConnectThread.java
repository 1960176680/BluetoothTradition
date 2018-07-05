package com.zhouwenguang.hz.bluetoothtradition;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;

import java.io.IOException;
import java.util.UUID;

/**
 * 连接为客户端
 */
public class ConnectThread extends Thread {
    private final String uuid="7efa3103-6187-49f6-9ae1-97e722cfe6f2";
    private BluetoothDevice bluetoothDevice;
    private BluetoothSocket bluetoothSocket;
    private BluetoothAdapter bluetoothAdapter;
    public ConnectThread(BluetoothDevice device){
        BluetoothSocket tmp = null;
        bluetoothDevice=device;
        try {
            tmp=bluetoothDevice.createRfcommSocketToServiceRecord(UUID.fromString(uuid));
        } catch (IOException e) {
            e.printStackTrace();
        }
        bluetoothSocket=tmp;
    }

    @Override
    public void run() {
        bluetoothAdapter=BluetoothAdapter.getDefaultAdapter();
        bluetoothAdapter.cancelDiscovery();
        try {
            bluetoothSocket.connect();
            manageConnectedSocket(bluetoothSocket);
        } catch (IOException e) {
            try {
                bluetoothSocket.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
            return;
        }
    }

    private void manageConnectedSocket(BluetoothSocket bluetoothSocket) {

    }
}
