package com.bs;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugin.logging.Log;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @goal soy
 */
public class SoyMojo extends AbstractMojo {


    private Log log;


    /**
     * Closure base dir, where Bin directory located.
     *
     * @parameter
     */
    String closureBaseDir;


    /**
     * Soy scanned directory
     *
     * @parameter
     */
    String soyDir;


    /**
     * Output directory for compiled sou.js files
     *
     * @parameter
     */
    String soyOutDir;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        log = getLog();

        ArrayList<String> commands = new ArrayList<String>(Arrays.asList("java",
                "-jar",
                closureBaseDir+"/SoyToJsSrcCompiler.jar",
                "--outputPathFormat",
                soyOutDir + "/{INPUT_FILE_NAME_NO_EXT}.js",
                "--shouldProvideRequireSoyNamespaces"));

        File[] files = new File(soyDir).listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.getName().contains(".soy");
            }
        });

        try{
            if(files != null && files.length > 0)
                for(File file : files){
                    log.info("Soy found: "+file.getCanonicalPath());
                    commands.add(file.getCanonicalPath());
                }
        }catch (Exception e){
            throw new MojoExecutionException("Error file scan", e);
        }

        Executor.execute(log,commands.toArray(new String[commands.size()]));

    }


//    java -jar /opt/closure/SoyToJsSrcCompiler.jar --outputPathFormat 'src/main/webapp/js/app/less/{INPUT_FILE_NAME_NO_EXT}.js' --shouldProvideRequireSoyNamespaces src/main/webapp/soy/test.soy

}
