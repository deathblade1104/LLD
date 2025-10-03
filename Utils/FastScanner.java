package Utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

public class FastScanner {
  private int BS = 1 << 16;
  private char NC = (char) 0;
  private byte[] buf = new byte[BS];
  private int bId = 0, size = 0;
  private char c = NC;
  private double cnt = 1;
  private BufferedInputStream in;

  public FastScanner() {
    in = new BufferedInputStream(System.in, BS);
  }

  public FastScanner(String s) {
    try {
      in = new BufferedInputStream(new FileInputStream(new File(s)), BS);
    } catch (Exception e) {
      in = new BufferedInputStream(System.in, BS);
    }
  }

  private char getChar() {
    while (bId == size) {
      try {
        size = in.read(buf);
      } catch (Exception e) {
        return NC;
      }
      if (size == -1)
        return NC;
      bId = 0;
    }
    return (char) buf[bId++];
  }

  public int nextInt() {
    return (int) nextLong();
  }

  public int[] nextInts(int N) {
    int[] res = new int[N];
    for (int i = 0; i < N; i++) {
      res[i] = (int) nextLong();
    }
    return res;
  }

  public ArrayList<Integer> nextIntList(int N) {
    ArrayList<Integer> ret = new ArrayList<>(N);
    for (int i = 0; i < N; i++)
      ret.add(nextInt());
    return ret;
  }

  public long[] nextLongs(int N) {
    long[] res = new long[N];
    for (int i = 0; i < N; i++) {
      res[i] = nextLong();
    }
    return res;
  }

  public ArrayList<Long> nextLongList(int N) {
    ArrayList<Long> ret = new ArrayList<>(N);
    for (int i = 0; i < N; i++)
      ret.add(nextLong());
    return ret;
  }

  public long nextLong() {
    cnt = 1;
    boolean neg = false;
    if (c == NC)
      c = getChar();
    for (; (c < '0' || c > '9'); c = getChar()) {
      if (c == '-')
        neg = true;
    }
    long res = 0;
    for (; c >= '0' && c <= '9'; c = getChar()) {
      res = (res << 3) + (res << 1) + c - '0';
      cnt *= 10;
    }
    return neg ? -res : res;
  }

  public double nextDouble() {
    double cur = nextLong();
    return c != '.' ? cur : cur + nextLong() / cnt;
  }

  public double[] nextDoubles(int N) {
    double[] res = new double[N];
    for (int i = 0; i < N; i++) {
      res[i] = nextDouble();
    }
    return res;
  }

  public String next() {
    StringBuilder sb = new StringBuilder();
    while (c <= 32)
      c = getChar();
    while (c > 32) {
      sb.append(c);
      c = getChar();
    }
    return sb.toString();
  }

  public String nextLine() {
    StringBuilder sb = new StringBuilder();
    while (c <= 32)
      c = getChar();
    while (c != '\n') {
      sb.append(c);
      c = getChar();
    }
    return sb.toString();
  }

  public boolean hasNext() {
    if (c > 32)
      return true;
    while (true) {
      c = getChar();
      if (c == NC)
        return false;
      else if (c > 32)
        return true;
    }
  }
}
