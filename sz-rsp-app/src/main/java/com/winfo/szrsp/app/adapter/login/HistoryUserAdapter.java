package com.winfo.szrsp.app.adapter.login;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.entity.user.HistoryUser;

import java.util.List;

/**
 * @ProjectName: gdmsaecApp
 * @PackageName: com.winfo.gdmsaec.app.adapter
 * @FileName: com.winfo.gdmsaec.app.adapter.HistoryUserAdapter.java
 * @Author: wenjie
 * @Date: 2017-10-20 15:45
 * @Description:
 * @Version:
 */
public class HistoryUserAdapter extends RecyclerView.Adapter<HistoryUserAdapter.CustomeHolder> {

    private Context context;
    private List<HistoryUser> historyUsers;

    public HistoryUserAdapter(Context context, List<HistoryUser> historyUsers) {
        this.context = context;
        this.historyUsers = historyUsers;
    }

    /**
     * 公布点击事件出去
     */
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onClick(HistoryUserAdapter historyUserAdapter, View itemView, int position);
    }

    @Override
    public HistoryUserAdapter.CustomeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final CustomeHolder customeHolder = new CustomeHolder(LayoutInflater.from(context).inflate(R.layout.history_user, parent, false));
        customeHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener != null) {
                    onItemClickListener.onClick(HistoryUserAdapter.this, customeHolder.itemView, customeHolder.getLayoutPosition());
                }
            }
        });
        return customeHolder;
    }

    @Override
    public void onBindViewHolder(HistoryUserAdapter.CustomeHolder holder, int position) {
        holder.tvShowUser.setText(historyUsers.get(position).getUsername());
    }

    @Override
    public int getItemCount() {
        return historyUsers.size();
    }

    class CustomeHolder extends RecyclerView.ViewHolder {

        TextView tvShowUser;

        CustomeHolder(View itemView) {
            super(itemView);
            tvShowUser = (TextView) itemView.findViewById(R.id.tv_history_suer);
        }
    }
}
