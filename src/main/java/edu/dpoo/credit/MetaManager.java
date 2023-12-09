package edu.dpoo.credit;

import java.io.BufferedWriter;
import java.io.File;

public class MetaManager {
    public static void createSubClass(String className) {
        String classDefinition = String.format(
                "package edu.dpoo.cards;\n\n" +
                "public class %sCreditCard extends CreditCard {public %sCreditCard() {}}\n",
                className, className);
        File file = new File("src/edu/dpoo/cards/" + className + "CreditCard.java");
        if (file.exists())
            return;
        try (BufferedWriter writer = new BufferedWriter(new java.io.FileWriter(file))) {
            writer.append(classDefinition);
        } catch (Exception ignored) {}
    }

    public static CreditCard fromName(String name) {
        try {
            return (CreditCard) Class.forName("edu.dpoo.cards." + name + "CreditCard").getConstructor().newInstance();
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }
}
