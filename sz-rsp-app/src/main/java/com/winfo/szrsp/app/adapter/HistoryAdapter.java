package com.winfo.szrsp.app.adapter;



import android.content.Context;
import android.widget.TextView;

import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.entity.HistoryAis;

import java.util.List;


public class HistoryAdapter extends CommonAdapter<HistoryAis> {

	public HistoryAdapter(Context context, List<HistoryAis> datas, int resource) {
		super(context, datas, resource);
	}

	@Override
	public void convert(ViewHolder holder, int position)
	{
		TextView tvCm = holder.getView(R.id.tv_history_ywcm);
		tvCm.setText(datas.get(position).getZwcm());
	}
	
	public void  changedata(List<HistoryAis> historyAis){
		this.datas = historyAis;
	}
}
