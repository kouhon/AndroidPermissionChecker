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
    /**
     * パーミッション許可リクエスト結果
     */
    public enum PermissionRequestResult
    {
        /**
         * リクエストコードがマッチしない
         */
        UN_MATCH_REQUEST_CODE,
        /**
         * 結果数が一つではない
         */
        GRANT_RESULTS_CNT_NOT_ONE,
        /**
         * 付与された
         */
        PERMISSION_GRANTED,
        /**
         * 拒否された
         */
        PERMISSION_DENIED
    }

    /**
     * パーミッションをチェックする
     * パーミッションが付与されていなかった際はパーミッション許可リクエストを行う
     * @param cpp パラメータオブジェクト
     * @return true:パーミッションは付与されている false:パーミッションは付与されていない
     */
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

    /**
     * パーミッションリクエスト結果をチェックする
     * パーミッションが付与されていなかった際はパーミッション許可再リクエストを行う
     * パーミッション付与を今後許可しない状態になった際はメッセージを表示し、設定画面へ遷移させる
     * @param requestCode リクエストコード
     * @param grantResults リクエスト結果
     * @param cprrp パラメータオブジェクト
     * @return
     */
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
        if(cprrp.mIsPermissionReRequest == false)
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
