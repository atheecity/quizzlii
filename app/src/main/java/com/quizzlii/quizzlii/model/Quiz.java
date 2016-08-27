package com.quizzlii.quizzlii.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.quizzlii.quizzlii.model.question.Question;
import java.util.List;

public class Quiz implements Parcelable {

    public static final Creator<Quiz> CREATOR = new Creator<Quiz>() {
        @Override
        public Quiz createFromParcel(Parcel source) {
            return new Quiz(source);
        }

        @Override
        public Quiz[] newArray(int size) {
            return new Quiz[size];
        }
    };

    private final String id;
    private final String name;
    private final int[] scores;
    private final List<Question> questions;
    private final boolean solved;

    public Quiz(String id, String name, int[] scores, List<Question> questions, boolean solved) {
        this.id = id;
        this.name = name;
        this.scores = new int[questions.size()];
        this.questions = questions;
        this.solved = solved;
    }

    protected Quiz(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.scores = in.createIntArray();
        this.questions = in.createTypedArrayList(Question.CREATOR);
        this.solved = in.readByte() != 0;
    }

    public int getScore(Question question) {
        try {
            return scores[questions.indexOf(question)];
        } catch (IndexOutOfBoundsException ioobe) {
            return 0;
        }
    }

    public int getScore() {
        int categoryScore = 0;
        for (int quizScore : scores) {
            categoryScore += quizScore;
        }
        return categoryScore;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeIntArray(this.scores);
        dest.writeTypedList(this.questions);
        dest.writeByte(this.solved ? (byte) 1 : (byte) 0);
    }
}
