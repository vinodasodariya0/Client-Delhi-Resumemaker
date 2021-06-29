package nithra.resume.maker.cv.builder.app.Activity;

import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

public class FileManager {


    public static File getExternalBreezyDirectory(Context mContext) {


       /* if (Build.VERSION.SDK_INT > 29) {




            File file = new File(mContext.getExternalFilesDir(Environment.DIRECTORY_DCIM).toString() + "/Nithra/ResumeBuilder");

            if (!file.exists()) {
                file.mkdirs();
            }
            return file;

        } else {*/


            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).toString() + "/Nithra/ResumeBuilder");
            PrintStream printStream = System.out;
            printStream.println("dir==1" + file);
            if (!file.exists()) {
                file.mkdirs();
            }
            return file;
     /*   }*/



    }


    public static File getfile(Context mContext,String fileName)  {


        Log.e("TAG", "createEmptyFile: 10101010");

        File externalBreezyDirectory = FileManager.getExternalBreezyDirectory(mContext);
        PrintStream printStream = System.out;
        printStream.println("dir==" + externalBreezyDirectory);
        File file = new File(externalBreezyDirectory, fileName);

        return file;
    }
}
