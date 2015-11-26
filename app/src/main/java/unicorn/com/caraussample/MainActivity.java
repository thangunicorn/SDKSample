package unicorn.com.caraussample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.zensur.api.ZensurSDK;
import com.zensur.api.ZensurSDKListener;
import com.zensur.api.ZensurSDKResponse;

public class MainActivity extends AppCompatActivity {
    private TextView _tvResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        // SDK test
        InitView();
        startSDK();
    }

    private void InitView(){
        _tvResponse = (TextView)findViewById(R.id.tvResponse);
    }

    private void startSDK(){
        ZensurSDK.getInstance().initSDK(getApplicationContext());
//        ZensurSDK.getInstance().setCustomerId("alexandru_driver", "Token 7de60faceb1e4c87208df6966bc680afe712dcb8");
        ZensurSDK.getInstance().startService(new ZensurSDKListener() {
            @Override
            public void zensurSDKUpdate(final ZensurSDKResponse zensurSDKResponse) {
                Log.d("Polarax", "Responser " + zensurSDKResponse.getStatusDescription());
                _tvResponse.post(new Runnable() {
                    @Override
                    public void run() {
                        _tvResponse.append( zensurSDKResponse.getStatusDescription() + "\n");
                    }
                });
            }
        });
    }
}
