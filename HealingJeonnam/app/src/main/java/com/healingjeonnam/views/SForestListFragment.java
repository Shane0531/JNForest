package com.healingjeonnam.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.healingjeonnam.R;
import com.healingjeonnam.adapter.ForestRecyclerViewAdapter;
import com.healingjeonnam.base.APIService;
import com.healingjeonnam.base.ForestApp;
import com.healingjeonnam.models.Forest;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class SForestListFragment extends android.support.v4.app.Fragment {
    List<Forest> forest = ForestApp.forest;
    protected ForestRecyclerViewAdapter adapter;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://168.131.35.106:8080")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    APIService service = retrofit.create(APIService.class);

    static String name;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        adapter = new ForestRecyclerViewAdapter(forest);

        SearchActivity activity = (SearchActivity) getActivity();
        name = activity.getData();

        RecyclerView rv = (RecyclerView) inflater.inflate(
                R.layout.fragment_sforest_list, container, false);

        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            ImageLoader imageLoader = ImageLoader.getInstance();

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                switch (newState) {
                    case RecyclerView.SCROLL_STATE_IDLE:
                        imageLoader.resume();
                        break;
                    case RecyclerView.SCROLL_STATE_DRAGGING:
                        //imageLoader.pause();
                        break;
                    case RecyclerView.SCROLL_STATE_SETTLING:
                        imageLoader.pause();
                        break;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });


        if(name.equals("")) {
            final Call<List<Forest>> listCall = service.listForest();
            listCall.enqueue(new Callback<List<Forest>>() {
                @Override
                public void onResponse(Response<List<Forest>> response, Retrofit retrofit) {
                    forest.clear();
                    forest.addAll(response.body());
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onFailure(Throwable t) {
                    Toast.makeText(getActivity(), "인터넷이 연결되어있지 않습니다!", Toast.LENGTH_LONG).show();

                }
            });
        }
        else {
            final Call<List<Forest>> listCall = service.listSForest(name);
            listCall.enqueue(new Callback<List<Forest>>() {
                @Override
                public void onResponse(Response<List<Forest>> response, Retrofit retrofit) {
                    forest.clear();
                    forest.addAll(response.body());
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onFailure(Throwable t) {
                    Toast.makeText(getActivity(), "인터넷이 연결되어있지 않습니다!", Toast.LENGTH_LONG).show();

                }
            });
        }


        rv.setLayoutManager(new LinearLayoutManager(rv.getContext()));
        rv.setAdapter(adapter);
        return rv;
    }

}
