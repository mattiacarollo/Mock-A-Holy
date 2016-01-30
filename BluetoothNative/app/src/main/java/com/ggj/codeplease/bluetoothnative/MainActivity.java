package com.ggj.codeplease.bluetoothnative;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.View.*;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Set;
import java.util.UUID;

public class MainActivity extends Activity {

    private static final int REQUEST_ENABLE_BT = 1;
    private static final String TAG_SEND_MESSAGE = "Send message to pair device";

    private Button onBtn;
    private Button offBtn;
    private Button listBtn;
    private Button findBtn;
    private TextView text;
    private BluetoothAdapter myBluetoothAdapter;
    private Set<BluetoothDevice> pairedDevices;
    private android.bluetooth.BluetoothDevice[] m_aPairedDevices;
    private ListView myListView;
    private ArrayAdapter<String> m_btArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // take an instance of BluetoothAdapter - Bluetooth radio
        myBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if(myBluetoothAdapter == null) {
            onBtn.setEnabled(false);
            offBtn.setEnabled(false);
            listBtn.setEnabled(false);
            findBtn.setEnabled(false);
            text.setText("Status: not supported");

            Toast.makeText(getApplicationContext(),"Your device does not support Bluetooth", Toast.LENGTH_LONG).show();
        } else {
            text = (TextView) findViewById(R.id.text);
            onBtn = (Button)findViewById(R.id.turnOn);
            onBtn.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    TurnOnBluetooth(v);
                }
            });

            offBtn = (Button)findViewById(R.id.turnOff);
            offBtn.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    TurnOffBluetooth(v);
                }
            });

            listBtn = (Button)findViewById(R.id.paired);
            listBtn.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    RetriveKnownDeviceList(v);
                }
            });

            findBtn = (Button)findViewById(R.id.search);
            findBtn.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    FindDevices(v);
                }
            });

            myListView = (ListView)findViewById(R.id.listView1);

            // create the arrayAdapter that contains the BTDevices, and set it to the ListView
            m_btArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
            myListView.setAdapter(m_btArrayAdapter);

            myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                    myBluetoothAdapter.cancelDiscovery();
                    final String info = ((TextView) arg1).getText().toString();

                    //get the device address when click the device item
                    String address = info.substring(info.length() - 19);

                    //connect the device when item is click
                    BluetoothDevice connect_device = myBluetoothAdapter.getRemoteDevice(address);

                    String message = "Messaggio inviato";
                    byte[] toSend = message.getBytes();

                    TelephonyManager tManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
                    UUID applicationUUID = UUID.fromString(tManager.getDeviceId());

                    try {
                        BluetoothSocket socket = connect_device.createRfcommSocketToServiceRecord(applicationUUID);
                        socket.connect();

                        OutputStream mmOutStream = socket.getOutputStream();
                        mmOutStream.write(toSend);
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                }
            });//************new_devices_list end

        }
    }

    public void TurnOnBluetooth(View view){
        if (!myBluetoothAdapter.isEnabled()) {
            Intent turnOnIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(turnOnIntent, REQUEST_ENABLE_BT);

            Toast.makeText(getApplicationContext(),"Bluetooth turned on" , Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(getApplicationContext(),"Bluetooth is already on", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        if(requestCode == REQUEST_ENABLE_BT){
            if(myBluetoothAdapter.isEnabled()) {
                text.setText("Status: Enabled");
            } else {
                text.setText("Status: Disabled");
            }
        }
    }

    public void RetriveKnownDeviceList(View view){
        // get paired devices
        pairedDevices = myBluetoothAdapter.getBondedDevices();

        // put it's one to the adapter
        for(BluetoothDevice device : pairedDevices) {
            m_btArrayAdapter.add(device.getName() + "\n" + device.getAddress());
        }

        Toast.makeText(getApplicationContext(),"Show Paired Devices", Toast.LENGTH_SHORT).show();
    }

    final BroadcastReceiver bReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            // When discovery finds a device
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                // Get the BluetoothDevice object from the Intent
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                // add the name and the MAC address of the object to the arrayAdapter
                m_btArrayAdapter.add(device.getName() + "\n" + device.getAddress());
                m_btArrayAdapter.notifyDataSetChanged();
            }
        }
    };

    public void FindDevices(View view) {
        if (myBluetoothAdapter.isDiscovering()) {
            // the button is pressed when it discovers, so cancel the discovery
            myBluetoothAdapter.cancelDiscovery();
        }
        else {
            m_btArrayAdapter.clear();
            myBluetoothAdapter.startDiscovery();

            registerReceiver(bReceiver, new IntentFilter(BluetoothDevice.ACTION_FOUND));
        }
    }

    public void TurnOffBluetooth(View view){
        myBluetoothAdapter.disable();
        text.setText("Status: Disconnected");

        Toast.makeText(getApplicationContext(), "Bluetooth turned off", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        unregisterReceiver(bReceiver);
    }

}