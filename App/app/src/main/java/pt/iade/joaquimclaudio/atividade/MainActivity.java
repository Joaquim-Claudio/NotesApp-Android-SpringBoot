package pt.iade.joaquimclaudio.atividade;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import pt.iade.joaquimclaudio.atividade.models.NoteItem;

public class MainActivity extends AppCompatActivity {
    protected RecyclerView itemsListView;
    protected ArrayList<NoteItem> itemsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //  Get the items from the WebServer
        itemsList = NoteItem.List();

        setupComponents();
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_add){
            Intent intent = new Intent(MainActivity.this, NoteActivity.class);
            intent.putExtra("item", new NoteItem());
            startActivity(intent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void setupComponents() {
        //  Setup action bar
        setSupportActionBar(findViewById(R.id.toolbar));

        //  Setup the notes RecyclerView
        itemsListView = (RecyclerView) findViewById(R.id.notes_list);
        itemsListView.setLayoutManager(new LinearLayoutManager(this));
    }
}