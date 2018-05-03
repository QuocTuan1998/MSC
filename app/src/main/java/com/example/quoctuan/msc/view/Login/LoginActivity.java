package com.example.quoctuan.msc.view.Login;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Patterns;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.example.quoctuan.msc.Common.Common;
import com.example.quoctuan.msc.Connect.DownloadJson;
import com.example.quoctuan.msc.R;
import com.example.quoctuan.msc.model.ParserJson.ParserJsonLogin;
import com.example.quoctuan.msc.model.User;
import com.example.quoctuan.msc.model.Users;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText login_ed_name, login_ed_pass;
    private Button login_btn_sign_in;
    private TextInputLayout login_til_name, login_til_pass;
    private ProgressDialog progressDialog;
    private LinearLayout linear_login;
    private Toolbar login_toolbar;

    private DownloadJson downloadJson;
    private ParserJsonLogin parserJsonLogin;
    private SharedPreferences sharedPreferences;
    private AnimationDrawable animationDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        addControls();
        addEvents();

    }

    private void addControls() {
        login_btn_sign_in   = findViewById(R.id.login_btn_sign_in);
        login_ed_name       = findViewById(R.id.login_edit_name);
        login_ed_pass       = findViewById(R.id.login_edit_pass);
        login_til_name      = findViewById(R.id.login_til_name);
        login_til_pass      = findViewById(R.id.login_til_pass);
        linear_login        = findViewById(R.id.linear_login);
        login_toolbar       = findViewById(R.id.login_toolbar);

        //animation background cho man hinh login
        animationDrawable = (AnimationDrawable) linear_login.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(2000);
        animationDrawable.start();
        //end animation background cho man hinh login

        //dấu mũi tên bên trái để trở về
        setSupportActionBar(login_toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        login_toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        login_toolbar.setTitle(getResources().getString(R.string.Login));
        //end dấu mũi tên bên trái để trở về
    }

    private void addEvents() {
        login_btn_sign_in.setOnClickListener(this);
        login_til_pass.setOnFocusChangeListener(this);
        login_til_name.setOnFocusChangeListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login_btn_sign_in:
                progressDialog = new ProgressDialog(this);
                progressDialog.setTitle("Đang xử lí đăng nhập");
                progressDialog.setMessage("vui lòng đợi chúng tôi trong giây lát");

                if (CheckInforLogin())

                    Login();
                break;
        }
    }

    private void Login() {
        List<HashMap<String, String>> attr = new ArrayList<>();

        //truyền theo dạng post
        HashMap<String, String> hm_controller = new HashMap<>();
        hm_controller.put("c", "user");
        HashMap<String, String> hm_function = new HashMap<>();
        hm_function.put("a", "login");
        HashMap<String, String> hm_email = new HashMap<>();
        hm_email.put("email", login_ed_name.getText().toString());
        HashMap<String, String> hm_password = new HashMap<>();
        hm_password.put("password", login_ed_pass.getText().toString());

        attr.add(hm_controller);
        attr.add(hm_function);
        attr.add(hm_email);
        attr.add(hm_password);

        downloadJson = new DownloadJson(attr);
        downloadJson.execute(getResources().getString(R.string.link_login));

        try {
            if (!downloadJson.get().isEmpty()){ //nếu downloadJson.get() có dữ liệu là <=> đăng nhập thành công
                parserJsonLogin = new ParserJsonLogin();
                LoginSuccess(parserJsonLogin.PaserJsonLogin(downloadJson.get()));
                progressDialog.dismiss();
            }else {
                LoginFail(); //ngược lại đăng nhập thất bại
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    //Đăng nhập thất bại và thông báo lỗi lên TextInputsLayout
    private void LoginFail() {
        login_til_name.setError("Có thể bạn nhập sai email");
        login_til_pass.setError("Có thể bạn nhập sai mật khẩu");
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                }catch (Exception e){}
                finally {
                    progressDialog.dismiss();
                }
            }
        });

        thread.start();
    }//end Đăng nhập thất bại và thông báo lỗi lên TextInputsLayout

    //Đăng nhập thành công và ghi xuống sharedPreferences
    private void LoginSuccess(User user) {
        sharedPreferences = getSharedPreferences("InforUser", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("id", user.getId());
        editor.putString("email", user.getEmail());
        editor.putString("password", user.getPassword());
        editor.putBoolean("role", user.isRole());
        editor.putBoolean("status", user.isStatus());
        editor.putString("token", user.getToken());
        editor.commit();
        editor.apply();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (Exception e) {
                } finally {
                    progressDialog.dismiss();
                    login_ed_name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                        @Override
                        public void onFocusChange(View view, boolean b) {
                            if (b) {
                                login_til_name.setErrorEnabled(false);
                            }
                        }
                    });
                    login_ed_pass.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                        @Override
                        public void onFocusChange(View view, boolean b) {
                            if (b) {
                                login_til_pass.setErrorEnabled(false);
                            }
                        }
                    });

                    thread.start();
                    Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                }//end Đăng nhập thành công

                //kiểm tra thông tin đăng nhập

            private boolean CheckInforLogin() {
                if (login_ed_name.getText().toString().isEmpty() || login_ed_pass.getText().toString().isEmpty()) {
                    if (login_ed_name.getText().toString().isEmpty())
                        login_til_name.setError("Bạn cần nhập địa chỉ email");
                    if (login_ed_pass.getText().toString().isEmpty())
                        login_ed_pass.setError("Bạn cần nhập mật khẩu");
                    return false;
                } else if (!Patterns.EMAIL_ADDRESS.matcher(login_ed_name.getText().toString()).matches()) {
                    login_til_name.setError("Bạn cần nhập địa chỉ email gồm abc@gmail.com");
                    return false;
                } else return true;
            }
            //end kiểm tra thông tin đăng nhập

            //sự kiện lắng nghe textInputLayout có được nhấn hay không
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    switch (view.getId()) {
                        case R.id.login_til_name:
                            login_til_name.setErrorEnabled(false);
                            break;
                        case R.id.login_til_pass:
                            login_til_pass.setEnabled(false);
                            break;
                    }
                }
            }
            //end sự kiện lắng nghe textInputLayout có được nhấn hay không
        login_toolbar.setNavigationOnClickListener(new View.OnClickListener()

            {
                @Override
                public void onClick (View view){
                onBackPressed();
            }
            });
        }

        @Override
        public void onClick (View view){
            switch (view.getId()) {
                case R.id.login_btn_sign_in:
                    progressDialog = new ProgressDialog(this);
                    progressDialog.setTitle("Đang xử lí đăng nhập");
                    progressDialog.setMessage("vui lòng đợi chúng tôi trong giây lát");
                    progressDialog.show();
                    if (CheckInforLogin())
                        Login();
                    break;
            }
        }

        private void Login () {
            List<HashMap<String, String>> attr = new ArrayList<>();

            //truyền theo dạng post
            HashMap<String, String> hm_controller = new HashMap<>();
            hm_controller.put("c", Common.USER);
            HashMap<String, String> hm_function = new HashMap<>();
            hm_function.put("a", Common.LOGIN);

            HashMap<String, String> hm_email = new HashMap<>();
            hm_email.put("email", login_ed_name.getText().toString());
            HashMap<String, String> hm_password = new HashMap<>();
            hm_password.put("password", login_ed_pass.getText().toString());

            attr.add(hm_controller);
            attr.add(hm_function);
            attr.add(hm_email);
            attr.add(hm_password);

            downloadJson = new DownloadJson(attr);
            downloadJson.execute(getResources().getString(R.string.link_login));

            try {
                if (!downloadJson.get().isEmpty()) { //nếu downloadJson.get() có dữ liệu là <=> đăng nhập thành công
                    parserJsonLogin = new ParserJsonLogin();
                    LoginSuccess(parserJsonLogin.PaserJsonLogin(downloadJson.get()));
                } else {
                    LoginFail(); //ngược lại đăng nhập thất bại
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        
