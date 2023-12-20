package pt.iade.joaquimclaudio.atividade;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

import pt.iade.joaquimclaudio.atividade.adapters.NoteItemRowAdapter;
import pt.iade.joaquimclaudio.atividade.models.NoteItem;

public class MainActivity extends AppCompatActivity {
    public static final int NOTE_ACTIVITY_RETURN_ID = 1;
    protected RecyclerView itemsListView;
    protected NoteItemRowAdapter itemRowAdapter;
    protected ArrayList<NoteItem> itemsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
            intent.putExtra("item_position", -1);
            intent.putExtra("item", new NoteItem());
            startActivityForResult(intent, NOTE_ACTIVITY_RETURN_ID);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NOTE_ACTIVITY_RETURN_ID){
            if (resultCode == AppCompatActivity.RESULT_OK){
                boolean delete = data.getBooleanExtra("delete", false);
                int position = data.getIntExtra("item_position", -1);
                NoteItem updatedItem = (NoteItem) data.getSerializableExtra("item");
                if (delete){
                    if (position != -1){
                        //  Delete an item in the list
                        itemsList.remove(position);
                        itemRowAdapter.notifyItemRemoved(position);
                    }

                } else {

                    if (position == -1){
                        //  Add new item to the list
                        itemsList.add(updatedItem);
                        itemRowAdapter.notifyItemInserted(itemsList.size() - 1);

                    }else{
                        //  Update an existing item
                        itemsList.set(position, updatedItem);
                        itemRowAdapter.notifyItemChanged(position);
                    }
                }

            }
        }
    }

    public void setupComponents() {
        //  Set up action bar
        setSupportActionBar(findViewById(R.id.toolbar));

        Log.e("setupComponents", "Entrou no setupComponents");

        NoteItem.List(new NoteItem.ListResponse() {
            @Override
            public void response(ArrayList<NoteItem> items) {
                itemsList = items;
                //  Set up row adapter
                itemRowAdapter = new NoteItemRowAdapter(MainActivity.this, itemsList);
                itemRowAdapter.setOnClickListener(new NoteItemRowAdapter.ItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(MainActivity.this, NoteActivity.class);
                        intent.putExtra("item_position", position);
                        intent.putExtra("item", itemsList.get(position));

                        startActivityForResult(intent, NOTE_ACTIVITY_RETURN_ID);
                    }
                });

                //  Set up the notes RecyclerView
                itemsListView = (RecyclerView) findViewById(R.id.notes_list);
                itemsListView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                Log.e("setupComponents", "Chegou ao setAdapter");
                itemsListView.setAdapter(itemRowAdapter);
            }
        });



    }
}