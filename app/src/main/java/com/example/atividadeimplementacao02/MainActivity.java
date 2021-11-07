package com.example.atividadeimplementacao02;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    List<ItemMenu> lista = new ArrayList<>();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ThreadAsyncTask tat = new ThreadAsyncTask();
        try {
            tat.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        exibirListaJsonWeb();
    }

    private void exibirListaJsonWeb() {
        RecyclerView rv = findViewById(R.id.recyclerView);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter((ArrayList<ItemMenu>) lista, this);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
    }

    /**
     * Inner class de AsyncTask
     *
     */
    private class ThreadAsyncTask extends AsyncTask<String, Integer, List<ItemMenu>> {

        @Override
        protected List<ItemMenu> doInBackground(String... param) {
            Log.i("AsyncTask", "doInBackground.....");
            try {
                String resourceURL = "http://192.168.0.5:5500/cardapio.json";
                URL url = new URL(resourceURL);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                InputStream is = con.getInputStream();
                java.util.Scanner s = new java.util.Scanner(is).useDelimiter("//A");
                String response = s.hasNext() ? s.next() : "";
                Gson gson = new Gson();
                Type type = new TypeToken<List<ItemMenu>>() {
                }.getType();

                lista = (List<ItemMenu>) gson.fromJson(response, type);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            return lista;
        }

        @Override
        protected void onPostExecute(List<ItemMenu> itensmMenu) {
            super.onPostExecute(itensmMenu);
        }
    }
}