package com.mmo.careerlogy.Fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.mmo.careerlogy.R;

public class DialogFullscreenFragment extends DialogFragment {

    public CallbackResult callbackResult;

    public void setOnCallbackResult(final CallbackResult callbackResult) {
        this.callbackResult = callbackResult;
    }

    private int request_code = 0;
    private View root_view;
    TextView Name;
    TextView Date;
    TextView title;
    TextView question;
    TextView answer;
    Bundle bundle;
    public static String questinedBy = "questinedBy" ;
    public static String questinedOn = "questinedOn" ;
    public static String questinedTitle = "questinedTitle" ;
    public static String questinedDesc = "questinedDesc" ;
    public static String questinedAnswer = "questinedAnswer" ;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        root_view = inflater.inflate(R.layout.dialog_answer, container, false);
        bundle = getArguments();

        Name     = root_view.findViewById(R.id.questinedBy);
        Date     = root_view.findViewById(R.id.questinedOn);
        title    = root_view.findViewById(R.id.questinedTitle);
        question = root_view.findViewById(R.id.questinedDesc);
        answer   = root_view.findViewById(R.id.questinedAnswer);

        ((ImageButton) root_view.findViewById(R.id.bt_close)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        Name.setText(bundle.getString(questinedBy));
        Date.setText(bundle.getString(questinedOn));
        title.setText(bundle.getString(questinedTitle));
        question.setText(bundle.getString(questinedDesc));
        answer.setText(bundle.getString(questinedAnswer));

        return root_view;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    public void setRequestCode(int request_code) {
        this.request_code = request_code;
    }

    public interface CallbackResult {
        void sendResult(int requestCode, Object obj);
    }

}