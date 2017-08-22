package com.lee.timeselector.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lee.timeselector.R;
import com.lee.timeselector.WheelTime;
import com.lee.timeselector.inter.CustomListener;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2017/8/20 0020.
 */

public class TimePickerView extends BasePickerView implements View.OnClickListener {
    private int layoutRes;

    private CustomListener customListener;

    //确定、取消按钮
    private Button btnSubmit, btnCancel;

    //标题
    private TextView tvTitle;
    //回调接口
    private OnTimeSelectorListener timeSelectListener;
    //内容显示位置 默认居中
    private int gravity = Gravity.CENTER;
    // 显示类型
    private boolean[] type;

    //自定义控件
    public WheelTime wheelTime;

    //分隔线类型
    private WheelView.DividerType dividerType;


    private String Str_Submit;//确定按钮字符串
    private String Str_Cancel;//取消按钮字符串
    private String Str_Title;//标题字符串


    private int Color_Submit;//确定按钮颜色
    private int Color_Cancel;//取消按钮颜色
    private int Color_Title;//标题颜色

    private int Color_Background_Wheel;//滚轮背景颜色
    private int Color_Background_Title;//标题背景颜色

    private int Size_Submit_Cancel;//确定取消按钮大小
    private int Size_Title;//标题字体大小
    private int Size_Content;//内容字体大小


    private Calendar date;//当前选中时间
    private Calendar startDate;//开始时间
    private Calendar endDate;//终止时间
    private int startYear;//开始年份
    private int endYear;//结尾年份

    private boolean cyclic;//是否循环
    private boolean cancelable;//是否能取消
    private boolean isCenterLabel;//是否只显示中间的label

    private int textColorOut; //分割线以外的文字颜色
    private int textColorCenter; //分割线之间的文字颜色
    private int dividerColor; //分割线的颜色
    private int backgroundId; //显示时的外部背景色颜色,默认是灰色


    // 条目间距倍数 默认1.6
    private float lineSpacingMultiplier = 1.6F;
    private boolean isDialog;//是否是对话框模式
    private String label_year, label_month, label_day, label_hours, label_mins, label_second;

    private static final String TAG_SUBMIT = "submit";
    private static final String TAG_CANCEL = "cancel";


    public TimePickerView(Builder builder) {
        super(builder.context);
        this.timeSelectListener = builder.timeSelectorListener;
        this.gravity = builder.gravity;
        this.type = builder.type;

        this.Str_Submit = builder.Str_Submit;
        this.Str_Cancel = builder.Str_Cancel;
        this.Str_Title = builder.Str_Title;
        this.Color_Submit = builder.Color_Submit;
        this.Color_Cancel = builder.Color_Cancel;
        this.Color_Title = builder.Color_Title;
        this.Color_Background_Wheel = builder.Color_Background_Wheel;
        this.Color_Background_Title = builder.Color_Background_Title;
        this.Size_Submit_Cancel = builder.Size_Submit_Cancel;
        this.Size_Title = builder.Size_Title;
        this.Size_Content = builder.Size_Content;
        this.startYear = builder.startYear;
        this.endYear = builder.endYear;
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
        this.date = builder.date;
        this.cyclic = builder.cyclic;
        this.isCenterLabel = builder.isCenterLabel;
        this.cancelable = builder.cancelable;
        this.label_year = builder.label_year;
        this.label_month = builder.label_month;
        this.label_day = builder.label_day;
        this.label_hours = builder.label_hours;
        this.label_mins = builder.label_mins;
        this.label_second = builder.label_second;
        this.textColorCenter = builder.textColorCenter;
        this.textColorOut = builder.textColorOut;
        this.dividerColor = builder.dividerColor;
        this.customListener = builder.customListener;
        this.layoutRes = builder.layoutRes;
        this.lineSpacingMultiplier = builder.lineSpacingMultiplier;
        this.isDialog = builder.isDialog;
        this.dividerType = builder.dividerType;
        this.backgroundId = builder.backgroundId;
        this.decorView = builder.decorView;
        initView(builder.context);
    }


