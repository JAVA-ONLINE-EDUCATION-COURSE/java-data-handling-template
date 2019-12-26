package com.epam.izh.rd.online;

import com.epam.izh.rd.online.repository.SimpleFileRepository;

public class SimpleFileRepositoryTest {
    public static void main(String[] args) {
        SimpleFileRepository simpleFileRepository = new SimpleFileRepository();

        //Указываю путь к папке где у меня лежит проект и получаю как раз эти 10 файлов, но тесты не проходят!!
        //Что не так??
        //Скорее всего дело в путях, что то напутали в относительных путях (Винда, *NIX), или напрочь забыли указать папку resources. ИМХО
        //Ну или я где то налажал
        long files = simpleFileRepository.countFilesInDirectory("G:\\");
        System.out.println(files);

        //Аналогично, указываю путь к папке где у меня лежит проект и получаю как раз эти 7 папок, но тесты не проходят!!
        //Что не так??
        //Скорее всего дело в путях, что то напутали в относительных путях (Винда, *NIX), или напрочь забыли указать папку resources. ИМХО
        //Ну или я где то налажал
        long dir = simpleFileRepository.countDirsInDirectory("G:\\");
        System.out.println(dir);

        //Пробовал на своей флешке - работает, в тестах вабще забыли про данный метод
        simpleFileRepository.copyTXTFiles("G:\\333", "G:\\333c");

        //Метод создает файл на диске с расширением txt, работает, но тест не проходит
        System.out.println(simpleFileRepository.createFile("G:\\2", "txtfile_1"));
    }
}
