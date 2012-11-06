package com.bs;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.logging.Log;

public class Executor {

	public static void execute(Log log, String[] commands) throws MojoExecutionException{
		try{
			Process pr = Runtime.getRuntime().exec(commands);
			BufferedReader input = new BufferedReader(new InputStreamReader(pr.getErrorStream()));
			 
            String line=null;
            while((line=input.readLine()) != null) {
                log.info("cc-maven-plugin: "+line);
            }

            int exitVal = pr.waitFor();
            if(exitVal != 0)
            	throw new MojoExecutionException("Failure exit code ["+exitVal+"]");
            log.info("cc-maven-plugin: Exited with no errors ["+exitVal+"]");
		}catch(Exception e){
			throw new MojoExecutionException(e.getMessage());
		}
	}
}
