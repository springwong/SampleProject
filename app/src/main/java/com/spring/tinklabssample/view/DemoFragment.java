package com.spring.tinklabssample.view;


import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jakewharton.rxbinding2.support.v7.widget.RxRecyclerView;
import com.jakewharton.rxbinding2.support.v7.widget.RxRecyclerViewAdapter;
import com.spring.tinklabssample.R;
import com.spring.tinklabssample.databinding.FragmentDemoBinding;
import com.spring.tinklabssample.viewmodel.DemoViewModel;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

/**
 * A simple {@link Fragment} subclass.
 */
public class DemoFragment extends Fragment {
    FragmentDemoBinding binding;

    @Inject
    DemoViewModel viewModel;

    public DemoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.fragment_demo, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupRecyclerView(binding.recyclerView);
        setupDataBinding();
        setupScrollEvent(binding.recyclerView, viewModel);

        viewModel.onCreate();
    }

    private void setupScrollEvent(RecyclerView recyclerView, DemoViewModel vm) {
        RxRecyclerView.scrollEvents(recyclerView).subscribe(recyclerViewScrollEvent -> {
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerViewScrollEvent.view().getLayoutManager();
            if(linearLayoutManager.findLastCompletelyVisibleItemPosition() == vm.dataSet.size() - 4) {
                vm.loadMore();
            }
        });
    }

    private void setupDataBinding() {
        viewModel.added.subscribe(advisorDatas -> {
            Log.d("TAG", advisorDatas.toString());
            Toast.makeText(getContext(), "Data loaded", Toast.LENGTH_SHORT).show();
            binding.recyclerView.getAdapter().notifyItemRangeInserted(viewModel.dataSet.size() - advisorDatas.size(), advisorDatas.size());
        }, throwable -> throwable.printStackTrace());
    }

    private void setupRecyclerView(RecyclerView recyclerView) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(new DemoAdapter(viewModel, getContext()));
    }

}
