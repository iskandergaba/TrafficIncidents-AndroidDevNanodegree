package com.gaba.alex.trafficincidents;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.gaba.alex.trafficincidents.Adapter.IncidentsAdapter;
import com.gaba.alex.trafficincidents.Data.IncidentsColumns;
import com.gaba.alex.trafficincidents.Data.IncidentsProvider;

/**
 * A placeholder fragment containing a simple view.
 */
public class IncidentsFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
    IncidentsAdapter mAdapter;
    ListView mListView;


    public IncidentsFragment() {
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_incidents, container, false);

        mListView = (ListView)rootView.findViewById(R.id.list_view);

        mAdapter = new IncidentsAdapter(getActivity().getBaseContext(),
                R.layout.list_view_item,
                null,
                new String[] { IncidentsColumns.TYPE, IncidentsColumns.DESCRIPTION, IncidentsColumns.ROAD_CLOSED, IncidentsColumns.SEVERITY},
                new int[] { R.id.incident_type , R.id.incident_description, R.id.incident_road_closed, R.id.incident_severity});
        mListView.setAdapter(mAdapter);

        /** Creating a loader for populating listview from sqlite database */
        /** This statement, invokes the method onCreatedLoader() */
        getActivity().getSupportLoaderManager().initLoader(0, null, this);
        return rootView;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Uri uri = IncidentsProvider.Incidents.CONTENT_URI;
        return new CursorLoader(getActivity(), uri, null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
    }
}
