package com.winfo.szrsp.app.mvp.plotting.view;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.winfo.dnc.sdk.Point;
import com.winfo.dnc.sdk.WinfoDNCView;
import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.activity.MainActivity;
import com.winfo.szrsp.app.mvp.plotting.presenter.PlottingPresenter;
import com.winfo.szrsp.app.sdk.entity.plotting.Plotting;
import com.winfo.szrsp.app.sdk.entity.plotting.PlottingListData;
import com.winfo.szrsp.app.sdk.utils.ACache;
import com.winfo.szrsp.app.utils.DialogUtils;
import com.winfo.szrsp.app.utils.DimensUtils;
import com.winfo.szrsp.app.utils.LoginUtils;
import com.winfo.szrsp.app.utils.LonLatUtils;
import com.winfo.szrsp.app.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;


public class PlottingAddView implements IPlottingView, View.OnClickListener {
    private Context mContext;
    //标绘dialog
    private Dialog plottingAddDialog;
    private TextView plotting_add_dissmiss;
    private List<Map<String,Object>> data;//一个集合
    //主界面标绘按钮
    private ImageButton imgbtnPlotting;

    private Button plotting_btn_dncview,plotting_btn_edit;

    private EditText plotting_jd_1,plotting_jd_2,plotting_jd_3;
    private EditText plotting_wd_1,plotting_wd_2,plotting_wd_3;

    private LinearLayout ll_jwd;

    private EditText plotting_name,plotting_mark;
    private Button btnCancel,btnSubmit;
    private WinfoDNCView winfoDNCView;
    private PlottingPresenter plottingPresenter;
    private Dialog dialog ;
    private Spinner sp_tbys;
    @SuppressLint("CutPasteId")
    public PlottingAddView(Context context, WinfoDNCView winfoDNCView, ImageButton imgbtnPlotting) {
        this.mContext = context;
        this.winfoDNCView = winfoDNCView;
        this.imgbtnPlotting = imgbtnPlotting;
        // 初始化列表的视图
        @SuppressLint("InflateParams")
        View view = LayoutInflater.from(mContext).inflate(R.layout.add_plotting_view, null);
        initView(view);
        initData();
        initEvent();

    }



    @SuppressLint("CutPasteId")
    private void initView(View view) {
//        int width = DeviceUtils.getScreenSize((Activity) mContext)[0]; // 屏幕宽度（像素）
        plottingAddDialog = new AlertDialog.Builder(mContext).create();
        plottingAddDialog.setCancelable(true);//
        plottingAddDialog.setCanceledOnTouchOutside(true);//
        plottingAddDialog.show();
        plottingAddDialog.setContentView(view);
        dialog = DialogUtils.createLoadingDialog(mContext,"加载中...");
        Window window = plottingAddDialog.getWindow();
        WindowManager.LayoutParams windowparams = window.getAttributes();
        windowparams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        windowparams.width = ViewGroup.LayoutParams.WRAP_CONTENT;
//        windowparams.width =  width-DimensUtils.dp2px(mContext,40);
        window.setGravity( Gravity.CENTER);
        window.setDimAmount(0);
        window.setBackgroundDrawableResource(android.R.color.transparent);
        window.setAttributes(windowparams);
        window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        sp_tbys=view.findViewById(R.id.spinner_tbys);
        sp_tbys.setDropDownVerticalOffset(DimensUtils.dp2px(mContext,35));
        plotting_add_dissmiss=view.findViewById(R.id.plotting_add_dissmiss);

        plotting_btn_dncview=view.findViewById(R.id.plotting_btn_dncview);
        plotting_btn_edit=view.findViewById(R.id.plotting_btn_edit);
        plotting_jd_1=view.findViewById(R.id.plotting_jd_1);
        plotting_jd_2=view.findViewById(R.id.plotting_jd_2);
        plotting_jd_3=view.findViewById(R.id.plotting_jd_3);

        plotting_wd_1=view.findViewById(R.id.plotting_wd_1);
        plotting_wd_2=view.findViewById(R.id.plotting_wd_2);
        plotting_wd_3=view.findViewById(R.id.plotting_wd_3);

        plotting_name=view.findViewById(R.id.plotting_name);
        plotting_mark=view.findViewById(R.id.plotting_mark);
        btnCancel=view.findViewById(R.id.btnCancel);
        btnSubmit=view.findViewById(R.id.btnSubmit);
        ll_jwd=view.findViewById(R.id.ll_jwd);

    }

