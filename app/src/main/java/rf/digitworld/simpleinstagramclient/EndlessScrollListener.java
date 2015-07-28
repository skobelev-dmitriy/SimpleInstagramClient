package rf.digitworld.simpleinstagramclient;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.AbsListView;

/**
 * Created by Дмитрий on 14.07.2015.
 */
public abstract   class EndlessScrollListener extends RecyclerView.OnScrollListener {

    private int visibleThreshold=0;
    private  int currentPage=0;
    private int previousTotalItemCount=0;
    private boolean loading=true;
    private int startingPageIndex=0;
    private LinearLayoutManager linearLayoutManager;

    int firstVisibleItem, visibleItemCount, totalItemCount;



    public EndlessScrollListener(LinearLayoutManager linearLayoutManager){
        this.linearLayoutManager=linearLayoutManager;
    }


    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        visibleItemCount=recyclerView.getChildCount();
        totalItemCount=linearLayoutManager.getItemCount();
        firstVisibleItem=linearLayoutManager.findFirstVisibleItemPosition();

        if(loading){
            if(totalItemCount>previousTotalItemCount){
                loading=false;
                previousTotalItemCount=totalItemCount;
            }
        }
        if(!loading&(totalItemCount-visibleItemCount)<=(firstVisibleItem+visibleThreshold)){
            currentPage++;
            onLoadMore(currentPage);
            loading=true;
        }

    }
    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
    }

  /*  public  EndlessScrollListener(int visibleThreshold, int startPage){
        this.visibleThreshold=visibleThreshold;
        this.startingPageIndex=startPage;
        this.currentPage=startPage;
    }*/
    public abstract  void onLoadMore(int page);




}
