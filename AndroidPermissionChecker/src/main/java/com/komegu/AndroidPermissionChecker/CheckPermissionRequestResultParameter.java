package com.komegu.AndroidPermissionChecker;

import android.app.Activity;
import android.support.annotation.NonNull;

/**
 * Created by kouichi on 15/11/13.
 */
public class CheckPermissionRequestResultParameter
{
    @NonNull public Activity mActivity;
    @NonNull public String mPermissionName;
    public boolean mIsPermissionRequest;
    public int mPermissionRequestCode;
    @NonNull public String mPermissionReRequestTitle;
    @NonNull public String mPermissionReRequestMessage;
    @NonNull public String mDoNotAllowTheFutureDescriptionTitle;
    @NonNull public String mDoNotAllowTheFutureDescriptionMessage;

    public static class Builder
    {
        private Activity mActivity;
        private String mPermissionName;
        private boolean mIsPermissionRequest;
        private int mPermissionRequestCode;
        private String mPermissionReRequestTitle;
        private String mPermissionReRequestMessage;
        private String mDoNotAllowTheFutureDescriptionTitle;
        private String mDoNotAllowTheFutureDescriptionMessage;

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

        public Builder permissionReRequestTitle(@NonNull String value)
        {
            this.mPermissionReRequestTitle = value;
            return this;
        }

        public Builder permissionReRequestMessage(@NonNull String value)
        {
            this.mPermissionReRequestMessage = value;
            return this;
        }

        public Builder doNotAllowTheFutureDescriptionTitle(@NonNull String value)
        {
            this.mDoNotAllowTheFutureDescriptionTitle = value;
            return this;
        }

        public Builder doNotAllowTheFutureDescriptionMessage(@NonNull String value)
        {
            this.mDoNotAllowTheFutureDescriptionMessage = value;
            return this;
        }

        public CheckPermissionRequestResultParameter build()
        {
            return new CheckPermissionRequestResultParameter(this);
        }
    }

    public CheckPermissionRequestResultParameter(Builder builder)
    {
        this.mActivity = builder.mActivity;
        this.mPermissionName = builder.mPermissionName;
        this.mIsPermissionRequest = builder.mIsPermissionRequest;
        this.mPermissionRequestCode = builder.mPermissionRequestCode;
        this.mPermissionReRequestTitle = builder.mPermissionReRequestTitle;
        this.mPermissionReRequestMessage = builder.mPermissionReRequestMessage;
        this.mDoNotAllowTheFutureDescriptionTitle = builder.mDoNotAllowTheFutureDescriptionTitle;
        this.mDoNotAllowTheFutureDescriptionMessage = builder.mDoNotAllowTheFutureDescriptionMessage;
    }

}
