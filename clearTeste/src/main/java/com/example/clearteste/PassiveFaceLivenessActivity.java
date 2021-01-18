package com.example.clearteste;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;

import static com.example.clearteste.PassiveFaceLiveness.PARAMETER_NAME;

public class PassiveFaceLivenessActivity extends Activity {

    // REQUEST_CODES to identify what activity is been started and know which result get in onActivityResult method
    private static final int PASSIVE_FACE_LIVENESS_CODE = 2;

    public PassiveFaceLivenessActivity() {
    }

    public void onCreate(Bundle var1) {
        PassiveFaceLiveness passiveFaceLiveness = (PassiveFaceLiveness) this.getIntent().getSerializableExtra("PassiveFaceLiveness");

        com.combateafraude.passivefaceliveness.PassiveFaceLiveness passiveFaceLivenessSdk = passiveFaceLiveness.getSdkObject();

        Intent intent = new Intent(this, com.combateafraude.passivefaceliveness.PassiveFaceLivenessActivity.class);
        intent.putExtra(PARAMETER_NAME, passiveFaceLivenessSdk);
        super.setIntent(intent);

        super.onCreate(var1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        // Discover if the SDK had finished with some result
        if (resultCode == RESULT_OK && data != null) {
            // Discover what SDK had finished
            if (requestCode == PASSIVE_FACE_LIVENESS_CODE) {
                PassiveFaceLivenessResult passiveFaceLivenessResult = (PassiveFaceLivenessResult) data.getSerializableExtra(PassiveFaceLivenessResult.PARAMETER_NAME);
                if (passiveFaceLivenessResult == null) {
                    Toast.makeText(this, "Should never get here!", Toast.LENGTH_SHORT).show();
                }
            }
        } else {
            Toast.makeText(this, "You closed the SDK activity!", Toast.LENGTH_SHORT).show();
        }
        super.onActivityResult(requestCode, resultCode, data);

        setResult(resultCode, data);
        finish();
    }

}
