package com.example.stars.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.stars.R;
import com.example.stars.adapter.StarAdapter;
import com.example.stars.classes.Star;
import com.example.stars.service.StarService;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private StarAdapter adapter;
    private List<Star> allStars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        allStars = StarService.getInstance().getAll();
        adapter = new StarAdapter(this, new ArrayList<>(allStars));

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setQueryHint("Search...");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) { return false; }

            @Override
            public boolean onQueryTextChange(String newText) {
                List<Star> filtered = new ArrayList<>();
                for (Star s : allStars) {
                    if (s.getNom().toLowerCase().contains(newText.toLowerCase()))
                        filtered.add(s);
                }
                adapter.updateList(filtered);
                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_share) {
            Intent share = new Intent(Intent.ACTION_SEND);
            share.setType("text/plain");
            share.putExtra(Intent.EXTRA_TEXT, "Découvrez l'app Stars !");
            startActivity(Intent.createChooser(share, "Partager via"));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}