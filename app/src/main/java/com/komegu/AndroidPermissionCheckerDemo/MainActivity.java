package com.komegu.AndroidPermissionCheckerDemo;

import android.Manifest;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.komegu.AndroidPermissionChecker.CheckPermissionParameter;
import com.komegu.AndroidPermissionChecker.CheckPermissionRequestResultParameter;
import com.komegu.AndroidPermissionChecker.Checker;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CheckPermissionParameter cp = new CheckPermissionParameter.Builder(this, Manifest.permission.CAMERA)
                .isPermissionRequest(true)
                .permissionRequestCode(0)
                .permissionRequestDescriptionTitle("permissionRequestDescriptionTitle")
                .permissionRequestDescriptionMessage("permissionRequestDescriptionMessage")
                .build();
        Checker.checkPermission(cp);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode,
            String[] permissions,
            int[] grantResults)
    {
        CheckPermissionRequestResultParameter cprrp = new CheckPermissionRequestResultParameter.Builder(
                this,
                Manifest.permission.CAMERA)
                .isPermissionReRequest(true)
                .permissionRequestCode(0)
                .permissionReRequestTitle("permissionReRequestTitle")
                .permissionReRequestMessage("permissionReRequestMessage")
                .doNotAllowTheFutureDescriptionTitle("doNotAllowTheFutureDescriptionTitle")
                .doNotAllowTheFutureDescriptionMessage("doNotAllowTheFutureDescriptionMessage")
                .build();
        Checker.checkPermissionRequestResult(requestCode, grantResults, cprrp);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