    private void initView(Context context) {
        setDialogOutSideCancelable(cancelable);
        initViews(backgroundId);
        initEvents();
//        if (customListener == null) {
//            LayoutInflater.from(context).inflate(R.layout.pickerview_time, contentContainer);

        //顶部标题
//            tvTitle = (TextView) findViewById(R.id.tvTitle);

        //确定和取消按钮
//            btnSubmit = (Button) findViewById(R.id.btnSubmit);
//            btnCancel = (Button) findViewById(R.id.btnCancel);

//            btnSubmit.setTag(TAG_SUBMIT);
//            btnCancel.setTag(TAG_CANCEL);

//            btnSubmit.setOnClickListener(this);
//            btnCancel.setOnClickListener(this);

        //设置文字
//            btnSubmit.setText(TextUtils.isEmpty(Str_Submit) ? context.getResources().getString(R.string.pickerview_submit) : Str_Submit);
//            btnCancel.setText(TextUtils.isEmpty(Str_Cancel) ? context.getResources().getString(R.string.pickerview_cancel) : Str_Cancel);
//            tvTitle.setText(TextUtils.isEmpty(Str_Title) ? "" : Str_Title);//默认为空

        //设置文字颜色
//            btnSubmit.setTextColor(Color_Submit == 0 ? pickerview_timebtn_nor : Color_Submit);
//            btnCancel.setTextColor(Color_Cancel == 0 ? pickerview_timebtn_nor : Color_Cancel);
//            tvTitle.setTextColor(Color_Title == 0 ? pickerview_topbar_title : Color_Title);

        //设置文字大小
//            btnSubmit.setTextSize(Size_Submit_Cancel);
//            btnCancel.setTextSize(Size_Submit_Cancel);
//            tvTitle.setTextSize(Size_Title);
//            RelativeLayout rv_top_bar = (RelativeLayout) findViewById(R.id.rv_topbar);
//            rv_top_bar.setBackgroundColor(Color_Background_Title == 0 ? pickerview_bg_topbar : Color_Background_Title);
//        } else {
//
//        }
        customListener.customLayout(LayoutInflater.from(context).inflate(layoutRes, contentContainer));
        // 时间转轮 自定义控件
        LinearLayout timePickerView = (LinearLayout) findViewById(R.id.timepicker);

        timePickerView.setBackgroundColor(Color_Background_Wheel == 0 ? bgColor_default : Color_Background_Wheel);

        wheelTime = new WheelTime(timePickerView, type, gravity, Size_Content);

        if (startYear != 0 && endYear != 0 && startYear <= endYear) {
            setRange();
        }

        if (startDate != null && endDate != null) {
            if (startDate.getTimeInMillis() <= endDate.getTimeInMillis()) {
                setRangDate();
            }
        } else if (startDate != null && endDate == null) {
            setRangDate();
        } else if (startDate == null && endDate != null) {
            setRangDate();
        }

        setTime();
        wheelTime.setLabels(label_year, label_month, label_day, label_hours, label_mins, label_second);

        setOutSideCancelable(cancelable);

        wheelTime.setCyclic(cyclic);
        wheelTime.setDividerColor(dividerColor);
        wheelTime.setDividerType(dividerType);
        wheelTime.setLineSpacingMultiplier(lineSpacingMultiplier);
        wheelTime.setTextColorOut(textColorOut);
        wheelTime.setTextColorCenter(textColorCenter);
        wheelTime.isCenterLabel(isCenterLabel);
    }


    /**
     * 设置选中时间,默认选中当前时间
     */
    @SuppressLint("WrongConstant")
    private void setTime() {
        int year, month, day, hours, minute, seconds;

        Calendar calendar = Calendar.getInstance();
        if (date == null) {
            calendar.setTimeInMillis(System.currentTimeMillis());
            year = calendar.get(Calendar.YEAR);
            month = calendar.get(Calendar.MONTH);
            day = calendar.get(Calendar.DAY_OF_MONTH);
            hours = calendar.get(Calendar.HOUR_OF_DAY);
            minute = calendar.get(Calendar.MINUTE);
            seconds = calendar.get(Calendar.SECOND);
        } else {
            year = date.get(Calendar.YEAR);
            month = date.get(Calendar.MONTH);
            day = date.get(Calendar.DAY_OF_MONTH);
            hours = date.get(Calendar.HOUR_OF_DAY);
            minute = date.get(Calendar.MINUTE);
            seconds = date.get(Calendar.SECOND);
        }


        wheelTime.setPicker(year, month, day, hours, minute, seconds);
    }

    /**
     * 设置可以选择的时间范围, 要在setTime之前调用才有效果
     */
    private void setRange() {
        wheelTime.setStartYear(startYear);
        wheelTime.setEndYear(endYear);

    }


    /**
     * 设置可以选择的时间范围, 要在setTime之前调用才有效果
     */
    private void setRangDate() {
        wheelTime.setRangDate(startDate, endDate);
        //如果设置了时间范围
        if (startDate != null && endDate != null) {
            //判断一下默认时间是否设置了，或者是否在起始终止时间范围内
            if (date == null || date.getTimeInMillis() < startDate.getTimeInMillis()
                    || date.getTimeInMillis() > endDate.getTimeInMillis()) {
                date = startDate;
            }
        } else if (startDate != null) {
            //没有设置默认选中时间,那就拿开始时间当默认时间
            date = startDate;
        } else if (endDate != null) {
            date = endDate;
        }
    }

