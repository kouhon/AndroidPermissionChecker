package com.komegu.AndroidPermissionChecker;

import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.PermissionChecker;
import android.app.AlertDialog;

/**
 * Created by kouichi on 15/11/13.
 */
public class Checker
{
    public static boolean checkPermission(@NonNull final CheckParameter cp)
    {
        int result = PermissionChecker.checkSelfPermission(
                cp.mActivity.getApplicationContext(),
                cp.mPermissionName);
        if(result == PermissionChecker.PERMISSION_GRANTED)
        {
            return true;
        }
        if(cp.mIsPermissionRequest == false)
        {
            return false;
        }

        if(ActivityCompat.shouldShowRequestPermissionRationale(
                cp.mActivity, cp.mPermissionName) == false)
        {
            ActivityCompat.requestPermissions(
                    cp.mActivity,
                    new String[]{cp.mPermissionName},
                    cp.mPermissionRequestCode);
            return false;
        }

        new AlertDialog.Builder(cp.mActivity)
                .setTitle(cp.mPermissionRequestNoticeTitle)
                .setMessage(cp.mPermissionRequestNoticeMessage)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        ActivityCompat.requestPermissions(
                                cp.mActivity,
                                new String[]{cp.mPermissionName},
                                cp.mPermissionRequestCode);
                    }
                })
                .create()
                .show();
        return false;
    }

    public static 
}
