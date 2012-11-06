package com.bs;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.google.javascript.jscomp.CompilationLevel;
import com.google.javascript.jscomp.Compiler;
import com.google.javascript.jscomp.CompilerOptions;
import com.google.javascript.jscomp.PassConfig;
import com.google.javascript.jscomp.PassFactory;
import com.google.javascript.jscomp.SourceFile;
import com.google.javascript.jscomp.WarningLevel;

public class ClosureTest {

//	@Test
//	public void testCompile(){
//		
//		URL url = ClosureTest.class.getResource("/");
//		
//		CompilerOptions opts = new CompilerOptions();
//		CompilationLevel.SIMPLE_OPTIMIZATIONS.setOptionsForCompilationLevel(opts);
//		WarningLevel.VERBOSE.setOptionsForWarningLevel(opts);
//		
//		String externs[] = {
//				/*url.getPath()+"js/app/app1.js",
//				url.getPath()+"js/lib/comp1.js",
//				url.getPath()+"js/lib/comp2.js",
//				url.getPath()+"js/lib/comp3.js"*/
//		};
//		
//		String files[] = {
//				url.getPath()+"js/entries/entry1.js",
//				url.getPath()+"js/app/app1.js"/*,
//				url.getPath()+"js/lib/comp1.js",
//				url.getPath()+"js/lib/comp2.js"*/,
//				url.getPath()+"js/lib/comp3.js"
//		};
//		
//		Compiler comp = new Compiler();
//		
//		List<SourceFile> inps = new ArrayList<SourceFile>();
//		for(String file : files){
//			inps.add(SourceFile.fromFile(file));
//		}
//		List<SourceFile> exts = new ArrayList<SourceFile>();
//		for(String file : externs){
//			exts.add(SourceFile.fromFile(file));
//		}
//		comp.compile(/*Arrays.asList(SourceFile.fromCode("externs.js",
//		        "function alert(x) {}"))*/exts, 
//				/*Arrays.asList(SourceFile.fromFile(url.getPath()+"js/entries/entry1.js"))*/inps, 
//				opts);
//		
//		System.out.println(comp.toSource());
//		
//	}
}
