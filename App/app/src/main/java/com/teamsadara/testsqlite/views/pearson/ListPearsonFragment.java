package com.teamsadara.testsqlite.views.pearson;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.teamsadara.testsqlite.R;

import com.teamsadara.testsqlite.models.dataProvider.PearsonDataProvider;
import com.teamsadara.testsqlite.controllers.PearsonController;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ListPearsonFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ListPearsonFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListPearsonFragment extends Fragment {

    /*Components*/
    android.support.v7.widget.RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layout;
    ArrayList<PearsonDataProvider> arrayList = new ArrayList<PearsonDataProvider>();
    SwipeRefreshLayout swipeRefreshLayout;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ListPearsonFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListPearsonFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListPearsonFragment newInstance(String param1, String param2) {
        ListPearsonFragment fragment = new ListPearsonFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_pearson, container, false);

        //AQUÍ VA EL CÓDIGO DE 'FAB BUTTON'
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                Intent intent = new Intent(view.getContext(), CreatePearsonActivity.class);
                startActivity(intent);
            }
        });

        //AQUI VA EL CODIGO DEL DISEÑO
        this.recyclerView = (RecyclerView)view.findViewById(R.id.rcvPearsonList);
        this.LoadData();

        //SWIPER
        this.swipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.swipe_container);
        this.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {

                LoadData();
                swipeRefreshLayout.setRefreshing(false);

            }

        });

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private ArrayList<PearsonDataProvider> getPearsons() throws SQLException {
        PearsonController controller = new PearsonController(this.getActivity());
        return controller.getPearsons();
    }

    private void LoadData() {

        try {
            this.adapter = new PearsonRecyclerAdapter(this.getPearsons());
            this.recyclerView.setHasFixedSize(true);
            this.layout = new LinearLayoutManager(this.getActivity());
            recyclerView.setLayoutManager(this.layout);
            recyclerView.setAdapter(this.adapter);
        }
        catch(Exception ex) {
            Toast.makeText(this.getActivity(), "Error: " + ex.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

}
