package com.zaid.www.util;

import java.io.PrintStream;
import java.util.Arrays;

/***
 * helper class to print red and black trees
 */
public class PaddedWriter {
    private int width = 0;
    private char fillChar = ' ';
    private final PrintStream writer;
    public PaddedWriter(PrintStream writer) {
        this.writer = writer;
    }
    void setw(int i) {
        width = i;
    }
    void setfill(char c) {
        fillChar = c;
    }
    void write(String str) {
        write(str.toCharArray());
    }
    void write(char[] buf) {
        if (buf.length < width) {
            char[] pad = new char[width - buf.length];
            Arrays.fill( pad, fillChar );
            writer.print(pad);
        }
        writer.print( buf );
        setw(0);
    }
    void write() {
        char[] pad = new char[width];
        Arrays.fill( pad, fillChar );
        writer.print(pad);
        setw(0);
    }
    void endl() {
        writer.println();
        setw(0);
    }
}
