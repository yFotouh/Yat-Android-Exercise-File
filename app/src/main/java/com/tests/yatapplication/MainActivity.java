package com.tests.yatapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {
    Button btn;
    Button btn2;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.button3);
        btn2 = (Button) findViewById(R.id.button);
        textView = (TextView) findViewById(R.id.textView5);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeToInternalStorage();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readFileFromInternalStorage();
            }
        });

        // Find the toolbar view inside the activity layout
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        setSupportActionBar(toolbar);


//        webView();
//        listview();
//        newCode();
    }

    private void readSharedPref() {
        SharedPreferences sh
                = getSharedPreferences("MySharedPref",
                MODE_PRIVATE);

// The value will be default as empty string
// because for the very first time
// when the app is opened,
// there is nothing to show
        String s1 = sh.getString("name", "");
        int a = sh.getInt("age", 0);
        Toast.makeText(MainActivity.this, s1 + a, Toast.LENGTH_LONG).show();
    }

    private void sharedPrefExample() {
        SharedPreferences sharedPreferences
                = getSharedPreferences("MySharedPref",
                MODE_PRIVATE);

// Creating an Editor object
// to edit(write to the file)
        SharedPreferences.Editor myEdit
                = sharedPreferences.edit();

// Storing the key and its value
// as the data fetched from edittext
        myEdit.putString(
                "name",
                "youssef");
        myEdit.putInt(
                "age",
                50);

// Once the changes have been made,
// we need to commit to apply those changes made,
// otherwise, it will throw an error
        myEdit.commit();
    }

    // Menu icons are inflated just as they were with actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.item1:
                Toast.makeText(this, "Menu selected", Toast.LENGTH_LONG).show();
                return true;
            case R.id.item2:
                Toast.makeText(this, "Hello world", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void webView() {
        WebView webView = (WebView) findViewById(R.id.webview);
        String web = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "<title>Page Title</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "<h1>This is a Heading</h1>\n" +
                "<p>This is a paragraph.</p>\n" +
                "\n" +
                "</body>\n" +
                "</html>";
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
//        webView.loadData(web, "text/html", "UTF-8");
        webView.loadUrl("https://www.google.com");
    }

    private void listview() {
        RecyclerView rv = (RecyclerView) findViewById(R.id.rv);
        List<ListItem> listItems = new ArrayList<>();
        ListItem li1 = new ListItem();
        li1.personName = "hassan";
        ListItem li2 = new ListItem();
        li2.personName = "omar";
        ListItem li3 = new ListItem();
        li3.personName = "karim";
        ListItem li4 = new ListItem();
        li4.personName = "ahmed";
        listItems.add(li3);
        listItems.add(li2);
        listItems.add(li1);
        listItems.add(li4);
        MyListAdapter adapter = new MyListAdapter(listItems);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);
    }

    private void newCode() {
        Button btn1 = (Button) findViewById(R.id.button9);
        Button btn2 = (Button) findViewById(R.id.button10);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
// create a FragmentManager
                BlankFragment blankFragment = new BlankFragment();
                androidx.fragment.app.FragmentManager fm = getSupportFragmentManager();
// create a FragmentTransaction to begin the transaction and replace the Fragment
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
// replace the FrameLayout with new Fragment
                fragmentTransaction.replace(R.id.fragment, blankFragment);
                fragmentTransaction.commit(); // save the changes
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
// create a FragmentManager
                BlankFragment2 blankFragment = new BlankFragment2();
                androidx.fragment.app.FragmentManager fm = getSupportFragmentManager();
// create a FragmentTransaction to begin the transaction and replace the Fragment
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
// replace the FrameLayout with new Fragment
                fragmentTransaction.replace(R.id.fragment, blankFragment);
                fragmentTransaction.commit();
            }
        });
    }

    private void addNotification() {
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Notifications Example")
                        .setContentText("This is a test notification");

        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        // Add as notification
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }

    private void oldCode() {
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                addNotification();
//            }
//        });
//        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);
//        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int progress,
//                                          boolean fromUser) {
//                Toast.makeText(getApplicationContext(), "seekbar progress: " + progress, Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//                Toast.makeText(getApplicationContext(), "seekbar touch started!", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//                Toast.makeText(getApplicationContext(), "seekbar touch stopped!", Toast.LENGTH_SHORT).show();
//            }
//        });
//        Switch sw = (Switch) findViewById(R.id.switch1);
//        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                System.out.println(50);
//            }
//        });
//        Spinner sp = (Spinner) findViewById(R.id.spinner);
//        ArrayList<String> stringArrayList = new ArrayList<>();
//        stringArrayList.add("One");
//        stringArrayList.add("Two");
//        stringArrayList.add("Three");
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
//                R.layout.support_simple_spinner_dropdown_item,
//                stringArrayList);
//        sp.setAdapter(adapter);
//        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(MainActivity.this, "Hello", Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
    }

    private void showDialogFragment() {
        FragmentManager fm = getSupportFragmentManager();
        CustomDialog customDialog = CustomDialog.newInstance("");
        customDialog.show(fm, "fragment_edit_name");

    }

    private void showADialog() {
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(this);
        //Uncomment the below code to Set the message and title from the strings.xml file
        builder.setTitle("Warning");

        //Setting message manually and performing action on button click
        builder.setMessage("Do you want to close this application ?")
                .setCancelable(false)
                .setPositiveButton("Mashy", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                        Toast.makeText(getApplicationContext(), "you choose yes action for alertbox",
                                Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("La2a", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //  Action for 'NO' Button
                        dialog.cancel();
                        Toast.makeText(getApplicationContext(), "you choose no action for alertbox",
                                Toast.LENGTH_SHORT).show();
                    }
                });
        //Creating dialog box
        AlertDialog alert = builder.create();
        //Setting the title manually
        alert.setTitle("AlertDialogExample");
        alert.show();
    }

    // write text to file
    public void writeToInternalStorage() {
        // add-write text into file
        try {
            FileOutputStream fileout = openFileOutput("mytextfile.txt", MODE_PRIVATE);
            OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
            outputWriter.write("one two three".toString());
            outputWriter.close();

            //display file saved message
            Toast.makeText(getBaseContext(), "File saved successfully!",
                    Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Read text from file
    public void readFileFromInternalStorage() {
        //reading text from file
        try {
            FileInputStream fileIn = openFileInput("mytextfile.txt");
            InputStreamReader InputRead = new InputStreamReader(fileIn);

            char[] inputBuffer = new char[100];
            String s = "";
            int charRead;

            while ((charRead = InputRead.read(inputBuffer)) > 0) {
                // char to string conversion
                String readstring = String.copyValueOf(inputBuffer, 0, charRead);
                s += readstring;
            }
            InputRead.close();
            textView.setText(s);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}