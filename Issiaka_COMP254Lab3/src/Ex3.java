import java.io.File;
import java.util.Scanner;

/**
 * COMP-254 Lab 3 – Exercise 3
 * Recursively search the file system for files matching a given name.
 */
class FileFinder {

    public static void find(File path, String filename) {
        if (path == null) return;
        File[] entries = path.listFiles();
        if (entries == null) return;

        for (File f : entries) {
            if (f.isFile() && f.getName().equals(filename)) {
                System.out.println("Found: " + f.getAbsolutePath());
            } else if (f.isDirectory()) {
                find(f, filename);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter starting directory path: ");
        String start = sc.nextLine().trim();
        System.out.print("Enter filename to search for (case-sensitive): ");
        String target = sc.nextLine().trim();

        File root = new File(start);
        if (!root.exists() || !root.isDirectory()) {
            System.out.println("The starting path must be an existing directory.");
            sc.close();
            return;
        }

        find(root, target);
        System.out.println("Search complete.");
        sc.close();
    }
}
