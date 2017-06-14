package com.spring.tinklabssample.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.spring.tinklabssample.R;
import com.spring.tinklabssample.core.GenericBindingViewHolder;
import com.spring.tinklabssample.databinding.RecyclerItemTypeBannerBinding;
import com.spring.tinklabssample.databinding.RecyclerItemTypeProfileBinding;
import com.spring.tinklabssample.viewmodel.DemoViewModel;

/**
 * Created by spring on 14/6/2017.
 */
class DemoAdapter extends RecyclerView.Adapter<GenericBindingViewHolder> {

    private DemoViewModel vm;
    private Context context;
    private LayoutInflater inflater;
    private int TYPE_PROFILE = 0;
    private int TYPE_BANNER = 1;

    public DemoAdapter(DemoViewModel vm, Context context) {
        this.vm = vm;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public GenericBindingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding itemBinding = null;
        if (viewType == TYPE_PROFILE) {
            itemBinding = DataBindingUtil.inflate(inflater, R.layout.recycler_item_type_profile, parent, false);
        }
        if (viewType == TYPE_BANNER) {
            itemBinding = DataBindingUtil.inflate(inflater, R.layout.recycler_item_type_banner, parent, false);
        }
        return new GenericBindingViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(GenericBindingViewHolder holder, int position) {
        if (holder.viewHolderBinding instanceof RecyclerItemTypeProfileBinding) {
            RecyclerItemTypeProfileBinding itemBinding = (RecyclerItemTypeProfileBinding) holder.viewHolderBinding;
            itemBinding.tvTitle.setText(vm.dataSet.get(position).getUserName());
            itemBinding.tvDesc.setText(vm.dataSet.get(position).getRecommend());
            Glide.with(context).load(vm.dataSet.get(position).getAvatarImageUrl()).apply(new RequestOptions().placeholder(R.drawable.placeholder2)).into(itemBinding.ivProfile);
        }
        if (holder.viewHolderBinding instanceof RecyclerItemTypeBannerBinding) {
            RecyclerItemTypeBannerBinding itemBinding = (RecyclerItemTypeBannerBinding) holder.viewHolderBinding;
            Glide.with(context).load(vm.dataSet.get(position).getAvatarImageUrl()).apply(new RequestOptions().placeholder(R.drawable.placeholder2)).into(itemBinding.ivProfile);
        }
    }

    @Override
    public int getItemCount() {
        return vm.dataSet.size() - 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position % 2 == 0) {
            return TYPE_PROFILE;
        } else {
            return TYPE_BANNER;
        }
    }
}
