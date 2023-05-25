package edu.shoppinglist.androiddemo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    ImageButton scan;
    TextView shoppinglist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scan = findViewById(R.id.scan);
        shoppinglist = findViewById(R.id.shoppinglist);
        shoppinglist.setText("Lista zakupowa pusta\n\nZeskanuj kod QR z aplikacji web");
        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == scan) {
                    //Toast.makeText(getBaseContext(), "Skanuj kod", Toast.LENGTH_SHORT).show();
                    IntentIntegrator integrator = new IntentIntegrator(MainActivity.this);
                    integrator.setCaptureActivity(AnyOrientationCaptureActivity.class);
                    integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                    integrator.setPrompt("Skaner obsługuje wszystkie typy!");
                    integrator.setOrientationLocked(false);
                    integrator.setBeepEnabled(true);
                    integrator.initiateScan();
                }
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (intentResult != null) {
            if (intentResult.getContents() == null) {
                Toast.makeText(getBaseContext(), "Anulowano skanowanie", Toast.LENGTH_LONG).show();
            } else {
                String contents = data.getStringExtra("SCAN_RESULT");
                Typeface tf = Typeface.createFromAsset(getAssets(),"fonts/CoffeCake.ttf");
                shoppinglist.setTypeface(tf);
                shoppinglist.setText(contents + "\n\n\nUdanych zakupów!");
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}