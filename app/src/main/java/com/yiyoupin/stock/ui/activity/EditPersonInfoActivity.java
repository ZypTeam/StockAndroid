package com.yiyoupin.stock.ui.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jph.takephoto.model.CropOptions;
import com.jph.takephoto.model.TResult;
import com.jusfoun.baselibrary.Util.PhoneUtil;
import com.jusfoun.baselibrary.Util.RegularUtils;
import com.jusfoun.baselibrary.base.NoDataModel;
import com.jusfoun.baselibrary.net.Api;
import com.yiyoupin.stock.R;
import com.yiyoupin.stock.comment.ApiService;
import com.yiyoupin.stock.comment.Constant;
import com.yiyoupin.stock.delegate.ApiUploadFiles;
import com.yiyoupin.stock.delegate.UserInfoDelegate;
import com.yiyoupin.stock.model.UploadDataModel;
import com.yiyoupin.stock.model.UserModel;
import com.yiyoupin.stock.ui.dialog.BottomDialog;
import com.yiyoupin.stock.ui.util.ImageLoderUtil;
import com.yiyoupin.stock.ui.util.TakeHelper;
import com.yiyoupin.stock.ui.view.BackTitleView;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * @author wangcc
 * @date 2018/3/25
 * @describe
 */

public class EditPersonInfoActivity extends BaseTakeActivity {

    protected BackTitleView titleView;
    protected TextView phone;
    protected EditText inputPhone;
    protected TextView code;
    protected ImageView image;
    protected EditText inputCode;
    protected TextView phoneCode;
    protected EditText inputPhoneCode;
    protected TextView txtUsername;
    protected EditText inputName;
    protected TextView txtNickname;
    protected EditText inputNickname;
    protected TextView txtEmail;
    protected EditText inputEmail;
    protected TextView txtWechart;
    protected EditText inputWechar;
    protected TextView submit;
    private TextView head;
    private ImageView iconHead;

    private Subscription timer;
    protected TextView timerTxt;
    private UserModel userModel;

