object TestFolderCreator {
    @JvmStatic
    fun main(args: Array<String>) {
        val basePath: String = "C:/Users/arina_6wfwgf1/Desktop/lab_5_so"

        for (i in 1..3) {
            val folder: File = File(basePath + "/test_old_folder_" + i)
            if (!folder.exists()) {
                val created: Boolean = folder.mkdir()
                if (created) {
                    // Установим дату модификации на 40 дней назад
                    val cal: Calendar = Calendar.getInstance()
                    cal.add(Calendar.DAY_OF_YEAR, -40) // 40 дней назад
                    val modified: Boolean = folder.setLastModified(cal.getTimeInMillis())

                    kotlin.io.println(
                        "Создана папка: " + folder.getName() +
                                ", дата изменена: " + modified
                    )
                }
            } else {
                kotlin.io.println("Папка уже существует: " + folder.getName())
            }
        }
    }
}