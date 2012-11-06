package com.bs;

import java.io.File;

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
				throw new MojoExecutionException("Error create ["+outName+"] file");
			}
			
			String[] commands = new String[]{"python", 
					closureBaseDir+"/closure/bin/build/closurebuilder.py",
					"--root="+closureBaseDir+"/closure/goog",
					"--root="+closureBaseDir+"/third_party/closure/goog",
					"--root="+customBaseDir,
					"--namespace="+ns,
					"--output_mode=compiled",
					"--compiler_jar="+compilerJarfile,
					"--compiler_flags=--compilation_level=ADVANCED_OPTIMIZATIONS",
					"--output_file="+outName};
			Executor.execute(log,commands);
		}
	}
	
	

}
