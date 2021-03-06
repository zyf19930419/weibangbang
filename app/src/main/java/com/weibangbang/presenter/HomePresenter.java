package com.weibangbang.presenter;

import com.weibangbang.base.BaseView;
import com.weibangbang.model.HomeModel;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/10/9 9:21
 * 功能描述：
 */
public class HomePresenter extends BasePresenter {

    private HomeModel mModel;

    public HomePresenter(BaseView baseView) {
        super(baseView);
        mModel = new HomeModel();
    }

    /**
     * 首页轮播图接口
     *
     */
    public void postBanner() {
        mModel.postBanners(mBaseView);
    }

    /**
     * 首页公告列表接口
     */
    public void postNotice() {
        mModel.postNotice(mBaseView);
    }

    /**
     * 首页公告详情接口
     */
    public void postNoticeDetails(String notice_id) {
        mModel.postNoticeDetails(notice_id, mBaseView);
    }

    /**
     * 我要赚佣接口
     */
    public void postMakeMoney() {
        mModel.postMakeMoney(mBaseView);
    }

    /**
     *免费领用详情
     */
    public void postReceiveInfo(String token) {
        mModel.postReceiveInfo(token, mBaseView);
    }

    /**
     *免费领取提交接口
     */
    public void postReceiveCommit(String token,String name,String phone,String address) {
        mModel.postReceiveCommit(token,name,phone,address,mBaseView);
    }

    /**
     * 开通会员
     */
    public void postVipList(String token) {
        mModel.postVipList(token, mBaseView);
    }

    /**
     * 升级会员接口
     */
    public void postVipUpgrade(String token,String upgrade,String pay_way) {
        mModel.postVipUpgrade(token,upgrade,pay_way, mBaseView);
    }


    /**
     * 联系客服接口
     */
    public void postContactCustomerService() {
        mModel.postContactCustomerService(mBaseView);
    }

    /**
     * 投放广告接口
     */
    public void postLaunchCommit(String token, String user_launch_content, String user_launch_compellation, String user_launch_phone) {
        mModel.postLaunchCommit(token, user_launch_content, user_launch_compellation, user_launch_phone, mBaseView);
    }

    /**
     * 分享接口
     */
    public void postShare(String token) {
        mModel.postShare(token,mBaseView);
    }

    /**
     * 招商微信
     */
    public void postAttract() {
        mModel.postAttract(mBaseView);
    }

    /**
     * 公告信息
     */
    public void postAnnouncement() {
        mModel.postAnnouncement(mBaseView);
    }
}
