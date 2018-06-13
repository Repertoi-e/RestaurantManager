package edu.school.restaurantmanager.workfile;

import edu.school.restaurantmanager.MainFrame;

import java.io.File;
import java.nio.file.Paths;

public class WorkFile {

    private File m_File;

    public WorkFile() {
    }

    public File getFile() { return m_File; }

    public void updateFile(File file) {
        m_File = file;
        MainFrame.getDirWatcher().updateDir(Paths.get(file.getParent()));
    }
}
