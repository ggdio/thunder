package br.com.ggdio;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;
import org.apache.ignite.lang.IgniteCallable;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		try (Ignite ignite = Ignition.start("example-ignite.xml")) {
			Collection<IgniteCallable<Integer>> calls = new ArrayList<>();

			for (final String word : "a b c d e f g h i j k l m n o p q r s t u v x z".split(" "))
				calls.add(word::length);

			// Execute callable on ignite
			Collection<Integer> res = ignite.compute().call(calls);

			// Add up all the results.
			int sum = res.stream().mapToInt(Integer::intValue).sum();

			System.out.println("Total number of characters is '" + sum + "'.");
		}
	}
}
