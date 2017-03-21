package cn.smartpolice.tools;

import java.util.List;

/**
 * 字节数组处理
 * 
 * @author 刘超
 *
 */
public class ByteArrayProc {

	// int 转换为byte
	public byte[] int2bytes(int i) {
		byte[] b = new byte[4];
		b[0] = (byte) (0xff & i);
		b[1] = (byte) ((0xff00 & i) >> 8);
		b[2] = (byte) ((0xff0000 & i) >> 16);
		b[3] = (byte) ((0xff000000 & i) >> 24);
		return b;
	}

	// 系统提供的数组拷贝方法arraycopy
	public byte[] sysCopy(List<byte[]> srcArrays) {
		int len = 0;
		for (byte[] srcArray : srcArrays) {
			len += srcArray.length;
		}
		byte[] destArray = new byte[len];
		int destLen = 0;
		for (byte[] srcArray : srcArrays) {
			System.arraycopy(srcArray, 0, destArray, destLen, srcArray.length);
			destLen += srcArray.length;
		}
		return destArray;
	}
}
