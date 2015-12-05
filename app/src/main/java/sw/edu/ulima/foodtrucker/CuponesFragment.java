package sw.edu.ulima.foodtrucker;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class CuponesFragment extends Fragment {

    @Bind(R.id.recycler_view_cupones)
    RecyclerView recyclerView;


    ParseQuery<ParseObject> query= ParseQuery.getQuery("promos");
    CuponesRecyclerAdapter adapter;
    List<ParseObject> list=new ArrayList<>();

    public CuponesFragment() {
    }



    public static CuponesFragment newInstance(){
        CuponesFragment fragment = new CuponesFragment();

        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_cupones, container, false);
        ButterKnife.bind(this, rootView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //el time out no me deja mostrar los truckers


        adapter= new CuponesRecyclerAdapter(list);
        recyclerView.setAdapter(adapter);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {


                for (ParseObject object : objects) {
                    list.add(object);
                }

                adapter.notifyDataSetChanged();

            }
        });


        return rootView;
    }

}