    protected void initEvents() {
    }


    @Override
    public void onClick(View v) {
        String tag = (String) v.getTag();
        if (tag.equals(TAG_SUBMIT)) {
            returnData();
        }
        dismiss();
    }

    public void returnData() {
        if (timeSelectListener != null) {
            try {
                Date date = WheelTime.dateFormat.parse(wheelTime.getTime());
                timeSelectListener.onTimeSelect(date, clickView);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    public static class Builder {

        //TODO
        //分割线类型
        private WheelView.DividerType dividerType;

        /**
         * 時間選擇佈局
         */
//        TODO
        private int layoutRes = R.layout.pickerview_time;
        private CustomListener customListener;
        private Context context;
        private OnTimeSelectorListener timeSelectorListener;

        //顯示類型，默認全部顯示

        private boolean[] type = new boolean[]{true, true, true, true, true, false};
        //內容顯示位置，默認居中
        private int gravity = Gravity.CENTER;
        /*************************
         * 按鈕文字設置
         *********************************/
        //确认按钮
        private String Str_Submit;

        //取消按钮
        private String Str_Cancel;

        //标题文字
        private String Str_Title;

        /***********************
         * 按鈕背景顏色設置
         ***********************************/
        //确定按钮颜色
        private int Color_Submit;

        //取消按钮颜色
        private int Color_Cancel;

        //标题颜色
        private int Color_Title;

        /***********************
         * 选择时间的背景颜色
         ***********************************/
        //滚轮背景颜色
        private int Color_Background_Wheel;
        //标题背景颜色
        private int Color_Background_Title;

        /***********************
         * 设置按钮大小
         ***********************************/
        //确定取消按钮大小
        private int Size_Submit_Cancel = 17;

        //标题字体大小
        private int Size_Title = 18;
        //内容字体大小
        private int Size_Content = 18;

        //当前选中时间
        private Calendar date;


        //开始时间
        private Calendar startDate;
        //终止时间
        private Calendar endDate;
        //开始年份
        private int startYear;
        //结尾年份
        private int endYear;

        //是否循环
        private boolean cyclic = false;
        //是否能取消
        private boolean cancelable = true;
        //是否只显示中间的label（標籤）
        private boolean isCenterLabel = true;

        //显示pickerview的根View,默认是activity的根view
        public ViewGroup decorView;

        //分割线以外的文字颜色
        private int textColorOut;

        //分割线之间的文字颜色
        private int textColorCenter;
        //分割线的颜色
        private int dividerColor;
        //显示时的外部背景色颜色,默认是灰色
        private int backgroundId;


        // 条目间距倍数 默认1.6
        private float lineSpacingMultiplier = 1.6F;

        //是否是对话框模式
        private boolean isDialog;

        //时间单位
        private String label_year, label_month, label_day, label_hours, label_mins, label_second;

        //內部類構造
        public Builder(Context context, OnTimeSelectorListener listener) {
            this.context = context;
            this.timeSelectorListener = listener;
        }

        //設置類型
        public Builder setType(boolean[] type) {
            this.type = type;
            return this;
        }

        /**
         * 因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11
         *
         * @param date
         * @return
         */
        public Builder setDate(Calendar date) {
            this.date = date;
            return this;
        }

        public Builder setLayoutRes(int res, CustomListener customListener) {
            this.layoutRes = res;
            this.customListener = customListener;
            return this;
        }

        /**
         * 设置分割线的颜色
         *
         * @param dividerColor
         */
        public Builder setDividerColor(int dividerColor) {
            this.dividerColor = dividerColor;
            return this;
        }


        public Builder isCenterLabel(boolean isCenterLabel) {
            this.isCenterLabel = isCenterLabel;
            return this;
        }


        /**
         * 设置起始时间
         * 因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11
         *
         * @return
         */

        public Builder setRangDate(Calendar startDate, Calendar endDate) {
            this.startDate = startDate;
            this.endDate = endDate;
            return this;
        }

        public TimePickerView build() {
            return new TimePickerView(this);
        }


        public Builder setLabel(String label_year, String label_month, String label_day, String label_hours, String label_mins) {
            this.label_year = label_year;
            this.label_month = label_month;
            this.label_day = label_day;
            this.label_hours = label_hours;
            this.label_mins = label_mins;
            return this;
        }


    }


    public interface OnTimeSelectorListener {
        void onTimeSelect(Date date, View view);
    }
}
