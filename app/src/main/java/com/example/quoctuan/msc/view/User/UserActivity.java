package com.example.quoctuan.msc.view.User;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quoctuan.msc.Common.Common;
import com.example.quoctuan.msc.Connect.DownloadJson;
import com.example.quoctuan.msc.R;
import com.example.quoctuan.msc.model.ParserJson.ParserJsonLogin;
import com.example.quoctuan.msc.view.Main.MainFragment.OfflineFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class UserActivity extends AppCompatActivity implements View.OnClickListener {
    private Toolbar user_toolbar;
    private TextView user_email;
    private ImageView uset_img_status;
    private Button user_btn_logout, user_btn_new_password;

    private SharedPreferences sharedPreferences;
    private Dialog dialog;

    //dialog
    private TextView dialog_newpass_txt_oldpassword, dialog_newpass_txt_newpassword, dialog_newpass_txt_request_newpassword;
    private Button dialog_newpass_btn_cancel, dialog_newpass_btn_ok;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        sharedPreferences = getSharedPreferences("InforUser", MODE_PRIVATE);
        addControls();
        addData();
        addEvents();
    }


    private void addControls() {
        user_toolbar          = findViewById(R.id.user_toolbar);
        user_email            = findViewById(R.id.user_email);
        uset_img_status       = findViewById(R.id.uset_img_status);
        user_btn_logout       = findViewById(R.id.user_btn_logout);
        user_btn_new_password = findViewById(R.id.user_btn_new_password);
    }

    private void addData() {
        user_toolbar.setTitle("User");
        user_toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black);
        user_email.setText(sharedPreferences.getString("email", ""));
        if (sharedPreferences.getBoolean("status", false)){
            uset_img_status.setImageLevel(1);
        }else {
            uset_img_status.setImageLevel(0);
        }
    }

    private void addEvents() {
        user_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        user_btn_logout.setOnClickListener(this);
        user_btn_new_password.setOnClickListener(this);
    }

    private void ShowDialogNewPassword(){
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_new_password);

        addControlsDialog();
        addEventsDialog();

        dialog.show();
        Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
    }

    private void addControlsDialog() {
        dialog_newpass_txt_oldpassword         = dialog.findViewById(R.id.dialog_newpass_txt_oldpassword);
        dialog_newpass_txt_newpassword         = dialog.findViewById(R.id.dialog_newpass_txt_newpassword);
        dialog_newpass_txt_request_newpassword = dialog.findViewById(R.id.dialog_newpass_txt_request_newpassword);
        dialog_newpass_btn_cancel              = dialog.findViewById(R.id.dialog_newpass_btn_cancel);
        dialog_newpass_btn_ok                  = dialog.findViewById(R.id.dialog_newpass_btn_ok);
    }

    private void addEventsDialog() {
        dialog_newpass_btn_cancel.setOnClickListener(this);
        dialog_newpass_btn_ok.setOnClickListener(this);
    }

    private void RegisNewPassword(){
        if (CheckInfoNewPassword()){
            List<HashMap<String, String>> attr = new ArrayList<>();
            HashMap<String, String> HmController = new HashMap<>();
            HmController.put("c", Common.USER);
            HashMap<String, String> HmAction = new HashMap<>();
            HmAction.put("a", Common.NEWPASSWORD);
            HashMap<String, String> HmID = new HashMap<>();
            HmID.put("id", String.valueOf(sharedPreferences.getInt("id", 0)));
            HashMap<String, String> HmNewPassword = new HashMap<>();
            HmNewPassword.put("password", String.valueOf(dialog_newpass_txt_newpassword.getText()));

            attr.add(HmController);
            attr.add(HmAction);
            attr.add(HmID);
            attr.add(HmNewPassword);

            DownloadJson downloadJson = new DownloadJson(attr);
            downloadJson.execute(Common.URL_API);

            try {
                if (new ParserJsonLogin().ParserJsonNewPassword(downloadJson.get()))
                    ChangePasswordSuccess();
                else ChangePasswordFail();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    private void ChangePasswordSuccess() {
        Toast.makeText(this, "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("password", dialog_newpass_txt_newpassword.getText().toString());
        editor.commit();
        editor.apply();
        dialog.dismiss();
    }

    private void ChangePasswordFail() {
        dialog.dismiss();
        Toast.makeText(this, "Đổi mật khẩu thất bại", Toast.LENGTH_SHORT).show();
    }

    private boolean CheckInfoNewPassword(){
        if (dialog_newpass_txt_oldpassword.getText().equals("")){
            dialog_newpass_txt_oldpassword.setError("Bạn cần nhập mật khẩu cũ của bạn");
            return false;
        }else
        if (!dialog_newpass_txt_oldpassword.getText().toString().equals(sharedPreferences.getString("password", ""))){
            dialog_newpass_txt_oldpassword.setError("Bạn cần nhập chính xác mật khẩu cũ");
            return false;
        }else
        if (dialog_newpass_txt_newpassword.getText().equals("")){
            dialog_newpass_txt_newpassword.setError("Bạn cần nhập mật khẩu mới");
            return false;
        }else
        if (!dialog_newpass_txt_request_newpassword.getText().toString().equals(dialog_newpass_txt_newpassword.getText().toString())){
            dialog_newpass_txt_request_newpassword.setError("Bạn cần nhập đúng với ô trên");
            return false;
        }else return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.user_btn_logout:
                sharedPreferences.edit().clear().apply();
                onBackPressed();
                new OfflineFragment().CheckUserName();
                break;
            case R.id.user_btn_new_password:
                sharedPreferences = null;
                sharedPreferences = getSharedPreferences("InforUser", MODE_PRIVATE);
                ShowDialogNewPassword();
                break;
            case R.id.dialog_newpass_btn_cancel:
                dialog.dismiss();
                break;
            case R.id.dialog_newpass_btn_ok:
                RegisNewPassword();
                break;
        }
    }
}
