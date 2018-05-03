/**
 * PdfPrint for printing
 *
 * @author Brother Industries, Ltd.
 * @version 2.2
 */
package com.winfo.szrsp.app.print.printprocess;

import android.content.Context;
import android.os.Build;

import com.brother.ptouch.sdk.PrinterInfo.ErrorCode;
import com.winfo.szrsp.app.print.common.MsgDialog;
import com.winfo.szrsp.app.print.common.MsgHandle;


public class PdfPrint extends BasePrint {

    private int startIndex;
    private int endIndex;
    private String mPdfFile;

    public PdfPrint(Context context, MsgHandle mHandle, MsgDialog mDialog) {
        super(context, mHandle, mDialog);
    }

    /**
     * get print pdf pages
     */
    public int getPdfPages(String file) {
        if (Build.VERSION.SDK_INT < 21) {
            return mPrinter.getPDFPages(file);
        } else {
            return mPrinter.getPDFFilePages(file);

        }
    }

    /**
     * set print pdf pages
     */
    public void setPrintPage(int start, int end) {

        startIndex = start;
        endIndex = end;
    }

    /**
     * set print data
     */
    public void setFiles(String file) {
        mPdfFile = file;

    }

    /**
     * do the particular print
     */
    @Override
    protected void doPrint() {

        for (int i = startIndex; i <= endIndex; i++) {
            if (Build.VERSION.SDK_INT < 21) {
                mPrintResult = mPrinter.printPDF(mPdfFile, i);
            } else {
                mPrintResult = mPrinter.printPdfFile(mPdfFile, i);

            }
            if (mPrintResult.errorCode != ErrorCode.ERROR_NONE) {
                break;
            }
        }
    }

}
