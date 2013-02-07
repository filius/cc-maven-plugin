package com.bs;

import java.io.File;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugin.logging.Log;

import com.google.common.io.Files;

/**
 * @goal deps
 */
public class DepsMojo extends AbstractMojo{
	
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
	 * File name, where must be outputed deps.js
	 * 
	 * @parameter
	 */
	String outDepsfile;
	
	/**
	 * Path prefil for write in deps.js
	 * 
	 * @parameter
	 */
	String depsPrefix;
	
	private Log log; 
	
	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		log = getLog();
		
		File out = new File(outDepsfile);
		try{
			Files.createParentDirs(out);
			Files.touch(out);
		}catch(Exception e){
			throw new MojoExecutionException("Error create ["+outDepsfile+"] file", e);
		}
		
		String[] commands = new String[]{"python", 
				closureBaseDir+"/closure/bin/build/depswriter.py",
				"--root_with_prefix="+customBaseDir+" "+depsPrefix,
				"--output_file="+outDepsfile};
		Executor.execute(log,commands);
	}

}
