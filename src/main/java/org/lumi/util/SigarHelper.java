package org.lumi.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * Created by John Tsantilis (A.K.A lumi) on 30/8/2016.
 */
public class SigarHelper {
    /**
     * Inject the bundled sigar library from the agent jar to the jvm (it gets extracted to the user home directory)
     * @param library
     */
    public static void loadLibrary(String library) {
        try {
            String outPath = System.getProperty("user.home");
            File outputLibraryFolder = new File(outPath);
            /*if ( !outputLibraryFolder.exists() ) {
                outputLibraryFolder.mkdir();

            }*/
            InputStream in =  SigarHelper.class.getClassLoader().getResourceAsStream( library );
            File outputFile = new File( outputLibraryFolder, library );

            if (!outputFile.exists()) {
                //outputFile.delete();
                outputFile.createNewFile();
                FileOutputStream outputStream = new FileOutputStream( outputFile );
                byte[] array = new byte[8192];
                int bytesRead = -1;
                while ( ( bytesRead = in.read( array ) ) != -1 ) {
                    outputStream.write( array, 0, bytesRead );

                }
                outputStream.flush();
                outputStream.close();

            }

            System.load( outputFile.getAbsolutePath() );
            System.setProperty("org.hyperic.sigar.path", outPath);

        }
        catch ( Throwable e ) {
            e.printStackTrace();

        }

    }

}
