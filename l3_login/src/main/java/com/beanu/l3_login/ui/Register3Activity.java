package com.beanu.l3_login.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.beanu.arad.base.ToolBarActivity;
import com.beanu.arad.support.log.KLog;
import com.beanu.l2_imageselector.GlideLoader;
import com.beanu.l3_login.R;
import com.beanu.l3_login.mvp.contract.RegisterContract;
import com.beanu.l3_login.mvp.model.RegisterModelImpl;
import com.beanu.l3_login.mvp.presenter.RegisterPresenterImpl;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.yanzhenjie.album.Action;
import com.yanzhenjie.album.Album;
import com.yanzhenjie.album.AlbumFile;

import java.util.ArrayList;
import java.util.List;


/**
 * 注册第三步,设置头像和昵称
 */
public class Register3Activity extends ToolBarActivity<RegisterPresenterImpl, RegisterModelImpl> implements View.OnClickListener, RegisterContract.View {

    ImageView mImgRegisterAvatar;
    EditText mEditRegisterNickname;
    Button mBtnRegisterComplete;

    private String phone, password, yzm, nickname;
    private String imgPath;

    public static final int REQUEST_CODE = 1000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register3);

        mImgRegisterAvatar = findViewById(R.id.img_register_avatar);
        mEditRegisterNickname = findViewById(R.id.edit_register_nickname);
        mBtnRegisterComplete = findViewById(R.id.btn_register_complete);

        mImgRegisterAvatar.setOnClickListener(this);
        mBtnRegisterComplete.setOnClickListener(this);

        phone = getIntent().getStringExtra("phone");
        password = getIntent().getStringExtra("password");
        yzm = getIntent().getStringExtra("yzm");

        mEditRegisterNickname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 0) {
                    mBtnRegisterComplete.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.img_register_avatar) {
            showImageSelector();

        } else if (i == R.id.btn_register_complete) {
            nickname = mEditRegisterNickname.getText().toString();

            mPresenter.register(phone, password, yzm, nickname);


        }
    }



    @Override
    public String setupToolBarTitle() {
        return "设置信息";
    }

    @Override
    public boolean setupToolBarLeftButton(View leftButton) {
        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        return true;
    }


    //打开图片选择器
    private void showImageSelector() {

        Album.image(this) // Image selection.
                .multipleChoice()
                .camera(true)
                .columnCount(3)
                .selectCount(1)
                .onResult(new Action<ArrayList<AlbumFile>>() {
                    @Override
                    public void onAction(int requestCode, @NonNull ArrayList<AlbumFile> result) {
                        imgPath = result.get(0).getPath();
                        Glide.with(Register3Activity.this).load(imgPath).apply(RequestOptions.centerCropTransform()).into(mImgRegisterAvatar);
                        //上传到服务器
                        mPresenter.uploadAvatar(imgPath);
                    }
                }).start();
    }


    @Override
    public void registerSuccess() {
        //注册成功
        KLog.d("注册成功3");
    }

    @Override
    public void registerFail(String msg) {
        //注册失败
    }

    @Override
    public void obtainSMS(String smsCode) {
        //nothing
    }

}
