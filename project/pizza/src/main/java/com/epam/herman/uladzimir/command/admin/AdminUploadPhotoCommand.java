package com.epam.herman.uladzimir.command.admin;

import com.epam.herman.uladzimir.command.Command;
import com.epam.herman.uladzimir.route.ResponseType;
import com.epam.herman.uladzimir.route.Route;
import com.epam.herman.uladzimir.controller.RequestWrapper;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static com.epam.herman.uladzimir.command.AttributeName.PHOTO;
import static com.epam.herman.uladzimir.route.ResponsePath.REDIRECT_TO_ADMIN_UPLOAD_PAGE;

/**
 * This class is used to upload the photo to the system.
 *
 * @author Uladzimir Herman
 * @see Command
 */
public class AdminUploadPhotoCommand implements Command {

    private static final Logger LOGGER =
            LogManager.getLogger(AdminUploadPhotoCommand.class);

    private static final String SAVE_DIRECTORY = "resources" + File.separator + "images";
    private static final String IMAGE_TYPE_JPG = "image/jpeg";
    private static final String IMAGE_TYPE_PNG = "image/png";
    private static final String FILENAME_EXTENSION_JPG = ".jpg";
    private static final String FILENAME_EXTENSION_PNG = ".png";

    @Override
    public Route execute(RequestWrapper requestWrapper) {
        Route route = new Route();

        List<Part> parts = requestWrapper.getParts();
        String uploadPath = requestWrapper.getApplicationPath() +
                File.separator + SAVE_DIRECTORY;
        File directory = new File(uploadPath);

        if (!directory.exists()) {
            directory.mkdirs();
        }

        String fileName;

        for (Part part : parts) {

            if (part.getName().equals(PHOTO)) {

                if (part.getContentType().equals(IMAGE_TYPE_JPG) ||
                        part.getContentType().equals(IMAGE_TYPE_PNG)) {

                    do {
                        fileName = uploadPath + File.separator + UUID.randomUUID().toString() +
                                (part.getContentType().equals(IMAGE_TYPE_JPG) ?
                                        FILENAME_EXTENSION_JPG : FILENAME_EXTENSION_PNG);
                    } while (new File(fileName).exists());

                    try {
                        part.write(fileName);

                        route.setResponseType(ResponseType.REDIRECT);
                        route.setResponsePath(REDIRECT_TO_ADMIN_UPLOAD_PAGE);
                    } catch (IOException e) {
                        LOGGER.error("IOException occurred when running the command: ", e);
                    }

                }

            }

        }

        return route;
    }

}