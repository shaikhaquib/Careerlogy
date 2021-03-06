package com.DIS.careerlogy.Extra;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.DIS.careerlogy.R;

public class StudentInstruction {

    Context activity;
    Dialog dialog;

    //..we need the context else we can not create the dialog so get context in constructor
    public StudentInstruction(Context activity) {
        this.activity = activity;
    }

    public void show() {

        dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //...set cancelable false so that it's never get hidden
        dialog.setCancelable(false);
        //...that's the layout i told you will inflate later
        dialog.setContentView(R.layout.student_instrction);

        dialog.findViewById(R.id.btDismiss).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        TextView textView = dialog.findViewById(R.id.txtInstruction);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        // final android.support.v7.app.AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        dialog.show();
        dialog.getWindow().setAttributes(lp);
    }

    //..also create a method which will hide the dialog when some work is done
    public void dismiss() {
        dialog.dismiss();
    }

}