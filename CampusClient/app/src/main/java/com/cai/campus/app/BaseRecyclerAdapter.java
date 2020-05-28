package com.cai.campus.app;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述：recyclerview的adapter的封装
 * 作者：zzh
 * time:2019/05/06
 */
public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<BaseRecyclerAdapter.BaseViewHolder> {

    protected List<T> dataList;//数据集合
    private Context context;//上下文
    private final int itemId;
    private OnItemClickListner onItemClickListner;//单击事件

    protected BaseRecyclerAdapter(List<T> dataList, int itemId) {
        this.dataList = dataList;
        this.itemId = itemId;
        setHasStableIds(true);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(itemId, parent, false);
        final BaseViewHolder baseViewHolder = new BaseViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListner != null)
                    onItemClickListner.onItemClickListner(v, baseViewHolder.getLayoutPosition());
            }
        });
        return baseViewHolder;
    }

    @Override
    public void onBindViewHolder(BaseRecyclerAdapter.BaseViewHolder holder, final int position) {
        T t = dataList.get(position);
        bindData(holder, position, t);
    }

    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size();
    }

    /**
     * 刷新数据
     *
     * @param datas
     */
    public void refresh(List<T> datas) {
        this.dataList = datas;
        notifyDataSetChanged();
    }

    /**
     * 添加数据
     *
     * @param datas
     */
    public void addData(List<T> datas) {
        this.dataList.addAll(datas);
        notifyDataSetChanged();
    }

    /**
     * 添加数据(局部刷新，局部刷新时必须充血getItemId方法，同时setHasStableIds(true))
     *
     * @param datas
     */
    public void addDataWithoutAnim(List<T> datas) {
        if (datas == null)
            return;
        int size = this.dataList.size();
        this.dataList.addAll(datas);
        notifyItemRangeChanged(size, datas.size());
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * 绑定数据
     *
     * @param holder   具体的viewHolder
     * @param position 对应的索引
     * @param t
     */
    protected abstract void bindData(BaseViewHolder holder, int position, T t);

    /**
     * 设置文本属性
     *
     * @param view
     * @param text
     */
    public void setItemText(View view, String text) {
        if (view instanceof TextView) {
            ((TextView) view).setText(text);
        }
    }

    /**
     * 设置图片属性
     *
     * @param view
     * @param url
     */
    public void setItemImage(View view, String url) {
        if (view instanceof ImageView && context != null) {
            ImageView imageView = (ImageView) view;
            Glide.with(context).load(url).into(imageView);
        }
    }

    /**
     * 设置圆形图片属性
     *
     * @param view
     * @param url
     */
    public void setItemImageCircle(View view, String url) {
        if (view instanceof ImageView && context != null) {
            ImageView imageView = (ImageView) view;
            Glide.with(context).load(url).into(imageView);
        }
    }

    /**
     * 设置圆形图片属性
     *
     * @param view
     * @param id
     */
    public void setItemImageDrawable(View view, int id) {
        if (view instanceof ImageView) {
            ImageView imageView = (ImageView) view;
            imageView.setImageDrawable(context.getDrawable(id));
        }
    }

    /**
     * 设置控件可见性
     *
     * @param view
     * @param visible
     */
    public void setItemViewVisible(View view, int visible) {
        if (view != null) {
            switch (visible) {
                case View.VISIBLE:
                    view.setVisibility(View.VISIBLE);
                    break;
                case View.INVISIBLE:
                    view.setVisibility(View.INVISIBLE);
                    break;
                case View.GONE:
                    view.setVisibility(View.GONE);
                    break;
                default:
                    break;
            }
        }
    }

    public void setOnItemClickListner(OnItemClickListner onItemClickListner) {
        this.onItemClickListner = onItemClickListner;
    }

    public interface OnItemClickListner {
        void onItemClickListner(View v, int position);
    }

    /**
     * 封装ViewHolder ,子类可以直接使用
     */
    public class BaseViewHolder extends RecyclerView.ViewHolder {

        private final Map<Integer, View> mViewMap;

        BaseViewHolder(View itemView) {
            super(itemView);
            mViewMap = new HashMap<>();
        }

        /**
         * 获取设置的view
         *
         * @param id
         * @return
         */
        public View getView(int id) {
            View view = mViewMap.get(id);
            if (view == null) {
                view = itemView.findViewById(id);
                mViewMap.put(id, view);
            }
            return view;
        }
    }
}