    private void initData() {
        plottingPresenter=new PlottingPresenter();
        plottingPresenter.attachMvpView(this);
        data = new ArrayList<>();
        data=getDat();
        SimpleAdapter s_adapter = new SimpleAdapter(mContext, data, R.layout.spinner_item_tbys, new String[]{"image", "text"}, new int[]{R.id.img_tbys, R.id.tv_tbys});
        //控件与适配器绑定
        sp_tbys.setAdapter(s_adapter);


        List<InputFilter> inputFilters89 = new ArrayList<>();
        inputFilters89.add(new MaxmumFilter(0, 89, 0));

        List<InputFilter> inputFilters179 = new ArrayList<>();
        inputFilters179.add(new MaxmumFilter(0, 179, 0));
        List<InputFilter> inputFilters59 = new ArrayList<>();
        inputFilters59.add(new MaxmumFilter(0, 59, 0));

        plotting_jd_1.setFilters(inputFilters179.toArray(new InputFilter[inputFilters179.size()]));

        plotting_jd_2.setFilters(inputFilters59.toArray(new InputFilter[inputFilters59.size()]));

        plotting_jd_3.setFilters(inputFilters59.toArray(new InputFilter[inputFilters59.size()]));

        plotting_wd_1.setFilters(inputFilters89.toArray(new InputFilter[inputFilters89.size()]));

        plotting_wd_2.setFilters(inputFilters59.toArray(new InputFilter[inputFilters59.size()]));

        plotting_wd_3.setFilters(inputFilters59.toArray(new InputFilter[inputFilters59.size()]));

    }



    private void initEvent() {

        plotting_add_dissmiss.setOnClickListener(this);
        plotting_btn_edit.setOnClickListener(this);
        plotting_btn_dncview.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);

