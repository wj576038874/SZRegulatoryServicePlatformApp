/**
 * Activity of printing pdf files
 *
 * @author Brother Industries, Ltd.
 * @version 2.2
 */

package com.winfo.szrsp.app.print;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.brother.ptouch.sdk.Printer;
import com.brother.ptouch.sdk.PrinterInfo;
import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.print.common.Common;
import com.winfo.szrsp.app.print.common.MsgDialog;
import com.winfo.szrsp.app.print.common.MsgHandle;
import com.winfo.szrsp.app.print.printprocess.PdfPrint;
import com.winfo.szrsp.app.print.printprocess.PrinterModelInfo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class Activity_PrintPdf extends BaseActivity {

    private Spinner mSpinnerStartPage;
    private Spinner mSpinnerEndPage;
    private CheckBox mChkAllPages;
    private Button mBtnPrint;
    private ImageButton titleBar_imgbtn_back;
    String namepath="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print_pdf);
        init();
        namepath=getIntent().getStringExtra("namepath2");


        // initialization for Activity
        mBtnPrint = (Button) findViewById(R.id.btnPrint);
        mBtnPrint.setEnabled(false);
        mBtnPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                printButtonOnClick();
            }
        });

        TextView tvSelectFile = (TextView) findViewById(R.id.tvSelectFile);
        tvSelectFile.setOnClickListener(new View.OnClickListener() {
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
        titleBar_imgbtn_back= (ImageButton) findViewById(R.id.titleBar_imgbtn_back);
        titleBar_imgbtn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // initialization for printing
        mDialog = new MsgDialog(this);
        mHandle = new MsgHandle(this, mDialog);
        myPrint = new PdfPrint(this, mHandle, mDialog);

        // set the adapter when printing by way of Bluetooth
        BluetoothAdapter bluetoothAdapter = super.getBluetoothAdapter();
        myPrint.setBluetoothAdapter(bluetoothAdapter);

        mSpinnerStartPage = (Spinner) findViewById(R.id.spinnerStartPage);
        mSpinnerEndPage = (Spinner) findViewById(R.id.spinnerEndPage);
        mSpinnerStartPage.setEnabled(false);
        mSpinnerEndPage.setEnabled(false);

        mChkAllPages = (CheckBox) this.findViewById(R.id.chkAllPages);
        mChkAllPages.setEnabled(false);
        mChkAllPages.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton arg0, boolean arg1) {

                mSpinnerStartPage.setEnabled(!arg1);
                mSpinnerEndPage.setEnabled(!arg1);
            }
        });

        // get data from other application by way of intent sending
