package code.com.movie_based_app.listener;


import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
/**
 * Created by lihui1 on 2017/12/26.
 */

public class AutoLoadListener implements OnScrollListener {

    public interface AutoLoadCallback{
        void upToLoad();
    }

    private int getLastVisibilePosition = 0;
    private int lastVisibilePositionY = 0;
    private AutoLoadCallback mCallback;
    public AutoLoadListener(AutoLoadCallback callback){
        this.mCallback = callback;
    }
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (scrollState == OnScrollListener.SCROLL_STATE_IDLE){
            if (view.getLastVisiblePosition() == (view.getCount()-1)){
                View v = view.getChildAt(view.getChildCount()-1);
                int location[] = new int[2];
                v.getLocationOnScreen(location);
                int y = location[1];

                if (view.getLastVisiblePosition() != getLastVisibilePosition && lastVisibilePositionY != y){
                    getLastVisibilePosition = view.getLastVisiblePosition();
                    lastVisibilePositionY = y;
                    return;
                } else if (view.getLastVisiblePosition() == getLastVisibilePosition && lastVisibilePositionY == y){
                    mCallback.upToLoad();
                }
            }
            getLastVisibilePosition = 0;
            lastVisibilePositionY = 0;
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }
}
