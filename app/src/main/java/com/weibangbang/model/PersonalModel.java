package com.weibangbang.model;

import com.weibangbang.api.body.ChangePasswordBody;
import com.weibangbang.api.body.InformationBody;
import com.weibangbang.api.body.TokenBody;
import com.weibangbang.api.body.WithDrawalBody;
import com.weibangbang.base.BaseView;

import java.io.File;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/10/11 9:08
 * 功能描述：
 */
public class PersonalModel extends BaseModel {
    /**
     * 个人中心-总收入，今日收入，已提现接口(新2)
     */
    public void postPersonalPage(String token, BaseView baseView) {
        TokenBody tokenBody = new TokenBody();
        tokenBody.setToken(token);
        mCall = mApiService.postPersonalPage(tokenBody);
        request(baseView);
    }

    /**
     * 个人信息修改显示页接口
     */
    public void postInformationDisplay(String token, BaseView baseView) {
        TokenBody tokenBody = new TokenBody();
        tokenBody.setToken(token);
        mCall = mApiService.postInformationDisplay(tokenBody);
        request(baseView);
    }

    /**
     * 个人信息修改页接口
     *
     * @param token
     * @param name
     * @param sex
     * @param age
     * @param address
     */
    public void postInformation(String token, String name, String sex, String age, String address, BaseView baseView) {
        InformationBody informationBody = new InformationBody();
        informationBody.setToken(token);
        informationBody.setName(name);
        informationBody.setSex(sex);
        informationBody.setAge(age);
        informationBody.setDowntown(address);
        mCall = mApiService.postInformation(informationBody);
        request(baseView);
    }

    /**
     * 我的团队-我的一级会员接口
     */
    public void postTeam11J(String token, BaseView baseView) {
        TokenBody tokenBody = new TokenBody();
        tokenBody.setToken(token);
        mCall = mApiService.postTeam11J(tokenBody);
        request(baseView);
    }

    /**
     * 我的团队-我的二级会员接口
     */
    public void postTeam22J(String token, BaseView baseView) {
        TokenBody tokenBody = new TokenBody();
        tokenBody.setToken(token);
        mCall = mApiService.postTeam22J(tokenBody);
        request(baseView);
    }

    /**
     * 我的钱包
     */
    public void postMyWallet(String token, BaseView baseView) {
        TokenBody tokenBody = new TokenBody();
        tokenBody.setToken(token);
        mCall = mApiService.postMyWallet(tokenBody);
        request(baseView);
    }

    /**
     *可提现金额
     */
    public void postUserBalance(String token, BaseView baseView) {
        TokenBody tokenBody = new TokenBody();
        tokenBody.setToken(token);
        mCall = mApiService.postUserBalance(tokenBody);
        request(baseView);
    }

    /**
     *提现
     * @param money
     * @param way
     * @param account
     */
    public void postWithDrawal(String token,String money, String way, String account, BaseView baseView) {
        WithDrawalBody withDrawalBody = new WithDrawalBody();
        withDrawalBody.setToken(token);
        withDrawalBody.setMoney(money);
        withDrawalBody.setWay(way);
        withDrawalBody.setAccount(account);
        mCall = mApiService.postWithDrawal(withDrawalBody);
        request(baseView);
    }

    /**
     * 修改密码接口
     *
     * @param token
     * @param old_pass 旧密码
     * @param new_pass 新密码
     */
    public void postChangePassword(String token, String old_pass, String new_pass, BaseView baseView) {
        ChangePasswordBody changePasswordBody = new ChangePasswordBody();
        changePasswordBody.setToken(token);
        changePasswordBody.setOld_pass(old_pass);
        changePasswordBody.setNew_pass(new_pass);
        mCall = mApiService.postChangePassword(changePasswordBody);
        request(baseView);
    }


    /**
     * 退出登录接口
     */
    public void postLoginOut(String token, BaseView baseView) {
        TokenBody tokenBody = new TokenBody();
        tokenBody.setToken(token);
        mCall = mApiService.postLoginOut(tokenBody);
        request(baseView);
    }

    /**
     * 图片上传
     *
     * @param fileList
     * @param baseView
     */
    public void postUpLoad(List<File> fileList, BaseView baseView) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), fileList.get(0));
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("image", fileList.get(0).getName(), requestBody);
        mCall = mApiService.postUpLoad(body);
        request(baseView);
    }
}
