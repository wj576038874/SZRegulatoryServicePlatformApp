/**
 * Activity of printing image/prn files
 *
 * @author Brother Industries, Ltd.
 * @version 2.2
 */
package com.winfo.szrsp.app.print;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.print.common.Common;
import com.winfo.szrsp.app.print.common.MsgDialog;
import com.winfo.szrsp.app.print.common.MsgHandle;
import com.winfo.szrsp.app.print.printprocess.ImagePrint;

import java.util.ArrayList;

public class Activity_PrintImage extends BaseActivity {

    private final ArrayList<String> mFiles = new ArrayList<String>();
    private ImageView mImageView;
    private Button mBtnPrint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print_image);


        Button btnSelectFile = (Button) findViewById(R.id.btnSelectFile);
        btnSelectFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectFileButtonOnClick();
            }
        });

        Button btnPrinterSettings = (Button) findViewById(R.id.btnPrinterSettings);
        btnPrinterSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                printerSettingsButtonOnClick();

            }
        });

        Button btnPrinterStatus = (Button) findViewById(R.id.btnPrinterStatus);
        btnPrinterStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                printerStatusButtonOnClick();

            }
        });

        mBtnPrint = (Button) findViewById(R.id.btnPrint);
        mBtnPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                printButtonOnClick();

            }
        });

        mBtnPrint.setEnabled(false);

        CheckBox chkMutilSelect = (CheckBox) this
                .findViewById(R.id.chkMultipleSelect);
        chkMutilSelect
                .setOnCheckedChangeListener(new OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton arg0,
                                                 boolean arg1) {
                        showMultiSelect(arg1);
                    }
                });

        mImageView = (ImageView) this.findViewById(R.id.imageView);

        // get data from other application by way of intent sending
        final Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String file = extras.getString(Common.INTENT_FILE_NAME);
            setDisplayFile(file);
            mBtnPrint.setEnabled(true);
        }

        // initialization for printing
        mDialog = new MsgDialog(this);
        mHandle = new MsgHandle(this, mDialog);
        myPrint = new ImagePrint(this, mHandle, mDialog);

        // when use bluetooth print set the adapter
        BluetoothAdapter bluetoothAdapter = super.getBluetoothAdapter();
        myPrint.setBluetoothAdapter(bluetoothAdapter);
    }

    /**
     * Called when [select file] button is tapped
     */
    @Override
    public void selectFileButtonOnClick() {

        SharedPreferences prefs = PreferenceManager
                .getDefaultSharedPreferences(this);

        // call File Explorer Activity to select a image or prn file
        final String imagePrnPath = prefs.getString(
                Common.PREFES_IMAGE_PRN_PATH, "");
        final Intent fileList = new Intent(Activity_PrintImage.this,
                Activity_FileList.class);
        fileList.putExtra(Common.INTENT_TYPE_FLAG, Common.FILE_SELECT_PRN_IMAGE);
        fileList.putExtra(Common.INTENT_FILE_NAME, imagePrnPath);
        startActivityForResult(fileList, Common.FILE_SELECT_PRN_IMAGE);
    }

    /**
     * Called when [Print] button is tapped
     */
    @Override
    public void printButtonOnClick() {

        // set the printing data
        ((ImagePrint) myPrint).setFiles(mFiles);

        if (!checkUSB())
            return;

        // call function to print
        myPrint.print();
    }

    /**
     * Called when [Printer Status] button is tapped
     */
    private void printerStatusButtonOnClick() {
        if (!checkUSB())
            return;
        myPrint.getPrinterStatus();
    }

    /**
     * Called when an activity you launched exits, giving you the requestCode
     * you started it with, the resultCode it returned, and any additional data
     * from it.
     */
    @Override
    protected void onActivityResult(final int requestCode,
                                    final int resultCode, final Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        // set the image/prn file
        if (resultCode == RESULT_OK
                && requestCode == Common.FILE_SELECT_PRN_IMAGE) {
            final String strRtn = data.getStringExtra(Common.INTENT_FILE_NAME);
            setImageOrPrnFile(strRtn);
        }
    }

    /**
     * set the image/prn file
     */
    private void setImageOrPrnFile(String file) {
        CheckBox chkMultiSelect = (CheckBox) this
                .findViewById(R.id.chkMultipleSelect);
        TextView tvSelectedFiles = (TextView) findViewById(R.id.tvSelectedFiles);

        if (chkMultiSelect.isChecked()) {
            if (!mFiles.contains(file)) {
                mFiles.add(file);

                int count = mFiles.size();
                String str = "";
                for (int i = 0; i < count; i++) {
                    str = str + mFiles.get(i) + "\n";
                }
                tvSelectedFiles.setText(str);
            }
        } else {
            setDisplayFile(file);
        }

        mBtnPrint.setEnabled(true);
    }

    /**
     * set the selected file to display
     */
    @SuppressWarnings("deprecation")
    private void setDisplayFile(String file) {
        mFiles.clear();
        mFiles.add(file);

        ((TextView) findViewById(R.id.tvSelectedFiles)).setText(file);
        if (Common.isImageFile(file)) {

            WindowManager windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
            Display display = windowManager.getDefaultDisplay();
            int displayWidth = display.getWidth();
            int displayHeight = display.getHeight();
            TextView tvSelectedFiles = (TextView) findViewById(R.id.tvSelectedFiles);

            int[] location = new int[2];
            tvSelectedFiles.getLocationOnScreen(location);

            int height = displayHeight - location[1]
                    - tvSelectedFiles.getHeight();
            Bitmap mBitmap = Common.fileToBitmap(file, displayWidth, height);

            mImageView.setImageBitmap(mBitmap);
        } else {
            mImageView.setImageBitmap(null);
        }
    }

    /**
     * set the status of controls when the [Multi Select] CheckBox is checked or
     * not
     */
    private void showMultiSelect(boolean isVisible) {
        mFiles.clear();
        mBtnPrint.setEnabled(false);

        TextView tvSelectedFiles = (TextView) findViewById(R.id.tvSelectedFiles);
        tvSelectedFiles.setText("");

        if (isVisible) {
            mImageView.setImageBitmap(null);
        }
    }

}
