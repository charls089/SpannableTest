package com.example.spannabletest;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void checkMatcher() {
        String[] textArr = {"공유주차장", "제휴주차장", "자주 물어보는 것", "기타"};
        String input = "주차";
        for(String text: textArr) {
            Matcher matcher = Pattern.compile(input).matcher(text);
            boolean isMatch = matcher.find();
            System.out.println(isMatch);
            while(matcher.find()) {
                System.out.println(String.format("start : %d, end : %d", matcher.start(), matcher.end()));
            }
        }
    }
}