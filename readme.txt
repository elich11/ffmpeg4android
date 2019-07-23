ffmpeg4android
To add FFmpeg support to your app:

Add this permission to your manifest:
Add this line to your app gradle.build: implementation 'com.netcompss:ffmpeg4android_lib:41.06'
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
Make sure your device contains the in.mp4 file (small mp4 file for the tests), in this location: /sdcard/videokit/in.mp4
  
