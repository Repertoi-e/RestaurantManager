package edu.school.restaurantmanager.workfile;

import edu.school.restaurantmanager.MainFrame;

import java.nio.file.*;
import java.io.IOException;

public class DirWatcher implements Runnable {

    private final WatchService m_Watcher;
    private Path m_Directory;
    private WatchKey m_WatchKey;

    public DirWatcher() throws IOException {
        m_Watcher = FileSystems.getDefault().newWatchService();
    }

    @Override
    public void run() {
        try {
            for (;;) {
                WatchKey key = m_Watcher.take();

                if (this.m_WatchKey != key)
                    continue;

                for (WatchEvent<?> event : key.pollEvents()) {
                    @SuppressWarnings("unchecked")
                    Path name = ((WatchEvent<Path>)event).context();

                    // Ако файлът променен в директорията е нашият,
                    // ъпдейтва менюто и т.н.
                    if (name.toString().equals(MainFrame.getWorkFile().getFile().getName()))
                        MainFrame.getMenuView().showItems();
                }

                if (!key.reset())
                    break;
            }
        } catch (InterruptedException ignored) { }
    }

    public void updateDir(Path dir) {
        m_Directory = dir;
        try {
            m_WatchKey = m_Directory.register(m_Watcher, StandardWatchEventKinds.ENTRY_MODIFY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
