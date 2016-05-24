package com.ucsbstudent.phillip.gauchocalendar_android;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by lauraanthony on 5/22/16.
 */
public class FBHash extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        printHashKey();
    }
    public void printHashKey (){
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.ucsbstudent.phillip.gauchocalendar_android",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("COM", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
    }
}