    private BottomDialog bottomDialog;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_edit_person_info;
    }

    @Override
    public void initDatas() {

        bottomDialog = new BottomDialog(mContext, R.style.my_dialog);
        bottomDialog.setTxt("拍照", "从相册选取");
        bottomDialog.setTopListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TakeHelper.pickByTake(getTakePhoto());
                bottomDialog.dismiss();
            }
        });

        bottomDialog.setBottomListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TakeHelper.pickBySelect(takePhoto);
                bottomDialog.dismiss();
            }
        });
    }

    @Override
    public void initView() {
        head = findViewById(R.id.txt_head);
        iconHead = findViewById(R.id.icon_head);
        timerTxt = (TextView) findViewById(R.id.timer);
        titleView = (BackTitleView) findViewById(R.id.title_view);
        phone = (TextView) findViewById(R.id.phone);
        inputPhone = (EditText) findViewById(R.id.input_phone);
        code = (TextView) findViewById(R.id.code);
        image = (ImageView) findViewById(R.id.image);
        inputCode = (EditText) findViewById(R.id.input_code);
        phoneCode = (TextView) findViewById(R.id.phone_code);
        inputPhoneCode = (EditText) findViewById(R.id.input_phone_code);
        txtUsername = (TextView) findViewById(R.id.txt_username);
        inputName = (EditText) findViewById(R.id.input_name);
        txtNickname = (TextView) findViewById(R.id.txt_nickname);
        inputNickname = (EditText) findViewById(R.id.input_nickname);
        txtEmail = (TextView) findViewById(R.id.txt_email);
        inputEmail = (EditText) findViewById(R.id.input_email);
        txtWechart = (TextView) findViewById(R.id.txt_wechart);
        inputWechar = (EditText) findViewById(R.id.input_wechar);
        submit = (TextView) findViewById(R.id.submit);
    }

    @Override
    public void initAction() {

        userModel = UserInfoDelegate.getInstance().getUserInfo();

        ImageLoderUtil.loadCircleImage(mContext, iconHead, "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1522439396278&di=25e6651bf56bc21ebaaf4c3df36f2502&imgtype=0&src=http%3A%2F%2Fpic38.nipic.com%2F20140306%2F251960_125327345000_2.jpg", R.mipmap.ic_launcher_round);
        timerTxt.setOnClickListener(v -> {
            getCode();
        });
        head.setOnClickListener(v -> {
            bottomDialog.show();
        });

        submit.setOnClickListener(v -> {
            updateData();
        });
        updateView();
    }

    @Override
    public void takeSuccess(TResult result) {
        super.takeSuccess(result);
        if (result != null&&result.getImages()!=null
                &&result.getImages().size()>0) {
            uploadHead(result.getImages().get(0).getOriginalPath());
        }
    }

    private void uploadHead(String path) {
        showLoadDialog();
        ApiUploadFiles.uploadFiles(path)
                .subscribe(new Action1<UploadDataModel>() {
                    @Override
                    public void call(UploadDataModel uploadDataModel) {

                        hideLoadDialog();
                        if (uploadDataModel.getCode() == 0) {
                            userModel.setUser_picture(uploadDataModel.getData());
                            UserInfoDelegate.getInstance().saveUserInfo(userModel);
                            ImageLoderUtil.loadCircleImage(mContext, iconHead, userModel.getUser_picture(), R.mipmap.ic_launcher_round);
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        hideLoadDialog();
                    }
                });
    }

    private void updateView() {
        if (userModel != null) {
            ImageLoderUtil.loadCircleImage(mContext, iconHead, userModel.getUser_picture(), R.mipmap.ic_launcher_round);
            inputName.setText(userModel.getName());
            inputName.setSelection(inputName.getText().length());
            inputNickname.setText(userModel.getNick_name());
            inputEmail.setText(userModel.getEmail());

        }
    }

    private void getCode() {
        if (!RegularUtils.checkMobile(inputPhone.getText().toString())) {
            showToast("手机号不正确");
            return;
        }
        showLoadDialog();
        HashMap<String, String> params = new HashMap<>();
        params.put("phone", inputPhone.getText().toString());
        params.put("type", "2");
        addNetwork(Api.getInstance().getService(ApiService.class).getPhoneCode(params)
                , new Action1<NoDataModel>() {
                    @Override
                    public void call(NoDataModel noDataModel) {
                        hideLoadDialog();
                        if (noDataModel.getCode() == 0) {
                            timer = Observable.interval(1, TimeUnit.SECONDS)
                                    .take(120)
                                    .map(new Func1<Long, Long>() {
                                        @Override
                                        public Long call(Long aLong) {
                                            return 120 - aLong;
                                        }
                                    })
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(new Observer<Long>() {

                                        @Override
                                        public void onCompleted() {
                                            timerTxt.setEnabled(true);
                                            timerTxt.setText("重发");
                                        }

                                        @Override
                                        public void onError(Throwable e) {
                                            timerTxt.setEnabled(true);
                                            timerTxt.setText("重发");
                                        }

                                        @Override
                                        public void onNext(Long aLong) {
                                            timerTxt.setEnabled(false);
                                            timerTxt.setText(aLong + "s");
                                        }
                                    });
                            showToast(R.string.code_send);
                        }

                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        hideLoadDialog();
                        Toast.makeText(mContext, "获取失败", Toast.LENGTH_SHORT).show();
                    }
                });
    }


    private void updateData() {
        showLoadDialog();
        HashMap<String, String> params = new HashMap<>();
        params.put("name", inputName.getText().toString());
        params.put("nick_name", inputNickname.getText().toString());
        params.put("email", inputEmail.getText().toString());
        params.put("sex", "1");
        params.put("birthday", "2018-01-01");
//        params.put("id",UserInfoDelegate.getInstance().getUserId());
        addNetwork(Api.getInstance().getService(ApiService.class).editInfo(params)
                , new Action1<NoDataModel>() {
                    @Override
                    public void call(NoDataModel noDataModel) {

                        hideLoadDialog();
                        if (noDataModel.getCode() == 0) {
                            userModel.setName(inputName.getText().toString());
                            userModel.setNick_name(inputNickname.getText().toString());
                            userModel.setEmail(inputEmail.getText().toString());
                            userModel.setSex("1");
                            userModel.setBirthday("1990");
                            UserInfoDelegate.getInstance().saveUserInfo(userModel);
                            updateView();
                            rxManage.post(Constant.EDIT_USER,"");
                            showToast("修改成功");
                            onBackPressed();
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        hideLoadDialog();
                        showToast("修改失败");
                    }
                });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null && timer.isUnsubscribed()) {
            timer.unsubscribe();
        }
    }
}
