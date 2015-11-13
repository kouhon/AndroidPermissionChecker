package com.komegu.AndroidPermissionChecker;

import android.app.Activity;
import android.support.annotation.NonNull;

/**
 * Created by kouichi on 15/11/13.
 */
public class CheckParameter
{
    @NonNull public Activity mActivity;
    @NonNull public String mPermissionName;
    public boolean mIsPermissionRequest;
    public int mPermissionRequestCode;
    @NonNull public String mPermissionRequestNoticeTitle;
    @NonNull public String mPermissionRequestNoticeMessage;

    public static class Builder
    {
        private Activity mActivity;
        private String mPermissionName;
        private boolean mIsPermissionRequest;
        private int mPermissionRequestCode;
        private String mPermissionRequestNoticeTitle;
        private String mPermissionRequestNoticeMessage;

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

        public Builder permissionRequestNoticeTitle(@NonNull String value)
        {
            this.mPermissionRequestNoticeTitle = value;
            return this;
        }

        public Builder permissionRequestNoticeMessage(@NonNull String value)
        {
            this.mPermissionRequestNoticeMessage = value;
            return this;
        }

        public CheckParameter build()
        {
            return new CheckParameter(this);
        }
    }

    public CheckParameter(Builder builder)
    {
        this.mActivity = builder.mActivity;
        this.mPermissionName = builder.mPermissionName;
        this.mIsPermissionRequest = builder.mIsPermissionRequest;
        this.mPermissionRequestCode = builder.mPermissionRequestCode;
        this.mPermissionRequestNoticeTitle = builder.mPermissionRequestNoticeTitle;
        this.mPermissionRequestNoticeMessage = builder.mPermissionRequestNoticeMessage;
    }
}
