package com.qlct.pttkht.eqtest.activity;

import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.qlct.pttkht.eqtest.MainActivity;
import com.qlct.pttkht.eqtest.R;
import com.qlct.pttkht.eqtest.network.Connectivity;
import com.qlct.pttkht.eqtest.network.INetworkChange;
import com.qlct.pttkht.eqtest.network.NetworkReceiver;
import com.qlct.pttkht.eqtest.utils.DialogOneButtonUtil;

public class BaseActivity extends AppCompatActivity implements INetworkChange {

    private boolean isRegisteredBroadcast = false;
    private NetworkReceiver receiver;
    private static  DialogOneButtonUtil dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        receiver = new NetworkReceiver(this, this);
        dialog = new DialogOneButtonUtil(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        final IntentFilter filters = new IntentFilter();
        filters.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        filters.addAction("android.net.wifi.WIFI_STATE_CHANGED");
        filters.addAction("android.net.wifi.STATE_CHANGE");
        if (!isRegisteredBroadcast) {
            registerReceiver(receiver, filters);
            isRegisteredBroadcast = true;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (isRegisteredBroadcast) {
            unregisterReceiver(receiver);
            isRegisteredBroadcast = false;
        }
    }

    @Override
    public void onNetworkchange(final boolean isConnectted, final int typeNetwork) {
        if (!Connectivity.isConnected(this)) {
            dialog.setText("Notice", "Please check your internet!");
            dialog.setType(3);
            dialog.setDialogOneButtonClick(new DialogOneButtonUtil.DialogOneButtonClickListener() {
                @Override
                public void okClick() {
                    dialog.dismiss();
                }
            });
            if (!dialog.isShowing())
                dialog.show();
        }

    }
}
