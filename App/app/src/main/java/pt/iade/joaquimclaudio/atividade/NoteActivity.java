package pt.iade.joaquimclaudio.atividade;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import pt.iade.joaquimclaudio.atividade.models.NoteItem;

public class NoteActivity extends AppCompatActivity {

    protected TextView date;
    protected EditText title;
    protected EditText content;
    protected Switch importantSwitch;

    protected int itemPosition;
    protected NoteItem item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        Intent intent = getIntent();
        itemPosition = intent.getIntExtra("item_position", -1);
        item = (NoteItem) intent.getSerializableExtra("item");

        setupComponents();
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.note, menu);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_save){
            commitView();
            this.item.save();

            //  Setup the result to be return to the calling activity
            Intent returnIntent = new Intent();
            returnIntent.putExtra("delete", false);
            returnIntent.putExtra("item_position", itemPosition);
            returnIntent.putExtra("item", this.item);
            setResult(AppCompatActivity.RESULT_OK, returnIntent);

            finish();
            return true;
        } else if (item.getItemId() == R.id.action_undo){

        } else if (item.getItemId() == R.id.action_delete){
            //  Setup the result to be return to the calling activity
            Intent returnIntent = new Intent();
            returnIntent.putExtra("delete", true);
            returnIntent.putExtra("item_position", itemPosition);
            returnIntent.putExtra("item", this.item);
            setResult(AppCompatActivity.RESULT_OK, returnIntent);

            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setupComponents() {
        //  Setup action bar
        setSupportActionBar(findViewById(R.id.toolbar));

        //  Taking the all the views
        date = findViewById(R.id.note_date_textView);
        title = findViewById(R.id.note_title_editText);
        content = findViewById(R.id.note_content_multilineEditText);
        importantSwitch = findViewById(R.id.note_important);

        //  Populating components
        populateView();
    }

    protected void populateView(){
        int day = item.getCreationDate().get(Calendar.DAY_OF_MONTH);
        int month = item.getCreationDate().get(Calendar.MONTH);
        int year = item.getCreationDate().get(Calendar.YEAR);
        Date time = item.getCreationDate().getTime();
        date.setText("Criado a: " + String.format("%02d/%02d/%04d", day, month, year) + ", às ");
        title.setText(item.getTitle());
        content.setText(item.getContent());
        importantSwitch.setChecked(item.isImportant());
    }

    protected void commitView(){
        item.setTitle(title.getText().toString());
        item.setContent(content.getText().toString());
        item.setCreationDate(new GregorianCalendar());
        item.setImportant(importantSwitch.isChecked());
    }
}