//        final Bundle extras = getIntent().getExtras();
//        if (extras != null) {
//            String file = extras.getString(Common.INTENT_FILE_NAME);
//            setPdfFile(file);
//        }
    if(namepath!=null&&!namepath.equals("")){
        setPdfFile(namepath);
    }

    }
    private void init() {
        try {
            raw2file("RJ4030_102mm.bin", R.raw.rj4030_102mm);
            raw2file("RJ4030_102mm152mm.bin", R.raw.rj4030_102mm152mm);
            raw2file("RJ4040_102mm.bin", R.raw.rj4040_102mm);
            raw2file("RJ4040_102mm152mm.bin", R.raw.rj4040_102mm152mm);
            raw2file("RJ4030Ai_102mm.bin", R.raw.rj4030ai_102mm);
            raw2file("RJ4030Ai_102mm152mm.bin", R.raw.rj4030ai_102mm152mm);

            raw2file("RJ3050_76mm.bin", R.raw.rj3050_76mm);
            raw2file("RJ3150_76mm.bin", R.raw.rj3150_76mm);
            raw2file("RJ3150_76mm44mm.bin", R.raw.rj3150_76mm44mm);
            raw2file("RJ3050Ai_76mm.bin", R.raw.rj3050ai_76mm);
            raw2file("RJ3150Ai_76mm.bin", R.raw.rj3150ai_76mm);
            raw2file("RJ3150Ai_76mm44mm.bin", R.raw.rj3150ai_76mm44mm);
            raw2file("RJ2030_50mm.bin", R.raw.rj2030_50mm);
            raw2file("RJ2050_50mm.bin", R.raw.rj2050_50mm);
            raw2file("RJ2030_58mm.bin", R.raw.rj2030_58mm);
            raw2file("RJ2050_58mm.bin", R.raw.rj2050_58mm);
            raw2file("RJ2140_58mm.bin", R.raw.rj2140_58mm);
            raw2file("RJ2140_50x85mm.bin", R.raw.rj2140_50x85mm);
            raw2file("RJ2150_58mm.bin", R.raw.rj2150_58mm);
            raw2file("RJ2150_50x85mm.bin", R.raw.rj2150_50x85mm);


            raw2file("TD2020_57mm.bin", R.raw.td2020_57mm);
            raw2file("TD2020_40mm40mm.bin", R.raw.td2020_40mm40mm);
            raw2file("TD2120_57mm.bin", R.raw.td2120_57mm);
            raw2file("TD2120_40mm40mm.bin", R.raw.td2120_40mm40mm);
            raw2file("TD2130_57mm.bin", R.raw.td2130_57mm);
            raw2file("TD2130_40mm40mm.bin", R.raw.td2130_40mm40mm);
            raw2file("TD4100N_102mm.bin", R.raw.td4100n_102mm);
            raw2file("TD4100N_102mm152mm.bin", R.raw.td4100n_102mmx152mm);
            raw2file("TD4000_102mm.bin", R.raw.td4000_102mm);
            raw2file("TD4000_102mm152mm.bin", R.raw.td4000_102mmx152mm);

        } catch (Exception ignored) {
        }
        setPreferences();
    }
    private void setPreferences() {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(this);
        // initialization for print
        Printer printer = new Printer();
        PrinterInfo printerInfo = printer.getPrinterInfo();
        if (printerInfo == null) {
            printerInfo = new PrinterInfo();
            printer.setPrinterInfo(printerInfo);

        }
        if (sharedPreferences.getString("printerModel", "").equals("")) {
            String printerModel = printerInfo.printerModel.toString();
            PrinterModelInfo.Model model = PrinterModelInfo.Model.valueOf(printerModel);

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("printerModel", printerModel);
            editor.putString("port", printerInfo.port.toString());
            editor.putString("address", printerInfo.ipAddress);
            editor.putString("macAddress", printerInfo.macAddress);

            // Override SDK default paper size
            editor.putString("paperSize", model.getDefaultPaperSize());

            editor.putString("orientation", printerInfo.orientation.toString());
            editor.putString("numberOfCopies",
                    Integer.toString(printerInfo.numberOfCopies));
            editor.putString("halftone", printerInfo.halftone.toString());
            editor.putString("printMode", printerInfo.printMode.toString());
            editor.putString("pjCarbon", Boolean.toString(printerInfo.pjCarbon));
            editor.putString("pjDensity",
                    Integer.toString(printerInfo.pjDensity));
            editor.putString("pjFeedMode", printerInfo.pjFeedMode.toString());
            editor.putString("align", printerInfo.align.toString());
            editor.putString("leftMargin",
                    Integer.toString(printerInfo.margin.left));
            editor.putString("valign", printerInfo.valign.toString());
            editor.putString("topMargin",
                    Integer.toString(printerInfo.margin.top));
            editor.putString("customPaperWidth",
                    Integer.toString(printerInfo.customPaperWidth));
            editor.putString("customPaperLength",
                    Integer.toString(printerInfo.customPaperLength));
            editor.putString("customFeed",
                    Integer.toString(printerInfo.customFeed));
            editor.putString("paperPosition",
                    printerInfo.paperPosition.toString());
            editor.putString("customSetting",
                    sharedPreferences.getString("customSetting", ""));
            editor.putString("rjDensity",
                    Integer.toString(printerInfo.rjDensity));
            editor.putString("rotate180",
                    Boolean.toString(printerInfo.rotate180));
            editor.putString("dashLine", Boolean.toString(printerInfo.dashLine));

            editor.putString("peelMode", Boolean.toString(printerInfo.peelMode));
            editor.putString("mode9", Boolean.toString(printerInfo.mode9));
            editor.putString("pjSpeed", Integer.toString(printerInfo.pjSpeed));
            editor.putString("pjPaperKind", printerInfo.pjPaperKind.toString());
            editor.putString("printerCase",
                    printerInfo.rollPrinterCase.toString());
            editor.putString("printQuality", printerInfo.printQuality.toString());
            editor.putString("skipStatusCheck",
                    Boolean.toString(printerInfo.skipStatusCheck));
            editor.putString("checkPrintEnd", printerInfo.checkPrintEnd.toString());
            editor.putString("imageThresholding",
                    Integer.toString(printerInfo.thresholdingValue));
            editor.putString("scaleValue",
                    Double.toString(printerInfo.scaleValue));
            editor.putString("trimTapeAfterData",
                    Boolean.toString(printerInfo.trimTapeAfterData));
            editor.apply();
        }

    }
    /**
     * copy from raw in resource
     */
    private void raw2file(String fileName, int fileID) {

        File newdir = new File(Common.CUSTOM_PAPER_FOLDER);
        if (!newdir.exists()) {
            newdir.mkdir();
        }
        File dstFile = new File(Common.CUSTOM_PAPER_FOLDER + fileName);
        if (!dstFile.exists()) {
            try {
                InputStream input;
                OutputStream output;
                input = this.getResources().openRawResource(fileID);
                output = new FileOutputStream(dstFile);
                int DEFAULT_BUFFER_SIZE = 1024 * 4;
                byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
                int n;
                while (-1 != (n = input.read(buffer))) {
                    output.write(buffer, 0, n);
                }
                input.close();
                output.close();
            } catch (IOException ignored) {
            }
        }
    }
    /**
     * Called when [Select] button is tapped
     */
    @Override
    public void selectFileButtonOnClick() {

        // call File Explorer Activity to select a pdf file
        SharedPreferences prefs = PreferenceManager
                .getDefaultSharedPreferences(this);
        final String pdfPath = prefs.getString(Common.PREFES_PDF_PATH, "");
        final Intent fileList = new Intent(Activity_PrintPdf.this,
                Activity_FileList.class);
        fileList.putExtra(Common.INTENT_TYPE_FLAG, Common.FILE_SELECT_PDF);
        fileList.putExtra(Common.INTENT_FILE_NAME, pdfPath);
        startActivityForResult(fileList, Common.FILE_SELECT_PDF);

    }

    /**
     * Called when [Print] button is tapped
     */
    @Override
    public void printButtonOnClick() {
        if (!checkUSB())
            return;
        int startPage;
        int endPage;

        // All pages
        if (mChkAllPages.isChecked()) {
            startPage = 1;
            endPage = mSpinnerEndPage.getCount();
        } else { // set pages
            startPage = Integer.parseInt((String) mSpinnerStartPage
                    .getSelectedItem());
            endPage = Integer.parseInt((String) mSpinnerEndPage
                    .getSelectedItem());
        }

        // error if startPage > endPage
        if (startPage > endPage) {
            mDialog.showAlertDialog(getString(R.string.msg_title_warning),
                    getString(R.string.error_input));
            return;
        }

        // call function to print
        ((PdfPrint) myPrint).setPrintPage(startPage, endPage);
        myPrint.print();

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

        // get pdf File and set the new data to display
        if (resultCode == RESULT_OK && requestCode == Common.FILE_SELECT_PDF) {
            final String strRtn = data.getStringExtra(Common.INTENT_FILE_NAME);
            setPdfFile(strRtn);
        }
    }

    /**
     * set the pdf file for printing
     */
    private void setPdfFile(String file) {

        if (Common.isPdfFile(file)) {
            TextView txt = (TextView) findViewById(R.id.tvSelectedPdf);
            txt.setText(file);
            setSpinnerData(file);
            mChkAllPages.setEnabled(true);
            mChkAllPages.setChecked(true);
            mBtnPrint.setEnabled(true);
            ((PdfPrint) myPrint).setFiles(file);
        }
    }

    /**
     * set the data of Spinners
     */
    private void setSpinnerData(String pdfFile) {

        // get the pages info. of the pdf file
        int pages = ((PdfPrint) myPrint).getPdfPages(pdfFile);
        String data[] = new String[pages];
        for (int i = 0; i < pages; i++) {
            data[i] = String.valueOf(i + 1);
        }

        // set the pages info. to display
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(
                this, android.R.layout.simple_spinner_item, data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerStartPage.setAdapter(adapter);
        mSpinnerStartPage.setSelection(0);

        mSpinnerEndPage.setAdapter(adapter);
        mSpinnerEndPage.setSelection(pages - 1);

    }
}
