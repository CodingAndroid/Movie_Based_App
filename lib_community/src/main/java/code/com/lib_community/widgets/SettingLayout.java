package code.com.lib_community.widgets;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;

import code.com.lib_community.R;
import code.com.lib_community.utils.UscreenUtil;
import code.com.lib_community.utils.ViewUtil;

/**
 * Created by lihui1 on 2018/1/10.
 */

public class SettingLayout extends LinearLayout{

    private Context mContext;
    private SparseArray<View> mItemsViews;

    public SettingLayout(Context context) {
        this(context, null);
    }

    public SettingLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SettingLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        mItemsViews = new SparseArray<View>();
    }

    public SettingLayout addItem(Item item){
        if (item == null){
            throw new IllegalArgumentException("item 为空");
        }
        if (mItemsViews.get(item.titleStrId) != null){
            throw new IllegalArgumentException("");
        }
        @SuppressLint("InflateParams")
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_setting_layout, null);
        addView(v, new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, UscreenUtil.dp2Px(mContext, 48)));
        mItemsViews.put(item.titleStrId, v);
        refreshItem(item);
        return this;
    }

    public SettingLayout refreshItem(final Item item){
        if (item == null) {
            throw new NullPointerException("Item为空");
        }

        View v = getItemView(item);
        if (v == null) {
            throw new IllegalArgumentException("尚未添加此Item, 请先通过addItem方法添加");
        }

        // 根据数据初始化视图
        if (item.iconResId != 0) {
            ImageView ivIcon = (ImageView) v.findViewById(R.id.iv_set_icon);
            ivIcon.setVisibility(View.VISIBLE);
            ivIcon.setImageResource(item.iconResId);
        }

        TextView tvTitle = (TextView) v.findViewById(R.id.tv_set_title);
        tvTitle.setText(item.titleStrId);

        if (!TextUtils.isEmpty(item.numStr)) {
            TextView tvUnit = (TextView) v.findViewById(R.id.tv_set_num);
            tvUnit.setVisibility(View.VISIBLE);
            tvUnit.setText(item.numStr);
        }

        if (item.hasToRight) {
            ImageView ivToRight = (ImageView) v.findViewById(R.id.iv_set_to_right);
            ivToRight.setVisibility(View.VISIBLE);
        }

        View vDivider = v.findViewById(R.id.v_set_divider);
        if (item.sep == SEP.FILL) {
            vDivider.setVisibility(View.VISIBLE);
        } else if (item.sep == SEP.AFTERICON) {
            vDivider.setVisibility(View.VISIBLE);
            ViewGroup.MarginLayoutParams mp = ((ViewGroup.MarginLayoutParams) vDivider.getLayoutParams());
            mp.leftMargin = UscreenUtil.dp2Px(mContext, 10);
        }

        if (item.listener != null) {
            ViewUtil.clickEffectByAlphaWithBg(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    item.listener.click(SettingLayout.this, item);
                }
            }, v);
        }
        return this;
    }

    public SettingLayout addCustomView(View view){
        addView(view);
        return this;
    }

    public SettingLayout addSpace(int spaceHeight){
        Space space = new Space(mContext);
        space.setLayoutParams(new ViewGroup.LayoutParams(LayoutParams.MATCH_PARENT, spaceHeight));
        addView(space);
        return this;
    }

    public SettingLayout addHeadTips(int strId) {
        return addHeadTips(mContext.getResources().getString(strId));
    }

    public SettingLayout addHeadTips(String tips) {
        TextView tvTips = new TextView(mContext);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT);
        params.leftMargin = UscreenUtil.dp2Px(mContext, 0);
        params.bottomMargin = UscreenUtil.dp2Px(mContext, 0);
        tvTips.setLayoutParams(params);
        tvTips.setText(tips);
        tvTips.setTextColor(Color.DKGRAY);
        tvTips.setTextSize(12);
        tvTips.setBackgroundColor(getResources().getColor(R.color.gray));
        addView(tvTips);
        return this;
    }

    public View getItemView(Item item) {
        return getItemView(item.titleStrId);
    }

    /**
     * 通过Title的字符串资源ID来获取对应的View
     *
     * @param titleStrId 字符串资源ID
     * @return 对应的Item视图
     */
    public View getItemView(int titleStrId) {
        return mItemsViews.get(titleStrId);
    }

    public interface IClickListener {
        void click(SettingLayout layout, Item item);
    }

    /**
     * NO 表示不显示底部分割线
     * FILL 表示占满整个底部的分割线
     * AFTERICON 表示紧随图像的分割线
     */
    public static enum SEP{
        NO, FILL, AFTERICON;
    }

    public static class Item{
        public int titleStrId;
        public int iconResId;
        public String numStr;
        public boolean hasToRight;
        public SEP sep;
        public IClickListener listener;

        public Item(int titleStrId) {
            this.titleStrId = titleStrId;
        }

        /**
         *
         * @param titleStrId 字符串ID, 用来标识Item
         * @param iconResId  最左边的Icon图标（当等于0时，不显示图标）
         * @param numStr     数量字符串
         * @param hasToRight 是否显示向右的图标
         * @param sep        分割线样式
         * @param listener   点击Item发生的事件
         */
        public Item(int titleStrId, int iconResId, String numStr, boolean hasToRight, SEP sep,
                    IClickListener listener) {
            super();
            this.iconResId = iconResId;
            this.titleStrId = titleStrId;
            this.numStr = numStr;
            this.hasToRight = hasToRight;
            this.sep = sep;
            this.listener = listener;
        }
    }
}
