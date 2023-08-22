package cli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import tributary.Tributary;

public class TributaryCLI {
    public static void main(String[] args) throws IOException {
        Tributary controller = new Tributary();
        System.out.println("Please enter your command:");
        while (true) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String input = reader.readLine();
            String[] command = input.split(" ");

            switch (command[0]) {
                case "create":
                    createCommand(command, controller); break;
                case "produce":
                    produceCommand(command, controller); break;
                case "consume":
                    consumeCommand(command, controller); break;
                case "show":
                    showCommand(command, controller); break;
                case "parallel":
                    parallelCommand(command, controller); break;
                default:
            }
        }
    }
}
