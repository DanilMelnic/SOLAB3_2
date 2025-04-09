import java.io.File;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

public class FolderCleaner {

    public static void main(String[] args) {
        String folderPath = "C:/Users/arina_6wfwgf1/Desktop/lab_5_so"; // Путь к нужной папке на рабочем столе
        File folder = new File(folderPath);

        // Проверяем, что указанная папка существует
        if (!folder.exists() || !folder.isDirectory()) {
            System.out.println("Указанная папка не существует или это не каталог.");
            return;
        }

        // Получаем список папок в указанной директории
        File[] directories = folder.listFiles(File::isDirectory);
        if (directories == null || directories.length == 0) {
            System.out.println("В папке нет подкаталогов.");
            return;
        }

        // Получаем текущую дату
        long currentTime = System.currentTimeMillis();
        long thirtyDaysAgo = currentTime - (30L * 24 * 60 * 60 * 1000);

        // Сортируем папки по дате последней модификации
        Arrays.sort(directories, (d1, d2) -> Long.compare(d2.lastModified(), d1.lastModified()));

        int count = 0;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Папки старше 30 дней:");
        for (File dir : directories) {
            if (dir.lastModified() < thirtyDaysAgo) {
                System.out.println("Папка: " + dir.getName());
                System.out.println("Дата последней модификации: " + new Date(dir.lastModified()));
                System.out.println("Путь: " + dir.getAbsolutePath());
                System.out.println("-------------------------------------------------");

                // Предлагаем удалить папки
                System.out.print("Хотите удалить эту папку? (да/нет): ");
                String userChoice = scanner.nextLine().trim().toLowerCase();

                if ("да".equals(userChoice)) {
                    boolean deleted = deleteDirectoryRecursively(dir);
                    if (deleted) {
                        System.out.println("Папка " + dir.getName() + " удалена.");
                    } else {
                        System.out.println("Не удалось удалить папку " + dir.getName());
                    }
                }

                count++;
            }

            // Ограничиваем вывод 5 папками
            if (count >= 5) break;
        }

        // Если папки не были найдены или удалены
        if (count == 0) {
            System.out.println("Не найдено папок старше 30 дней или все папки были недавно изменены.");
        }

        scanner.close();
    }

    // Рекурсивная функция для удаления папки и её содержимого
    public static boolean deleteDirectoryRecursively(File directory) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    // Рекурсивный вызов для подкаталогов
                    deleteDirectoryRecursively(file);
                } else {
                    // Удаляем файл
                    file.delete();
                }
            }
        }

        return directory.delete();
    }
}
