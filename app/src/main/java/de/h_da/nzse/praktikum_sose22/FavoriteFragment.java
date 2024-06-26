package de.h_da.nzse.praktikum_sose22;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FavoriteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FavoriteFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    List<StationItem> favoredStations;

    private StationListAdapter stationListAdapter;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FavoriteFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FavoriteFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FavoriteFragment newInstance(String param1, String param2) {
        FavoriteFragment fragment = new FavoriteFragment();
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
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            favoredStations = getArguments().getParcelableArrayList("selected");
            Log.e("MapFragment", "Received " +
                    favoredStations.size() + " Stations from the map");
            favoredStations.forEach(stationItem -> Log.e("LIST_FRAGMENT", stationItem.street + stationItem.streetNumber));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        createList(view);
    }

    private void createList(View view) {
        RecyclerView sList = view.findViewById(R.id.resultView);
        stationListAdapter = new StationListAdapter(requireContext(), favoredStations, StationListAdapter.MODUS.FAVORITE);
        sList.setAdapter(stationListAdapter);
        sList.setLayoutManager(new LinearLayoutManager(this.getContext()));

        SharedPreferences pref = requireContext().getSharedPreferences(getString(R.string.shared_preferences),
                Context.MODE_PRIVATE);
        boolean isRepairman = pref.getBoolean("isRepairman", true);

    }

    public void setData(Set<StationItem> favorised) {
        if (stationListAdapter != null) {
            stationListAdapter.setData(new ArrayList<>(favorised));
        }
    }

    // Initialise it from onAttach()
}