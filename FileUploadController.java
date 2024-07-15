package com.example.demo.controller;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.cxf.jaxrs.ext.multipart.MultipartBody;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@Path("/upload")
public class FileUploadController {

    @GET
    @Path("/file")
    public Response uploadFile(MultipartBody multipartBody) {
        Attachment attachment = multipartBody.getRootAttachment();
        if (attachment == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("File is empty").build();
        }

        try {
            byte[] bytes = attachment.getObject(byte[].class);
            Path path = Paths.get(System.getProperty("java.io.tmpdir") + "/" + attachment.getContentDisposition().getParameter("filename"));
            Files.write(path, bytes);
            return Response.ok("File uploaded successfully: " + attachment.getContentDisposition().getParameter("filename")).type(MediaType.TEXT_PLAIN).build();
        } catch (IOException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("File upload failed").build();
        }
    }
}
