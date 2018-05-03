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
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.print.common.Common;
import com.winfo.szrsp.app.print.common.MsgDialog;
import com.winfo.szrsp.app.print.common.MsgHandle;
import com.winfo.szrsp.app.print.printprocess.PdfPrint;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PrintPdfActivity  extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.titleBar_imgbtn_back)
    ImageButton titleBar_imgbtn_back;

    @BindView(R.id.spinnerStartPage)
    Spinner mSpinnerStartPage;

    @BindView(R.id.spinnerEndPage)
    Spinner mSpinnerEndPage;
    @BindView(R.id.chkAllPages)
    CheckBox mChkAllPages;

    @BindView(R.id.btnPrint)
    Button mBtnPrint;

    @BindView(R.id.btnPrinterSettings)
    Button btnPrinterSettings;
    @BindView(R.id.tvSelectFile)
    TextView tvSelectFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print_pdf);
        ButterKnife.bind(this);
        initData();
        initEvent();
    }

    private void initData() {
        mBtnPrint.setEnabled(false);
        // initialization for printing
        mDialog = new MsgDialog(this);
        mHandle = new MsgHandle(this, mDialog);
        myPrint = new PdfPrint(this, mHandle, mDialog);

        BluetoothAdapter bluetoothAdapter = super.getBluetoothAdapter();
        myPrint.setBluetoothAdapter(bluetoothAdapter);

        mSpinnerStartPage.setEnabled(false);
        mSpinnerEndPage.setEnabled(false);
        mChkAllPages.setEnabled(false);
        // get data from other application by way of intent sending
        final Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String file = extras.getString(Common.INTENT_FILE_NAME);
            setPdfFile(file);
        }
    }

    private void initEvent() {
        titleBar_imgbtn_back.setOnClickListener(this);
        mBtnPrint.setOnClickListener(this);
        tvSelectFile.setOnClickListener(this);
        btnPrinterSettings.setOnClickListener(this);
        mChkAllPages.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton arg0, boolean arg1) {

                mSpinnerStartPage.setEnabled(!arg1);
                mSpinnerEndPage.setEnabled(!arg1);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.titleBar_imgbtn_back:
                finish();
                break;
            case R.id.btnPrint:
                printButtonOnClick();
                break;
            case R.id.tvSelectFile:
                selectFileButtonOnClick();
                break;
            case R.id.btnPrinterSettings:
                printerSettingsButtonOnClick();
                break;


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
        final Intent fileList = new Intent(PrintPdfActivity.this,
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
