package somekindofbox.recursgetcli.downloader;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;

public class Downloader {
	public static void openHTTPDownload(String filePath, InputStream in, HttpURLConnection http) {
		try {
			File newFilePath = null;
			if (!new File(filePath.toString() + File.separator + http.getURL().getFile()).exists()) {
				newFilePath = new File(filePath.substring(filePath.indexOf(filePath), filePath.lastIndexOf('/')));
				System.out.println("Making file subdirectories");
				try {
					newFilePath.mkdirs();
				} catch (Exception e) {
					System.out.println("Subdirectory file creation error");
					e.printStackTrace();
					return;
				}
			} else {
				newFilePath = new File(filePath.toString());
			}
			BufferedInputStream stream = new BufferedInputStream(in);
			FileOutputStream fio = new FileOutputStream(filePath);
			StringBuilder fileSize = new StringBuilder("[");
			double downloadprog = 0.00;
			fileSize.append(http.getContentLengthLong() / 0.00097656 + "MB] ");
			double percent = 0.00;
			byte b[] = new byte[1024];
			int r;
			System.out.println("Downloading...");
			while ((r = stream.read(b, 0, 1024)) != -1) {
				fio.write(b, 0, r);
				downloadprog += r;
				percent = (downloadprog * 100) / http.getContentLength();
				System.out.println(fileSize.toString() + (int) percent + "%");
			}
			stream.close();
			fio.close();
			http.disconnect();
			System.out.println("File finished");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
