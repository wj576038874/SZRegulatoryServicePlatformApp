package com.winfo.szrsp.app.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * 公用的适配器（lsitView  gridView）
 * @author 00
 *
 * @param <T> 泛型
 */
public abstract class CommonAdapter<T> extends BaseAdapter {

	/**
	 * 上下文 
	 */
	protected Context mContext;
	/**
	 * 数据  子类调用该类的构造方法，将数据传进来 然后赋值
	 * 		从子类中获得
	 */
	protected List<T> datas;
	/**
	 *  子类调用该类的构造方法，将资源id传进来 然后赋值
	 */
	protected int resource;
	
	/**
	 * 构造方法
	 * @param context 上下文  子类获得 
	 * @param datas	数据 子类获得
	 * @param resource	资源id 子类获得
	 */
	public CommonAdapter(Context context, List<T> datas, int resource) {
		this.mContext = context;
		this.datas = datas;
		this.resource = resource;
	}

	@Override
	public int getCount() {
		return datas.size();
	}

	@Override
	public Object getItem(int position) {
		return datas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		ViewHolder holder = ViewHolder.getViewHolder(mContext, resource, position, parent, convertView);
		/**
		 * 数据的绑定
		 */
		convert(holder,position);
		
		return holder.getmConvertView();
	}
	/**
	 * 子类需要实现的方法  也就是数据的绑定
	 * @param holder 缓存的view
	 * @param position 没绑定数据的索引
	 */
	public abstract void convert(ViewHolder holder, int position);
}
