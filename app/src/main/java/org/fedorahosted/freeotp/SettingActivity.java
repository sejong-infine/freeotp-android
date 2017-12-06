package org.fedorahosted.freeotp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Switch;

import org.fedorahosted.freeotp.CredentialManager.TimeType;

public class SettingActivity extends Activity {
    CredentialManager mCredentialManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCredentialManager = CredentialManager.getInstance();
        setContentView(R.layout.setting);

        Switch enableLock = findViewById(R.id.setting_enable);
        RadioGroup durationGroup = findViewById(R.id.setting_durationGroup);

        enableLock.setChecked(mCredentialManager.getEnable());
        switch(mCredentialManager.getTime()) {
            case SEC_10:
                durationGroup.check(R.id.setting_duration10);
                break;
            case SEC_30:
                durationGroup.check(R.id.setting_duration30);
                break;
            case SEC_60:
                durationGroup.check(R.id.setting_duration60);
                break;
        }

        enableLock.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    mCredentialManager.setEnable(isChecked);
            }
        });

        durationGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId) {
                    case R.id.setting_duration10:
                        mCredentialManager.setTime(TimeType.SEC_10);
                        break;
                    case R.id.setting_duration30:
                        mCredentialManager.setTime(TimeType.SEC_30);
                        break;
                    case R.id.setting_duration60:
                        mCredentialManager.setTime(TimeType.SEC_60);
                        break;
                }
            }
        });
    }
}
