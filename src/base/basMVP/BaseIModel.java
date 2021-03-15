package base.basMVP;

public interface BaseIModel {

    interface ObjectBack {
        void success(String result);

        void error(int code);
    }
}
