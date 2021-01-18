package com.example.clearteste.clearsale;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.combateafraude.helpers.sdk.failure.InvalidTokenReason;
import com.combateafraude.helpers.sdk.failure.LibraryReason;
import com.combateafraude.helpers.sdk.failure.NetworkReason;
import com.combateafraude.helpers.sdk.failure.PermissionReason;
import com.combateafraude.helpers.sdk.failure.SDKFailure;
import com.combateafraude.helpers.sdk.failure.ServerReason;
import com.combateafraude.helpers.sdk.failure.StorageReason;

import static com.example.clearteste.clearsale.PassiveFaceLiveness.PARAMETER_NAME;

public class PassiveFaceLivenessActivity extends com.combateafraude.passivefaceliveness.PassiveFaceLivenessActivity {

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
                if (passiveFaceLivenessResult != null) {
                    postPassiveFaceLiveness(passiveFaceLivenessResult);
                } else {
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

    private void postPassiveFaceLiveness(PassiveFaceLivenessResult passiveFaceLivenessResult) {
        SDKFailure sdkFailure = passiveFaceLivenessResult.getSdkFailure();
        if (sdkFailure == null) {
            Toast.makeText(this, "SDK successfully finished", Toast.LENGTH_SHORT).show();
        } else if (sdkFailure instanceof InvalidTokenReason) {
            Toast.makeText(this, "Invalid token", Toast.LENGTH_SHORT).show();
        } else if (sdkFailure instanceof PermissionReason) {
            Toast.makeText(this, "One or more permission is missing:" + sdkFailure.getMessage(), Toast.LENGTH_SHORT).show();
        } else if (sdkFailure instanceof NetworkReason) {
            Toast.makeText(this, "You don't have internet or the request exceeded the timeout", Toast.LENGTH_SHORT).show();
        } else if (sdkFailure instanceof ServerReason) {
            Toast.makeText(this, "There is some server error. Please, notify us: " + sdkFailure.getMessage(), Toast.LENGTH_SHORT).show();
        } else if (sdkFailure instanceof StorageReason) {
            Toast.makeText(this, "The SDK couldn't save the image because the device doesn't have enough space", Toast.LENGTH_SHORT).show();
        } else if (sdkFailure instanceof LibraryReason) {
            Toast.makeText(this, "One internal library failed to execute: " + sdkFailure.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
