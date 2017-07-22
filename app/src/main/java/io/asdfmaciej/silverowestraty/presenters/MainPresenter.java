package io.asdfmaciej.silverowestraty.presenters;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import io.asdfmaciej.silverowestraty.R;

/**
 * Created by Maciej on 2017-07-16.
 */

public class MainPresenter {
    public interface ViewContract {
        String getMap();
        String getState();
        String[] getStringArray(Integer id);
        ArrayList<String> getPlayers();
        void setText(String txt);
    }

    ViewContract viewContract;

    public void generate() {
        if (this.viewContract == null) {
            Log.v("mainpresenter", "generate failed due to null viewcontract");
            return;
        }
        Map mirage = new HashMap();
        mirage.put("Full buy", R.array.taktyki_mirage_full);
        mirage.put("Pistol", R.array.taktyki_mirage_pistol);
        Map mapy = new HashMap();
        mapy.put("Mirage", mirage);

        String map = viewContract.getMap();
        String state = viewContract.getState();
        ArrayList<String> players = viewContract.getPlayers();

        int id = (int) ((Map) mapy.get(map)).get(state);
        String[] taktyki = viewContract.getStringArray(id);
        String output = "";
        for (String p: players) {
            String taktyk = (taktyki[new Random().nextInt(taktyki.length)]);
            output += p + " - " + taktyk + "\n";
        }
        viewContract.setText(output);
    }

    public void attachView(ViewContract viewContract) {
        this.viewContract = viewContract;
        initializeView();
    }

    public void detachView() {
        this.viewContract = null;
    }
    public ViewContract getViewContract() { return viewContract; }

    private void initializeView() {
        if (this.viewContract == null) {
            Log.v("mainpresenter", "init failed due to null viewcontract");
            return;
        }

    }
}
