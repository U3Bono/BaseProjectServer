package base.basMVP;

public class BaseIPresenter<T extends BaseIView> {

    protected T view;

    public void attachView(T view) {
        this.view = view;
    }

    public void detachView() {
        if (view != null) {
            view = null;
        }
    }

}
