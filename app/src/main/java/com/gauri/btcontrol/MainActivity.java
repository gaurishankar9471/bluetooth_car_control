package com.gauri.btcontrol;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private CardView mForward, mBackward, mLeft, mRight;
    private Button mBTConnect;
    private String command;

    private boolean bluetooth_connected = false;

    private final String DEVICE_ADDRESS = "98:D3:35:70:ED:59";
    //MAC Address of Bluetooth Module
    private final UUID PORT_UUID = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");


    private OutputStream outputStream;
    private InputStream inputStream;
    private BluetoothDevice device;
    private BluetoothSocket socket;

    private boolean btnActive = false;

    private Toolbar mToolbar;

    private TextView mConnectionStatusTxt, mBTName, mBTAddress;



    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = findViewById(R.id.main_activity_tb);
        setSupportActionBar(mToolbar);


        mForward =  findViewById(R.id.forward);
        mBackward = findViewById(R.id.backward);
        mLeft =  findViewById(R.id.left);
        mRight = findViewById(R.id.right);
        mBTConnect = findViewById(R.id.bt_connect);
        mConnectionStatusTxt = findViewById(R.id.activity_main_status_txt);
        mBTAddress = findViewById(R.id.activity_main_bt_add__txt);
        mBTName = findViewById(R.id.activity_main_bt_name_txt);



        mForward.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (bluetooth_connected){
                    if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {

                        btnActive = true;
                        mForward.setCardBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.colorActive));

                        command = "F";

                    try {

                        outputStream.write(command.getBytes());

                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                    } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) //MotionEvent.ACTION_UP is when you release a button
                    {

                        command = "S";
                        mForward.setCardBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.colorDefault));


                    try {
                        outputStream.write(command.getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                    }


                }
                else {
                    Toast.makeText(getApplicationContext(),"Please Connect to Bluetooth Device First...",Toast.LENGTH_LONG).show();
                }

                return false;
            }
        });



        mBackward.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (bluetooth_connected){

                    if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {

                        command = "B";
                        mBackward.setCardBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.colorActive));

                    try {

                        outputStream.write(command.getBytes());

                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                    } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) //MotionEvent.ACTION_UP is when you release a button
                    {
                        command = "S";
                        mBackward.setCardBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.colorDefault));

                    try {
                        outputStream.write(command.getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                    }


                }
                else {
                    Toast.makeText(getApplicationContext(),"Please Connect to Bluetooth Device First...",Toast.LENGTH_LONG).show();
                }

                return false;
            }
        });
        mRight.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (bluetooth_connected){

                    if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                        command = "R";
                        mRight.setCardBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.colorActive));


                    try {

                        outputStream.write(command.getBytes());

                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                    } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) //MotionEvent.ACTION_UP is when you release a button
                    {
                        command = "S";
                        mRight.setCardBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.colorDefault));


                    try {
                        outputStream.write(command.getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                    }

                }
                else {
                    Toast.makeText(getApplicationContext(),"Please Connect to Bluetooth Device First...",Toast.LENGTH_LONG).show();
                }


                return false;
            }
        });
        mLeft.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (bluetooth_connected){
                    if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                        command = "L";
                        mLeft.setCardBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.colorActive));


                    try {

                        outputStream.write(command.getBytes());

                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                    } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) //MotionEvent.ACTION_UP is when you release a button
                    {
                        mLeft.setCardBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.colorDefault));
                        command = "S";



                    try {
                        outputStream.write(command.getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                    }

                }
                else {
                    Toast.makeText(getApplicationContext(),"Please Connect to Bluetooth Device First...",Toast.LENGTH_LONG).show();

                }

                return false;
            }
        });





        //Button that connects the device to the bluetooth module when pressed
        final String connected ="Connected";

        mBTConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!bluetooth_connected){
                    showAvailableDeviceListDialog();
                }
                else {
                    try {
                        socket.close();
                        Toast.makeText(getApplicationContext(),"Disconnected",Toast.LENGTH_LONG).show();
                        mBTConnect.setText("Connect");
                        mConnectionStatusTxt.setText("Not Connected");
                        mBTName.setText("Name");
                        mBTAddress.setText("Address");
                        bluetooth_connected = false;
                    } catch (IOException e) {
                        Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }
                }
                /*

                if(BTinit()){
                    BTconnect();
                    if(BTconnect()){
                        mBTConnect.setText(connected);
                        Toast.makeText(getApplicationContext(),"Bluetooth Connected",Toast.LENGTH_LONG).show();
                    }
                }
                */
            }
        });
    }

    private void showAvailableDeviceListDialog() {

        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.available_device_list_layout, null);

        final ListView mDeviceList = alertLayout.findViewById(R.id.available_device_list_view);

        Button submit = alertLayout.findViewById(R.id.comment_layout_submit_btn);
        Button cancel = alertLayout.findViewById(R.id.comment_layout_cancel_btn);


        final AlertDialog.Builder alert = new AlertDialog.Builder(this);

        // this is set the view from XML inside AlertDialog
        alert.setView(alertLayout);
        final AlertDialog dialog = alert.create();
        dialog.setCanceledOnTouchOutside(false);
       // dialog.getWindow().getAttributes().windowAnimations = R.style.PauseDialogAnimation;
        dialog.show();


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Please Click on Available Address to get connected with Bluetooth Device",Toast.LENGTH_LONG).show();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                Toast.makeText(getApplicationContext(),"Canceled",Toast.LENGTH_LONG).show();
            }
        });

        ArrayList<String> mobileArray = new ArrayList<>();

        final ArrayAdapter adapter = new ArrayAdapter<String>(this,
                R.layout.my_list_view, mobileArray);
        mDeviceList.setAdapter(adapter);

        final BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();


        if(mBluetoothAdapter == null) //Checks if the device supports bluetooth
        {
            Toast.makeText(getApplicationContext(), "Device doesn't support bluetooth", Toast.LENGTH_SHORT).show();
        }

        if(!mBluetoothAdapter.isEnabled()) //Checks if bluetooth is enabled. If not, the program will ask permission from the user to enable it
        {
            Intent enableAdapter = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableAdapter,0);

            try
            {
                Thread.sleep(1000);
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
        }

        if (mBluetoothAdapter.isEnabled()){
            Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
            for(BluetoothDevice bt : pairedDevices) {
                mobileArray.add(bt.getAddress()+ "-" + bt.getName());
            }

            mDeviceList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    Toast.makeText(getApplicationContext(),"Connecting...",Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                    String [] total =  mDeviceList.getItemAtPosition(i).toString().split("-");
                    String add = total[0];
                    String name = total[1];


                    Set<BluetoothDevice> bondedDevices = mBluetoothAdapter.getBondedDevices();

                    if(bondedDevices.isEmpty()) //Checks for paired bluetooth devices
                    {
                        Toast.makeText(getApplicationContext(), "Please pair the device first", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        for(BluetoothDevice iterator : bondedDevices)
                        {
                            if(iterator.getAddress().equals(add))
                            {
                                device = iterator;
                                BTconnect(add, name);

                                break;
                            }
                            else {
                                Toast.makeText(getApplicationContext(),"This Bluetooth Device is not paired. Please pair the device the device first",Toast.LENGTH_LONG).show();
                            }
                        }
                    }

                }
            });

        }



    }



    public void BTconnect(String add, String name)
    {
        boolean connected = true;

        try
        {

            socket = device.createRfcommSocketToServiceRecord(PORT_UUID); //Creates a socket to handle the outgoing connection
            socket.connect();

        }
        catch(IOException e)
        {
            e.printStackTrace();
            connected = false;
        }

        if(connected)
        {
            try
            {
                outputStream = socket.getOutputStream();
                Toast.makeText(getApplicationContext(),"Socket Connected", Toast.LENGTH_LONG).show();
                bluetooth_connected = true;
                mConnectionStatusTxt.setText("Connected");
                mBTAddress.setText(add);
                mBTName.setText(name);
                mBTConnect.setText("Disconnect");
                //gets the output stream of the socket
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }
        else {
            mBTConnect.setText("Connect");

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_overflow_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        if (id == R.id.home_overflow_about_us){
            Intent intent = new Intent(MainActivity.this,AboutUsActivity.class);
            startActivity(intent);
        }

        if (id == R.id.home_overflow_more_app) {
            Intent intent = new Intent(MainActivity.this,MoreAppActivity.class);
            startActivity(intent);
        }

        if (id == R.id.home_overflow_rate_this_app){
            Intent rateIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getApplicationContext().getPackageName()));
            startActivity(rateIntent);
        }
        if (id == R.id.home_overflow_share_this_app){
            try {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_SUBJECT, "Bluetooth Control");
                i.putExtra(Intent.EXTRA_TEXT,"Download this Bluetooth Bot Control App. Best App for Bluetooth Connectivity with Arduino \n For more details Download Our App \n Link :https://play.google.com/store/apps/details?id="+getApplicationContext().getPackageName() );
                startActivity(Intent.createChooser(i, "Select One"));
            } catch(Exception e) {
                Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
            }

        }
        if (id == R.id.home_overflow_exit) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


}

