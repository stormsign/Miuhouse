package com.miuhouse.community.fragment;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.miuhouse.community.R;
import com.miuhouse.community.adapter.SimpleAdapter;
import com.miuhouse.community.bean.BaseBean;
import com.miuhouse.community.bean.DictsBean;
import com.miuhouse.community.http.FinalData;
import com.miuhouse.community.http.GsonRequest;
import com.miuhouse.community.http.VolleySingleton;
import com.miuhouse.community.utils.Constants;
import com.miuhouse.community.utils.SPUtils;
import com.miuhouse.community.utils.StringUtils;
import com.miuhouse.community.utils.ToastUtils;
import com.miuhouse.community.widget.ProgressFragment;
import com.orhanobut.dialogplus.DialogPlus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 租房子
 * Created by kings on 1/20/2016.
 */
public class RentingHouseFrxagment extends BuyAndRentHouseBaseFragment {
    private final static int TYPE = 2;
    private ProgressFragment progress;


    @Override
    public int provideViewId() {
        return R.layout.fragment_renting;
    }


    @Override
    public List<String> getPriceList() {
        return Arrays.asList(SPUtils.getSPData(Constants.ZFPRICE));
    }


    @Override
    public void viewfill() {
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRequest();
            }
        });
    }

    public void sendRequest() {
        showProgress();
        String urlPath = FinalData.URL_VALUE + "demand";
        Map<String, Object> map = new HashMap<>();
        map.put("propertyId", getPropertyID());
        map.put("type", TYPE);
        map.put("huxing", getHouseType());
        map.put("address", etAddress.getText().toString());
        map.put("price", getPrice());
        map.put("other", etMessage.getText().toString());
        map.put("mobile", etPhoneNumber.getText().toString());
        GsonRequest<BaseBean> request = new GsonRequest<>(Request.Method.POST, urlPath, BaseBean.class, map, new Response.Listener<BaseBean>() {
            @Override
            public void onResponse(BaseBean baseBean) {
//                ToastUtils.showToast(getActivity(), baseBean.getMsg());
                progress.dismissAllowingStateLoss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                progress.dismissAllowingStateLoss();
                ToastUtils.showToast(getActivity(), "提交失败");
            }
        });
        VolleySingleton.getInstance(getActivity()).addToRequestQueue(request);
    }

    private void showProgress() {
        progress = new ProgressFragment().newInstance();
        progress.setMessage("正在提交. . .");
        progress.show(getFragmentManager(), "renting");
    }

    public boolean isFill() {
        if (StringUtils.isEmpty(getEtAddressToString())) {
            etAddress.setError("请输入地址");
            etAddress.requestFocus();
            return true;
        }
        if (StringUtils.isEmpty(getPrice())) {
            ToastUtils.showToast(getActivity(), "请选择价格");
            return true;
        }
        if (StringUtils.isEmpty(getHouseType())) {
            ToastUtils.showToast(getActivity(), "请选择户型");
            return true;
        }
        if (StringUtils.isEmpty(getEtPhoneNumberToString())) {
            etPhoneNumber.setError("请输入电话号码");
            etPhoneNumber.requestFocus();
            return true;
        }
        return false;
    }

}
