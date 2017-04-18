package com.schonherz.project.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Janos Harsanyi <hajani003@gmail.com>
 */
public class UploadedFiles {

    public static final String UPLOAD_PREFIX = "upload_";

    public static final File UPLOAD_IMAGES_LOCATION
            = new File(System.getProperty("jboss.home.dir") + "/uploads");

    public static String moveUploadedFile(UploadedFile uploadedFile, File destination)
            throws IOException {
        if (!destination.exists()) {
            destination.mkdirs();
        }

        InputStream input = uploadedFile.getInputstream();
        File outputFile = getUniqueName(uploadedFile, destination);
        OutputStream output = new FileOutputStream(outputFile);

        try {
            IOUtils.copy(input, output);
        } finally {
            IOUtils.closeQuietly(input);
            IOUtils.closeQuietly(output);
        }

        return outputFile.getName();
    }

    private static File getUniqueName(UploadedFile uploadedFile, File destination)
            throws IOException {
        String filename = FilenameUtils.getName(uploadedFile.getFileName());
        String extension = "." + FilenameUtils.getExtension(filename);
        return File.createTempFile(UPLOAD_PREFIX, extension, destination);
    }

    public static void deleteUpload(String fileName, File parentFile) {
        if (fileName == null || parentFile == null) {
            return;
        }
        FileUtils.deleteQuietly(new File(parentFile, fileName));
    }

    public static boolean isNullOrEmpty(UploadedFile profilePicture) {
        return profilePicture == null
                || profilePicture.getFileName().isEmpty()
                || profilePicture.getSize() == 0;
    }

    private static final Set<String> ALLOWED_IMAGE_EXTENSIONS
            = Collections.unmodifiableSet(new HashSet<>(
                    Arrays.asList("jpg", "jpeg", "bmp", "gif", "png")));

    public static boolean isImage(UploadedFile file) {
        String extension = FilenameUtils.getExtension(file.getFileName()).toLowerCase();
        return ALLOWED_IMAGE_EXTENSIONS.contains(extension);
    }

    public static boolean sizeBelow(UploadedFile file, long sizeLimit) {
        return file.getSize() < sizeLimit;
    }
}
