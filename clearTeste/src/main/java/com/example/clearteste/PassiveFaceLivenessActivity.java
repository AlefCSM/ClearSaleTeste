package com.example.clearteste;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import static com.example.clearteste.PassiveFaceLiveness.PARAMETER_NAME;

public class PassiveFaceLivenessActivity extends Activity {

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
}
