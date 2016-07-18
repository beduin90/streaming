package com.appstreaming.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.awt.image.BufferedImage;
import com.xuggle.mediatool.IMediaWriter;
import com.xuggle.mediatool.IMediaReader;

import com.xuggle.mediatool.IMediaViewer;

import com.xuggle.mediatool.IMediaWriter;

import com.xuggle.mediatool.ToolFactory;
import java.io.File;

import java.io.IOException;



public class LoadDriver {
    public static void main(String[] args) {
        try {
            // The newInstance() call is a work around for some
            // broken Java implementations

            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception ex) {
            // handle the error
        }
    }
}