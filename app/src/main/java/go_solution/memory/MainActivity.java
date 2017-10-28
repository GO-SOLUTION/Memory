package go_solution.memory;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.zxing.client.android.Intents;
import com.google.zxing.integration.android.IntentIntegrator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int SCAN_QR_CODE_REQUEST_CODE = 0;

    private List<Saver> datasave;
    public int clicks;

    private RecyclerView rv;
    private RecyclerView.Adapter rvadapter;
    private RecyclerView.LayoutManager rvmanager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        datasave = new ArrayList<>();
        datasave.add(new Saver());

        rv = (RecyclerView)findViewById(R.id.recycler);
        rv.setHasFixedSize(false);
        rvmanager = new GridLayoutManager(this, 2);
        rv.setLayoutManager(rvmanager);
        rvadapter = new CustomAdapter(datasave,getApplicationContext(),this);
        rv.setAdapter(rvadapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem menuItem = menu.add("Log");
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                try {
                    log(datasave);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    public void takeQrCodePicture() {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setCaptureActivity(MyCaptureActivity.class);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
        integrator.setOrientationLocked(false);
        integrator.addExtra(Intents.Scan.BARCODE_IMAGE_ENABLED, true);
        integrator.initiateScan();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i("MemoryActivity", "onActivityResult: ");
        if (requestCode == IntentIntegrator.REQUEST_CODE
                && resultCode == RESULT_OK) {

            Bundle extras = data.getExtras();
            String path = extras.getString(
                    Intents.Scan.RESULT_BARCODE_IMAGE_PATH);
            String code = extras.getString(
                    Intents.Scan.RESULT);
            datasave.get(clicks).setCode(code);
            datasave.get(clicks).setPath(path);
            if(clicks % 2 == 0 && clicks == datasave.size() -1) {
                datasave.add(new Saver());
                datasave.add(new Saver());
            }

            rvadapter.notifyDataSetChanged();


        }
    }

    private void log(List<Saver> memoryCards) throws JSONException {
        Intent intent = new Intent("ch.appquest.intent.LOG");

        if (getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY).isEmpty()) {
            Toast.makeText(this, "Logbook App not installed", Toast.LENGTH_LONG).show();
            return;
        }
        JSONArray jsonArrayMain = new JSONArray();
        for (int i = 0; i < memoryCards.size();i+=2) {
            if (i+1 <= datasave.size() -1) {
                JSONArray jsonArray = new JSONArray();
                jsonArray.put(memoryCards.get(i).getCode());
                jsonArray.put(memoryCards.get(i + 1).getCode());
                jsonArrayMain.put(jsonArray);
            }
        }
        JSONObject jsonMessage = new JSONObject();
        jsonMessage.put("task", "Memory");
        jsonMessage.put("solution", jsonArrayMain);
        String message = jsonMessage.toString();

        intent.putExtra("ch.appquest.logmessage", message);

        startActivity(intent);
    }

}



