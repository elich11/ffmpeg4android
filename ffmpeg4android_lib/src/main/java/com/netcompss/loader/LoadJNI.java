package com.netcompss.loader;

import java.io.File;

import com.netcompss.ffmpeg4android.CommandValidationException;
import com.netcompss.ffmpeg4android.GeneralUtils;
import com.netcompss.ffmpeg4android.Prefs;

import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;

public final class LoadJNI {

	static {
		System.loadLibrary("loader-jni");
	}
	
	/**
	 * 
	 * @param args ffmpeg command
	 * @param workFolder working directory 
	 * @param ctx Android context
	 * @param isValidate apply validation to the command
	 * @throws CommandValidationException
	 */
	public void run(String[] args, String workFolder, Context ctx, boolean isValidate) throws CommandValidationException {
		Log.i(Prefs.TAG, "running ffmpeg4android_lib: " + Prefs.version);
		// delete previous log: this is essential for correct progress calculation
		String vkLogPath = workFolder + "vk.log";
		GeneralUtils.deleteFileUtil(vkLogPath);
		GeneralUtils.printCommand(args);
		
		//printInternalDirStructure(ctx);
		
		if (isValidate) {
			if (GeneralUtils.isValidCommand(args))
				load(args, workFolder, getVideokitLibPath(ctx), true);
			else
				throw new CommandValidationException();
		}
		else {
			load(args, workFolder, getVideokitLibPath(ctx), true);
		}
		
	}
	
	/**
	 * 
	 * @param args ffmpeg command
	 * @param workFolder working directory
	 * @param ctx Android context
	 * @throws CommandValidationException
	 */
	public void run(String[] args, String workFolder, Context ctx) throws CommandValidationException {
		run(args, workFolder, ctx, true);
	}
	
	
	private static void printInternalDirStructure(Context ctx) {
		Log.d(Prefs.TAG, "=printInternalDirStructure=");
		Log.d(Prefs.TAG, "==============================");
		File file = new File(ctx.getFilesDir().getParent());
		analyzeDir(file);
		Log.d(Prefs.TAG, "==============================");
	}
	
	private static void analyzeDir(File path) {
		if (path.isDirectory()) {
			Log.d(Prefs.TAG, "Scanning dir: " + path.getAbsolutePath());
			File[] files1 = path.listFiles();
			for (int i = 0; i < files1.length; i++) {
				analyzeDir(files1[i]);
			}
			Log.d(Prefs.TAG, "==========");
		}
		else {
			Log.w(Prefs.TAG, path.getAbsolutePath() + " not a dir.");

		}
	}
	
	private static String getVideokitLibPath(Context ctx) {

		//String videokitLibPath = ctx.getFilesDir().getParent()  + "/lib/libvideokit.so";
		String videokitLibPath = ctx.getApplicationInfo().nativeLibraryDir  + "/libvideokit.so";

		File file = new File(videokitLibPath);
		if(file.exists())  {     
		    Log.i(Prefs.TAG, "videokitLibPath exits");
			Log.i(Prefs.TAG, videokitLibPath);
		}
		else {
			Log.e(Prefs.TAG, "videokitLibPath not exits: " + videokitLibPath);

		}

		return videokitLibPath;
		
	}
	
	
	
	public void fExit( Context ctx) {
		fexit(getVideokitLibPath(ctx));
	}


	@SuppressWarnings("JniMissingFunction")
	public native String fexit(String videokitLibPath);

	@SuppressWarnings("JniMissingFunction")
	public native String unload();

	@SuppressWarnings("JniMissingFunction")
	public native String load(String[] args, String videokitSdcardPath, String videokitLibPath, boolean isComplex);

}
