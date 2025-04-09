
    import java.io.File;
    import java.io.IOException;
    import java.util.Calendar;

    public class TestFolderCreator {
        public static void main(String[] args) {
            String basePath = "C:/Users/arina_6wfwgf1/Desktop/lab_5_so";

            for (int i = 1; i <= 3; i++) {
                File folder = new File(basePath + "/test_old_folder_" + i);
                if (!folder.exists()) {
                    boolean created = folder.mkdir();
                    if (created) {
                        // Установим дату модификации на 40 дней назад
                        Calendar cal = Calendar.getInstance();
                        cal.add(Calendar.DAY_OF_YEAR, -40); // 40 дней назад
                        boolean modified = folder.setLastModified(cal.getTimeInMillis());

                        System.out.println("Создана папка: " + folder.getName() +
                                ", дата изменена: " + modified);
                    }
                } else {
                    System.out.println("Папка уже существует: " + folder.getName());
                }
            }
        }
    }

