package smpl.lang;
import smpl.sys.*;
import java_cup.runtime.*;
import java.io.*;


public class SmplLexer implements java_cup.runtime.Scanner {
	private final int YY_BUFFER_SIZE = 512;
	private final int YY_F = -1;
	private final int YY_NO_STATE = -1;
	private final int YY_NOT_ACCEPT = 0;
	private final int YY_START = 1;
	private final int YY_END = 2;
	private final int YY_NO_ANCHOR = 4;
	private final int YY_BOL = 128;
	private final int YY_EOF = 129;

    int comNestLvl;
    public int getChar()
    {
	return yychar + 1;
    }
    public int getLine()
    {
	return yyline + 1;
    }
    public String getText()
    {
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

	public SmplLexer (java.io.Reader reader) {
		this ();
		if (null == reader) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(reader);
	}

	public SmplLexer (java.io.InputStream instream) {
		this ();
		if (null == instream) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(new java.io.InputStreamReader(instream));
	}

	private SmplLexer () {
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
	private final int STRING = 2;
	private final int YYINITIAL = 0;
	private final int COMMENT = 1;
	private final int yy_state_dtrans[] = {
		0,
		139,
		98
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
		/* 0 */ YY_NOT_ACCEPT,
		/* 1 */ YY_NO_ANCHOR,
		/* 2 */ YY_NO_ANCHOR,
		/* 3 */ YY_NO_ANCHOR,
		/* 4 */ YY_NO_ANCHOR,
		/* 5 */ YY_NO_ANCHOR,
		/* 6 */ YY_NO_ANCHOR,
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
		/* 82 */ YY_NO_ANCHOR,
		/* 83 */ YY_NO_ANCHOR,
		/* 84 */ YY_NO_ANCHOR,
		/* 85 */ YY_NO_ANCHOR,
		/* 86 */ YY_NO_ANCHOR,
		/* 87 */ YY_NO_ANCHOR,
		/* 88 */ YY_NO_ANCHOR,
		/* 89 */ YY_NO_ANCHOR,
		/* 90 */ YY_NO_ANCHOR,
		/* 91 */ YY_NO_ANCHOR,
		/* 92 */ YY_NO_ANCHOR,
		/* 93 */ YY_NO_ANCHOR,
		/* 94 */ YY_NO_ANCHOR,
		/* 95 */ YY_NO_ANCHOR,
		/* 96 */ YY_NO_ANCHOR,
		/* 97 */ YY_NO_ANCHOR,
		/* 98 */ YY_NO_ANCHOR,
		/* 99 */ YY_NO_ANCHOR,
		/* 100 */ YY_NO_ANCHOR,
		/* 101 */ YY_NO_ANCHOR,
		/* 102 */ YY_NO_ANCHOR,
		/* 103 */ YY_NO_ANCHOR,
		/* 104 */ YY_NOT_ACCEPT,
		/* 105 */ YY_NO_ANCHOR,
		/* 106 */ YY_NO_ANCHOR,
		/* 107 */ YY_NO_ANCHOR,
		/* 108 */ YY_NO_ANCHOR,
		/* 109 */ YY_NO_ANCHOR,
		/* 110 */ YY_NO_ANCHOR,
		/* 111 */ YY_NO_ANCHOR,
		/* 112 */ YY_NO_ANCHOR,
		/* 113 */ YY_NO_ANCHOR,
		/* 114 */ YY_NOT_ACCEPT,
		/* 115 */ YY_NO_ANCHOR,
		/* 116 */ YY_NO_ANCHOR,
		/* 117 */ YY_NO_ANCHOR,
		/* 118 */ YY_NO_ANCHOR,
		/* 119 */ YY_NOT_ACCEPT,
		/* 120 */ YY_NO_ANCHOR,
		/* 121 */ YY_NO_ANCHOR,
		/* 122 */ YY_NO_ANCHOR,
		/* 123 */ YY_NOT_ACCEPT,
		/* 124 */ YY_NO_ANCHOR,
		/* 125 */ YY_NO_ANCHOR,
		/* 126 */ YY_NOT_ACCEPT,
		/* 127 */ YY_NO_ANCHOR,
		/* 128 */ YY_NO_ANCHOR,
		/* 129 */ YY_NOT_ACCEPT,
		/* 130 */ YY_NO_ANCHOR,
		/* 131 */ YY_NO_ANCHOR,
		/* 132 */ YY_NOT_ACCEPT,
		/* 133 */ YY_NO_ANCHOR,
		/* 134 */ YY_NO_ANCHOR,
		/* 135 */ YY_NOT_ACCEPT,
		/* 136 */ YY_NO_ANCHOR,
		/* 137 */ YY_NOT_ACCEPT,
		/* 138 */ YY_NO_ANCHOR,
		/* 139 */ YY_NOT_ACCEPT,
		/* 140 */ YY_NO_ANCHOR,
		/* 141 */ YY_NO_ANCHOR,
		/* 142 */ YY_NO_ANCHOR,
		/* 143 */ YY_NO_ANCHOR,
		/* 144 */ YY_NO_ANCHOR,
		/* 145 */ YY_NO_ANCHOR,
		/* 146 */ YY_NO_ANCHOR,
		/* 147 */ YY_NO_ANCHOR,
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
		/* 166 */ YY_NO_ANCHOR,
		/* 167 */ YY_NO_ANCHOR,
		/* 168 */ YY_NO_ANCHOR,
		/* 169 */ YY_NO_ANCHOR,
		/* 170 */ YY_NO_ANCHOR,
		/* 171 */ YY_NO_ANCHOR,
		/* 172 */ YY_NO_ANCHOR,
		/* 173 */ YY_NO_ANCHOR,
		/* 174 */ YY_NO_ANCHOR,
		/* 175 */ YY_NO_ANCHOR,
		/* 176 */ YY_NO_ANCHOR,
		/* 177 */ YY_NO_ANCHOR,
		/* 178 */ YY_NO_ANCHOR,
		/* 179 */ YY_NO_ANCHOR,
		/* 180 */ YY_NO_ANCHOR,
		/* 181 */ YY_NO_ANCHOR,
		/* 182 */ YY_NO_ANCHOR,
		/* 183 */ YY_NO_ANCHOR,
		/* 184 */ YY_NO_ANCHOR,
		/* 185 */ YY_NO_ANCHOR,
		/* 186 */ YY_NO_ANCHOR,
		/* 187 */ YY_NO_ANCHOR,
		/* 188 */ YY_NO_ANCHOR,
		/* 189 */ YY_NO_ANCHOR,
		/* 190 */ YY_NO_ANCHOR,
		/* 191 */ YY_NO_ANCHOR,
		/* 192 */ YY_NO_ANCHOR,
		/* 193 */ YY_NO_ANCHOR,
		/* 194 */ YY_NO_ANCHOR,
		/* 195 */ YY_NO_ANCHOR,
		/* 196 */ YY_NO_ANCHOR,
		/* 197 */ YY_NO_ANCHOR,
		/* 198 */ YY_NO_ANCHOR,
		/* 199 */ YY_NO_ANCHOR,
		/* 200 */ YY_NO_ANCHOR,
		/* 201 */ YY_NO_ANCHOR,
		/* 202 */ YY_NO_ANCHOR,
		/* 203 */ YY_NO_ANCHOR,
		/* 204 */ YY_NO_ANCHOR,
		/* 205 */ YY_NO_ANCHOR,
		/* 206 */ YY_NO_ANCHOR,
		/* 207 */ YY_NO_ANCHOR,
		/* 208 */ YY_NO_ANCHOR,
		/* 209 */ YY_NO_ANCHOR,
		/* 210 */ YY_NO_ANCHOR,
		/* 211 */ YY_NO_ANCHOR,
		/* 212 */ YY_NO_ANCHOR,
		/* 213 */ YY_NO_ANCHOR,
		/* 214 */ YY_NO_ANCHOR,
		/* 215 */ YY_NO_ANCHOR,
		/* 216 */ YY_NO_ANCHOR,
		/* 217 */ YY_NO_ANCHOR,
		/* 218 */ YY_NO_ANCHOR,
		/* 219 */ YY_NO_ANCHOR
	};
	private int yy_cmap[] = unpackFromString(1,130,
"4:8,2:2,1,4,2,1,4:18,2,33,15,8,57,26,28,59,43,44,5,25,61,6,13,3,12:2,7:8,41" +
",60,36,37,38,48,39,10:6,58:20,40,14,42,27,58,4,29,11,21,30,24,23,58,53,47,5" +
"8,55,20,56,16,32,19,49,22,18,17,51,50,54,9,52,34,45,31,46,35,4,0:2")[0];

	private int yy_rmap[] = unpackFromString(1,220,
"0,1:3,2,1,3,4,5,6,7,8,9:3,10,11,12,6,1,13,1,14,9,15,16,1:7,17,1:5,6,7,18,9," +
"1:7,6,19,20,1:2,6:7,1:5,21,6:3,22,6:4,23,6:4,24,6:13,1:3,25,26,1:4,7,27,4,2" +
"8,6:3,29,30,31,9:2,32,33,34,19,35,36,37,20,38,39,40,41,42,43,44,45,46,47,48" +
",21,49,48,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,70,71" +
",72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,90,91,92,93,94,95,96" +
",97,98,99,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,1" +
"16,117,118,119,120,121,122,123,124,125,126,127,128,129,130,131")[0];

	private int yy_nxt[][] = unpackFromString(132,62,
"1,2,3,4,5,6,7,8,105,9:2,107,8,10,5,11,170,171,199,116,172,173,200,174,201,1" +
"2,13,14,15,175,176,16,121,17,18,19,20,21,22,23,24,25,26,27,28,29,30,124,115" +
",9:5,213,9,219,115,9,120,31,32,-1:65,33,-1,34,-1:59,35,-1:65,106,-1:4,106,-" +
"1:56,8,-1,9:3,8,104,-1:2,9:9,114:4,9:2,-1,9,114,9,-1:4,114,-1:7,9,114,9:8,1" +
"14,9,-1:10,9,-1,9:4,-1:3,9:15,-1,9:3,-1:4,9,-1:7,9:12,-1:10,40,-1:4,40,-1:5" +
"0,129:14,-1,129:46,-1:7,114,-1,9:3,114,-1:3,9:9,114:4,9:2,-1,9,114,9,-1:4,1" +
"14,-1:7,9,114,9:8,114,9,-1:10,114,-1,9:3,114,-1:3,9:9,114:3,42,9:2,-1,9,114" +
",9,-1:4,114,-1:7,9,114,9:8,114,9,-1:34,43,-1:37,114,-1,9:3,114,-1:3,9:9,114" +
":4,9:2,-1,9,114,9,-1:2,44,-1,114,-1:7,9,114,9:8,114,9,-1:40,45,-1:61,46,-1:" +
"65,47,-1:57,48,-1:4,49,-1:21,33:60,-1:7,9,-1,9:4,-1:3,9:15,-1,146,9:2,-1:4," +
"9,-1:7,187,9:11,-1:10,51,-1:2,51:3,-1:8,51,-1,51:2,-1:4,51:2,-1:43,52,-1:56" +
",137,-1:2,137:3,-1:8,137,-1,137:2,-1:4,137:2,-1:38,9,-1,9:4,-1:3,9:15,-1,9:" +
"3,-1:4,9,-1:7,9,82,9:10,-1:10,9,-1,9:4,-1:3,9:15,-1,9:3,-1:4,9,-1:7,198,9:1" +
"1,-1:10,9,-1,9:4,-1:3,9:4,168,9:10,-1,9:3,-1:4,9,-1:7,9:12,-1:3,1,-1,113:12" +
",99,100,113:46,-1:15,101,102,103,-1:53,119,-1,123,-1:2,126,-1:2,36,-1:5,37," +
"38,-1:44,9,-1,9:4,-1:3,9:6,202,9,39,9:6,-1,9:3,-1:4,9,-1:7,9:12,-1:10,135,-" +
"1:2,135:3,-1:8,135,-1,135:2,-1:4,135:2,-1:36,96,-1:58,113:12,-1:2,113:46,-1" +
":7,9,-1,9:4,-1:3,9:6,41,9:6,179,9,-1,9:3,-1:4,9,-1:7,9:12,-1:23,63,-1:44,97" +
",-1:67,132:3,-1:4,132:9,-1:4,132:2,-1,132,-1,132,-1:12,132,-1,132:8,-1,132," +
"-1:10,9,-1,9:4,-1:3,9:6,110,9:8,-1,9:3,-1:4,9,-1:7,9:12,-1:14,64,-1:57,9,-1" +
",9:4,-1:3,9:7,50,9:7,-1,9:3,-1:4,9,-1:7,9:12,-1:22,65,-1:45,53:4,111,53:2,1" +
"11:3,53:3,117,122,125,53:2,128,53,131,111,53:4,111:2,53:31,-1:7,9,-1,9:4,-1" +
":3,9:15,-1,9:3,-1:4,9,-1:7,9:7,55,9:4,-1:10,135,-1:2,135:3,-1:8,135,66,135:" +
"2,-1:4,135:2,-1:32,129:14,54,129:46,-1:7,9,-1,9:4,-1:3,9,108,9:13,-1,9:3,-1" +
":4,9,-1:7,9:12,-1:10,135,-1:2,135:3,-1:8,135,-1,67,135,-1:4,135:2,-1:90,62," +
"-1:9,9,-1,9:4,-1:3,9:15,-1,9:3,-1:4,9,-1:7,9:5,56,9:6,-1:10,134,-1:2,134:3," +
"-1:8,134,-1,134:2,-1:4,134:2,-1:38,9,-1,9:4,-1:3,9,57,9:13,-1,9:3,-1:4,9,-1" +
":7,9:12,-1:10,9,-1,9:4,-1:3,9,189,150,9,151,9,58,9:8,-1,9:3,-1:4,9,-1:7,9:1" +
"2,-1:3,1,95:2,112,95,118,95:56,-1:7,9,-1,9:4,-1:3,9:6,59,9:8,-1,9:3,-1:4,9," +
"-1:7,9:12,-1:10,9,-1,9:4,-1:3,9:6,60,9:8,-1,9:3,-1:4,9,-1:7,9:12,-1:10,9,-1" +
",9:4,-1:3,9:14,109,-1,9:3,-1:4,9,-1:7,9:12,-1:10,9,-1,9:4,-1:3,9:7,61,9:7,-" +
"1,9:3,-1:4,9,-1:7,9:12,-1:10,9,-1,9:4,-1:3,68,9:14,-1,9:3,-1:4,9,-1:7,9:12," +
"-1:10,9,-1,9:4,-1:3,9:8,69,9:6,-1,9:3,-1:4,9,-1:7,9:12,-1:10,9,-1,9:4,-1:3," +
"9:5,70,9:9,-1,9:3,-1:4,9,-1:7,9:12,-1:10,9,-1,9:4,-1:3,9:6,71,9:8,-1,9:3,-1" +
":4,9,-1:7,9:12,-1:10,9,-1,9:4,-1:3,9:15,-1,9:3,-1:4,9,-1:7,9:5,72,9:6,-1:10" +
",9,-1,9:4,-1:3,9,73,9:13,-1,9:3,-1:4,9,-1:7,9:12,-1:10,9,-1,9:4,-1:3,9:8,74" +
",9:6,-1,9:3,-1:4,9,-1:7,9:12,-1:10,9,-1,9:4,-1:3,9:4,75,9:10,-1,9:3,-1:4,9," +
"-1:7,9:12,-1:10,9,-1,9:4,-1:3,9:14,76,-1,9:3,-1:4,9,-1:7,9:12,-1:10,9,-1,9:" +
"4,-1:3,9:8,77,9:6,-1,9:3,-1:4,9,-1:7,9:12,-1:10,9,-1,9:4,-1:3,9:15,-1,9:3,-" +
"1:4,9,-1:7,9,78,9:10,-1:10,9,-1,9:4,-1:3,9:15,-1,9:3,-1:4,9,-1:7,9:8,79,9:3" +
",-1:10,9,-1,9:4,-1:3,9,80,9:13,-1,9:3,-1:4,9,-1:7,9:12,-1:10,9,-1,9:4,-1:3," +
"9,81,9:13,-1,9:3,-1:4,9,-1:7,9:12,-1:10,9,-1,9:4,-1:3,9:2,83,9:12,-1,9:3,-1" +
":4,9,-1:7,9:12,-1:10,9,-1,9:4,-1:3,9:15,-1,9:3,-1:4,9,-1:7,9:6,84,9:5,-1:10" +
",9,-1,9:4,-1:3,9:8,85,9:6,-1,9:3,-1:4,9,-1:7,9:12,-1:10,9,-1,9:4,-1:3,9:14," +
"86,-1,9:3,-1:4,9,-1:7,9:12,-1:10,9,-1,9:4,-1:3,9:6,87,9:8,-1,9:3,-1:4,9,-1:" +
"7,9:12,-1:10,9,-1,9:4,-1:3,9:14,88,-1,9:3,-1:4,9,-1:7,9:12,-1:10,9,-1,9:4,-" +
"1:3,9:6,89,9:8,-1,9:3,-1:4,9,-1:7,9:12,-1:10,9,-1,9:4,-1:3,9,90,9:13,-1,9:3" +
",-1:4,9,-1:7,9:12,-1:10,9,-1,9:4,-1:3,9:15,-1,9:3,-1:4,9,-1:7,9,91,9:10,-1:" +
"10,9,-1,9:4,-1:3,9:14,92,-1,9:3,-1:4,9,-1:7,9:12,-1:10,9,-1,9:4,-1:3,93,9:1" +
"4,-1,9:3,-1:4,9,-1:7,9:12,-1:10,9,-1,9:4,-1:3,9,94,9:13,-1,9:3,-1:4,9,-1:7," +
"9:12,-1:10,9,-1,9:4,-1:3,9:8,127,9:6,-1,130,9:2,-1:4,9,-1:7,9:12,-1:10,9,-1" +
",9:4,-1:3,9:6,133,9:8,-1,9:3,-1:4,9,-1:7,9:6,177,9:5,-1:10,9,-1,9:4,-1:3,9:" +
"8,136,9:4,180,9,-1,9:3,-1:4,9,-1:7,181,9:11,-1:10,9,-1,9:4,-1:3,9:4,204,9:8" +
",138,140,-1,216,9:2,-1:4,9,-1:7,9:12,-1:10,9,-1,9:4,-1:3,9:15,-1,141,9:2,-1" +
":4,9,-1:7,9:12,-1:10,9,-1,9:4,-1:3,142,9:14,-1,9:3,-1:4,9,-1:7,9:12,-1:10,9" +
",-1,9:4,-1:3,9:8,143,9:6,-1,9:3,-1:4,9,-1:7,9:12,-1:10,9,-1,9:4,-1:3,9:6,20" +
"6,9,144,9:6,-1,9:3,-1:4,9,-1:7,9:12,-1:10,9,-1,9:4,-1:3,9:15,-1,9:2,145,-1:" +
"4,9,-1:7,9:12,-1:10,9,-1,9:4,-1:3,9:15,-1,9:3,-1:4,9,-1:7,147,9:11,-1:10,9," +
"-1,9:4,-1:3,9:15,-1,9:2,148,-1:4,9,-1:7,9:12,-1:10,9,-1,9:4,-1:3,9:2,149,9:" +
"12,-1,9:3,-1:4,9,-1:7,9:12,-1:10,9,-1,9:4,-1:3,9:13,152,9,-1,9:3,-1:4,9,-1:" +
"7,9:12,-1:10,9,-1,9:4,-1:3,9:2,153,9:12,-1,9:3,-1:4,9,-1:7,9:12,-1:10,9,-1," +
"9:4,-1:3,9:15,-1,9:3,-1:4,9,-1:7,9:3,154,211,9:7,-1:10,9,-1,9:4,-1:3,9:13,1" +
"55,9,-1,9:3,-1:4,9,-1:7,9:12,-1:10,9,-1,9:4,-1:3,9:6,156,9:8,-1,9:3,-1:4,9," +
"-1:7,9:12,-1:10,9,-1,9:4,-1:3,157,9:14,-1,9:3,-1:4,9,-1:7,9:12,-1:10,9,-1,9" +
":4,-1:3,9:2,158,9:12,-1,9:3,-1:4,9,-1:7,9:12,-1:10,9,-1,9:4,-1:3,9:5,159,9:" +
"9,-1,9:3,-1:4,9,-1:7,9:12,-1:10,9,-1,9:4,-1:3,9:4,160,9:10,-1,9:3,-1:4,9,-1" +
":7,9:12,-1:10,9,-1,9:4,-1:3,9:13,161,9,-1,9:3,-1:4,9,-1:7,9:12,-1:10,9,-1,9" +
":4,-1:3,9,162,9:13,-1,9:3,-1:4,9,-1:7,9:12,-1:10,9,-1,9:4,-1:3,9:8,163,9:6," +
"-1,9:3,-1:4,9,-1:7,9:12,-1:10,9,-1,9:4,-1:3,9,164,9:13,-1,9:3,-1:4,9,-1:7,9" +
":12,-1:10,9,-1,9:4,-1:3,9:6,165,9:8,-1,9:3,-1:4,9,-1:7,9:12,-1:10,9,-1,9:4," +
"-1:3,9:4,166,9:10,-1,9:3,-1:4,9,-1:7,9:12,-1:10,9,-1,9:4,-1:3,9:15,-1,167,9" +
":2,-1:4,9,-1:7,9:12,-1:10,9,-1,9:4,-1:3,169,9:14,-1,9:3,-1:4,9,-1:7,9:12,-1" +
":10,9,-1,9:4,-1:3,9,203,9:13,-1,9:3,-1:4,9,-1:7,178,9:3,214,9,215,9:5,-1:10" +
",9,-1,9:4,-1:3,9:8,182,9:6,-1,9:3,-1:4,9,-1:7,9:12,-1:10,9,-1,217,9:3,-1:3," +
"9:4,183,9:10,-1,9:3,-1:4,9,-1:7,9:2,184,9:9,-1:10,9,-1,9:4,-1:3,9:8,185,9:6" +
",-1,9:3,-1:4,9,-1:7,9:12,-1:10,9,-1,9:4,-1:3,9:13,186,9,-1,9:3,-1:4,9,-1:7," +
"9:12,-1:10,9,-1,9:4,-1:3,9:13,188,9,-1,9:3,-1:4,9,-1:7,9:12,-1:10,9,-1,9:4," +
"-1:3,9:15,-1,9:3,-1:4,9,-1:7,190,9:11,-1:10,9,-1,9:4,-1:3,9:8,191,9:6,-1,9:" +
"3,-1:4,9,-1:7,9:12,-1:10,9,-1,9:4,-1:3,9:2,192,9:12,-1,9:3,-1:4,9,-1:7,9:12" +
",-1:10,9,-1,9:4,-1:3,9:6,193,9:8,-1,9:3,-1:4,9,-1:7,9:12,-1:10,9,-1,9:4,-1:" +
"3,9:2,194,9:12,-1,9:3,-1:4,9,-1:7,9:12,-1:10,9,-1,9:4,-1:3,9:15,-1,195,9:2," +
"-1:4,9,-1:7,9:12,-1:10,9,-1,9:4,-1:3,9:13,196,9,-1,9:3,-1:4,9,-1:7,9:12,-1:" +
"10,9,-1,9:4,-1:3,9:15,-1,9:3,-1:4,9,-1:7,9:6,197,9:5,-1:10,9,-1,9:4,-1:3,9:" +
"15,-1,9:3,-1:4,9,-1:7,9:6,205,9:5,-1:10,9,-1,9:2,207,9,-1:3,9:15,-1,9:3,-1:" +
"4,9,-1:7,9:12,-1:10,9,-1,9:4,-1:3,9:13,208,9,-1,9:3,-1:4,9,-1:7,9:12,-1:10," +
"9,-1,9:4,-1:3,209,9:14,-1,9:3,-1:4,9,-1:7,9:12,-1:10,9,-1,9:4,-1:3,9:3,210," +
"9:11,-1,9:3,-1:4,9,-1:7,9:12,-1:10,9,-1,9:4,-1:3,9,212,9:13,-1,9:3,-1:4,9,-" +
"1:7,9:12,-1:10,9,-1,9:4,-1:3,9:8,218,9:6,-1,9:3,-1:4,9,-1:7,9:12,-1:3");

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
					case 1:
						
					case -2:
						break;
					case 2:
						{
					 yychar = 0;
					}
					case -3:
						break;
					case 3:
						{}
					case -4:
						break;
					case 4:
						{return new Symbol(sym.DIVIDE);}
					case -5:
						break;
					case 5:
						{
					  throw new java.io.IOException(":" + yyline
					    + ": Unrecognised input: " +
					    yytext());
					}
					case -6:
						break;
					case 6:
						{return new Symbol(sym.MULTIPLY);}
					case -7:
						break;
					case 7:
						{return new Symbol(sym.MINUS);}
					case -8:
						break;
					case 8:
						{return new Symbol(sym.INTEGER, new Integer (yytext()));}
					case -9:
						break;
					case 9:
						{return new Symbol (sym.ID, yytext());}
					case -10:
						break;
					case 10:
						{return new Symbol(sym.DOT);}
					case -11:
						break;
					case 11:
						{yybegin(STRING); return new Symbol(sym.OPENQUOTE);}
					case -12:
						break;
					case 12:
						{return new Symbol(sym.PLUS);}
					case -13:
						break;
					case 13:
						{return new Symbol(sym.MODULO);}
					case -14:
						break;
					case 14:
						{return new Symbol(sym.POWER);}
					case -15:
						break;
					case 15:
						{return new Symbol(sym.BAND);}
					case -16:
						break;
					case 16:
						{return new Symbol(sym.BOR);}
					case -17:
						break;
					case 17:
						{return new Symbol(sym.NOT);}
					case -18:
						break;
					case 18:
						{return new Symbol(sym.EOF);}
					case -19:
						break;
					case 19:
						{return new Symbol(sym.BNOT);}
					case -20:
						break;
					case 20:
						{return new Symbol(sym.LT);}
					case -21:
						break;
					case 21:
						{return new Symbol(sym.EQ);}
					case -22:
						break;
					case 22:
						{return new Symbol(sym.GT);}
					case -23:
						break;
					case 23:
						{return new Symbol(sym.CONCAT);}
					case -24:
						break;
					case 24:
						{return new Symbol(sym.LBRACKET);}
					case -25:
						break;
					case 25:
						{return new Symbol(sym.COLON);}
					case -26:
						break;
					case 26:
						{return new Symbol(sym.RBRACKET);}
					case -27:
						break;
					case 27:
						{return new Symbol(sym.LPAREN);}
					case -28:
						break;
					case 28:
						{return new Symbol(sym.RPAREN);}
					case -29:
						break;
					case 29:
						{return new Symbol(sym.LBRACE);}
					case -30:
						break;
					case 30:
						{return new Symbol(sym.RBRACE);}
					case -31:
						break;
					case 31:
						{return new Symbol(sym.SEMI);}
					case -32:
						break;
					case 32:
						{return new Symbol(sym.COMMA);}
					case -33:
						break;
					case 33:
						{/* ignore inline comments */}
					case -34:
						break;
					case 34:
						{
                                yybegin(COMMENT);
                                comNestLvl = 0;
                            }
					case -35:
						break;
					case 35:
						{
					  throw new java.io.IOException(":" + yyline
					    + ": '*/' Trying to terminate block comment " +
					    "that doesn't exist. ");
					}
					case -36:
						break;
					case 36:
						{return new Symbol(sym.TRUE);}
					case -37:
						break;
					case 37:
						{return new Symbol(sym.FALSE);}
					case -38:
						break;
					case 38:
						{return new Symbol(sym.NIL);}
					case -39:
						break;
					case 39:
						{return new Symbol(sym.BE);}
					case -40:
						break;
					case 40:
						{return new Symbol(sym.DOUBLE,
						new Double (yytext()));}
					case -41:
						break;
					case 41:
						{return new Symbol(sym.PAIR);}
					case -42:
						break;
					case 42:
						{return new Symbol(sym.AND);}
					case -43:
						break;
					case 43:
						{return new Symbol(sym.OR);}
					case -44:
						break;
					case 44:
						{return new Symbol(sym.NE);}
					case -45:
						break;
					case 45:
						{return new Symbol(sym.LE);}
					case -46:
						break;
					case 46:
						{return new Symbol(sym.GE);}
					case -47:
						break;
					case 47:
						{return new Symbol(sym.VLBRACKET);}
					case -48:
						break;
					case 48:
						{return new Symbol(sym.ASSIGN);}
					case -49:
						break;
					case 49:
						{return new Symbol(sym.VRBRACKET);}
					case -50:
						break;
					case 50:
						{return new Symbol(sym.IF);}
					case -51:
						break;
					case 51:
						{return new Symbol(sym.HEXINT, new Integer (Integer.parseInt(yytext().substring(2), 16)));}
					case -52:
						break;
					case 52:
						{return new Symbol(sym.BININT,
						new Integer (Integer.parseInt(yytext().substring(2), 2)));}
					case -53:
						break;
					case 53:
						{return new Symbol(sym.CHAR, yytext().substring(2));}
					case -54:
						break;
					case 54:
						{String str = yytext().substring(1,yytext().length() - 1);
                                                return new Symbol (sym.STRING, new String(str)); }
					case -55:
						break;
					case 55:
						{return new Symbol(sym.NEW);}
					case -56:
						break;
					case 56:
						{return new Symbol(sym.TRY);}
					case -57:
						break;
					case 57:
						{return new Symbol(sym.LET);}
					case -58:
						break;
					case 58:
						{return new Symbol(sym.CAR);}
					case -59:
						break;
					case 59:
						{return new Symbol(sym.CDR);}
					case -60:
						break;
					case 60:
						{return new Symbol(sym.FOR);}
					case -61:
						break;
					case 61:
						{return new Symbol(sym.DEF);}
					case -62:
						break;
					case 62:
						{return new Symbol (sym.CHAR, yytext());}
					case -63:
						break;
					case 63:
						{return new Symbol(sym.NEWLINE);}
					case -64:
						break;
					case 64:
						{return new Symbol(sym.TAB);}
					case -65:
						break;
					case 65:
						{return new Symbol(sym.SPACE);}
					case -66:
						break;
					case 66:
						{return new Symbol(sym.CRETURN);}
					case -67:
						break;
					case 67:
						{return new Symbol(sym.FORMFEED);}
					case -68:
						break;
					case 68:
						{return new Symbol(sym.THEN);}
					case -69:
						break;
					case 69:
						{return new Symbol(sym.SIZE);}
					case -70:
						break;
					case 70:
						{return new Symbol(sym.PROC);}
					case -71:
						break;
					case 71:
						{return new Symbol(sym.PAIR);}
					case -72:
						break;
					case 72:
						{return new Symbol (sym.LAZY);}
					case -73:
						break;
					case 73:
						{return new Symbol(sym.LIST);}
					case -74:
						break;
					case 74:
						{return new Symbol(sym.CASE);}
					case -75:
						break;
					case 75:
						{return new Symbol(sym.CALL);}
					case -76:
						break;
					case 76:
						{return new Symbol(sym.READ);}
					case -77:
						break;
					case 77:
						{return new Symbol(sym.ELSE);}
					case -78:
						break;
					case 78:
						{return new Symbol(sym.ISEQV);}
					case -79:
						break;
					case 79:
						{return new Symbol(sym.BREAK);}
					case -80:
						break;
					case 80:
						{return new Symbol(sym.START);}
					case -81:
						break;
					case 81:
						{return new Symbol(sym.PRINT);}
					case -82:
						break;
					case 82:
						{return new Symbol(sym.ISPAIR);}
					case -83:
						break;
					case 83:
						{return new Symbol(sym.CLASS);}
					case -84:
						break;
					case 84:
						{return new Symbol(sym.CATCH);}
					case -85:
						break;
					case 85:
						{return new Symbol(sym.WHILE);}
					case -86:
						break;
					case 86:
						{return new Symbol(sym.THREAD);}
					case -87:
						break;
					case 87:
						{return new Symbol(sym.SUBSTR);}
					case -88:
						break;
					case 88:
						{return new Symbol(sym.SHARED);}
					case -89:
						break;
					case 89:
						{return new Symbol(sym.CONSTRUCTOR);}
					case -90:
						break;
					case 90:
						{return new Symbol(sym.EXPORT);}
					case -91:
						break;
					case 91:
						{return new Symbol(sym.ISEQ);}
					case -92:
						break;
					case 92:
						{return new Symbol(sym.METHOD);}
					case -93:
						break;
					case 93:
						{return new Symbol(sym.PRINTLN);}
					case -94:
						break;
					case 94:
						{return new Symbol(sym.READINT);}
					case -95:
						break;
					case 95:
						{/* ignore everything else */}
					case -96:
						break;
					case 96:
						{comNestLvl++;}
					case -97:
						break;
					case 97:
						{
                                if (comNestLvl > 0)
                                comNestLvl--;
                                else {
                                        yybegin(YYINITIAL);
                                     }
                            }
					case -98:
						break;
					case 98:
						{return new Symbol(sym.STRING, yytext());}
					case -99:
						break;
					case 99:
						{return new Symbol(sym.STRING, "\\");}
					case -100:
						break;
					case 100:
						{yybegin(YYINITIAL); return new Symbol(sym.CLOSEQUOTE);}
					case -101:
						break;
					case 101:
						{return new Symbol(sym.STRING, "\"");}
					case -102:
						break;
					case 102:
						{return new Symbol(sym.STRING, "\n");}
					case -103:
						break;
					case 103:
						{return new Symbol(sym.STRING, "\t");}
					case -104:
						break;
					case 105:
						{
					  throw new java.io.IOException(":" + yyline
					    + ": Unrecognised input: " +
					    yytext());
					}
					case -105:
						break;
					case 106:
						{return new Symbol(sym.INTEGER, new Integer (yytext()));}
					case -106:
						break;
					case 107:
						{return new Symbol (sym.ID, yytext());}
					case -107:
						break;
					case 108:
						{return new Symbol(sym.NOT);}
					case -108:
						break;
					case 109:
						{return new Symbol(sym.AND);}
					case -109:
						break;
					case 110:
						{return new Symbol(sym.OR);}
					case -110:
						break;
					case 111:
						{return new Symbol(sym.CHAR, yytext().substring(2));}
					case -111:
						break;
					case 112:
						{/* ignore everything else */}
					case -112:
						break;
					case 113:
						{return new Symbol(sym.STRING, yytext());}
					case -113:
						break;
					case 115:
						{
					  throw new java.io.IOException(":" + yyline
					    + ": Unrecognised input: " +
					    yytext());
					}
					case -114:
						break;
					case 116:
						{return new Symbol (sym.ID, yytext());}
					case -115:
						break;
					case 117:
						{return new Symbol(sym.CHAR, yytext().substring(2));}
					case -116:
						break;
					case 118:
						{/* ignore everything else */}
					case -117:
						break;
					case 120:
						{
					  throw new java.io.IOException(":" + yyline
					    + ": Unrecognised input: " +
					    yytext());
					}
					case -118:
						break;
					case 121:
						{return new Symbol (sym.ID, yytext());}
					case -119:
						break;
					case 122:
						{return new Symbol(sym.CHAR, yytext().substring(2));}
					case -120:
						break;
					case 124:
						{return new Symbol (sym.ID, yytext());}
					case -121:
						break;
					case 125:
						{return new Symbol(sym.CHAR, yytext().substring(2));}
					case -122:
						break;
					case 127:
						{return new Symbol (sym.ID, yytext());}
					case -123:
						break;
					case 128:
						{return new Symbol(sym.CHAR, yytext().substring(2));}
					case -124:
						break;
					case 130:
						{return new Symbol (sym.ID, yytext());}
					case -125:
						break;
					case 131:
						{return new Symbol(sym.CHAR, yytext().substring(2));}
					case -126:
						break;
					case 133:
						{return new Symbol (sym.ID, yytext());}
					case -127:
						break;
					case 134:
						{return new Symbol(sym.CHAR, yytext().substring(2));}
					case -128:
						break;
					case 136:
						{return new Symbol (sym.ID, yytext());}
					case -129:
						break;
					case 138:
						{return new Symbol (sym.ID, yytext());}
					case -130:
						break;
					case 140:
						{return new Symbol (sym.ID, yytext());}
					case -131:
						break;
					case 141:
						{return new Symbol (sym.ID, yytext());}
					case -132:
						break;
					case 142:
						{return new Symbol (sym.ID, yytext());}
					case -133:
						break;
					case 143:
						{return new Symbol (sym.ID, yytext());}
					case -134:
						break;
					case 144:
						{return new Symbol (sym.ID, yytext());}
					case -135:
						break;
					case 145:
						{return new Symbol (sym.ID, yytext());}
					case -136:
						break;
					case 146:
						{return new Symbol (sym.ID, yytext());}
					case -137:
						break;
					case 147:
						{return new Symbol (sym.ID, yytext());}
					case -138:
						break;
					case 148:
						{return new Symbol (sym.ID, yytext());}
					case -139:
						break;
					case 149:
						{return new Symbol (sym.ID, yytext());}
					case -140:
						break;
					case 150:
						{return new Symbol (sym.ID, yytext());}
					case -141:
						break;
					case 151:
						{return new Symbol (sym.ID, yytext());}
					case -142:
						break;
					case 152:
						{return new Symbol (sym.ID, yytext());}
					case -143:
						break;
					case 153:
						{return new Symbol (sym.ID, yytext());}
					case -144:
						break;
					case 154:
						{return new Symbol (sym.ID, yytext());}
					case -145:
						break;
					case 155:
						{return new Symbol (sym.ID, yytext());}
					case -146:
						break;
					case 156:
						{return new Symbol (sym.ID, yytext());}
					case -147:
						break;
					case 157:
						{return new Symbol (sym.ID, yytext());}
					case -148:
						break;
					case 158:
						{return new Symbol (sym.ID, yytext());}
					case -149:
						break;
					case 159:
						{return new Symbol (sym.ID, yytext());}
					case -150:
						break;
					case 160:
						{return new Symbol (sym.ID, yytext());}
					case -151:
						break;
					case 161:
						{return new Symbol (sym.ID, yytext());}
					case -152:
						break;
					case 162:
						{return new Symbol (sym.ID, yytext());}
					case -153:
						break;
					case 163:
						{return new Symbol (sym.ID, yytext());}
					case -154:
						break;
					case 164:
						{return new Symbol (sym.ID, yytext());}
					case -155:
						break;
					case 165:
						{return new Symbol (sym.ID, yytext());}
					case -156:
						break;
					case 166:
						{return new Symbol (sym.ID, yytext());}
					case -157:
						break;
					case 167:
						{return new Symbol (sym.ID, yytext());}
					case -158:
						break;
					case 168:
						{return new Symbol (sym.ID, yytext());}
					case -159:
						break;
					case 169:
						{return new Symbol (sym.ID, yytext());}
					case -160:
						break;
					case 170:
						{return new Symbol (sym.ID, yytext());}
					case -161:
						break;
					case 171:
						{return new Symbol (sym.ID, yytext());}
					case -162:
						break;
					case 172:
						{return new Symbol (sym.ID, yytext());}
					case -163:
						break;
					case 173:
						{return new Symbol (sym.ID, yytext());}
					case -164:
						break;
					case 174:
						{return new Symbol (sym.ID, yytext());}
					case -165:
						break;
					case 175:
						{return new Symbol (sym.ID, yytext());}
					case -166:
						break;
					case 176:
						{return new Symbol (sym.ID, yytext());}
					case -167:
						break;
					case 177:
						{return new Symbol (sym.ID, yytext());}
					case -168:
						break;
					case 178:
						{return new Symbol (sym.ID, yytext());}
					case -169:
						break;
					case 179:
						{return new Symbol (sym.ID, yytext());}
					case -170:
						break;
					case 180:
						{return new Symbol (sym.ID, yytext());}
					case -171:
						break;
					case 181:
						{return new Symbol (sym.ID, yytext());}
					case -172:
						break;
					case 182:
						{return new Symbol (sym.ID, yytext());}
					case -173:
						break;
					case 183:
						{return new Symbol (sym.ID, yytext());}
					case -174:
						break;
					case 184:
						{return new Symbol (sym.ID, yytext());}
					case -175:
						break;
					case 185:
						{return new Symbol (sym.ID, yytext());}
					case -176:
						break;
					case 186:
						{return new Symbol (sym.ID, yytext());}
					case -177:
						break;
					case 187:
						{return new Symbol (sym.ID, yytext());}
					case -178:
						break;
					case 188:
						{return new Symbol (sym.ID, yytext());}
					case -179:
						break;
					case 189:
						{return new Symbol (sym.ID, yytext());}
					case -180:
						break;
					case 190:
						{return new Symbol (sym.ID, yytext());}
					case -181:
						break;
					case 191:
						{return new Symbol (sym.ID, yytext());}
					case -182:
						break;
					case 192:
						{return new Symbol (sym.ID, yytext());}
					case -183:
						break;
					case 193:
						{return new Symbol (sym.ID, yytext());}
					case -184:
						break;
					case 194:
						{return new Symbol (sym.ID, yytext());}
					case -185:
						break;
					case 195:
						{return new Symbol (sym.ID, yytext());}
					case -186:
						break;
					case 196:
						{return new Symbol (sym.ID, yytext());}
					case -187:
						break;
					case 197:
						{return new Symbol (sym.ID, yytext());}
					case -188:
						break;
					case 198:
						{return new Symbol (sym.ID, yytext());}
					case -189:
						break;
					case 199:
						{return new Symbol (sym.ID, yytext());}
					case -190:
						break;
					case 200:
						{return new Symbol (sym.ID, yytext());}
					case -191:
						break;
					case 201:
						{return new Symbol (sym.ID, yytext());}
					case -192:
						break;
					case 202:
						{return new Symbol (sym.ID, yytext());}
					case -193:
						break;
					case 203:
						{return new Symbol (sym.ID, yytext());}
					case -194:
						break;
					case 204:
						{return new Symbol (sym.ID, yytext());}
					case -195:
						break;
					case 205:
						{return new Symbol (sym.ID, yytext());}
					case -196:
						break;
					case 206:
						{return new Symbol (sym.ID, yytext());}
					case -197:
						break;
					case 207:
						{return new Symbol (sym.ID, yytext());}
					case -198:
						break;
					case 208:
						{return new Symbol (sym.ID, yytext());}
					case -199:
						break;
					case 209:
						{return new Symbol (sym.ID, yytext());}
					case -200:
						break;
					case 210:
						{return new Symbol (sym.ID, yytext());}
					case -201:
						break;
					case 211:
						{return new Symbol (sym.ID, yytext());}
					case -202:
						break;
					case 212:
						{return new Symbol (sym.ID, yytext());}
					case -203:
						break;
					case 213:
						{return new Symbol (sym.ID, yytext());}
					case -204:
						break;
					case 214:
						{return new Symbol (sym.ID, yytext());}
					case -205:
						break;
					case 215:
						{return new Symbol (sym.ID, yytext());}
					case -206:
						break;
					case 216:
						{return new Symbol (sym.ID, yytext());}
					case -207:
						break;
					case 217:
						{return new Symbol (sym.ID, yytext());}
					case -208:
						break;
					case 218:
						{return new Symbol (sym.ID, yytext());}
					case -209:
						break;
					case 219:
						{return new Symbol (sym.ID, yytext());}
					case -210:
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
