package com.zhouwenguang.hz.bluetoothtradition;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    BluetoothAdapter mBluetoothAdapter;
    ArrayAdapter arrayAdapter;
    public static final int REQUEST_ENABLE_BT=0x0001;

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=findViewById(R.id.listView);
        arrayAdapter=new ArrayAdapter(MainActivity.this,android.R.layout.simple_list_item_1);
        listView.setAdapter(arrayAdapter);

        IntentFilter intentFilter=new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(mBroadcastReceiver,intentFilter);
        mBluetoothAdapter=BluetoothAdapter.getDefaultAdapter();
//        判断支不支持蓝牙
        if (mBluetoothAdapter==null){
            Toast.makeText(MainActivity.this,"不支持蓝牙", Toast.LENGTH_SHORT).show();
            return;
        }
//        判断蓝牙是否可用
        if (!mBluetoothAdapter.isEnabled()){
//            两种启用蓝牙的方法
//            mBluetoothAdapter.enable();
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }
        if (mBluetoothAdapter.isEnabled()){
            mBluetoothAdapter.startDiscovery();
        }
    }
    public final BroadcastReceiver mBroadcastReceiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
                String action=intent.getAction();
                if (BluetoothDevice.ACTION_FOUND.equals(action)){
                    BluetoothDevice bluetoothDevice=intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                    arrayAdapter.add(bluetoothDevice.getName() + "\n" + bluetoothDevice.getAddress());
                    arrayAdapter.notifyDataSetChanged();
                }
        }
    };
}
