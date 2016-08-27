package com.quizzlii.quizzlii.model.question;

import android.os.Parcel;
import android.os.Parcelable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public abstract class Question<A> implements Parcelable {

    public static final Creator<Question> CREATOR = new Creator<Question>() {

        @SuppressWarnings("TryWithIdenticalCatches")
        @Override
        public Question createFromParcel(Parcel source) {
            QuestionType type = QuestionType.values()[source.readInt()];
            try {
                Constructor<? extends Question> constructor = type.getType().getConstructor(Parcel.class);
                return constructor.newInstance(source);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

            throw new UnsupportedOperationException("Could not create Quiz");
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

    private final String questionLibelle;
    private final String quizType;
    private A answer;
    private boolean solved;

    protected Question(String question, A answer, boolean solved) {
        this.questionLibelle = question;
        this.answer = answer;
        this.solved = solved;
        this.quizType = getType().getJsonName();
    }

    protected Question(Parcel in) {
        this.questionLibelle = in.readString();
        this.quizType = getType().getJsonName();
        this.solved = in.readByte() != 0;
    }

    public abstract QuestionType getType();

    public abstract String getStringAnswer();


    @Override
    public int describeContents() {
        return 0;
    }

    public String getQuestion() {
        return questionLibelle;
    }

    public A getAnswer() {
        return answer;
    }

    protected void setAnswer(A answer) {
        this.answer = answer;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.questionLibelle);
        dest.writeString(this.quizType);
        dest.writeByte(this.solved ? (byte) 1 : (byte) 0);
    }

    @Override
    public String toString() {
        return getType() + ": \"" + getQuestion() + "\"";
    }
}
