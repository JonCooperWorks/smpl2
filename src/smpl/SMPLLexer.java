/* Specification for ECOLI tokens */
// user customisations
package smpl;
import java_cup.runtime.*;
// Jlex directives


public class SMPLLexer implements java_cup.runtime.Scanner {
	private final int YY_BUFFER_SIZE = 512;
	private final int YY_F = -1;
	private final int YY_NO_STATE = -1;
	private final int YY_NOT_ACCEPT = 0;
	private final int YY_START = 1;
	private final int YY_END = 2;
	private final int YY_NO_ANCHOR = 4;
	private final int YY_BOL = 128;
	private final int YY_EOF = 129;

    public int getChar() {
	return yychar + 1;
    }
    public int getLine() {
	return yyline + 1;
    }
    public String getText() {
	return yytext();
    }
	private java.io.BufferedReader yy_reader;
	private int yy_buffer_index;
	private int yy_buffer_read;
	private int yy_buffer_start;
	private int yy_buffer_end;
	private char yy_buffer[];
	private int yychar;
	private int yyline;
	private boolean yy_at_bol;
	private int yy_lexical_state;

	public SMPLLexer (java.io.Reader reader) {
		this ();
		if (null == reader) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(reader);
	}

	public SMPLLexer (java.io.InputStream instream) {
		this ();
		if (null == instream) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(new java.io.InputStreamReader(instream));
	}

	private SMPLLexer () {
		yy_buffer = new char[YY_BUFFER_SIZE];
		yy_buffer_read = 0;
		yy_buffer_index = 0;
		yy_buffer_start = 0;
		yy_buffer_end = 0;
		yychar = 0;
		yyline = 0;
		yy_at_bol = true;
		yy_lexical_state = YYINITIAL;
	}

