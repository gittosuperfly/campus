package com.cai.campus.features.group.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.cai.campus.R;
import com.cai.campus.common.network.model.UserAccount;

public class LookUserInfoDialog extends Dialog {

    private Activity activity;
    private UserAccount userInfo;

    public LookUserInfoDialog(@NonNull Activity activity, UserAccount userInfo) {
        super(activity, R.style.ActionSheetDialogStyle);
        this.activity = activity;
        this.userInfo = userInfo;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_user_info);

        setViewLocation();
        setCanceledOnTouchOutside(true);//外部点击取消


        TextView userNameTv = findViewById(R.id.userName);
        TextView userPhoneTv = findViewById(R.id.userPhone);
        TextView userEmailTv = findViewById(R.id.userEmail);
        TextView userSixTv = findViewById(R.id.userSix);
        TextView userIntroTv = findViewById(R.id.userIntro);

        userNameTv.setText(userInfo.getName());
        userPhoneTv.setText(userInfo.getPhone());
        userEmailTv.setText(userInfo.getEmail());
        if (userInfo.getIntroduction() == null || userInfo.getIntroduction().equals("")) {
            userIntroTv.setText("无");
        } else {
            userIntroTv.setText(userInfo.getIntroduction());
        }
        if (userInfo.getSex() != null) {
            userSixTv.setText(userInfo.getSex() == 1 ? "男" : "女");
        }

    }

    /**
     * 设置dialog位于屏幕底部
     */
    private void setViewLocation() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;

        Window window = this.getWindow();
        assert window != null;
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.x = 0;
        layoutParams.y = height;
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        // 设置显示位置
        onWindowAttributesChanged(layoutParams);
    }

}
