package base.basMVP;

import java.util.Map;

public interface BaseIModel {

    interface ObjectBack{
        void success(Object obj);
        void error(int code);
    }
}
