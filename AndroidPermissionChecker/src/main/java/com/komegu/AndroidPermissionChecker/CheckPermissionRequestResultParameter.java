package com.komegu.AndroidPermissionChecker;

import android.app.Activity;
import android.support.annotation.NonNull;

/**
 * Created by kouichi on 15/11/13.
 */
public class CheckPermissionRequestResultParameter
{
    /**
     * Activity
     */
    @NonNull public Activity mActivity;
    /**
     * チェックするパーミッション名
     */
    @NonNull public String mPermissionName;
    /**
     * パーミッション許可に失敗した際に再リクエストするかどうか
     * true:再リクエストする false:再リクエストしない
     */
    public boolean mIsPermissionReRequest;
    /**
     * パーミッションリクエストコード
     */
    public int mPermissionRequestCode;
    /**
     * パーミッション再リクエストする際の説明タイトル
     */
    @NonNull public String mPermissionReRequestTitle;
    /**
     * パーミッション再リクエストする際の説明
     */
    @NonNull public String mPermissionReRequestMessage;
    /**
     * 今後許可しない状態になった際の説明タイトル
     */
    @NonNull public String mDoNotAllowTheFutureDescriptionTitle;
    /**
     * 今後許可しない状態になった際の説明
     */
    @NonNull public String mDoNotAllowTheFutureDescriptionMessage;

    public static class Builder
    {
        private Activity mActivity;
        private String mPermissionName;
        private boolean mIsPermissionReRequest;
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

        public Builder isPermissionReRequest(boolean value)
        {
            this.mIsPermissionReRequest = value;
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
        this.mIsPermissionReRequest = builder.mIsPermissionReRequest;
        this.mPermissionRequestCode = builder.mPermissionRequestCode;
        this.mPermissionReRequestTitle = builder.mPermissionReRequestTitle;
        this.mPermissionReRequestMessage = builder.mPermissionReRequestMessage;
        this.mDoNotAllowTheFutureDescriptionTitle = builder.mDoNotAllowTheFutureDescriptionTitle;
        this.mDoNotAllowTheFutureDescriptionMessage = builder.mDoNotAllowTheFutureDescriptionMessage;
    }

}
