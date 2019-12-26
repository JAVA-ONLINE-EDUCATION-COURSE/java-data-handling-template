package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.repository.SimpleFileRepository;

import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleRegExpService implements RegExpService {
    final static String SEPARATOR = System.getProperty("file.separator");
    //Это конечно плохо, но т.к. явно сказано изменить файл sensitive_data.txt (из директории resources), хардкодю путь к нему
    final String filePathIS = "src" + SEPARATOR + "main" + SEPARATOR + "resources" + SEPARATOR + "sensitive_data.txt";
    SimpleFileRepository simpleFileRepository = new SimpleFileRepository();


    /**
     * Метод должен читать файл sensitive_data.txt (из директории resources) и маскировать в нем конфиденциальную информацию.
     * Номер счета должен содержать только первые 4 и последние 4 цифры (1234 **** **** 5678). Метод должен содержать регулярное
     * выражение для поиска счета.
     *
     * @return обработанный текст
     */
    //Не знаю можно или нет, но я обращаюсь с сделанному мной методу в SimpleFileRepository
    //Не знаю почему, но ошибка, проверил текст в тесте, все слово в слово, пробелов лишних нет, что не так??
    @Override
    public String maskSensitiveData() {
        String forFormat = simpleFileRepository.readFileFromResources("sensitive_data.txt");
        Pattern pattern = Pattern.compile("(\\s\\d{4})(\\s\\d{4}\\s\\d{4})(\\s\\d{4})");
        Matcher matcher = pattern.matcher(forFormat);
        StringBuffer stringBuffer = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(stringBuffer, "$1 **** ****$3");
        }
        matcher.appendTail(stringBuffer);

        try {
            FileWriter fileWriter = new FileWriter(filePathIS, false);
            fileWriter.write(stringBuffer.toString());
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stringBuffer.toString();
    }

    /**
     * Метод должен считыввать файл sensitive_data.txt (из директории resources) и заменять плейсхолдер ${payment_amount} и ${balance} на заданные числа. Метод должен
     * содержать регулярное выражение для поиска плейсхолдеров
     *
     * @return обработанный текст
     */
    //Не знаю почему, но ошибка, проверил текст в тесте, все слово в слово, пробелов лишних нет, что не так??
    @Override
    public String replacePlaceholders(double paymentAmount, double balance) {
        String forFormat = simpleFileRepository.readFileFromResources("sensitive_data.txt");
        Pattern patternPayment = Pattern.compile("\\$\\{payment_amount}");

        Matcher matcherPayment = patternPayment.matcher(forFormat);
        while (matcherPayment.find()) {
            forFormat = forFormat.replaceAll("\\$\\{payment_amount}", String.format("%.0f", paymentAmount));

        }
        Pattern patternBalance = Pattern.compile("\\$\\{balance}");
        Matcher matcherBalance = patternBalance.matcher(forFormat);
        while (matcherBalance.find()) {
            forFormat = forFormat.replaceAll("\\$\\{balance}", String.format("%.0f", balance));
        }

        try {
            FileWriter fileWriter = new FileWriter(filePathIS, false);
            fileWriter.write(forFormat);
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return forFormat;
    }
}
