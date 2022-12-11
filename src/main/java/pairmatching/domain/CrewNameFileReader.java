package pairmatching.domain;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CrewNameFileReader {
    public List<String> readFileBy(String course) {  // "백엔드"
        String filePath = String.format("src/main/resources/%s-crew.md",
                convertCourseFromKoreanToEnglish(course));
        List<String> results = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                results.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException("파일에 이상이 있습니다. 에러 메세지 : " + e.getMessage());
        }

        return results;
    }

    private String convertCourseFromKoreanToEnglish(String course) {
        if (course.equals("백엔드")) {
            return "backend";
        }

        return "frontend";
    }
}
