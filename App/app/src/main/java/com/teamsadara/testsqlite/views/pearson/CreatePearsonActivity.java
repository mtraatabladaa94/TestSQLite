package com.teamsadara.testsqlite.views.pearson;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.teamsadara.testsqlite.R;
import com.teamsadara.testsqlite.controllers.PearsonController;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreatePearsonActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private EditText txtPearsonName;
    private EditText txtBirthDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_pearson);

        ActionBar actionBar = this.getSupportActionBar();
        if(actionBar != null) {
            this.showUpButton(actionBar);
        }

        mTextMessage = (TextView) findViewById(R.id.message);
        txtPearsonName = (EditText)findViewById(R.id.edtPearsonName);
        txtBirthDate = (EditText)findViewById(R.id.edtBirthDate);

        Button btnSavePearson = (Button)findViewById(R.id.btnSavePearson);
        btnSavePearson.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                try {

                    Date birthDate = getDateFromString(txtBirthDate.getText().toString());
                    CreatePearson(txtPearsonName.getText().toString(), birthDate);
                    clearInput();

                }
                catch (ParseException ex) {
                    Toast.makeText(CreatePearsonActivity.this, "Error: " + ex.getMessage(), Toast.LENGTH_LONG).show();
                }
                catch (Exception ex) {
                    Toast.makeText(CreatePearsonActivity.this, "Error: " + ex.getMessage(), Toast.LENGTH_LONG).show();
                }

            }

        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case 16908332://Id de UpButton
                this.finish();
                break;
        }
        return (super.onOptionsItemSelected(item));

    }

    private void clearInput() {

        this.txtPearsonName.setText("");
        this.txtBirthDate.setText("");

    }

    private void showUpButton(ActionBar actionBar) {
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private Date getDateFromString(String dateText) throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date;
        date = dateFormat.parse(dateText);
        return date;

    }

    private void CreatePearson(String name, Date birthDate) {

        PearsonController controller = new PearsonController(this);
        controller.Create(name, birthDate);

    }

}
