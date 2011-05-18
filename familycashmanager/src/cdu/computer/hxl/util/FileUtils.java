package cdu.computer.hxl.util;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FileUtils {

	public InputStream in = null;

	public FileUtils() {
	}

	public FileUtils(InputStream in) {
		this.in = in;
	}

	public String readText() {

		String text = "";

		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String lineStr = null;
		try {
			while ((lineStr = br.readLine()) != null) {
				text += lineStr + "\n";
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return text;
	}

	public ZipOutputStream getZipOputStream(File file) {
		ZipOutputStream zos = null;
		try {
			zos = new ZipOutputStream(new FileOutputStream(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return zos;
	}

	public DataInputStream getDataInpuStream(File file) {
		DataInputStream ds = null;
		try {
			ds = new DataInputStream(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return ds;
	}

	public DataOutputStream getDataOutputStream(File file) {
		DataOutputStream dos = null;
		try {
			dos = new DataOutputStream(new FileOutputStream(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return dos;
	}

	public static void main(String[] args) {
		FileUtils file = new FileUtils(
				Resource.getResourceStream("aboutMe.txt"));
		// System.out.println(file.readText());
		// FileUtils fu = new FileUtils();
		try {
			ZipOutputStream zos = file.getZipOputStream(new File("a.zip"));
			zos.putNextEntry(new ZipEntry("aboutMe.txt"));
			zos.write(file.readText().getBytes());
			zos.finish();
			zos.closeEntry();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
