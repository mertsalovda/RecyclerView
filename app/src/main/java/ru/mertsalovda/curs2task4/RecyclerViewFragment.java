package ru.mertsalovda.curs2task4;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecyclerViewFragment extends Fragment {

    private RecyclerView recyclerView;

    private final ComplexRecyclerViewAdapter mAdapter = new ComplexRecyclerViewAdapter();


    public RecyclerViewFragment() {
    }

    public static RecyclerViewFragment newInstance() {
        return new RecyclerViewFragment();
    }

    private ComplexRecyclerViewAdapter.OnItemClickListener mListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = new ComplexRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                mAdapter.delete(position);
            }
        };
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        //Сохранить Fragment при смене конфигурации
        setRetainInstance(true);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycler);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fr_recycler_view, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mAdapter);
        //Listener -> Adapter
        mAdapter.setListener(mListener);
        if (savedInstanceState == null) {
            mAdapter.addData(initData());
        }
    }
    //Начальные данные
    private List<Object> initData() {
        List<Object> list = new ArrayList<>();

        list.add(new TextCard(UUID.randomUUID().toString().substring(0, 10),
                UUID.randomUUID().toString().substring(0, 10)));
        list.add(new ImageCard());
        list.add(new ImageCard());
        list.add(new TextCard(UUID.randomUUID().toString().substring(0, 10),
                UUID.randomUUID().toString().substring(0, 10)));
        list.add(new ImageCard());
        list.add(new TextCard(UUID.randomUUID().toString().substring(0, 10),
                UUID.randomUUID().toString().substring(0, 10)));
        list.add(new TextCard(UUID.randomUUID().toString().substring(0, 10),
                UUID.randomUUID().toString().substring(0, 10)));
        list.add(new ImageCard());

        return list;
    }

    @Override
    public void onDetach() {
        mListener = null;
        super.onDetach();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_fr_recycler_view, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mi_add_image:
                mAdapter.addData(new ImageCard());
                //Фокус на добавленный элемент
                recyclerView.smoothScrollToPosition(mAdapter.getItemCount());
                return true;
            case R.id.mi_add_text:
                mAdapter.addData(new TextCard(UUID.randomUUID().toString().substring(0, 10),
                        UUID.randomUUID().toString().substring(0, 10)));
                //Фокус на добавленный элемент
                recyclerView.smoothScrollToPosition(mAdapter.getItemCount());
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
