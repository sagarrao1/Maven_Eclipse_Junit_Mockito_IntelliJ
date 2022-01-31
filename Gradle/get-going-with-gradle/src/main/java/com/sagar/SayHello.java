package com.sagar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class SayHello {

	public static void main(String[] args) throws IOException {
//		System.out.println("enter language");
//		Scanner scanner = new Scanner(System.in);
//		String language = scanner.nextLine();
////		String language = args[0];
//		
//		BufferedReader bufferedReader = Files.newBufferedReader(Paths.get("./src/main/resources/"+language+".txt"));
//		String line = bufferedReader.readLine();
//		System.out.println(line);
	
        String language = args[0];

        InputStream resourceStream = SayHello.class.getClassLoader().getResourceAsStream(language + ".txt");
        assert resourceStream != null;
        BufferedReader bufferedInputStream = new BufferedReader(new InputStreamReader(resourceStream, StandardCharsets.UTF_8));

        System.out.println(bufferedInputStream.readLine());	
	
	}

}
