import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.filechooser.*;
import java.io.*;
import java.util.*;
import java.awt.image.*;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;
import javax.imageio.*;
import javax.imageio.stream.ImageOutputStream;
import java.io.*;
import java.util.zip.*;
import java.util.*;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.Scanner;


class Gui extends JFrame implements ActionListener {

    static JLabel l;
    JFileChooser j;
    String q;

    public static void main(String args[])
    {

        JFrame f = new JFrame("File Compressor");


        f.setSize(400, 400);


        f.setVisible(true);

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JButton button1 = new JButton("Choose");


        JButton button2 = new JButton("Compress Image");

        JButton button3 = new JButton("Compress File");

        JButton button4 = new JButton("Decompress File");


        Gui g1 = new Gui();


        button1.addActionListener(g1);
        button2.addActionListener(g1);
        button3.addActionListener(g1);
        button4.addActionListener(g1);


        JPanel p = new JPanel();


        p.add(button1);
        p.add(button2);
        p.add(button3);
        p.add(button4);


        l = new JLabel("no file selected");


        p.add(l);
        f.add(p);

        f.show();
    }
    public void actionPerformed(ActionEvent evt)
    {

        String com = evt.getActionCommand();

        if (com.equals("Choose")) {

            j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());


            int r = j.showOpenDialog(null);


            if (r == JFileChooser.APPROVE_OPTION)

            {

                l.setText(j.getSelectedFile().getAbsolutePath());
            }

            else
                l.setText("the user cancelled the operation");


        }
        else if(com.equals("Compress File")){
            try {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Choose a File to upload");
                // pass reference of your JFrame here
                int response = fileChooser.showSaveDialog(this);
                if (response == JFileChooser.APPROVE_OPTION) {

                    File selectedFile = fileChooser.getSelectedFile();
                    q = selectedFile.getAbsolutePath();
                }
                FileInputStream fin = new FileInputStream(j.getSelectedFile().getAbsolutePath());
                FileOutputStream fout = new FileOutputStream(q);
                DeflaterOutputStream out = new DeflaterOutputStream(fout);

                int i;
                while ((i = fin.read()) != -1) {
                    out.write((byte) i);
                    out.flush();
                }
                fin.close();
                out.close();
            } catch (Exception e) {
                System.out.println(e);
            }
            l.setText("The operation has been executed");
            try {
                File f = new File(j.getSelectedFile().getAbsolutePath());           //file to be delete
                if (f.delete())                      //returns Boolean value
                {
                    l.setText(f.getName() + " deleted");   //getting and printing the file name
                } else {
                    l.setText("failed");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if(com.equals("Decompress File")) {
            try {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Choose a File to upload");
                // pass reference of your JFrame here
                int response = fileChooser.showSaveDialog(this);
                if (response == JFileChooser.APPROVE_OPTION) {

                    File selectedFile = fileChooser.getSelectedFile();
                    q = selectedFile.getAbsolutePath();
                }
                FileInputStream fin = new FileInputStream(j.getSelectedFile().getAbsolutePath());
                InflaterInputStream in = new InflaterInputStream(fin);
                FileOutputStream fout = new FileOutputStream(q);

                int i;
                while ((i = in.read()) != -1) {
                    fout.write((byte) i);
                    fout.flush();
                }
                fin.close();
                fout.close();
                in.close();
            } catch (Exception e) {
                System.out.println(e);
            }
            l.setText("The operation has been executed");
            try {
                File f = new File(j.getSelectedFile().getAbsolutePath());           //file to be delete
                if (f.delete())                      //returns Boolean value
                {
                    l.setText(f.getName() + " deleted");   //getting and printing the file name
                } else {
                    l.setText("failed");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


        }


        else{

            try{
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Choose a File to upload");
                // pass reference of your JFrame here
                int response = fileChooser.showSaveDialog(this);
                if (response == JFileChooser.APPROVE_OPTION) {

                    File selectedFile = fileChooser.getSelectedFile();
                    q = selectedFile.getAbsolutePath();
                }

                File input = new File(j.getSelectedFile().getAbsolutePath());
                BufferedImage image = ImageIO.read(input);

                File compressedImageFile = new File(q);
                OutputStream os =new FileOutputStream(compressedImageFile);

                Iterator<ImageWriter>writers =  ImageIO.getImageWritersByFormatName("jpg");
                ImageWriter writer = (ImageWriter) writers.next();

                ImageOutputStream ios = ImageIO.createImageOutputStream(os);
                writer.setOutput(ios);

                ImageWriteParam param = writer.getDefaultWriteParam();

                param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
                param.setCompressionQuality(0.5f);                                              // pass quality factor through variable.
                writer.write(null, new IIOImage(image, null, null), param);

                os.close();
                ios.close();
                writer.dispose();
                l.setText("Compression completed");


            }
            catch(IOException e ){
                System.out.println("Error occured");
            }




        }
    }
}
