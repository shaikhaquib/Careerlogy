package com.DIS.careerlogy.Extra;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.DIS.careerlogy.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class PopSplash {

    Context activity;
    Dialog dialog;

    //..we need the context else we can not create the dialog so get context in constructor
    public PopSplash(Context activity) {
        this.activity = activity;
    }

    public void show() {

        dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //...set cancelable false so that it's never get hidden
        dialog.setCancelable(false);
        //...that's the layout i told you will inflate later
        dialog.setContentView(R.layout.popupsplash);

        ImageView imgPopup = dialog.findViewById(R.id.imgPopup);
        Glide.with(activity)
//                .setDefaultRequestOptions(requestOptions)
//                .load(mStickers.get(i))
                .load(R.drawable.pop_up)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgPopup);
        dialog.findViewById(R.id.btDismiss).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        // final android.support.v7.app.AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.show();
    }

    //..also create a method which will hide the dialog when some work is done
    public void dismiss() {
        dialog.dismiss();
    }

}