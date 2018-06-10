package sd.learningjava.collection.launcher;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class CollectionLauncher {

    public static void main(String[] args) {
        try {
            final List<String> fileList = new ArrayList<>();
            Files.newDirectoryStream(Paths.get(".",
                    "Collection",
                    "src",
                    "sd",
                    "learningjava",
                    "collection",
                    "processor"),
                    path -> path.toString().endsWith(".java"))
                    .forEach(p -> fileList.add(p.getFileName()
                            .toString()
                            .replaceFirst("\\.java$", "")));

            System.out.println("-:: Collection Demo ::-");
            System.out.println("-----------------------");
            IntStream.range(0, fileList.size())
                    .forEach(idx -> System.out.println((idx + 1) + ". " + fileList.get(idx)));

            System.out.print("Enter your choice: ");
            int choice = new Scanner(System.in).nextInt();
            String className = "sd.learningjava.collection.processor." + fileList.get(choice - 1);
            Class<?> clz = Class.forName(className);
            Object obj = clz.getDeclaredConstructor().newInstance();
            clz.getDeclaredMethod("demoRun").invoke(obj);
        } catch (IOException
                | IndexOutOfBoundsException
                | ClassNotFoundException
                | IllegalAccessException
                | NoSuchMethodException
                | InstantiationException
                | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
