package com.examples.ffmpeg4android;

import com.examples.ffmpeg4android_demo.R;
import com.netcompss.ffmpeg4android.GeneralUtils;
import com.netcompss.ffmpeg4android.Prefs;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Main extends Activity  {
	
	@Override
	protected void onResume() {
		super.onResume();
		Log.i(Prefs.TAG, "Main on resume handling log copy in case of a crash");
		String demoVideoFolder = Environment.getExternalStorageDirectory().getAbsolutePath() + "/videokit/";
		String workFolder = getApplicationContext().getFilesDir() + "/";
		String vkLogPath = workFolder + "vk.log";
		GeneralUtils.copyFileToFolder(vkLogPath, demoVideoFolder);

	}
	
	@Override
	  public void onCreate(Bundle savedInstanceState) {
	      super.onCreate(savedInstanceState);
	      setContentView(R.layout.main);
	      GeneralUtils.checkForPermissionsMAndAbove(Main.this, false);
	      
	      Button simpleAct =  (Button)findViewById(R.id.startSimpleAct);
	      simpleAct.setOnClickListener(new OnClickListener() {
				public void onClick(View v){
					Log.i(Prefs.TAG, "run simpleAct.");
					startAct(SimpleExample.class);
					
				}
		  });
	      
	      Button progressAct =  (Button)findViewById(R.id.startProgressAct);
	      progressAct.setOnClickListener(new OnClickListener() {
				public void onClick(View v){
					Log.i(Prefs.TAG, "run progressAct.");
					startAct(ProgressBarExample.class);
					
				}
		  });
	      
	      Button progressWithNotificationAct =  (Button)findViewById(R.id.startProgressWithNotificationAct);
	      progressWithNotificationAct.setOnClickListener(new OnClickListener() {
				public void onClick(View v){
					Log.i(Prefs.TAG, "run progressWithNotificationAct.");
					startAct(ProgressBarWithNotificationExample.class);
				}
		  });
	      
	      Button multiAct =  (Button)findViewById(R.id.startMultiAct);
	      multiAct.setOnClickListener(new OnClickListener() {
				public void onClick(View v){
					Log.i(Prefs.TAG, "run MultiplyCommandsExample.");
					startAct(MultipleCommandsExample.class);
				}
		  });
	      
	      
	}
	
	public static String getAboutText(Context ctx) {
    	StringBuffer b = new StringBuffer();
    	b.append("\n" + ctx.getString(R.string.full_app_name) + "\n");
    	b.append("Developed by: NetComps LTD\n" );
    	b.append("Version: " + GeneralUtils.getVersionName(ctx) + "\n");
    	b.append("Support: " + ctx.getString(R.string.email) + "\n");
    	return b.toString();
    }
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_about:
    		AlertDialog.Builder builder = new AlertDialog.Builder(this);
    		builder.setTitle("About").
    		setMessage(getAboutText(getApplicationContext())).
    		setPositiveButton("OK", new DialogInterface.OnClickListener() {
    	           public void onClick(DialogInterface dialog, int id) {
    	               // User clicked OK button
    	           }
    	       });
    		
    		AlertDialog about  = builder.create();
    		about.show();
     		return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	private void startAct(Class act) {
		Intent intent = new Intent(this, act);
		Log.d(Prefs.TAG, "Starting act:" + act);
		this.startActivity(intent);
	}
	
	


}
