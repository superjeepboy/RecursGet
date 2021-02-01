package somekindofbox.recursgetcli;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import somekindofbox.recursgetcli.downloader.Downloader;
import somekindofbox.recursgetcli.httphandler.ConnectionHandler;

public class Main { 

	public static void main(String[] args) {

		if (args.length < 1) {
			System.out.println("Usage: rget url path");
			System.out.println("Requires an http/https URL to work.");
			System.exit(0);
		} else if (args.length == 2) {
			switch (args.length) {
			case 1:
				System.out.println("No file output path specified; aborting");
				System.exit(0);
				break;
			case 2:
				System.out.println("URL Path: " + args[0].toString() + '\n'
						+ "File output path (before link mirroring): " + args[1].toString());
				break;
			}
		}

		
		HttpURLConnection http = null;
		URL link = null;
		File folder = null;
		StringBuilder filePath = new StringBuilder();
		try {
			link = new URL(args[0].toString());
			Scanner sc = new Scanner(System.in);
			http = ConnectionHandler.openHTTPConnection(link.openConnection());
			sc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (link != null) {
			if (link.getFile() != null) {
				try {
					folder = new File(args[1]);
					filePath.append(folder.getAbsolutePath());
					if (folder.exists()) {
						System.out.println("Path already exists, no need to create folders");
					} else {
						System.out.println("Path does not exist, attempting to create filepath");
						try {
							if (folder.mkdir()) {
								System.out.println("Path created successfully");
							}
						} catch (Exception e) {
							System.out.println("File writing error");
							e.printStackTrace();
						}
					}
					if (link.getFile() == "") {
						filePath.append(File.separator);
						filePath.append("download.html");
					} else {
						filePath.append(link.getFile());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (http != null) {
					try {
						Downloader.openHTTPDownload(filePath.toString(), http.getInputStream(), http);
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else {
					System.out.println("oh fuck http is null again");
					System.exit(0);
				}
				
			}
		}
	}
}
