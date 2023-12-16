package pt.iade.joaquimclaudio.atividade;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import pt.iade.joaquimclaudio.atividade.models.NoteItem;

public class NoteActivity extends AppCompatActivity {

    private TextView date;
    private EditText title;
    private EditText content;

    private NoteItem item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        Intent intent = getIntent();
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
    }
}