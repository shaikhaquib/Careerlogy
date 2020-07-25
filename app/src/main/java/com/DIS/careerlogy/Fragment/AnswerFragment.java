package com.DIS.careerlogy.Fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.DIS.careerlogy.Models.ProbSubCatLstItem;
import com.DIS.careerlogy.Models.CategoryOperationsEditResponse;
import com.DIS.careerlogy.Network.RetrofitClient;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.DIS.careerlogy.Extra.Constants;
import com.DIS.careerlogy.Extra.Progress;
import com.DIS.careerlogy.Fragment.Admin.RecentAnswerFragment;
import com.DIS.careerlogy.Models.UploadTestimonialResponse;
import com.DIS.careerlogy.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.DIS.careerlogy.LoginActivity.USER;

public class AnswerFragment extends DialogFragment {

    public CallbackResult callbackResult;
    private RecentAnswerFragment.EditNameDialogListener listener;

    public AnswerFragment(RecentAnswerFragment.EditNameDialogListener listener) {
        this.listener = listener;
    }

    public AnswerFragment() {

    }

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
    EditText edtAnswer;
    Bundle bundle;
    LinearLayout viewMode, editMode;
    public static String questinedBy = "questinedBy";
    public static String questinedOn = "questinedOn";
    public static String questinedTitle = "questinedTitle";
    public static String questinedDesc = "questinedDesc";
    public static String questinedAnswer = "questinedAnswer";
    public static String isAnswered = "answered";
    public static String needClarification = "needclarification";
    public static String AnswerID = "AID";
    public static String QuestionID = "QID";

    private String answered;
    private String needclarification;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        root_view = inflater.inflate(R.layout.dialog_answer, container, false);
        bundle = getArguments();

        Name = root_view.findViewById(R.id.questinedBy);
        Date     = root_view.findViewById(R.id.questinedOn);
        title    = root_view.findViewById(R.id.questinedTitle);
        question = root_view.findViewById(R.id.questinedDesc);
        answer   = root_view.findViewById(R.id.questinedAnswer);
        viewMode = root_view.findViewById(R.id.AnswerViewMode);
        editMode = root_view.findViewById(R.id.AnswerEditMode);
        edtAnswer = root_view.findViewById(R.id.edtAnswer);

        if (USER.getUMType().equalsIgnoreCase("U")) {
            ((ImageView) root_view.findViewById(R.id.edit)).setVisibility(View.GONE);
        } else {
            ((ImageView) root_view.findViewById(R.id.edit)).setVisibility(View.VISIBLE);
        }



        ((ImageButton) root_view.findViewById(R.id.bt_close)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        ((ImageButton) root_view.findViewById(R.id.bt_edtCancel)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewMode.setVisibility(View.VISIBLE);
                editMode.setVisibility(View.GONE);
            }
        });
        ((ImageView) root_view.findViewById(R.id.edit)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtAnswer.requestFocus();
                edtAnswer.setFocusable(true);
                editMode.setVisibility(View.VISIBLE);
                viewMode.setVisibility(View.GONE);
            }
        });
        ((ImageButton) root_view.findViewById(R.id.bt_Save)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveAnswer();
            }
        });

        Name.setText(bundle.getString(questinedBy));
        Date.setText(bundle.getString(questinedOn));
        title.setText(bundle.getString(questinedTitle));
        question.setText(bundle.getString(questinedDesc));
        answer.setText(bundle.getString(questinedAnswer));
        edtAnswer.setText(bundle.getString(questinedAnswer));

        if (bundle.containsKey(isAnswered)) {
            answered = bundle.getString(isAnswered);
            needclarification = bundle.getString(needClarification);

            if (answered.equals("0") && needclarification.equals("0")) {
                root_view.findViewById(R.id.need_Clearification).setVisibility(View.VISIBLE);
                root_view.findViewById(R.id.need_Clearification).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setNeedClearification(bundle.getString(QuestionID), root_view);
                    }
                });
            } else if (answered.equals("0") && needclarification.equals("1")) {
                root_view.findViewById(R.id.status).setVisibility(View.VISIBLE);
            }

        }

        return root_view;
    }

    private void setNeedClearification(String qid, View root_view) {
        final Progress progress = new Progress(getActivity());
        progress.show();
        Call<CategoryOperationsEditResponse> call = RetrofitClient.getInstance().getApi().NeedClarification(qid);
        call.enqueue(new Callback<CategoryOperationsEditResponse>() {
            @Override
            public void onResponse(Call<CategoryOperationsEditResponse> call, Response<CategoryOperationsEditResponse> response) {
                progress.dismiss();
                if (response.isSuccessful()) {
                    if (response.body().isError())
                        Constants.Alert(getActivity(), response.body().getErrormsg());
                    else {
                        Constants.Alert(getActivity(), response.body().getErrormsg());
                        root_view.findViewById(R.id.need_Clearification).setVisibility(View.GONE);
                    }


                }

            }

            @Override
            public void onFailure(Call<CategoryOperationsEditResponse> call, Throwable t) {
                progress.dismiss();
            }
        });
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


    private void SaveAnswer() {
        final Progress progress = new Progress(getActivity());
        progress.show();
        Call<UploadTestimonialResponse> addYouTubeLink = RetrofitClient.getInstance().getApi().EditAnswer(edtAnswer.getText().toString(),
                USER.getUMID(), bundle.getString(AnswerID));
        addYouTubeLink.enqueue(new Callback<UploadTestimonialResponse>() {
            @Override
            public void onResponse(Call<UploadTestimonialResponse> call, Response<UploadTestimonialResponse> response) {
                progress.dismiss();

                if (response.isSuccessful()) {
                    if (response.body().isError()) {
                        Constants.Alert(getActivity(), response.body().getErrormsg());
                    } else {
                        new MaterialAlertDialogBuilder(getActivity())
                                .setTitle("Alert")
                                .setMessage(response.body().getErrormsg())
                                .setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        listener.onFinishEditDialog(true);
                                        dismiss();

                                    }
                                })
                                .show();
                    }
                }

            }

            @Override
            public void onFailure(Call<UploadTestimonialResponse> call, Throwable t) {
                progress.dismiss();
            }
        });
    }



}