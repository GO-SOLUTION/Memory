package go_solution.memory;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.journeyapps.barcodescanner.CaptureActivity;
import com.google.zxing.client.android.Intents;
import com.google.zxing.integration.android.IntentIntegrator;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Saver> datasave;
    public int clicks;

    private RecyclerView rv;
    private RecyclerView.Adapter rvadapter;
    private RecyclerView.LayoutManager rvmanager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        datasave = new ArrayList<>();
        datasave.add(new Saver());

        rv = (RecyclerView)findViewById(R.id.recycler);
        rv.setHasFixedSize(false);
        rvmanager = new GridLayoutManager(this, 2);
        rv.setLayoutManager(rvmanager);
        rvadapter = new CustomAdapter(datasave,getApplicationContext(),this);
        rv.setAdapter(rvadapter);

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



}



