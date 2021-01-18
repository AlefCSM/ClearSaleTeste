package com.example.clearteste.clearsale;

import java.io.Serializable;

public class PassiveFaceLiveness implements Serializable {
    private static com.combateafraude.passivefaceliveness.PassiveFaceLiveness passiveFaceLiveness;
    public static final String PARAMETER_NAME = "PassiveFaceLiveness";
    
    private PassiveFaceLiveness(){

    }

    public String getSDKName() {
        return passiveFaceLiveness.getSDKName();
    }

    public String getTokenProduct() {
        return passiveFaceLiveness.getTokenProduct();
    }

    public static String getToken() {
        return passiveFaceLiveness.getMobileToken();
    }

    public com.combateafraude.passivefaceliveness.PassiveFaceLiveness  getSdkObject(){
        return passiveFaceLiveness;
    }

    public static class Builder {
        public PassiveFaceLiveness build() {
            String token = "4de15d9da0027f4fbec91605df8aeea0";
             passiveFaceLiveness = new com.combateafraude.passivefaceliveness.PassiveFaceLiveness.Builder(token)
                    
                    .build();
            return new PassiveFaceLiveness();
        }
    }

}
