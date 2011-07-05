/*
 * Copyright (c) 2011, Code Aurora Forum. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *        * Redistributions of source code must retain the above copyright
 *            notice, this list of conditions and the following disclaimer.
 *        * Redistributions in binary form must reproduce the above copyright
 *            notice, this list of conditions and the following disclaimer in the
 *            documentation and/or other materials provided with the distribution.
 *        * Neither the name of Code Aurora nor
 *            the names of its contributors may be used to endorse or promote
 *            products derived from this software without specific prior written
 *            permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NON-INFRINGEMENT ARE DISCLAIMED.    IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS;
 * OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR
 * OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 * ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.quicinc.fmradio;

import android.content.Intent;
import android.content.BroadcastReceiver;
import android.content.pm.PackageManager;
import android.content.Context;
import android.content.ComponentName;
import android.util.Log;
import android.os.SystemProperties;


public class FMTransmitterConfigReceiver extends BroadcastReceiver {

    private static final String TAG = "FMFolderConfigReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Log.d(TAG, "Received intent: " + action);
        if((action != null) && action.equals("android.intent.action.BOOT_COMPLETED")) {
            Log.d(TAG, "boot complete intent received");
            boolean isFmTransmitterSupported = SystemProperties.getBoolean("ro.fm.transmitter",true);
            if (!isFmTransmitterSupported) {
            PackageManager pManager = context.getPackageManager();
               if (pManager != null) {
                   Log.d(TAG, "disableing the FM Transmitter");
                   ComponentName fmTransmitter = new ComponentName("com.quicinc.fmradio", "com.quicinc.fmradio.FMTransmitterActivity");
                   pManager.setComponentEnabledSetting(fmTransmitter, PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                                                    PackageManager.DONT_KILL_APP);
               }
           }
        }
   }
}
