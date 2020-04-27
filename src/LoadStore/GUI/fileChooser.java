package LoadStore.GUI;

import java.io.File;
import java.nio.file.Path;

import javax.swing.JButton;
import javax.swing.JFileChooser;

public class fileChooser 
{
    File selectedFile=null;
public  fileChooser(String name){
JButton open=new JButton();
JFileChooser jf = new JFileChooser();
jf.setCurrentDirectory(new java.io.File("."));
jf.setDialogTitle("Select " + name+ " File");
if(jf.showOpenDialog(open)==JFileChooser.APPROVE_OPTION){
    
}
selectedFile=jf.getSelectedFile();
System.out.println("fileChooser:"+jf.getSelectedFile());
}

public File getSelectedFile() {
    return selectedFile;
}




    
}