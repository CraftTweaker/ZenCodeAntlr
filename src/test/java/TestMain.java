import org.antlr.v4.gui.TestRig;

import java.io.File;
import java.util.Objects;

public class TestMain {
    
    public static void main(String[] args) {
        File scriptsDir = new File("scripts");
        scriptsDir.mkdirs();
        scriptsDir.mkdir();
        for(File file : Objects.requireNonNull(scriptsDir.listFiles())) {
            if(file.isDirectory()) {
                for(File child : Objects.requireNonNull(file.listFiles())) {
                    if(child.isDirectory()) {
                        System.out.println("Only single directories are supported! Found sub folder: " + child.getAbsolutePath());
                    } else if(child.getName().endsWith(".zs")) {
                        String testName = file.getName();
                        String childName = child.getName().replaceAll("\\.zs", "");
                        runTest(testName + "/" + childName, child, testName);
                    }
                }
            } else if(file.getName().endsWith(".zs")) {
                runTest(file.getName().replaceAll("\\.zs", ""), file);
            }
        }
        
    }
    
    private static void runTest(String testName, File file, String antlrTestName) {
        System.out.println("Running test: " + testName + " for antlr test: " + testName);
        try {
            TestRig.main(new String[] {"com.blamejared.zencode.parser.ZenCode", antlrTestName, "-tokens", file.getPath()});
        } catch(Exception e) {
            System.out.println("Error running test: '" + testName + "'!");
            e.printStackTrace();
        }
        System.out.println("Finished running test: " + testName);
    }
    
    private static void runTest(String testName, File file) {
        runTest(testName, file, testName);
    }
    
}
