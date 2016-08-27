package com.quizzlii.quizzlii.model.question;

import com.quizzlii.quizzlii.model.JsonAttributes;

public enum QuestionType {

    TRUE_FALSE(JsonAttributes.QuestionType.TRUE_FALSE, TrueFalseQuiz.class);

    private final String jsonName;
    private final Class<? extends Question> type;

    QuestionType(final String jsonName, final Class<? extends Question> type) {
        this.jsonName = jsonName;
        this.type = type;
    }

    public String getJsonName() {
        return jsonName;
    }

    public Class<? extends Question> getType() {
        return type;
    }

}
