package com.example.clearteste;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.clearteste.clearsale.PassiveFaceLiveness;
import com.example.clearteste.clearsale.PassiveFaceLivenessActivity;

import static com.example.clearteste.clearsale.PassiveFaceLiveness.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }



    /**
     * The ActiveFaceLiveness SDK can replace any selfie capture. The facial movements required will prevent third photos
     */
    public void passiveFaceLiveness(View view) {
        // Create the PassiveFaceLiveness parameter
        PassiveFaceLiveness passiveFaceLiveness = new PassiveFaceLiveness.Builder()
                // other optional parameters like style, layout, request timeout, etc. For more information, go to https://github.com/combateafraude/Android/wiki
                .build();

        // Start the PassiveFaceLivenessActivity passing the DocumentDetector object by parameter. The result will be collected in onActivityResult method below
        Intent intent = new Intent(this, PassiveFaceLivenessActivity.class);
        intent.putExtra(PARAMETER_NAME, passiveFaceLiveness);
        startActivityForResult(intent, 2);
    }
}