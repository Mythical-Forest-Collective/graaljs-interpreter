/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.foc.graaljsenv;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

import org.graalvm.polyglot.Engine;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.HostAccess;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Can only accept one argument!");
            return;
        }

        String code;
        try {
            code = Files.readString(Paths.get(args[0]));
        } catch (IOException e) {
            System.out.println("Can't find the specified file!");
            return;
        }

        Engine engine = Engine.newBuilder()
          .option("engine.WarnInterpreterOnly", "false")
          .build();

        Context context = Context.newBuilder("js")
         .allowHostAccess(HostAccess.ALL)
         .allowHostClassLookup(className -> true)
         .engine(engine)
         .build();
        context.eval("js", code);
    }
}
