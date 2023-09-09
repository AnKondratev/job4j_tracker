package ru.job4j.io;

import java.util.Scanner;

public class Matches {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Игра 11.");
        boolean turn = true;
        int count = 11;
        while (count > 0) {
            String player = turn ? "Первый игрок" : "Второй игрок";
            System.out.println(player + " введите число от 1 до 3:");
            int matches = 0;
            try {
                matches = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Ошибка формата. ");
            }
            turn = !turn;
            if (matches > 0 && matches < 4 && count >= matches) {
                count -= matches;
            } else {
                System.out.println(player + " повторите попытку ввода! ");
                turn = !turn;
            }
        }
        if (!turn) {
            System.out.println("Выиграл первый игрок");
        } else {
            System.out.println("Выиграл второй игрок");
        }
    }
}