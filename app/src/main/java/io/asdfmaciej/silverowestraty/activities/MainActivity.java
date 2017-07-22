package io.asdfmaciej.silverowestraty.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

import io.asdfmaciej.silverowestraty.R;
import io.asdfmaciej.silverowestraty.presenters.MainPresenter;

public class MainActivity extends AppCompatActivity implements MainPresenter.ViewContract {
    MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.initPresenter();
        this.attachButtons();
    }

    @Override
    public String getMap() {
        Spinner spinner = (Spinner) findViewById(R.id.spnMap);
        return spinner.getSelectedItem().toString();
    }

    @Override
    public String getState() {
        Spinner spinner = (Spinner) findViewById(R.id.spnStany);
        return spinner.getSelectedItem().toString();
    }

    @Override
    public String[] getStringArray(Integer id) {
        return getResources().getStringArray(id);
    }

    @Override
    public ArrayList<String> getPlayers() {
        ArrayList<String> arr = new ArrayList<>();
        arr.add(((EditText) findViewById(R.id.edGracz1)).getText().toString());
        arr.add(((EditText) findViewById(R.id.edGracz2)).getText().toString());
        arr.add(((EditText) findViewById(R.id.edGracz3)).getText().toString());
        arr.add(((EditText) findViewById(R.id.edGracz4)).getText().toString());
        arr.add(((EditText) findViewById(R.id.edGracz5)).getText().toString());
        return arr;
    }

    @Override
    public void setText(String txt) {
        TextView straty = (TextView) findViewById(R.id.txtStrategie);
        straty.setText(txt);
    }

    private void attachButtons() {
        Button btn = (Button) findViewById(R.id.btGeneruj);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.generate();
            }
        });
    }

    private void initPresenter() {
        presenter = new MainPresenter();
        presenter.attachView(this);
    }
}
