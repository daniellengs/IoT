package br.com.dts.webservices;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import br.com.dts.webservices.adapter.PlacesAdapter;
import br.com.dts.webservices.model.Place;
import br.com.dts.webservices.model.PlaceGeosonList;
import br.com.dts.webservices.util.LogWrapper;
import br.com.dts.webservices.util.Utils;

/**
 * Created by diegosouza on 8/3/16.
 */


public class ListPlacesActivity extends AppCompatActivity {

    private Gson mGson;

    private PlacesAdapter mPlacesAdapter;

    public static final String EXTRA_PLACE = "place";

    private ListView mListPlaces;

    private Place[] mPlacles;

    private ProgressDialog mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListPlaces = (ListView) findViewById(R.id.list_places);
        mListPlaces.setEmptyView(findViewById(android.R.id.empty));
        //loadData();

        //Call AsyncTask to download json Data
        new SyncDataTask().execute();
    }

    private void loadData() {

        try {
            mGson = new Gson();

            PlaceGeosonList places;

            URL url = new URL(Utils.sURL);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(10 * 1000);
            connection.setConnectTimeout(15 * 1000);
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.setDoOutput(false);
            connection.connect();

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {

                final BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                places = mGson.fromJson(reader, PlaceGeosonList.class);

                mPlacles = places.getFeatures();

            } else {
                LogWrapper.log("Erro HTTP " + connection.getResponseCode());
                Snackbar.make(mListPlaces, getString(R.string.connection_exception), Snackbar.LENGTH_LONG).show();
            }


        } catch (IOException e) {
            //Toast.makeText(ListPlacesActivity.this, R.string.connection_exception, Toast.LENGTH_LONG).show();
            Snackbar.make(mListPlaces, getString(R.string.connection_exception), Snackbar.LENGTH_LONG).show();
        }
    }

    private void setupListView() {
        //init adapter
        mPlacesAdapter = new PlacesAdapter(this, mPlacles);

        mListPlaces.setAdapter(mPlacesAdapter);

        addListFooter();
        addListHeared();

        mListPlaces.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Place place = (Place) adapterView.getItemAtPosition(i);

                Intent it = new Intent(ListPlacesActivity.this, PlaceDetailActivity.class);
                it.putExtra(EXTRA_PLACE, place);

                startActivity(it);
            }
        });
    }

    private void addListFooter() {
        final int PADDING = 10;
        TextView txtHeader = new TextView(this);
        txtHeader.setBackgroundColor(Color.BLUE);
        txtHeader.setTextColor(Color.WHITE);
        txtHeader.setText(R.string.list_header_text);
        txtHeader.setPadding(PADDING, PADDING, 0, PADDING);
        mListPlaces.addHeaderView(txtHeader);

    }

    private void addListHeared() {
        final int PADDING = 10;
        final TextView txtFooter = new TextView(this);
        txtFooter.setText(getResources().getQuantityString(
                R.plurals.list_header_plural,
                mPlacesAdapter.getCount(),
                mPlacesAdapter.getCount()));
        txtFooter.setBackgroundColor(Color.BLUE);
        txtFooter.setGravity(Gravity.LEFT);
        txtFooter.setPadding(0, PADDING, PADDING, PADDING);
        mListPlaces.addFooterView(txtFooter);

    }

    private class SyncDataTask extends AsyncTask<Object, Object, Object> {

        @Override
        protected void onPreExecute() {
            //Show progress while downloading data
            mProgress = new ProgressDialog(ListPlacesActivity.this);
            mProgress.setTitle(R.string.dialog_wait_title);
            mProgress.setIcon(getDrawable(R.drawable.icon));
            mProgress.setMessage(getString(R.string.dialog_wait_message));
            mProgress.show();

        }

        @Override
        protected Object doInBackground(Object... params) {
            //load data in backgroud
            loadData();
            return null;
        }


        @Override
        protected void onPostExecute(Object result) {
            //Dismiss project and setup List Adapter
            if (mProgress != null && mProgress.isShowing()) {
                try {
                    mProgress.dismiss();
                } catch (Exception e) {

                }
            }
            setupListView();

        }
    }

}



