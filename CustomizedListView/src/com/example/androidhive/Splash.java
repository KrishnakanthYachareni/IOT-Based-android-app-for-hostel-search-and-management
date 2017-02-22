package com.example.androidhive;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;

public class Splash extends Activity{

	Button next;
	@Override
	protected void onCreate(Bundle krishnakanth) {
		// TODO Auto-generated method stub
		super.onCreate(krishnakanth);
		setContentView(R.layout.welcome_slide3);
		//MediaPlayer oursong = MediaPlayer.create(Splash.this, R.raw.titinic);
		//SharedPreferences getPrefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		//boolean music = getPrefs.getBoolean("checkbox", true);
		//if(music == true)
		//oursong.start();
		Thread timer = new Thread(){
			public void run(){
				try{
					sleep(3000);
				}catch(InterruptedException e){
					e.printStackTrace();
				}finally{
					Intent openstarting = new Intent("android.intent.action.MAINS");
					startActivity(openstarting);
				}
			}
		}; timer.start();
		
		}

	
}
