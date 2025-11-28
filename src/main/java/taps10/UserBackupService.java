package taps10;


import java.io.*;
import java.nio.file.*;
import java.util.List;

public class UserBackupService {

    public void saveUsers(List<User> users, String filePath) {
        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(users);
            System.out.println("Users saved successfully to " + filePath);
        } catch (IOException | InvalidPathException e) {
            System.out.println("Error saving users: " + e.getMessage());
        }
    }

    public List<User> loadUsers(String filePath) {
        try (ObjectInputStream ois =
                     new ObjectInputStream(new FileInputStream(filePath))) {
            List<User> users = (List<User>) ois.readObject();
            System.out.println("Users loaded successfully from " + filePath);
            return users;
        } catch (IOException | ClassNotFoundException | InvalidPathException e) {
            System.out.println("Error loading users: " + e.getMessage());
            return List.of();
        }
    }

    public void backupFile(String sourceFile, String backupDir) {
        try {
            Path source = Paths.get(sourceFile);
            Path dir = Paths.get(backupDir);
            Files.createDirectories(dir);
            Path target = dir.resolve("users_backup.dat");
            Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Backup created at " + target);
        } catch (IOException | InvalidPathException e) {
            System.out.println("Error creating backup: " + e.getMessage());
        }
    }

    public void deleteBackup(String backupFilePath) {
        try {
            Path file = Paths.get(backupFilePath);
            Files.deleteIfExists(file);
            System.out.println("Backup file deleted successfully: " + backupFilePath);
        } catch (IOException | InvalidPathException e) {
            System.out.println("Error deleting backup: " + e.getMessage());
        }
    }
}
