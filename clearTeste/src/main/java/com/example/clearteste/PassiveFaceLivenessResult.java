package com.example.clearteste;

public class PassiveFaceLivenessResult extends com.combateafraude.passivefaceliveness.PassiveFaceLivenessResult{
    public com.combateafraude.helpers.sdk.failure.SDKFailure getSdkFailure(){
        return super.getSdkFailure();
    }

    public static final String PARAMETER_NAME = "PassiveFaceLivenessResult";
}
