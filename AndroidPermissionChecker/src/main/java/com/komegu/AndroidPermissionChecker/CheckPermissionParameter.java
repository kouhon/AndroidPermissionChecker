package com.komegu.AndroidPermissionChecker;

import android.app.Activity;
import android.support.annotation.NonNull;

/**
 * Created by kouichi on 15/11/13.
 */
public class CheckPermissionParameter
{
    @NonNull public Activity mActivity;
    @NonNull public String mPermissionName;
    public boolean mIsPermissionRequest;
    public int mPermissionRequestCode;
    @NonNull public String mPermissionRequestDescriptionTitle;
    @NonNull public String mPermissionRequestDescriptionMessage;

    public static class Builder
    {
        private Activity mActivity;
        private String mPermissionName;
        private boolean mIsPermissionRequest;
        private int mPermissionRequestCode;
        private String mPermissionRequestDescriptionTitle;
        private String mPermissionRequestDescriptionMessage;

        public Builder(@NonNull Activity activity, @NonNull String permissionName)
        {
            this.mActivity = activity;
            this.mPermissionName = permissionName;
        }

        public Builder isPermissionRequest(boolean value)
        {
            this.mIsPermissionRequest = value;
            return this;
        }

        public Builder permissionRequestCode(int value)
        {
            this.mPermissionRequestCode = value;
            return this;
        }

        public Builder permissionRequestDescriptionTitle(@NonNull String value)
        {
            this.mPermissionRequestDescriptionTitle = value;
            return this;
        }

        public Builder permissionRequestDescriptionMessage(@NonNull String value)
        {
            this.mPermissionRequestDescriptionMessage = value;
            return this;
        }

        public CheckPermissionParameter build()
        {
            return new CheckPermissionParameter(this);
        }
    }

    public CheckPermissionParameter(Builder builder)
    {
        this.mActivity = builder.mActivity;
        this.mPermissionName = builder.mPermissionName;
        this.mIsPermissionRequest = builder.mIsPermissionRequest;
        this.mPermissionRequestCode = builder.mPermissionRequestCode;
        this.mPermissionRequestDescriptionTitle = builder.mPermissionRequestDescriptionTitle;
        this.mPermissionRequestDescriptionMessage = builder.mPermissionRequestDescriptionMessage;
    }
}
