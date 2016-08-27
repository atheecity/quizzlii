package com.quizzlii.quizzlii.model.question;

import android.os.Parcel;
import com.quizzlii.quizzlii.helper.ParcelableHelper;

public class TrueFalseQuiz extends Question<Boolean> {

    public TrueFalseQuiz(String question, Boolean answer, boolean solved) {
        super(question, answer, solved);
    }

    public TrueFalseQuiz(Parcel in) {
        super(in);
        setAnswer(ParcelableHelper.readBoolean(in));
    }

    @Override
    public String getStringAnswer() {
        return getAnswer().toString();
    }

    @Override
    public QuestionType getType() {
        return QuestionType.TRUE_FALSE;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        ParcelableHelper.writeBoolean(dest, getAnswer());
    }

}
