import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class FileHandler {

	public static ZipFile GetNonEmptyZipFileObject(String zipFileLocation) throws IOException {
		ZipFile zipFile = new ZipFile(zipFileLocation);
		return (zipFile.size() != 0) ? zipFile : null;
	}
	
	
	
	private static void process(File f, Object... objs) throws IOException {
		// .. Do somework here
		System.out.println("File Name: " + f.getName());
		System.out.println("Content:\n" + Files.readAllBytes(f.toPath()));
		System.out.println("File Name: " + getExtension(f.getAbsolutePath()));
	}
	

	public static void processZipFile(ZipFile zipFile) throws Exception {
		Enumeration<? extends ZipEntry> entries = zipFile.entries();

		try (T temp = new T()) {
			while (entries.hasMoreElements()) {
				
				ZipEntry entry = entries.nextElement();
				String fileName = entry.getName();
				InputStream istream = zipFile.getInputStream(entry);
				File f = new File(temp.getAbsolutePath() + File.separatorChar + fileName);
				OutputStream oStream = new FileOutputStream(temp.getAbsolutePath() + File.separatorChar + fileName);
				
				int read = 0;
				byte[] bytes = new byte[1024];
				while ((read = istream.read(bytes)) != -1) {
					oStream.write(bytes, 0, read);
				}

				istream.close();
				oStream.flush();
				oStream.close();

				process(f);
			}
		}
	}
	
	

	
	private static String getExtension(String filePath){
		// return FilenameUtils.getExtension("/path/to/file/foo.txt");
		return null;
	}


	private static class T implements AutoCloseable {

		private final File tempFile;

		public T() throws IOException {
			tempFile = new File("./temp");
			setUp();
		}

		public String getAbsolutePath() {
			return tempFile.getAbsolutePath();
		}
		
		private void setUp() {
			if(!tempFile.exists()){
				tempFile.mkdir();
			}
		}
		
		@Override
		public void close() throws Exception {
			teardown();
		}

		private void teardown() throws IOException {
			if(tempFile.exists()){
				Path directory = Paths.get(tempFile.getAbsolutePath());
				   Files.walkFileTree(directory, new SimpleFileVisitor<Path>() {
					   @Override
					   public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
						   Files.delete(file);
						   return FileVisitResult.CONTINUE;
					   }

					   @Override
					   public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
						   Files.delete(dir);
						   return FileVisitResult.CONTINUE;
					   }

				   });
			}
		}
	}

}
