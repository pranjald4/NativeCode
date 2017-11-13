package com.example.pdesai4.temp;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.daon.fido.client.sdk.core.FidoSdkFactory;
import com.daon.fido.client.sdk.core.IFidoSdk;
import com.daon.fido.client.sdk.core.IUafInitialiseCallback;
import com.daon.sdk.authenticator.capture.CaptureFragment;
import com.daon.sdk.faceauthenticator.capture.AuthenticateFaceFragment;
import com.daon.sdk.faceauthenticator.capture.RegisterFaceFragment;

public class MainActivity extends AppCompatActivity implements CaptureFragment.CaptureCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialise SDK then list authenticators
        Bundle parameters = new Bundle();
        parameters.putString("com.daon.sdk.log","true");

        IFidoSdk sdk = FidoSdkFactory.getFidoSdk(this);
        sdk.initialise(parameters, new IUafInitialiseCallback() {
            @Override
            public void onUafInitialiseComplete() {
                try {
                    RegisterFaceFragment fragment = new RegisterFaceFragment();

                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.add(R.id.daonView, fragment);
                    transaction.commit();
                }catch (Exception e){
                    Log.i("Pranjal", e.toString());
                }
            }

            @Override
            public void onUafInitialiseFailed(int code, String message) {
                Log.i("Pranjal2", message);
            }
        });
    }

    @Override
    public void onCaptureComplete(Bundle bundle) {
        Log.i("Pranjal", "Success");
    }

    @Override
    public void onCaptureFailed(Bundle bundle, int i, String s) {
        Log.i("Pranjal", "Failed");
    }
}
