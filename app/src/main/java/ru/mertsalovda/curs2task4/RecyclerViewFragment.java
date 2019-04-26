package ru.mertsalovda.curs2task4;


import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecyclerViewFragment extends Fragment {

    RecyclerView recyclerView;

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
        if (context instanceof ComplexRecyclerViewAdapter.OnItemClickListener){
            mListener = (ComplexRecyclerViewAdapter.OnItemClickListener)context;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
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
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);
        mAdapter.setListener(mListener);
        mAdapter.addData(testDataObjects());

    }

    private List<Object> testDataObjects() {
        List<Object> list = new ArrayList<>();

        list.add(new TextCard("Text: ", "3165431"));
        list.add(new ImageCard());
        list.add(new ImageCard());
        list.add(new TextCard("Text: ", "5422450254"));
        list.add(new ImageCard());
        list.add(new TextCard("Text: ", "79343456"));
        list.add(new TextCard("Text: ", "4567878"));
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
                Toast.makeText(getContext(), "ADD IMAGE", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.mi_add_text:
                mAdapter.addData(new TextCard("text","654321354641"));
                Toast.makeText(getContext(), "ADD TEXT", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
