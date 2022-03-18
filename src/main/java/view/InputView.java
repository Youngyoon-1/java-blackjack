package view;

import domain.participant.Player;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String NAME_OR_CARD_DELIMITER = ",";
    private static final String INPUT_NAME_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String DUPLICATE_NAME_MESSAGE = "중복된 이름은 불가합니다.";
    private static final String BLANK_INPUT_MESSAGE = "값을 입력해주세요.";
    private static final String HIT_SUFFIX_MESSAGE = "는  한 장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";
    private static final String INVALID_HIT_MESSAGE = "예는 y, 아니오는 n를 입력하세요.";
    private static final String DO_HIT = "y";
    private static final String DO_NOT_HIT = "n";
    private static final String INVALID_NUMBER_MESSAGE = "숫자를 입력해주세요.";

    private InputView() {
    }

    public static List<String> inputPlayerName() {
        print(INPUT_NAME_MESSAGE);
        final String names = readLine();
        if (hasNoDuplication(names)) {
            return getSplitAndTrim(names);
        }
        print(DUPLICATE_NAME_MESSAGE);
        return inputPlayerName();
    }

    private static void print(final String message) {
        System.out.println(message);
    }

    private static String readLine() {
        String input = SCANNER.nextLine();
        if (input.isBlank()) {
            print(BLANK_INPUT_MESSAGE);
            return readLine();
        }
        return input;
    }

    private static boolean hasNoDuplication(final String input) {
        final List<String> parsedInput = getSplitAndTrim(input);
        final int beforeCount = parsedInput.size();
        final int afterCount = (int) parsedInput.stream().distinct().count();
        return beforeCount == afterCount;
    }

    private static List<String> getSplitAndTrim(final String input) {
        return Arrays.stream(input.split(NAME_OR_CARD_DELIMITER)).map(String::trim).collect(Collectors.toList());
    }

    public static int inputBettingMoney(final Player player) {
        System.out.printf("%s의 배팅 금액은?%n", player.getName());
        return convertToInt(readLine());
    }

    private static int convertToInt(final String string) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException e) {
            print(INVALID_NUMBER_MESSAGE);
            return convertToInt(readLine());
        }
    }

    public static boolean inputTryToHit(final Player player) {
        printPlayerHitIntro(player.getName());

        String response = readLine();
        if (isYOrN(response)) {
            return response.equalsIgnoreCase(DO_HIT);
        }
        print(INVALID_HIT_MESSAGE);
        return inputTryToHit(player);
    }

    private static void printPlayerHitIntro(final String name) {
        print(name + HIT_SUFFIX_MESSAGE);
    }

    private static boolean isYOrN(final String response) {
        return response.equalsIgnoreCase(DO_HIT) || response.equalsIgnoreCase(DO_NOT_HIT);
    }
}