        sp_tbys.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        winfoDNCView.plotting_point_type="1";
                        break;
                    case 1:
                        winfoDNCView.plotting_point_type="2";
                        break;
                    case 2:
                        winfoDNCView.plotting_point_type="3";
                        break;
                    case 3:
                        winfoDNCView.plotting_point_type="4";
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        plotting_jd_1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {
                changeLon();
            }
        });
        plotting_jd_2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                changeLon();
            }
        });
        plotting_jd_3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                changeLon();
            }
        });
        plotting_wd_1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                changeLat();
            }
        });
        plotting_wd_2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                changeLat();
            }
        });
        plotting_wd_3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                changeLat();
            }
        });
        plottingAddDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                //TODO
                if(!winfoDNCView.is_select_plotting_point){
                    winfoDNCView.is_select_plotting_point=false;
                    winfoDNCView.winfoDNCParams.plottingPoint=null;
                    ll_jwd.setVisibility(View.GONE);
                    winfoDNCView.refreshMap();
                    MainActivity activity = (MainActivity) mContext;
                    activity.dissmissConfirmPlotting();
                }


            }
        });
    }

    private void changeLon() {
        String jd1;
        String jd2;
        String jd3;
        if(plotting_jd_1.getText().toString().equals("")){
            jd1="0";
        }else {
            jd1=plotting_jd_1.getText().toString();
        }

        if(plotting_jd_2.getText().toString().equals("")){
            jd2="0";
        }else {
            jd2=plotting_jd_2.getText().toString();
        }

        if(plotting_jd_3.getText().toString().equals("")){
            jd3="0";
        }else {
            jd3=plotting_jd_3.getText().toString();
        }
        if(winfoDNCView.winfoDNCParams.plottingPoint==null){
            winfoDNCView.winfoDNCParams.plottingPoint=new Point();
        }
        winfoDNCView.winfoDNCParams.plottingPoint.lon= LonLatUtils.DMStoDD(jd1+"/"+jd2+"/"+jd3);
        winfoDNCView.refreshMap();
    }
    private void changeLat() {
        String wd1;
        String wd2;
        String wd3;
        if(plotting_wd_1.getText().toString().equals("")){
            wd1="0";
        }else {
            wd1=plotting_wd_1.getText().toString();
        }

        if(plotting_wd_2.getText().toString().equals("")){
            wd2="0";
        }else {
            wd2=plotting_wd_2.getText().toString();
        }

        if(plotting_wd_3.getText().toString().equals("")){
            wd3="0";
        }else {
            wd3=plotting_wd_3.getText().toString();
        }
        if(winfoDNCView.winfoDNCParams.plottingPoint==null){
            winfoDNCView.winfoDNCParams.plottingPoint=new Point();
        }
        winfoDNCView.winfoDNCParams.plottingPoint.lat= LonLatUtils.DMStoDD(wd1+"/"+wd2+"/"+wd3);
        winfoDNCView.refreshMap();
    }

    public void dismiss() {
        plottingAddDialog.dismiss();
    }

    /**
     * 显示标绘的列表数据
     */
    public void showAddPlotting() {
        plottingAddDialog.show();
        winfoDNCView.is_select_plotting_point=false;
//        if(winfoDNCView.winfoDNCParams.plottingPoint==null){
//            plotting_jd_1.setText("");
//            plotting_jd_2.setText("");
//            plotting_jd_3.setText("");
//            plotting_wd_1.setText("");
//            plotting_wd_2.setText("");
//            plotting_wd_3.setText("");
//        }
    }

    /**
     *
     * @param plottingPoint 地图上选择的点
     */
    public void showAddPlotting(Point plottingPoint) {
//        winfoDNCView.is_select_plotting_point=false;
//        winfoDNCView.winfoDNCParams.plottingPoint=null;
//        if(winfoDNCView.winfoDNCParams.plottingPoint==null){
//            plotting_jd_1.setText("");
//            plotting_jd_2.setText("");
//            plotting_jd_3.setText("");
//            plotting_wd_1.setText("");
//            plotting_wd_2.setText("");
//            plotting_wd_3.setText("");
//        }
        winfoDNCView.is_select_plotting_point=false;
        plottingAddDialog.show();
        String lat=LonLatUtils.DDtoDMS( plottingPoint.lat);
        String lon=LonLatUtils.DDtoDMS( plottingPoint.lon);
        ll_jwd.setVisibility(View.VISIBLE);
        setEditCanEdit(false);

        String[] splitLat = lat.split("/");
        String[] splitLon = lon.split("/");
        plotting_jd_1.setText(splitLon[0]);
        plotting_jd_2.setText(splitLon[1]);
        plotting_jd_3.setText(splitLon[2]);

        plotting_wd_1.setText(splitLat[0]);
        plotting_wd_2.setText(splitLat[1]);
        plotting_wd_3.setText(splitLat[2]);

    }
    private void setEditCanEdit(boolean editCanEdit) {
        plotting_jd_1.setEnabled(editCanEdit);
        plotting_jd_2.setEnabled(editCanEdit);
        plotting_jd_3.setEnabled(editCanEdit);
        plotting_wd_1.setEnabled(editCanEdit);
        plotting_wd_2.setEnabled(editCanEdit);
        plotting_wd_3.setEnabled(editCanEdit);
    }
    @Override
    public Dialog getDailog() {
        return dialog;
    }

    @Override
    public void showOnFaile(String msg) {
    }

    @Override
    public void notFound() {

    }

    @Override
    public void setPlottingListData(PlottingListData data) {
    }
    @Override
    public void loginExpired(String msg) {
        LoginUtils.loginOutShowDialog(mContext,msg,msg);
    }

    @Override
    public void deleteSuccess(String msg) {
    }

    @Override
    public void deleteFaile(String msg) {
    }

    @Override
    public void addSuccess(String msg) {
        ToastUtils.showToast(mContext,msg);
        plottingAddDialog.dismiss();
    }

    @Override
    public void addFaile(String msg) {
        ToastUtils.showToast(mContext,msg);
    }

    @Override
    public void editSuccess(String msg) {

    }

    @Override
    public void editFaile(String msg) {

    }

    @Override
    public void onClick(View v) {
        //todo
        switch (v.getId()){
            case R.id.plotting_add_dissmiss:
                plottingAddDialog.dismiss();

                break;
            case R.id.plotting_btn_edit:
                ll_jwd.setVisibility(View.VISIBLE);
                setEditCanEdit(true);
                break;
            case R.id.plotting_btn_dncview:
                winfoDNCView.is_select_plotting_point=true;
                if(winfoDNCView.winfoDNCParams.plottingPoint!=null){
                    winfoDNCView.setCenter(winfoDNCView.winfoDNCParams.plottingPoint);
                }
                winfoDNCView.refreshMap();
                plottingAddDialog.dismiss();
                break;
            case R.id.btnCancel:
                plottingAddDialog.dismiss();

                break;
            case R.id.btnSubmit:
                if(winfoDNCView.winfoDNCParams.plottingPoint==null){
                    ToastUtils.showToast(mContext,"请选择标绘点");
                    return;
                 }
                if(plotting_name.getText().toString().equals("")){
                    ToastUtils.showToast(mContext,"请填写名称");
                    return;
                }
                if(plotting_mark.getText().toString().equals("")){
                    ToastUtils.showToast(mContext,"请填写备注");
                    return;
                }

               // plottingAddDialog.dismiss();
                Plotting plotting=new Plotting();
                plotting.setBhname(plotting_name.getText().toString());
                plotting.setDrawingType("1");
                plotting.setLatLon(winfoDNCView.winfoDNCParams.plottingPoint.lat+","+winfoDNCView.winfoDNCParams.plottingPoint.lon);
                plotting.setRemarks(plotting_mark.getText().toString());
                plotting.setUserUuid(ACache.get(mContext).getAsString("uuid"));
                plottingPresenter.addPlotting(plotting,true);
                break;


        }
    }

    public  boolean   isDialogShowing(){
        return plottingAddDialog.isShowing();
    }

    //数据源
    private List<Map<String,Object>> getDat() {
        Map<String, Object> map = new HashMap<>();
        map.put("image", R.mipmap.spot_other);
        map.put("text", "其他点");
        data.add(map);
        Map<String, Object> map1 = new HashMap<>();
        map1.put("image", R.mipmap.spot_wreck);
        map1.put("text", "沉船点");
        data.add(map1);
        Map<String, Object> map2 = new HashMap<>();
        map2.put("image", R.mipmap.spot_oil);
        map2.put("text", "溢油点");
        data.add(map2);
        Map<String, Object> map3 = new HashMap<>();
        map3.put("image", R.mipmap.spot_accident);
        map3.put("text", "事故点");
        data.add(map3);
        return data;
    }

    /**
     * 限制输入最大值
     */
    private  class MaxmumFilter implements InputFilter {

        private final Pattern pattern;
        private final double maxNum;

        MaxmumFilter(int min, double maxNum, int numOfDecimals) {
            pattern = Pattern.compile("^" + (min < 0 ? "-?" : "")
                    + "[0-9]*\\.?[0-9]" + (numOfDecimals > 0 ? ("{0," + numOfDecimals + "}$") : "*"));
            this.maxNum = maxNum;
        }

        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            if (source.equals(".")) {
                if (dstart == 0 || !(dest.charAt(dstart - 1) >= '0' && dest.charAt(dstart - 1) <= '9') || dest.charAt(0) == '0') {
                    return "";
                }
            }
            if (source.equals("0") && (dest.toString()).contains(".") && dstart == 0) {
                return "";
            }

            StringBuilder builder = new StringBuilder(dest);
            builder.delete(dstart, dend);
            builder.insert(dstart, source);
            if (!pattern.matcher(builder.toString()).matches()) {
                return "";
            }

            if (!TextUtils.isEmpty(builder)) {
                double num = Double.parseDouble(builder.toString());
                if (num > maxNum) {
                    return "";
                }
            }
            return source;
        }
    }

}
