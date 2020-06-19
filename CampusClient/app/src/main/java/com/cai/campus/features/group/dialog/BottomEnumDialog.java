package com.cai.campus.features.group.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cai.campus.R;
import com.cai.campus.common.network.model.GroupUser;
import com.cai.campus.common.network.model.UserAccount;

public abstract class BottomEnumDialog extends Dialog implements View.OnClickListener {

    private Activity activity;
    private RelativeLayout lookUserBtn, setAdminBtn, makeOverBtn, quitUserBtn, lookSignInBtn;
    private TextView setAdminBtnText;
    private int myState;
    private UserAccount me;
    private GroupUser groupUser;

    public BottomEnumDialog(Activity activity, int myState, UserAccount me, GroupUser groupUser) {
        super(activity, R.style.ActionSheetDialogStyle);
        this.activity = activity;
        this.myState = myState;
        this.me = me;
        this.groupUser = groupUser;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_bottom_enum);

        lookUserBtn = findViewById(R.id.lookUserBtn);
        setAdminBtn = findViewById(R.id.setAdminBtn);
        makeOverBtn = findViewById(R.id.makeOverBtn);
        quitUserBtn = findViewById(R.id.quitUserBtn);
        lookSignInBtn = findViewById(R.id.lookSignInBtn);
        setAdminBtnText = findViewById(R.id.setAdminBtnText);

        lookUserBtn.setOnClickListener(this);
        setAdminBtn.setOnClickListener(this);
        makeOverBtn.setOnClickListener(this);
        quitUserBtn.setOnClickListener(this);
        lookSignInBtn.setOnClickListener(this);

        setViewLocation();
        setCanceledOnTouchOutside(true);//外部点击取消

        if (myState == 0) {
            setAdminBtn.setVisibility(View.GONE);
            makeOverBtn.setVisibility(View.GONE);
            quitUserBtn.setVisibility(View.GONE);
            lookSignInBtn.setVisibility(View.GONE);
        } else if (myState == 1) {
            if (groupUser.getStatus() >= myState) {
                setAdminBtn.setVisibility(View.GONE);
                makeOverBtn.setVisibility(View.GONE);
                quitUserBtn.setVisibility(View.GONE);
            } else {
                setAdminBtn.setVisibility(View.GONE);
                makeOverBtn.setVisibility(View.GONE);
            }
        } else {
            if (groupUser.getStatus() == 1) {
                setAdminBtnText.setText("取消管理员");
            } else if (groupUser.getStatus() == 2) {
                setAdminBtn.setVisibility(View.GONE);
                makeOverBtn.setVisibility(View.GONE);
                quitUserBtn.setVisibility(View.GONE);
            } else {
                setAdminBtnText.setText("设置管理员");
            }
        }

        if (me.getUid() == groupUser.getUserInfo().getUid()) {
            lookSignInBtn.setVisibility(View.VISIBLE);
        }

    }

    /**
     * 设置dialog位于屏幕底部
     */
    private void setViewLocation() {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        int height = dm.heightPixels;

        Window window = this.getWindow();
        assert window != null;
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.x = 0;
        lp.y = height;
        lp.width = ViewGroup.LayoutParams.MATCH_PARENT;
        lp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        // 设置显示位置
        onWindowAttributesChanged(lp);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.lookUserBtn:
                lookUserBtnClick();
                this.cancel();
                break;
            case R.id.setAdminBtn:
                setAdminBtnClick();
                this.cancel();
                break;
            case R.id.makeOverBtn:
                makeOverBtnClick();
                this.cancel();
                break;
            case R.id.quitUserBtn:
                quitUserBtnClick();
                this.cancel();
                break;
            case R.id.lookSignInBtn:
                lookSignInBtnClick();
                this.cancel();
                break;
        }
    }

    public abstract void lookUserBtnClick();

    public abstract void setAdminBtnClick();

    public abstract void makeOverBtnClick();

    public abstract void quitUserBtnClick();

    public abstract void lookSignInBtnClick();

}