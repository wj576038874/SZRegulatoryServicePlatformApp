package com.winfo.szrsp.app.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * listView缓存
 *
 * @author 00
 */
public class ViewHolder {
    /**
     * 存储view的集合 map类型的集合
     */
    private SparseArray<View> mViews;
    /**
     * item的坐标
     */
    private int mPosition;
    /**
     * 缓存的view
     */
    private View mConvertView;

    /**
     * @param context  上下文
     * @param resource 资源文件的id  需要把那个文件变成view对象
     * @param position item的下标
     * @param parent   父布局
     */
    public ViewHolder(Context context, int resource, int position, ViewGroup parent) {
        this.mPosition = position;
        /**
         * 初始化存储view的集合
         */
        mViews = new SparseArray<View>();
        /**
         * 初始化布局文件填充器 将其变成view
         */
        this.mConvertView = LayoutInflater.from(context).inflate(resource, parent, false);//创建出convertView
        /**
         * 设置缓存
         */
        mConvertView.setTag(this);
    }

    /**
     * 向外提供一个获取viewholder的方法
     *
     * @param context     上下文
     * @param resource    资源文件的id
     * @param position    item的坐标
     * @param parent      父布局
     * @param convertView 缓存view
     * @return viewHolder
     */
    public static ViewHolder getViewHolder(Context context, int resource, int position, ViewGroup parent, View convertView) {
        if (convertView == null) {
            return new ViewHolder(context, resource, position, parent);
        } else {
            ViewHolder holder = (ViewHolder) convertView.getTag();
            holder.mPosition = position;
            return holder;
        }
    }

    /**
     * 获取viewholder里面的控件的方法
     *
     * @param viewId view的id
     * @return view
     */
    @SuppressWarnings("unchecked")
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    /**
     * 获取设置好的convertView
     *
     * @return convertView
     */
    public View getmConvertView() {
        return mConvertView;
    }


    /**
     * 给文本view赋值
     *
     * @param viewId textView的id
     * @param text   文本内容
     * @return ViewHolder
     */
    public ViewHolder setText(int viewId, String text) {
        TextView textView = getView(viewId);
        textView.setText(text);
        return this;
    }


}
