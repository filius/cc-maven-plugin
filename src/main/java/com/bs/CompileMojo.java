package com.bs;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugin.logging.Log;

import com.google.common.io.Files;

/**
 * @goal compile
 */
public class CompileMojo extends AbstractMojo{

	
	/**
     * Closure base dir, where Bin directory located.
     *
     * @parameter
     */
	String closureBaseDir;
	
	/**
     * Custom project base dir, the root of your js files.
     *
     * @parameter
     */
	String customBaseDir;
	
	/**
     * 
     *
     * @parameter
     */
	String compilerJarfile;
	
	/**
     * 
     *
     * @parameter
     */
	String outDir;
	
	/**
     * 
     *
     * @parameter
     */
	String[] namespaces;
	
	/**
     * values of ADVANCED_OPTIMIZATIONS SIMPLE_OPTIMIZATIONS WHITESPACE_ONLY
     *
     * @parameter
     */
	String compilationLevel;

    /**
     *
     *
     * @parameter
     */
    String mapDir;
	
	private Log log; 
	
	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		log = getLog();
		
		for(String ns : namespaces){
			String outName = outDir+"/"+ns.toLowerCase()+".js";
			File outFile = new File(outName);
			try{
				Files.createParentDirs(outFile);
				Files.touch(outFile);
			}catch(Exception e){
				throw new MojoExecutionException("Error create ["+outName+"] file", e);
			}

            String mapFileName = null;
            if(mapDir != null && !mapDir.isEmpty()){
                mapFileName = mapDir + "/"+ns.toLowerCase()+".js.map";
                try{
                    Files.createParentDirs(new File(mapFileName));
                }catch(Exception e){
                    throw new MojoExecutionException("Error create ["+outName+"] file", e);
                }
            }

            ArrayList<String> commands = new ArrayList<String>(Arrays.asList("python",
					closureBaseDir+"/closure/bin/build/closurebuilder.py",
					//"--root="+closureBaseDir+"/closure/goog",
					"--root="+closureBaseDir+"/third_party/closure/goog",
					"--root="+customBaseDir,
					"--namespace="+ns,
					"--output_mode=compiled",
					"--compiler_jar="+compilerJarfile,
					"--compiler_flags=--compilation_level="+compilationLevel,
                    "--output_file="+outName));
            if(mapFileName != null){
                commands.add("--compiler_flags=--create_source_map="+mapFileName);
                commands.add("--compiler_flags=--source_map_format=V3");
            }
			Executor.execute(log,commands.toArray(new String[commands.size()]));

            if(mapFileName != null){
                try{
                    FileWriter fw = new FileWriter(outName, true);
                    fw.write("\n//@ sourceMappingURL=");
                    fw.write(mapFileName);
                    fw.close();
                }catch (Exception e){
                    throw new MojoExecutionException("Error write SourceMap to ["+outName+"]", e);
                }
            }
		}
	}
	
	

}
