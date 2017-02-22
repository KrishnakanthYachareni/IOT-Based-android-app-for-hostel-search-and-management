package com.example.androidhive;

import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class CustomizedListView extends Activity {
	// All static variables
	static final String URL = "http://192.168.43.237/webapp/List/music.xml";
	// XML node keys
	static final String KEY_SONG = "song"; // parent node
	static final String KEY_ID = "id";
	static final String KEY_TITLE = "title";
	static final String KEY_ARTIST = "artist";
	static final String KEY_DURATION = "duration";
	static final String KEY_THUMB_URL = "thumb_url";
	
	ListView list;
    LazyAdapter adapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		

		ArrayList<HashMap<String, String>> songsList = new ArrayList<HashMap<String, String>>();

		XMLParser parser = new XMLParser();
		String xml = parser.getXmlFromUrl(URL); // getting XML from URL
		Document doc = parser.getDomElement(xml); // getting DOM element
		
		NodeList nl = doc.getElementsByTagName(KEY_SONG);
		// looping through all song nodes <song>
		for (int i = 0; i < nl.getLength(); i++) {
			// creating new HashMap
			HashMap<String, String> map = new HashMap<String, String>();
			Element e = (Element) nl.item(i);
			// adding each child node to HashMap key => value
			map.put(KEY_ID, parser.getValue(e, KEY_ID));
			map.put(KEY_TITLE, parser.getValue(e, KEY_TITLE));
			map.put(KEY_ARTIST, parser.getValue(e, KEY_ARTIST));
			map.put(KEY_DURATION, parser.getValue(e, KEY_DURATION));
			map.put(KEY_THUMB_URL, parser.getValue(e, KEY_THUMB_URL));

			// adding HashList to ArrayList
			songsList.add(map);
		}
		

		list=(ListView)findViewById(R.id.list);
		
		// Getting adapter by passing xml data ArrayList
        adapter=new LazyAdapter(this, songsList);        
        list.setAdapter(adapter);
        

        // Click event for single list row
        list.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {
            	AlertDialog.Builder alertDialog = new AlertDialog.Builder(CustomizedListView.this);
                if(position==0){
                /*	//Toast.makeText(getApplicationContext(),"You have selected pst3", Toast.LENGTH_LONG).show();
                	 alertDialog.setTitle("Alert Dialog");
                	 
                     // Setting Dialog Message
                     alertDialog.setMessage("Welcome to AndroidHive.info");
              
                     // Setting Icon to Dialog
                     alertDialog.setIcon(R.drawable.heart);
                     alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                         public void onClick(DialogInterface dialog, int which) {
                         // Write your code here to execute after dialog closed
                         Toast.makeText(getApplicationContext(), "Thank You for Choose My Hostel", Toast.LENGTH_SHORT).show();
                         }
                 });
          
                 // Showing Alert Message
                 alertDialog.show(); */
                	Intent openstarting = new Intent("android.intent.action.DbselectActivity");
					startActivity(openstarting);
            } 
                if(position>=1){
                	
                	Intent openstarting = new Intent("android.intent.action.insert");
					startActivity(openstarting);
                	//Toast.makeText(getApplicationContext(),"You have selected pst3", Toast.LENGTH_LONG).show();
                }
         }});
	}	
}