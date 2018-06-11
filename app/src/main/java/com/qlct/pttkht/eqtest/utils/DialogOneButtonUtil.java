package com.qlct.pttkht.eqtest.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.qlct.pttkht.eqtest.R;

/**
 * Created by thaopt on 8/28/17.
 */

public class DialogOneButtonUtil extends Dialog {
    private TextView mTitleTextView, mContentTextView;
    private int mType = 0;
    private Context mContext;

    /**
     *
     * @param context
     * @param title
     * @param message
     */
    public static void showAlert(Context context, String title, String message) {
        DialogOneButtonUtil dialog = new DialogOneButtonUtil(context);
        dialog.setText(title, message);
        dialog.show();
    }

    public DialogOneButtonUtil(@NonNull Context context) {
        super(context);
        this.mContext = context;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_one_button);
        getWindow().setBackgroundDrawableResource(R.drawable.bg_dialog);
        initView();
    }

    private void initView() {
        mTitleTextView = (TextView) findViewById(R.id.title_dialog_one_button);
        mContentTextView = (TextView) findViewById(R.id.content_dialog_one_button);
        findViewById(R.id.btn_dialog_show_error).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mType == 1) {
                    ((Activity) mContext).finish();
                } else if (mType == 2) {
                    mListener.okClick();
                    ((Activity) mContext).finish();
                } else if (mType == 3)
                    mListener.okClick();
                dismiss();
            }
        });
    }

    public void setText(String title, String content) {
        mTitleTextView.setText(title);
        mContentTextView.setText(content);
    }

    public void setType(int type) {
        this.mType = type;
    }

    //callback

    public interface DialogOneButtonClickListener {
        void okClick();
    }

    private DialogOneButtonClickListener mListener;

    public void setDialogOneButtonClick(DialogOneButtonClickListener listener) {
        this.mListener = listener;
    }
}
