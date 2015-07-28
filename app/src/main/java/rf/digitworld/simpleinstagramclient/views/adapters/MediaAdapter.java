package rf.digitworld.simpleinstagramclient.views.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import it.sephiroth.android.library.picasso.Picasso;
import rf.digitworld.simpleinstagramclient.R;
import rf.digitworld.simpleinstagramclient.models.Comment;
import rf.digitworld.simpleinstagramclient.models.Comments;
import rf.digitworld.simpleinstagramclient.models.Post;
import rf.digitworld.simpleinstagramclient.utils.DateUtils;
import rf.digitworld.simpleinstagramclient.views.MediaViewHolder;

/**
 * Created by Дмитрий on 14.07.2015.
 */
public class MediaAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<Post> posts;
    private Context context;
    public static final String TAG="MediaAdapter";
    private OnItemClickListener listener;
    public interface OnItemClickListener{
        void  onItemClick(View itemView, int position);
    }
    public void setOnItemClicklistener(OnItemClickListener listener){
        this.listener=listener;
    }
    public MediaAdapter (Context context,List<Post> posts){
        this.posts=posts;
        this.context=context;
        Log.d(TAG, "new MediaAdapter");
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Log.d(TAG,"createViewHolder");
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater=LayoutInflater.from(viewGroup.getContext());


        View v = inflater.inflate(R.layout.layout_viewholder, viewGroup, false);
        viewHolder = new MediaViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        Post post=posts.get(position);
        Log.d(TAG, "onBindViewHolder");
        MediaViewHolder vh=(MediaViewHolder) viewHolder;

        Picasso.with(context)
                .load(post.getUser().getProfilePicture())
                .resize(36, 36)
                .into(vh.getAvatar());
        vh.getUserName().setText(post.getUser().getUsername());

          vh.getTime().setText(DateUtils.timeAgo(post.getCreatedTime()));
        try {
            vh.getCaption().setText(post.getCaption().getText());
        } catch (NullPointerException e){
            vh.getCaption().setText("Непереводимый набор символов :(");
           // Log.d(TAG,e.getMessage());
        }

        Picasso.with(context)
                .load(post.getImages().getStandardResolution().getUrl())
                .placeholder(R.mipmap.ic_launcher)
                .into(vh.getImage());
        vh.getLikes().setText("" + post.getLikes().getCount());
        vh.getComents().setText(""+post.getComments().getCount());
        LinearLayout coments_layout=vh.getComentsLayout();
       // TextView com1=(TextView)coments_layout.findViewById(R.id.text_comment);
        List<Comment> comments=post.getComments().getData();
        for (int i = 0; i <4 ; i++) {
            TextView com=new TextView(context);
            //com.setTextColor(Resources.getSystem().getColor(R.color.color_text));
            com.setTextSize(14f);

            String user=comments.get(i).getFrom().getUsername();
            String comment=comments.get(i).getText();
            Spanned sp= Html.fromHtml("<b>"+user+"</b> "+comment);
            com.setText(sp);
            coments_layout.addView(com);
        }

    }




    @Override
    public int getItemCount() {
        return posts.size();
    }
}
