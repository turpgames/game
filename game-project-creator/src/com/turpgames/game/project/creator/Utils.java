package com.turpgames.game.project.creator;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;

public class Utils {
	public static void deleteDir(String dir) throws IOException {
		FileUtils.deleteDirectory(new File(dir));
	}

	public static void deleteFile(String path) throws IOException {
		File f = new File(path);
		f.delete();
	}

	public static void copyDir(String src, String dest) throws IOException {
		FileUtils.copyDirectory(new File(src), new File(dest));
	}

	public static void rename(String dir, String newName) throws IOException {
		File f = new File(dir);
		String newPath = f.getParentFile().getPath() + "/" + newName;
		f.renameTo(new File(newPath));
	}

	public static String readAllText(String dir) throws IOException {
		return FileUtils.readFileToString(new File(dir), "utf-8");
	}

	public static void writeAllText(String dir, String text) throws IOException {
		FileUtils.writeStringToFile(new File(dir), text, "utf-8");
	}

	public static void replaceInFile(String filePath, String token, String value) throws IOException {
		String fileContent = readAllText(filePath);

		boolean replaced = false;

		while (fileContent.contains(token)) {
			fileContent = fileContent.replace(token, value);
			replaced = true;
		}

		if (replaced)
			writeAllText(filePath, fileContent);
	}

	public static File[] getFiles(String dir, final String extension) throws IOException {
		return FileUtils.listFiles(
				new File(dir),
				new IOFileFilter() {

					@Override
					public boolean accept(File dir, String name) {
						return name.endsWith(extension);
					}

					@Override
					public boolean accept(File file) {
						return file.getName().endsWith(extension);
					}
				},
				TrueFileFilter.INSTANCE)
				.toArray(new File[0]);
	}
}
