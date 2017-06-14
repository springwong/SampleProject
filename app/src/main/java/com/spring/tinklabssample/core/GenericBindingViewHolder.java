package com.spring.tinklabssample.core;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by spring on 14/6/2017.
 */

/***
 * Store ViewDataBinding in viewHolder
 */
public class GenericBindingViewHolder extends RecyclerView.ViewHolder {
    public ViewDataBinding viewHolderBinding;

    public GenericBindingViewHolder(View itemView) {
        super(itemView);
    }

    public GenericBindingViewHolder(ViewDataBinding viewHolderBinding) {
        super(viewHolderBinding.getRoot());
        this.viewHolderBinding = viewHolderBinding;
    }
}
