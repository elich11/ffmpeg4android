To add FFmpeg support to your app:

Add this permission to your manifest:

android.permission.WRITE_EXTERNAL_STORAGE

Add this line to your app gradle.build: 

implementation 'com.netcompss:ffmpeg4android_lib:41.07'

And you are done!



Test it by adding to your activity onCreate:

    GeneralUtils.checkForPermissionsMAndAbove(MainActivity.this, true);
    
    LoadJNI vk = new LoadJNI();
    
    try {
    
        String workFolder = getApplicationContext().getFilesDir().getAbsolutePath();
        
        String[] complexCommand = {"ffmpeg","-i", "/sdcard/videokit/in.mp4"};
        
        vk.run(complexCommand , workFolder , getApplicationContext());
        
        Log.i("test", "ffmpeg4android finished successfully");
        
    } catch (Throwable e) {
    
        Log.e("test", "vk run exception.", e);
        
    }
Make sure your device contains the in.mp4 file (small mp4 file for the tests), in this location: /sdcard/videokit/in.mp4.
You can get it from the demo assets folder: 
ffmpeg4android/ffmpeg4android_demo/src/main/assets/
