package nithra.resume.maker.cv.builder.app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.ParcelFileDescriptor;
import android.print.PageRange;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import com.google.dexmaker.stock.ProxyBuilder;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class PdfWriter implements InvocationHandler {
    private PrintDocumentAdapter adapter;
    private Context context;
    @SuppressLint({"NewApi"})
    private final PrintAttributes defaultPrintAttributes = new PrintAttributes.Builder().setMediaSize(PrintAttributes.MediaSize.ISO_A4).setColorMode(2).setResolution(new PrintAttributes.Resolution("1", "Foo", 300, 300)).setMinMargins(PrintAttributes.Margins.NO_MARGINS).build();
    private File emptyPdfFile;
    private PrintAttributes newPrintAttributes;
    private PrintAttributes oldPrintAttributes;
    private PdfWriterCallback pdfWriterCallback;

    public interface PdfWriterCallback {
        void onWriteFailed();

        void onWriteFinished();
    }

    public PdfWriter(Context context2, PrintDocumentAdapter printDocumentAdapter, File file) {
        this.context = context2;
        this.adapter = printDocumentAdapter;
        this.emptyPdfFile = file;
    }

    public static File getDexCacheDirectory(Context context2) {
        return context2.getDir("dex-cache", 0);
    }

    public void setOldPrintAttributes(PrintAttributes printAttributes) {
        this.oldPrintAttributes = printAttributes;
    }

    public void setNewPrintAttributes(PrintAttributes printAttributes) {
        this.newPrintAttributes = printAttributes;
    }

    public PrintAttributes getDefaultPrintAttributes() {
        return this.defaultPrintAttributes;
    }

    @SuppressLint({"NewApi"})
    public void write(PdfWriterCallback pdfWriterCallback2) throws IOException {
        this.pdfWriterCallback = pdfWriterCallback2;
        PrintDocumentAdapter.LayoutResultCallback layoutResultCallback = getLayoutResultCallback(this);
        PrintDocumentAdapter printDocumentAdapter = this.adapter;
        PrintAttributes printAttributes = this.oldPrintAttributes;
        if (printAttributes == null) {
            printAttributes = this.defaultPrintAttributes;
        }
        PrintAttributes printAttributes2 = this.newPrintAttributes;
        if (printAttributes2 == null) {
            printAttributes2 = this.defaultPrintAttributes;
        }
        printDocumentAdapter.onLayout(printAttributes, printAttributes2, null, layoutResultCallback, null);
    }

    @Override // java.lang.reflect.InvocationHandler
    public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
        PdfWriterCallback pdfWriterCallback2;
        PdfWriterCallback pdfWriterCallback3;
        if (method != null && "onLayoutFinished".equals(method.getName())) {
            onWrite();
            return null;
        } else if (method != null && "onWriteFinished".equals(method.getName()) && (pdfWriterCallback3 = this.pdfWriterCallback) != null) {
            pdfWriterCallback3.onWriteFinished();
            return null;
        } else if (method == null || !"onWriteFailed".equals(method.getName()) || (pdfWriterCallback2 = this.pdfWriterCallback) == null) {
            return null;
        } else {
            pdfWriterCallback2.onWriteFailed();
            return null;
        }
    }

    @SuppressLint({"NewApi"})
    private void onWrite() throws IOException {
        PrintDocumentAdapter.WriteResultCallback writeResultCallback = getWriteResultCallback(this);
        ParcelFileDescriptor open = ParcelFileDescriptor.open(this.emptyPdfFile, 805306368);
        this.adapter.onWrite(new PageRange[]{PageRange.ALL_PAGES}, open, null, writeResultCallback);
    }

    @SuppressLint({"NewApi"})
    private PrintDocumentAdapter.LayoutResultCallback getLayoutResultCallback(InvocationHandler invocationHandler) throws IOException {
        return (PrintDocumentAdapter.LayoutResultCallback) ProxyBuilder.forClass(PrintDocumentAdapter.LayoutResultCallback.class).dexCache(getDexCacheDirectory(this.context)).handler(invocationHandler).build();
    }

    @SuppressLint({"NewApi"})
    private PrintDocumentAdapter.WriteResultCallback getWriteResultCallback(InvocationHandler invocationHandler) throws IOException {
        return (PrintDocumentAdapter.WriteResultCallback) ProxyBuilder.forClass(PrintDocumentAdapter.WriteResultCallback.class).dexCache(getDexCacheDirectory(this.context)).handler(invocationHandler).build();
    }
}
