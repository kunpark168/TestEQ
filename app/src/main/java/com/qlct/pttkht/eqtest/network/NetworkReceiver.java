package com.qlct.pttkht.eqtest.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Anh on 07/04/2018.
 */

public class NetworkReceiver extends BroadcastReceiver {

    private INetworkChange mListener;
    private Context mContext;

    public NetworkReceiver(INetworkChange mListener, Context mContext) {
        this.mListener = mListener;
        this.mContext = mContext;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (Connectivity.isConnected(mContext)) {
            if (Connectivity.isConnectedWifi(mContext)) {
                mListener.onNetworkchange(true, 1);
            } else if (Connectivity.isConnectedMobile(mContext))
                mListener.onNetworkchange(true, 0);
        } else
            mListener.onNetworkchange(false, 100);

    }

}