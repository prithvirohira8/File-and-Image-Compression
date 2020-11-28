(Note: The compression of files and images work for only .txt files and .JPG images)

This is a program to comress and decompress files and compress images using Java.
The GUI made to execute this program is made using Java Swing.

The DeflaterOutputStream and InflaterInputStream classes provide mechanism to compress and decompress the data in the deflate compression format.
After the files are compressed or decompressed the original files are deleted. 

In order to compress an image, we read the image and convert into BufferedImage object
The compression quality set for the images is 0.05.

(Note: While saving the files or images add the extesions .txt or .jpg)