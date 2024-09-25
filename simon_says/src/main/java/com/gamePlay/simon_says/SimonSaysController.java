package com.gamePlay.simon_says;

import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import java.util.*;

@RestController
@RequestMapping("/game")
public class SimonSaysController {

    private static final String SEQUENCE_SESSION_KEY = "sequence";
    private static final String[] COLORS = {"Red", "Blue", "Green", "Yellow"};

    @GetMapping("/generate-sequence")
    public List<String> generateSequence(HttpSession session) {
        List<String> sequence = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            sequence.add(COLORS[random.nextInt(COLORS.length)]);
        }
        session.setAttribute(SEQUENCE_SESSION_KEY, sequence);
        return sequence;
    }

    @PostMapping("/validate-sequence")
    public boolean validateSequence(@RequestBody List<String> userSequence, HttpSession session) {
        List<String> generatedSequence = (List<String>) session.getAttribute(SEQUENCE_SESSION_KEY);
        return generatedSequence != null && generatedSequence.equals(userSequence);
    }
}