	private boolean yy_eof_done = false;
	private final int YYSTRING = 1;
	private final int YYINITIAL = 0;
	private final int YYBINARY = 3;
	private final int YYCHAR = 2;
	private final int YYHEX = 4;
	private final int yy_state_dtrans[] = {
		0,
		74,
		142,
		144,
		144
	};
	private void yybegin (int state) {
		yy_lexical_state = state;
	}
	private int yy_advance ()
		throws java.io.IOException {
		int next_read;
		int i;
		int j;

		if (yy_buffer_index < yy_buffer_read) {
			return yy_buffer[yy_buffer_index++];
		}

		if (0 != yy_buffer_start) {
			i = yy_buffer_start;
			j = 0;
			while (i < yy_buffer_read) {
				yy_buffer[j] = yy_buffer[i];
				++i;
				++j;
			}
			yy_buffer_end = yy_buffer_end - yy_buffer_start;
			yy_buffer_start = 0;
			yy_buffer_read = j;
			yy_buffer_index = j;
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}

		while (yy_buffer_index >= yy_buffer_read) {
			if (yy_buffer_index >= yy_buffer.length) {
				yy_buffer = yy_double(yy_buffer);
			}
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}
		return yy_buffer[yy_buffer_index++];
	}
	private void yy_move_end () {
		if (yy_buffer_end > yy_buffer_start &&
		    '\n' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
		if (yy_buffer_end > yy_buffer_start &&
		    '\r' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
	}
	private boolean yy_last_was_cr=false;
	private void yy_mark_start () {
		int i;
		for (i = yy_buffer_start; i < yy_buffer_index; ++i) {
			if ('\n' == yy_buffer[i] && !yy_last_was_cr) {
				++yyline;
			}
			if ('\r' == yy_buffer[i]) {
				++yyline;
				yy_last_was_cr=true;
			} else yy_last_was_cr=false;
		}
		yychar = yychar
			+ yy_buffer_index - yy_buffer_start;
		yy_buffer_start = yy_buffer_index;
	}
	private void yy_mark_end () {
		yy_buffer_end = yy_buffer_index;
	}
	private void yy_to_mark () {
		yy_buffer_index = yy_buffer_end;
		yy_at_bol = (yy_buffer_end > yy_buffer_start) &&
		            ('\r' == yy_buffer[yy_buffer_end-1] ||
		             '\n' == yy_buffer[yy_buffer_end-1] ||
		             2028/*LS*/ == yy_buffer[yy_buffer_end-1] ||
		             2029/*PS*/ == yy_buffer[yy_buffer_end-1]);
	}
	private java.lang.String yytext () {
		return (new java.lang.String(yy_buffer,
			yy_buffer_start,
			yy_buffer_end - yy_buffer_start));
	}
	private int yylength () {
		return yy_buffer_end - yy_buffer_start;
	}
	private char[] yy_double (char buf[]) {
		int i;
		char newbuf[];
		newbuf = new char[2*buf.length];
		for (i = 0; i < buf.length; ++i) {
			newbuf[i] = buf[i];
		}
		return newbuf;
	}
	private final int YY_E_INTERNAL = 0;
	private final int YY_E_MATCH = 1;
	private java.lang.String yy_error_string[] = {
		"Error: Internal error.\n",
		"Error: Unmatched input.\n"
	};
	private void yy_error (int code,boolean fatal) {
		java.lang.System.out.print(yy_error_string[code]);
		java.lang.System.out.flush();
		if (fatal) {
			throw new Error("Fatal Error.\n");
		}
	}
	private int[][] unpackFromString(int size1, int size2, String st) {
		int colonIndex = -1;
		String lengthString;
		int sequenceLength = 0;
		int sequenceInteger = 0;

		int commaIndex;
		String workString;

		int res[][] = new int[size1][size2];
		for (int i= 0; i < size1; i++) {
			for (int j= 0; j < size2; j++) {
				if (sequenceLength != 0) {
					res[i][j] = sequenceInteger;
					sequenceLength--;
					continue;
				}
				commaIndex = st.indexOf(',');
				workString = (commaIndex==-1) ? st :
					st.substring(0, commaIndex);
				st = st.substring(commaIndex+1);
				colonIndex = workString.indexOf(':');
				if (colonIndex == -1) {
					res[i][j]=Integer.parseInt(workString);
					continue;
				}
				lengthString =
					workString.substring(colonIndex+1);
				sequenceLength=Integer.parseInt(lengthString);
				workString=workString.substring(0,colonIndex);
				sequenceInteger=Integer.parseInt(workString);
				res[i][j] = sequenceInteger;
				sequenceLength--;
			}
		}
		return res;
	}
	private int yy_acpt[] = {
		/* 0 */ YY_NO_ANCHOR,
		/* 1 */ YY_NO_ANCHOR,
		/* 2 */ YY_NO_ANCHOR,
		/* 3 */ YY_NO_ANCHOR,
		/* 4 */ YY_NO_ANCHOR,
		/* 5 */ YY_NO_ANCHOR,
		/* 6 */ YY_NOT_ACCEPT,
		/* 7 */ YY_NO_ANCHOR,
		/* 8 */ YY_NO_ANCHOR,
		/* 9 */ YY_NO_ANCHOR,
		/* 10 */ YY_NO_ANCHOR,
		/* 11 */ YY_NO_ANCHOR,
		/* 12 */ YY_NO_ANCHOR,
		/* 13 */ YY_NO_ANCHOR,
		/* 14 */ YY_NO_ANCHOR,
		/* 15 */ YY_NO_ANCHOR,
		/* 16 */ YY_NO_ANCHOR,
		/* 17 */ YY_NO_ANCHOR,
		/* 18 */ YY_NO_ANCHOR,
		/* 19 */ YY_NO_ANCHOR,
		/* 20 */ YY_NO_ANCHOR,
		/* 21 */ YY_NO_ANCHOR,
		/* 22 */ YY_NO_ANCHOR,
		/* 23 */ YY_NO_ANCHOR,
		/* 24 */ YY_NO_ANCHOR,
		/* 25 */ YY_NO_ANCHOR,
		/* 26 */ YY_NO_ANCHOR,
		/* 27 */ YY_NO_ANCHOR,
		/* 28 */ YY_NO_ANCHOR,
		/* 29 */ YY_NO_ANCHOR,
		/* 30 */ YY_NO_ANCHOR,
		/* 31 */ YY_NO_ANCHOR,
		/* 32 */ YY_NO_ANCHOR,
		/* 33 */ YY_NO_ANCHOR,
		/* 34 */ YY_NO_ANCHOR,
		/* 35 */ YY_NO_ANCHOR,
		/* 36 */ YY_NO_ANCHOR,
		/* 37 */ YY_NO_ANCHOR,
		/* 38 */ YY_NO_ANCHOR,
		/* 39 */ YY_NO_ANCHOR,
		/* 40 */ YY_NO_ANCHOR,
		/* 41 */ YY_NO_ANCHOR,
		/* 42 */ YY_NO_ANCHOR,
		/* 43 */ YY_NO_ANCHOR,
		/* 44 */ YY_NO_ANCHOR,
		/* 45 */ YY_NO_ANCHOR,
		/* 46 */ YY_NO_ANCHOR,
		/* 47 */ YY_NO_ANCHOR,
		/* 48 */ YY_NO_ANCHOR,
		/* 49 */ YY_NO_ANCHOR,
		/* 50 */ YY_NO_ANCHOR,
		/* 51 */ YY_NO_ANCHOR,
		/* 52 */ YY_NO_ANCHOR,
		/* 53 */ YY_NO_ANCHOR,
		/* 54 */ YY_NO_ANCHOR,
		/* 55 */ YY_NO_ANCHOR,
		/* 56 */ YY_NO_ANCHOR,
		/* 57 */ YY_NO_ANCHOR,
		/* 58 */ YY_NO_ANCHOR,
		/* 59 */ YY_NO_ANCHOR,
		/* 60 */ YY_NO_ANCHOR,
		/* 61 */ YY_NO_ANCHOR,
		/* 62 */ YY_NO_ANCHOR,
		/* 63 */ YY_NO_ANCHOR,
		/* 64 */ YY_NO_ANCHOR,
		/* 65 */ YY_NO_ANCHOR,
		/* 66 */ YY_NO_ANCHOR,
		/* 67 */ YY_NO_ANCHOR,
		/* 68 */ YY_NO_ANCHOR,
		/* 69 */ YY_NO_ANCHOR,
		/* 70 */ YY_NO_ANCHOR,
		/* 71 */ YY_NO_ANCHOR,
		/* 72 */ YY_NO_ANCHOR,
		/* 73 */ YY_NO_ANCHOR,
		/* 74 */ YY_NO_ANCHOR,
		/* 75 */ YY_NO_ANCHOR,
		/* 76 */ YY_NO_ANCHOR,
		/* 77 */ YY_NO_ANCHOR,
		/* 78 */ YY_NO_ANCHOR,
		/* 79 */ YY_NO_ANCHOR,
		/* 80 */ YY_NO_ANCHOR,
		/* 81 */ YY_NO_ANCHOR,
		/* 82 */ YY_NOT_ACCEPT,
		/* 83 */ YY_NO_ANCHOR,
		/* 84 */ YY_NO_ANCHOR,
		/* 85 */ YY_NO_ANCHOR,
		/* 86 */ YY_NO_ANCHOR,
		/* 87 */ YY_NO_ANCHOR,
		/* 88 */ YY_NOT_ACCEPT,
		/* 89 */ YY_NO_ANCHOR,
		/* 90 */ YY_NOT_ACCEPT,
		/* 91 */ YY_NO_ANCHOR,
		/* 92 */ YY_NOT_ACCEPT,
		/* 93 */ YY_NO_ANCHOR,
		/* 94 */ YY_NOT_ACCEPT,
		/* 95 */ YY_NO_ANCHOR,
		/* 96 */ YY_NOT_ACCEPT,
		/* 97 */ YY_NO_ANCHOR,
		/* 98 */ YY_NOT_ACCEPT,
		/* 99 */ YY_NO_ANCHOR,
		/* 100 */ YY_NOT_ACCEPT,
		/* 101 */ YY_NO_ANCHOR,
		/* 102 */ YY_NOT_ACCEPT,
		/* 103 */ YY_NO_ANCHOR,
		/* 104 */ YY_NOT_ACCEPT,
		/* 105 */ YY_NO_ANCHOR,
		/* 106 */ YY_NOT_ACCEPT,
		/* 107 */ YY_NO_ANCHOR,
		/* 108 */ YY_NOT_ACCEPT,
		/* 109 */ YY_NO_ANCHOR,
		/* 110 */ YY_NOT_ACCEPT,
		/* 111 */ YY_NO_ANCHOR,
		/* 112 */ YY_NOT_ACCEPT,
		/* 113 */ YY_NO_ANCHOR,
		/* 114 */ YY_NOT_ACCEPT,
		/* 115 */ YY_NO_ANCHOR,
		/* 116 */ YY_NOT_ACCEPT,
		/* 117 */ YY_NO_ANCHOR,
		/* 118 */ YY_NOT_ACCEPT,
		/* 119 */ YY_NO_ANCHOR,
		/* 120 */ YY_NOT_ACCEPT,
		/* 121 */ YY_NO_ANCHOR,
		/* 122 */ YY_NOT_ACCEPT,
		/* 123 */ YY_NO_ANCHOR,
		/* 124 */ YY_NOT_ACCEPT,
		/* 125 */ YY_NO_ANCHOR,
		/* 126 */ YY_NOT_ACCEPT,
		/* 127 */ YY_NO_ANCHOR,
		/* 128 */ YY_NOT_ACCEPT,
		/* 129 */ YY_NO_ANCHOR,
		/* 130 */ YY_NOT_ACCEPT,
		/* 131 */ YY_NO_ANCHOR,
		/* 132 */ YY_NOT_ACCEPT,
		/* 133 */ YY_NO_ANCHOR,
		/* 134 */ YY_NOT_ACCEPT,
		/* 135 */ YY_NO_ANCHOR,
		/* 136 */ YY_NOT_ACCEPT,
		/* 137 */ YY_NO_ANCHOR,
		/* 138 */ YY_NOT_ACCEPT,
		/* 139 */ YY_NO_ANCHOR,
		/* 140 */ YY_NOT_ACCEPT,
		/* 141 */ YY_NOT_ACCEPT,
		/* 142 */ YY_NOT_ACCEPT,
		/* 143 */ YY_NOT_ACCEPT,
		/* 144 */ YY_NOT_ACCEPT,
		/* 145 */ YY_NO_ANCHOR,
		/* 146 */ YY_NOT_ACCEPT,
		/* 147 */ YY_NOT_ACCEPT,
		/* 148 */ YY_NO_ANCHOR,
		/* 149 */ YY_NO_ANCHOR,
		/* 150 */ YY_NO_ANCHOR,
		/* 151 */ YY_NO_ANCHOR,
		/* 152 */ YY_NO_ANCHOR,
		/* 153 */ YY_NO_ANCHOR,
		/* 154 */ YY_NO_ANCHOR,
		/* 155 */ YY_NO_ANCHOR,
		/* 156 */ YY_NO_ANCHOR,
		/* 157 */ YY_NO_ANCHOR,
		/* 158 */ YY_NO_ANCHOR,
		/* 159 */ YY_NO_ANCHOR,
		/* 160 */ YY_NO_ANCHOR,
		/* 161 */ YY_NO_ANCHOR,
		/* 162 */ YY_NO_ANCHOR,
		/* 163 */ YY_NO_ANCHOR,
		/* 164 */ YY_NO_ANCHOR,
		/* 165 */ YY_NO_ANCHOR,
		/* 166 */ YY_NOT_ACCEPT,
		/* 167 */ YY_NO_ANCHOR,
		/* 168 */ YY_NO_ANCHOR,
		/* 169 */ YY_NO_ANCHOR,
		/* 170 */ YY_NO_ANCHOR,
		/* 171 */ YY_NO_ANCHOR,
		/* 172 */ YY_NO_ANCHOR,
		/* 173 */ YY_NO_ANCHOR,
		/* 174 */ YY_NO_ANCHOR,
		/* 175 */ YY_NO_ANCHOR
	};
	private int yy_cmap[] = unpackFromString(1,130,
"4:9,7,2,4:2,6,4:18,7,15,56,25,4,10,13,52,32,33,5,8,31,9,57,3,60:2,54:8,11,3" +
"0,17,12,16,47,24,59:4,26,59,53:5,27,53:6,28,53:7,36,55,37,4,61,4,18,51,40,2" +
"0,38,29,53,43,42,53:2,41,53,19,21,39,48,22,44,23,50,49,53,58,46,45,34,1,35," +
"14,4,0:2")[0];

	private int yy_rmap[] = unpackFromString(1,176,
"0,1,2,3,4,5,6,1:6,7,1:2,8,9,10,11,1,6,1:4,12:2,13,1,13,14,15,16,17,18,19,20" +
",21,22,23,12,24,25,12:3,26,27,28,29,30,12,31,32,12:4,33,12:6,34,12,35,1,12:" +
"4,36,1:3,37,38,12,39,40,39,1,41,42,43,13,44,14,45,15,46,16,47,17,48,18,49,5" +
"0,51,19,52,20,53,54,55,56,57,58,59,60,61,23,62,24,63,64,65,25,66,10,67,26,6" +
"8,27,69,28,70,29,71,72,73,30,74,75,76,34,77,78,79,80,81,82,83,84,85,86,87,8" +
"8,89,90,91,92,93,94,95,96,97,98,99,100,101,102,103,104,105,106,107,108,109," +
"110,111,112,113")[0];

	private int yy_nxt[][] = unpackFromString(114,62,
"1,78,2,3,-1,80,4,86,80,87,80,5,80:2,6,-1,80:3,165,145,80,167,168,-1,82,169," +
"80:3,7,8,9,10,11,12,13,14,170,171,148,149,89,80,172,80:6,91,15,80,16,80,17," +
"88,80:2,16,80,-1:63,79,2,90,-1,92,4,2,94,96,98,100,102,104,-1,146,106,108,1" +
"10,-1:2,112,-1:2,114,-1:40,19,-1,93,-1:2,80:3,-1,80:2,-1:2,80:8,-1:2,80:4,-" +
"1:8,80:14,-1,80:3,-1:2,80:4,-1:2,4,90,-1,92,4:2,94,96,98,100,102,104,-1,146" +
",106,108,110,-1:2,112,-1:2,114,-1:74,20,-1:26,21,-1:3,21:2,-1:65,25,-1:53,8" +
"0,-1,80,-1:2,80:3,-1,80:2,-1:2,80:8,-1:2,80:4,-1:8,80:14,-1,80,16,80,-1,28," +
"80:2,16,80,-1:56,29,-1:6,122,18,-1:4,122,-1:55,19,-1,19:3,-1,19:55,-1:3,80," +
"-1,80,-1:2,80:3,-1,80:2,-1:2,80:8,-1:2,80:4,-1:8,80:14,-1,80:3,-1:2,80:4,-1" +
":54,30,-1:5,30,-1:3,31,-1:3,31:2,-1:56,32,-1:3,32:2,-1:56,33,-1:3,33:2,-1:5" +
"6,34,-1:3,34:2,-1:56,35,-1:3,35:2,-1:56,36,-1:3,36:2,-1:56,37,-1:3,37:2,-1:" +
"56,38,-1:3,38:2,-1:56,39,-1:3,39:2,-1:56,40,-1:3,40:2,-1:114,42,-1:19,43,-1" +
",43,-1:5,43,-1:2,43,-1:8,43,-1,43,-1:10,43,-1:2,43,-1:4,43:2,-1:3,47,-1:3,4" +
"7:2,-1:56,48,-1:3,48:2,-1:56,49,-1:3,49:2,-1:56,50,-1:3,50:2,-1:56,51,-1:3," +
"51:2,-1:56,53,-1:3,53:2,-1:57,80,-1,80,-1:2,80:3,-1,80:2,-1:2,80:8,-1:2,80:" +
"4,-1:8,80:4,164,80:9,-1,80:3,-1:2,80:4,-1:3,80,-1,80,-1:2,80:3,-1,80:2,-1:2" +
",80:8,-1:2,80:4,-1:8,80:9,67,80:4,-1,80:3,-1:2,80:4,-1:2,66,-1:3,66:2,-1:57" +
",80,-1,80,-1:2,80:3,-1,80:2,-1:2,80:8,-1:2,80:4,-1:8,80:3,139,80:10,-1,80:3" +
",-1:2,80:4,1,85:55,75,85:5,-1,78,18,-1:4,78,-1:55,79:2,-1:4,79,-1:55,122,83" +
",90,-1,92,4,81,94,96,98,100,102,104,-1,146,106,108,110,-1:2,112,-1:2,114,-1" +
":60,22,-1:5,23,-1:8,24,-1:12,116,-1:3,118,-1:2,120,-1:4,85:55,-1,85:5,-1,78" +
",83,90,-1,92,4,86,94,96,98,100,102,104,-1,146,106,108,110,-1:2,112,-1:2,114" +
",-1:40,80,-1,80,-1:2,80:3,-1,80:2,-1:2,80:8,-1:2,80:4,-1:8,80:14,-1,80,16,8" +
"0,-1,88,80:2,16,80,-1:3,80,-1,80,-1:2,80:3,-1,80:2,-1:2,80:8,-1:2,80:3,26,-" +
"1:8,80:14,-1,80:3,-1:2,80:4,-1:3,80,-1,80,-1:2,80:3,-1,80:2,-1:2,80:8,-1:2," +
"80:4,-1:8,27,80:13,-1,80:3,-1:2,80:4,-1,136:2,80,136,103,136:2,93:3,136,93:" +
"2,136:2,93:8,136:2,93:4,136:8,93:14,136,93:3,136:2,93:4,-1:3,80,-1,80,-1:2," +
"80:3,-1,80:2,-1:2,80:8,-1:2,80:3,41,-1:8,80:14,-1,80:3,-1:2,80:4,-1:3,80,-1" +
",80,-1:2,80:3,-1,80:2,-1:2,80:6,44,80,-1:2,80:4,-1:8,80:3,121,80:2,123,80:7" +
",-1,80:3,-1:2,80:4,-1:3,80,-1,80,-1:2,80:3,-1,80:2,-1:2,80:6,45,80,-1:2,80:" +
"4,-1:8,80:14,-1,80:3,-1:2,80:4,-1:12,124,-1:52,80,-1,80,-1:2,80:3,-1,80:2,-" +
"1:2,80:7,46,-1:2,80:4,-1:8,80:14,-1,80:3,-1:2,80:4,-1:3,52,-1,80,-1:2,80:3," +
"-1,80:2,-1:2,80:8,-1:2,80:4,-1:8,80:14,-1,80:3,-1:2,80:4,-1:2,53,80,-1,80,5" +
"3:2,80:3,-1,80:2,-1:2,80:8,-1:2,80:4,-1:8,80:14,-1,80:3,-1:2,80:4,-1:2,38,-" +
"1:3,38:2,-1:4,128,-1:52,80,-1,80,-1:2,80:3,-1,80:2,-1:2,80:4,54,80:3,-1:2,8" +
"0:4,-1:8,80:14,-1,80:3,-1:2,80:4,-1:2,39,-1:3,39:2,-1:4,130,-1:52,80,-1,80," +
"-1:2,80:3,-1,80:2,-1:2,80:3,55,80:4,-1:2,80:4,-1:8,80:14,-1,80:3,-1:2,80:4," +
"-1:19,132,-1:45,80,-1,80,-1:2,80:3,-1,80:2,-1:2,80:8,-1:2,56,80:3,-1:8,80:1" +
"4,-1,80:3,-1:2,80:4,-1:22,134,-1:42,80,-1,80,-1:2,80:3,-1,80:2,-1:2,80:8,-1" +
":2,80:4,-1:8,57,80:13,-1,80:3,-1:2,80:4,-1:3,80,-1,80,-1:2,80:3,-1,80:2,-1:" +
"2,80:8,-1:2,80:4,-1:8,80:9,58,80:4,-1,80:3,-1:2,80:4,-1:3,80,-1,80,-1:2,80:" +
"3,-1,80:2,-1:2,80:6,59,80,-1:2,80:4,-1:8,80:14,-1,80:3,-1:2,80:4,-1:18,166," +
"-1,166,-1:5,166,-1:2,166,-1:8,166,-1,166,-1:10,166,-1:2,166,-1:4,166:2,-1:4" +
",80,-1,80,-1:2,80:3,-1,80:2,-1:2,80:8,-1:2,80:4,-1:8,80:2,60,80:11,-1,80:3," +
"-1:2,80:4,-1:3,80,-1,80,-1:2,80:3,-1,80:2,-1:2,80:8,-1:2,80:4,-1:8,80:3,61," +
"80:10,-1,80:3,-1:2,80:4,-1:3,80,-1,80,-1:2,80:3,-1,80:2,-1:2,80:8,-1:2,80:4" +
",-1:8,62,80:13,-1,80:3,-1:2,80:4,-1:3,80,-1,80,-1:2,80:3,-1,80:2,-1:2,80:8," +
"-1:2,80:4,-1:8,80:8,63,80:5,-1,80:3,-1:2,80:4,-1:3,80,-1,80,-1:2,80:3,-1,80" +
":2,-1:2,80:7,64,-1:2,80:4,-1:8,80:14,-1,80:3,-1:2,80:4,-1:3,80,-1,80,-1:2,8" +
"0:3,-1,80:2,-1:2,80:8,-1:2,80:4,-1:8,65,80:13,-1,80:3,-1:2,80:4,-1:3,80,-1," +
"80,-1:2,80:3,-1,80:2,-1:2,80:7,68,-1:2,80:4,-1:8,80:14,-1,80:3,-1:2,80:4,-1" +
":20,138,-1:44,80,-1,80,-1:2,80:3,-1,80:2,-1:2,80:8,-1:2,80:4,-1:8,80:9,70,8" +
"0:4,-1,80:3,-1:2,80:4,-1:3,80,-1,80,-1:2,80:3,-1,80:2,-1:2,80:6,71,80,-1:2," +
"80:4,-1:8,80:14,-1,80:3,-1:2,80:4,-1,136:2,-1,136,140,136:56,-1:3,80,-1,80," +
"-1:2,80:3,-1,80:2,-1:2,80:7,72,-1:2,80:4,-1:8,80:14,-1,80:3,-1:2,80:4,-1:3," +
"80,-1,80,-1:2,80:3,-1,80:2,-1:2,80:3,73,80:4,-1:2,80:4,-1:8,80:14,-1,80:3,-" +
"1:2,80:4,-1:3,84,-1:76,69,-1,69,-1:5,69,-1:2,69,-1:8,69,-1,69,-1:10,69,-1:2" +
",69,-1:4,69:2,-1,1,-1:17,76:6,-1:2,76:4,-1:8,76:9,-1,76:4,77,76:2,143,-1:2," +
"76:3,-1:20,76,-1:2,76,-1:6,76,-1:25,76,-1:6,1,-1:64,80,-1,80,-1:2,80:3,-1,8" +
"0:2,-1:2,80:8,-1:2,80:4,-1:8,95,80:13,-1,80:3,-1:2,80:4,-1:12,126,-1:67,141" +
",-1,141,-1:5,141,-1:2,141,-1:8,141,-1,141,-1:10,141,-1:2,141,-1:4,141:2,-1:" +
"4,80,-1,80,-1:2,80:3,-1,80:2,-1:2,80:2,97,80,99,80:3,-1:2,80:4,-1:8,80:14,-" +
"1,80:3,-1:2,80:4,-1:3,80,-1,80,-1:2,80:3,-1,80:2,-1:2,80:2,158,80:5,-1:2,80" +
":4,-1:8,101,80:3,159,80:9,-1,80:3,-1:2,80:4,-1:3,80,-1,80,-1:2,80:3,-1,80:2" +
",-1:2,80:7,105,-1:2,80:4,-1:8,80:14,-1,80:3,-1:2,80:4,-1:3,80,-1,80,-1:2,80" +
":3,-1,80:2,-1:2,80:2,107,80:5,-1:2,80:4,-1:8,80:14,-1,80:3,-1:2,80:4,-1:3,8" +
"0,-1,80,-1:2,80:3,-1,80:2,-1:2,80:8,-1:2,80:4,-1:8,109,80:13,-1,80:3,-1:2,8" +
"0:4,-1:3,80,-1,80,-1:2,80:3,-1,80:2,-1:2,80:8,-1:2,80:2,111,80,-1:8,80:14,-" +
"1,80:3,-1:2,80:4,-1:3,80,-1,80,-1:2,80:3,-1,80:2,-1:2,80:8,-1:2,80:4,-1:8,8" +
"0:6,113,80:7,-1,80:3,-1:2,80:4,-1:3,80,-1,80,-1:2,80:3,-1,80:2,-1:2,80:8,-1" +
":2,80:4,-1:8,80:11,115,173,80,-1,80:3,-1:2,80:4,-1:3,80,-1,80,-1:2,80:3,-1," +
"80:2,-1:2,80:8,-1:2,80:4,-1:8,80:4,117,80:9,-1,80:3,-1:2,80:4,-1:3,80,-1,80" +
",-1:2,80:3,-1,80:2,-1:2,80:5,119,80:2,-1:2,80:4,-1:8,80:4,161,80:9,-1,80:3," +
"-1:2,80:4,-1:3,80,-1,80,-1:2,80:3,-1,80:2,-1:2,80:8,-1:2,80:4,-1:8,80:7,125" +
",80:6,-1,80:3,-1:2,80:4,-1:3,80,-1,80,-1:2,80:3,-1,80:2,-1:2,80:8,-1:2,80:4" +
",-1:8,80:6,127,80:7,-1,80:3,-1:2,80:4,-1:3,80,-1,80,-1:2,80:3,-1,80:2,-1:2," +
"80:8,-1:2,80:4,-1:8,80:7,129,80:6,-1,80:3,-1:2,80:4,-1:3,80,-1,80,-1:2,80:3" +
",-1,80:2,-1:2,80:3,131,80:4,-1:2,80:4,-1:8,80:14,-1,80:3,-1:2,80:4,-1:3,80," +
"-1,80,-1:2,80:3,-1,80:2,-1:2,80:8,-1:2,80:4,-1:8,80:3,133,80:10,-1,80:3,-1:" +
"2,80:4,-1:3,80,-1,80,-1:2,80:3,-1,80:2,-1:2,80:7,135,-1:2,80:4,-1:8,80:14,-" +
"1,80:3,-1:2,80:4,-1:3,80,-1,80,-1:2,80:3,-1,80:2,-1:2,80:3,137,80:4,-1:2,80" +
":4,-1:8,80:14,-1,80:3,-1:2,80:4,-1:3,80,-1,80,-1:2,80:3,-1,80:2,-1:2,80:5,1" +
"50,80:2,-1:2,80:4,-1:8,80:14,-1,80:3,-1:2,80:4,-1:18,147,-1,147,-1:5,147,-1" +
":2,147,-1:8,147,-1,147,-1:10,147,-1:2,147,-1:4,147:2,-1:4,80,-1,80,-1:2,80:" +
"3,-1,80:2,-1:2,80:8,-1:2,80:4,-1:8,151,80:13,-1,80:3,-1:2,80:4,-1:3,80,-1,8" +
"0,-1:2,80:3,-1,80:2,-1:2,80:8,-1:2,80:4,-1:8,80:5,152,80:8,-1,80:3,-1:2,80:" +
"4,-1:3,80,-1,80,-1:2,80:3,-1,80:2,-1:2,80:8,-1:2,80,153,80:2,-1:8,80:14,-1," +
"80:3,-1:2,80:4,-1:3,80,-1,80,-1:2,80:3,-1,80:2,-1:2,80:8,-1:2,80:4,-1:8,80:" +
"3,154,80:6,155,80:3,-1,80:3,-1:2,80:4,-1:3,80,-1,80,-1:2,80:3,-1,80:2,-1:2," +
"80:2,156,80:3,157,80,-1:2,80:4,-1:8,80:14,-1,80:3,-1:2,80:4,-1:3,80,-1,80,-" +
"1:2,80:3,-1,80:2,-1:2,80:8,-1:2,80:4,-1:8,80:4,160,80:7,175,80,-1,80:3,-1:2" +
",80:4,-1:3,80,-1,80,-1:2,80:3,-1,80:2,-1:2,80:2,162,80:5,-1:2,80:4,-1:8,80:" +
"14,-1,80:3,-1:2,80:4,-1:3,80,-1,80,-1:2,80:3,-1,80:2,-1:2,80:8,-1:2,80:4,-1" +
":8,80:6,163,80:7,-1,80:3,-1:2,80:4,-1:3,80,-1,80,-1:2,80:3,-1,80:2,-1:2,80:" +
"8,-1:2,80:4,-1:8,80:13,174,-1,80:3,-1:2,80:4");

	public java_cup.runtime.Symbol next_token ()
		throws java.io.IOException {
		int yy_lookahead;
		int yy_anchor = YY_NO_ANCHOR;
		int yy_state = yy_state_dtrans[yy_lexical_state];
		int yy_next_state = YY_NO_STATE;
		int yy_last_accept_state = YY_NO_STATE;
		boolean yy_initial = true;
		int yy_this_accept;

		yy_mark_start();
		yy_this_accept = yy_acpt[yy_state];
		if (YY_NOT_ACCEPT != yy_this_accept) {
			yy_last_accept_state = yy_state;
			yy_mark_end();
		}
		while (true) {
			if (yy_initial && yy_at_bol) yy_lookahead = YY_BOL;
			else yy_lookahead = yy_advance();
			yy_next_state = YY_F;
			yy_next_state = yy_nxt[yy_rmap[yy_state]][yy_cmap[yy_lookahead]];
			if (YY_EOF == yy_lookahead && true == yy_initial) {

	return new Symbol(sym.EOF);
			}
			if (YY_F != yy_next_state) {
				yy_state = yy_next_state;
				yy_initial = false;
				yy_this_accept = yy_acpt[yy_state];
				if (YY_NOT_ACCEPT != yy_this_accept) {
					yy_last_accept_state = yy_state;
					yy_mark_end();
				}
			}
			else {
				if (YY_NO_STATE == yy_last_accept_state) {
					throw (new Error("Lexical Error: Unmatched Input."));
				}
				else {
					yy_anchor = yy_acpt[yy_last_accept_state];
					if (0 != (YY_END & yy_anchor)) {
						yy_move_end();
					}
					yy_to_mark();
					switch (yy_last_accept_state) {
					case 0:
						{ }
					case -2:
						break;
					case 1:
						
					case -3:
						break;
					case 2:
						{ yychar = 0; }
					case -4:
						break;
					case 3:
						{ return new Symbol( sym.VARIABLE, yytext() ); }
					case -5:
						break;
					case 4:
						{ return new Symbol(sym.BOR); }
					case -6:
						break;
					case 5:
						{ return new Symbol(sym.COLON); }
					case -7:
						break;
					case 7:
						{ return new Symbol(sym.SEMI); }
					case -8:
						break;
					case 8:
						{ return new Symbol(sym.COMMA); }
					case -9:
						break;
					case 9:
						{ return new Symbol(sym.LPAREN); }
					case -10:
						break;
					case 10:
						{ return new Symbol(sym.RPAREN); }
					case -11:
						break;
					case 11:
						{ return new Symbol(sym.LBRACE); }
					case -12:
						break;
					case 12:
						{ return new Symbol(sym.RBRACE); }
					case -13:
						break;
					case 13:
						{ return new Symbol(sym.LBRACKET); }
					case -14:
						break;
					case 14:
						{ return new Symbol(sym.RBRACKET); }
					case -15:
						break;
					case 15:
						{ yybegin(YYCHAR); }
					case -16:
						break;
					case 16:
						{ return new Symbol( sym.INT, new Integer( yytext() ) ); }
					case -17:
						break;
					case 17:
						{ yybegin(YYSTRING); }
					case -18:
						break;
					case 18:
						{ yychar = 0; }
					case -19:
						break;
					case 19:
						{ }
					case -20:
						break;
					case 20:
						{ return new Symbol(sym.VECRIGHT); }
					case -21:
						break;
					case 21:
						{ return new Symbol(sym.BNOT); }
					case -22:
						break;
					case 22:
						{ return new Symbol(sym.TRUE); }
					case -23:
						break;
					case 23:
						{ return new Symbol(sym.FALSE); }
					case -24:
						break;
					case 24:
						{ return new Symbol(sym.NIL); }
					case -25:
						break;
					case 25:
						{ return new Symbol(sym.VECLEFT); }
					case -26:
						break;
					case 26:
						{ return new Symbol(sym.IF); }
					case -27:
						break;
					case 27:
						{ return new Symbol(sym.BE); }
					case -28:
						break;
					case 28:
						{ return new Symbol( sym.DOUBLE, new Double( yytext() ) ); }
					case -29:
						break;
					case 29:
						{ return new Symbol( sym.STRING, "" ); }
					case -30:
						break;
					case 30:
						{ return new Symbol( sym.DOUBLE, new Double( yytext() ) ); }
					case -31:
						break;
					case 31:
						{ return new Symbol(sym.DIV); }
					case -32:
						break;
					case 32:
						{ return new Symbol(sym.MUL); }
					case -33:
						break;
					case 33:
						{ return new Symbol(sym.PLUS); }
					case -34:
						break;
					case 34:
						{ return new Symbol(sym.MINUS); }
					case -35:
						break;
					case 35:
						{ return new Symbol(sym.MOD); }
					case -36:
						break;
					case 36:
						{ return new Symbol(sym.EQUAL); }
					case -37:
						break;
					case 37:
						{ return new Symbol(sym.BAND); }
					case -38:
						break;
					case 38:
						{ return new Symbol(sym.GTHAN); }
					case -39:
						break;
					case 39:
						{ return new Symbol(sym.LTHAN); }
					case -40:
						break;
					case 40:
						{ return new Symbol(sym.CONCAT); }
					case -41:
						break;
					case 41:
						{ return new Symbol(sym.DEF); }
					case -42:
						break;
					case 42:
						{ return new Symbol( sym.BIN, Integer.parseInt( yytext().substring(2), 2 ) ); }
					case -43:
						break;
					case 43:
						{ return new Symbol( sym.HEX, Integer.parseInt( yytext().substring(2), 16 ) ); }
					case -44:
						break;
					case 44:
						{ return new Symbol(sym.CAR); }
					case -45:
						break;
					case 45:
						{ return new Symbol(sym.CDR); }
					case -46:
						break;
					case 46:
						{ return new Symbol(sym.LET); }
					case -47:
						break;
					case 47:
						{ return new Symbol(sym.ASSIGN); }
					case -48:
						break;
					case 48:
						{ return new Symbol(sym.NEQUAL); }
					case -49:
						break;
					case 49:
						{ return new Symbol(sym.GEQUAL); }
					case -50:
						break;
					case 50:
						{ return new Symbol(sym.LEQUAL); }
					case -51:
						break;
					case 51:
						{ return new Symbol(sym.LOR); }
					case -52:
						break;
					case 52:
						{ }
					case -53:
						break;
					case 53:
						{ return new Symbol(sym.LNOT); }
					case -54:
						break;
					case 54:
						{ return new Symbol(sym.READ); }
					case -55:
						break;
					case 55:
						{ return new Symbol(sym.THEN); }
					case -56:
						break;
					case 56:
						{ return new Symbol(sym.TRUE); }
					case -57:
						break;
					case 57:
						{ return new Symbol(sym.ELSE); }
					case -58:
						break;
					case 58:
						{ return new Symbol(sym.EQVQ); }
					case -59:
						break;
					case 59:
						{ return new Symbol(sym.PAIR); }
					case -60:
						break;
					case 60:
						{ return new Symbol(sym.PROC); }
					case -61:
						break;
					case 61:
						{ return new Symbol(sym.CALL); }
					case -62:
						break;
					case 62:
						{ return new Symbol(sym.CASE); }
					case -63:
						break;
					case 63:
						{ return new Symbol(sym.LAZY); }
					case -64:
						break;
					case 64:
						{ return new Symbol(sym.LIST); }
					case -65:
						break;
					case 65:
						{ return new Symbol(sym.SIZE); }
					case -66:
						break;
					case 66:
						{ return new Symbol(sym.LAND); }
					case -67:
						break;
					case 67:
						{ return new Symbol(sym.PAIRQ); }
					case -68:
						break;
					case 68:
						{ return new Symbol(sym.PRINT); }
					case -69:
						break;
					case 69:
						{ return new Symbol(sym.CHAR, new Integer( yytext().substring(2) ).toString() ); }
					case -70:
						break;
					case 70:
						{ return new Symbol(sym.EQUALQ); }
					case -71:
						break;
					case 71:
						{ return new Symbol(sym.SUBSTR); }
					case -72:
						break;
					case 72:
						{ return new Symbol(sym.READINT); }
					case -73:
						break;
					case 73:
						{ return new Symbol(sym.PRINTLN); }
					case -74:
						break;
					case 74:
						{ return new Symbol( sym.STRING, yytext() ); }
					case -75:
						break;
					case 75:
						{ yybegin(YYINITIAL); }
					case -76:
						break;
					case 76:
						{ return new Symbol( sym.CHAR, yytext() ); }
					case -77:
						break;
					case 77:
						{ yybegin(YYINITIAL); }
					case -78:
						break;
					case 78:
						{ }
					case -79:
						break;
					case 79:
						{ yychar = 0; }
					case -80:
						break;
					case 80:
						{ return new Symbol( sym.VARIABLE, yytext() ); }
					case -81:
						break;
					case 81:
						{ return new Symbol(sym.BOR); }
					case -82:
						break;
					case 83:
						{ yychar = 0; }
					case -83:
						break;
					case 84:
						{ }
					case -84:
						break;
					case 85:
						{ return new Symbol( sym.STRING, yytext() ); }
					case -85:
						break;
					case 86:
						{ }
					case -86:
						break;
					case 87:
						{ return new Symbol( sym.VARIABLE, yytext() ); }
					case -87:
						break;
					case 89:
						{ return new Symbol( sym.VARIABLE, yytext() ); }
					case -88:
						break;
					case 91:
						{ return new Symbol( sym.VARIABLE, yytext() ); }
					case -89:
						break;
					case 93:
						{ return new Symbol( sym.VARIABLE, yytext() ); }
					case -90:
						break;
					case 95:
						{ return new Symbol( sym.VARIABLE, yytext() ); }
					case -91:
						break;
					case 97:
						{ return new Symbol( sym.VARIABLE, yytext() ); }
					case -92:
						break;
					case 99:
						{ return new Symbol( sym.VARIABLE, yytext() ); }
					case -93:
						break;
					case 101:
						{ return new Symbol( sym.VARIABLE, yytext() ); }
					case -94:
						break;
					case 103:
						{ return new Symbol( sym.VARIABLE, yytext() ); }
					case -95:
						break;
					case 105:
						{ return new Symbol( sym.VARIABLE, yytext() ); }
					case -96:
						break;
					case 107:
						{ return new Symbol( sym.VARIABLE, yytext() ); }
					case -97:
						break;
					case 109:
						{ return new Symbol( sym.VARIABLE, yytext() ); }
					case -98:
						break;
					case 111:
						{ return new Symbol( sym.VARIABLE, yytext() ); }
					case -99:
						break;
					case 113:
						{ return new Symbol( sym.VARIABLE, yytext() ); }
					case -100:
						break;
					case 115:
						{ return new Symbol( sym.VARIABLE, yytext() ); }
					case -101:
						break;
					case 117:
						{ return new Symbol( sym.VARIABLE, yytext() ); }
					case -102:
						break;
					case 119:
						{ return new Symbol( sym.VARIABLE, yytext() ); }
					case -103:
						break;
					case 121:
						{ return new Symbol( sym.VARIABLE, yytext() ); }
					case -104:
						break;
					case 123:
						{ return new Symbol( sym.VARIABLE, yytext() ); }
					case -105:
						break;
					case 125:
						{ return new Symbol( sym.VARIABLE, yytext() ); }
					case -106:
						break;
					case 127:
						{ return new Symbol( sym.VARIABLE, yytext() ); }
					case -107:
						break;
					case 129:
						{ return new Symbol( sym.VARIABLE, yytext() ); }
					case -108:
						break;
					case 131:
						{ return new Symbol( sym.VARIABLE, yytext() ); }
					case -109:
						break;
					case 133:
						{ return new Symbol( sym.VARIABLE, yytext() ); }
					case -110:
						break;
					case 135:
						{ return new Symbol( sym.VARIABLE, yytext() ); }
					case -111:
						break;
					case 137:
						{ return new Symbol( sym.VARIABLE, yytext() ); }
					case -112:
						break;
					case 139:
						{ return new Symbol( sym.VARIABLE, yytext() ); }
					case -113:
						break;
					case 145:
						{ return new Symbol( sym.VARIABLE, yytext() ); }
					case -114:
						break;
					case 148:
						{ return new Symbol( sym.VARIABLE, yytext() ); }
					case -115:
						break;
					case 149:
						{ return new Symbol( sym.VARIABLE, yytext() ); }
					case -116:
						break;
					case 150:
						{ return new Symbol( sym.VARIABLE, yytext() ); }
					case -117:
						break;
					case 151:
						{ return new Symbol( sym.VARIABLE, yytext() ); }
					case -118:
						break;
					case 152:
						{ return new Symbol( sym.VARIABLE, yytext() ); }
					case -119:
						break;
					case 153:
						{ return new Symbol( sym.VARIABLE, yytext() ); }
					case -120:
						break;
					case 154:
						{ return new Symbol( sym.VARIABLE, yytext() ); }
					case -121:
						break;
					case 155:
						{ return new Symbol( sym.VARIABLE, yytext() ); }
					case -122:
						break;
					case 156:
						{ return new Symbol( sym.VARIABLE, yytext() ); }
					case -123:
						break;
					case 157:
						{ return new Symbol( sym.VARIABLE, yytext() ); }
					case -124:
						break;
					case 158:
						{ return new Symbol( sym.VARIABLE, yytext() ); }
					case -125:
						break;
					case 159:
						{ return new Symbol( sym.VARIABLE, yytext() ); }
					case -126:
						break;
					case 160:
						{ return new Symbol( sym.VARIABLE, yytext() ); }
					case -127:
						break;
					case 161:
						{ return new Symbol( sym.VARIABLE, yytext() ); }
					case -128:
						break;
					case 162:
						{ return new Symbol( sym.VARIABLE, yytext() ); }
					case -129:
						break;
					case 163:
						{ return new Symbol( sym.VARIABLE, yytext() ); }
					case -130:
						break;
					case 164:
						{ return new Symbol( sym.VARIABLE, yytext() ); }
					case -131:
						break;
					case 165:
						{ return new Symbol( sym.VARIABLE, yytext() ); }
					case -132:
						break;
					case 167:
						{ return new Symbol( sym.VARIABLE, yytext() ); }
					case -133:
						break;
					case 168:
						{ return new Symbol( sym.VARIABLE, yytext() ); }
					case -134:
						break;
					case 169:
						{ return new Symbol( sym.VARIABLE, yytext() ); }
					case -135:
						break;
					case 170:
						{ return new Symbol( sym.VARIABLE, yytext() ); }
					case -136:
						break;
					case 171:
						{ return new Symbol( sym.VARIABLE, yytext() ); }
					case -137:
						break;
					case 172:
						{ return new Symbol( sym.VARIABLE, yytext() ); }
					case -138:
						break;
					case 173:
						{ return new Symbol( sym.VARIABLE, yytext() ); }
					case -139:
						break;
					case 174:
						{ return new Symbol( sym.VARIABLE, yytext() ); }
					case -140:
						break;
					case 175:
						{ return new Symbol( sym.VARIABLE, yytext() ); }
					case -141:
						break;
					default:
						yy_error(YY_E_INTERNAL,false);
					case -1:
					}
					yy_initial = true;
					yy_state = yy_state_dtrans[yy_lexical_state];
					yy_next_state = YY_NO_STATE;
					yy_last_accept_state = YY_NO_STATE;
					yy_mark_start();
					yy_this_accept = yy_acpt[yy_state];
					if (YY_NOT_ACCEPT != yy_this_accept) {
						yy_last_accept_state = yy_state;
						yy_mark_end();
					}
				}
			}
		}
	}
}
