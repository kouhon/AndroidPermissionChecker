package com.komegu.AndroidPermissionChecker;

import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.PermissionChecker;
import android.app.AlertDialog;
import android.content.Intent;
import android.provider.Settings;
import android.net.Uri;

/**
 * Created by kouichi on 15/11/13.
 */
public class Checker
{
    public enum PermissionRequestResult
    {
        UN_MATCH_REQUEST_CODE,
        GRANT_RESULTS_CNT_NOT_ONE,
        PERMISSION_GRANTED,
        PERMISSION_DENIED
    }

    public static boolean checkPermission(@NonNull final CheckPermissionParameter cpp)
    {
        int result = PermissionChecker.checkSelfPermission(
                cpp.mActivity.getApplicationContext(),
                cpp.mPermissionName);
        if(result == PermissionChecker.PERMISSION_GRANTED)
        {
            return true;
        }
        if(cpp.mIsPermissionRequest == false)
        {
            return false;
        }

        if(ActivityCompat.shouldShowRequestPermissionRationale(
                cpp.mActivity, cpp.mPermissionName) == false)
        {
            ActivityCompat.requestPermissions(
                    cpp.mActivity,
                    new String[]{cpp.mPermissionName},
                    cpp.mPermissionRequestCode);
            return false;
        }

        new AlertDialog.Builder(cpp.mActivity)
                .setTitle(cpp.mPermissionRequestDescriptionTitle)
                .setMessage(cpp.mPermissionRequestDescriptionMessage)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        ActivityCompat.requestPermissions(
                                cpp.mActivity,
                                new String[]{cpp.mPermissionName},
                                cpp.mPermissionRequestCode);
                    }
                })
                .create()
                .show();
        return false;
    }

    public static PermissionRequestResult checkPermissionRequestResult(
            int requestCode,
            @NonNull int[] grantResults,
            @NonNull final CheckPermissionRequestResultParameter cprrp)
    {
        if(requestCode != cprrp.mPermissionRequestCode)
        {
            return PermissionRequestResult.UN_MATCH_REQUEST_CODE;
        }
        if(grantResults.length != 1)
        {
            return PermissionRequestResult.GRANT_RESULTS_CNT_NOT_ONE;
        }
        if(grantResults[0] == PackageManager.PERMISSION_GRANTED)
        {
            return PermissionRequestResult.PERMISSION_GRANTED;
        }
        if(cprrp.mIsPermissionRequest == false)
        {
            return PermissionRequestResult.PERMISSION_DENIED;
        }
        if(ActivityCompat.shouldShowRequestPermissionRationale(
                cprrp.mActivity,
                cprrp.mPermissionName))
        {
            new AlertDialog.Builder(cprrp.mActivity)
                    .setTitle(cprrp.mPermissionReRequestTitle)
                    .setMessage(cprrp.mPermissionReRequestMessage)
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            ActivityCompat.requestPermissions(
                                    cprrp.mActivity,
                                    new String[]{cprrp.mPermissionName},
                                    cprrp.mPermissionRequestCode);
                        }
                    })
                    .create()
                    .show();
        }
        else
        {
            new AlertDialog.Builder(cprrp.mActivity)
                    .setTitle(cprrp.mDoNotAllowTheFutureDescriptionTitle)
                    .setMessage(cprrp.mDoNotAllowTheFutureDescriptionMessage)
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                            Uri uri = Uri.fromParts("package", cprrp.mActivity.getPackageName(), null);
                            intent.setData(uri);
                            cprrp.mActivity.startActivity(intent);
                        }
                    })
                    .create()
                    .show();
        }
        return PermissionRequestResult.PERMISSION_DENIED;
    }
}
