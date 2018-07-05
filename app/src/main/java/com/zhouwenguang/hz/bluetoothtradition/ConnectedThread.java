package com.zhouwenguang.hz.bluetoothtradition;

import android.bluetooth.BluetoothSocket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ConnectedThread extends Thread {
    private  BluetoothSocket mmSocket;
    private  InputStream mmInStream;
    private  OutputStream mmOutStream;
    public ConnectedThread(BluetoothSocket socket) {
        mmSocket = socket;
        try {
            mmInStream = socket.getInputStream();
            mmOutStream = socket.getOutputStream();
        } catch (IOException e) { }
    }

    public void run() {
        byte[] buffer = new byte[1024];  // buffer store for the stream
        int bytes; // bytes returned from read()

        // Keep listening to the InputStream until an exception occurs
        while (true) {
            try {
                // Read from the InputStream
                bytes = mmInStream.read(buffer);
                // Send the obtained bytes to the UI activity
//                mHandler.obtainMessage(MESSAGE_READ, bytes, -1, buffer)
//                        .sendToTarget();
            } catch (IOException e) {
                break;
            }
        }
    }

    /* Call this from the main activity to send data to the remote device */
    public void write(byte[] bytes) {
        try {
            mmOutStream.write(bytes);
        } catch (IOException e) { }
    }

    /* Call this from the main activity to shutdown the connection */
    public void cancel() {
        try {
            mmSocket.close();
        } catch (IOException e) { }
    }
}
