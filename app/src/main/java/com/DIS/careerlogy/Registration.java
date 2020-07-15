package com.DIS.careerlogy;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;
import com.DIS.careerlogy.Activity.UserVerification;
import com.DIS.careerlogy.Extra.Constants;
import com.DIS.careerlogy.Extra.Progress;
import com.DIS.careerlogy.Models.CitiesInStateItem;
import com.DIS.careerlogy.Models.CitiesModel;
import com.DIS.careerlogy.Models.ModelUserCategory;
import com.DIS.careerlogy.Models.RegisterResponse;
import com.DIS.careerlogy.Models.StateModel;
import com.DIS.careerlogy.Models.UserCategoryItem;
import com.DIS.careerlogy.Network.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Registration extends AppCompatActivity {
    private static final String TAG = "Registration";

    List<String> state = new ArrayList<>();
    List<String> cities = new ArrayList<>();
    List<String> studentCategory = new ArrayList<>();
    List<String> entrepreneurCategory = new ArrayList<>();
    RadioGroup rgUserType;
    Progress progress;
    TextView txtUserType;
    Button  dropdGender;
    Button  dropDownState;
    Button  dropDownAge;
    Button  dropdownSlection;
    Button  dropDownCity;
    Button  SignUp;
    TextInputEditText rgFname;
    TextInputEditText rgLname;
    TextInputEditText rgEmail;
    TextInputEditText rgMobile;
    TextInputEditText rgPassword;
    TextInputEditText rgCPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        state = getStateList();

        studentCategory = getUserCategory("Student");
        entrepreneurCategory = getUserCategory("Entrepreneur");
        rgUserType = findViewById(R.id.rgUserType);
        txtUserType = findViewById(R.id.txtUserType);
        SignUp = findViewById(R.id.Signup);

        rgFname    = findViewById(R.id.fname);
        rgLname    = findViewById(R.id.Lname);
        rgEmail    = findViewById(R.id.email);
        rgMobile   = findViewById(R.id.mobile);
        rgPassword = findViewById(R.id.password);
        rgCPassword= findViewById(R.id.Cpassword);

        progress = new Progress(this);
        setupDropDown();

        rgUserType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = findViewById(checkedId);
                txtUserType.setText(radioButton.getText().toString());
                Button dropdownSlection= findViewById(R.id.dropdownSlection);
                dropdownSlection.setText("Choose");
            }
        });

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isValidForm())
                    RegisterUser();
            }
        });

        findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            }
        });

    }

    private void RegisterUser() {
    progress.show();
        Call<RegisterResponse> call = RetrofitClient.getInstance().getApi().Registration("U",
                rgFname.getText().toString()+" "+rgLname.getText().toString(),
                dropDownState.getText().toString(),
                rgMobile.getText().toString(),
                dropdGender.getText().toString(),
                rgEmail.getText().toString(),
                dropDownAge.getText().toString(),
                "India",
                dropDownCity.getText().toString(),
                txtUserType.getText().toString(),
                dropdownSlection.getText().toString(),
                rgPassword.getText().toString()
        );
        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                progress.dismiss();
                if (response.body().isError()){
                    Constants.Alert(Registration.this, response.body().getMessage());
                }else {
                    Toast.makeText(Registration.this, "OTP has been sent to you on your mobile phone", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), UserVerification.class).putExtra("mob",rgMobile.getText().toString()));
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                progress.dismiss();
            }
        });

    }

    private void setupDropDown() {
     dropdGender      =  ((Button) findViewById(R.id.dropdGender));
     dropDownState    =  ((Button) findViewById(R.id.dropDownState));
     dropDownAge      =  ((Button) findViewById(R.id.dropDownAge));
     dropdownSlection = ((Button) findViewById(R.id.dropdownSlection));
     dropDownCity = ((Button) findViewById(R.id.dropDownCity));



        dropdGender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChoiceDialog((Button) v, new String[]{"Male", "Female"});
            }
        });
        dropDownAge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCalender(v);
            }
        });
        dropDownState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] stateArray = new String[0];
                if (!state.isEmpty()) {
                    stateArray = new String[state.size()];
                    stateArray = state.toArray(stateArray);
                } else {
                    state = getStateList();
                    stateArray = new String[state.size()];
                    stateArray = state.toArray(stateArray);
                }
                showChoiceDialog((Button) v, stateArray);
            }
        });
        dropdownSlection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] array = new String[0];

                if (rgUserType.getCheckedRadioButtonId() ==  R.id.rbStudent){
                    if (!studentCategory.isEmpty()) {
                        array = new String[studentCategory.size()];
                        array = studentCategory.toArray(array);
                    } else {
                        studentCategory = getUserCategory("Student");
                        array = new String[studentCategory.size()];
                        array = studentCategory.toArray(array);
                    }
                }else {
                    if (!entrepreneurCategory.isEmpty()) {
                        array = new String[entrepreneurCategory.size()];
                        array = entrepreneurCategory.toArray(array);
                    } else {
                        entrepreneurCategory = getUserCategory("Entrepreneur");
                        array = new String[entrepreneurCategory.size()];
                        array = entrepreneurCategory.toArray(array);
                    }
                }


                showChoiceDialog((Button) v, array);
            }
        });
        dropDownCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] citiesArray = new String[0];
                if (!state.isEmpty()) {
                    citiesArray = new String[cities.size()];
                    citiesArray = cities.toArray(citiesArray);
                }
                showChoiceDialog((Button) v, citiesArray);
            }
        });
    }

    private void showCalender(final View v) {
        MaterialDatePicker.Builder<Long> builder =
                MaterialDatePicker.Builder.datePicker();
        MaterialDatePicker<Long> picker = builder.build();
        picker.show(getSupportFragmentManager(), picker.toString());
        picker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>() {
            @Override
            public void onPositiveButtonClick(Long selection) {
             Button button= findViewById(v.getId());
             button.setText(Constants.longToDate("YYYY-MM-dd",selection));
                if (button.getError()!=null)
                    button.setError(null);

            }
        });
    }

    private List<String> getStateList() {
        final List<String> list = new ArrayList<>();
        Call<StateModel> stateModelCall = RetrofitClient.getInstance().getApi().stateList();
        stateModelCall.enqueue(new Callback<StateModel>() {
            @Override
            public void onResponse(Call<StateModel> call, Response<StateModel> response) {
                Log.d(TAG, "onResponse: ");
                assert response.body() != null;
                List<StateModel.StateListItem> stateLists = response.body().getStateList();
                for (int i = 0; i < stateLists.size(); i++) {
                    Log.d(TAG, "onResponse: " + stateLists.get(i).getStatename());
                    list.add(stateLists.get(i).getStatename());
                }
            }

            @Override
            public void onFailure(Call<StateModel> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
            }
        });
        return list;

    }
    private List<String> getUserCategory(String type) {
        final List<String> list = new ArrayList<>();
        Call<ModelUserCategory> stateModelCall = RetrofitClient.getInstance().getApi().getUserCategory(type);
        stateModelCall.enqueue(new Callback<ModelUserCategory>() {
            @Override
            public void onResponse(Call<ModelUserCategory> call, Response<ModelUserCategory> response) {
                Log.d(TAG, "onResponse: ");
                assert response.body() != null;
                List<UserCategoryItem> stateLists = response.body().getUserCategory();
                for (int i = 0; i < stateLists.size(); i++) {
                    Log.d(TAG, "onResponse: " + stateLists.get(i).getUserCategorySubName());
                    list.add(stateLists.get(i).getUserCategorySubName());
                }
            }

            @Override
            public void onFailure(Call<ModelUserCategory> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
            }
        });
        return list;

    }
    private void getCitiesInState(String stateName) {
        progress.show();
        Call<CitiesModel> stateModelCall = RetrofitClient.getInstance().getApi().getCitiesInState(stateName);
        stateModelCall.enqueue(new Callback<CitiesModel>() {
            @Override
            public void onResponse(Call<CitiesModel> call, Response<CitiesModel> response) {
                Log.d(TAG, "onResponse: ");
                progress.dismiss();
                cities.clear();
                assert response.body() != null;
                List<CitiesInStateItem> stateLists = response.body().getCitiesInState();
                for (int i = 0; i < stateLists.size(); i++) {
                    Log.d(TAG, "onResponse: " + stateLists.get(i).getCityname());
                    cities.add(stateLists.get(i).getCityname());
                }
            }

            @Override
            public void onFailure(Call<CitiesModel> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
                progress.dismiss();
            }
        });
    }

    private void showChoiceDialog(final Button v, final String[] strings) {
        final MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this);
        builder.setCancelable(true);
        builder.setItems(strings, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                v.setText(strings[which]);
                if (v.getError()!=null)
                    v.setError(null);

                if (v.getId()==R.id.dropDownState)
                    getCitiesInState(strings[which]);

            }
        });
        builder.show();
    }

    public Boolean isValidForm() {

        if (rgFname.getText().toString().isEmpty()) {
            Constants.Alert(Registration.this, "Please Enter Your First Name");
            rgFname.setError("Please Enter Your First Name");
            return false;
        } else if (rgLname.getText().toString().isEmpty()) {
            Constants.Alert(Registration.this, "Please Enter Your Last Name");
            rgLname.setError("Please Enter Your Last Name");
            return false;
        } else if (dropdGender.getText().toString().equals("Choose")) {
            Constants.Alert(Registration.this, "Please Select Your Gender");
            dropdGender.setError("Please Select Your Gender");
            return false;
        } else if (dropDownAge.getText().toString().equals("Choose")) {
            Constants.Alert(Registration.this, "Please Select Your Date of Birth");
            dropDownAge.setError("Please Select Your Date of Birth");
            return false;
        } else if (rgEmail.getText().toString().isEmpty()) {
            Constants.Alert(Registration.this, "Please Enter Your Email address");
            rgEmail.setError("Please Enter Your Email address");
            return false;
        } else if (rgMobile.getText().toString().isEmpty()) {
            Constants.Alert(Registration.this, "Please Enter Your Phone no");
            rgMobile.setError("Please Enter Your Phone no");
            return false;
        } else if (rgMobile.getText().toString().length() > 10) {
            Constants.Alert(Registration.this, "Please Enter Valid Phone no");
            rgMobile.setError("Please Enter Valid Phone no");
            return false;
        } else if (dropDownState.getText().toString().equals("Choose")) {
            Constants.Alert(Registration.this, "Please Select Your State");
            dropDownState.setError("Please Select Your State");
            return false;
        } else if (dropDownCity.getText().toString().equals("Choose")) {
            Constants.Alert(Registration.this, "Please Select Your City");
            dropDownCity.setError("Please Select Your City");
            return false;
        } else if (dropdownSlection.getText().toString().equals("Choose")) {
            Constants.Alert(Registration.this, "Please Select User Category");
            dropdownSlection.setError("Please Select User Category");
            return false;
        } else if (rgPassword.getText().toString().isEmpty()) {
            Constants.Alert(Registration.this, "Please Enter password");
            rgPassword.setError("Please Enter password");
            return false;
        } else if (rgPassword.getText().toString().length() < 6) {
            Constants.Alert(Registration.this, "Password length must be 6 or greater");
            rgPassword.setError("Password length must be 6 or greater");
            return false;
        }
        if (!rgPassword.getText().toString().equals(rgCPassword.getText().toString())) {
            Constants.Alert(Registration.this, "Password not match");
            rgCPassword.setError("Password not match");
            return false;
        } else
            return true;
    }
}
