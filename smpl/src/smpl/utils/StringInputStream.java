package smpl.utils;

import java.io.*;

public class StringInputStream extends InputStream {
    
    StringReader sr;

    public StringInputStream (String s) {
	sr = new StringReader(s);
    }

    public StringInputStream (StringReader reader) {
	sr = reader;
    }

    public int available() throws IOException {
	if (sr.ready())
	    return 1;
	else
	    return 0;
    }

    public void close() throws IOException {
	sr.close();
    }

    public void mark(int limit) {
	try {
	    sr.mark(limit);
	} catch (IOException ioe) {
	    System.out.println("Failed to set mark in String input stream: " +
			       ioe.getMessage());
	}
    }

    public boolean markSupported() {
	return sr.markSupported();
    }

    public void reset() throws IOException {
	sr.reset();
    }
    
    public int read() throws IOException {
	return sr.read();
    }

    public long skip(long n) throws IOException {
	return sr.skip(n);
    }

}
